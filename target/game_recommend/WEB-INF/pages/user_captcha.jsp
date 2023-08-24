
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<a id="clo-cap" class="cap-a">关闭</a><a id="reload-img"  class="cap-a" href="javascript:void(0)">看不清？</a>
<a id="click-img" href="javascript:void(0)">
    <img class="cap-img" src="${img_data}">
</a>
<img smt="1" class="smt-img" src="images/xh1.svg">
<img smt="2" class="smt-img" src="images/xh2.svg">
<img smt="3" class="smt-img" src="images/xh3.svg">
<span>请依次点击：
    <%String [] chars = (String[]) request.getAttribute("char");
        for ( int i = 0; i < chars.length; i++){ %>
   "<% out.println(chars[i]);%>"
<%}%>
</span>

