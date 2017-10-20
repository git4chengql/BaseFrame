$(function(){
	User = {
	   
	   layerIndex:0,
	   //页面入口函数
	   init:function(){
		   User.initGrid();
		   User.bindUI();
		   $(window).resize(function(){
			   $('#dg').datagrid('resize', {
				   width:function(){
					   return window.innerWidth;
				   }
			   });
		   });
	   },
	   
	   //绑定事件
	   bindUI:function(){
		   $('#addBtn').click(function(){
			   User.updateUser();
		   });
		   
		   $('#editBtn').click(function(){
			   User.editUser();
		   });
		   
		   $('#queryBtn').click(function(){
			   User.queryUser();
		   })
		   
		   $('#delBtn').click(function(){
			   User.delUser();
		   })
	   },
	   
	   //初始化表格
	   initGrid:function(){
		   var _Grid =  $('#dg').datagrid({
				    url:'allUser.do',
				    pagination:true,
				    singleSelect:true,
				    queryParams: {
								 pageSize:20,
								 userName:$('#userName').val()
						         },
					loadMsg:'请稍等,数据正在加载中……',
					emptyMsg:'无记录',
					rownumbers:true,
				    width:window.innerWidth,
				    height:window.innerHeight-79,
				    striped:true,
				    columns:[[
						{field:'userId',title:'代码',width:100,align:'center',checkbox:true},
						{field:'loginName',title:'登录名称',width:200,align:'center'},
						{field:'userName',title:'用户名称',width:200,align:'center'},
						{field:'price',title:'操作',width:400,align:'center',formatter:function(row,data){
						   return ' <i class="layui-icon" title="权限分配" onclick="javascript:User.updatePermission('+data.userId+')">&#xe614;</i>'+
						          ' <i class="layui-icon" title="编辑" onclick="javascript:User.updateUser('+data.userId+')">&#xe642;</i>';
						}}
				    ]]
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
	   
	   //查询
	   queryUser:function(){
		   User.initGrid();
	   },
	   
	   //新增&编辑
	   updateUser:function(UserId){
		   User.layerIndex = layer.open({
								   type: 2,
								   title:'用户管理',
								   area: ['600px', '400px'], //宽高
								   content: '/user/getUserForAddOrUpdate.do?userId='+(UserId==null?'':UserId),
								   btn:['确定','取消'],
								   yes:function(layero,index) {
									   var childFrame =  document.getElementById('layui-layer-iframe'+layero).contentWindow;  
									   var param = childFrame.getParam();
									   if(param!=false){
										   User.saveUser(param);
									   }
								   }
							  });
	   },
	   
	   editUser:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length==1){
			   User.updateUser(selections[0].userId);
			}else{
				layer.msg('请选择一条记录');
			}
	   },
	   
	   //保存
	   saveUser:function(param){
		   $.ajax({
		        type: 'post',
		        url: 'addUser.do?'+param,
//		        url: 'addUser.do?UserName'+UserName+'&UserId='+UserId+'&LoginName='+LoginName+(Pwd==''?'':'&password='+Pwd),
		        success: function (data) {
		        	if(data.success){
		        		layer.close(User.layerIndex);
			        	User.initGrid();
		        	}else
		        		layer.msg(data.message);
		        }
		    });
	   },
	   
	   //删除
	   delUser:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length==1){
			  User.layerIndex = layer.confirm('确定删除？', {
				                   title:'提示',
								   btn: ['确定','取消']
								 }, function(){
									 User.delFun(selections[0].userId);
							       }
							   );
			}else{
				layer.msg('请选择一条记录');
			}
	   },
	   
	   //删除
	   delFun:function(UserId){
		   $.ajax({
		        type: 'post',
		        url: 'deleteUser.do?userId='+UserId,
		        success: function (data) {
		        	if(data.success){
		        		layer.close(User.layerIndex);
			        	User.initGrid();
		        	}else
		        		layer.msg(data.message);
		        }
		    });
	   },
	   
	   /**
	    * 更新勾选菜单权限
	    * @param UserId
	    */
	   updatePermission:function(UserId){
		   User.layerIndex = layer.open({
			   type: 1,
			   title:'菜单权限管理',
			   area: ['200px', '460px'], //宽高
			   content: $('#treeContainer').html(),
			   btn:['确定','取消'],
			   yes:function(index){
				   var nodes = $('#menuTree').tree('getChecked');
				   var s = [];
	                for (var i = 0; i < nodes.length; i++) {
	                	s.push(parseInt(nodes[i].id));
	                }
	               if(s.length==0){
	            	   layer.msg('请勾选菜单！');  
	               }else{
	            	   $.ajax({
					        type: 'post',
					        url: 'saveUserMenu.do?menus='+s,
					        data:{"menus":s,"userId":UserId},
					        success: function (data) {
					        	if(data.success==true){
					        		$('#dg').datagrid('reload');
					        		layer.close(User.layerIndex);
					        		layer.msg(data.message);
					        	}else
					        		layer.msg(data.message);
					        }
					    });  
	               }
			   },
			   success:function(layero, index){
				   //初始化菜单树
				   User.initTree(UserId);
			   }
		  });
	   },
	   
	   //初始化菜单树
	   initTree:function(UserId){
		   $('#menuTree').tree({
				lines:true,
				checkbox:true,
				cascadeCheck:true,
				url:'/menu/getallusermenu.json?userId='+UserId,
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
	}
	
	//页面入口
	User.init();
	
})