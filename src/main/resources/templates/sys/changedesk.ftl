<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>角色管理</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
</head>
<script type="text/javascript" src="/js/echarts.min.js"></script>
<body style="background:RGB(224,242,255);">
	<div style="width:100%;height:100%">
		<div style="width:100%;height:30%;margin-top:30px;">
			 <div style="width:18%;margin-left:50px; float:left;position:relative;">
			 <img src="/images/lvxingshe.png" style="width:80%;heigth:70%"/>
			 <span style="position: absolute;left:35%;bottom:10%;color:#f4c046;font-size:16px;" id="travel"></span>
			</div>
			<div style="width:18%;float:left;position:relative;">
			 <img src="/images/daoyou.png" style="width:80%;heigth:70%"/>
			 <span style="position: absolute;left:38%;bottom:10%;color:#fa8564;font-size:16px;" id="guides"></span>
			</div>
			<div style="width:18%;float:left;position:relative;">
			 <img src="/images/xjjd.png" style="width:80%;heigth:70%"/>
			  <span style="position: absolute;left:35%;bottom:10%;color:#8770c0;font-size:16px;" id="hotel"></span>
			</div>
			<div style="width:18%;float:left;position:relative;">
			 <img src="/images/jingqu.png" style="width:80%;heigth:70%;"/>
			   <span style="position: absolute;left:35%;bottom:10%;color:#2bba8f;font-size:16px;" id="spot"></span>
			</div>
			<div style="width:18%;float:left;position:relative;">
			 <img src="/images/gouwudian.png" style="width:80%;heigth:70%"/>
			  <span style="position: absolute;left:38%;bottom:10%;color:#099eca;font-size:16px;" id="shopping"></span>
			</div>
		</div>
		<div style="width:100%;height:10%">
		</div>
		<div style="width:100%;height:60%">
		    <div  id="complain" style="width:50%;height:350px;float:left">
	        </div>
	       <div  id="complaintype" style="width:40%;height:250px;float:left">
	       </div>
		</div>
	
	</div>

  <#include "/base/bottom.ftl">
  <#include "/base/js4easyui.ftl">
  <script src="/js/ysusoft/sys/changedesk.js"></script>
</body>
</html>