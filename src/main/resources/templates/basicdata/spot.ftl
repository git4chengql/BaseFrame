<!DOCTYPE html>
<html>
<head>
    <title>旅游景区</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
</head>
<body>
              
<fieldset class="layui-elem-field">
	 <legend><div>操作</div></legend>
		<div class="layui-field-box">
			<div class="layui-input-inline">
			  <input id="fspotName" class="layui-input" placeholder="请输入景区名称">
			</div>
		<#include "/base/btn.ftl">
	   </div>
   </fieldset>
  <table id="dg"></table>
 
<div id="popContainer" style="display:none;">
<form class="layui-form" action="" style="margin-top:20px;" id="myform">
  <input id="spotId" type="hidden" name="spotId">
  
  <div id="wizard">
	<ul id="status">
		<li class="active"><strong>1.</strong>基础信息</li>
		<li><strong>2.</strong>其他信息</li>
	</ul>
  	<div class="items">
			<div class="page">
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
						    <label class="layui-form-label"><span style="color: red">*</span>景区名称:</label>
						    <div class="layui-input-inline">
						      <input type="text" name="spotName" id="spotName" lay-verify="required" autocomplete="off" class="layui-input">
						    </div>
					    </div>
					    <div class="layui-inline">
					      <label class="layui-form-label">所在地:</label>
					      <div class="layui-input-inline">
					        <input type="text" name="locationName" id="locationName" autocomplete="off" class="layui-input" onclick="$.openBaseCodeWinForTree($(this).attr('id'));">
					      </div>
					    </div>
					  </div>
					  
					  <div class="layui-form-item">
					    <div class="layui-inline">
						    <label class="layui-form-label">许可证号:</label>
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
						    <label class="layui-form-label">机构代码:</label>
						    <div class="layui-input-inline">
						      <input type="text" name="organizeCode" id="organizeCode" autocomplete="off" class="layui-input">
						    </div>
					    </div>
					    <div class="layui-inline">
					      <label class="layui-form-label">景区等级:</label>
					      <div class="layui-input-inline">
					        <input type="text" name="grade" id="grade" autocomplete="off" class="layui-input">
					      </div>
					    </div>
					  </div>
				  
				  	  <div class="layui-form-item">
					    <div class="layui-inline">
						    <label class="layui-form-label">景区类型:</label>
						    <div class="layui-input-inline">
						      <input type="text" name="category" id="category" autocomplete="off" class="layui-input">
						    </div>
					    </div>
					    <div class="layui-inline">
					      <label class="layui-form-label">游览季节:</label>
					      <div class="layui-input-inline">
					        <input type="text" name="season" id="season" autocomplete="off" class="layui-input">
					      </div>
					    </div>
					  </div>
					  
				  </div>
  				  <div style="width:100%;float:left;">
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
					        <input type="text" name="telphone" id="telphone" lay-verify="phone" autocomplete="off" class="layui-input">
					      </div>
					    </div>
					  </div>
					  
				 	 <div class="layui-form-item">
					    <div class="layui-inline">
						    <label class="layui-form-label">开放时间:</label>
						    <div class="layui-input-inline">
						      <input type="text" name="opentime" id="opentime" autocomplete="off" class="layui-input">
						    </div>
					    </div>
					    <div class="layui-inline">
					      <label class="layui-form-label">门票价格:</label>
					      <div class="layui-input-inline">
					        <input type="text" name="price" id="price" autocomplete="off" class="layui-input">
					      </div>
					    </div>
					  </div>
				 	 
				 	 <div class="layui-form-item">
					    <label class="layui-form-label">详细地址:</label>
					    <div class="layui-input-block" style="width:82%;">
					      <input type="text" name="address" id="address" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					  
					   <div class="layui-form-item">
					    <label class="layui-form-label">门票说明:</label>
					    <div class="layui-input-block" style="width:82%;">
					      <input type="text" name="description" id="description" autocomplete="off" class="layui-input">
					    </div>
					  </div>
			     </div>
			     <div class="btn_nav">
	                  <input type="button" class="next right" value="下一步&raquo;" />
	             </div>
			  </div>
			  <div class="page">
			  	  <div class="layui-form-item">
				    <div class="layui-inline">
					    <label class="layui-form-label">景区面积:</label>
					    <div class="layui-input-inline">
					      <input type="text" name="area" id="area" autocomplete="off" class="layui-input">
					    </div>
				    </div>
				    <div class="layui-inline">
				      <label class="layui-form-label">基础设施:</label>
				      <div class="layui-input-inline">
				        <input type="text" name="infrastructure" id="infrastructure" autocomplete="off" class="layui-input">
				      </div>
				    </div>
				  </div>
			  	  
			  	  <div class="layui-form-item">
				    <div class="layui-inline">
					    <label class="layui-form-label">景点称号:</label>
					    <div class="layui-input-inline">
					      <input type="text" name="spottitle" id="spottitle" autocomplete="off" class="layui-input">
					    </div>
				    </div>
				    <div class="layui-inline">
				      <label class="layui-form-label">景观类型:</label>
				      <div class="layui-input-inline">
				        <input type="text" name="landtype" id="landtype" autocomplete="off" class="layui-input">
				      </div>
				    </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <div class="layui-inline">
					    <label class="layui-form-label">周边设施:</label>
					    <div class="layui-input-inline">
					      <input type="text" name="around" id="around" autocomplete="off" class="layui-input">
					    </div>
				    </div>
				    <div class="layui-inline">
				      <label class="layui-form-label">游览线路:</label>
				      <div class="layui-input-inline">
				        <input type="text" name="sightline" id="sightline" autocomplete="off" class="layui-input">
				      </div>
				    </div>
				  </div>
				  
				 <div class="layui-form-item">
				    <div class="layui-inline">
					    <label class="layui-form-label">旅游活动:</label>
					    <div class="layui-input-inline">
					      <input type="text" name="activity" id="activity" autocomplete="off" class="layui-input">
					    </div>
				    </div>
				    <div class="layui-inline">
				      <label class="layui-form-label">服务设施:</label>
				      <div class="layui-input-inline">
				        <input type="text" name="service" id="service" autocomplete="off" class="layui-input">
				      </div>
				    </div>
				  </div>
			  	  
			  	  <div class="layui-form-item">
				    <label class="layui-form-label">交通情况:</label>
				    <div class="layui-input-block">
				      <input type="text" name="traffic" id="traffic" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				   <div class="layui-form-item">
				    <label class="layui-form-label">停车场信息:</label>
				    <div class="layui-input-block">
				      <input type="text" name="parking" id="parking" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <label class="layui-form-label">加油站信息:</label>
				    <div class="layui-input-block">
				      <input type="text" name="gas" id="gas" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <label class="layui-form-label">景区简介:</label>
				    <div class="layui-input-block">
				      <input type="text" name="brif" id="brif" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="btn_nav">
	                  <input type="button" class="prev" style="float:left" value="&laquo;上一步" />
	                  <button class="next right layui-btn" lay-submit lay-filter="demo1">提交</button>
	                  <button class="next right layui-btn layui-btn-primary" type="reset" id="closeBtn">关闭</button>
	               </div>
			  </div>
		</div>
	</div>
