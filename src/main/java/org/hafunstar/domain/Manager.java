package org.hafunstar.domain;

import org.apache.ibatis.type.Alias;

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
 * @Description:
 */
public class Manager implements Serializable {
    private Integer managerId;
    private String managerNickname;
    private String managerName;
    private String managerPassword;
    private Date managerLastTime;

    public String getManagerLastTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(managerLastTime);
    }

    public void setManagerLastTime(Date managerLastTime) {
        this.managerLastTime = managerLastTime;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerNickname() {
        return managerNickname;
    }

    public void setManagerNickname(String managerNickname) {
        this.managerNickname = managerNickname;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", managerNickname='" + managerNickname + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerPassword='" + managerPassword + '\'' +
                ", managerLastTime=" + managerLastTime +
                '}';
    }
}
