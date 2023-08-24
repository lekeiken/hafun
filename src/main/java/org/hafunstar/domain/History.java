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
 * @Description:
 */
@Component
public class History implements Serializable {

    private Integer hisId;
    private HomePage homePage;
    private String hisUrl;
    private Date updateTime;

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public Integer getHisId() {
        return hisId;
    }

    public void setHisId(Integer hisId) {
        this.hisId = hisId;
    }

    public String getHisUrl() {
        return hisUrl;
    }

    public void setHisUrl(String hisUrl) {
        this.hisUrl = hisUrl;
    }

    public String getUpdateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "History{" +
                "hisId=" + hisId +
                ", homePage=" + homePage +
                ", hisUrl='" + hisUrl + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
