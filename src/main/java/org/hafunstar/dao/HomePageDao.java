package org.hafunstar.dao;

import org.hafunstar.domain.HomePage;
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
public interface HomePageDao {

    /**
     * 根据id查
     * @param code
     * @return
     */
    HomePage findHomePageById(Integer id);

    /**
     * 根据is_new查询 0为刚写完的草稿 1为最新发布 2为旧的发布
     * @param code
     * @return
     */
    HomePage findByStatusCode(Integer code);

    /**
     * 添加一个
     * @param homePage
     */
    int savePageInfo(HomePage homePage);

    /**
     * 更新
     * @param homePage
     * @return
     */
    int changePageInfo(HomePage homePage);

    /**
     * 根据id删除
     * @param id
     */
    int deletePageById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    int deletePageByInt(Integer id);

}
