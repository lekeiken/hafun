package org.hafunstar.controller.admin;

import org.hafunstar.domain.*;
import org.hafunstar.exception.ExceptionInfo;
import org.hafunstar.service.AccountService;
import org.hafunstar.service.CommentService;
import org.hafunstar.service.HistoryService;
import org.hafunstar.service.ManagerService;
import org.hafunstar.service.imp.BlacklistTokenServiceImp;
import org.hafunstar.utils.DigestUtil;
import org.hafunstar.utils.paging.Page;
import org.hafunstar.utils.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
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
@RequestMapping("/manager")
public class AdminController {

    private ManagerService managerService;
    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private CommentService commentService;
    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    private HistoryService historyService;
    @Autowired
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    private BlacklistTokenServiceImp tokenService;
    @Autowired
    public void setTokenService(BlacklistTokenServiceImp tokenService) {
        this.tokenService = tokenService;
    }


    /**
     * 后台入口
     * @return 后台登录页面
     */
    @RequestMapping("/0")
    public String managerA(){
        return "admin/managerial";
    }

    /**
     *
     * @return 添加管理员页面
     */
    @RequestMapping("/addAdminPage")
    public String managerAdd(){ return "admin/add_admin"; }
    /**
     *验证登录
     * @return
     */
    @RequestMapping(value = "/adminLogin")
    public String  adminLoginRequest(Manager manager, @RequestParam("Captcha") String code,
                               HttpServletRequest request, HttpServletResponse response,Model model)
            throws Exception {

        String serverCheckcode = "";
        //从服务器的session中取出验证码
        serverCheckcode  = (String) request.getSession().getAttribute("verificationCode");

        if(code != null){
            //将客户端验证码和服务器端验证比较，如果相等，则表示验证通过
            if (serverCheckcode.equals(code.toLowerCase())) {
                //再验证密码
                String clientPassword = manager.getManagerPassword();
                String servicePassword = managerService.login(manager.getManagerName());
                if(DigestUtil.authenticate(clientPassword,servicePassword)){
                    JwtUtil jwt = new JwtUtil();
                    String token = jwt.createJWT(manager.getManagerName());
                    Cookie cookie = new Cookie("token",token);
                    response.addCookie(cookie);
                    //验证成功后就删除验证码的缓存
                    request.getSession().removeAttribute("verificationCode");
                    return "redirect:/manager/admin";
                }else{
                    model.addAttribute("loginInfo","用户名或密码错误");
                    return "admin/managerial";
                }
            }else {
                model.addAttribute("loginInfo","验证码错误");
                return "admin/managerial";
            }
        }else {
            model.addAttribute("loginInfo","验证码为空");
            return "admin/managerial";
        }


    }


    /**
     * 登录成功页面
     * @return 后台登录页面
     */
    @RequestMapping("/admin")
    public String adminMain(HttpServletRequest request, HttpServletResponse response,Model model){

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("managerAdmin");
        model.addAttribute("commentCount",commentService.commentAndSonFindCount());
        model.addAttribute("adminNikeName",managerService.getNickname(username));
        model.addAttribute("accountCount",accountService.getAccountCount()+"");
        model.addAttribute("systemVersion",System.getProperty("os.version"));
        //将该管理员登录时间记录到数据库
        managerService.updateLastLoginTime(username);
        return "admin/adminMain";
    }

    /**
     * 对当前管理员操作：退出，修改密码以及左侧菜单栏等操作
     * @param ac
     * @return
     */
    @RequestMapping("/adminAction/{ac}")
    public ModelAndView adminMenuRequest(@PathVariable("ac") String ac, HttpServletRequest request,ModelAndView mv ){

        if(ac.equals("update")){//修改密码

            mv.setViewName("admin/revise_passwd");

        }else if(ac.equals("homeedit")){

            mv.setViewName("admin/home_edit");

        }else if(ac.equals("history")){

            List<History> histories;
            int count = historyService.findHisCount();
            Page page = new Page(count);
            histories = historyService.findHistoryAll();
            mv.addObject("his_list",histories);
            mv.addObject("page",page);
            mv.setViewName("admin/history_list");

        } else if(ac.equals("comment")){

            List<Comment> comments;
            int count = commentService.commentFindCount();
            Page page = new Page(count);
            comments = commentService.mainCommentFindAll(page.getBeginIndex(), page.getPageSize());
            mv.addObject("comment_list",comments);
            mv.addObject("search","All");
            mv.addObject("page",page);
            mv.setViewName("admin/comment_list");

        }else if(ac.equals("user")){

            List<Account> accountList;
            int count = accountService.getAccountCount();
            Page page = new Page(count);
            accountList = accountService.accountFindAll(page.getBeginIndex(), page.getPageSize());
            mv.addObject("account_list",accountList);
            mv.addObject("page",page);
            mv.setViewName("admin/user_list");

        }else if(ac.equals("message")){

            mv.setViewName("admin/messagebod");

        }else if(ac.equals("upload")){

            mv.setViewName("admin/upload_img");

        }else if(ac.equals("admin_set")){
            List<Manager> managers;
            //查出总记录条数
            int count = managerService.getAdminsCount();
            Page page = new Page(count);
            managers = managerService.managerFindAll(page.getBeginIndex(), page.getPageSize());
            mv.addObject("admin_list",managers);
            mv.addObject("page",page);

            mv.setViewName("admin/admin_list");

        }else if(ac.equals("log")){

            mv.setViewName("admin/log_list");

        }else if(ac.equals("quit")){//退出登录

            JwtUtil jwt = new JwtUtil();
            //删除全局管理员用户名
            request.getSession().removeAttribute("managerAdmin");
            //设置token失效
            String token = jwt.tokenInCookie(request.getCookies());
            //把token加入黑名单
            BlacklistToken blacklistToken = new BlacklistToken();
            blacklistToken.setTokenString(token);
            tokenService.saveBlacklistToken(blacklistToken);
            mv.setViewName("redirect:/manager/0");
        }else{
            mv.setViewName("forward:/error/1");
        }
        return mv;
    }

