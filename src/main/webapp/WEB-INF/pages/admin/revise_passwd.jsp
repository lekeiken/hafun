<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i class="user"></i><em>修改密码</em></span>
    </div>
    <form id="admin_change_password_form">
    <table class="noborder">
        <tr>
            <td style="text-align:right;">旧密码：</td>
            <td><input id="this_opw" type="password" name="oldPassword" passwordshow="1" class="textBox length-middle"/><a href="javascript:void(0)" onclick="oldpass();return false;">👁</a></td>
        </tr>
        <tr>
            <td style="text-align:right;">新密码：</td>
            <td><input id="this_pw" type="password" name="newPassword" passwordshow="1" class="textBox length-middle"/><a href="javascript:void(0)" onclick="newpass();return false;">👁</a></td>
        </tr>
        <tr>
            <td style="text-align:right;">再次确认密码：</td>
            <td><input id="this_repw" type="password" class="textBox length-middle" onkeyup="checkpassword()"/><span id="tishi" style="width: "></span></td>
        </tr>
        <tr>
            <td style="text-align:right;"></td>
            <td><input type="button" disabled="true" class="tdBtn" value="保存" onclick="change_pass()"/></td>
        </tr>
    </table>
    </form>
</div>


