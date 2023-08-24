package org.hafunstar.dao;

import org.apache.ibatis.annotations.Param;
import org.hafunstar.domain.Account;
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
public interface AccountDao {

    /**
     * 查询所有
     * @return
     */
    List<Account> findAccountAll(@Param(value="pos") Integer startPos,@Param(value="size") Integer pageSize);

    /**
     * 登录验证用户名密码
     * @param account
     * @return
     */
    Account findAccountByEmailAndPasswd(String name);

    /**
     * 验证用户名是否重复
     * @param name
     * @return
     */
    Account findNameRepeat(String name);

    /**
     * 根据id查用户
     * @param id
     * @return
     */
    List<Account> findAccountById(int id);

    /**
     * 根据用户名查用户
     * @param name
     * @return
     */
    List<Account> findAccountByName(String name);

    /**
     * 根据注册信息查用户
     * @param phone
     * @return
     */
    List<Account> findAccountByEmail(String phone);

    /**
     * 根据id查客户信息
     * @param id
     * @return
     */
    String findAccountNameById(int id);

    String findAccountPasswdById(int id);

    String findAccountEmailById(int id);

    Integer findAccountActivateById(int id);
    /**
     * 添加一个用户
     * @param account
     */
    int saveAccount(Account account);

    /**
     * 更新用户头像图片链接地址
     * @param account
     */
    int updateAccountImgUrl(Account account);

    /**
     * 修改客户信息
     * @param account
     * @return
     */
    int changeAccount(Account account);

    int changeAccountPassword(Account account);

    /**
     * 删除一个，或者批量删除
     * @param uid
     */
    int deleteAccount(@Param("uid")int[] id);

    /**
     * 根据用户id查询图片url
     * @param uid
     * @return
     */
    Account findIdToImgUrl(int uid);

    /**
     * 查总记录条数
     * @return
     */
    int findUserCount();

    /**
     * 通过邮箱查id
     * @param name
     * @return
     */
    Account findIdByName(String name);

    /**
     * 更新激活状态
     * @param id
     * @return
     */
    int updateAccountActivate(int id);
}