    /**
     * 修改本管理员的密码
     * @param oldPass
     * @param newPass
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/adminChangePW")
    public Map<String, Object> adminChangePassword(@RequestParam("oldPassword")String oldPass,
          @RequestParam("newPassword")String newPass,HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {

        ResponseData data = new ResponseData();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("managerAdmin");
        String servicePassword = managerService.login(username);
        String clientPassword = DigestUtil.encodePBE(oldPass);
        if(DigestUtil.authenticate(clientPassword,servicePassword)){

            Manager manager = new Manager();
            manager.setManagerPassword(DigestUtil.encodePBE(newPass));
            int result = managerService.changePassword(manager);
            data.putDataValue("info",result>0? "修改成功":"修改失败");
        }else {

            data.putDataValue("info","旧密码错误，修改失败");
        }

        return data.getData();
    }

    /**
     * 接受分页信息并处理返回
     * @param current 请求的第几页
     * @param mv
     * @return
     */
    @RequestMapping(value = "/AdminPage",method = RequestMethod.GET)
    public ModelAndView adminPage(@RequestParam("pageNow") String current,ModelAndView mv){

        List<Manager> managers = new ArrayList<>();
        int count = managerService.getAdminsCount();
        Page p = new Page(count,Integer.parseInt(current));
        managers = managerService.managerFindAll(p.getBeginIndex(), p.getPageSize());
        mv.addObject("admin_list",managers);
        mv.addObject("page",p);
        mv.setViewName("admin/admin_list");

        return mv;
    }

    /**
     * 删除管理员
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/adminDel",method = RequestMethod.GET)
    public Map<String, Object> adminDel(String[] ids) throws ExceptionInfo {

        ResponseData data = new ResponseData();

        if(ids != null){
            int[] id = new int[ids.length];
            for(int i = 0;i<ids.length;i++){

                id[i]=Integer.parseInt(ids[i]);
                //超级管理员无法删除
                if(id[i] == 1){
                    id[i] = -2;
                }
            }

            int result = managerService.deleteAdminByBatch(id);

            data.putDataValue("info",result>0? "删除成功":"删除失败");
        }else {
            throw new ExceptionInfo("提交参数不能为空！");
        }
        return data.getData();
    }

    /**
     * 添加管理员
     */
    @ResponseBody
    @RequestMapping("/AddAdmin")
    public Map<String, Object> adminAdd(Manager manager) throws ExceptionInfo, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {

        ResponseData data = new ResponseData();

        if(!manager.getManagerName().equals("")&&
                !manager.getManagerNickname().equals("")&&
                !manager.getManagerPassword().equals("")){
            String a = manager.getManagerPassword();
            manager.setManagerPassword(DigestUtil.encodePBE(a));//加密 密码
            int result = managerService.addAdminOne(manager);
            data.putDataValue("info",result>0? "添加成功":"添加失败");
        }else {
            throw new ExceptionInfo("输入不能为空！");
        }
        return data.getData();
    }

    @ResponseBody
    @RequestMapping(value = "/Update_Admin",method = RequestMethod.POST)
    public Map<String, Object> adminUpdate(Manager manager) throws ExceptionInfo {

        ResponseData data = new ResponseData();
        Integer id = manager.getManagerId();
        if(manager.getManagerNickname().equals("")){
            String s = managerService.managerFindNicknameById(id);
            manager.setManagerNickname(s);
        }
        if(manager.getManagerName().equals("")){

            String s = managerService.managerFindNameById(id);
            manager.setManagerName(s);
        }
        if(manager.getManagerPassword().equals("")){

            String s = managerService.managerFindPasswdById(id);
            manager.setManagerPassword(s);
        }else {
            String pw = manager.getManagerPassword();
            manager.setManagerPassword(DigestUtil.getAdminString(pw));
        }
        int result = managerService.managerChange(manager);
        data.putDataValue("info",result>0? "修改成功":"修改失败");
        return data.getData();
    }
}
