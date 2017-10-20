$(function(){
	
layui.use(['laypage','form','layer','element'], function(){
	   var form = layui.form()
	   ,layer = layui.layer
	   ,element = layui.element()
	   ,$ = layui.jquery
	   ,laypage = layui.laypage;
	   
		//一些事件监听
		element.on('tab(docDemoTabBrief)', function(data){
			FAQ.firstBuild(""+(data.index+1));
//			alert(data.index);
		});	  
	
		
	var pageSize = 6;//每页显示的行数
	var count = 45;//每行显示的最多文字数
	var FAQ = {
	   //页面入口函数
	   init:function(){
		   FAQ.bindUI();
	   },
			   
	   //绑定事件
	   bindUI:function(){
		   FAQ.firstBuild("1");
	   },//bindUI结束
	   
	   firstBuild:function(type){
		   $.ajax({
		        type: 'post',
		        url: '../complain/getFAQ.do',
		        data : {"page":1,"pageSize":pageSize,"type":type},
		        success: function (data) {
		        	FAQ.buildTable(type,data.rows);
		        	FAQ.layPage(type,Math.ceil(data.total/pageSize));
		        }
		    });
	   },
	   
	   layPage:function(type,pages){
		   laypage({
			    cont: 'demo'+type
			    ,pages: pages//总页数
			    ,groups: 5 //连续显示分页数
			    ,jump: function(obj, first){
			      if(!first){
			    	  FAQ.buildAjax(type,obj.curr);
			      }
			    }
			  });
	   },
	   
	   buildTable:function(type,rows){
		   for(i=0;i<rows.length;i++){
			  var title = rows[i].title;
			  if(title.length>count){
				 title = title.substring(0,count) +"...?"
			  }
			  $("#col_"+type+"_"+i).html("<a style='color:black;text-decoration:underline;' onclick='selectFAQ("+rows[i].faqId+");' href='javascript:void(0);'>"+title+"</a>");
		   }
	   },
	   
	   buildAjax:function(type,page){
		   $.ajax({
		        type: 'post',
		        url: '../complain/getFAQ.do',
		        data : {"page":page,"pageSize":pageSize,"type":type},
		        success: function (data) {
		        	FAQ.clearTable(type);
		        	FAQ.buildTable(type,data.rows);
		        }
		    });
	   },
	   
	   clearTable:function(type){
		   $("#col_"+type+"_"+0).html("　");
		   $("#col_"+type+"_"+1).html("　");
		   $("#col_"+type+"_"+2).html("　");
		   $("#col_"+type+"_"+3).html("　");
		   $("#col_"+type+"_"+4).html("　");
		   $("#col_"+type+"_"+5).html("　");
	   }
	   
	   
	}; 
		   
    //页面入口
	FAQ.init();
	}); 
});
function selectFAQ(faqId){
//	alert(faqId);
	parent.selectFAQ(faqId);
}