<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>代码生成</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <#include "/base/meta.ftl">
</head>
<body style="padding-top: 10px;">
	<div class="row">  
       <div class="col-md-12">
           <div class="layui-form-item">
           <form class="layui-form" action="">
		       <label class="layui-form-label">单行选择框</label>
			    <div class="layui-input-block">
			      <select name="interest" lay-filter="aihao">
			        <option value=""></option>
			        <option value="0">写作</option>
			        <option value="1" selected="">阅读</option>
			        <option value="2">游戏</option>
			        <option value="3">音乐</option>
			        <option value="4">旅行</option>
			      </select>
		         </div>
           </form>
  </div>
     	</div> 
     </div>  
  <#include "/base/bottom.ftl">
  <script>
     layui.use(['form', 'layedit', 'laydate'], function(){
     
     });
  </script>
</body>
</html>