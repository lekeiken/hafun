<%--
  Created by IntelliJ IDEA.
  User: tanto
  Date: 2021/6/5
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="robots" content="nofollow"/>

    <link href="${pageContext.request.contextPath}/css/adminStyle.css" rel="stylesheet" type="text/css" />
    <style>
        body{width:100%;height:100%;overflow:hidden;background:url(${pageContext.request.contextPath}/images/admin/pc_loginBg.jpg) no-repeat;background-size:cover;position:absolute;}
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/Particleground.js"></script>


    <script>
        $(document).ready(function() {
            $('body').particleground({
                dotColor:'green',
                lineColor:'#c9ec6e'
            });
            $('.intro').css({
                'margin-top': -($('.intro').height() /2)
            });
            $("#captchaImage").click(function captcha() {
                $(this).attr("src", "${pageContext.request.contextPath}/image?i=" + Math.random())
            });
        });
    </script>

</head>
<body>
<section class="loginform">
    <h1>后台管理系统</h1>
    <form method="post" action="adminLogin">
        <ul>
            <li>
                <label>账号：</label>
                <input type="text" name="managerName" class="textBox" placeholder="管理员账号"/>
            </li>
            <li>
                <label>密码：</label>
                <input type="password" name="managerPassword" class="textBox" placeholder="登陆密码"/>
            </li>
            <li>
                <label>验证码：</label>
                <input type="Captcha"  class="Captcha" name="Captcha" placeholder="请输入验证码！">
                <img id="captchaImage" src="${pageContext.request.contextPath}/image"
                     style="height: 40px;width: 120px;margin-top:25px;" >
            </li>
            <li>
                <input type="submit" class="bottom" value="立即登陆"/>
            </li>
        </ul>
    </form>
    <div class="error"><span id="warning" style="color: red">${loginInfo}</span></div>
</section>
</body>
</html>
