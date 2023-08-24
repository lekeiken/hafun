
$(document).ready(function(){
	
 //Tabel Interlaced background color 2015-04-20 DeathGhost
 $('.Interlaced tr:odd').addClass('trbgcolor');
 //left menu toggle style
 $('.menu-list-title').click(function(){
	   $(this).next('li').toggle('1500');
	  });
 //menu current background color
 $(".menu-children li").click(function(){
	 $(".menu-children li").css({background:'none'});
	 $(this).css({background:'#f35844'});
	});



//左侧菜单点击ajax请求事件

});
//ajax方式修改管理员密码
function update_pw() {
	var url = 'adminAction/update';

	$("#mainCont_div").load(url);
}
//验证两次密码输入是否一致
function checkpassword() {
	var password = $("#this_pw").val();
	var repassword = $("#this_repw").val();

	if(password == repassword) {
		$("#tishi").html("两次密码输入一致");
		$("#tishi").css("color","green");
		$(".tdBtn").attr("disabled",false);

	}else {
		$("#tishi").html("两次密码输入不一致！");
		$("#tishi").css("color","red");
		$(".tdBtn").attr("disabled",true);
	}
}
function oldpass(){
	var show1 = $("#this_opw").attr("passwordshow");
	var show2 = $("#this_opw_copy").attr("passwordshow");
	if(show1 == 1){
		$("#this_opw").after('<input id="this_opw_copy" type="text" name="oldPassword" passwordshow="2" class="textBox length-middle" style="display:none;"/>')
		$("#this_opw_copy").val($("#this_opw").val());
		$("#this_opw").remove();
		$("#this_opw_copy").show();
	}
	if(show2 == 2){
		$("#this_opw_copy").after('<input id="this_opw" type="password" name="oldPassword" passwordshow="1" class="textBox length-middle" style="display:none;"/>')
		$("#this_opw").val($("#this_opw_copy").val());
		$("#this_opw_copy").remove();
		$("#this_opw").show();
	}
}
function newpass(){

	var show1 = $("#this_pw").attr("passwordshow");
	var show2 = $("#this_pw_copy").attr("passwordshow");
	if(show1 == 1){
		$("#this_pw").after('<input id="this_pw_copy" type="text" name="newPassword" passwordshow="2" class="textBox length-middle" style="display:none;"/>')
		$("#this_pw_copy").val($("#this_pw").val());
		$("#this_pw").remove();
		$("#this_pw_copy").show();
	}
	if(show2 == 2){
		$("#this_pw_copy").after('<input id="this_pw" type="password" name="newPassword" passwordshow="1" class="textBox length-middle" style="display:none;"/>')
		$("#this_pw").val($("#this_pw_copy").val());
		$("#this_pw_copy").remove();
		$("#this_pw").show();
	}
}
//修改管理员密码
function change_pass() {
	$.ajax({
		type: "POST",
		dataType: "json",
		url: "adminChangePW" ,
		data: $('#admin_change_password_form').serialize(),
		success: function (result) {
			console.log(result);//打印服务端返回的数据(调试用)
			var info = result.info;
			if(info != null){
				alert(info);
				if(info == '修改成功'){
					$("form#admin_change_password_form input").val("");
				}
			}

		},
		error : function() {
			alert("异常！");
		}
	});
}





