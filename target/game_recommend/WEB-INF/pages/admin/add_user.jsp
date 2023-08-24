<%--
  Created by IntelliJ IDEA.
  User: tanto
  Date: 2021/8/3
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i class="add_user"></i><em>添加新用户</em></span>
    </div>
    <form action="javascript:void(0)" id="user_add_form">
        <table class="list-style">
            <tr>
                <td style="width:15%;text-align:right;">用户名：</td>
                <td><input type="text" name="accountName" class="textBox length-middle"/></td>
            </tr>
            <tr>
                <td style="text-align:right;">用户邮箱：</td>
                <td><input type="text" name="accountEmail" class="textBox length-middle"/></td>
            </tr>
            <tr>
                <td style="text-align:right;">设置密码：</td>
                <td><input type="password" name="accountPasswd" class="textBox length-middle"/></td>
            </tr>
            <tr>
                <td style="text-align:right;">是否为激活用户：</td>
                <td><input type="radio" name="isActivate" value="1">激活<br>
                    <input type="radio" name="isActivate" value="0">未激活</td>
            </tr>
            <td style="text-align:right;"></td>
            <td><input type="submit" class="tdBtn AddUserBtn" value="添加新用户"/></td>
            </tr>
        </table>
    </form>
</div>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"/>
<script type="text/javascript">
    $(document).ready(function(){

        $(".AddUserBtn").click(function (){

            $.ajax({
                type: "POST",
                dataType: "json",
                url: "AddAccount" ,
                data: $('#user_add_form').serialize(),
                success: function (result) {
                    var info = result.info;
                    if(info != null){
                        alert(info);

                        $("#user_add_form input").val("");

                    }

                },
                error : function() {
                    alert("异常！");
                }
            });
        })

    })
</script>
