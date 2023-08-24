package org.hafunstar.dao;

import org.apache.ibatis.annotations.Param;
import org.hafunstar.domain.SonComment;
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
public interface SonCommentDao {
    /**
     * 根据父评论id查询子评论
     * @return

    List<SonComment> findFidToComment(int fid);*/
    /**
     * 根据父评论id删除子评论
     * @return
     */
    int deleteSonCommentByFid(@Param("sid")Integer[] id);
    /**
     * 根据id查询子评论
     * @return
     */
    int deleteSonCommentById(@Param("sid")Integer[] id);

    int findSonComCountByCid(@Param("cid")int[] id);

    int findSonCommentCount();

    int saveSonComment(SonComment sonComment);
}
