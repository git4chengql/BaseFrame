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
		   
		   
	Spot = {
	   //页面入口函数
	   init:function(){
		   Spot.initGrid();
		   Spot.bindUI();
	   },
			   
	   //绑定事件
	   bindUI:function(){
		   
		   //查询
		   $('#queryBtn').click(function(){
			   Spot.initGrid();
		   });
		   
		   //新增
		   $('#addBtn').click(function(){
			   Spot.addSpot();
		   });
		   
		   //编辑
		   $('#editBtn').click(function(){
			   Spot.updateSpot();
		   });
		   
		   //删除
		   $('#delBtn').click(function(){
			   Spot.delSpot();
		   });
		   
	   },//bindUI结束
	   
	   //新增保存方法
	   addSpot:function(){
		   
			//打开表单弹框
		   var index = layer.open({
			   type: 1,
			   title:'新增旅游景区',
			   area: ['900px', '600px'], //宽高
			   content: $('#popContainer'),
		    });
			//提交表单
			Spot.formSubmit(index);
	   },
	   
	   formSubmit:function(index){
		   form.on('submit(demo1)', function(data){
			   var data = decodeURIComponent($('#myform').serialize(),true);
			   data += "&logoImg="+$('#logoImg').attr('imgName');
			   data += '&location='+$('#locationName').attr('realValue');
			   $.ajax({
			        type: 'post',
			        url: 'addorupdatespot.do',
			        data : data,
			        success: function (data) {
			        	if(data.success){
			        		layer.msg(data.message);
			        		$('#dg').datagrid('reload');
			        		$("#wizard").scrollable().begin();
			        		layer.close(index);
			        		Spot.clearInput();
			        	}else
			        		layer.msg(data.message);
			        }
			    });
		        return false;
		   });
		   $('#closeBtn').click(function(){
			   $("#wizard").scrollable().begin();
				layer.close(index);
		   });
	   },
	   
	   //编辑方法
	   updateSpot:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length>1 || selections.length<=0){
			   layer.msg("选择一行记录");
		   }else{
			   //打开表单弹框，并赋值
			   var index = layer.open({
				   type: 1,
				   title:'编辑旅游景区',
				   area: ['900px', '600px'], //宽高
				   content: $('#popContainer'),
				   success:function(layero, index){
					   $('#spotId').val(selections[0].spotId);
					   $('#spotName').val(selections[0].spotName);
					   $('#locationName').val(selections[0].locationName);
					   $('#locationName').attr('realValue',selections[0].location);
					   $('#address').val(selections[0].address);
					   $('#manager').val(selections[0].manager);
					   $('#telphone').val(selections[0].telphone);
					   $('#category').val(selections[0].category);
					   $('#licenseKey').val(selections[0].licenseKey);
					   $('#licenseNumber').val(selections[0].licenseNumber);
					   $('#organizeCode').val(selections[0].organizeCode);
					   $('#grade').val(selections[0].grade);
					   form.render('select');
					   $('#season').val(selections[0].season);
					   $('#opentime').val(selections[0].opentime);
					   $('#price').val(selections[0].price);
					   $('#description').val(selections[0].description);
					   $('#area').val(selections[0].area);
					   $('#infrastructure').val(selections[0].infrastructure);
					   $('#spottitle').val(selections[0].spottitle);
					   $('#landtype').val(selections[0].landtype);
					   $('#around').val(selections[0].around);
					   $('#sightline').val(selections[0].sightline);
					   $('#activity').val(selections[0].activity);
					   $('#service').val(selections[0].service);
					   $('#traffic').val(selections[0].traffic);
					   $('#parking').val(selections[0].parking);
					   $('#gas').val(selections[0].gas);
					   $('#brif').val(selections[0].brif);
					   $("#logoImg").attr('imgName',selections[0].logoImg);
					   if(selections[0].imgName!="" &&selections[0].imgName!="undefined" && typeof(selections[0].imgName) != "undefined"){
							$('#logoImg').attr('src',window._downUrl+$('#logoImg').attr('imgName'));
					   }
				   }
			  });
			  //提交表单
			  Spot.formSubmit(index);
		   }
	   },
	   
	   //删除方法
	   delSpot:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length==1){
			   var index = layer.confirm('确定删除？', {
                   title:'提示',
				   btn: ['确定','取消']
				   }, function(){
					   $.ajax({
					        type: 'post',
					        url: 'delspot.do?spotId='+selections[0].spotId,
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
			    url:'allspot.do',
			    pagination:true,
			    singleSelect:true,
			    queryParams: {
					 pageSize:20,
					 spotName:$('#fspotName').val()
			    },
				loadMsg:'请稍等,数据正在加载中……',
				emptyMsg:'无记录',
				rownumbers:true,
			    width:window.innerWidth,
			    height:window.innerHeight-79,
			    striped:true,
			    columns:[[
					{field:'spotId',title:'主键',width:100,align:'center',checkbox:true},
					{field:'spotName',title:'景区名称',width:200,align:'center'},
					{field:'locationName',title:'所在地',width:200,align:'center'},
				/*	{field:'address',title:'详细地址',width:300,align:'center'},*/
					{field:'manager',title:'负责人',width:150,align:'center'},
					{field:'telphone',title:'负责人电话',width:150,align:'center'},
					{field:'category',title:'景区类型',width:150,align:'center'},
					{field:'grade',title:'景区等级',width:150,align:'center'},
					{field:'opentime',title:'开放时间',width:150,align:'center'},
					{field:'price',title:'门票价格',width:150,align:'center'},
			    ]],
			    onDblClickRow: function (rowIndex, rowData) {
					   var index = layer.open({
						   type: 1,
						   area: ['900px', '500px'],
						   shadeClose: true, //开启遮罩关闭
						   title:"旅游景区详情",
						   content: $('#detail').html(),
						   success:function(layero, index){
							   $('#detailname').html(rowData.spotName);
							   $('#detailgrade').html(rowData.grade);
							   $('#detailcategory').html(rowData.category);
							   $('#detaillocationName').html(rowData.locationName);
							   $('#detailopentime').html(rowData.opentime);
							   $('#detailmanager').html(rowData.manager);
							   $('#detailprice').html(rowData.price);
							   $('#detailtelphone').html(rowData.telphone);
							   $('#detailaddress').html(rowData.address);
							   $('#detailbrif').html(rowData.brif);
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
		   $("#spotId").val("");
		   $("#spotName").val("");
		   $("#locationName").val("");
		   $("#address").val("");
		   $("#manager").val("");
		   $("#telphone").val("");
		   $("#category").val("");
		   $("#licenseKey").val("");
		   $("#licenseNumber").val("");
		   $("#organizeCode").val("");
		   $("#grade").val("");
		   form.render('select');
		   $("#season").val("");
		   $("#opentime").val("");
		   $("#price").val("");
		   $("#description").val("");
		   $("#area").val("");
		   $("#infrastructure").val("");
		   $("#spottitle").val("");
		   $("#landtype").val("");
		   $("#sightline").val("");
		   $("#around").val("");
		   $("#activity").val("");
		   $("#service").val("");
		   $("#traffic").val("");
		   $("#parking").val("");
		   $("#gas").val("");
		   $("#brif").val("");
	   },
	   
			 
	}; 
		   
    //页面入口
	Spot.init();
}); 
