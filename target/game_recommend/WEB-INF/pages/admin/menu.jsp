
<div class="menu-list">
 <a href="${pageContext.request.contextPath}/manager/admin" target="mainCont" class="block menu-list-title center" style="border:none;margin-bottom:8px;color:#fff;">起始页</a>
 <ul>
  <li class="menu-list-title">
   <span>内容管理</span>
   <i>◢</i>
  </li>
  <li>
   <ul class="menu-children">
    <li><a href="adminAction/homeedit" id="edit_home" title="编辑首页内容">首页编辑</a></li>
    <li><a href="adminAction/history" id="history" title="">历史编辑列表</a></li>
    <li><a href="adminAction/comment" id="user_comment" title="评论列表">评论管理</a></li>
    <li><a href="adminAction/user" id="user_list" title="用户列表">用户管理</a></li>
    <li><a href="adminAction/message" id="web_message" title="向我推荐游戏的留言">留言管理</a></li>
   </ul>
  </li>
  <li class="menu-list-title">
   <span>系统设置</span>
   <i>◢</i>
  </li>
  <li>
   <ul class="menu-children">
    <li><a href="adminAction/upload" id="up_set" title="用户上传头像设置" target="mainCont">上传图片</a></li>
    <li><a href="adminAction/admin_set" id="admin_list" title="站点基本设置" target="mainCont">站点管理员</a></li>
    <li><a href="adminAction/log" id="log_list" title="网站日志管理" target="mainCont">操作日志列表</a></li>
   </ul>
  </li>
 </ul>
</div>
<script>
 $(document).ready(function(){
  $("#edit_home").click(function (e){

   menuoption(e,$(this));

  })

  $("#history").click(function (e){menuoption(e,$(this));})

  $("#user_comment").click(function (e){menuoption(e,$(this));})

  $("#user_list").click(function (e){menuoption(e,$(this));})

  $("#web_message").click(function (e){menuoption(e,$(this));})

  $("#up_set").click(function (e){menuoption(e,$(this));})

  $("#admin_list").click(function (e){menuoption(e,$(this));})

  $("#log_list").click(function (e){menuoption(e,$(this));})

  function menuoption(e,i){
   var url = i.attr('href');
   $("#mainCont_div").load(url);
   e.preventDefault();
  }
 });


</script>