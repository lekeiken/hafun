package org.hafunstar.controller;

import org.apache.ibatis.jdbc.Null;
import org.hafunstar.domain.*;
import org.hafunstar.service.AccountService;
import org.hafunstar.service.CommentService;
import org.hafunstar.service.HomePageService;
import org.hafunstar.utils.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
public class IndexController {

    private HomePageService homePageService;
    @Autowired
    public void setHomePageService(HomePageService homePageService) {
        this.homePageService = homePageService;
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

    /**
     * 网站首页纯净版
     * @param request
     * @param mv
     * @return
     */
    @RequestMapping(value = "/")
    public ModelAndView indexPure(ModelAndView mv){

        HomePage homePage = homePageService.findHomePageByNew(0);
        mv.addObject("HomePage",homePage);

        mv.setViewName("index");
        return mv;
    }
    /**
     * 网站首页
     * @param request
     * @param mv
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request, ModelAndView mv){

        String username = "";
        try {
            username = (String)request.getSession().getAttribute("username");
        }catch (NullPointerException e){
            mv.setViewName("index_not");
        }

        HomePage homePage = homePageService.findHomePageByNew(0);
        mv.addObject("HomePage",homePage);
        if(username.equals("")){

            mv.setViewName("index_not");
        }else{

            List<Account> accounts = accountService.accountFindAsId(username);
            Account account = new Account();
            if (accounts != null && accounts.size() > 0) {
                account = accounts.get(0);
            }
            mv.addObject("img_url",account.getAccountImgUrl());
            mv.addObject("u_id",account.getAccountId());
            mv.setViewName("index_have");
        }


        return mv;
    }

    /**
     * 请求登录页面并返回
     * @return
     */
    @RequestMapping(value = "/LoginPage")
    public String loginPage(){
        return "login";
    }

    /**
     * 请求注册页面并返回
     * @return
     */
    @RequestMapping(value = "/RegisterPage")
    public String registerPage(){
        return "register";
    }

    /**
     * 加载所有评论
     * @return
     */
    @RequestMapping(value = "/Comment/{id}")
    public ModelAndView commentAll(@PathVariable("id") Integer pid,HttpServletRequest request,ModelAndView mv){
        String username = "";
        username = (String)request.getSession().getAttribute("username");

        if(username.equals("")){
            //先得到前3个评论加子评论的总数
            int count = commentService.findCommentCountByTop(0,3);
            //再用总数查所有记录
            List<Comment> comments = commentService.findCommentByPageId(0,count,pid);

            mv.addObject("CommentList",comments);
            mv.setViewName("comment_page_not");
        }else {
            int count = commentService.findCommentCountByTop(0,5);
            List<Comment> comments = commentService.findCommentByPageId(0,count,pid);

            Integer total = commentService.commentFindCount(pid);
            Page page = new Page(total,1,5);

            mv.addObject("CommentList",comments);
            mv.addObject("page",page);
            mv.addObject("tc",count);//当前页父评论加子评论总共多少条  下一页查询用
            mv.addObject("p_id",pid);

            mv.setViewName("comment_page_have");
        }
        return mv;

    }

    /**
     *
     * @param current当前页
     * @param pageCount总页数
     * @param mv
     * @return
     */
    @RequestMapping(value = "/cmtPage")
    public ModelAndView commentAll(@RequestParam("pageNow") Integer current,
                                   @RequestParam("FPageCount") Integer pageCount,
                                   @RequestParam("SPageCount") Integer sonPageCount,
                                   @RequestParam("pid") Integer pid,
                                   ModelAndView mv){
        Page page = new Page(pageCount,current,5);
        int count = commentService.findCommentCountByTop(page.getBeginIndex(), page.getPageSize());
        List<Comment> comments = commentService.findCommentByPageId(sonPageCount,count,pid);
        mv.addObject("CommentList",comments);
        mv.addObject("page",page);
        mv.addObject("tc",count);//当前页父评论加子评论总共多少条  下一页查询用
        mv.addObject("p_id",pid);

        mv.setViewName("comment_page_have");

        return mv;

    }

    @RequestMapping(value = "CMT/main",method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> commentMain(@RequestParam("pid") Integer pid,
                                    HttpSession session,
                                    Comment comment,
                                    Account account,
                                    HomePage homePage,
                                    @RequestParam("context") String context){

        ResponseData responseData = new ResponseData();
        Integer uid = (Integer)session.getAttribute("user_id");//得到评论人id

        if(uid != null){
            account.setAccountId(uid);
            homePage.setPageId(pid);
            comment.setAccount(account);
            comment.setHomePage(homePage);
            comment.setCommentContent(context);
            comment.setCommentTime(new Date());
            comment.setCommentShow(0);


            if(commentService.saveComment(comment) > 0){

                responseData.putDataValue("info","发布成功");
            }else {
                responseData.putDataValue("info","发布失败");
            }
        }else {
            responseData.putDataValue("info","发布失败");
        }

        return responseData.getData();
    }

    /**
     * 保存评论到子评论
     * @param pid页面id
     * @param rid回复给谁
     * @param cid父评论id
     * @param context
     * @return
     */
    @RequestMapping(value = "CMT/vice",method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> commentVice(HttpSession session,
                                    SonComment sonComment,
                                    @RequestParam("replyid") Integer rid,
                                    @RequestParam("cid") Integer cid,
                                    @RequestParam("context") String context){
        Integer uid = (Integer)session.getAttribute("user_id");

        ResponseData responseData = new ResponseData();
        if(uid != null){
            Account accountR = new Account();
            accountR.setAccountId(rid);
            Account account = new Account();
            account.setAccountId(uid);
            sonComment.setComUser(account);
            sonComment.setReplyUser(accountR);
            sonComment.setCommentContent(context);
            sonComment.setCommentTime(new Date());
            sonComment.setFatherCommentId(cid);
            sonComment.setSonCommentShow(0);


            if(commentService.saveComment(sonComment) > 0){

                responseData.putDataValue("info","发布成功");
            }else {
                responseData.putDataValue("info","发布失败");
            }
        }else {
            responseData.putDataValue("info","发布失败");
        }

        return responseData.getData();
    }

    /**
     * 返回个人中心页面
     * @param uid
     * @return
     */
    @RequestMapping(value = "profile/{id}",method = RequestMethod.GET)
    public String personalPage(@PathVariable String uid){

        return "profile";
    }

    /**
     * 上传头像
     * @return
     */
    @RequestMapping(value = "upImg",method = RequestMethod.GET)
    public String personalPage(){

        return "user_upload";
    }

}
