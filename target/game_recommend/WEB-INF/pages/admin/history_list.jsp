
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrap-h" style="background-color: #E0E0E0;overflow:auto">
    <div class="page-title">
        <span class="modular fl"><i></i><em>历史列表</em></span>
    </div>
    <div class="his-list">
        <c:forEach items="${his_list}" var="h" varStatus="status">
            <div class="his">
                <input type="hidden" name="hid" value="${h.homePage.pageId}">
                <img class="his-img" src="${pageContext.request.contextPath}${h.homePage.pageImg}">
                <span class="his-fort">游戏名：${h.homePage.pageGameName}</span>
                <span class="his-fort">更新时间：${h.updateTime}</span>
                <span class="his-fort"><a href="javascript:void(0)" class="his-edt-btn" href="javascript:void(0)">编辑</a>|
                    <a class="his-del-btn" href="javascript:void(0)">删除</a></span>
            </div>
        </c:forEach>
    </div>
</div>
<div class="hide-div-h">
    <div class="half-column">
        <span class="block top-margin">游戏名：<span class="before-homepage" id="pageGameName"></span></span>
        <span class="block top-margin">推荐指数：<span class="before-homepage" id="pageIndex"></span></span>
        <span class="block top-margin">游戏分类：<span class="before-homepage" id="pageGameType"></span></span>
        <span class="block top-margin">游戏平台：<span class="before-homepage" id="pagePlatform"></span></span>
        <span class="block top-margin">宣传图片：<span class="before-homepage" id="pageImg"></span></span>
        <span class="block top-margin">制作者：<span class="before-homepage" id="pageProducer"></span></span>
        <span class="block top-margin">发售时间：<span class="before-homepage" id="pageLaunchDate"></span></span>
        <span class="block top-margin">系统：<span class="before-homepage" id="pageSystem"></span></span>
        <span class="block top-margin">CPU：<span class="before-homepage" id="pageCpu"></span></span>
        <span class="block top-margin">内存：<span class="before-homepage" id="pageMemory"></span></span>
        <span class="block top-margin">硬盘：<span class="before-homepage" id="pageDisk"></span></span>
        <span class="block top-margin">显卡：<span class="before-homepage" id="pageGraphics"></span></span>
        游戏简介：<textarea id="pageGameIntro" class="textarea"></textarea><input type="button" id="pageGameIntro-btn" value="复制粘贴" \>
        实玩测评：<textarea id="pageEvaluation" class="textarea"></textarea><input type="button" id="pageEvaluation-btn" value="复制粘贴" \>
    </div>
    <div class="half-column">
        <table class="list-style">

            <form id="homepage-change-form" class="update-form-h" action="javascript:void(0)">
                <input type="hidden" id="HomePage-id" name="pageId">
                <tr>
                    <td>游戏名：<input type="text" name="pageGameName" class="textBox length-long"/></td>
                </tr>
                <tr>
                    <td>推荐指数：<input type="text" name="pageIndex" class="textBox length-long"/></td>
                </tr>
                <tr>
                    <td>游戏分类：<input type="text" name="pageGameType" class="textBox length-long"/></td>
                </tr>
                <tr>
                    <td>游戏平台：<input type="text" name="pagePlatform" class="textBox length-long"/></td>
                </tr>
                <tr>
                    <td>宣传图片：<input type="text" name="pageImg" class="textBox length-long"></td>
                </tr>
                <tr>
                    <td>制作者：<input type="text" name="pageProducer" class="textBox length-short"/></td>
                </tr>
                <tr>
                    <td>发售时间：<input type="date" name="pageLaunchDate" class="textBox length-short"/></td>
                </tr>
                <tr>
                    <td>系统：<input type="text" name="pageSystem" class="textBox length-short"/></td>
                </tr>
                <tr>
                    <td>CPU：<input type="text" name="pageCpu" class="textBox length-short"/></td>
                </tr>
                <tr>
                    <td>内存：<input type="text" name="pageMemory" class="textBox length-short"/></td>
                </tr>
                <tr>
                    <td>硬盘：<input type="text" name="pageDisk" class="textBox length-short"/></td>
                </tr>
                <tr>
                    <td>显卡：<input type="text" name="pageGraphics" class="textBox length-short"/></td>
                </tr>
                <tr>
                    <td>游戏简介：<div id="game_content_change" name="pageGameIntro" ></div></td>
                </tr>
                <tr>
                    <td>实玩测评：<div id="player_content_change" name="pageEvaluation" ></div></td>
                </tr>
                <tr>
                    <td><input type="button" id="change-hp-btn" value="修改"></td>
                </tr>
            </form>
        </table>
        <input type="button" class="hp-close-btn" value="返回">
    </div>
