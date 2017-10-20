$(function(){
	
layui.use(['form','layer'], function(){
	   var form = layui.form()
	   ,layer = layui.layer,
		   
		   
	Complain = {
	   //页面入口函数
	   init:function(){
		   Complain.bindUI();
	   },
			   
	   //绑定事件
	   bindUI:function(){
		   form.on('submit(demo1)', function(data){
			   var data = decodeURIComponent($('#myform').serialize(),true);
			   $.ajax({
			        type: 'post',
			        url: 'verifycode.do',
			        data : data,
			        success: function (data) {
			        	if(data.success){
			        		layer.msg(data.message);
			        		Complain.inputClear();
			        	}else{
			        		layer.msg(data.message);
			        		var radom = Math.floor(Math.random() * Math.pow(10, 8));
		                    $("#randCodeImage").attr("src", "generateCode.do?uuuid="+radom);
		                    $("#code").val("").focus(); // 清空并获得焦点
			        	}
			        }
			    });
			   return false;
		   });
		   
	   },//bindUI结束
	   
	   inputClear:function(){
		   $("input").val("");
		   $("textarea").val("");
		   var radom = Math.floor(Math.random() * Math.pow(10, 8));
           $("#randCodeImage").attr("src", "generateCode.do?uuuid="+radom);
           $("#code").val("").focus(); // 清空并获得焦点
	   }
	};
	   
    //页面入口
	Complain.init();
	}); 
});
/**
 * 生成常见问题页面，并反馈相关信息。
 */
function openFAQ() {
	
	layer.open({
		type : 2,
		title : '常见问题',
		area : [ '700px', '450px' ], //宽高
		content : '/complain/selectFAQ.do',
		btn : [ '关闭' ]
	});
}
function selectFAQ(faqId){
	 $.ajax({
	        type: 'post',
	        url: '../complain/getFAQContent.do',
	        data : {"faqId":faqId},
	        success: function (data) {
	        	if(data.success){
	        		$("#content").html(data.message);
	        		layer.closeAll('iframe'); 
	        	}else{
	        		layer.msg("获取常见问题失败，请刷新后重试！");
	        	}
	        }
	    });
}
/**
 * 刷新验证码
 */
function changeCode(){
     var radom = Math.floor(Math.random() * Math.pow(10, 8));
     //uuuid是随机参数，后端不会做处理，作用是避免浏览器读取缓存的链接
     $("#randCodeImage").attr("src", "generateCode.do?uuuid="+radom);
}