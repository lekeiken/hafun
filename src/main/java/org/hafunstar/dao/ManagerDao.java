package org.hafunstar.dao;

import org.apache.ibatis.annotations.Param;
import org.hafunstar.domain.Manager;
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
public interface ManagerDao {

    /**
     * 登录查询
     * @param manager
     * @return
     */
    String findManagerLogin(String name);

    /**
     * 查询所有管理员
     * @return
     */
    List<Manager> findManagerAll(@Param(value="pos") Integer startPos,@Param(value="size") Integer pageSize);

    /**
     *根据id查昵称
     * @param id
     * @return
     */
    String findManagerNicknameById(int id);

    /**
     *根据id查管理员名
     * @param id
     * @return
     */
    String findManagerNameById(int id);

    /**
     *根据id查密码
     * @param id
     * @return
     */
    String findManagerPasswdById(int id);

    /**
     * 保存一个管理员
     */
    int managerSave(Manager manager);

    /**
     * 删除一个管理员
     */
    int managerDeleteByBatch(@Param("ids")int[] ids);

    /**
     * 修改密码
     * @param manager
     */
    int updatePasswd(Manager manager);

    /**
     * 修改管理员信息
     * @param manager
     * @return
     */
    int changeManager(Manager manager);
    /**
     *更新管理员登录时间
     * @param name
     */
    int updateLastTime(String name);

    /**
     *根据用户查信息
     * @param name
     * @return
     */
    Manager nameToNickname(String name);

    /**
     * 通过管理员名查id ，id=1就是超级管理员
     * @param name
     * @return
     */
    Integer adminIsRoot(String name);

    /**
     * 总记录条数
     */
    Integer adminCount();
}
