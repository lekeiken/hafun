<%--
  Created by IntelliJ IDEA.
  User: tanto
  Date: 2021/8/3
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i class="add_user"></i><em>添加管理员</em></span>
    </div>
    <form action="javascript:void(0)" id="admin_add_form">
        <table class="list-style">
            <tr>
                <td style="width:15%;text-align:right;">管理员昵称：</td>
                <td><input type="text" class="textBox length-middle" name="managerNickname"/></td>
            </tr>
            <tr>
                <td style="text-align:right;">管理员用户名：</td>
                <td><input type="text" class="textBox length-middle" name="managerName"/></td>
            </tr>
            <tr>
                <td style="text-align:right;">设置密码：</td>
                <td><input type="password" id="new_admin_pass" class="textBox length-middle" passwordshow="1" name="managerPassword"/><a href="javascript:void(0)" class="add_admin_pass_Btn">👁</a></td>
            </tr>
            <td style="text-align:right;"></td>
            <td><button type="button" class="tdBtn AddBtn">添加新管理员</button></td>
            </tr>
        </table>
    </form>
</div>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"/>
<script type="text/javascript">
    $(document).ready(function(){

        $(".AddBtn").click(function (){

            $.ajax({
                type: "POST",
                dataType: "json",
                url: "AddAdmin" ,
                data: $('#admin_add_form').serialize(),
                success: function (result) {
                    var info = result.info;
                    if(info != null){
                        alert(info);

                        $("#admin_add_form input").val("");

                    }

                },
                error : function() {
                    alert("异常！");
                }
            });
        })

        $(".add_admin_pass_Btn").click(function (){

            var show1 = $("#new_admin_pass").attr("passwordshow");
            var show2 = $("#new_admin_pass_copy").attr("passwordshow");
            if(show1 == 1){
                $("#new_admin_pass").after('<input id="new_admin_pass_copy" type="text" name="managerPassword"  passwordshow="2" class="textBox length-middle" style="display:none;"/>')
                $("#new_admin_pass_copy").val($("#new_admin_pass").val());
                $("#new_admin_pass").remove();
                $("#new_admin_pass_copy").show();
            }
            if(show2 == 2){
                $("#new_admin_pass_copy").after('<input id="new_admin_pass" type="password" name="managerPassword" passwordshow="1" class="textBox length-middle" style="display:none;"/>')
                $("#new_admin_pass").val($("#new_admin_pass_copy").val());
                $("#new_admin_pass_copy").remove();
                $("#new_admin_pass").show();
            }
        })


    })
</script>