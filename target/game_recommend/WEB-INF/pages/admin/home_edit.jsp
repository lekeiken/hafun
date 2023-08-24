<%--
  Created by IntelliJ IDEA.
  User: tanto
  Date: 2021/8/3
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i class="add"></i><em>添加试玩推荐游戏</em></span>
        <span class="modular fr"><a href="javascript:void(0)" id="draft_btn" class="pt-link-btn">草稿</a></span>

    </div>
    <form id="home-form" action="javascript:void(0)">
        <table class="list-style">
            <tr>
                <td style="text-align:right;width:15%;">游戏名称：</td>
                <td><input type="text" name="pageGameName" class="textBox length-long"/></td>
            </tr>
            <tr>
                <td style="text-align:right;width:15%;">推荐指数：</td>
                <td><select id="index-option">
                    <option value="5">5</option>
                    <option value="4">4</option>
                    <option value="3">3</option>
                    <option value="2">2</option>
                    <option value="1">1</option>
                </select><span class="index-option"></span></td>
            </tr>
            <tr>
                <td style="text-align:right;">游戏分类：</td>
                <td>

                        <input type="checkbox" name="pageGameType" value="角色扮演">角色扮演
                        <input type="checkbox" name="pageGameType" value="动作">动作
                        <input type="checkbox" name="pageGameType" value="冒险">冒险
                        <input type="checkbox" name="pageGameType" value="动作冒险">动作冒险
                        <input type="checkbox" name="pageGameType" value="策略">策略
                        <input type="checkbox" name="pageGameType" value="模拟经营">模拟经营
                        <input type="checkbox" name="pageGameType" value="射击">射击
                        <input type="checkbox" name="pageGameType" value="体育竞技">体育竞技
                        <input type="checkbox" name="pageGameType" value="卡片卡牌">卡片卡牌
                        <input type="checkbox" name="pageGameType" value="即时战略">即时战略
                        <input type="checkbox" name="pageGameType" value="智益">智益

                </td>
            </tr>
            <tr>
                <td style="text-align:right;">游戏平台：</td>
                <td>
                    <input type="checkbox" name="pagePlatform" value="pc">pc
                    <input type="checkbox" name="pagePlatform" value="xbox1">xbox one
                    <input type="checkbox" name="pagePlatform" value="ps4">ps4
                    <input type="checkbox" name="pagePlatform" value="switch">switch
                </td>
            </tr>
            <tr>
                <td style="text-align:right;">游戏宣传图片：</td>
                <td>
                    <div class="select-img-div"></div>
                    <img id="now_img" width="75" height="105">
                    <input type="hidden" name="pageImg" id="img_name">
                    <input type="button" id="select-img-btn" value="选择图片"/>
                </td>
            </tr>
            <tr>
                <td style="text-align:right;">作者：</td>
                <td>
                    <input type="text" name="pageProducer" class="textBox length-short"/>
                </td>
            </tr>
            <tr>
                <td style="text-align:right;">发售时间：</td>
                <td>
                    <input type="date" name="pageLaunchDate" class="textBox length-short"/>
                </td>
            </tr>
            <tr>
                <td style="text-align:right;">配置&nbsp;系统：</td>
                <td>
                    <input type="text" name="pageSystem" class="textBox length-short"/>
                </td>
            </tr>
            <tr>
                <td style="text-align:right;">CPU：</td>
                <td>
                    <input type="text" name="pageCpu" class="textBox length-short"/>
                </td>
            </tr>
            <tr>
                <td style="text-align:right;">内存：</td>
                <td>
                    <input type="text" name="pageMemory" class="textBox length-short"/>
                </td>
            </tr>
            <tr>
                <td style="text-align:right;">硬盘：</td>
                <td>
                    <input type="text" name="pageDisk" class="textBox length-short"/>
                </td>
            </tr>
            <tr>
                <td style="text-align:right;">显卡：</td>
                <td>
                    <input type="text" name="pageGraphics" class="textBox length-short"/>
                </td>
            </tr>
    </form>
            <tr>
                <td style="text-align:right;">游戏简介：</td>
                <td><div id="game_content" name="pageGameIntro" class="textarea"></div></td>
            </tr>


            <tr>
                <td style="text-align:right;">实玩测评：</td>
                <td><div id="player_content" name="pageEvaluation" class="textarea" ></div></td>
            </tr>
            <tr>
                <td style="text-align:right;"></td>
                <td><input type="submit" id="home_save_btn" value="发布到主页" class="tdBtn"/>
                    <input type="button" id="draft_save_btn" value="保存为草稿" class="tdBtn"/></td>
            </tr>
        </table>

