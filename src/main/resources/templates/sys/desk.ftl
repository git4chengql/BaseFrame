<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>${systemsetting.title}</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <#include "/base/layui.ftl">
</head>
<body>
<div class="layui-layout layui-layout-admin" style="border-bottom: solid 5px #a7c0e0;">
			<div class="layui-header header header-demo">
				<div class="layui-main">
					<div class="admin-login-box">
						<a class="logo" style="left: 0;" href="#">
							<img src='/images/login/logo.png'>
						</a>
					</div>
					<div class="sysname">
						<span style="color:#eaeaea;">${systemName}</span><br>
						<a style="margin-left:20px;font-size:10px;color:#eaeaea;cursor: pointer;" href="syslist.do">[ 返回首页 ]</a>
					</div>
					<div id="switch"></div>
					<#assign firstMenuCount="0">
					<ul class="layui-nav admin-header-item daclass">
						<#list menus as menu>
						   <#if menu.isFlag==1>
	                        <li class="layui-nav-item ysusoft" menuId="${menu.menuId}">
								<a href="javascript:;">
									<div class="topmenu ${menu.icon}"></div>
									<span style="font-size:13px;"> ${menu.title}</span>
								</a>
							</li>
							<#else>  
							   <#assign firstMenuCount="1">
						   </#if>
	                    </#list>
	                    
	                    <#if firstMenuCount!="0">
	                    <li class="layui-nav-item" id="moreMenu">
							<a href="javascript:;"><i class="fa fa-bars" aria-hidden="true"></i> 更多</a>
							<dl class="layui-nav-child nav-backg">
							     <#list menus as menu>
							      <#if menu.isFlag!=1>
							       <dd class="ysusoft" menuId="${menu.menuId}">
									 <a href="javascript:;"><i class="layui-icon">${menu.icon}</i> ${menu.title}</a>
								   </dd>
								 </#if>
								 </#list>
						    </dl>
						</li>
						<!--更多菜单end--->
						</#if>
						<li class="layui-nav-item ">
							<a href="javascript:;" class="admin-header-user">
								<img src="/images/admin.png" />
								<span>${currentUser.userName}</span>
							</a>
							<dl class="layui-nav-child nav-backg">
								<dd style="display:none;">
									<a href="javascript:;"><i class="fa fa-user-circle" aria-hidden="true"></i> 个人信息</a>
								</dd>
								<dd style="display:none;">
									<a href="javascript:;"><i class="fa fa-gear" aria-hidden="true"></i> 设置</a>
								</dd>
								<dd id="lock">
									<a href="javascript:;">
										<i class="fa fa-lock" aria-hidden="true" style="padding-right: 3px;padding-left: 1px;"></i> 锁屏 (Alt+L)
									</a>
								</dd>
								<dd>
									<a href="logout.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
								</dd>
							</dl>
						</li>
					</ul>
					<ul class="layui-nav admin-header-item-mobile">
						<li class="layui-nav-item">
							<a href="logout.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="layui-side layui-bg-black" style="border-right: solid 2px #1AA094;" id="admin-side">
				<div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
			</div>
			<#if systemId==0>
			<div class="layui-body" id="admin-body" style="bottom: 0;">
				<div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
					<ul class="layui-tab-title" id="tabtitle">
						<li class="layui-this">
							<i class="fa fa-home" aria-hidden="true"></i>
							<cite id="firsttitle">${firstmenu.title}</cite>
						</li>
					</ul>
					<div class="layui-tab-content" style="min-height: 150px;padding:0;">
						<div class="layui-tab-item layui-show">
							<iframe src="${firstmenu.href}"></iframe>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-footer footer footer-demo" id="admin-footer">
				<div class="layui-main">
					<p>Copyright © 秦旅智慧旅游有限公司</p>
				</div>
			</div>
			</#if>
			<#if systemId==6>
			<div class="layui-supermap" id="admin-supermap">
				<div class="layui-tab admin-nav-card layui-tab-brief" style="height:97%;" lay-filter="admin-tab">
					<div id="admin-supermap-body" style="min-height: 150px; padding: 0px;height:100%;">
						<iframe src="${firstmenu.href}" style="width:100%; height:100%;"></iframe>
					</div>
				</div>
			</div>
			</#if>
			
			<div class="site-tree-mobile layui-hide">
				<i class="layui-icon">&#xe602;</i>
			</div>
			<div class="site-mobile-shade"></div>
			<!--锁屏模板 start-->
			<script type="text/template" id="lock-temp">
				<div class="admin-header-lock" id="lock-box">
					<div class="admin-header-lock-img">
						<img src="/images/admin.png"/>
					</div>
					<div class="admin-header-lock-name" id="lockUserName">beginner</div>
					<input type="text" class="admin-header-lock-input" value="输入密码解锁.." name="lockPwd" id="lockPwd" />
					<button class="layui-btn layui-btn-small" id="unlock">解锁</button>
				</div>
			</script>
			<!--锁屏模板 end -->
			
  <#include "/base/bottom.ftl">
	<script>
		if(${systemId}==6){
	    	$('#admin-side').hide();
	    	$('#tabtitle').hide();
	    	$('#admin-footer').hide();
	    }
	</script>
			
</body>
</html>