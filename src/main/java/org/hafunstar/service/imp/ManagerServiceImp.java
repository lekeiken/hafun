package org.hafunstar.service.imp;

import org.hafunstar.dao.ManagerDao;
import org.hafunstar.domain.Manager;
import org.hafunstar.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class ManagerServiceImp implements ManagerService {

    private ManagerDao managerDao;
    @Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }


    /**
     * 在数据库中查到记录返回真，授权登录
     * @param manager
     * @return
     */
    public String login(String name) {
        if(managerDao.findManagerLogin(name) == null){
            return "";
        }else {
            return managerDao.findManagerLogin(name);
        }
    }

    /**
     * 获得总记录条数
     * @return
     */
    public int getAdminsCount(){
        return managerDao.adminCount();
    }
    /**
     * 通过用户名得到昵称
     * @param name
     * @return
     */
    public String getNickname(String name){

       return managerDao.nameToNickname(name).getManagerNickname();

    }

    /**
     * 分页列出管理员表
     * @return
     */
    public List<Manager> managerFindAll(int startPos,int pageSize) {

        return managerDao.findManagerAll(startPos,pageSize);
    }

    @Override
    public String managerFindNicknameById(int id) {
        return managerDao.findManagerNicknameById(id);
    }

    @Override
    public String managerFindNameById(int id) {
        return managerDao.findManagerNameById(id);
    }

    @Override
    public String managerFindPasswdById(int id) {
        return managerDao.findManagerPasswdById(id);
    }

    /**
     * 修改密码
     * @param manager
     */
    public int changePassword(Manager manager){
        try {

            return managerDao.updatePasswd(manager);
        }catch (Exception e){

            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public int managerChange(Manager manager) {
        try {

            return managerDao.changeManager(manager);
        }catch (Exception e){

            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 更新管理员最后登录时间
     * @param name
     */
    public int updateLastLoginTime(String name) {

        try {
            return managerDao.updateLastTime(name);
        }catch (Exception e){

            e.printStackTrace();
            return -1;
        }

    }

    /**
     * 批量删除管理员
     * @param array
     * @return
     */
    public int deleteAdminByBatch(int[] array){

        try {
            return managerDao.managerDeleteByBatch(array);
        }catch (Exception e){

            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public int addAdminOne(Manager manager) {

        try {
            return managerDao.managerSave(manager);
        }catch (Exception e){

            e.printStackTrace();
            return -1;
        }
    }

}
