<%--
  Created by IntelliJ IDEA.
  User: tanto
  Date: 2021/8/3
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="hide-div">

    <div class="update-form-div">

        <form action="javascript:void(0)" id="admin_update_form" class="update-form">
            <div class="form-div-a">
            <input type="hidden" id="admin-id" name="managerId" >
            <label for="fname">名称</label>
            <input type="text" id="fname" name="managerNickname" />
            <label for="name">用户名</label>
            <input type="text" id="name" name="managerName" />
            <label for="passwd">密码</label>
            <input type="text" id="passwd" name="managerPassword" />
            <input type="submit" class="admin-update-btn" value="修改">
            <input type="button" class="admin-update-close-btn" value="取消">
            </div>
        </form>
    </div>
</div>

<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i class="user"></i><em>管理员列表</em></span>
        <span class="modular fr"><a href="javascript:void(0)" class="pt-link-btn addadmin">+添加管理员</a></span>
    </div>
    <table class="list-style Interlaced admin_table">
        <tr>
            <th></th>
            <th>管理员编号</th>
            <th>管理员账号</th>
            <th>名称或权限</th>
            <th>最后登陆时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${admin_list}" var="i" varStatus="status">
            <tr>
                <td><input type="checkbox" name="ids" value="${i.managerId}"/></td>
                <td>${page.pageCount + status.count}</td>
                <td>${i.managerName}</td>
                <td>${i.managerNickname}</td>
                <td class="center">${i.managerLastTime}</td>
                <td class="center">
                    <a href="javascript:void(0)" class="admin_update"><img src="${pageContext.request.contextPath}/images/admin/icon_edit.gif"/></a>
                    <a href="javascript:void(0)" class="admin_delete" aurl="adminDel?ids=${i.managerId}" title="删除"><img src="${pageContext.request.contextPath}/images/admin/icon_drop.gif"/></a>
                </td>
            </tr>
        </c:forEach>
    </table
    <div>
        <p>共${page.totalCount}条记录</p>
    </div>
    <div style="overflow:hidden;">
        <!-- Operation -->
        <div class="BatchOperation fl">
            <input type="checkbox" class="middle children-checkbox" name="AllId">
            <input type="button" class="btnStyle middle checkAll" value="全选"/>
            <input type="button" value="批量删除" class="btnStyle DelBtn" />
        </div>
        <!-- turn page -->
        <div id="AdminPage" class="turnPage center fr">
            <a href="javascript:void(0)" aurl="AdminPage?pageNow=1">首页</a>
            <c:if test="${page.hasPrePage}">
                <a href="javascript:void(0)" aurl="AdminPage?pageNow=${page.pageNow-1}">上一页</a>
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

                        <a href="javascript:void(0)" aurl="AdminPage?pageNow=${p}"><c:out value="${p}"/></a>
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

                            <a href="javascript:void(0)" aurl="AdminPage?pageNow=${p}"><c:out value="${p}"/></a>
                        </c:if>
                    </c:forEach>
                </c:if>
                <c:if test="${page.pageNow > 5}">
                    <c:forEach begin="${page.pageNow - 4}" end="${page.pageNow}" var="p">
                        <c:if test="${p == page.pageNow}">

                            <a href="javascript:void(0)" style="background-color: red"><c:out value="${p}"/></a>
                        </c:if>
                        <c:if test="${p != page.pageNow}">

                            <a href="javascript:void(0)" aurl="AdminPage?pageNow=${p}"><c:out value="${p}"/></a>
                        </c:if>
                    </c:forEach>
                </c:if>

            </c:if>
            <c:if test="${page.hasNextPage}">
                <a href="javascript:void(0)" aurl="AdminPage?pageNow=${page.pageNow+1}">下一页</a>
            </c:if>
            <c:if test="${!page.hasNextPage}">
                <a href="javascript:void(0)">下一页</a>
            </c:if>

            <a href="javascript:void(0)" aurl="AdminPage?pageNow=${page.totalPageCount}">尾页</a>
            <p>共${page.totalPageCount}页</p>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        //a标签直接请求改异步请求分页
        $("#AdminPage a").each(function (){
            $(this).click(function (){
                var url = $(this).attr("aurl");
                $("#mainCont_div").load(url);
            });
        })
        //删除
        $(".admin_delete").each(function (){
            $(this).click(function (){
                var url = $(this).attr("aurl");
                $.get(url,function(data,status){
                    if(status=="success")
                        alert(data.info);
                    if(status=="error")
                        alert("Error!");
                });
            });
        })
        //点击修改后的动作
        $(".admin_update").each(function (){
            $(this).click(function (){
                var value = $(this).closest("tr").find("input[name='ids']").val();

                $("#admin-id").val(value);
                $(".hide-div").css("display","block");
            })
        })

        //关闭修改弹窗
        $(".admin-update-close-btn").click(function (){

            $(".hide-div").css("display","none");
        })

        //提交修改
        $(".admin-update-btn").click(function (){

            $.ajax({
                type: "POST",
                dataType: "json",
                url: "Update_Admin" ,
                data: $("#admin_update_form").serialize(),
                success: function (result) {
                    var info = result.info;
                    if(info != null){
                        alert(info);
                        $(".hide-div").css("display","none");

                        var url = "adminAction/admin_set";
                        $("#mainCont_div").load(url);
                    }

                },
                error : function() {
                    alert("error！");
                }
            });

        })

        //批量删除
        $(".DelBtn").click(function (){

            var s = new Array;
            var i = 0;
            $("input[name='ids']").each(function (){

                if($(this).prop("checked") == true){

                    s[i]=$(this).val();
                    i++;
                }
            })

            var url = "adminDel?"
            for(var i=0;i<s.length;i++){
                url = url + "ids="+ s[i]+"&"
            }

            console.log(url);
            $.get(url.slice(0,url.length-1),function(data,status){
                if(status=="success")
                    alert(data.info);
                if(status=="error")
                    alert("Error!");
            });
        })

        //全选动作
        $(".checkAll").click(function (){
            var check = $("input[name='AllId']").prop("checked");
            $("input[name='ids']").each(function (){
                $(this).prop("checked",check);
            })
        })

        //添加管理员按钮
        $(".addadmin").click(function (){
            var url = "addAdminPage";
            $("#mainCont_div").load(url);
        })
    });
</script>