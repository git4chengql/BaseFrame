<!DOCTYPE html>
<html>
<head>
    <title>旅行社</title>
 	<#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
    <#include "/base/bottom.ftl">
    <#include "/base/scroll.ftl">
    <#include "/base/js4easyui.ftl">
</head>
<body>

  <fieldset class="layui-elem-field">
	 <legend><div>操作</div></legend>
		<div class="layui-field-box">
			<div class="layui-input-inline">
			  <input id="ftravelName" class="layui-input" placeholder="请输入旅行社名称">
			</div>
		<#include "/base/btn.ftl">
	   </div>
   </fieldset>
  <table id="dg"></table>

 <div id="popContainer" style="display:none;">
    <form class="layui-form" action="" id="myform" style="margin-top:20px;">
	<input id="travelId" type="hidden" name="travelId">
	
	<div id="wizard">
		<ul id="status">
			<li class="active"><strong>1.</strong>基础信息</li>
			<li><strong>2.</strong>公司信息</li>
			<li><strong>3.</strong>其他信息</li>
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
				   <label class="layui-form-label"><span style="color: red">*</span>旅行社名称:</label>
				    <div class="layui-input-inline">
				      <input type="text" name="travelName" id="travelName" lay-verify="required" autocomplete="off" class="layui-input">
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
				   <label class="layui-form-label">许可证号:</label>
				    <div class="layui-input-inline">
				      <input type="text" name="licenseKey" id="licenseKey" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  <div class="layui-inline">
			        <label class="layui-form-label">旅行社等级</label>
			        <div class="layui-input-inline">
			         <select lay-search="" name="grade" id="grade">
			            <option value="">请选择等级</option>
			            <option value="1">3A</option>
			            <option value="2">4A</option>
			            <option value="3">5A</option>
		              </select>
			       </div>
			     </div>
			    </div>
				  
				<div class="layui-form-item">
				  <div class="layui-inline">
				      <label class="layui-form-label">预定电话:</label>
				      <div class="layui-input-inline">
				        <input type="tel" name="telphone" id="telphone" autocomplete="off" class="layui-input">
				      </div>
				  </div>
				  <div class="layui-inline">
				    <label class="layui-form-label">业务类别:</label>
				    <div class="layui-input-inline">
				      <input type="text" name="category" id="category" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				</div>
				
				<div class="layui-form-item">
				  <div class="layui-inline">
				      <label class="layui-form-label">投诉电话:</label>
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
				  <div class="layui-inline">
				    <label class="layui-form-label" style="width:120px;">工商营业执照号:</label>
				    <div class="layui-input-inline" style="width:240px;">
				      <input type="text" name="licenseNumber" id="licenseNumber" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  <div class="layui-inline">
				    <label class="layui-form-label" style="width:120px;">组织机构代码:</label>
				    <div class="layui-input-inline" style="width:245px;">
				      <input type="text" name="organizeCode" id="organizeCode" autocomplete="off" class="layui-input">
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
				   <label class="layui-form-label">简介:</label>
				    <div class="layui-input-block" style="width:82%;">
				      <textarea class="layui-textarea" name="introduction" id="introduction" style="min-height:50px;"></textarea>
				    </div>
				 </div>
			  </div>
			  <div class="btn_nav">
                  <input type="button" class="next right" value="下一步&raquo;" />
               </div>
            </div>
            
            
			<div class="page">
			    <div class="layui-form-item">  
				   <label class="layui-form-label">产品信息:</label>
				    <div class="layui-input-block" style="width:82%;">
				      <textarea class="layui-textarea" name="production" id="production" style="min-height:50px;"></textarea>
				    </div>
			   </div>
			   
			  	<div class="layui-form-item">  
				   <label class="layui-form-label">注册信息:</label>
				    <div class="layui-input-block" style="width:82%;">
				      <input type="text" name="register" id="register" autocomplete="off" class="layui-input">
				    </div>
				</div>
				
				<div class="layui-form-item">  
				   <label class="layui-form-label">变更信息:</label>
				    <div class="layui-input-block" style="width:82%;">
				      <input type="text" name="alteration" id="alteration" autocomplete="off" class="layui-input">
				    </div>
				 </div>
				 
				 <div class="layui-form-item">  
				   <label class="layui-form-label">网站信息:</label>
				    <div class="layui-input-block" style="width:82%;">
				      <textarea class="layui-textarea" name="website" id="website" style="min-height:50px;"></textarea>
				    </div>
				 </div>
				 
				 <div class="layui-form-item">  
				   <label class="layui-form-label">人事信息:</label>
				    <div class="layui-input-block" style="width:82%;">
				      <textarea class="layui-textarea" name="personnel" id="personnel" style="min-height:50px;"></textarea>
				    </div>
				 </div>
				 
				 <div class="layui-form-item">  
				   <label class="layui-form-label">财务信息:</label>
				    <div class="layui-input-block" style="width:82%;">
				      <textarea class="layui-textarea" name="finance" id="finance" style="min-height:50px;"></textarea>
				    </div>
				 </div>
			   
               <div class="btn_nav">
                  <input type="button" class="prev" style="float:left" value="&laquo;上一步" />
                  <input type="button" class="next right" value="下一步&raquo;" />
               </div>
            </div>
			<div class="page">
                <div class="layui-form-item">  
				   <label class="layui-form-label" style="width:120px;">质保金缴纳情况:</label>
				    <div class="layui-input-block" style="width:80%;margin-left:150px;">
				      <textarea class="layui-textarea" name="guaranteePay" id="guaranteePay" style="min-height:50px;"></textarea>
				    </div>
				 </div>
               
				<div class="layui-form-item">  
				   <label class="layui-form-label" style="width:120px;">责任险投保情况:</label>
				    <div class="layui-input-block" style="width:80%;margin-left:150px;">
				      <textarea class="layui-textarea" name="insure" id="insure" style="min-height:50px;"></textarea>
				    </div>
				</div>
               
               <div class="btn_nav">
                  <input type="button" class="prev" style="float:left" value="&laquo;上一步" />
                  <button class="next right layui-btn" lay-submit lay-filter="demo1">提交</button>
                  <button class="next right layui-btn layui-btn-primary" type="reset" id="closeBtn">关闭</button>
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
					<img src="/images/icon/icon_phone.png"><span style="padding:5px 0 0 5px;" id="detailtelphone"></span>
				</span>
			</div>
			<div style="margin:15px 10px 5px 20px;background:#f5f5f5;width:97%;height:110px; ">
				 <div style="padding: 10px 0 2px 10px;float:left;width:38%;">许可证号：<span id="detaillicenseKey"></span></div>
				 <div style="padding: 10px 0 2px 10px;float:left;width:58%;">工商营业执照号：<span id="detaillicenseNumber"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">网站：<span id="detailwebsite"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">组织机构代码：<span id="detailorganizeCode"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">业务类型：<span id="detailcategory"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">地址：<span id="detailaddress"></span></div>
			</div>
		</div>
 		<div style="width:25%;float:left;padding-top:15px;">
			<img id="detailimg" style="width:190px;height:160px;padding-left:10px;">
		</div>
 	</div>
 	
 	<div class="layui-form-item" style="font-family:Times New Roman;margin-left:20px;padding-top:15px;width:95%;">
 		<span style="width:70px;">注册信息：</span><span id="detailregister" style="width:90%;display: inline-block;text-align: left;"></span>
 		<div class="hr-line-dashed"></div> 
 		<span style="width:70px;">变更信息：</span><span id="detailalteration" style="width:90%;display: inline-block;text-align: left;"></span>
 		<div class="hr-line-dashed"></div> 
 		<span style="width:70px;">产品信息：</span><span id="detailproduction" style="width:90%;display: inline-block;text-align: left;"></span>
 		<div class="hr-line-dashed"></div> 
 		<span style="width:70px;">人事信息：</span><span id="detailpersonnel" style="width:90%;display: inline-block;text-align: left;"></span>
 	</div>
 </script>

<script src="/js/ysusoft/basicdata/travel.js"></script>
</body>
</html>
