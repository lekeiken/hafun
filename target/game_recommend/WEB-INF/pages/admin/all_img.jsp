<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="all-img-div">
    <c:forEach items="${img_list}" var="i" varStatus="status">
        <div><a href="javascript:void(0)"><img src="${pageContext.request.contextPath}/uploads/temp/${i}" width="75" height="105"></a></div>
    </c:forEach>
</div>
<script>

    $(document).ready(function() {
        $('.all-img-div a').each(function (){
            $(this).click(function (){

                $(".all-img-div").hide();

                var img_src = $(this).find("img").attr("src");
                $("#now_img").attr("src",img_src);
                $("#now_img").show();
                if(img_src.includes("/uploads/temp/")){

                    var a =img_src.lastIndexOf("/uploads");
                    var n=img_src.slice(a,img_src.length);
                }
                $("#img_name").val(n);
                console.log(n);
            })
        })
    })
</script>
