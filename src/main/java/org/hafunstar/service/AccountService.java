package org.hafunstar.service;

import org.hafunstar.domain.Account;

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
public interface AccountService {

    List<Account> accountFindAll(int beginIndex, int pageSize);

    Account accountFindByEmailAndPass(String username);

    Account accountFindNameRepeat(String username);

    List<Account> accountFindAsId(int id);

    List<Account> accountFindAsId(String id);

    List<Account> accountFindAsName(String name);

    String accountFindNameById(int id);

    String accountFindPasswdById(int id);

    String accountFindEmailById(int id);

    Account accountFindIdByName(String name);

    int accountFindActivateById(int id);

    int accountSave(Account account);

    int accountChange(Account account);

    int accountChangePassWd(Account account);

    int accountDelete(int[] id);

    int getAccountCount();

    int accountUpdateActivate(int id);

    void accountUpdateImgUrl(Account account);
}
