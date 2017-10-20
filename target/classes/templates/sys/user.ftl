<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>用户管理</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
</head>
<body>
  <fieldset class="layui-elem-field">
	 <legend><div>操作</div></legend>
		<div class="layui-field-box">
			<div class="layui-input-inline">
			  <input id="userName" class="layui-input" placeholder="请输入用户名称">
			</div>
			<div class="layui-input-inline" style="margin-left:10px;">
			  <button class="layui-btn layui-btn-primary layui-btn-small" id="queryBtn"><i class="layui-icon">&#xe615;</i> 查询</button>
			</div>
			<div class="layui-input-inline">
			  <button class="layui-btn layui-btn-small" id="addBtn"><i class="layui-icon">&#xe654;</i> 新增</button>
			</div>
			<div class="layui-input-inline">
			  <button class="layui-btn layui-btn-normal layui-btn-small" id="editBtn"><i class="layui-icon">&#xe642;</i> 编辑</button>
			</div>
			<div class="layui-input-inline">
			  <button class="layui-btn layui-btn-warm layui-btn-small" id="delBtn"><i class="layui-icon">&#xe640;</i> 删除</button>
			</div>
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
  <script src="/js/ysusoft/sys/user.js"></script>
</body>
</html>