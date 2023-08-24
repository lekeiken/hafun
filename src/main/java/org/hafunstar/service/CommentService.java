package org.hafunstar.service;

import org.hafunstar.domain.Comment;
import org.hafunstar.domain.SonComment;

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

public interface CommentService {

    List<Comment> mainCommentFindAll(int beginIndex, int pageSize);

    int commentFindCount();

    int commentFindCount(int pid);

    int commentAndSonFindCount();

    int saveComment(Comment comment);
    int saveComment(SonComment comment);

    List<Comment> findCommentByPageId(int beginIndex, int pageSize,int pid);

    List<Comment> findCommentByUserName(String accountName);

    List<Comment> findCommentByGameName(int beginIndex, int pageSize,String gameName);

    List<Comment> findCommentById(int id);

    int findCommentCountByTop(int index,int size);

    int deleteCommentById(Integer []com,Integer [] s_com);
    int deleteCommentById(Integer [] s_com);
    int deleteCommentById(Integer commentId);
}