</div>

<script>
    $(document).ready(function() {
        $('#game_content').summernote(
            {
                height:150,
                minHeight:null,
                maxHeight:null,
                focus:true
            }
        );
        $('#player_content').summernote(
            {
                height:150,
                minHeight:null,
                maxHeight:null,
                focus:true
            }
        );
        $("#select-img-btn").click(function (){
            $("#now_img").hide();
            var url = "SelectImg";
            $(".select-img-div").load(url);
            $(".all-img-div").show();
        })

        $("#home_save_btn").click(function (){
            if(iudge_null()){
                alert("有输入框没有填写！")
                return false;
            }
            save_ajax(0,"SaveHP");
        });
        $("#draft_save_btn").click(function (){
            if(iudge_null()){
                alert("有输入框没有填写！")
                return false;
            }
            save_ajax(1,"SaveDraft");
        });

        function iudge_null(){
            $("#home-form input[type='text']").each(function (){
                if($(this).attr("name") == undefined){

                }else {

                    if($(this).val() == ""){

                        return true;
                    }
                }

            })
            $("#home-form input[type='date']").each(function (){
                if($(this).val() == ""){

                    return true;
                }
            })
            $("#home-form input[type='hidden']").each(function (){
                if($(this).val() == ""){
                    return true;

                }
            })
        }

        function save_ajax (i, url){
            const str = "";
            const str1 = "";
            $("input[name='pageGameType']:checked").each(function (i){

                str=str+$(this).val().toString()+",";
            })
            const typeStr = str.slice(0,str.length-1);
            $("input[name='pagePlatform']:checked").each(function (i){

                str1=str1+$(this).val().toString()+"|";
            })
            const typeStr1 = str1.slice(0,str1.length-1);
            const gameStr=$('#game_content').summernote('code');
            const playerStr=$('#player_content').summernote('code');
            const data = {};
            data["pageIsNew"] = i;
            $("#home-form input[type='text']").each(function (){
                if($(this).attr("name") == undefined){

                }else {

                    data[$(this).attr("name")] = $(this).val();
                }

            })
            $("#home-form input[type='date']").each(function (){
                data[$(this).attr("name")] = $(this).val();
            })
            $("#home-form input[type='hidden']").each(function (){
                data[$(this).attr("name")] = $(this).val();
            })
            data["pageIndex"] = $("#index-option").val();
            data["pageGameType"] = typeStr;
            data["pagePlatform"] = typeStr1;
            data["pageGameIntro"] = gameStr;
            data["pageEvaluation"] = playerStr;


            $.ajax({
                type: 'POST',
                url: url,
                data:JSON.stringify(data),
                contentType: "application/json",
                dataType: "json",
                async:false,
                success: function (result) {

                    alert(result.info)
                }
            })
        }
        $("#draft_btn").click(function (){
            $.get("Draft",function(data,status){
                console.log(data);
                $("input[name='pageGameName']").val(data.pageGameName);
                $("input[name='pageProducer']").val(data.pageProducer);
                const date = new Date(data.pageLaunchDate.toString());
                const year = date.getFullYear();
                const month = date.getMonth() + 1;
                const day = date.getDate();
                const time = year + '/' + month + '/' + day;
                $("input[name='pageLaunchDate']").val(time);
                $("input[name='pageSystem']").val(data.pageSystem);
                $("input[name='pageCpu']").val(data.pageCpu);
                $("input[name='pageMemory']").val(data.pageMemory);
                $("input[name='pageDisk']").val(data.pageDisk);
                $("input[name='pageGraphics']").val(data.pageGraphics);
                const g_str = data.pageGameIntro.toString();
                const p_str = data.pageEvaluation.toString();
                $('#game_content').summernote('code',g_str);
                $('#player_content').summernote('code',p_str);
                $('.index-option').text(data.pageIndex);


            });
        })
    });
</script>