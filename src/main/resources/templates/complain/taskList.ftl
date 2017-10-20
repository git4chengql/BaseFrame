<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>任务管理</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
	<link href="/css/ysusoft/complain/taskList.css" type="text/css" rel="stylesheet"/>
	<link href="/css/ysusoft/complain/zoomify.css" type="text/css" rel="stylesheet"/>
  	<script src="/js/plugins/layui/lay/dest/layui.all.js"></script>
  	<script src="/js/jquery.min.js"></script>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="tabDemo">
  <ul class="layui-tab-title">
    <li class="layui-this">待处理</li>
    <li>已办结</li>
  </ul>
  <div class="layui-tab-content" style="height: 100px;">
    <div class="layui-tab-item layui-show">
			<div class="layui-field-box">
				<div class="layui-input-inline">
				  <input id="backInfo" class="layui-input" placeholder="请输入案件回执编号">
				</div>
				<div class="layui-input-inline">
				  <button class="layui-btn layui-btn-small" id="queryBtn"><i class="layui-icon">&#xe654;</i> 查询</button>
				</div>
				<div class="layui-input-inline">
				  <button class="layui-btn layui-btn-small" id="addBtn"><i class="layui-icon">&#xe654;</i> 办理</button>
				</div>
				<div class="layui-input-inline">
				  <button class="layui-btn layui-btn-small layui-btn-danger layui-btn-disabled" id="delBtn"><i class="layui-icon">&#xe640;</i> 结案</button>
				</div>
		   </div>
	   	<!-- 未完成案件列表 -->
	  	<table id="dg"></table>
	  	<!-- 未完成案件内容 start -->
	  	<div class="complainContent">
	  		<!-- 案卷详情 -->
		       <div class="rowCont" > 
		         <div class="col-md-12" style="border-bottom: 1px dashed #e7eaec;"><strong>详细信息</strong></div>
		       </div>
			   <div class="rowCont">
		         <div class="col-md-4">回执编号: <span id="taskid"></span></div>
		         <div class="col-md-4">投诉时间: <span id="createDate"></span></div>
		         <div class="col-md-4">投诉类型: <span id="complainType"></span></div>
		       </div>
				<div class="rowCont">
		         <div class="col-md-4">用户姓名: <span id="userName"></span></div>
		         <div class="col-md-4">手机号码: <span id="telphone"></span></div>
		         <div class="col-md-4">  </div>
		       </div>
			   <div class="rowCont" > 
		         <div class="col-md-12">投诉主题: <span id="title"></span></div>
		       </div>
			   <div class="rowCont" > 
		         <div class="col-md-12" style="border-bottom: 1px dashed #e7eaec;">投诉内容: <span id="content"></span></div>
		       </div>
		       
		       <div class="rowCont" > 
		         <div class="col-md-12" style="border-bottom: 1px dashed #e7eaec;"><strong>多媒体信息</strong></div>
		       </div>
		       	<div class="rowCont" id="media" style="border-bottom: 1px dashed #e7eaec; ">
				</div>
			   <div class="rowCont">
		         <div class="col-md-12" style="border-bottom: 1px dashed #e7eaec;"><strong>处理过程</strong></div>
			   </div>
			   <div class="rowCont">
			   	<div id="process-detail" style="overflow: auto;height:50%;"></div>
			   </div>
	  	</div> 
	  	<!-- 未完成案件内容 end -->
    </div>
    <div class="layui-tab-item">
    	<div class="layui-field-box">
				<div class="layui-input-inline">
				  <input id="backInfoEnd" class="layui-input" placeholder="请输入案件回执编号">
				</div>
				<div class="layui-input-inline">
				  <button class="layui-btn layui-btn-small" id="queryBtnEnd"><i class="layui-icon">&#xe654;</i> 查询</button>
				</div>
		   </div>
		 <!-- 已完成案件列表 -->
	  	<table id="dgEnd"></table>
    	<!-- 已完成案件内容 start -->
	  	<div class="complainContent">
	  		<!-- 案卷详情 -->
		       <div class="rowCont" > 
		         <div class="col-md-12" style="border-bottom: 1px dashed #e7eaec;"><strong>详细信息</strong></div>
		       </div>
			   <div class="rowCont">
		         <div class="col-md-4">回执编号: <span id="taskidEnd"></span></div>
		         <div class="col-md-4">投诉时间: <span id="createDateEnd"></span></div>
		         <div class="col-md-4">投诉类型: <span id="complainTypeEnd"></span></div>
		       </div>
				<div class="rowCont">
		         <div class="col-md-4">用户姓名: <span id="userNameEnd"></span></div>
		         <div class="col-md-4">手机号码: <span id="telphoneEnd"></span></div>
		         <div class="col-md-4">  </div>
		       </div>
			   <div class="rowCont" > 
		         <div class="col-md-12">投诉主题: <span id="titleEnd"></span></div>
		       </div>
			   <div class="rowCont" > 
		         <div class="col-md-12" style="border-bottom: 1px dashed #e7eaec;">投诉内容: <span id="contentEnd"></span></div>
		       </div>
		       
		       <div class="rowCont" > 
		         <div class="col-md-12" style="border-bottom: 1px dashed #e7eaec;"><strong>多媒体信息</strong></div>
		       </div>
		       	<div class="rowCont" id="mediaEnd" style="border-bottom: 1px dashed #e7eaec;">
				</div>
			   <div class="rowCont">
		         <div class="col-md-12" style="border-bottom: 1px dashed #e7eaec;"><strong>处理过程</strong></div>
			   </div>
			   <div class="rowCont">
			   	<div id="process-detailEnd" style="overflow: auto;height:50%;"></div>
			   </div>
	  	</div> 
	  	<!-- 已完成案件内容 end -->
    
    </div>
  </div>
</div> 

  <#include "/base/bottom.ftl">
  <#include "/base/js4easyui.ftl">
  <script src="/js/ysusoft/complain/zoomify.js"></script>
  <script src="/js/ysusoft/complain/taskList.js"></script>
      
</body>
</html>