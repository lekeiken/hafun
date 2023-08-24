
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="wrap">

    <p>上传：</p>
    <form action="javascript:void(0)" enctype="multipart/form-data">
        <input type="file" name="file" value="选择图片"/>
        <input type="button" class="upbtn" value="上传">
    </form>
    <p class="upload_info" style="color: red"></p>
</div>
<script type="text/javascript">
    $(document).ready(function(){

        $(".upbtn").click(function (){

            var formData = new FormData();
            formData.append("file",$("input[name='file']")[0].files[0])
            $.ajax({
                type: "POST",
                url: "upload" ,
                data:formData,
                processData: false,  // 告诉jQuery不要去处理发送的数据
                contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
                success : function(data){

                        $(".upload_info").text(data.info)

                    }
            });
        })

    });
</script>
