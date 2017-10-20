$(function(){

	layui.use(['form','layer','laypage','laydate','upload'], function(){
		   var form = layui.form(),
		   layer = layui.layer,
		   laydate = layui.laydate;
		   layui.upload({
			    url: '/uploadweb/imgupload.json'
			    ,elem: '#test' //指定原始元素，默认直接查找class="layui-upload-file"
			    ,method: 'post' //上传接口的http类型
			    ,success: function(data){
			       if(data.success ==true){
			    	   $("#humanImg").attr('imgName',data.message);
			    	   $('#humanImg').attr('src',window._downUrl+$('#humanImg').attr('imgName'));
			       }
			    }
		   });
		   
	Guides = {
	   guideId:0,
	   index:0,
	   //页面入口函数
	   init:function(){
		   Guides.initGrid();
		   Guides.bindUI();
	   },
			   
	   //绑定事件
	   bindUI:function(){
		   
		   //查询
		   $('#queryBtn').click(function(){
			   Guides.initGrid();
		   });
		   
		   //新增
		   $('#addBtn').click(function(){
			   Guides.addGuides();
		   });
		   
		   //编辑
		   $('#editBtn').click(function(){
			   Guides.updateGuides();
		   });
		   
		   //删除
		   $('#delBtn').click(function(){
			   Guides.delGuides();
		   });
		   
	   },
	   
	   //新增保存方法
	   addGuides:function(){
			//打开表单弹框
			var index = layer.open({
			   type: 1,
			   title:'新增导游',
			   area: ['900px', '600px'], //宽高
			   content: $('#popContainer'),
			   cancel: function(){
				   Guides.clearInput();
			   }
		    });
			
			//表单提交
			Guides.formSubmit(index);
	   },
	   
	   formSubmit:function(index){
		   form.on('submit(demo1)', function(data){
			   var nationId = 0;
			   var eduId = 0;
			   var travelId = 0;
			   if($('#nationId').attr('realValue')!=undefined){
				   nationId = $('#nationId').attr('realValue');
			   }
			   if($('#eduId').attr('realValue')!=undefined){
				   eduId = $('#eduId').attr('realValue');
			   }
			   var data = decodeURIComponent($('#myform').serialize(),true);
			   data += "&imgName="+$('#humanImg').attr('imgName')+"&locationCity="+$('#cityName').attr('realValue');
			   data +="&nation.nationId="+nationId+"&edu.eduId="+eduId;
			   $.ajax({
			        type: 'post',
			        url: 'addorupdateguide.do',
			        data : data,
			        success: function (data) {
			        	Guides.guideId =0;
			        	if(data.success){
			        		layer.msg(data.message);
			        		Guides.initGrid();
			        		layer.close(index);
			        		$("#wizard").scrollable().begin();
			        		Guides.clearInput();
			        	}else
			        		layer.msg(data.message);
			        }
			    });
		        return false;
		   });
		   $('#closeBtn').click(function(){
				layer.close(index);
				$("#wizard").scrollable().begin();
		   });
	   },
	   
	   //编辑方法
	   updateGuides:function(){
		   if(Guides.guideId!=0){
			   $.ajax({
				   url: "getguidebyId.do",
				   data:{
					   "guideId":Guides.guideId
				   },
				   success: function(data){
					   $('#guideId').val(data[0].guideId);
						$('#guideName').val(data[0].guideName);
						$("input[name='sex'][value='"+data[0].sex+"']").attr("checked",true);//
						$('#telphone').val(data[0].telphone);
						$('#birth').val(data[0].birth);
						
						$('#nationId').val(data[0].nation.nationName);
						$('#nationId').attr('realValue',data[0].nation.nationId);
						
						$('#eduId').val(data[0].edu.eduName);
						$('#eduId').attr('realValue',data[0].edu.eduId);
						$('#guideNumber').val(data[0].guideNumber);
						$('#cardNumber').val(data[0].cardNumber);
						$('#idCard').val(data[0].idCard);
						
						$('#travel.travelId').val(data[0].travel.travelId);//
						form.render('select');
						$('#cityName').val(data[0].cityName);
						$('#cityName').attr('realValue',data[0].locationCity);
						
						$('#language').val(data[0].language);//
						$('#guideClass').val(data[0].guideClass);//
						form.render('select');
						$('#qualifyNumber').val(data[0].qualifyNumber);
						$('#licensingBody').val(data[0].licensingBody);
						$('#licensingDate').val(data[0].licensingDate);
						$('#annualSituation').val(data[0].annualSituation);
						$('#valuesInfo').val(data[0].valuesInfo);
						$('#training').val(data[0].training);
						$('#groupTour').val(data[0].groupTour);
						
						$("#humanImg").attr('imgName',data[0].imgName);
						if(data[0].imgName!="" &&data[0].imgName!="undefined" && typeof(data[0].imgName) != "undefined"){
							$('#humanImg').attr('src',window._downUrl+$('#humanImg').attr('imgName'));
						}
						
					   //打开表单弹框，并赋值
					   Guides.index = layer.open({
						   type: 1,
						   title:'编辑导游',
						   area: ['900px', '600px'], //宽高
						   content: $('#popContainer'),
						   cancel: function(){
							   Guides.clearInput();
						   },
						   success: function(layero, index){
						   }
					  });
					   
					  //表单提交
					  Guides.formSubmit(Guides.index);
				   }
			   });
		   }else{
				layer.msg('请选择一条记录');
		   }
	   },
	   
	   
	   //删除方法
	   delGuides:function(){
		   if(Guides.guideId!=0){
			   var index = layer.confirm('确定删除？', {
	               title:'提示',
				   btn: ['确定','取消']
			   }, function(){
				   $.ajax({
				        type: 'post',
				        url: 'delguide.do?guideId='+Guides.guideId,
				        success: function (data) {
				        	Guides.guideId =0;
				        	if(data.success){
				        		Guides.initGrid(1);
				        		layer.close(index);
				        		layer.msg(data.message);
				        	}else{
				        		layer.msg(data.message);
				        	}
				        }
				    });
		       });
		   }else{
				layer.msg('请选择一条记录');
		   }
	   },
	   
	   /**
	    * 双击块事件
	    * @param that
	    */
	   dblselectC:function(that){
		   var index = layer.open({
			   type: 1,
			   area: ['900px', '600px'],
			   shadeClose: true, //开启遮罩关闭
			   title:"导游详情",
			   content: $('#detail').html(),
			   success:function(layero, index){
				   var id = $(that).find(".guideId").text();
				   $.ajax({
				        type: 'post',
				        url: 'getguidebyId.do?guideId='+id,
				        success: function (data) {
				        	if(data.length>0){
				        		$('#detailname').html(data[0].guideName);
				        		$('#detailbirth').html(data[0].birth);
				        		$('#detailtelphone').html(data[0].telphone);
				        		var guideClass="";
				        		if(data[0].guideClass==1){
				        			guideClass="初级导游";
				        		}else if(data[0].guideClass==1){
				        			guideClass="中级导游";
				        		}else if(data[0].guideClass==2){
				        			guideClass="高级导游";
				        		}
				        		$('#detailguideClass').html(guideClass);
				        		if(data[0].sex==1){//男
				        			$('#detailsex').append("<img src='/images/icon/icon_man.png'>");
				        		}else if(data[0].sex==2){
				        			$('#detailsex').append("<img src='/images/icon/icon_woman.png'>");
				        		}
				        		$('#detailtravelId').html(data[0].travel.travelName);
				        		$('#detailguideNumber').html(data[0].guideNumber);
				        		$('#detailnationId').html(data[0].nation.nationName);
				        		$('#detailcardNumber').html(data[0].cardNumber);
				        		$('#detaileduId').html(data[0].edu.eduName);
				        		$('#detailqualifyNumber').html(data[0].qualifyNumber);
				        		$('#detailidCard').html(data[0].idCard);
				        		$('#detaillicensingBody').html(data[0].licensingBody);
				        		var language = "";
				        		if(data[0].language==1){
				        			language="汉语";
				        		}else if(data[0].language==2){
				        			language="英语";
				        		}else if(data[0].language==3){
				        			language="俄语";
				        		}
				        		$('#detaillanguage').html(language);
				        		$('#detaillicensingDate').html(data[0].licensingDate);
				        		$('#detailannualSituation').html(data[0].annualSituation);
				        		$('#detailvaluesInfo').html(data[0].valuesInfo);
				        		$('#detailtraining').html(data[0].training);
				        		$('#detailgroupTour').html(data[0].groupTour);
				        		if(data[0].imgName=="undefined" || data[0].imgName==null){
								   $('#detailimg').attr('src',"/images/placeholder.png");
							    }else{
								   $('#detailimg').attr('src',window._downUrl+data[0].imgName);
							    }
				        	}
				        }
				  });
			   }
		   });
	   },
	   
	   /**
	    * 点击块事件
	    * @param that
	    */
	   selectC:function(that){
		   //全局变量，获取最后一次选中的id
		   Guides.guideId = $(that).find(".guideId").text();
		   //点击效果互斥
		   if($(that).is(".wrap_click")){
			   $(that).removeClass('wrap_click');
		   }else{
			   $(that).addClass('wrap_click');
		   }
		   $('.wrap').not($(that)).removeClass('wrap_click');
	   },
	   
	   //给人员块赋值
	   setGuide:function(guides){
		   var results ='';
		   var sex='';
		   var src = '';
		   for(x in guides){
			   if(guides[x].sex==1) sex="/images/icon/icon_man.png";
			   else if(guides[x].sex==2) sex="/images/icon/icon_woman.png";
			   if(guides[x].imgName==null || guides[x].imgName=="undefined") {
				   src = '/images/placeholder.png';
			   }  else src = window._downUrl+guides[x].imgName;
			   results = results +  '  <div class="col-sm-2 col-md-2" ><div class="thumbnail wrap" onclick="Guides.selectC(this);" ondblclick="Guides.dblselectC(this);">'+
				   '<div style="height:60%;">'+
				   '  <img src="'+src+'" class="personimg">'+
				   '</div>'+
				   '<div style="height:40%;" class="caption">'+
				   '     <div style="display:none;">id:<span class="guideId">'+guides[x].guideId+'</span></div>'+
				   '     <div><font size="4" style="font-weight:bold;font_family:Times New Roman;">'+guides[x].guideName+'</font>&nbsp;&nbsp;<img src='+sex+' style="width:15px;height:15px;padding-bottom:4px;"></div>'+
				   '     <div>手机：<span>'+guides[x].telphone+'</span></div>'+
			       '     <div>旅行社：<span>'+guides[x].travel.travelName+'</span></div>'+
				   '</div>'+
				' </div></div>';
		   }
		  return results;
	   },
	   
	   //初始化表格
	   initGrid:function(curr){
		   $.ajax({
			   url: "allguides.do",
			   data:{
				   "page": curr || 1 ,
				   "pageSize":8,
				   "guideName":$('#fguideName').val() 
			   },
			   success: function(data){
				   $('#guidesC').html(Guides.setGuide(data.rows));
				   $("img").error(function () {
					   $(this).attr("src", "/images/placeholder.png");
				   });
				   var pages = Math.ceil(data.total/8);
				  //显示分页
				   layui.laypage({
				        cont: 'pageDemo', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
				        pages: pages, //通过后台拿到的总页数
				        curr: curr || 1, //当前页
				        jump: function(obj, first){ //触发分页后的回调
					          if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
					        	  Guides.initGrid(obj.curr);
					          }
				        }
				  });
			    }
		   });
	   },
	   
	   /**
	    * 清空
	    */
	   clearInput:function(){
		   $("#guideId").val("");
		   $("#guideName").val("");
		   $("#birth").val("");
		   $("#idCard").val("");
		   $("#telphone").val("");
		   $("input[name='sex'][value='1']").attr("checked",true);
		   $("#nationId").val("");
		   $("#nationId").attr('realValue',"");
		   $("#eduId").val("");
		   $("#eduId").attr('realValue',"");
		   $("#guideNumber").val("");
		   $("#cardNumber").val("");
		   $("#travelId").val("0");
		   $("#cityName").val("");
		   $("#cityName").attr('realValue',"");
		   $("#language").val("0");
		   $("#guideClass").val("0");
		   form.render('select');
		   $("#qualifyNumber").val("");
		   $("#licensingBody").val("");
		   $("#licensingDate").val("");
		   $("#annualSituation").val("");
		   $("#valuesInfo").val("");
		   $("#training").val("");
		   $("#groupTour").val("");
		   $("#humanImg").attr('imgName',"");
		   $('#humanImg').attr('src',"/images/placeholder.png");
	   },
	   
	   
	}; 
	
	//页面入口
	Guides.init();
	});
});