<!DOCTYPE html>
<html>
<head>
    <title>导游</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">

    <style>
   .thumbnail:hover{
		border: 1px solid #ff00ff;
		cursor:pointer;
	} 
	.wrap { border: 1px solid #ddd; }
	.wrap_click { border: 1px solid #ff00ff; }
	</style>
</head>
<body>
              
<fieldset class="layui-elem-field">
	 <legend><div>操作</div></legend>
		<div class="layui-field-box">
			<div class="layui-input-inline">
			  <input id="fguideName" class="layui-input" placeholder="请输入导游姓名">
			</div>
		<#include "/base/btn.ftl">
	   </div>
 </fieldset>
 
 <div class="layui-form-item" >
	 <div class="container-fluid" style="height:88%;overflow-y:auto;">
		<div id="guidesC" ></div>
	</div>
	<div id="pageDemo" style="left:40%;position:absolute; bottom:0;"></div>
</div>
	 

 <#-- 新增弹框 -->
<div class="layui-form-item" id="popContainer" style="display:none;">
<form class="layui-form" action="" style="margin-top:20px;" id="myform">
  <input id="guideId" type="hidden" name="guideId">
  
  <div id="wizard">
	<ul id="status">
		<li class="active"><strong>1.</strong>基础信息</li>
		<li><strong>2.</strong>其他信息</li>
	</ul>
  	<div class="items">
			<div class="page">
  
			  <div style="width:22%;float:left;">
			  	<div class="layui-form-item" style="margin-left:20%;">
				    <img id="humanImg" src="/images/placeholder.png" class="img-thumbnail" style="border-radius: 10px;width:160px;height:160px;">
				    <div style="margin-left:10px;margin-top:5px;">
			 			<input type="file" name="file" class="layui-upload-file" id="test" lay-title="点击添加"> 
					</div>
				</div>
			  </div>
			  
			  <div style="width:77%;float:left;">
			  	 <div class="layui-form-item">
				    <div class="layui-inline">
					    <label class="layui-form-label"><span style="color: red">*</span>导游姓名:</label>
					    <div class="layui-input-inline">
					      <input type="text" name="guideName" id="guideName" lay-verify="required" autocomplete="off" class="layui-input">
					    </div>
				    </div>
				    <div class="layui-inline">
				      <label class="layui-form-label"><span style="color: red">*</span>性别:</label>
				      <div class="layui-input-inline">
				        <input type="radio" name="sex" value="1" title="男" checked="">
				        <input type="radio" name="sex" value="2" title="女">
				      </div>
				    </div>
				 </div>
				 
				 <div class="layui-form-item">
				    <div class="layui-inline">
				      <label class="layui-form-label"><span style="color: red">*</span>联系电话:</label>
				      <div class="layui-input-inline">
				        <input type="tel" name="telphone" id="telphone" lay-verify="phone" autocomplete="off" class="layui-input">
				      </div>
				    </div>
				    <div class="layui-inline">
				      <label class="layui-form-label">出生日期:</label>
				      <div class="layui-input-inline">
				        <input type="text" name="birth" id="birth" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
				      </div>
				    </div>
				 </div>
				 
				 <div class="layui-form-item">
				    <div class="layui-inline">
				      <label class="layui-form-label"><span style="color: red">*</span>民族:</label>
				      <div class="layui-input-inline">
				        <input type="text" placeholder="请选择" id="nationId" autocomplete="off" class="layui-input" onclick="$.openBaseCodeNation($(this).attr('id'));">
				      </div>
				    </div>
				    <div class="layui-inline">
				      <label class="layui-form-label"><span style="color: red">*</span>学历:</label>
				      <div class="layui-input-inline">
				        <input type="text" placeholder="请选择" id="eduId" autocomplete="off" class="layui-input" onclick="$.openBaseCodeEdu($(this).attr('id'));">
				      </div>
				    </div>
				 </div>
				 
				 <div class="layui-form-item">
				    <div class="layui-inline">
				      <label class="layui-form-label"><span style="color: red">*</span>所属旅行社:</label>
				      <div class="layui-input-inline">
				        <select lay-search="" name="travel.travelId" id="travel.travelId">
				            <option value="0" >请选择旅行社</option>
						    <#list travels as travel>
							   <option value="${travel.travelId}">${travel.travelName}</option>
			                </#list>
			            </select> 
				      </div>
				    </div>
				     <div class="layui-inline">
					    <label class="layui-form-label">导游等级:</label>
					    <div class="layui-input-inline">
					      <select lay-search="" name="guideClass" id="guideClass">
				            <option value="0">请选择等级</option>
				            <option value="1">初级</option>
				            <option value="2">中级</option>
				            <option value="3">高级</option>
			              </select> 
					   </div>
				    </div>
				 </div>
				
			  </div>
				
			  <div style="width:100%;float:left;"> 
				  <div class="layui-form-item">
				    <div class="layui-inline">
				      <label class="layui-form-label">身份证号:</label>
				      <div class="layui-input-inline">
				        <input type="text" name="idCard" id="idCard" autocomplete="off" class="layui-input">
				      </div>
				    </div>
				    <div class="layui-inline">
				      <label class="layui-form-label">语种:</label>
				      <div class="layui-input-inline">
				         <select lay-search="0" name="language" id="language">
				            <option value="">请选择语种</option>
				            <option value="1">汉语</option>
				            <option value="2">英语</option>
				            <option value="3">俄语</option>
			              </select>
				      </div>
				    </div>
				 </div>
				 
				 <div class="layui-form-item">
				    <div class="layui-inline">
					    <label class="layui-form-label">所在城市:</label>
					    <div class="layui-input-inline">
					      <input type="text" placeholder="请选择" name="cityName" id="cityName" autocomplete="off" class="layui-input" onclick="$.openBaseCodeWinForTree($(this).attr('id'));">
					    </div>
				    </div>
				    <div class="layui-inline">
					    <label class="layui-form-label">导游证号:</label>
					    <div class="layui-input-inline">
					      <input type="text" name="guideNumber" id="guideNumber" autocomplete="off" class="layui-input">
					    </div>
				    </div>
				 </div>
				 
				 <div class="layui-form-item">
				   <div class="layui-inline">
				      <label class="layui-form-label">导游卡号:</label>
				      <div class="layui-input-inline">
				        <input type="text" name="cardNumber" id="cardNumber" autocomplete="off" class="layui-input">
				      </div>
				    </div>
				    <div class="layui-inline">
				      <label class="layui-form-label">资格证号:</label>
				      <div class="layui-input-inline">
				        <input type="text" name="qualifyNumber" id="qualifyNumber" autocomplete="off" class="layui-input">
				      </div>
				    </div>
				  </div>
				  
				  <div class="layui-form-item">    
				  	<div class="layui-inline">
					    <label class="layui-form-label">发证机构:</label>
					    <div class="layui-input-inline">
					      <input type="text" name="licensingBody" id="licensingBody" autocomplete="off" class="layui-input">
					    </div>
				    </div>
				    <div class="layui-inline">
				      <label class="layui-form-label">发证日期:</label>
				      <div class="layui-input-inline">
				        <input type="text" name="licensingDate" id="licensingDate" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
				      </div>
				    </div>
				 </div>
			  </div>
			  <div class="btn_nav">
                  <input type="button" class="next right" value="下一步&raquo;" />
              </div>
		  </div>
		  <div class="page">
		  	  <div class="layui-form-item">
			    <label class="layui-form-label">年审情况:</label>
			    <div class="layui-input-block" style="width:85%;">
			      <textarea class="layui-textarea" name="annualSituation" id="annualSituation" style="min-height:80px;"></textarea>
			    </div>
			  </div>
			  
			  <div class="layui-form-item">
			    <label class="layui-form-label">奖惩情况:</label>
			    <div class="layui-input-block" style="width:85%;">
			      <textarea class="layui-textarea" name="valuesInfo" id="valuesInfo" style="min-height:80px;"></textarea>
			    </div>
			  </div>
			  
			  <div class="layui-form-item">
			    <label class="layui-form-label">培训情况:</label>
			    <div class="layui-input-block" style="width:85%;">
			      <textarea class="layui-textarea" name="training" id="training" style="min-height:80px;"></textarea>
			    </div>
			  </div>
			  
			  <div class="layui-form-item">
			    <label class="layui-form-label">出团情况:</label>
			    <div class="layui-input-block" style="width:85%;">
			      <textarea class="layui-textarea" name="groupTour" id="groupTour" style="min-height:80px;"></textarea>
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
				<span style="padding-left:15px;" id="detailsex"></span>
				<span style="padding-left:15px;">
					<img src="/images/icon/icon_guide.png"><span style="padding:5px 0 0 5px;" id="detailguideClass"></span>
				</span>
				<span style="padding-left:15px;">
					<img src="/images/icon/icon_phone.png"><span style="padding:5px 0 0 5px;" id="detailtelphone"></span>
				</span>
				<span style="padding-left:15px;">
					<img src="/images/icon/icon_lvx.png"><span style="padding:5px 0 0 5px;" id="detailtravelId"></span>
				</span>
			</div>
			<div style="margin:15px 10px 5px 20px;background:#f5f5f5;width:97%;height:160px; ">
				 <div style="padding: 10px 0 2px 10px;float:left;width:38%;">出生日期：<span id="detailbirth"></span></div>
				 <div style="padding: 10px 0 2px 10px;float:left;width:58%;">导游证号：<span id="detailguideNumber"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">民族：<span id="detailnationId"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">导游卡号：<span id="detailcardNumber"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">学历：<span id="detaileduId"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">资格证号：<span id="detailqualifyNumber"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">身份证号：<span id="detailidCard"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">发证机构：<span id="detaillicensingBody"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:38%;">语种：<span id="detaillanguage"></span></div>
				 <div style="padding: 8px 0 2px 10px;float:left;width:58%;">发证日期：<span id="detaillicensingDate"></span></div>
			</div>
		</div>
 		<div style="width:25%;float:left;padding-top:30px;">
			<img id="detailimg" style="width:190px;height:190px;padding-left:10px;">
		</div>
 	</div>
 	
 	<div class="layui-form-item" style="font-family:Times New Roman;padding-top:15px;width:95%;">
 		<label class="layui-form-label">年审情况：</label>
	    <div class="layui-input-block" style="width:85%;">
	      <span id="detailannualSituation"></span>
	    </div>
	</div>
 	<div class="hr-line-dashed"></div> 
 	<div class="layui-form-item" style="font-family:Times New Roman;padding-top:15px;width:95%;">
 		<label class="layui-form-label">奖惩情况：</label>
	    <div class="layui-input-block" style="width:85%;">
	      <span id="detailvaluesInfo"></span>
	    </div>
	</div>
 	<div class="hr-line-dashed"></div> 
 	<div class="layui-form-item" style="font-family:Times New Roman;padding-top:15px;width:95%;">
 		<label class="layui-form-label">培训情况：</label>
	    <div class="layui-input-block" style="width:85%;">
	      <span id="detailtraining"></span>
	    </div>
	</div>
 	<div class="hr-line-dashed"></div> 
 	<div class="layui-form-item" style="font-family:Times New Roman;padding-top:15px;width:95%;">
 		<label class="layui-form-label">出团情况：</label>
	    <div class="layui-input-block" style="width:85%;">
	      <span id="detailgroupTour"></span>
	    </div>
	</div>
 </script>
    
    <#include "/base/bottom.ftl">
    <#include "/base/scroll.ftl">
    <#include "/base/js4easyui.ftl">
  <script src="/js/ysusoft/basicdata/guides.js"></script>
</body>
</html>