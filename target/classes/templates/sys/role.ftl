<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>角色管理</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
</head>
<body>
  <fieldset class="layui-elem-field">
	 <legend><div>操作</div></legend>
		<div class="layui-field-box">
			<div class="layui-input-inline">
			  <input id="roleName" class="layui-input" placeholder="请输入角色名称">
			</div>
		<#include "/base/btn.ftl">
	   </div>
   </fieldset>
  <table id="dg"></table>
  <!--菜单树模版 start-->
   <script type="text/template" id="treeContainer">
	 <ul class="easyui-tree" id="menuTree"></ul>
   </script>
  <!--菜单树模版 end -->
  <#include "/base/bottom.ftl">
  <#include "/base/js4easyui.ftl">
  <script src="/js/ysusoft/sys/role.js"></script>
</body>
</html>