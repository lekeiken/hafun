package org.hafunstar.controller.admin;

import org.hafunstar.domain.Account;
import org.hafunstar.domain.Comment;
import org.hafunstar.domain.CommentIdArray;
import org.hafunstar.domain.ResponseData;
import org.hafunstar.exception.ExceptionInfo;
import org.hafunstar.service.CommentService;
import org.hafunstar.utils.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
public class CommentController {

    private CommentService commentService;
    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 分页
     * @param current
     * @param mv
     * @return
     */
    @RequestMapping(value = "/manager/CommentPage",method = RequestMethod.GET)
    public ModelAndView commentPage(@RequestParam("pageNow") Integer current,
                                    @RequestParam("SearchKeyword")String key,
                                    @RequestParam("pageCount") Integer pageCount,
                                    @RequestParam("Search")String ser, ModelAndView mv){

        List<Comment> comments;
        if(ser.equals("All")){
            int count = commentService.commentFindCount();
            Page page = new Page(count,current);
            comments = commentService.mainCommentFindAll(page.getBeginIndex(), page.getPageSize());
            mv.addObject("comment_list",comments);
            mv.addObject("search","All");
            mv.addObject("page",page);
        }else if(ser.equals("Game")){

            Page page = new Page(pageCount,current);
            comments = commentService.findCommentByGameName(page.getBeginIndex(), page.getPageSize(),key);
            List<Comment> commentList = (List<Comment>) comments.get(0);//获得集合
            mv.addObject("comment_list",commentList);
            mv.addObject("search","Game");
            mv.addObject("searchName",key);
            mv.addObject("page",page);
        }

        mv.setViewName("admin/comment_list");
        return mv;
    }

    @RequestMapping(value = "/manager/CommentSearch",method = RequestMethod.GET)
    public ModelAndView accountSearchPage(@RequestParam("o")String a,@RequestParam("t")String b,ModelAndView mv){


        List<Comment> comments;

        if(!b.equals("")){
            if(a.equals("account_name")){

                comments = commentService.findCommentByUserName(b);
                mv.addObject("comment_list",comments);
            }else if (a.equals("game_name")){
                int index = 0;
                int size = 10;
                comments = commentService.findCommentByGameName(index,size,b);
                List<Comment> commentList = (List<Comment>) comments.get(0);//获得集合
                Integer total =((List<Integer>) comments.get(1)).get(0);//获得总数
                Page page = new Page(total);
                mv.addObject("comment_list",commentList);
                mv.addObject("search","Game");
                mv.addObject("searchName",b);
                mv.addObject("page",page);
            }else if (a.equals("comment_id")){

                comments = commentService.findCommentById(Integer.parseInt(b));
                mv.addObject("comment_list",comments);
            }
        }
        mv.setViewName("admin/comment_list");
        return mv;
    }

    @RequestMapping(value = "/manager/BatchCommentDel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> batchCommentDel(@RequestBody CommentIdArray idsDel) throws ExceptionInfo {

        ResponseData data = new ResponseData();

        int result = commentService.deleteCommentById(idsDel.getIds(),idsDel.getS_ids());
        data.putDataValue("info",result>=0? "删除成功":"删除失败");
        return data.getData();
    }
    @RequestMapping(value = "/manager/ACommentDel",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> aCommentDel(@RequestParam("cid")Integer id) throws ExceptionInfo {

        ResponseData data = new ResponseData();

        int result = commentService.deleteCommentById(id);
        data.putDataValue("info",result>=0? "删除成功":"删除失败");
        return data.getData();
    }
    @RequestMapping(value = "/manager/ASubCommentDel",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> aSubCommentDel(@RequestParam("sid")Integer id) throws ExceptionInfo {

        ResponseData data = new ResponseData();

        Integer []i={id};
        int result = commentService.deleteCommentById(i);
        data.putDataValue("info",result>=0? "删除成功":"删除失败");
        return data.getData();
    }

}
