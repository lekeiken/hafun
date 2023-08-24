package org.hafunstar.service;


import org.hafunstar.domain.Manager;

import org.springframework.stereotype.Service;

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
public interface ManagerService {

    List<Manager> managerFindAll(int start,int size);

    String managerFindNicknameById(int id);

    String managerFindNameById(int id);

    String managerFindPasswdById(int id);

    int getAdminsCount();

    String login(String username);

    String getNickname(String name);

    int changePassword(Manager manager);

    int managerChange(Manager manager);

    int updateLastLoginTime(String name);

    int deleteAdminByBatch(int[] array);

    int addAdminOne(Manager manager);
}
