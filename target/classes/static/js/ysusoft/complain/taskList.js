$(function(){
	layui.use(['form','layer','element'], function(){
	var form = layui.form(),element = layui.element(),layer = layui.layer;
	
	//一些事件监听
	element.on('tab(tabDemo)', function(data){
//		alert(data.index);
		if(data.index==1){
			Task.initGridEnd();
		}
	});	
	
	var Task = {
	   
	   layerIndex:0,
	   //页面入口函数
	   init:function(){
		   Task.initGrid();
		   Task.bindUI();
		  /* $(window).resize(function(){
			   $('#dg').datagrid('resize', {
				   width:function(){
					   return window.innerWidth;
				   }
			   });
		   });*/
		   $(".complainContent").height(window.innerHeight-115);
	   },
	   
	   //绑定事件
	   bindUI:function(){
		   
		   $('#queryBtn').click(function(){
			   Task.queryTask();
		   })
		   
		   $('#queryBtnEnd').click(function(){
			   Task.queryTaskEnd();
		   })
		   
		   $('#addBtn').click(function(){
			   Task.beforeComplete();
		   });
		   
		   $('#delBtn').click(function(){
			   if($('#delBtn').attr('class').indexOf('layui-btn-disabled')>0){
				   return ;
			   }
			   Task.afterComplete();
		   });
	   },
	   
	   //初始化表格
	   initGrid:function(){
		   var _Grid =  $('#dg').datagrid({
				    url:'../event/allTask.do',
				    pagination:true,
					rownumbers : false,
					fitColumns : false,
					singleSelect : true,
					fit : false,
				    queryParams: {
								 pageSize:20,
								 backInfo:$('#backInfo').val()
						         },
					loadMsg:'请稍等,数据正在加载中……',
					emptyMsg:'无记录',
				    width:(window.innerWidth*0.25),
				    height:window.innerHeight-115,
				    striped:true,
				    rowStyler: function(index,row){
				 		if (row.f_Pro_Status==0){
				 			return 'color:blue;font-weight:bold;cursor:pointer;'; 
				 		}
				 	},
				    columns:[[
						{field:'currentTask',title:'待办案件列表',width:'100%',align:'left',
							formatter: function(value,row,index){
								var tips = '';
								if(row.cueType==3||row.cueType==4)
							    	tips = ' <i class="fa fa-smile-o fa-lg pull-right" title="正常" style="color:#7FFF00;" ></i> ';
								else if(row.cueType==2)
									tips = ' <i class="fa fa-frown-o fa-lg pull-right" title="即将超时" style="color:#FFD700;" ></i> ';
								else if(row.cueType==1)
									tips = ' <i class="fa fa-frown-o fa-lg pull-right" title="已超时" style="color:red;" ></i> ';
								Date.prototype.toLocaleString = function() {
									return this.getFullYear() + "-"
											+ ((this.getMonth() + 1) < 10 ? "0" + (this.getMonth() + 1) : this.getMonth() + 1) + "-"
											+ (this.getDate() < 10 ? "0" + this.getDate() : this.getDate())
											+ " " + (this.getHours()<10?"0"+this.getHours():this.getHours())+ ":" 
											+ (this.getMinutes()<10?"0"+this.getMinutes():this.getMinutes()) + ":"
											+ (this.getSeconds()<10?"0"+this.getSeconds():this.getSeconds());
								};
								var img = '<span class="pull-right fa fa-random" style="cursor:pointer" title="查看流程图" onclick="javascript:traceImage('+row.processId+')"></span>';
								var content = row.complainId.content;
								if(content.length>18){
									content = content.substring(0,18) + "...";
								}
							    return ' <div class="container-fluid"><div class="row"><div class="col-md-12">'+tips+' '+row.complainId.title+'</div></div>'+
											 ' <div class="row"><div class="col-md-12 desc">'+content+'</div></div>'+
											 ' <div class="row"><div class="col-md-12">'+img+' '+ ( new Date( row.createDate) ).toLocaleString() +' </div></div>'+
									   ' </div>';
						}}
				    ]],
				    onClickRow:function(rowIndex,rowData){
				    	Task.showDetail(rowData);
				    	Task.showTransDetail(rowData.executionId,rowData);
					},
					onLoadSuccess:function(data){
				    	if(data.total>0){
				    		$('#dg').datagrid('selectRow', 0);
				    		Task.showDetail(data.rows[0]);
				    		Task.showTransDetail(data.rows[0].executionId,data.rows[0]);
				    	}
				    },
			});
		    
            var p = _Grid.datagrid('getPager');
		    if (p){
		    		$(p).pagination({
		    				showRefresh:true,
		    				showPageList:false,
		    				pageSize:20,
							displayMsg: ''
		    		});
		    };
	   },
	   
	   //初始化表格
	   initGridEnd:function(){
		   var _GridEnd =  $('#dgEnd').datagrid({
				    url:'../event/allTaskEnd.do',
				    pagination:true,
					rownumbers : false,
					fitColumns : false,
					singleSelect : true,
					fit : false,
				    queryParams: {
								 pageSize:20,
								 backInfo:$('#backInfoEnd').val()
						         },
					loadMsg:'请稍等,数据正在加载中……',
					emptyMsg:'无记录',
				    width:(window.innerWidth*0.25),
				    height:window.innerHeight-115,
				    striped:true,
				    rowStyler: function(index,row){
				 		if (row.f_Pro_Status==0){
				 			return 'color:blue;font-weight:bold;cursor:pointer;'; 
				 		}
				 	},
				    columns:[[
						{field:'currentTask',title:'已办结案件列表',width:'100%',align:'left',
							formatter: function(value,row,index){
								Date.prototype.toLocaleString = function() {
									return this.getFullYear() + "-"
											+ ((this.getMonth() + 1) < 10 ? "0" + (this.getMonth() + 1) : this.getMonth() + 1) + "-"
											+ (this.getDate() < 10 ? "0" + this.getDate() : this.getDate())
											+ " " + (this.getHours()<10?"0"+this.getHours():this.getHours())+ ":" 
											+ (this.getMinutes()<10?"0"+this.getMinutes():this.getMinutes()) + ":"
											+ (this.getSeconds()<10?"0"+this.getSeconds():this.getSeconds());
								};
								var img = '<span class="pull-right fa fa-random" style="cursor:pointer" title="查看流程图" onclick="javascript:traceImage('+row.processId+')"></span>';
								var content = row.complainId.content;
								if(content.length>18){
									content = content.substring(1,18) + "...";
								}
							    return ' <div class="container-fluid"><div class="row"><div class="col-md-12">'+row.complainId.title+'</div></div>'+
											 ' <div class="row"><div class="col-md-12 desc">'+content+'</div></div>'+
											 ' <div class="row"><div class="col-md-12">'+img+' '+ ( new Date( row.createDate) ).toLocaleString() +' </div></div>'+
									   ' </div>';
						}}
				    ]],
				    onClickRow:function(rowIndex,rowData){
				    	Task.showDetailEnd(rowData);
				    	Task.showTransDetailEnd(rowData.executionId,rowData);
					},
					onLoadSuccess:function(data){
				    	if(data.total>0){
				    		$('#dgEnd').datagrid('selectRow', 0);
				    		Task.showDetailEnd(data.rows[0]);
				    		Task.showTransDetailEnd(data.rows[0].executionId,data.rows[0]);
				    	}
				    },
			});
		    
            var p = _GridEnd.datagrid('getPager');
		    if (p){
		    		$(p).pagination({
		    				showRefresh:true,
		    				showPageList:false,
		    				pageSize:20,
							displayMsg: ''
		    		});
		    };
	   },
	   
	   //查询
	   queryTask:function(){
		   Task.initGrid();
	   },
	   //查询
	   queryTaskEnd:function(){
		   Task.initGridEnd();
	   },
	   
	   showDetail:function(rowData){
		   //控制办理和结案按钮
		   if(rowData.stageName.indexOf('受理平台')<0){
			   $("#delBtn").addClass("layui-btn-disabled");
		   }else{
			   $("#delBtn").removeClass("layui-btn-disabled");
		   }
		   	Date.prototype.toLocaleString = function() {
				return this.getFullYear() + "-"
						+ ((this.getMonth() + 1) < 10 ? "0" + (this.getMonth() + 1) : this.getMonth() + 1) + "-"
						+ (this.getDate() < 10 ? "0" + this.getDate() : this.getDate())
						+ " " + (this.getHours()<10?"0"+this.getHours():this.getHours())+ ":" 
						+ (this.getMinutes()<10?"0"+this.getMinutes():this.getMinutes()) + ":"
						+ (this.getSeconds()<10?"0"+this.getSeconds():this.getSeconds());
			};	
	    	var taskid = rowData.complainId.backInfo;
	    	var createDate = ( new Date(rowData.complainId.createDate) ).toLocaleString();
	    	var name = rowData.complainId.userName;
	    	var phone = rowData.complainId.telphone;
	    	var comlainType = rowData.comlainType;
	    	var title = rowData.complainId.title;
	    	var content = rowData.complainId.content;
	    	if(comlainType!=null && ""!=comlainType){
	    		switch(comlainType)
	    		{
	    		case "1":
	    			comlainType = "服务质量";
	    			break;
	    		case "2":
	    			comlainType = "卫生状况";
	    			break;
	    		case "3":
	    			comlainType = "欺客宰客";
	    			break;
	    		case "4":
	    			comlainType = "门票价格";
	    			break;
	    		case "5":
	    			comlainType = "其他";
	    			break;
	    		default:
	    			comlainType = "其他";
	    		}
	    	}
	    	var media = '';
	    	if(rowData.complainId.images!=null){
	    		var imgs = rowData.complainId.images.split(",");
	    		for(i=0;i<imgs.length;i++){
	    			media+='<img   style="vertical-align:sub; cursor: pointer;" src="'+imgs[i]+'" height="30px" width="40px" onclick="javascript:$(this).zoomify();" />&nbsp;';
	    		}
	    	}
	    	if(rowData.complainId.voices!=null){
	    		var voices = rowData.complainId.voices.split(",");
	    		for(i=0;i<voices.length;i++){
	    			media+='<span class="layui-icon" style="font-size: 27px; height:30px; width:30px; display:inline-block;" onClick="openVoice(\''+voices[i]+'\')" >&#xe652;</span>&nbsp;';
	    		}
	    	}
	    	if(rowData.complainId.audios!=null){
	    		var audios = rowData.complainId.audios.split(",");
	    		for(i=0;i<audios.length;i++){
	    			media+='<span class="layui-icon" style="font-size: 27px; height:30px; width:30px; display:inline-block;" onClick="openAudio(\''+audios[i]+'\')" >&#xe688;</span>&nbsp;';
	    		}
	    	}
	    	if(rowData.complainId.images==null&&rowData.complainId.audios==null&&rowData.complainId.voices==null){
	    		media ='无多媒体信息';
	    	}
	    	$("#taskid").html(taskid);
	    	$("#createDate").html(createDate);
	    	$("#userName").html(name);
	    	$("#telphone").html(phone);
	    	$("#complainType").html(comlainType);
	    	$("#title").html(title);
	    	$("#content").html(content);
	    	$("#media").html(media);
	    	$('img').zoomify({scale:0.6});
	   },
	   showDetailEnd:function(rowData){
		   	Date.prototype.toLocaleString = function() {
				return this.getFullYear() + "-"
						+ ((this.getMonth() + 1) < 10 ? "0" + (this.getMonth() + 1) : this.getMonth() + 1) + "-"
						+ (this.getDate() < 10 ? "0" + this.getDate() : this.getDate())
						+ " " + (this.getHours()<10?"0"+this.getHours():this.getHours())+ ":" 
						+ (this.getMinutes()<10?"0"+this.getMinutes():this.getMinutes()) + ":"
						+ (this.getSeconds()<10?"0"+this.getSeconds():this.getSeconds());
			};	
	    	var taskid = rowData.complainId.backInfo;
	    	var createDate = ( new Date(rowData.complainId.createDate) ).toLocaleString();
	    	var name = rowData.complainId.userName;
	    	var phone = rowData.complainId.telphone;
	    	var comlainType = rowData.comlainType;
	    	var title = rowData.complainId.title;
	    	var content = rowData.complainId.content;
	    	if(comlainType!=null && ""!=comlainType){
	    		switch(comlainType)
	    		{
	    		case "1":
	    			comlainType = "服务质量";
	    			break;
	    		case "2":
	    			comlainType = "卫生状况";
	    			break;
	    		case "3":
	    			comlainType = "欺客宰客";
	    			break;
	    		case "4":
	    			comlainType = "门票价格";
	    			break;
	    		case "5":
	    			comlainType = "其他";
	    			break;
	    		default:
	    			comlainType = "其他";
	    		}
	    	}
	    	var media = '';
	    	if(rowData.complainId.images!=null){
	    		var imgs = rowData.complainId.images.split(",");
	    		for(i=0;i<imgs.length;i++){
	    			media+='<img   style="vertical-align:sub; cursor: pointer;" src="'+imgs[i]+'" height="30px" width="40px" onclick="javascript:$(this).zoomify();" />&nbsp;';
	    		}
	    	}
	    	if(rowData.complainId.voices!=null){
	    		var voices = rowData.complainId.voices.split(",");
	    		for(i=0;i<voices.length;i++){
	    			media+='<span class="layui-icon" style="font-size: 27px; height:30px; width:30px; display:inline-block;" onClick="openVoice(\''+voices[i]+'\')" >&#xe652;</span>&nbsp;';
	    		}
	    	}
	    	if(rowData.complainId.audios!=null){
	    		var audios = rowData.complainId.audios.split(",");
	    		for(i=0;i<audios.length;i++){
	    			media+='<span class="layui-icon" style="font-size: 27px; height:30px; width:30px; display:inline-block;" onClick="openAudio(\''+audios[i]+'\')" >&#xe688;</span>&nbsp;';
	    		}
	    	}
	    	$("#taskidEnd").html(taskid);
	    	$("#createDateEnd").html(createDate);
	    	$("#userNameEnd").html(name);
	    	$("#telphoneEnd").html(phone);
	    	$("#complainTypeEnd").html(comlainType);
	    	$("#titleEnd").html(title);
	    	$("#contentEnd").html(content);
	    	$("#mediaEnd").html(media);
	    	$('img').zoomify({scale:0.6});
	   },
	   
	   /**
		 * 处理流程
		 */
	   showTransDetail:function(executionId,rowData){
				   Date.prototype.toLocaleString = function() {
						return this.getFullYear() + "-"
								+ ((this.getMonth() + 1) < 10 ? "0" + (this.getMonth() + 1) : this.getMonth() + 1) + "-"
								+ (this.getDate() < 10 ? "0" + this.getDate() : this.getDate())
								+ "<br> " + (this.getHours()<10?"0"+this.getHours():this.getHours())+ ":" 
								+ (this.getMinutes()<10?"0"+this.getMinutes():this.getMinutes()) + ":"
								+ (this.getSeconds()<10?"0"+this.getSeconds():this.getSeconds());
					};
			       	$.ajax({
						type : "post",
						url : '../event/getProcess.do',
						data : {
							"executionId" : executionId
						},
						success : function(rs) {
							if (rs.success) {
								var pdetail = '';
								
								//第一个投诉节点start
								pdetail += '<div class="timeline-item">';
								pdetail += '<div class="col-md-3 date">'
										+ '<i class="fa fa-clock-o"></i> '
										+ '<p style="padding-right:25px;">'
										+ '<p style="padding-right:25px;">'
										+ ( new Date(rowData.complainId.createDate) ).toLocaleString() +'</p></div>'
										+ '<div class="col-md-9 content">'
										+ '<p style="padding-left:15px;" class="m-b-xs">'
										+ '阶段'
										+ '<strong>【'
										+ '公众投诉'
										+ '】</strong> 投诉人姓名:'
										+ rowData.complainId.userName
										+ '</p>'
										+ '<p style="padding-left:15px;" ><span style="color:blue">投诉主题:</span>'
										+ rowData.complainId.title
										+ '</p></div>';
								pdetail += '</div>';
								//第一个投诉节点end
								
								for (var index = 0; index < rs.result.data.length; index++) {
									var tips = '阶段';
									pdetail += '<div class="timeline-item">';
									pdetail += '<div class="col-md-3 date">'
											+ '<i class="fa fa-clock-o"></i> '
											+ '<p style="padding-right:25px;">'
											+ '<p style="padding-right:25px;">'
											+ ( new Date(rs.result.data[index].endDate) ).toLocaleString()+'</p></div>'
											+ '<div class="col-md-9 content">'
											+ '<p style="padding-left:15px;" class="m-b-xs">'
											+ tips
											+ '<strong>【'
											+ rs.result.data[index].stage
											+ '】</strong> 操作人:'
											+ rs.result.data[index].userName
											+ '</p>'
											+ '<p style="padding-left:15px;" ><span style="color:blue">意见:</span>'
											+ rs.result.data[index].remark
											+ '</p></div>';
									pdetail += '</div>';
								}
								$('#process-detail').html('');
								$('#process-detail').append(pdetail);
							} else {
								parent.$.messager.alert('系统提示', '查看办理过程失败！','warning');
							}
						}
					});
       },

	   /**
		 * 处理流程
		 */
	   showTransDetailEnd:function(executionId,rowData){
				   Date.prototype.toLocaleString = function() {
						return this.getFullYear() + "-"
								+ ((this.getMonth() + 1) < 10 ? "0" + (this.getMonth() + 1) : this.getMonth() + 1) + "-"
								+ (this.getDate() < 10 ? "0" + this.getDate() : this.getDate())
								+ "<br> " + (this.getHours()<10?"0"+this.getHours():this.getHours())+ ":" 
								+ (this.getMinutes()<10?"0"+this.getMinutes():this.getMinutes()) + ":"
								+ (this.getSeconds()<10?"0"+this.getSeconds():this.getSeconds());
					};
			       	$.ajax({
						type : "post",
						url : '../event/getProcess.do',
						data : {
							"executionId" : executionId
						},
						success : function(rs) {
							if (rs.success) {
								var pdetail = '';
								
								//第一个投诉节点start
								pdetail += '<div class="timeline-item">';
								pdetail += '<div class="col-md-3 date">'
										+ '<i class="fa fa-clock-o"></i> '
										+ '<p style="padding-right:25px;">'
										+ '<p style="padding-right:25px;">'
										+ ( new Date(rowData.complainId.createDate) ).toLocaleString() +'</p></div>'
										+ '<div class="col-md-9 content">'
										+ '<p style="padding-left:15px;" class="m-b-xs">'
										+ '阶段'
										+ '<strong>【'
										+ '公众投诉'
										+ '】</strong> 投诉人姓名:'
										+ rowData.complainId.userName
										+ '</p>'
										+ '<p style="padding-left:15px;" ><span style="color:blue">投诉主题:</span>'
										+ rowData.complainId.title
										+ '</p></div>';
								pdetail += '</div>';
								//第一个投诉节点end
								
								for (var index = 0; index < rs.result.data.length; index++) {
									var tips = '阶段';
									pdetail += '<div class="timeline-item">';
									pdetail += '<div class="col-md-3 date">'
											+ '<i class="fa fa-clock-o"></i> '
											+ '<p style="padding-right:25px;">'
											+ '<p style="padding-right:25px;">'
											+ ( new Date(rs.result.data[index].endDate) ).toLocaleString()+'</p></div>'
											+ '<div class="col-md-9 content">'
											+ '<p style="padding-left:15px;" class="m-b-xs">'
											+ tips
											+ '<strong>【'
											+ rs.result.data[index].stage
											+ '】</strong> 操作人:'
											+ rs.result.data[index].userName
											+ '</p>'
											+ '<p style="padding-left:15px;" ><span style="color:blue">意见:</span>'
											+ rs.result.data[index].remark
											+ '</p></div>';
									pdetail += '</div>';
								}
								$('#process-detailEnd').html('');
								$('#process-detailEnd').append(pdetail);
							} else {
								parent.$.messager.alert('系统提示', '查看办理过程失败！','warning');
							}
						}
					});
       },
	   
	   /**
	    * 完成前保证选中一条记录
	    */
		beforeComplete : function() {
			var selections = $('#dg').datagrid('getSelections');
			if (selections.length != 1) {
				layer.msg('请选择一条记录！');
			} else {
				Task.completeTask(selections[0].currentTask);
			}
		},
	   // 新增&编辑
	   completeTask:function(TaskId){
		   $.ajax({
				type : "post",
				url : '../role/getAllRoles.do',
				success : function(rs) {
					if (rs.success) {
						var _form =  '<form class="layui-form" action="" id="myform" style="padding-top:20px;">'
							   //隐藏域
							   +'<input class="layui-input" type="hidden" required lay-verify="required" id="taskId" value="'+TaskId+'">'
							   //办理内容
							   +'<div style="margin-top:20px;">'
								   +'<label class="layui-form-label" >办理反馈</label>'
								   +'<div class="layui-input-inline">'
								   +' <textarea class="layui-textarea" style="left:40px;"  cols="60" id="remark" name="remark"></textarea>'
								   +'</div>'
							   +'</div>'
							   //传递角色
						   	   +'<div style="margin-top:20px;">'
							   +'<label class="layui-form-label" >移交部门</label><div class="layui-input-inline"><select id="roleid">';
							   for(i=0;i<rs.result.data.length;i++){
								   _form += '<option value="'+rs.result.data[i].roleId+'">'+rs.result.data[i].roleName+'</option>';
							   }
//							   +'<option value="1">服务质量</option></select>'
							   _form +='</select></div></div></form>';
						   Task.layerIndex = layer.open({
												   type: 1,
												   title:'办理案件',
												   area: ['600px', '600px'], //宽高
												   content: _form,
												   btn:['确定','取消'],
												   yes:function(layero,index) {
													   if(null!=$("#remark").val()&&$("#remark").val()!=""){
														   Task.saveTask($("#taskId").val(),$("#remark").val(),$("#roleid").val());
													   }else{
														   layer.msg("请填写办理反馈！");
													   }
												   }
											  });
						   form.render();
					} else {
						parent.$.messager.alert('系统提示', '获取移交部门失败！','warning');
					}
				}
			});
	   },
	   
	   //保存
	   saveTask:function(taskid,remark,roleid){
		   $.ajax({
		        type: 'post',
		        url: '../event/completeTask.do?wfTaskId='+taskid+'&remark='+remark+'&roleId='+roleid,
		        success: function (data) {
		        	if(data.success){
		        		layer.close(Task.layerIndex);
			        	Task.initGrid();
		        	}else
		        		layer.msg(data.message);
		        }
		    });
	   },
	   
		
		/**
		 * 结案保证选中一条记录
		 */
	    afterComplete : function() {
		   var selections = $('#dg').datagrid('getSelections');
			if (selections.length != 1) {
				layer.msg('请选择一条记录！');
			} else {
				Task.endTask(selections[0].currentTask);
			}
		},
		endTask : function(TaskId) {
			var _form = ''
					// 隐藏域
					+ '<input class="layui-input" type="hidden" required lay-verify="required" id="taskId" value="'+TaskId+'">'
					// 反馈内容
					+ '<div style="margin-top:20px;">'
					+ '<label class="layui-form-label" >反馈信息</label><div class="layui-input-inline"><textarea class="layui-textarea" cols="60" id="feedback" name="feedback"></textarea></div>'
					+ '</div>';
			Task.layerIndex2 = layer.open({
				type : 1,
				title : '结束案件',
				area : [ '600px', '400px' ], // 宽高
				content : _form,
				btn : [ '确定', '取消' ],
				yes : function(layero, index) {
					if(null!=$("#feedback").val()&&$("#feedback").val()!=""){
						Task.saveEndTask($("#taskId").val(),$("#feedback").val());
					}else{
						layer.msg("请填写反馈信息！");
					}
				}
			});
		},
		saveEndTask : function(taskid, feedback) {
			$.ajax({
				type : 'post',
				url : '../event/completeTask.do?wfTaskId=' + taskid + '&feedBack=' + feedback + '&otype=1',
				success : function(data) {
					if (data.success) {
						layer.close(Task.layerIndex2);
						Task.initGrid();
					} else
						layer.msg(data.message);
				}
			});
		},
	   
	   // 初始化菜单树
	   initTree:function(TaskId){
		   $('#menuTree').tree({
				lines:true,
				checkbox:true,
				cascadeCheck:true,
				url:'/menu/getallTaskmenu.json?TaskId='+TaskId,
				onClick: function(node){
					var target =  node.target;
					var _node = $('#menuTree').tree('getParent',target);
				},
			    onLoadSuccess:function(node,data){
			           var t = $(this);
					   if(data){
						   $(data).each(function(index,d){
							   if(this.state == 'closed'){
							       t.tree('expandAll');
							   }
			                })
					   }
			    }
			})
	   }
	};

	//页面入口
	Task.init();
	
	}); 
})
//因为与layui模块化冲突，所以单独拿出来。
function traceImage(processId) {
	layer.open({
		type : 2,
		title : '流程图',
		area : [ '900px', '570px' ], //宽高
		content : '../event/traceImage.do?processInstanceId=' + processId,
		btn : [ '关闭' ]
	});
}

function openVoice(url){
	   var _Voice = '<video width="100%" height="99%" controls="controls" src="'+url+'"/> ';
	   var layerIndexVoice = layer.open({
		   type: 1,
		   title:'视频播放',
		   area: ['600px', '450px'], //宽高
		   content: _Voice,
		   btn:['关闭']
	  });
}
function openAudio(url){
	var _Audio = '<audio width="400" height="200" controls="controls" src="'+url+'" ></audio>';
	var layerIndexAudio = layer.open({
		type: 1,
		title:'音频播放',
		area: ['280px', '145px'], //宽高
		content: _Audio,
		btn:['关闭']
	});
}