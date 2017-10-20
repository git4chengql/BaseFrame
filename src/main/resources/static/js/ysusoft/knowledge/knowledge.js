layui.use(['form','layer'], function(){
		   var form = layui.form(),
		   layer = layui.layer;
		   
	Knowledge = {
	   //页面入口函数
	   init:function(){
		   Knowledge.bindUI();
	   },
			   
	   //绑定事件
	   bindUI:function(){
		   
	   },
	   
	   moreContent:function(){
		 var obj="";
		 if($('#title').html()=="法律法规"){
			 obj={href: "/knowledge/law.html", icon: "fa-meetup", title: "法律法规"};
		 }else if($('#title').html()=="常见问题"){
			 obj={href: "/knowledge/question.html", icon: "fa-meetup", title: "常见问题"};
		 }else if($('#title').html()=="行业标准"){
			 obj={href: "/knowledge/standard.html", icon: "fa-meetup", title: "行业标准"};
		 }else if($('#title').html()=="不文明行为记录"){
			 obj={href: "/knowledge/behavior.html", icon: "fa-meetup", title: "不文明行为记录"};
		 }else if($('#title').html()=="经典案例"){
			 obj={href: "/knowledge/case.html", icon: "fa-meetup", title: "经典案例"};
		 }else if($('#title').html()=="旅游保险知识"){
			 obj={href: "/knowledge/insure.html", icon: "fa-meetup", title: "旅游保险知识"};
		 }else if($('#title').html()=="解决措施"){
			 obj={href: "/knowledge/solution.html", icon: "fa-meetup", title: "解决措施"};
		 }else if($('#title').html()=="行业分析报告"){
			 obj={href: "/knowledge/report.html", icon: "fa-meetup", title: "行业分析报告"};
		 }
		 parent.tab.tabAdd(obj);
	   },
	   
	   //改变右侧iframe内容
	   change:function(str){
		   $('#title').html(str);
		   if(str=="法律法规"){
			   $("#content").attr("src","/knowledge/law.html");
		   }else if(str=="常见问题"){
			   $("#content").attr("src","/knowledge/question.html");
		   }else if(str=="行业标准"){
			   $("#content").attr("src","/knowledge/standard.html");
		   }else if(str=="不文明行为记录"){
			   $("#content").attr("src","/knowledge/behavior.html");
		   }else if(str=="经典案例"){
			   $("#content").attr("src","/knowledge/case.html");
		   }else if(str=="旅游保险知识"){
			   $("#content").attr("src","/knowledge/insure.html");
		   }else if(str=="解决措施"){
			   $("#content").attr("src","/knowledge/solution.html");
		   }else if(str=="行业分析报告"){
			   $("#content").attr("src","/knowledge/report.html");
		   }
	   },
	   
	   
	}; 
	
	//页面入口
	Knowledge.init();
});