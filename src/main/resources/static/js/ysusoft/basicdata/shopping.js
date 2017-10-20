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
		   
		   
	Shopping = {
	   init:function(){
		   Shopping.initGrid();
		   Shopping.bindUI();
	   },
			   
	   /**
	    * 绑定事件
	    */
	   bindUI:function(){
		   
		   /**
		    * 查询
		    */
		   $('#queryBtn').click(function(){
			   Shopping.initGrid();
		   });
		   
		   /**
		    * 新增
		    */
		   $('#addBtn').click(function(){
			   Shopping.addShopping();
		   });
		   
		   /**
		    * 编辑
		    */
		   $('#editBtn').click(function(){
			   Shopping.updateShopping();
		   });
		   
		   /**
		    * 删除
		    */
		   $('#delBtn').click(function(){
			   Shopping.delShopping();
		   });
		   
	   },//bindUI结束
	   
	   /**
	    * 新增保存方法
	    */
	   addShopping:function(){
			var index = layer.open({
			   type: 1,
			   title:'新增购物点',
			   area: ['950px', '600px'], //宽高
			   content: $('#popContainer'),
		    });
			//表单提交
			Shopping.formSubmit(index);
	   },
	   
	   /**
	    * 提交表单
	    * @param index
	    */
	   formSubmit:function(index){
		   form.on('submit(demo1)', function(data){
			   var data = decodeURIComponent($('#myform').serialize(),true);
			   data += '&location='+$('#locationName').attr('realValue');
			   data += "&logoImg="+$('#logoImg').attr('imgName');
			   $.ajax({
			        type: 'post',
			        url: 'addorupdateshopping.do',
			        data : data,
			        success: function (data) {
			        	if(data.success){
			        		layer.msg(data.message);
			        		$('#dg').datagrid('reload');
			        		layer.close(index);
			        		Shopping.clearInput();
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
	   
	   /**
	    * 编辑方法
	    */
	   updateShopping:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length>1 || selections.length<=0){
			   layer.msg("请选择一行记录");
		   }else{
			   //打开表单弹框，并赋值
			   var index = layer.open({
				   type: 1,
				   title:'编辑购物点',
				   area: ['950px', '600px'], //宽高
				   content: $('#popContainer'),
				   success:function(layero, index){
					   $('#shoppingId').val(selections[0].shoppingId);
					   $('#shoppingName').val(selections[0].shoppingName);
					   $('#locationName').val(selections[0].locationName);
					   $('#locationName').attr('realValue',selections[0].location);
					   $('#address').val(selections[0].address);
					   $('#manager').val(selections[0].manager);
					   $('#telphone').val(selections[0].telphone);
					   $('#licenseKey').val(selections[0].licenseKey);
					   $('#licenseNumber').val(selections[0].licenseNumber);
					   $('#organizeCode').val(selections[0].organizeCode);
					   $('#opentime').val(selections[0].opentime);
					   $('#complaintCall').val(selections[0].complaintCall);
					   $('#payment').val(selections[0].payment);
					   $('#parking').val(selections[0].parking);
					   $('#commodity').val(selections[0].commodity);
					   $('#facility').val(selections[0].facility);
					   $('#brief').val(selections[0].brief);
					   $("#logoImg").attr('imgName',selections[0].logoImg);
					   if(selections[0].imgName!="" &&selections[0].imgName!="undefined" && typeof(selections[0].imgName) != "undefined"){
							$('#logoImg').attr('src',window._downUrl+$('#logoImg').attr('imgName'));
					   }
				   }
			  });
			  //表单提交
			  Shopping.formSubmit(index);
		   }
	   },
	   
	   /**
	    * 删除一条或者多条记录方法
	    */
	   delShopping:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length>=1){
			   var selections = $('#dg').datagrid('getSelections');
		    	var shoppingIds = "";
				for(var index = 0; index <selections.length; index++){
						if(shoppingIds != "") shoppingIds += ",";
						shoppingIds += selections[index].shoppingId;
					}
			  var index = layer.confirm('确定删除?', {
                   title:'提示',
				   btn: ['确定','取消']
				   }, function(){
					   $.ajax({
					        type: 'post',
					        url: 'delshopping.do?shoppingIds='+shoppingIds,
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
				layer.msg('请至少选择一条记录！');
			}
	   },
	   
	   
	   /**
	    * 初始化自适应表格
	    * 增加双击事件
	    */
	   initGrid:function(){
		   var _Grid =  $('#dg').datagrid({
			    url:'allshopping.do',
			    pagination:true,
			    queryParams: {
					 pageSize:20,
					 shoppingName:$('#fshoppingName').val()
			    },
				loadMsg:'请稍等,数据正在加载中……',
				emptyMsg:'无记录',
				rownumbers:true,
			    width:window.innerWidth,
			    height:window.innerHeight-79,
			    striped:true,
			    fit:true,
			    fitColumns:true,
			    columns:[[
					{field:'shoppingId',title:'主键',width:100,align:'center',checkbox:true},
					{field:'shoppingName',title:'购物点名称',width:200,align:'center'},
					{field:'locationName',title:'所在地',width:200,align:'center'},
					{field:'address',title:'详细地址',width:300,align:'center'},
					{field:'opentime',title:'营业时间',width:150,align:'center'},
					{field:'complaintCall',title:'质量投诉电话',width:150,align:'center'}
			    ]],
			   onDblClickRow: function (rowIndex, rowData) {
					   var index = layer.open({
						   type: 1,
						   title:'购物点详情',
						   area: ['900px', '500px'],
						   content: $('#detail').html(),
						   success:function(layero, index){
							   $('#detailname').html(rowData.shoppingName);
							   $('#detaillocationName').html(rowData.locationName);
							   $('#detailopentime').html(rowData.opentime);
							   $('#detaillicenseKey').html(rowData.licenseKey);
							   $('#detailorganizeCode').html(rowData.organizeCode);
							   $('#detailcomplaintCall').html(rowData.complaintCall);
							   $('#detailpayment').html(rowData.payment);
							   $('#detailaddress').html(rowData.address);
							   $('#detailbrief').html(rowData.brief);
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
		   $("#shoppingId").val("");
		   $("#shoppingName").val("");
		   $("#locationName").val("");
		   $("#locationName").attr('realValue',"");
		   $("#licenseKey").val("");
		   $("#licenseNumber").val("");
		   $("#organizeCode").val("");
		   $("#opentime").val("");
		   $("#complaintCall").val("");
		   $("#payment").val("");
		   $("#address").val("");
		   $("#parking").val("");
		   $("#commodity").val("");
		   $("#facility").val("");
		   $("#brief").val("");
	   },
	   
			 
	}; 
		   
    //页面入口
	Shopping.init();
	}); 
});