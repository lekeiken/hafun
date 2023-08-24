package org.hafunstar.service.imp;

import org.hafunstar.dao.AccountDao;
import org.hafunstar.dao.CommentDao;
import org.hafunstar.dao.SonCommentDao;
import org.hafunstar.domain.Comment;
import org.hafunstar.domain.SonComment;
import org.hafunstar.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:
 */
@Service
public class CommentServiceImp implements CommentService {


    private CommentDao commentDao;
    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    private SonCommentDao sonCommentDao;
    @Autowired
    public void setSonCommentDao(SonCommentDao sonCommentDao) {
        this.sonCommentDao = sonCommentDao;
    }


    @Override
    public List<Comment> mainCommentFindAll(int beginIndex, int pageSize) {
        return commentDao.findCommentAll(beginIndex,pageSize);
    }

    @Override
    public int commentFindCount() {
        return commentDao.findCommentCount();
    }

    @Override
    public int commentFindCount(int pid) {

        return commentDao.findCommentCountByPid(pid);
    }

    @Override
    public int commentAndSonFindCount() {

        return commentDao.findCommentCount()+sonCommentDao.findSonCommentCount();
    }

    @Override
    public int saveComment(Comment comment) {
        return commentDao.saveComment(comment);
    }

    @Override
    public int saveComment(SonComment comment) {
        return sonCommentDao.saveSonComment(comment);
    }


    @Override
    public List<Comment> findCommentByUserName(String accountName) {
        return commentDao.findCommentByAccount(accountName);
    }

    @Override
    public List<Comment> findCommentByPageId(int beginIndex, int pageSize,int pid) {
        return commentDao.findCommentByPageId(beginIndex,pageSize,pid);
    }

    @Override
    public List<Comment> findCommentByGameName(int beginIndex, int pageSize,String gameName) {
        return commentDao.findCommentByGame(beginIndex,pageSize,gameName);
    }

    @Override
    public List<Comment> findCommentById(int id) {
        return commentDao.findCommentById(id);
    }

    @Override
    @Transactional
    public int deleteCommentById(Integer[] comment, Integer[] s_com) {

        try {
            if(deleteCommentById(s_com) == -1){

                throw new SQLException("Failed to delete subcomments");
            }

            commentDao.deleteCommentById(comment);

            return sonCommentDao.deleteSonCommentByFid(comment);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    @Transactional
    public int deleteCommentById(Integer commentId) {

        try {
            Integer [] c = {commentId};
            commentDao.deleteCommentById(c);

            return sonCommentDao.deleteSonCommentByFid(c);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deleteCommentById(Integer[] s_com) {

        try {
            return sonCommentDao.deleteSonCommentById(s_com);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    @Transactional
    @Override
    public int findCommentCountByTop(int index,int size){

        int[] count = commentDao.findCidByTop(index,size);
        return sonCommentDao.findSonComCountByCid(count)+size;
    }


}
