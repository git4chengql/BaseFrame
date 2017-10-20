<!DOCTYPE html>
<html>
<head>
    <title>星级饭店</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
</head>
<body>
              
<fieldset class="layui-elem-field">
	 <legend><div>操作</div></legend>
		<div class="layui-field-box">
			<div class="layui-input-inline">
			  <input id="fhotelName" class="layui-input" placeholder="请输入饭店名称">
			</div>
		<#include "/base/btn.ftl">
	   </div>
   </fieldset>
  <table id="dg"></table>
 
<div id="popContainer" style="display:none;">
<form class="layui-form" action="" style="margin-top:20px;" id="myform">
  <input id="hotelId" type="hidden" name="hotelId">
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
		    <label class="layui-form-label"><span style="color: red">*</span>饭店名称:</label>
		    <div class="layui-input-inline">
		      <input type="text" name="hotelName" id="hotelName" lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">邮编:</label>
	      <div class="layui-input-inline">
	         <input type="text" name="zipCode" id="zipCode" autocomplete="off" class="layui-input">
	       </div>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">饭店类型:</label>
	      <div class="layui-input-inline">
	         <select lay-search="" name="category" id="category">
	            <option value="">请选择类型</option>
	            <option value="1">暂住型饭店</option>
	            <option value="2">长住型饭店</option>
	            <option value="3">度假型饭店</option>
	            <option value="4">会议型饭店</option>
              </select>
	       </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label">饭店星级:</label>
		    <div class="layui-input-inline">
		       <select lay-search="" name="starLevel" id="starLevel">
	            <option value="">请选择星级</option>
	            <option value="1">一星级</option>
	            <option value="2">二星级</option>
	            <option value="3">三星级</option>
	            <option value="4">四星级</option>
	            <option value="5">五星级</option>
               </select>
		    </div>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">负责人:</label>
	      <div class="layui-input-inline">
	        <input type="text" name="manager" id="manager" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">负责人电话:</label>
	      <div class="layui-input-inline">
	        <input type="tel" name="telphone" id="telphone" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">饭店总机:</label>
	      <div class="layui-input-inline">
	        <input type="text" name="switchboard" id="switchboard" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">营业执照号:</label>
	      <div class="layui-input-inline">
	        <input type="text" name="licenseNumber" id="licenseNumber" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	  </div>
	  
  </div>
  
  
  <div style="width:100%;float:left;">
  	 <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">配套设施:</label>
	      <div class="layui-input-inline" style="width:290px;">
	        <input type="text" name="facility" id="facility" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">经营数据:</label>
	      <div class="layui-input-inline" style="width:290px;">
	        <input type="text" name="statistic" id="statistic" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	  </div>
  	 
  	 <div class="layui-form-item">
	    <label class="layui-form-label">详细地址:</label>
	    <div class="layui-input-block" style="width:80%;">
	      <input type="text" name="address" id="address" autocomplete="off" class="layui-input">
	    </div>
	 </div>
	 <div class="layui-form-item">  
	   <label class="layui-form-label">简介:</label>
	    <div class="layui-input-block" style="width:80%;">
	      <textarea class="layui-textarea" name="introduction" id="introduction" style="min-height:50px;"></textarea>
	    </div>
	 </div> 
  </div>
  
  <div class="layui-form-item" style="text-align:center;padding-top:20px;">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">提交</button>
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
				<span style="padding-left:15px;color:#f5c024;font-size:17px;font-weight:bold;" id="detailstarLevel"></span>
				<span style="padding-left:15px;">
					<img src="/images/icon/icon_phone.png"><span style="padding:5px 0 0 5px;" id="detailswitchboard"></span>
				</span>
			</div>
			<div style="margin:15px 10px 5px 20px;background:#f5f5f5;width:97%;height:110px; ">
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">饭店类型：<span id="detailcategory"></span></div>
				 <div style="padding: 10px 0 2px 10px;float:left;width:58%;">工商营业执照号：<span id="detaillicenseNumber"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">负责人：<span id="detailmanager"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">负责人电话：<span id="detailtelphone"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">地址：<span id="detailaddress"></span></div>
			</div>
		</div>
 		<div style="width:25%;float:left;padding-top:15px;">
			<img id="detailimg" style="width:190px;height:160px;padding-left:10px;">
		</div>
 	</div>
 	
 	<div class="layui-form-item" style="font-family:Times New Roman;margin-left:20px;padding-top:15px;width:95%;">
 		<span style="width:70px;">简介：</span><span id="detailintroduction" style="width:90%;display: inline-block;text-align: left;"></span>
 	</div>
 </script>     
     
  <#include "/base/bottom.ftl">
  <#include "/base/js4easyui.ftl">
  <script src="/js/ysusoft/basicdata/hotel.js"></script>
</body>
</html>