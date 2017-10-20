<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>用户新增和修改</title>
	<script src="/js/jquery.min.js"></script>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
	<#include "/base/js4easyui.ftl">
</head>
<body>
<#if user??>
<div style="margin-top:20px;"><label class="layui-form-label" >登录名称</label><div class="layui-input-inline"><input class="layui-input" disabled="true" type="text" required lay-verify="required" id="newloginName" value="${user.loginName}"></div></div>
<input id="UserId" type="hidden" value="${user.userId}" name="UserId" />
<#else>
<div style="margin-top:20px;"><label class="layui-form-label" >登录名称</label><div class="layui-input-inline"><input class="layui-input" type="text" required lay-verify="required" id="newloginName" value=""></div></div>
<div style="margin-top:20px;"><label class="layui-form-label" >登录密码</label><div class="layui-input-inline"><input class="layui-input" type="password" required lay-verify="required" id="newPwd" ></div></div>
<div style="margin-top:20px;"><label class="layui-form-label" >确认密码</label><div class="layui-input-inline"><input class="layui-input" type="password" required lay-verify="required" id="conPwd" ></div></div>
<input id="UserId" type="hidden" value="" name="UserId" />
</#if>
<div style="margin-top:20px;"><label class="layui-form-label" >角色选择</label><div class="layui-input-inline"> 
		
		<#if txtGroupsSelect??>
		<input id="txtGroupsSelect" type="hidden" value="${txtGroupsSelect}"
			name="txtGroupsSelect" />
		<#else>
		<input id="txtGroupsSelect" type="hidden" value=""
			name="txtGroupsSelect" />
		</#if>
		<table class="tweenBoxTable" name="groups_tweenbox"
			id="groups_tweenbox" cellspacing="0" cellpadding="0">
			<tbody>
				<tr> <td>已角色</td> <td></td> <td>可角色</td> </tr>
				<tr><td><select id="selectGroups" multiple="multiple"
						class="input-large" name="selectGroups"
						style="height: 150px; width: 150px">
						<#if userRole??>
						<#list userRole  as  auserRole>
						<option value="${auserRole.roleId}">${auserRole.roleName}</option>
						</#list>
						</#if>
					</select></td>
					<td align="center">
						<div style="margin-left: 5px; margin-right: 5px">
							<button onclick="selectedAll()" class="btn btn-primary"
								type="button" style="width: 50px;" title="全选">&lt;&lt;</button>
						</div>
						<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;">
							<button onclick="selected()" class="btn btn-primary"
								type="button" style="width: 50px;" title="选择">&lt;</button>
						</div>
						<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;">
							<button onclick="unselected()" class="btn btn-primary"
								type="button" style="width: 50px;" title="取消">&gt;</button>
						</div>
						<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px">
							<button onclick="unselectedAll()" class="btn btn-primary"
								type="button" style="width: 50px;" title="全取消">&gt;&gt;</button>
						</div>
					</td>
					<td><select id="groupsForSelect"
						multiple="multiple" class="input-large"
						style="height: 150px; width: 150px">
						<#if role??>
						<#list role  as  arole>
						 <option value="${arole.roleId}">${arole.roleName}</option>
						</#list>
						</#if>
					</select></td>
				</tr>
			</tbody>
		</table>
</div></div>
<#if user??>
<div style="margin-top:20px;"><label class="layui-form-label" >用户名称</label><div class="layui-input-inline"><input class="layui-input" type="text" required lay-verify="required" id="newUserName" value="${user.userName}"></div></div>
<#else>
<div style="margin-top:20px;"><label class="layui-form-label" >用户名称</label><div class="layui-input-inline"><input class="layui-input" type="text" required lay-verify="required" id="newUserName" ></div></div>
</#if>
	<#include "/base/bottom.ftl">
	<script type="text/javascript"  src="/js/jquery.md5.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#groupsForSelect").dblclick(function() {
				selected();
			});
			$("#selectGroups").dblclick(function() {
				unselected();
			});
		});
		function selected() {
			var selOpt = $("#groupsForSelect option:selected");
		
			selOpt.remove();
			var selObj = $("#selectGroups");
			selObj.append(selOpt);
		
			var selOpt = $("#selectGroups")[0];
			ids = "";
			for (var i = 0; i < selOpt.length; i++) {
				ids += (selOpt[i].value  + ",");
			}
		
			if (ids != "") {
				ids = ids.substring(0, ids.length - 1);
			}
			$('#txtGroupsSelect').val(ids);
		}
		
		function selectedAll() {
			var selOpt = $("#groupsForSelect option");
		
			selOpt.remove();
			var selObj = $("#selectGroups");
			selObj.append(selOpt);
		
			var selOpt = $("#selectGroups")[0];
			ids = "";
			for (var i = 0; i < selOpt.length; i++) {
				ids += (selOpt[i].value  + ",");
			}
		
			if (ids != "") {
				ids = ids.substring(0, ids.length - 1);
			}
			$('#txtGroupsSelect').val(ids);
		}
		
		function unselected() {
			var selOpt = $("#selectGroups option:selected");
			selOpt.remove();
			var selObj = $("#groupsForSelect");
			selObj.append(selOpt);
		
			var selOpt = $("#selectGroups")[0];
			ids = "";
			for (var i = 0; i < selOpt.length; i++) {
				ids += (selOpt[i].value + ",");
			}
			
			if (ids != "") {
				ids = ids.substring(0, ids.length - 1);
			}
			$('#txtGroupsSelect').val(ids);
		}
		
		function unselectedAll() {
			var selOpt = $("#selectGroups option");
			selOpt.remove();
			var selObj = $("#groupsForSelect");
			selObj.append(selOpt);
			$('#txtGroupsSelect').val("");
		}
		function getParam(){
			if($("#UserId").val()==""){//新增
				if($("#newloginName").val()==""||$("#newPwd").val()==""){
					layer.msg('请填写必填项！');
					return false;
				}
			}
			if($("#newPwd").val()!=$("#conPwd").val()){
				layer.msg('确认密码与登录密码不一致！');
				return false;
			}
			var param = "txtGroupsSelect="+$('#txtGroupsSelect').val()+"&UserName="+$('#newUserName').val()+"&UserId="+$('#UserId').val()+"&LoginName="+$('#newloginName').val()+"&password="+($('#newPwd').val()==undefined?'':$.md5($('#newPwd').val()));
			return param ;			
		}
	</script>
</body>
</html>