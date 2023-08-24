package org.hafunstar.service.imp;

import org.hafunstar.dao.AccountDao;
import org.hafunstar.domain.Account;
import org.hafunstar.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
public class AccountServiceImp implements AccountService {

    private AccountDao accountDao;
    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> accountFindAll(int beginIndex, int pageSize) {
        List<Account> a = accountDao.findAccountAll(beginIndex,pageSize);
        return a;
    }

    @Override
    public Account accountFindByEmailAndPass(String username) {

        return accountDao.findAccountByEmailAndPasswd(username);

    }

    public List<Account> accountFindAsId(int id){
        List<Account> account = accountDao.findAccountById(id);
        return account;
    }

    public List<Account> accountFindAsName(String name){
        List<Account> accounts = accountDao.findAccountByName(name);
        return accounts;
    }

    @Override
    public String accountFindNameById(int id) {
        return accountDao.findAccountNameById(id);
    }

    @Override
    public String accountFindPasswdById(int id) {
        return accountDao.findAccountPasswdById(id);
    }

    @Override
    public String accountFindEmailById(int id) {
        return accountDao.findAccountEmailById(id);
    }

    @Override
    public Account accountFindIdByName(String name) {
        return accountDao.findIdByName(name);
    }

    @Override
    public int accountFindActivateById(int id) {
        return accountDao.findAccountActivateById(id);
    }

    public List<Account> accountFindAsId(String email){
        List<Account> account = accountDao.findAccountByEmail(email);
        return account;
    }

    @Override
    public int accountSave(Account account) {
        try {
            //为新添加的用户添加默认头像
            account.setAccountImgUrl("images/12949415.png");
            return accountDao.saveAccount(account);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int accountChange(Account account) {
        try {
            return accountDao.changeAccount(account);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int accountChangePassWd(Account account) {
        try {
            return accountDao.changeAccountPassword(account);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int accountDelete(int[] id) {
        try {
            return accountDao.deleteAccount(id);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int getAccountCount() {

        return accountDao.findUserCount();
    }

    @Override
    public int accountUpdateActivate(int id) {
        return accountDao.updateAccountActivate(id);
    }

    @Override
    public void accountUpdateImgUrl(Account account) {
        accountDao.updateAccountImgUrl(account);
    }

    @Override
    public Account accountFindNameRepeat(String username) {

        return accountDao.findNameRepeat(username);
    }
}
