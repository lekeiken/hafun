package org.hafunstar.controller.admin;

import org.hafunstar.domain.Account;
import org.hafunstar.domain.ResponseData;
import org.hafunstar.exception.ExceptionInfo;
import org.hafunstar.service.AccountService;
import org.hafunstar.utils.DigestUtil;
import org.hafunstar.utils.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
public class AccountController {
    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     *根据参数返回分页信息
     * @param current
     * @param mv
     * @return
     */
    @RequestMapping(value = "/manager/UserPage",method = RequestMethod.GET)
    public ModelAndView accountPage(@RequestParam("pageNow") String current, ModelAndView mv){

        List<Account> accounts;
        int count = accountService.getAccountCount();
        Page page = new Page(count,Integer.parseInt(current));
        accounts = accountService.accountFindAll(page.getBeginIndex(), page.getPageSize());

        mv.addObject("account_list",accounts);
        mv.addObject("page",page);
        mv.setViewName("admin/user_list");
        return mv;
    }

    /**
     *查询搜索指定的用户
     * @param a查询依据
     * @param b查询值
     * @param mv
     * @return
     */
    @RequestMapping(value = "/manager/UserSearch",method = RequestMethod.GET)
    public ModelAndView accountSearchPage(@RequestParam("o")String a,@RequestParam("t")String b,ModelAndView mv){


        List<Account> accounts = new ArrayList<>();

        if(!b.equals("")){
            if(a.equals("id")){

                accounts = accountService.accountFindAsId(Integer.parseInt(b));
            }else if (a.equals("name")){

                accounts = accountService.accountFindAsName(b);
            }else if (a.equals("phone")){

                accounts = accountService.accountFindAsId(b);
            }
        }


        Page page = new Page(accounts.size());
        mv.addObject("account_list",accounts);
        mv.addObject("page",page);
        mv.setViewName("admin/user_list");
        return mv;
    }

    /**
     * 添加用户
     * @param account
     * @return
     * @throws ExceptionInfo
     */
    @ResponseBody
    @RequestMapping(value = "/manager/AddAccount",method = RequestMethod.POST)
    public Map<String, Object> accountAdd(Account account) throws ExceptionInfo, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {

        ResponseData data = new ResponseData();
        if(!account.getAccountName().equals("")&&
                !account.getAccountPasswd().equals("")&&
                !account.getAccountEmail().equals("")) {
            String pw = account.getAccountPasswd();
            account.setAccountPasswd(DigestUtil.encodePBE(pw));//加密 密码
            int result = accountService.accountSave(account);
            data.putDataValue("info", result > 0 ? "添加成功" : "添加失败");

        }else {
            throw new ExceptionInfo("输入不能为空！");
        }
        return data.getData();
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/manager/userDel",method = RequestMethod.GET)
    public Map<String, Object> accountDel(String[] ids) throws ExceptionInfo {

        ResponseData data = new ResponseData();

        if(ids != null){
            int[] id = new int[ids.length];
            for(int i = 0;i<ids.length;i++){
                Integer n =Integer.parseInt(ids[i]);
                id[i] = n;
            }

            int result = accountService.accountDelete(id);

            data.putDataValue("info",result>0? "删除成功":"删除失败");
        }else {
            throw new ExceptionInfo("提交参数不能为空！");
        }
        return data.getData();
    }

    @RequestMapping("/manager/AddUserPage")
    public String addNewUser(){

        return "admin/add_user";
    }

    /**
     * 修改客户信息
     * @param account
     * @return
     * @throws ExceptionInfo
     */
    @ResponseBody
    @RequestMapping(value = "/manager/Edit_User",method = RequestMethod.POST)
    public Map<String, Object> userChange(Account account) throws ExceptionInfo, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {

        ResponseData data = new ResponseData();
        Integer id = account.getAccountId();
        Account ac = new Account();
        List<Account> accounts =  accountService.accountFindAsId(id);
        if(!account.getAccountName().equals("")){
            if(accounts.size() >0){
                ac = accounts.get(0);
                ac.setAccountName(account.getAccountName());
            }

        }

        if(!account.getAccountEmail().equals("")){
            if(accounts.size() >0){
                ac = accounts.get(0);
                ac.setAccountEmail(account.getAccountEmail());
            }
        }
        if(!account.getIsActivate().equals("")){
            if(accounts.size() >0){
                ac = accounts.get(0);
                ac.setIsActivate(account.getIsActivate());
            }
        }
        int result = accountService.accountChange(account);
        data.putDataValue("info",result>0? "修改成功":"修改失败");
        return data.getData();
    }
}
