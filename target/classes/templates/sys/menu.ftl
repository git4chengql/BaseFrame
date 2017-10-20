<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>菜单管理</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
</head>
<body>
    <fieldset class="layui-elem-field">
	 <legend><div>操作</div></legend>
		<div class="layui-field-box">
			<div class="layui-input-inline">
			  <button class="layui-btn layui-btn-primary layui-btn-small" id="addFirstBtn"><i class="layui-icon">&#xe654;</i> 新增一级菜单</button>
			  <button class="layui-btn layui-btn-primary layui-btn-small" id="addSecondBtn"><i class="layui-icon">&#xe654;</i> 新增二级菜单</button>
			  <button class="layui-btn layui-btn-small" id="editBtn"><i class="layui-icon">&#xe642;</i> 编辑</button>
			  <button class="layui-btn layui-btn-danger layui-btn-small" id="delBtn"><i class="layui-icon">&#xe640;</i> 删除</button>
			</div>
	   </div>
   </fieldset>
    <div id="treeC"  style="width:200px;height:100%;overflow: auto;margin-left:20px;" class="ysusoft-div">
      <ul class="easyui-tree" id="menuTree">
      </ul>
   </div>
   <!--表单-->
   <div class="layui-field-box ysusoft-div" style="margin-left:40px;display:none;" id="formDiv">
      <h4 id="title_xz" style="text-align:center;padding-bottom:20px;">新增一级菜单</h4>
        <input id="menuId" type="hidden" name="menuId">
       <form class="layui-form" action="" id="myform">
       <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">所属系统:</label>
	      <div class="layui-input-inline">
	          <select lay-search="" name="systemId" id="systemId">
	            <#list systems as system>
				   <option value="${system.systemId}">${system.systemName}</option>
                </#list>
             </select>
	      </div>
	    </div>
	 </div>
      <div class="layui-form-item" style="display:none;" id="parentmenu">
	    <label class="layui-form-label">上级菜单:</label>
	    <div class="layui-input-block">
	      <input id="parentMenuId" type="hidden" name="parentMenuId">
	      <input id="parentMenuName" type="text" name="parentMenuName" lay-verify="title" autocomplete="off" disabled="disabled" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">菜单名称:</label>
	    <div class="layui-input-block">
	      <input id="title" type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入菜单名称" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">菜单地址:</label>
	    <div class="layui-input-block">
	      <input id="href" type="text" name="href" lay-verify="title" autocomplete="off" placeholder="请输入菜单地址" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">菜单图标:</label>
	    <div class="layui-input-block">
	      <input id="icon" type="text" name="icon" lay-verify="title" autocomplete="off" placeholder="请选择菜单图标" class="layui-input">
	    </div>
	  </div>
	   </form>
	   <div class="layui-form-item">
	    <label class="layui-form-label">顶部显示:</label>
	    <div class="layui-input-block">
	      <input type="radio" name="isFlag" value="1" title="是">是
          <input type="radio" name="isFlag" value="0" title="否" checked>否
	    </div>
	  </div>
	  <div class="layui-form-item" style="padding-left:40px;">
	  	<button class="layui-btn layui-btn-small" id="saveBtn">保存</button>
	  	<button class="layui-btn layui-btn-small" id="updateBtn" style="display:none;">更新</button>
	  	<button class="layui-btn layui-btn-primary layui-btn-small" id="clearBtn">清空</button>
	  </div>
   </div>
  <#include "/base/bottom.ftl">
  <#include "/base/js4easyui.ftl">
  <script src="/js/ysusoft/sys/menu.js"></script>
</body>
</html>