</div>
<script>
    $(document).ready(function(){
        $('#game_content_change').summernote(
            {
                height:150,
                minHeight:null,
                maxHeight:null,
                focus:true
            }
        );
        $('#player_content_change').summernote(
            {
                height:150,
                minHeight:null,
                maxHeight:null,
                focus:true
            }
        );
        $(".his-del-btn").each(function (){
            $(this).click(function (){
                var value = $(this).closest("div").find("input[name='hid']").val();
                var url = "HomePageDel?hid="+value;
                $.get(url,function(data,status){
                    if(status=="success")
                        alert(data.info);
                    if(status=="error")
                        alert("Error!");
                });
            })
        })

        $(".his-edt-btn").each(function (){
            $(this).click(function (){
                var value = $(this).closest("div").find("input[name='hid']").val();
                var url = "GetH?hid="+value;
                $.get(url,function(data,status){
                    if(status=="success")

                    $("#pageGameName").text(data.pageGameName);
                    $("#pageProducer").text(data.pageProducer);
                    $("#pageGameType").text(data.pageGameType);
                    $("#pagePlatform").text(data.pagePlatform);

                    $("#pageImg").text(data.pageImg);
                    const date = new Date(parseInt(data.pageLaunchDate));
                    const year = date.getFullYear();
                    const month = date.getMonth() + 1;
                    const day = date.getDate();
                    const time = year + '/' + month + '/' + day;
                    $("#pageLaunchDate").text(time);
                    $("#pageSystem").text(data.pageSystem);
                    $("#pageCpu").text(data.pageCpu);
                    $("#pageMemory").text(data.pageMemory);
                    $("#pageDisk").text(data.pageDisk);
                    $("#pageGraphics").text(data.pageGraphics);
                    const g_str = data.pageGameIntro.toString();
                    const p_str = data.pageEvaluation.toString();
                    $("#pageGameIntro").text(g_str);
                    $("#pageEvaluation").text(p_str);
                    $("#pageIndex").text(data.pageIndex);
                    if(status=="error")
                        alert("Error!");
                });
                $("#HomePage-id").val(value);
                $(".hide-div-h").css("display","block");
                $(".wrap-h").css("display","none");
            })
        })
        $("#pageGameIntro-btn").click(function (){

            const g_str = $("#pageGameIntro").val();
            $('#game_content_change').summernote('code',g_str);
        })

        $("#pageEvaluation-btn").click(function (){
            const p_str = $("#pageEvaluation").val();
            $('#player_content_change').summernote('code',p_str);
        })
        $("#change-hp-btn").click(function (){
            var data = {};
            $("#homepage-change-form input[type='hidden']").each(function (){

                    data[$(this).attr("name")] = $(this).val();

            })
            $("#homepage-change-form input[type='text']").each(function (){
                if($(this).attr("name") == undefined){

                }else {

                    if($(this).val() != null && $(this).val() != ""){
                        data[$(this).attr("name")] = $(this).val();
                    }
                }

            })
            $("#homepage-change-form input[type='date']").each(function (){
                if($(this).val() != null && $(this).val() != ""){
                    data[$(this).attr("name")] = $(this).val();
                }
            })

            const gameStr=$('#game_content_change').summernote('code');
            const playerStr=$('#player_content_change').summernote('code');
            if(gameStr != null && gameStr != ""){
                data["pageGameIntro"] = gameStr;
            }
            if(playerStr != null && playerStr != ""){
                data["pageEvaluation"] = playerStr;
            }
            $.ajax({
                type: 'POST',
                url: "changePh",
                data:JSON.stringify(data),
                contentType: "application/json",
                dataType: "json",
                async:false,
                success: function (result) {

                    alert(result.info)
                }
            })
        })
        $(".hp-close-btn").click(function (){
            $(".hide-div").css("display","none");
            $(".wrap-h").css("display","block");
        })
    })
</script>
