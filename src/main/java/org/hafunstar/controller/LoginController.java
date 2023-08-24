package org.hafunstar.controller;

import org.hafunstar.domain.*;
import org.hafunstar.exception.ExceptionInfo;
import org.hafunstar.service.AccountService;
import org.hafunstar.service.imp.BlacklistTokenServiceImp;
import org.hafunstar.utils.DigestUtil;
import org.hafunstar.utils.Email;
import org.hafunstar.utils.RandomUtil;
import org.hafunstar.utils.token.JwtUtil;
import org.hafunstar.utils.vercode.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:
 */
@Controller
public class LoginController {

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private BlacklistTokenServiceImp tokenService;
    @Autowired
    public void setTokenService(BlacklistTokenServiceImp tokenService) {
        this.tokenService = tokenService;
    }
    /**
     * 登录
     * @param response
     * @param request
     * @param session
     * @param loginVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> captchaCheck(HttpServletResponse response,HttpServletRequest request, HttpSession session,
                                     @RequestBody LoginVo loginVo
                                     ) throws Exception {


        ResponseData responseData = new ResponseData();
        JwtUtil jwt = new JwtUtil(30);

        List<Captcha> captchaList = (List<Captcha>) session.getAttribute("point");


        CaptchaUtil captchaUtil = new CaptchaUtil();
        if(captchaUtil.checkPoint(loginVo.getPoints(),captchaList)){//对比成功，验证码正确
            try{
                Account account = accountService.accountFindByEmailAndPass(loginVo.getUsername());
                String servicePass = account.getAccountPasswd();
                String clientPass = loginVo.getPassword();
                Integer accountId = account.getAccountId();
                if(DigestUtil.authenticate(clientPass,servicePass)){
                    String tokenService = jwt.createJWT(loginVo.getUsername(),accountId);
                    Cookie cookie = new Cookie("token",tokenService);
                    cookie.setMaxAge(24*60*60*30);
                    response.addCookie(cookie);
                    //验证成功后就删除验证码的缓存
                    session.removeAttribute("point");
                    responseData.putDataValue("info","登录成功");

                }else {
                    responseData.putDataValue("info","用户名或密码错误");
                }
            }catch (NullPointerException e){
                responseData.putDataValue("info","用户名或密码错误");
            }




        }else {
            responseData.putDataValue("info","验证码错误");
        }

        return responseData.getData();
    }

    /**
     * 退出登录状态
     * @return
     */
    @RequestMapping(value = "/exit",method = RequestMethod.GET)
    public String quitLogin(HttpServletRequest request){
        JwtUtil jwt = new JwtUtil();
        String token = jwt.tokenInCookie(request.getCookies());
        //把token加入黑名单
        BlacklistToken blacklistToken = new BlacklistToken();
        blacklistToken.setTokenString(token);
        tokenService.saveBlacklistToken(blacklistToken);
        return "forward:/";
    }

