
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

</head>
<body>
<div class="main-wrap-box-bg">
</div>
<div class="main-wrap-box">
    <div class="top-bar">
        <div class="logo"><img src="images/3223.png"></div>
        <div class="Umenu" >
            <a href="exit" class="u_menu_2">登出</a>
            <a href="profile/${u_id}" class="u_menu_1">个人资料</a>
            <a href="profile" class="login1"><img src="${img_url}" class="login-img">
            </a>
        </div>
        <input type="hidden" name="accountId" value="${u_id}">
    </div>

    <div class="main-box">
        <div class="leftcolumn">
            <div class="game-info-bg"></div>
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
            <div class="config-info-bg"></div>
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
            </div>
        </div>
        <div class="rightcolumn-bg"></div>
        <div class="rightcolumn">
            <div  class="eva">
                <span>评价</span>
                <span>
                    <input type="hidden" id="eva-star" value="${HomePage.pageIndex}">

    			</span>
                <span>评价</span>
            </div>
            <div class="content-txt">
                ${HomePage.pageEvaluation}
            </div>

        </div>

    </div>
    <div class="bottom-box">
        <div class="btm-left"><input type="button" value="看大伙怎么说"/></div>
        <div class="btm-right"><input type="button" value="看往期"/></div>
    </div>
</div>
<div class="comments">

</div>

</body>
</html>
<script type="text/javascript">
    $(document).ready(function(){
        var starNum_y = $("#eva-star").val();
        var starNum_n = 5-starNum_y;
        var i=0;
        var j=0;
        while (i<starNum_y)
        {
            $("#eva-star").before('<object data="images/yes_star.svg" type="image/svg+xml"></object>');
            i++;
        }
        while (j<starNum_n)
        {
            $("#eva-star").after('<object data="images/no_star.svg" type="image/svg+xml"></object>');
            j++;
        }
        $(".game-info").hover(
            function(){
                $(".game-info-bg").css("display","inline-block");
            },
            function(){
                $(".game-info-bg").css("display","none");
            }
        )
        $(".config-info").hover(
            function(){
                $(".config-info-bg").css("display","inline-block");
            },
            function(){
                $(".config-info-bg").css("display","none");
            }
        )
        $(".rightcolumn").hover(
            function(){
                $(".rightcolumn-bg").css("display","inline-block");
            },
            function(){
                $(".rightcolumn-bg").css("display","none");
            }
        )
        $(".btm-left input").click(function (){
            var url = "Comment/"+$("#pid").val();
            $(".comments").load(url);
        })
        $(".btm-right input").click(function (){
            window.open("History");

        })
        $(".Umenu").mouseenter(function(){
            $( ".u_menu_1" ).toggle("slow");
            sleep(200);
            $( ".u_menu_2" ).toggle("slow");
        });
        $(".Umenu").mouseleave(function(){
            $( ".u_menu_2" ).toggle("slow");
            sleep(200);
            $( ".u_menu_1" ).toggle("slow");

        });

    })
    function sleep(d){
        for(var t = Date.now();Date.now() - t <= d;);
    }
</script>
