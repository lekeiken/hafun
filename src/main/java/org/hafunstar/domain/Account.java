package org.hafunstar.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:对应用户表
 */
@Component
public class Account implements Serializable {
    private Integer accountId;
    private String accountName;
    private String accountPasswd;
    private String accountEmail;
    private String accountImgUrl;
    private Integer isActivate;
    private Date accountRegTime;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPasswd() {
        return accountPasswd;
    }

    public void setAccountPasswd(String accountPasswd) {
        this.accountPasswd = accountPasswd;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountImgUrl() {
        return accountImgUrl;
    }

    public void setAccountImgUrl(String accountImgUrl) {
        this.accountImgUrl = accountImgUrl;
    }

    public Integer getIsActivate() {
        return isActivate;
    }

    public void setIsActivate(Integer isActivate) {
        this.isActivate = isActivate;
    }

    public String getAccountRegTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(accountRegTime);
    }

    public void setAccountRegTime(Date accountRegTime) {
        this.accountRegTime = accountRegTime;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", accountPasswd='" + accountPasswd + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", accountImgUrl='" + accountImgUrl + '\'' +
                ", isActivate=" + isActivate +
                '}';
    }
}
