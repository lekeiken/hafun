。
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <title>注册</title>
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
        .f_right{
            float: right;
        }
        .f_left{
            float: left;
        }
        .verify-box{
            width: 50%;
            height: 10%;
            margin: 0 auto;
            position: relative;
            top:20%;
            text-align: center;
            border-radius: 5px;
            font-size: 16px;
            display: none;
        }
        .verify-btn{
            width: 130px;
            height: 70px;
            font-size: 2.0em;
            margin-top:20%;
        }
    </style>
</head>
<body>
<div class="reg-main-warp">
    <div class="reg-warp">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="nickname" class="col-sm-2 control-label">昵称</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="nickname" placeholder="1-10位汉字字母数字">
                </div>
            </div>
            <div class="form-group">
                <label for="contact" class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="contact" placeholder="">
                </div>
            </div>
            <div class="form-group">
                <label for="passwd" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="passwd" placeholder="8-16位必须包含字母数字特殊符号">
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
                    <button type="button" id="register-btn" class="btn btn-default">注册</button>
                </div>
            </div>
        </form>
    </div>
    <div class="captcha">

    </div>

</div>
</body>
</html>
<script type="text/javascript">
    var img_count=1;
    var points=[];
    $(document).on('click', "#clo-cap", function () { //关闭验证码框
        $(".captcha").css("display","none");
        img_count=1;
        points=[];
        $(".smt-img").css("display","none");
    });
    $(document).on('click', "#reload-img", function () {  //重新获取验证码图片
        $(".captcha").load("userCapt");
        img_count=1;
        points=[];
        $(".smt-img").css("display","none");
    });
    $(document).on('click', "#click-img", function (e) { //获取点击标点
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
            const point={"x":x,"y":y};
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

    $(document).ready(function() {

        $("#register-btn").click(function (){

            var emailPat =/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/g;  //邮箱
            var phonePat =/^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/g;    //手机
            var nickName =/^[\u4e00-\u9fa5A-Za-z0-9_]{1,10}$/g;  //名字
            var passwordPat =/^[A-Za-z0-9~!@#$%^&*()_.,/;:]{8,16}$/g;
            var
            s = true;
            var passwd = $("#passwd").val();
            var name = $("#nickname").val();
            var contact = $("#contact").val();
            console.log(passwd , name , contact,points)
            var a = nickName.test(name);
            const b = emailPat.test(contact);
            const c = passwordPat.test(passwd);
            if(points.length == 0){
                alert("请完成验证码")
                s = false
            }
            if(!a){
                alert("昵称格式错误")
                s = false
            }
            if(!b){
                alert("邮箱格式错误")
                s = false
            }
            if(!c){
                alert("密码格式错误")
                s = false
            }

            if(s){
                const
                    data = {
                        username:name,
                        password:passwd,
                        contact:contact,
                        points:points
                    };
                $.ajax({
                    type : "POST",

                    url : "regUser",

                    dataType : "json",

                    processData: false,
                    async: false,

                    contentType : "application/json",

                    data : JSON.stringify(data),

                    success : function(res) {

                        if(res.info == "success"){
                            alert("注册成功，已向你的注册邮箱发送一封邮件，" +
                                "点击邮件内的链接以激活账户，新注册的用户一周内未激活自动注销");
                        }else {
                            alert(res.info);
                        }


                    }

                });
                img_count=1;
                points=[];
            }

        })
        $(".vercode-box").click(function (){

            $(".captcha").load("userCapt");
            $(".captcha").css("display","block");
        })

    });

</script>
