package org.hafunstar.domain;

import org.springframework.security.core.userdetails.User;

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

public class SonComment implements Serializable {
     private Integer sonCommentId;
     private Integer fatherCommentId;
     private String commentContent;
     private Date commentTime;
     private Integer sonCommentShow;
     private Account comUser;
     private Account replyUser;

    public Integer getSonCommentId() {
        return sonCommentId;
    }

    public void setSonCommentId(Integer sonCommentId) {
        this.sonCommentId = sonCommentId;
    }

    public Integer getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(Integer fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
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

    public Account getComUser() {
        return comUser;
    }

    public void setComUser(Account comUser) {
        this.comUser = comUser;
    }

    public Account getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(Account replyUser) {
        this.replyUser = replyUser;
    }

    public Integer getSonCommentShow() {
        return sonCommentShow;
    }

    public void setSonCommentShow(Integer sonCommentShow) {
        this.sonCommentShow = sonCommentShow;
    }

    @Override
    public String toString() {
        return "SonComment{" +
                "sonCommentId=" + sonCommentId +
                ", fatherCommentId=" + fatherCommentId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", sonCommentShow=" + sonCommentShow +
                ", comUser=" + comUser +
                ", replyUser=" + replyUser +
                '}';
    }
}
