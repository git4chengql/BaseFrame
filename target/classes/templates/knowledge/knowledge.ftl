<!DOCTYPE html>
<html>
<head>
    <title>知识库主页面</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
<style>
   .thumbnail-block:hover{
		border: 1px solid #ff00ff;
		cursor:pointer;
		box-shadow: 0 1px 3px rgba(0,0,0,0.5);
	} 
</style>
</head>
<body>
              
	<div class="layui-form-item">
		 <div class="layui-inline" style="width:50%;margin-right: 0;float:left;">
			<div class="container-fluid" style="margin: 50px 0 0 30px;">
				<div class="row">
				    <div class="col-sm-6 col-md-6">
				        <div class="thumbnail-block" onclick="Knowledge.change('法律法规');">
				        	<img src="/images/icon/法律法规.png">
				        	<div class="caption-block"> 
				        		<div class="words">法律法规</div>
				        		<div class="digit">200</div>
							</div>
				        </div>
				    </div>
				    <div class="col-sm-6 col-md-6">
				        <div class="thumbnail-block" onclick="Knowledge.change('行业标准');">
				        	<img src="/images/icon/行业标准.png">
				        	<div class="caption-block"> 
				        		<div class="words">行业标准</div>
				        		<div class="digit">216</div>
							</div>
				        </div>
				    </div>
				 </div>
				 <div class="row">
				    <div class="col-sm-6 col-md-6">
				        <div class="thumbnail-block" onclick="Knowledge.change('常见问题');">
				        	<img src="/images/icon/常见问题.png">
				        	<div class="caption-block"> 
				        		<div class="words">常见问题</div>
				        		<div class="digit">171</div>
							</div>
				        </div>
				    </div>
				    <div class="col-sm-6 col-md-6">
				        <div class="thumbnail-block"  onclick="Knowledge.change('不文明行为记录');">
				        	<img src="/images/icon/不文明行为记录.png">
				        	<div class="caption-block"> 
				        		<div class="words" style="width:112px;overflow-y:auto;">不文明行为记录</div>
				        		<div class="digit">38</div>
							</div>
				        </div>
				    </div>
				 </div>
				 <div class="row">
				    <div class="col-sm-6 col-md-6">
				        <div class="thumbnail-block" onclick="Knowledge.change('经典案例');">
				        	<img src="/images/icon/经典案例.png">
				        	<div class="caption-block"> 
				        		<div class="words">经典案例</div>
				        		<div class="digit">56</div>
							</div>
				        </div>
				    </div>
				    <div class="col-sm-6 col-md-6">
				        <div class="thumbnail-block" onclick="Knowledge.change('旅游保险知识');">
				        	<img src="/images/icon/保险知识.png">
				        	<div class="caption-block"> 
				        		<div class="words">旅游保险知识</div>
				        		<div class="digit">145</div>
							</div>
				        </div>
				    </div>
				 </div>
				 <div class="row">
				    <div class="col-sm-6 col-md-6">
				        <div class="thumbnail-block" onclick="Knowledge.change('解决措施');">
				        	<img src="/images/icon/解决措施.png">
				        	<div class="caption-block"> 
				        		<div class="words">解决措施</div>
				        		<div class="digit">78</div>
							</div>
				        </div>
				    </div>
				    <div class="col-sm-6 col-md-6">
				        <div class="thumbnail-block" onclick="Knowledge.change('行业分析报告');">
				        	<img src="/images/icon/行业分析.png">
				        	<div class="caption-block"> 
				        		<div class="words">行业分析报告</div>
				        		<div class="digit">73</div>
							</div>
				        </div>
				    </div>
				 </div>
			</div>
		 </div>
		 <div class="layui-inline" style="width:48%;margin-right: 0;margin-left:5px;float:left;">
			<div class="layui-panel">
			    <h2 class="layui-colla-title" style="padding-left:15px;"><span class="layui-icon">&#xe632;</span> <span id="title">常见问题</span><a style="float:right;color:blue;" href="javascript:void(0)" onclick="Knowledge.moreContent();">更多>></a></h2>
			    <div class="layui-panel-content">
			    	<iframe src="/knowledge/question.do" id="content" style="width:100%;height:100%;min-height:390px;" frameborder="0"></iframe>
				</div>
			</div>
		 </div>
	</div>
    
    <#include "/base/bottom.ftl">
    <#include "/base/js4easyui.ftl">
    <script src="/js/ysusoft/knowledge/knowledge.js"></script>
</body>
</html>