package org.hafunstar.dao;

import org.apache.ibatis.annotations.Param;
import org.hafunstar.domain.Comment;
import org.springframework.stereotype.Repository;

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
@Repository
public interface CommentDao {

    /**
     * 查询所有评论
     * @return
     */
    List<Comment> findCommentAll(@Param(value="pos") Integer startPos, @Param(value="size") Integer pageSize);

    int saveComment(Comment comment);

    /**
     * 根据id删除评论
     * @param id
     */
    int deleteCommentById(@Param("cid")Integer[] id);

    /**
     * 查询评论总数
     * @return
     */
    int findCommentCount();

    int findCommentCountByPid(int id);

    List<Comment> findCommentByPageId(
            @Param(value="pos") Integer startPos,
            @Param(value="size") Integer pageSize,
            @Param(value="id")Integer pid);

    List<Comment> findCommentByAccount(String username);

    List<Comment> findCommentByGame(
            @Param(value="pos") Integer startPos,
            @Param(value="size") Integer pageSize,
            @Param(value="name")String gameName);


    List<Comment> findCommentById(int id);

    List<Comment> findCidByPid(int id);

    int [] findCidByTop(
            @Param(value="pos") Integer startPos,
            @Param(value="size") Integer pageSize);
}
