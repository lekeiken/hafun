<%--
  Created by IntelliJ IDEA.
  User: tanto
  Date: 2021/8/3
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i class="user"></i><em>留言/推荐</em></span>
    </div>
    <table class="list-style Interlaced">
        <tr>
            <th>编号/th>
            <th>留言时间</th>
            <th>推荐的游戏名</th>
            <th>留言的愿意理由</th>
            <th>联系方式</th>
            <th>操作</th>
        </tr>
        <tr>
            <td class="center">011</td>
            <td class="center">2015-04-18 10:39</td>
            <td class="center">游戏名</td>
            <td width="300">
                <div style="width:400px;height:50px;overflow:scroll;overflow-x:hidden;">
                    这里是留言内容
                </div>
            </td>
            <td class="center">QQ:394728491</td>
            <td class="center">
                <a class="inline-block" title="移除"><img src="${pageContext.request.contextPath}/images/admin/icon_drop.gif"/></a>
            </td>
        </tr>
    </table>
    <!-- BatchOperation -->
    <div style="overflow:hidden;">
        <!-- turn page -->
        <div class="turnPage center fr">
            <a>第一页</a>
            <a>1</a>
            <a>最后一页</a>
        </div>
    </div>
</div>
