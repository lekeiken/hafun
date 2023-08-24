<%--
  Created by IntelliJ IDEA.
  User: DuckKing
  Date: 2022/7/12
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <title>修改密码</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
    <style type="text/css">
        html,body{
            margin: 0 ;
            padding: 0;
            width: 100%;
            height: 100%;

        }
        .reg-main-warp{
            width: 100%;
            height: 100%;
            background:#fd8080;
        }
        .reg-warp{
            width: 50%;
            height: 60%;
            position: absolute;
            left:50%;
            top:50%;
            transform: translate(-50%, -75%);
            padding: 20px;
            border-radius: 10px;
            border:1px solid #99FFCC;
            display: block;
            background:#bbeeff;
        }
        .form-group{
            margin-top: 28px;
            margin-bottom: 28px;
        }
        .col-sm-2{
            width: 18%;
        }
        .col-sm-10{
            width: 58%;
        }


    </style>
</head>
<body>
<div class="reg-main-warp">
    <div class="reg-warp">
        <form class="form-horizontal" action="javascript:void(0)" role="form">
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">新密码</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="password" id="password" placeholder="8-16位必须包含字母数字特殊符号">
                </div>
            </div>
            <div class="form-group">
                <label for="captcha" class="col-sm-2 control-label">验证码</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="captcha" id="captcha" placeholder="请输入发到您邮箱的邮件中的验证码">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" id="change_PW_btn" class="btn btn-default">提交</button>
                </div>
            </div>
        </form>
    </div>

</div>
</body>
</html>
<script>
    $(document).ready(function() {
        $("#change_PW_btn").click(function (){
            var password = $("#password").val();
            var captcha = $("#captcha").val();
            $.ajax({
                type : "POST",

                url : "setPassword",

                dataType : "json",

                processData: false,
                async: false,

                contentType : "application/json",

                data : JSON.stringify({ "password":password, "captcha":captcha }),
                success : function(res) {

                    alert(res.info);

                }
            });
        })
    })
</script>
