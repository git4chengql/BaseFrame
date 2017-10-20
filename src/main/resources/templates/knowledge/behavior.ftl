<!DOCTYPE html>
<html>
<head>
    <title>知识库-不文明行为记录</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">

</head>
<body>
              
	 <div class="layui-form-item" style="line-height:35px;padding-top:10px;">          
	<ul class="fa-ul">
		<#list behaviors as behavior>
             <li><i class="fa-li fa fa-circle" style="top:10px;color:#46a3ff;"></i>  ${behavior.behvTitle}</li>
        </#list>
	</ul>
	
    </div>
    
    <#include "/base/bottom.ftl">
    <#include "/base/js4easyui.ftl">
</body>
</html>