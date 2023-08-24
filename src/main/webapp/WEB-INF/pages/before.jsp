<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
    <title>往期历史</title>
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
      background: #606060;
      
    }
    .his-main-warp{
      width: 100%;
      min-height: 600px;
      
    }
    .bar-warp{
      height: 100px;
      width: 100%;
      border-bottom:2px solid #fff;
    }
    .bar-warp span{
      font-family: "黑体";
      font-size: 3.8em;
      color: #c3e8b2;
      margin-top: 30px;
      position: relative;
      left: 40%;
      display: inline-block;
    }
    .centent-warp{
      width: 20%;
      height: 340px;
      float: left;
      padding-top: 20px; 
    }
    .centent-warp img{

      display: block;
      margin: 0 auto;
    }
    .centent-warp span{

display: block;
      font-family: "Microsoft YaHei";
      font-size: 18px;
      color: #fff;
      text-align: center;
      margin-top:10px; 
    }
  </style>
</head>
<body>
  <div class="his-main-warp">
    <div class="bar-warp">
      <span>往期发布</span>
    </div>
<c:forEach items="${historyList}" var="h" varStatus="status">
    <div class="centent-warp">
     <a href="p/${h.hisUrl}"><img src="${h.homePage.pageImg}"></a>
     <span>${h.homePage.pageGameName}</span>
     <span>${h.updateTime}</span>
  </div>
</c:forEach>
  </div>
</body>
</html>