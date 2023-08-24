package org.hafunstar.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@Component
public class Comment implements Serializable {

    private Integer commentId;
    private String commentContent;
    private Date commentTime;
    private Integer commentShow;
    private Account account;
    private HomePage homePage;

    private List<SonComment>  sonComList = new ArrayList<>();

    public List<SonComment> getSonComList() {
        return sonComList;
    }

    public void setSonComList(List<SonComment> sonComList) {
        this.sonComList = sonComList;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(commentTime);
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public Integer getCommentShow() {
        return commentShow;
    }

    public void setCommentShow(Integer commentShow) {
        this.commentShow = commentShow;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", account=" + account +
                ", homePage=" + homePage +
                ", sonComList=" + sonComList.toString() +
                '}';
    }
}
