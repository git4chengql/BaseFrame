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
	
	Travel = {
	   index:0,
	   //页面入口函数
	   init:function(){
		   Travel.initGrid();
		   Travel.bindUI();
	   },
	   
	   //绑定事件
	   bindUI:function(){
		   //查询
		   $('#queryBtn').click(function(){
			   Travel.initGrid();
		   });
		   
		   //新增
		   $('#addBtn').click(function(){
			   Travel.addTravel();
		   });
		   
		   //编辑
		   $('#editBtn').click(function(){
			   Travel.updateTravel();
		   });
		   
		   //删除
		   $('#delBtn').click(function(){
			   Travel.delTravel();
		   });
	   },
	   
	   //保存方法
	   addTravel:function(){
		   Travel.index = layer.open({
			   type: 1,
			   title:'新增旅行社',
			   area: ['900px', '600px'], 
			   content: $('#popContainer'),
		   });
		   //表单提交
		   Travel.formSubmit();
	   },
	   
	   formSubmit:function(){
		   form.on('submit(demo1)', function(data){
			   var data = decodeURIComponent($('#myform').serialize(),true);
			   data += "&logoImg="+$('#logoImg').attr('imgName');
			   $.ajax({
			        type: 'post',
			        url: 'addorupdatetravel.do',
			        data : data,
			        success: function (data) {
			        	if(data.success){
			        		layer.msg(data.message);
			        		$('#dg').datagrid('reload');
			        		$("#wizard").scrollable().begin();
			        		layer.close(Travel.index);
			        		Travel.clearInput();
			        	}else
			        		layer.msg(data.message);
			        }
			    });
			   return false;
		   });
		   $('#closeBtn').click(function(){
				layer.close(Travel.index);
				$("#wizard").scrollable().begin();
		   });
	   },
	   
	   //编辑方法
	   updateTravel:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length>1 || selections.length<=0){
			   layer.msg("选择一行记录");
		   }else{
			   Travel.index = layer.open({
				   type: 1,
				   title:'编辑旅行社',
				   area: ['900px', '600px'], 
				   content: $('#popContainer'),
				   success:function(layero, index){
					   $('#travelId').val(selections[0].travelId);
					   $('#travelName').val(selections[0].travelName);
					   $('#zipCode').val(selections[0].zipCode);
					   $('#licenseKey').val(selections[0].licenseKey);
					   $('#grade').val(selections[0].grade);
					   form.render('select');
					   $('#telphone').val(selections[0].telphone);
					   $('#category').val(selections[0].category);
					   $('#complaintCall').val(selections[0].complaintCall);
					   $('#payment').val(selections[0].payment);
					   $('#licenseNumber').val(selections[0].licenseNumber);
					   $('#organizeCode').val(selections[0].organizeCode);
					   $('#address').val(selections[0].address);
					   $('#register').val(selections[0].register);
					   $('#alteration').val(selections[0].alteration);
					   $('#production').val(selections[0].production);
					   $('#website').val(selections[0].website);
					   $('#personnel').val(selections[0].personnel);
					   $('#finance').val(selections[0].finance);
					   $('#guaranteePay').val(selections[0].guaranteePay);
					   $('#insure').val(selections[0].insure);
					   $('#introduction').val(selections[0].introduction);
					   $("#logoImg").attr('imgName',selections[0].logoImg);
					   if(selections[0].imgName!="" &&selections[0].imgName!="undefined" && typeof(selections[0].imgName) != "undefined"){
							$('#logoImg').attr('src',window._downUrl+$('#logoImg').attr('imgName'));
					   }
				   }
			  });
			  //表单提交
			  Travel.formSubmit();
		   }
	   },
	   
	   //删除方法
	   delTravel:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length==1){
			   Travel.index = layer.confirm('确定删除？', {
                   title:'提示',
				   btn: ['确定','取消']
				   }, function(){
					   $.ajax({
					        type: 'post',
					        url: 'deltravel.do?travelId='+selections[0].travelId,
					        success: function (data) {
					        	if(data.success){
					        		$('#dg').datagrid('reload');
					        		layer.close(Travel.index);
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
			    url:'alltravel.do',
			    pagination:true,
			    singleSelect:true,
			    queryParams: {
					 pageSize:20,
					 travelName:$('#ftravelName').val()
			    },
				loadMsg:'请稍等,数据正在加载中……',
				emptyMsg:'无记录',
				rownumbers:true,
			    width:window.innerWidth,
			    height:window.innerHeight-79,
			    striped:true,
			    columns:[[
					{field:'travelId',title:'主键',width:100,align:'center',checkbox:true},
					{field:'travelName',title:'旅行社名称',width:200,align:'center'},
					{field:'grade',title:'旅行社等级',width:100,align:'center',
						formatter:function(value, row, index){ 
							if(value=="1") return "3A";
							else if(value=="2") return "4A";
							else if(value=="3") return "5A";
							else return "";
					}},
					{field:'category',title:'业务类别',width:150,align:'center'},
					{field:'licenseNumber',title:'工商营业执照号',width:150,align:'center'},
					{field:'organizeCode',title:'组织机构代码',width:150,align:'center'},
					{field:'telphone',title:'预定电话',width:150,align:'center'},
					{field:'complaintCall',title:'投诉电话',width:150,align:'center'},
					{field:'address',title:'详细地址',width:280,align:'center'},
			    ]],
			    onDblClickRow: function (rowIndex, rowData) {
					   var index = layer.open({
						   type: 1,
						   area: ['900px', '500px'],
						   shadeClose: true, //开启遮罩关闭
						   title:"旅行社详情",
						   content: $('#detail').html(),
						   success:function(layero, index){
							   $('#detailname').html(rowData.travelName);
							   var grade ='';
							   if(rowData.grade=="1"){
								   grade = '3A';
							   }else if(rowData.grade=="2"){
								   grade = '4A';
							   }else if(rowData.grade=="3"){
								   grade = '5A';
							   }
							   $('#detailgrade').html(grade);
							   $('#detailtelphone').html(rowData.telphone);
							   $('#detaillicenseKey').html(rowData.licenseKey);
							   $('#detaillicenseNumber').html(rowData.licenseNumber);
							   $('#detailwebsite').html(rowData.website);
							   $('#detailorganizeCode').html(rowData.organizeCode);
							   $('#detailcategory').html(rowData.category);
							   $('#detailaddress').html(rowData.address);
							   $('#detailregister').html(rowData.register);
							   $('#detailalteration').html(rowData.alteration);
							   $('#detailproduction').html(rowData.production);
							   $('#detailpersonnel').html(rowData.personnel);
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
		   $("#travelId").val("");
		   $("#travelName").val("");
		   $("#zipCode").val("");
		   $("#licenseKey").val("");
		   $("#grade").val("");
		   $("#telphone").val("");
		   $("#category").val("");
		   $("#complaintCall").val("");
		   $("#payment").val("");
		   $("#licenseNumber").val("");
		   $("#organizeCode").val("");
		   $("#address").val("");
		   $("#register").val("");
		   $("#alteration").val("");
		   $("textarea").val("");
	   },
	   
	   
	}; 
	
	//页面入口
	Travel.init();
	});	
});