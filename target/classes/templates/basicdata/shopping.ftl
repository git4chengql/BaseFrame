<!DOCTYPE html>
<html>
<head>
    <title>购物点</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
</head>
<body>
              
<fieldset class="layui-elem-field">
	 <legend><div>操作</div></legend>
		<div class="layui-field-box">
			<div class="layui-input-inline">
			  <input id="fshoppingName" class="layui-input" placeholder="请输入购物点名称">
			</div>
		<#include "/base/btn.ftl">
	   </div>
   </fieldset>
  <table id="dg"></table>
 
<div id="popContainer" style="display:none;">
<form class="layui-form" action="" style="margin-top:20px;" id="myform">
  <input id="shoppingId" type="hidden" name="shoppingId">
  
  <div style="width:22%;float:left;">
  	<div class="layui-form-item" style="margin-left:20px;">
	    <img id="logoImg" src="/images/evtplace.jpg" class="img-thumbnail" style="border-radius: 10px;width:160px;height:160px;">
	    <div style="margin-left:10px;margin-top:5px;">
 			<input type="file" name="file" class="layui-upload-file" id="test" lay-title="点击添加LOGO"> 
		</div>
	</div>
  </div>
  
  <div style="width:77%;float:left;">
  	 <div class="layui-form-item">
	    <div class="layui-inline">
		    <label class="layui-form-label" style="width:90px;"><span style="color: red">*</span>购物点名称:</label>
		    <div class="layui-input-inline">
		      <input type="text" name="shoppingName" id="shoppingName" lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">所在地:</label>
	      <div class="layui-input-inline">
	         <input type="text" name="locationName" id="locationName" class="layui-input" onclick="$.openBaseCodeWinForTree($(this).attr('id'));">
	      </div>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-inline">
		    <label class="layui-form-label" style="width:90px;">许可证号:</label>
		    <div class="layui-input-inline">
		      <input type="text" name="licenseKey" id="licenseKey" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">营业执照号:</label>
	      <div class="layui-input-inline">
	        <input type="text" name="licenseNumber" id="licenseNumber" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-inline">
		    <label class="layui-form-label" style="width:90px;">组织机构代码:</label>
		    <div class="layui-input-inline">
		      <input type="text" name="organizeCode" id="organizeCode" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label">营业时间:</label>
		    <div class="layui-input-inline">
		      <input type="text" name="opentime" id="opentime" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	     <div class="layui-inline">
	      <label class="layui-form-label" style="width:90px;">质量投诉电话:</label>
	      <div class="layui-input-inline">
	        <input type="text" name="complaintCall" id="complaintCall" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">支付方式:</label>
	      <div class="layui-input-inline">
	        <input type="text" name="payment" id="payment" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	  </div>
	  
  </div>
  
 <div style="width:100%;float:left;">
 	<div class="layui-form-item">
	    <label class="layui-form-label">详细地址:</label>
	    <div class="layui-input-block" style="width:80%;">
	      <input type="text" name="address" id="address" autocomplete="off" class="layui-input">
	    </div>
	 </div>
	 
	 <div class="layui-form-item">
	    <label class="layui-form-label">停车信息:</label>
	    <div class="layui-input-block" style="width:80%;">
	      <input type="text" name="parking" id="parking" autocomplete="off" class="layui-input">
	    </div>
	 </div>
	 
	 <div class="layui-form-item">
	    <label class="layui-form-label">主营商品:</label>
	    <div class="layui-input-block" style="width:80%;">
	      <input type="text" name="commodity" id="commodity" autocomplete="off" class="layui-input">
	    </div>
	 </div>
	 
	 <div class="layui-form-item">
	    <label class="layui-form-label">周边设施:</label>
	    <div class="layui-input-block" style="width:80%;">
	      <input type="text" name="facility" id="facility" autocomplete="off" class="layui-input">
	    </div>
	 </div>
	 
	 <div class="layui-form-item">
	    <label class="layui-form-label">简介:</label>
	    <div class="layui-input-block" style="width:80%;">
	      <input type="text" name="brief" id="brief" autocomplete="off" class="layui-input">
	    </div>
	 </div>
 </div>
  
  <div class="layui-form-item" id="hideDiv">
	   <div class="layui-input-block" style="text-align:center;padding-top:10px;">
		  <button class="layui-btn" lay-submit lay-filter="demo1">提交</button>
		  <button class="layui-btn layui-btn-primary" type="reset" id="closeBtn">关闭</button>
	  </div>
  </div>
</form>
</div>

 <script type="text/template" id="detail">
 	<div class="layui-form-item" style="height:20%;font-family:Times New Roman;">
 		<div style="width:75%;float:left;">
			<div style="margin:15px 5px 0 50px;">
				<font size="5" style="font-weight:bold;color:#343434;"  id="detailname"></font>
			</div>
			<div style="margin:15px 10px 5px 20px;background:#f5f5f5;width:97%;height:130px; ">
				 <div style="padding: 10px 0 2px 10px;float:left;width:38%;">所在地：<span id="detaillocationName"></span></div>
				 <div style="padding: 10px 0 2px 10px;float:left;width:58%;">营业时间：<span id="detailopentime"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">许可证号：<span id="detaillicenseKey"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">组织机构代码：<span id="detailorganizeCode"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">质量投诉电话：<span id="detailcomplaintCall"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">支付方式：<span id="detailpayment"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:98%;">地址：<span id="detailaddress"></span></div>
			</div>
		</div>
 		<div style="width:25%;float:left;padding-top:15px;">
			<img id="detailimg" style="width:190px;height:160px;padding-left:10px;">
		</div>
 	</div>
 	
 	<div class="layui-form-item" style="font-family:Times New Roman;margin-left:20px;padding-top:15px;width:95%;">
 		<span style="width:70px;">简介：</span><span id="detailbrief" style="width:90%;text-align: left;"></span>
 	</div>
 </script>
     
  <#include "/base/bottom.ftl">
  <#include "/base/js4easyui.ftl">
  <script src="/js/ysusoft/basicdata/shopping.js"></script>
</body>
</html>