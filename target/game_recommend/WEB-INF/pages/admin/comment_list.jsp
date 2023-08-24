<%--
  Created by IntelliJ IDEA.
  User: tanto
  Date: 2021/8/3
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i class="order"></i><em>评论列表</em></span>
        <span class="modular fr"><a href="javascript:void(0)" class="pt-link-btn audit_comment">审核评论</a></span>
    </div>
    <div class="operate">
        <form>
            <img src="${pageContext.request.contextPath}/images/admin/icon_search.gif"/>
            <select  id="CommSearSel">
                <option value="account_name">用户名</option>
                <option value="game_name">游戏名</option>
                <option value="comment_id">评论编号</option>
            </select>
            <input type="text" class="textBox length-long ComSearText" placeholder="输入"/>
            <input type="button" value="查询" class="tdBtn CommSearchBtn"/>
        </form>
    </div>
    <table class="list-style Interlaced" >
        <tr>
            <th></th>
            <th>评论编号</th>
            <th>父评论id</th>
            <th>游戏名</th>
            <th>评论时间</th>
            <th>评论者</th>
            <th>回复给？</th>
            <th>评论内容</th>
            <th>审核状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${comment_list}" var="c" varStatus="status">
            <tr>
                <td>
                    <a href="javascript:void(0)" on="1" class="comment-btn" cid="${page.pageCount + status.count}">
                        <div class="close"></div>
                    </a>
                </td>
                <td class="center"><input type="checkbox" name="ids" value="${c.commentId}"/>
                    <span class="block">${page.pageCount + status.count}</span>
                </td>
                <td class="center">
                    <span class="block"></span>
                </td>
                <td class="center">
                    <span class="block">${c.homePage.pageGameName}</span>
                </td>
                <td class="center">
                    <span class="block">${c.commentTime}</span>
                </td>
                <td class="center">
                    <span>${c.account.accountName}</span>
                </td>
                <td class="center">
                    <span></span>
                </td>
                <td class="center" width="300">
                    <div style="width:400px;height:50px;overflow:scroll;overflow-x:hidden;">
                            ${c.commentContent}
                    </div>
                </td>
                <td class="center">
                    <span><c:if test="${c.commentShow == 0}">否</c:if>
                    <c:if test="${c.commentShow == 1}">是</c:if></span>
                </td>
                <td class="center">
                    <a href="javascript:void(0)" class="inline-block comment-del-btn" title="删除"><img src="${pageContext.request.contextPath}/images/admin/icon_trash.gif"/></a>
                </td>
            </tr>
            <tr>
                <td colspan="10">
                    <div class="son-comment" sid="${page.pageCount + status.count}">
                        <table  class="list-style">
                        <c:forEach items="${c.sonComList}" var="s" varStatus="status">
                            <tr>
                                <td class="center"><input type="checkbox" name="s_ids" value="${s.sonCommentId}"/>
                                    <span class="block"></span>
                                </td>
                                <td class="center">
                                    <span class="block">${s.fatherCommentId}</span>
                                </td>
                                <td class="center">
                                    <span class="block"></span>
                                </td>
                                <td class="center">
                                    <span class="block">${s.commentTime}</span>
                                </td>
                                <td class="center">
                                    <span>${s.comUser.accountName}</span>
                                </td>
                                <td class="center">
                                    <span>${s.replyUser.accountName}</span>
                                </td>
                                <td class="center" width="300">
                                    <div style="width:400px;height:50px;overflow:scroll;overflow-x:hidden;">
                                            ${s.commentContent}
                                    </div>
                                </td>
                                <td class="center">
                                <span><c:if test="${s.sonCommentShow == 0}">否</c:if>
                                <c:if test="${s.sonCommentShow == 1}">是</c:if></span>
                                </td>
                                <td class="center">
                                    <a href="javascript:void(0)" class="inline-block sub-comment-del-btn" title="删除"><img src="${pageContext.request.contextPath}/images/admin/icon_trash.gif"/></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </table>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <!-- BatchOperation -->
    <div style="overflow:hidden;">
        <!-- Operation -->
        <div class="BatchOperation fl">
            <input type="checkbox" class="AllBox" id="del"/>
            <input type="button" value="删除评论" class="btnStyle batch-DelBtn"/>
        </div>
        <!-- turn page -->
        <div id="CommentPage" class="turnPage center fr">
            <a href="javascript:void(0)" aurl="CommentPage?pageNow=1&Search=${search}&pageCount=${page.totalCount}&SearchKeyword=${searchName}">首页</a>
            <c:if test="${page.hasPrePage}">
                <a href="javascript:void(0)" aurl="CommentPage?pageNow=${page.pageNow-1}&Search=${search}&pageCount=${page.totalCount}&SearchKeyword=${searchName}">上一页</a>
            </c:if>
            <c:if test="${!page.hasPrePage}">
                <a href="javascript:void(0)">上一页</a>
            </c:if>

            <c:if test="${page.totalPageCount <= 5 && page.totalPageCount > 0}">

                <c:forEach begin="1" end="${page.totalPageCount}" var="p">

                    <c:if test="${p == page.pageNow}">

                        <a href="javascript:void(0)" style="background-color: red"><c:out value="${p}"/></a>
                    </c:if>
                    <c:if test="${p != page.pageNow}">

                        <a href="javascript:void(0)" aurl="CommentPage?pageNow=${p}&Search=${search}&pageCount=${page.totalCount}&SearchKeyword=${searchName}"><c:out value="${p}"/></a>
                    </c:if>

                </c:forEach>
            </c:if>
            <c:if test="${page.totalPageCount > 5}">

                <c:if test="${page.pageNow <= 5}">
                    <c:forEach begin="1" end="5" var="p">
                        <c:if test="${p == page.pageNow}">

                            <a href="javascript:void(0)" style="background-color: red"><c:out value="${p}"/></a>
                        </c:if>
                        <c:if test="${p != page.pageNow}">

                            <a href="javascript:void(0)" aurl="CommentPage?pageNow=${p}&Search=${search}&pageCount=${page.totalCount}&SearchKeyword=${searchName}"><c:out value="${p}"/></a>
                        </c:if>
                    </c:forEach>
                </c:if>
                <c:if test="${page.pageNow > 5}">
                    <c:forEach begin="${page.pageNow - 4}" end="${page.pageNow}" var="p">
                        <c:if test="${p == page.pageNow}">

                            <a href="javascript:void(0)" style="background-color: red"><c:out value="${p}"/></a>
                        </c:if>
                        <c:if test="${p != page.pageNow}">

                            <a href="javascript:void(0)" aurl="CommentPage?pageNow=${p}&Search=${search}&pageCount=${page.totalCount}&SearchKeyword=${searchName}"><c:out value="${p}"/></a>
                        </c:if>
                    </c:forEach>
                </c:if>

            </c:if>
            <c:if test="${page.hasNextPage}">
                <a href="javascript:void(0)" aurl="CommentPage?pageNow=${page.pageNow+1}&Search=${search}&pageCount=${page.totalCount}&SearchKeyword=${searchName}">下一页</a>
            </c:if>
            <c:if test="${!page.hasNextPage}">
                <a href="javascript:void(0)">下一页</a>
            </c:if>

            <a href="javascript:void(0)" aurl="CommentPage?pageNow=${page.totalPageCount}&Search=${search}&pageCount=${page.totalCount}&SearchKeyword=${searchName}">尾页</a>
            <p>共${page.totalPageCount}页</p>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $("#CommentPage a").each(function (){
            $(this).click(function (){
                var url = $(this).attr("aurl");
                $("#mainCont_div").load(url);
            });
        })

        //附带条件的搜索按钮
        $(".CommSearchBtn").click(function (){

            var text = $(".ComSearText").val();
            if (text != null){
                var option = $("#CommSearSel").val();
                var url = "CommentSearch?o="+ option +"&t="+text
                $("#mainCont_div").load(url);
            }else {
                alert("输入不能为空！")
            }

        })

        $(".AllBox").click(function (){
            var check = $(this).prop("checked");
            $("input[name='ids']").each(function (){
                $(this).prop("checked",check);
            })
        })

        $(".batch-DelBtn").click(function (){

            var com_list = new Array;
            var s_com_list = new Array;

            $("input[name='ids']:checked").each(function (i){

                com_list[i]=$(this).val();
            })
            $("input[name='s_ids']:checked").each(function (j){

                s_com_list[j]=$(this).val();

            })
            $.ajax({
                type: 'POST',
                url: "BatchCommentDel",
                cache: false,  //禁用缓存
                data:JSON.stringify({"ids":com_list,"s_ids":s_com_list}),
                contentType: "application/json",
                dataType: "json",
                async:true,
                success: function (result) {

                    alert(result.info)
                }
            })

        })

        $(".sub-comment-del-btn").each(function (){
            $(this).click(function (){
                var value = $(this).closest("tr").find("input[name='s_ids']").val();
                var url = "ASubCommentDel?sid="+value;
                $.get(url,function(data,status){
                    if(status=="success")
                        alert(data.info);
                    if(status=="error")
                        alert("Error!");
                });

            })
        })

        $(".comment-del-btn").each(function (){
            $(this).click(function (){
                var value = $(this).closest("tr").find("input[name='ids']").val();
                var url = "ACommentDel?cid="+value;
                $.get(url,function(data,status){
                    if(status=="success")
                        alert(data.info);
                    if(status=="error")
                        alert("Error!");
                });
            })
        })

        $(".comment-btn").each(function (){
            $(this).click(function (){
                var on = $(this).attr("on");
                var cid = $(this).attr("cid");
                if(on == 1){

                    $(this).attr("on","2");
                    $(this).find("div").removeClass("close");
                    $(this).find("div").addClass("open");
                    $(".son-comment").each(function (){
                        var sid = $(this).attr("sid");

                        if(sid == cid){

                            $(this).css("display","inline-block");
                        }
                    })
                }
                if(on == 2){

                    $(this).attr("on","1");
                    $(this).find("div").removeClass("open");
                    $(this).find("div").addClass("close");
                    $(".son-comment").each(function (){
                        var sid = $(this).attr("sid");
                        if(sid == cid){

                            $(this).css("display","none");
                        }
                    })
                }
            })
        })
    })
</script>