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
			  <button class="layui-btn layui-btn-small" id="insertBtn"><i class="layui-icon">&#xe654;</i> 新增列</button>
			</div>
			<div class="layui-input-inline">
			  <button class="layui-btn layui-btn-small" id="saveBtn"><i class="layui-icon">&#xe654;</i> 保存列</button>
			</div>
			<div class="layui-input-inline">
			  <button class="layui-btn layui-btn-small" id="generatBtn"><i class="layui-icon">&#xe654;</i> 生成代码</button>
			</div>
			
	   </div>
   </fieldset>
  <table id="dg"></table>
  
  
  <script type="text/template" id="popContainer">
<form class="layui-form" action="" style="margin-top:20px;" id="myform">
  <input id="shoppingId" type="hidden" name="shoppingId">
  <div class="layui-form-item">
    <div class="layui-inline">
	    <label class="layui-form-label"><span style="color: red">*</span>模型名称:</label>
	    <div class="layui-input-inline">
	     	<input type="text" placeholder="请输入模型名称" lay-verify="required" class="form-control" id="modelText"/>
	    </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">文件路径:</label>
      <div class="layui-input-inline">
      	<input type="text" placeholder="请输入文件路径"  lay-verify="required"  class="form-control" id="rootText"/>
      </div>
    </div>
  </div>
  
  
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label"><span style="color: red">*</span>输入表名:</label>
      <div class="layui-input-inline">
      	<input type="text" placeholder="请输入表名"  lay-verify="required"  class="form-control" id="tableText"/>
      </div>
    </div>
    <div class="layui-inline">
	    <label class="layui-form-label">注释内容:</label>
	    <div class="layui-input-inline">
	     	<input type="text" placeholder="请输入注释内容" class="form-control" id="descText"/>
	    </div>
    </div>
  </div>
  <div class="layui-form-item" id="hideDiv" style="display:block">
	   <div class="layui-input-block" style="text-align:right;">
		  <button class="layui-btn" lay-submit lay-filter="demo1"  id="confirmBtn">提交</button>
		  <button class="layui-btn layui-btn-primary" type="reset" id="closeBtn">关闭</button>
	  </div>
  </div>
</form>
</script>
  
  
  <#include "/base/bottom.ftl">
  <#include "/base/js4easyui.ftl">
  <script src="/js/ysusoft/sys/table.js"></script>
</body>
</html>