    /**
     * 注册用户
     * @param registerVo
     * @param session
     * @param request
     * @param account
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    @RequestMapping(value = "/regUser",method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> registerUser(@RequestBody RegisterVo registerVo,
                                            HttpSession session,
                                            HttpServletRequest request,
                                            Account account
                                            ) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {

        List<Captcha> captchaList = (List<Captcha>) session.getAttribute("point");
        ResponseData responseData = new ResponseData();
        CaptchaUtil captchaUtil = new CaptchaUtil();
        if(captchaUtil.checkPoint(registerVo.getPoints(),captchaList)){
            if(registerVo.getUsername() != null && registerVo.getPassword() != null && registerVo.getContact() != null){//确定传来的数据不是空
                if(accountService.accountFindByEmailAndPass(registerVo.getContact()) == null){//邮箱没有注册

                    if(accountService.accountFindNameRepeat(registerVo.getUsername()) == null){//用户昵称不重复

                        account.setAccountName(registerVo.getUsername());
                        account.setAccountPasswd(DigestUtil.encodePBE(registerVo.getPassword()));
                        account.setAccountEmail(registerVo.getContact());
                        account.setAccountImgUrl("images/12949415.png");
                        account.setIsActivate(0);
                        accountService.accountSave(account);
                        responseData.putDataValue("info","success");//以上为注册用户

                        Thread t = new Thread(() -> {//新建一个线程发邮件
                            Account a = accountService.accountFindIdByName(registerVo.getContact());
                            String url = "";

                            try {
                                url = request.getRequestURL().toString()+"/atv?uid="+ DigestUtil.idEncrypt(a.getAccountId())+"&r="+DigestUtil.userNameRandom(a.getAccountName());
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            String context = "<p>请点击以下链接以激活账户<a href=\""+url+"\">"+url +"</a>感谢您的支持!<p>";
                            if(Email.sendMail(registerVo.getContact(),context)){

                            }else {
                                Email.sendMail(registerVo.getContact(),context);
                            }

                        });
                        t.start();

                    }else {
                        responseData.putDataValue("info","名字重复，请换一个");
                    }
                }else {
                    responseData.putDataValue("info","该用户已被注册");
                }

            }else {
                responseData.putDataValue("info","提交为空");
            }

        }else {
            responseData.putDataValue("info","验证码错误，验证失败");
        }

        return responseData.getData();

    }

    /**
     * 激活账户
     * @param id
     * @return
     */
    @RequestMapping(value = "/regUser/atv",method = RequestMethod.GET)
    public String activateUser(@RequestParam("uid") String id,@RequestParam("r") String random) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(id != null){
            try{
                Integer.parseInt(id);

            }catch (Exception e){
                e.printStackTrace();
                return "atv_fail";
            }
            Integer uid = DigestUtil.idDecode(id);
            String username = accountService.accountFindNameById(uid);
            String num = DigestUtil.userNameRandom(username);
            //参数中的随机数和本地通过id得出的随机数一样才行
            if(num.equals(random)){
                int result = accountService.accountUpdateActivate(DigestUtil.idDecode(id));
                if(result >0 ){
                    return "atv_success";
                }else {
                    return "atv_fail";
                }
            }else {
                return "atv_fail";
            }


        }else {
            return "atv_fail";
        }

    }

    /**
     *
     * @return 修改密码页面
     */
    @RequestMapping(value = "/setPassword",method = RequestMethod.GET)
    public ModelAndView changePassWordPage(@RequestParam("uname") String username, ModelAndView mv
            , HttpServletRequest request){
        try {
            Account account = accountService.accountFindIdByName(username);
            if(account == null){
                mv.addObject("errorMsg","该用户不存在");
                mv.setViewName("error/error");
                return  mv;
            }
        }catch (NullPointerException e){
            mv.addObject("errorMsg","该用户不存在");
            mv.setViewName("error/error");
            return  mv;
        }
        request.getSession().setAttribute("AccountMail",username);
        Thread t = new Thread(() -> {
            String code = RandomUtil.sixNum();
            request.getSession().setAttribute("Captcha",code);
            String context = "<p>本次的验证码是："+code+"。</p>";
            if(Email.sendMail(username,context)){

            }else {
                Email.sendMail(username,context);
            }

        });
        t.start();
        mv.setViewName("change_password");
        return mv;
    }

    /**
     * 验证验证码，修改密码
     * @param password
     * @param captcha
     * @param request
     * @return
     */
    @RequestMapping(value = "/setPassword",method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> changePassWord(
            @RequestBody ChangePassWd changePassVo,Account account,
            HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {
        String code = (String) request.getSession().getAttribute("Captcha");
        String username = (String) request.getSession().getAttribute("AccountMail");
        ResponseData responseData = new ResponseData();

        if(code.equals(changePassVo.getCaptcha())){

            String passWd = DigestUtil.encodePBE(changePassVo.getPassword());
            account.setAccountEmail(username);
            account.setAccountPasswd(passWd);

            int result = accountService.accountChangePassWd(account);
            if(result > 0){
                responseData.putDataValue("info","修改成功");
                //修改密码后退出登录，重新登录
                Thread t = new Thread(() -> {
                    JwtUtil jwt = new JwtUtil();
                    String token = jwt.tokenInCookie(request.getCookies());
                    //把token加入黑名单
                    BlacklistToken blacklistToken = new BlacklistToken();
                    blacklistToken.setTokenString(token);
                    tokenService.saveBlacklistToken(blacklistToken);
                });
                t.start();

            }else {
                responseData.putDataValue("info","修改失败");
            }

        }else {
            responseData.putDataValue("info","验证码错误");
        }
        return responseData.getData();
    }


}
