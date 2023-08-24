<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="js/jquery-3.2.1.min.js"></script>

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
        .login-main-warp{
            width: 100%;
            height: 100%;
            background: #009999;
        }
        .login-warp{
            width: 430px;
            height: 300px;
            position: absolute;
            left:50%;
            top:50%;
            transform: translate(-50%, -50%);
            padding: 20px;
            border-radius: 10px;
            background: #CC9900;
        }
        .form-group{
            margin-top: 28px;
            margin-bottom: 28px;
        }
        .col-sm-2{
            width: 18%;
        }
        .col-sm-10{
            width: 72%;
        }
        .vercode-box{
            width: 72%;
            height: 42px;
            border-radius: 5px;
            border: 1.5px solid #333222;
            background-image: linear-gradient( #e6e6fa,#20b2aa,#2f4f4f);
        }
        .vercode-box:hover{
            background-image: linear-gradient( #2f4f4f,#20b2aa,#e6e6fa);
        }
        .f_right{
            float: right;
        }

        .prompt-info{
            width: 150px;
            height: 23px;
            margin: 0 auto;
            position: relative;
            top:80%;
            background: #990000;
            color: #fff;
            text-align: center;
            border-radius: 5px;
            font-size: 16px;
            display: none;
        }
        .captcha{
            display: none;
            width: 320px;
            height: 250px;
            padding-left: 10px;
            position: absolute;
            top:40%;
            left: 40%;
            background-color: #fff;
        }
        .cap-a{
            display: block;
            margin-top: 10px;
            margin-bottom: 10px;
            float: right;
        }
        .captcha span{
            display: block;
            font-size: 10px;
            margin-top: 10px;
            font-family: "Microsoft YaHei";
        }
        .cap-img{
            width: 300px;
            height: 180px;
            margin: 0  auto;
        }
        .smt-img {
            display: none;
            width: 20px;
            height: 20px;
            position: fixed;
        }
    </style>
</head>
<body>
<div class="login-main-warp">
    <div class="login-warp">
        <form id="login_form" action="javascript:void(0)" class="form-horizontal" role="form">

            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="username" placeholder="手机或邮箱号">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password" placeholder="请输入密码">
                </div>
            </div>
            <div class="form-group">
                <label for="captcha" class="col-sm-2 control-label">验证码</label>
                <div class="col-sm-10">
                    <input type="button" class="vercode-box" id="captcha" value="点击"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" id="login_sub" class="btn btn-default f_right">登录</button>
                    <a href="RegisterPage">注册</a>
                    <a id="setPW" href="javascript:void(0)">忘记密码？</a>
                </div>
            </div>
        </form>
    </div>
    <div class="captcha">

    </div>
    <div class="prompt-info">
        <span id="login_r_info"></span>
    </div>
    <p class="login-success" style="display: none"><span></span>秒后自动跳转到<a id="success-a" href="<%=request.getContextPath()%>/">首页</a></p>
</div>


</body>
</html>
<script>
    var img_count=1;
    var points=[];
    var num = 3;
    var bot ;
    var isClick = false;
    $("#success-a").click(function (){
        isClick = true;//点过就是真，没点就是否
    })
    function stopFunction(){
        clearInterval(bot);//停止倒计时
        if(isClick){

        }else {
            document.getElementById("success-a").click();
        }


    }
    $(document).ready(function() {
        $(".vercode-box").click(function (){

            $(".captcha").load("userCapt");
            $(".captcha").css("display","block");
        })

        $("#setPW").click(function (){
            var username = $("#username").val();
            if (username == null || username == "") {
                alert("需要输入名字。");
                return false;
            }
            window.location.href="setPassword?uname="+username;

        })

    });

    $(document).on('click', "#login_sub", function () {
        var username = $("#username").val();
        var password = $("#password").val();

        $.ajax({
            type: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            url: "login" ,
            processData: false,
            async: false,
            data: JSON.stringify({username:username,password:password,points:points}),
            success: function (result) {
                $(".prompt-info").css("display","block");
                $("#login_r_info").text(result.info);
                if(result.info == "登录成功"){

                    $(".login-success").css("display","block");
                    $(".login-success span").text(num);

                    bot = setInterval(function(){ //倒计时3秒后自动跳转
                        if(num >= 0){
                            $(".login-success span").text(num);
                            num--;
                        }else {
                            stopFunction();
                        }

                    },1000);

                }
                if(result.info == null){
                    $("#login_r_info").text("系统未知错误");
                }
            }

        });
        img_count=1;
        points=[];
    });

    $(document).on('click', "#clo-cap", function () {
        $(".captcha").css("display","none");
        img_count=1;
        points=[];
        $(".smt-img").css("display","none");
    });
    $(document).on('click', "#reload-img", function () {
        $(".captcha").load("userCapt");
        img_count=1;
        points=[];
        $(".smt-img").css("display","none");
    });
    $(document).on('click', "#click-img", function (e) {
        if(img_count < 4){
            var offset = $(".cap-img").offset();
            var ol = parseInt(offset.left);
            var os = parseInt(offset.top);
            var xx = e.originalEvent.x || e.originalEvent.layerX || 0;
            var yy = e.originalEvent.y || e.originalEvent.layerY || 0;
            $(".captcha img").each(function(){
                var xh = $(this).attr("smt");
                if(xh == img_count){
                    $(this).css("left",xx-10);
                    $(this).css("top",yy-10);
                    $(this).css("display","block");
                }
            })
            const x=xx-ol;
            const y= yy-os;
            const point={};
            point.x=x;
            point.y=y;
            points.push(point);
            if (img_count == 3){
                window.setTimeout(function (){
                    $(".captcha").css("display","none");
                    $(".smt-img").css("display","none");
                    $("#captcha").val("完成");

                },500);

                img_count=1;
            }else {
                img_count++;
            }

        }else {
            $(".captcha").load("userCapt");
            img_count=1;
        }
    });
</script>