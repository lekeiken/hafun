
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>欢迎来到-嗨猩逑，发掘优质的单机独立game</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link href="css/indexStyle.css" rel="stylesheet" />
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
        .main-wrap-box{
            width: 100%;
            height: 100%;
            overflow: auto;
            box-sizing:border-box;

            position: absolute;
            left: 0px;
            top: 0px;
        }
        .main-wrap-box-bg{
            background-image:url('images/8oky1j.png');
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            opacity: 0.9;
            filter: blur(20px) saturate(120%) ;

            width: 100%;
            height: 100%;


        }
        a{ text-decoration:none}
        .top-bar{
            width: 100%;
            height: 10%;
            float: left;
            box-sizing:border-box;
        }


        .logo img{

            width: 100px;
            height: 60px;

        }

        .main-box{
            width: 100%;
            height: 80%;

            box-sizing:border-box;
            float: left;
        }
        .game-info{
            width: 55%;
            height: 100%;

            float: left;
            box-sizing:border-box;
            color: #F0FFFF;
            padding:3% 0% 0% 5%;
        }

        .game-img{

            width: 130px;
            height: auto;
        }
        .game-img-box{
            margin-left: 2%;
        }

        .game-proper{
            width: 280px;
            height: auto;

            font-family: "KaiTi";
            font-size: 16px;
        }
        .game-proper ul{
            list-style-type: none;
            padding: 0px;
            margin: 0px;
        }
        .game-proper ul li{
            margin: 15px 0px 15px 0px;
        }
        .game-jj{
            width: 80%;
            height: 80px;
            font-size: 14px;
            font-family: "Microsoft YaHei";

            text-align:justify;
            text-indent:25px;
            line-height:160%;
            letter-spacing:1px;

        }

        .config-info{
            width: 45%;
            height: 100%;

            float: left;
            color: #F0F8FF;
            box-sizing:border-box;
            padding:18% 0% 0% 1%;
        }

        .c-con{
            height: 80%;
            width: 100%;
        }
        .c-con ul{
            list-style-type: none;
            padding: 0px;
            margin: 0px;
        }
        .c-con ul li{
            height: 30px;
            margin: 5px 0px 5px 0px;
        }
        .c-con ul li span{
            position:relative;
            bottom: 10px;
            left: 20px;
        }
        .eva{
            width: 100%;
            height: 5%;
            margin: auto;
        }



        .btm-right{
            width: 100%;
            float: left;
            height:20%;
            box-sizing:border-box;
            padding-left: 70%;

        }
        .btm-right input{
            width: 80px;
            height: 30px;
            border-radius: 10px;
            background: #D93B27;

        }

        .bottom-box{
            height: 10%;
            width: 100%;
            float: left;
            box-sizing:border-box;
        }

        @media (max-width:960px){
            .game-info{width:58%;}
            .config-info{width:38%;}
        }

        @media (max-width:768px){
            .game-info{width:100%;}
            .config-info{width:100%;}
        }


    </style>
</head>
<body>
<div class="main-wrap-box-bg">
</div>
<div class="main-wrap-box">
    <div class="top-bar">
        <div class="logo"><img src="images/3223.png"></div>
    </div>

    <div class="main-box">
            <div class="game-info">
                <input type="hidden" id="pid" value="${HomePage.pageId}">
                <img class="game-img" src="${HomePage.pageImg}">
                <div class="game-proper">
                    <ul>
                        <li><span>游戏名：</span><span>${HomePage.pageGameName}</span></li>
                        <li><span>游戏类型：</span><span>${HomePage.pageGameType}</span></li>
                        <li><span>游戏平台：</span><span>${HomePage.pagePlatform}</span></li>
                        <li><span>发行商/作者：</span><span>${HomePage.pageProducer}</span></li>
                        <li><span>发售时间：</span><span>${HomePage.pageLaunchDate}</span></li>
                    </ul>
                </div>
                <div class="game-jj">${HomePage.pageGameIntro}</div>
            </div>
            <div class="config-info">
                <div class="c-con">
                    <ul>
                        <li><embed src="images/system.svg" type="image/svg+xml" /><span>系统:</span><span>${HomePage.pageSystem}</span></li>
                        <li><embed src="images/cpu.svg" type="image/svg+xml" /><span>处理器：</span><span>${HomePage.pageCpu}</span></li>
                        <li><embed src="images/ncc.svg" type="image/svg+xml" /><span>内存：</span><span>${HomePage.pageMemory}</span></li>
                        <li><embed src="images/g.svg" type="image/svg+xml" /><span>显卡：</span><span>${HomePage.pageGraphics}</span></li>
                        <li><embed src="images/dis.svg" type="image/svg+xml" /><span>存储空间：</span><span>${HomePage.pageDisk}</span></li>
                    </ul>
                </div>
                <div class="btm-right"><input type="button" value="看往期"/></div>
            </div>
    </div>
    <div class="bottom-box">

    </div>
</div>

</body>
</html>
<script type="text/javascript">
    $(document).ready(function(){


        $(".btm-right input").click(function (){
            window.open("History");

        })

    })
</script>
