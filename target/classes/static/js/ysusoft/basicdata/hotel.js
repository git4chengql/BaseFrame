$(function(){
	layui.use(['form','layer','laydate','upload'], function(){
		   var form = layui.form()
		   ,layer = layui.layer
		   ,laydate = layui.laydate;
		   layui.upload({
			    url: '/uploadweb/imgupload.json'
			    ,elem: '#test' //指定原始元素，默认直接查找class="layui-upload-file"
			    ,method: 'post' //上传接口的http类型
			    ,success: function(data){
			       if(data.success ==true){
			    	   $("#logoImg").attr('imgName',data.message);
			    	   $('#logoImg').attr('src',window._downUrl+$('#logoImg').attr('imgName'));
			       }
			    }
		   });
	
	Hotel = {
	   //页面入口函数
	   init:function(){
		   Hotel.initGrid();
		   Hotel.bindUI();
	   },
			   
	   //绑定事件
	   bindUI:function(){
		   
		   //查询
		   $('#queryBtn').click(function(){
			   Hotel.initGrid();
		   });
		   
		   //新增
		   $('#addBtn').click(function(){
			   Hotel.addHotel();
		   });
		   
		   //编辑
		   $('#editBtn').click(function(){
			   Hotel.updateHotel();
		   });
		   
		   //删除
		   $('#delBtn').click(function(){
			   Hotel.delHotel();
		   });
		   
	   },
	   
	   //新增保存方法
	   addHotel:function(){
			
		    //打开表单弹框
			var index = layer.open({
			   type: 1,
			   title:'新增星级酒店',
			   area: ['900px', '530px'], //宽高
			   content: $('#popContainer'),
		    });
			
			//提交表单
			Hotel.formSubmit(index);
	   },
	   formSubmit:function(index){
		   form.on('submit(demo1)', function(data){
			   var data = decodeURIComponent($('#myform').serialize(),true);
			   data += "&logoImg="+$('#logoImg').attr('imgName');
			   $.ajax({
			        type: 'post',
			        url: 'addorupdatehotel.do',
			        data : data,
			        success: function (data) {
			        	if(data.success){
			        		layer.msg(data.message);
			        		$('#dg').datagrid('reload');
			        		layer.close(index);
			        		Hotel.clearInput();
			        	}else
			        		layer.msg(data.message);
			        }
			    });
		        return false;
		   });
		   $('#closeBtn').click(function(){
				layer.close(index);
		   });
	   },
	   
	   //编辑方法
	   updateHotel:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length>1 || selections.length<=0){
			   layer.msg("选择一行记录");
		   }else{
			   //打开表单弹框，并赋值
			   var index = layer.open({
				   type: 1,
				   title:'编辑星级酒店',
				   area: ['900px', '530px'], //宽高
				   content: $('#popContainer'),
				   success:function(layero, index){
					   $('#hotelId').val(selections[0].hotelId);
					   $('#hotelName').val(selections[0].hotelName);
					   $('#zipCode').val(selections[0].zipCode);
					   $('#category').val(selections[0].category);
					   $('#starLevel').val(selections[0].starLevel);
					   form.render('select');
					   $('#manager').val(selections[0].manager);
					   $('#telphone').val(selections[0].telphone);
					   $('#switchboard').val(selections[0].switchboard);
					   $('#licenseNumber').val(selections[0].licenseNumber);
					   $('#facility').val(selections[0].facility);
					   $('#statistic').val(selections[0].statistic);
					   $('#address').val(selections[0].address);
					   $('#introduction').val(selections[0].introduction);
					   $("#logoImg").attr('imgName',selections[0].logoImg);
					   if(selections[0].imgName!="" &&selections[0].imgName!="undefined" && typeof(selections[0].imgName) != "undefined"){
							$('#logoImg').attr('src',window._downUrl+$('#logoImg').attr('imgName'));
					   }
				   }
			  });
			  //提交表单
			  Hotel.formSubmit(index);
		   }
	   },
	   
	   //删除方法
	   delHotel:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length==1){
			  var index = layer.confirm('确定删除？', {
                   title:'提示',
				   btn: ['确定','取消']
				   }, function(){
					   $.ajax({
					        type: 'post',
					        url: 'delhotel.do?hotelId='+selections[0].hotelId,
					        success: function (data) {
					        	if(data.success){
					        		$('#dg').datagrid('reload');
					        		layer.close(index);
					        		layer.msg(data.message);
					        	}else
					        		layer.msg(data.message);
					        }
					    });
			       });
			}else{
				layer.msg('请选择一条记录');
			}
	   },
	   
	   
	   //初始化表格
	   initGrid:function(){
		   var _Grid =  $('#dg').datagrid({
			    url:'allhotel.do',
			    pagination:true,
			    singleSelect:true,
			    queryParams: {
					 pageSize:20,
					 hotelName:$('#fhotelName').val()
			    },
				loadMsg:'请稍等,数据正在加载中……',
				emptyMsg:'无记录',
				rownumbers:true,
			    width:window.innerWidth,
			    height:window.innerHeight-79,
			    striped:true,
			    columns:[[
					{field:'hotelId',title:'主键',width:100,align:'center',checkbox:true},
					{field:'hotelName',title:'饭店名称',width:200,align:'center'},
					{field:'category',title:'饭店类型',width:200,align:'center',
						formatter:function(val,row){
							if (val =="1"){
								return '暂住型饭店';
							}else if (val =="2"){
								return "长住型饭店";
							}else if (val =="3"){
								return "度假型饭店";
							}else if (val =="4"){
								return "会议型饭店";
							}
					}},
					{field:'starLevel',title:'饭店星级',width:150,align:'center',
						formatter:function(val,row){
							if (val =="1"){
								return '一星级';
							}else if (val =="2"){
								return "二星级";
							}else if (val =="3"){
								return "三星级";
							}else if (val =="4"){
								return "四星级";
							}else if (val =="5"){
								return "五星级";
							}
					}},
					{field:'address',title:'详细地址',width:300,align:'center'},
					{field:'manager',title:'联系人',width:150,align:'center'},
					{field:'telphone',title:'联系人电话',width:150,align:'center'},
					{field:'switchboard',title:'酒店电话',width:150,align:'center'},
			    ]],
			    onDblClickRow: function (rowIndex, rowData) {
			    	var index = layer.open({
						   type: 1,
						   area: ['900px', '500px'],
						   shadeClose: true, //开启遮罩关闭
						   title:"星级酒店详情",
						   content: $('#detail').html(),
						   success:function(layero, index){
							   $('#detailname').html(rowData.hotelName);
							   var category ='';
							   if(rowData.category=="1"){
								   category = '暂住型饭店';
							   }else if(rowData.category=="2"){
								   category = '长住型饭店';
							   }else if(rowData.category=="3"){
								   category = '度假型饭店';
							   }else if(rowData.category=="4"){
								   category = '会议型饭店';
							   }
							   $('#detailcategory').html(category);
							   $('#detailswitchboard').html(rowData.switchboard);
							   $('#detailtelphone').html(rowData.telphone);
							   $('#detaillicenseNumber').html(rowData.licenseNumber);
							   $('#detailmanager').html(rowData.manager);
							   $('#detailintroduction').html(rowData.introduction);
							   $('#detailaddress').html(rowData.address);
							   var starLevel ='';
							   if(rowData.starLevel=="1"){
								   starLevel = '一星级';
							   }else if(rowData.starLevel=="2"){
								   starLevel = '二星级';
							   }else if(rowData.starLevel=="3"){
								   starLevel = '三星级';
							   }else if(rowData.starLevel=="4"){
								   starLevel = '四星级';
							   }else if(rowData.starLevel=="5"){
								   starLevel = '五星级';
							   }
							   $('#detailstarLevel').html(starLevel);
							   if(rowData.logoImg=="undefined" || rowData.logoImg==null){
								   $('#detailimg').attr('src',"/images/evtplace.jpg");
							   }else{
								   $('#detailimg').attr('src',window._downUrl+rowData.logoImg);
							   }
						   }
					  });
			    }
			});
            var p = _Grid.datagrid('getPager');
		    if (p){
	    		$(p).pagination({
    				showRefresh:true,
    				showPageList:false,
    				pageSize:20
	    		});
		    };
	   },
	   
	   /**
	    * 清空
	    */
	   clearInput:function(){
		   $("#hotelId").val("");
		   $("#hotelName").val("");
		   $("#zipCode").val("");
		   $("#address").val("");
		   $("#manager").val("");
		   $("#telphone").val("");
		   $("#starLevel").val("");
		   $("#switchboard").val("");
		   $("#licenseNumber").val("");
		   $("#facility").val("");
		   $("#statistic").val("");
		   $("#category").val("");
		   $("#introduction").val("");
	   },
	   
	   
	}; 
	
	//页面入口
	Hotel.init();
	});
});