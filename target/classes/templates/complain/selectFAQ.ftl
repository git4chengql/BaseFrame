<!DOCTYPE html>
<html>
<head>
    <title>常见问题</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
    <style>
    	.layui-tab {
		    margin: 0;
		}
		.layui-table {
		    margin: 0;
		}
		.layui-tab-content {
		    padding: 0px;
		    
		}
    </style>
</head>
<body>
  
  <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">服务质量</li>
    <li>卫生状况</li>
    <li>欺客宰客</li>
    <li>门票价格</li>
    <li>其他问题</li>
  </ul>
  <div class="layui-tab-content" style="height: 100%;">
    <div class="layui-tab-item layui-show">
		<table class="layui-table" lay-skin="line">
		  <colgroup>
		    <col width="100%">
		  </colgroup>
		  <tbody>
		    <tr>
		      <td id="col_1_0">　</td>
		    </tr>
		    <tr>
		      <td id="col_1_1">　</td>
		    </tr>
		    <tr>
		      <td id="col_1_2">　</td>
		    </tr>
		    <tr>
		      <td id="col_1_3">　</td>
		    </tr>
		    <tr>
		      <td id="col_1_4">　</td>
		    </tr>
		    <tr>
		      <td id="col_1_5">　</td>
		    </tr>
		  </tbody>
		</table>   
		<div id="demo1"></div>
	</div>
    <div class="layui-tab-item">
		<table class="layui-table" lay-skin="line">
		  <colgroup>
		    <col width="100%">
		  </colgroup>
		  <tbody>
		    <tr>
		      <td id="col_2_0">　</td>
		    </tr>
		    <tr>
		      <td id="col_2_1">　</td>
		    </tr>
		    <tr>
		      <td id="col_2_2">　</td>
		    </tr>
		    <tr>
		      <td id="col_2_3">　</td>
		    </tr>
		    <tr>
		      <td id="col_2_4">　</td>
		    </tr>
		    <tr>
		      <td id="col_2_5">　</td>
		    </tr>
		  </tbody>
		</table>   
		<div id="demo2"></div>
	</div>
    <div class="layui-tab-item">
		<table class="layui-table" lay-skin="line">
		  <colgroup>
		    <col width="100%">
		  </colgroup>
		  <tbody>
		    <tr>
		      <td id="col_3_0">　</td>
		    </tr>
		    <tr>
		      <td id="col_3_1">　</td>
		    </tr>
		    <tr>
		      <td id="col_3_2">　</td>
		    </tr>
		    <tr>
		      <td id="col_3_3">　</td>
		    </tr>
		    <tr>
		      <td id="col_3_4">　</td>
		    </tr>
		    <tr>
		      <td id="col_3_5">　</td>
		    </tr>
		  </tbody>
		</table>   
		<div id="demo3"></div>
	</div>
    <div class="layui-tab-item">
		<table class="layui-table" lay-skin="line">
		  <colgroup>
		    <col width="100%">
		  </colgroup>
		  <tbody>
		    <tr>
		      <td id="col_4_0">　</td>
		    </tr>
		    <tr>
		      <td id="col_4_1">　</td>
		    </tr>
		    <tr>
		      <td id="col_4_2">　</td>
		    </tr>
		    <tr>
		      <td id="col_4_3">　</td>
		    </tr>
		    <tr>
		      <td id="col_4_4">　</td>
		    </tr>
		    <tr>
		      <td id="col_4_5">　</td>
		    </tr>
		  </tbody>
		</table>   
		<div id="demo4"></div>
	</div>
    <div class="layui-tab-item">
		<table class="layui-table" lay-skin="line">
		  <colgroup>
		    <col width="100%">
		  </colgroup>
		  <tbody>
		    <tr>
		      <td id="col_5_0">　</td>
		    </tr>
		    <tr>
		      <td id="col_5_1">　</td>
		    </tr>
		    <tr>
		      <td id="col_5_2">　</td>
		    </tr>
		    <tr>
		      <td id="col_5_3">　</td>
		    </tr>
		    <tr>
		      <td id="col_5_4">　</td>
		    </tr>
		    <tr>
		      <td id="col_5_5">　</td>
		    </tr>
		  </tbody>
		</table>   
		<div id="demo5"></div>
	</div>
  </div>
</div> 
  
  
  <#include "/base/bottom.ftl">
  <#include "/base/js4easyui.ftl">
    <script src="/js/ysusoft/complain/selectFAQ.js"></script>
</body>
</html>