</form>
</div>
 
  <script type="text/template" id="detail">
 	<div class="layui-form-item" style="height:20%;font-family:Times New Roman;">
 		<div style="width:75%;float:left;">
			<div style="margin:15px 5px 0 50px;">
				<font size="5" style="font-weight:bold;color:#343434;"  id="detailname"></font>
				<span style="padding-left:15px;color:#f5c024;font-size:17px;font-weight:bold;" id="detailgrade"></span>
				<span style="padding-left:15px;">
					<img src="/images/icon/icon_jq.png"><span style="padding:5px 0 0 5px;" id="detailcategory"></span>
				</span>
			</div>
			<div style="margin:15px 10px 5px 20px;background:#f5f5f5;width:97%;height:110px; ">
				 <div style="padding: 10px 0 2px 10px;float:left;width:38%;">所在地：<span id="detaillocationName"></span></div>
				 <div style="padding: 10px 0 2px 10px;float:left;width:58%;">开放时间：<span id="detailopentime"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">负责人：<span id="detailmanager"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">门票价格：<span id="detailprice"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">负责人电话：<span id="detailtelphone"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">地址：<span id="detailaddress"></span></div>
			</div>
		</div>
 		<div style="width:25%;float:left;padding-top:15px;">
			<img id="detailimg" style="width:190px;height:160px;padding-left:10px;">
		</div>
 	</div>
 	
 	<div class="layui-form-item" style="font-family:Times New Roman;margin-left:20px;padding-top:15px;width:95%;">
 		<span style="width:70px;">景区简介：</span><span id="detailbrif" style="width:90%;display: inline-block;text-align: left;"></span>
 	</div>
 </script>
 
     
  <#include "/base/bottom.ftl">
  <#include "/base/scroll.ftl">
  <#include "/base/js4easyui.ftl">
  <script src="/js/ysusoft/basicdata/spot.js"></script>
</body>
</html>