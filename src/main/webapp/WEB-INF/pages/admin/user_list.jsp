<%--
  Created by IntelliJ IDEA.
  User: tanto
  Date: 2021/8/3
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="hide-div">

    <div class="update-form-div">

        <form action="javascript:void(0)" id="user_update_form" class="update-form">
            <input type="hidden" id="user-id" name="AccountId" >
            <div class="form-div-a"><label for="fname">用户名</label>
            <input type="text" id="fname" name="accountName" />
            <label for="name">注册邮箱或电话</label>
            <input type="text" id="name" name="accountEmail" />
            </div>
            <input type="radio" name="isActivate" value="1">激活
            <input type="radio" name="isActivate" value="0">未激活
            <div class="form-div-a"><input type="submit" class="user-update-btn" value="修改">
            <input type="button" class="user-update-close-btn" value="取消"></div>
        </form>
    </div>
</div>
<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i class="user"></i><em>用户列表</em></span>
        <span class="modular fr"><a href="javascript:void(0)" class="pt-link-btn add_user_btn">+添加新用户</a></span>
    </div>
    <div class="operate">
        <form>
            <select id="UserSearSel">
                <option value="name">昵称</option>
                <option value="id">id</option>
                <option value="phone">邮箱或电话</option>
            </select>
            <input type="text" class="textBox length-long UserSearText" placeholder="输入昵称、姓名、手机号码..."/>
            <input type="button" value="查询" class="tdBtn userSearchBtn"/>
        </form>
    </div>
    <table class="list-style Interlaced user_table">
        <tr>
            <th><input type="checkbox" class="AllBox"/></th>
            <th>编号</th>
            <th>头像</th>
            <th>用户名</th>
            <th>邮件地址</th>
            <th>是否验证</th>
            <th>注册日期</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${account_list}" var="a" varStatus="status">
            <tr>
                <td><input type="checkbox" name="ids" value="${a.accountId}"/></td>
                <td class="center"><span class="middle">${page.pageCount + status.count}</span></td>
                <td class="center"><img src="${pageContext.request.contextPath}${a.accountImgUrl}"  width="70" height="70"></td>
                <td class="center">${a.accountName}</td>
                <td class="center">${a.accountEmail}</td>
                <td class="center"><c:if test="${a.isActivate == 0}"><img src="${pageContext.request.contextPath}/images/admin/no.gif"/></c:if>
                    <c:if test="${a.isActivate == 1}"><img src="${pageContext.request.contextPath}/images/admin/yes.gif"/></c:if></td>
                <td class="center">${a.accountRegTime}</td>
                <td class="center">
                    <a href="javascript:void(0)" class="inline-block user_edt" title="编辑"><img src="${pageContext.request.contextPath}/images/admin/icon_edit.gif"/></a>
                    <a href="javascript:void(0)" class="inline-block user_del" aurl="userDel?ids=${a.accountId} title="删除"><img src="${pageContext.request.contextPath}/images/admin/icon_drop.gif"/></a>
                </td>
            </tr>
        </c:forEach>

    </table>
    <!-- BatchOperation -->
    <div style="overflow:hidden;">
        <!-- Operation -->
        <div class="BatchOperation fl">
            <input type="button" value="批量删除" class="btnStyle DelBtn"/>
        </div>
        <div>
            <p>共${page.totalCount}条记录</p>
        </div>
        <!-- turn page -->
        <div id="UserPage" class="turnPage center fr">
            <a href="javascript:void(0)" aurl="UserPage?pageNow=1">首页</a>
            <c:if test="${page.hasPrePage}">
                <a href="javascript:void(0)" aurl="UserPage?pageNow=${page.pageNow-1}">上一页</a>
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

                        <a href="javascript:void(0)" aurl="UserPage?pageNow=${p}"><c:out value="${p}"/></a>
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

                            <a href="javascript:void(0)" aurl="UserPage?pageNow=${p}"><c:out value="${p}"/></a>
                        </c:if>
                    </c:forEach>
                </c:if>
                <c:if test="${page.pageNow > 5}">
                    <c:forEach begin="${page.pageNow - 4}" end="${page.pageNow}" var="p">
                        <c:if test="${p == page.pageNow}">

                            <a href="javascript:void(0)" style="background-color: red"><c:out value="${p}"/></a>
                        </c:if>
                        <c:if test="${p != page.pageNow}">

                            <a href="javascript:void(0)" aurl="UserPage?pageNow=${p}"><c:out value="${p}"/></a>
                        </c:if>
                    </c:forEach>
                </c:if>

            </c:if>
            <c:if test="${page.hasNextPage}">
                <a href="javascript:void(0)" aurl="UserPage?pageNow=${page.pageNow+1}">下一页</a>
            </c:if>
            <c:if test="${!page.hasNextPage}">
                <a href="javascript:void(0)">下一页</a>
            </c:if>
            <a href="javascript:void(0)" aurl="UserPage?pageNow=${page.totalPageCount}">尾页</a>
            <p>共${page.totalPageCount}页</p>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        var nowPage = 1;
        $("#UserPage a").each(function (){
            if($(this).css("background-color") == "rgb(255, 0, 0)"){

                nowPage = $(this).text();
            }
            $(this).click(function (){
                var url = $(this).attr("aurl");
                $("#mainCont_div").load(url);
                var a_text = $(this).text();

            });
        })
        $(".user_del").each(function (){
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
        $(".DelBtn").click(function (){

            var s = new Array;
            var i = 0;
            $("input[name='ids']").each(function (){

                if($(this).prop("checked") == true){

                    s[i]=$(this).val();
                    i++;
                }
            })

            var url = "userDel?"
            for(var i=0;i<s.length;i++){
                url = url + "ids="+ s[i]+"&"
            }

            console.log(url);
            $.get(url.slice(0,url.length-1),function(data,status){
                if(status=="success")
                    alert(data.info);
                    var url = "UserPage?pageNow=" + nowPage;
                    $("#mainCont_div").load(url);
                if(status=="error")
                    alert("Error!");
            });
        })

        $(".AllBox").click(function (){
            var check = $(this).prop("checked");
            $("input[name='ids']").each(function (){
                $(this).prop("checked",check);
            })
        })

        $(".userSearchBtn").click(function (){

            var text = $(".UserSearText").val();
            if (text != null){
                var option = $("#UserSearSel").val();
                var url = "UserSearch?o="+ option +"&t="+text
                $("#mainCont_div").load(url);
            }else {
                alert("输入不能为空！")
            }

        })
        $(".add_user_btn").click(function (){

            var url = "AddUserPage"
            $("#mainCont_div").load(url);
        })
        $(".user_edt").each(function (){
            $(this).click(function (){
                var value = $(this).closest("tr").find("input[name='ids']").val();

                $("#user-id").val(value);
                $(".hide-div").css("display","block");
            })
        })
        $(".user-update-btn").click(function (){

            $.ajax({
                type: "POST",
                dataType: "json",
                url: "Edit_User",
                data: $("#user_update_form").serialize(),
                success: function (result) {
                    var info = result.info;
                    if(info != null){
                        alert(info);
                        $(".hide-div").css("display","none");

                        var url = "adminAction/user";
                        $("#mainCont_div").load(url);
                    }

                },
                error : function() {
                    alert("error！");
                }
            });

        })

    })

</script>