
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="com-warp">
    <h3 style="margin-left: 50px">评论</h3>
    <hr/>
    <div class="comment-box-">
        <input type="hidden" class="pid" value="${p_id}">
        <c:forEach items="${CommentList}" var="c" varStatus="status">

            <div class="main-cmd floa">
                <div class="main-left floa" >
                    <img src="${c.account.accountImgUrl}" class="shadow" width="60" height="60">
                </div>
                <div class="main-right floa">

                    <span style="width: 100%" class="c-username">

                    ${c.account.accountName}:
                </span>
                    <P>${c.commentContent}</P>

                    <span style="width: 100%">${c.commentTime}|
                        <a class="c-a" replyusername="${c.account.accountName}"
                           cid="${c.commentId}" uid="${c.account.accountId}"
                           href="javascript:void(0)">回复</a>
                    </span>
                </div>

                <c:if test="${c.sonComList.size() > 0}">
                    <div class="second-cmd floa">

                        <c:forEach items="${c.sonComList}" var="s" varStatus="status">
                            <div class="a-second">

                                <div class="main-right floa">
                                    <input type="hidden" name="suid" value="${s.comUser.accountId}">

                                    <span style="width: 100%" class="c-username">
                            ${s.comUser.accountName}&nbsp;回复&nbsp;
                            ${s.replyUser.accountName}:
                        </span>
                                    <P>${s.commentContent}</P>

                                    <span style="width: 100%">${s.commentTime}|
                                        <a class="c-a" replyusername="${s.comUser.accountName}"
                                           uid="${s.comUser.accountId}" cid="${c.commentId}"
                                           href="javascript:void(0)">回复</a>
                                    </span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </c:forEach>
        <!--分页-->
        <div class="Pagination">
            <ul class="pagination">
                <c:if test="${page.hasPrePage}">
                    <li class="page-item"><a class="page-link" href="javascript:void(0)"
                           aurl="cmtPage?pageNow=${page.pageNow-1}&FPageCount=${page.totalCount}
                           &SPageCount=${tc}&pid=${p_id}">
                            &laquo;
                        </a>
                    </li>
                </c:if>
                <c:if test="${!page.hasPrePage}">
                    <li class="page-item disabled"><a class="page-link" href="javascript:void(0)">&laquo;</a></li>
                </c:if>
                <c:if test="${page.totalPageCount <= 5 && page.totalPageCount > 0}">

                    <c:forEach begin="1" end="${page.totalPageCount}" var="p">

                        <c:if test="${p == page.pageNow}">

                            <li class="page-item">
                                <a class="page-link" href="javascript:void(0)">
                                    <c:out value="${p}"/>
                                </a>
                            </li>

                        </c:if>
                        <c:if test="${p != page.pageNow}">

                            <li class="page-item">
                                <a class="page-link" href="javascript:void(0)"
                                   aurl="cmtPage?pageNow=${p}&FPageCount=${page.totalCount}
                           &SPageCount=${tc}&pid=${p_id}">
                                    <c:out value="${p}"/>
                                </a>
                            </li>
                        </c:if>

                    </c:forEach>
                </c:if>
                <c:if test="${page.totalPageCount > 5}">

                    <c:if test="${page.pageNow <= 5}">
                        <c:forEach begin="1" end="5" var="p">
                            <c:if test="${p == page.pageNow}">

                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">
                                        <c:out value="${p}"/>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${p != page.pageNow}">
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)"
                                       aurl="cmtPage?pageNow=${p}&FPageCount=${page.totalCount}
                           &SPageCount=${tc}&pid=${p_id}">
                                        <c:out value="${p}"/>
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${page.pageNow > 5}">
                        <c:forEach begin="${page.pageNow - 4}" end="${page.pageNow}" var="p">
                            <c:if test="${p == page.pageNow}">

                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">
                                        <c:out value="${p}"/>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${p != page.pageNow}">

                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)"
                                       aurl="cmtPage?pageNow=${p}&FPageCount=${page.totalCount}
                           &SPageCount=${tc}&pid=${p_id}">
                                        <c:out value="${p}"/>
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:if>

                </c:if>
                <c:if test="${page.hasNextPage}">
                    <li class="page-item">
                        <a class="page-link" href="javascript:void(0)"
                                             aurl="cmtPage?pageNow=${page.pageNow+1}&FPageCount=${page.totalCount}
                           &SPageCount=${tc}&pid=${p_id}">
                        &raquo;
                        </a>
                    </li>
                </c:if>
                <c:if test="${!page.hasNextPage}">
                <li class="page-item disabled"><a class="page-link" href="javascript:void(0)">&raquo;</a></li>
                </c:if>


            </ul>
        </div>

    </div>
    <div class="is-log">
        <div class="is-log-info"><span class="repley-info"></span><a href="javascript:void(0)" class="info-cancel">❎</a></div>
        <div class="is-log-txt">
            <textarea class="is-log-text"  maxlength="200"></textarea>
        </div>
        <div class="is-log-sub">
            <a class="is-log-sub-a" href="#"><img src="images/83242.jpg" width="40" height="40"></a>
            <input type="button" class="cmt-btn" value="发布">
        </div>
    </div>
</div>
<div class="bottom-warp">

</div>
<script type="text/javascript">
    $(document).ready(function(){
        $(".is-log-sub-a").hover(
            function(){
                $(".is-log-sub-a img").attr("src","images/23943.jpg");
            },
            function(){
                $(".is-log-sub-a img").attr("src","images/83242.jpg");
            }
        )
        $(".Pagination").each(function (){
            $(this).click(function (){
                var url = $(this).attr("aurl");
                $(".comments").load(url);
            });
        })
        var pid = $(".pid").val();;//页码id
        var replyusername = "";//被回复人名字
        var replyid = 0;//被回复人id
        var cmtid = 0;//评论id
        $(".c-a").each(function (){
            $(this).click(function (){
                replyusername = $(this).attr("replyusername");
                cmtid =  $(this).attr("cid");
                replyid = $(this).attr("uid");
                $(".repley-info").text("回复给："+replyusername);
                $(".is-log-info").css("display","inline-block");
                $(".is-log-text").attr("autofocus","autofocus")
            });
        })
        $(".info-cancel").click(function (){//取消对回复给谁或者换个人回复

            $(".is-log-info").css("display","none");

            replyusername = "";
            replyid = 0;
            cmtid = 0;
        })



        $(".cmt-btn").click(function (){
            var data;
            if(replyusername == ""){
                data = {
                    pid:pid,
                    context:$(".is-log-text").val()
                };
                $.post("CMT/main",data,
                    function(data,status){
                        replyusername = "";
                        replyid = 0;
                        cmtid = 0;
                    });
            }else {
                data = {
                    replyid:replyid,
                    cid:cmtid,
                    context:$(".is-log-text").val()
                };
                $.post("CMT/vice",data,
                    function(data,status){

                        alert(data.info)
                        if(data.info == "发布成功"){
                            $(".is-log-info").css("display","none");
                            $(".is-log-text").val("");
                            replyusername = "";
                            replyid = 0;
                            cmtid = 0;
                        }
                    });
            }

        })

    });
</script>
