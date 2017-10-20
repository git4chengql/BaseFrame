<!DOCTYPE html>
<html>
<head>
    <title>投诉</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
</head>
<body>

    <form class="layui-form" action="" id="myform" style="padding-top:20px;">
       <h4 style="text-align:center;">用户投诉信息</h4>
       <div class="layui-form-item" style="padding-top:20px;">
	    <div class="layui-inline">
		    <label class="layui-form-label"><span style="color:red">*</span>姓名:</label>
		    <div class="layui-input-inline">
		      <input id="userName" type="text" name="userName" lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label"><span style="color:red">*</span>手机号码:</label>
		    <div class="layui-input-inline">
		      <input id="telphone" type="text" name="telphone" lay-verify="phone" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label"><span style="color:red">*</span>投诉类型:</label>
		    <div class="layui-input-inline">
		      	<input id="userId" type="hidden" name="userId" autocomplete="off" class="layui-input" value="1" >
		    	<select name="complainType">
		    		<option value="1">服务质量</option>
		    		<option value="2">卫生状况</option>
		    		<option value="3">欺客宰客</option>
		    		<option value="4">门票价格</option>
		    		<option value="5">其他</option>
		    	</select>
		    </div>
	    </div>
	  </div>
	 
	  <div class="layui-form-item">
	    <label class="layui-form-label">投诉主题:</label>
	    <div class="layui-input-block">
	      <input id="title" type="text" name="title" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">投诉内容:<br><br><a style="text-decoration:underline;color: red;" href="javascript:void(0);" onclick="openFAQ();" >(常见问题)</a></label>
	    <div class="layui-input-block">
	      <textarea class="layui-textarea" id="content" name="content"></textarea>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-inline">
		    <label class="layui-form-label"><span style="color:red">*</span>验证码:</label>
		    <div class="layui-input-inline">
		      <input id="code" type="text" name="code" autocomplete="off" lay-verify="required" class="layui-input">
		    </div>
	    </div>
	    <div class="layui-inline">
		    <a href="javascript:void(0);" onclick="changeCode()">
		    	<img id="randCodeImage" alt="验证码"  src="generateCode.do" />
		    </a>
		</div>
	  </div>
 
	  <div class="layui-form-item" style="text-align:center;">
	  	<button class="layui-btn" lay-submit lay-filter="demo1">提交</button>
	  	<button type="reset" class="layui-btn layui-btn-primary">重置</button>
	  </div>
   </form>	 
    
  <#include "/base/bottom.ftl">
  <#include "/base/js4easyui.ftl">
    <script src="/js/ysusoft/complain/complain.js"></script>
</body>
</html>