<%--
  Created by IntelliJ IDEA.
  User: DuckKing
  Date: 2021/12/6
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>头像上传</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body{
            text-align: center;
        }
        #user-photo {
            width:50px;
            height:50px;
            margin-top: 10px;
            border-radius: 50%;
        }
        #photo {
            max-width:100%;
            max-height:350px;
        }
        .img-preview-box {
            text-align: center;
        }
        .img-preview-box > div {
            display: inline-block;;
            margin-right: 10px;
        }
        .img-preview {
            overflow: hidden;
        }
        .img-preview-box .img-preview-lg {
            width: 150px;
            height: 150px;
            border-radius: 50%;
        }
        .img-preview-box .img-preview-md {
            width: 100px;
            height: 100px;
            border-radius: 50%;
        }
        .img-preview-box .img-preview-sm {
            width: 50px;
            height: 50px;
            border-radius: 50%;
        }
    </style>
</head>
<body>
<button class="btn btn-primary" data-target="#changeModal" data-toggle="modal">打开</button><br/>
<div class="user-photo-box">
    <img id="user-photo" src="${pageContext.request.contextPath}/images/12949415.png">
</div>
</div>
<div class="modal fade" id="changeModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title text-primary">
                    <i class="fa fa-pencil"></i>
                    更换头像
                </h4>
            </div>
            <div class="modal-body">
                <p class="tip-info text-center">
                    未选择图片
                </p>
                <div class="img-container hidden">
                    <img src="" alt="" id="photo">
                </div>
                <div class="img-preview-box hidden">
                    <hr>
                    <span>150*150:</span>
                    <div class="img-preview img-preview-lg">
                    </div>
                    <span>100*100:</span>
                    <div class="img-preview img-preview-md">
                    </div>
                    <span>30*30:</span>
                    <div class="img-preview img-preview-sm">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <label class="btn btn-danger pull-left" for="photoInput">
                    <input type="file" class="sr-only" id="photoInput" accept="image/*">
                    <span>打开图片</span>
                </label>
                <button id="subBtn" class="btn btn-primary disabled" disabled="true" >提交</button>
                <button class="btn btn-close" aria-hidden="true" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
    var initCropperInModal = function(img, input, modal){
        var $image = img;
        var $inputImage = input;
        var $modal = modal;
        var options = {
            aspectRatio: 1, // 纵横比
            viewMode: 2,
            preview: '.img-preview' // 预览图的class名
        };
        // 模态框隐藏后需要保存的数据对象
        var saveData = {};
        var URL = window.URL || window.webkitURL;
        var blobURL;
        $modal.on('show.bs.modal',function () {
            // 如果打开模态框时没有选择文件就点击“打开图片”按钮
            if(!$inputImage.val()){
                $inputImage.click();
            }
        }).on('shown.bs.modal', function () {
            // 重新创建
            $image.cropper( $.extend(options, {
                ready: function () {
                    // 当剪切界面就绪后，恢复数据
                    if(saveData.canvasData){
                        $image.cropper('setCanvasData', saveData.canvasData);
                        $image.cropper('setCropBoxData', saveData.cropBoxData);
                    }
                }
            }));
        }).on('hidden.bs.modal', function () {
            // 保存相关数据
            saveData.cropBoxData = $image.cropper('getCropBoxData');
            saveData.canvasData = $image.cropper('getCanvasData');
            // 销毁并将图片保存在img标签
            $image.cropper('destroy').attr('src',blobURL);
        });
        if (URL) {
            $inputImage.change(function() {
                var files = this.files;
                var file;
                if (!$image.data('cropper')) {
                    return;
                }
                if (files && files.length) {
                    file = files[0];
                    if (/^image\/\w+$/.test(file.type)) {

                        if(blobURL) {
                            URL.revokeObjectURL(blobURL);
                        }
                        blobURL = URL.createObjectURL(file);

                        // 重置cropper，将图像替换
                        $image.cropper('reset').cropper('replace', blobURL);

                        // 选择文件后，显示和隐藏相关内容
                        $('.img-container').removeClass('hidden');
                        $('.img-preview-box').removeClass('hidden');
                        $('#changeModal .disabled').removeAttr('disabled').removeClass('disabled');
                        $('#changeModal .tip-info').addClass('hidden');

                    } else {
                        window.alert('请选择一个图像文件！');
                    }
                }
            });
        } else {
            $inputImage.prop('disabled', true).addClass('disabled');
        }
    }



    $("#subBtn").click(function (){

        $('#photo').cropper('getCroppedCanvas',{
            width:100,
            height:100
        }).toBlob(function(blob){
            // 转化为blob后更改src属性，隐藏模态框
            var formData = new FormData();

            var nameImg = new Date().getTime() + '.png';

            formData.set("file",blob,nameImg);
            $.ajax({
                url: 'user/upload_img', // 要上传的地址
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data.status == 0) {
                        // 将上传的头像的地址填入，为保证不载入缓存加个随机数
                        $('.user-photo').attr('src', "${pageContext.request.contextPath}"+data.img_url);
                        $('#changeModal').modal('hide');
                        alert(data.info);
                    } else {
                        alert(data.info);
                    }
                }
            });
        });



    })



    $(function(){
        initCropperInModal($('#photo'),$('#photoInput'),$('#changeModal'));
    });
</script>
</body>
</html>
