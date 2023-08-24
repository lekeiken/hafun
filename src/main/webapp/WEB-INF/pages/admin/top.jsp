
<div class="header">
 <div class="fl logo-a" style="width:376px;height:75px;background-image: url(${pageContext.request.contextPath}/images/admin/admin_logo.png);background-repeat: no-repeat;
         background-position: 38% 50%;">
  <div class="logo-b" style="width:181px;height:65px;background-image: url(${pageContext.request.contextPath}/images/admin/logo.png);background-repeat: no-repeat;
          background-position: 0% 50%;">
  </div>
 </div>
 <div class="top-link">
  <a href="#"  title="访问站点"><i class="shopLinkIcon"></i><span>访问站点</span></a>
  <a href="#"  title="管理员"><i class="adminIcon"></i><span>管理员：${adminNikeName}</span></a>
  <a href="javascript:void(0)" onclick="update_pw();return false;" title="修改密码"><i class="revisepwdIcon"></i><span>修改密码</span></a>
  <a href="adminAction/quit" title="安全退出" ><i class="quitIcon"></i><span>安全退出</span></a>
 </div>
</div>
