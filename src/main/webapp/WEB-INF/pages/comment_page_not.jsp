
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="com-warp">
    <h3 style="margin-left: 50px">评论</h3>
    <hr/>
    <div class="comment-box-">

        <c:forEach items="${CommentList}" var="c" varStatus="status">
        <div class="main-cmd floa">
            <div class="main-left floa" >
                <img src="${c.account.accountImgUrl}" class="shadow" width="60" height="60">
            </div>
            <div class="main-right floa">

                <input type="hidden" name="uid" value="${c.account.accountId}">
                <span style="width: 100%" class="c-username">
                    <input type="hidden" name="cid" value="${c.commentId}">
                    ${c.account.accountName}:
                </span>
                <P>${c.commentContent}</P>

                <span style="width: 100%">${c.commentTime}|<a href="LoginPage">回复</a></span>
            </div>

            <c:if test="${c.sonComList.size() > 0}">
            <div class="second-cmd floa">

                <c:forEach items="${c.sonComList}" var="s" varStatus="status">
                <div class="a-second">

                    <div class="main-right floa">
                        <input type="hidden" name="uid" value="${s.comUser.accountId}">

                        <span style="width: 100%" class="c-username">
                            ${s.comUser.accountName}回复
                            ${s.replyUser.accountName}:
                        </span>
                        <P>${s.commentContent}</P>

                        <span style="width: 100%">${s.commentTime}|<a href="LoginPage">回复</a></span>
                    </div>
                </div>
                </c:forEach>
            </div>
            </c:if>
        </div>
        </c:forEach>
    </div>
    <div class="not-log"><span><a href="LoginPage">登陆</a>后发表评论和查看所有评论</span></div>
</div>
<div class="bottom-warp">

</div>
<script type="text/javascript">
    $(document).ready(function(){


    });
</script>
