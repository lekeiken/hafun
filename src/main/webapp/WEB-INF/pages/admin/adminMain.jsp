<%--
  Created by IntelliJ IDEA.
  User: tanto
  Date: 2021/6/5
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/public.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
    <link href="${pageContext.request.contextPath}/css/adminStyle.css" rel="stylesheet" type="text/css" charset="UTF-8" />
    <meta name="robots" content="nofollow"/>

    <link href="${pageContext.request.contextPath}/dist/summernote.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/dist/summernote.min.js"></script>
</head>
<body>
<div class="max-column" style="color:#3d3d3d;background:#313131;width:100%;height: 1200px;overflow:hidden;">
    <%@ include file="top.jsp"%>
    <div class="left-column" style="float: left;width: 14%;height: 100%;">

        <div class="middle-column" style=" float: right;width: 10px;height: 100%;">
            <%@ include file="bar.jsp"%>
        </div>
        <%@ include file="menu.jsp"%>
    </div>
    <div class="right-column" id="mainCont_div" style="float: left;width: 86%;height: 100%;background-color: #cccccc">
        <%@ include file="main.jsp"%>
    </div>
</div>
</body>
</html>
