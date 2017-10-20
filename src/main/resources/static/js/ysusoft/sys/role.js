$(function(){
	Role = {
	   
	   layerIndex:0,
	   //页面入口函数
	   init:function(){
		   Role.initGrid();
		   Role.bindUI();
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
		   
		   /**
		    * 绑定新增按钮
		    */
		   $('#addBtn').click(function(){
			   Role.updateRole();
		   });
		   
		   
		   /**
		    * 绑定编辑按钮
		    */
		   $('#editBtn').click(function(){
			   Role.beforeRole();
		   });
		   
		   /**
		    *绑定查询按钮
		    */
		   $('#queryBtn').click(function(){
			   Role.queryRole();
		   });
		   
		   
		   /**
		    * 绑定删除按钮
		    */
		   $('#delBtn').click(function(){
			   Role.delRole();
		   });
	   },
	   
	   /**
	    * 初始化表格
	    */
	   initGrid:function(){
		   var _Grid =  $('#dg').datagrid({
				    url:'allrole.do',
				    pagination:true,
				    selectOnCheck:true,
				    /*singleSelect:true,*/
				    queryParams: {
								 pageSize:20,
								 roleName:$('#roleName').val()
						         },
					loadMsg:'请稍等,数据正在加载中……',
					emptyMsg:'无记录',
					rownumbers:true,
				    width:window.innerWidth,
				    height:window.innerHeight-79,
				    striped:true,
				    columns:[[
						{field:'roleId',title:'代码',width:100,align:'center',checkbox:true},
						{field:'roleName',title:'名称',width:200,align:'center'},
						{field:'price',title:'操作',width:400,align:'center',formatter:function(row,data,rowIndex){
						   return ' <i class="layui-icon" title="权限分配" onclick="javascript:Role.updatePermission('+data.roleId+','+rowIndex+')">&#xe614;</i>'+
						          ' <i class="layui-icon" title="编辑" onclick="javascript:Role.updateRole('+data.roleId+',\''+data.roleName+'\')">&#xe642;</i>';
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
	   
	   /**
	    * 查询方法
	    */
	   queryRole:function(){
		   Role.initGrid();
	   },
	   
	   /**
	    * 新增&编辑
	    */
	   updateRole:function(roleId,roleName){
		   roleName=roleName||'';
		   var _form =  '<div style="margin-top:20px;"><label class="layui-form-label" >角色名称</label><div class="layui-input-inline"><input class="layui-input" type="text" required lay-verify="required" id="newRole" value="'+roleName+'"></div></div>';
		   Role.layerIndex = layer.open({
								   type: 1,
								   title:'角色管理',
								   area: ['420px', '160px'], //宽高
								   content: _form,
								   btn:['确定','取消'],
								   yes:function(index){
									   Role.saveRole(roleId||0,$('#newRole').val());
								   }
							  });
	   },
	   
	  /** 
	   * 编辑前保证选中一条记录
	   */
	   beforeRole:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length!=1){
			   layer.msg('请选择一条记录！');
		   }else{
			   Role.updateRole(selections[0].roleId,selections[0].roleName);
		   }
	   },
	   
	   /**
	    * 保存
	    */
	   saveRole:function(roleId,roleName){
		   $.ajax({
		        type: 'post',
		        url: 'addrole.do?roleName='+roleName+'&roleId='+roleId,
		        success: function (data) {
		        	if(data.success){
		        		layer.close(Role.layerIndex);
			        	Role.initGrid();
		        	}else
		        		layer.msg(data.message);
		        }
		    });
	   },
	   
	   /**
	    * 删除角色
	    */
	   delRole:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length>=1){
			    var roleIdslist1 = [];
				for(var index = 0; index <selections.length; index++){
					roleIdslist1.push(parseInt(selections[index].roleId));
				}
			  Role.layerIndex = layer.confirm('确定删除？', {
	                   title:'提示',
					   btn: ['确定','取消']
					 }, function(){
						 $.ajax({
						        type: 'post',
						        url: 'deleteRoleIds.do',
						        traditional: true,
						        data:{roleIdslist:roleIdslist1},
						        success: function (data) {
						        	if(data.success==true){
						        		$('#dg').datagrid('reload');
						        		layer.close(Role.layerIndex);
						        		layer.msg(data.message);
						        	}else
						        		layer.msg(data.message);
						        }
						    });
				       }
				   );
			}else{
				layer.msg('请至少选择一条记录！');
			}
	   
	   },
	   
	   /**
	    * 更新权限
	    */
	   updatePermission:function(roleId,index){
		 //打开
		   Role.layerIndex = layer.open({
			   type: 1,
			   title:'菜单权限管理',
			   area: ['200px', '460px'], //宽高
			   content: $('#treeContainer').html(),
			   btn:['确定','取消'],
			   yes:function(index){
				   var nodes = $('#menuTree').tree('getChecked');
				   var s = []
	                for (var i = 0; i < nodes.length; i++) {
	                	s.push(parseInt(nodes[i].id));
	                }
	               if(s.length==0){
	            	   layer.msg('请勾选菜单！');  
	               }else{
	            	   $.ajax({
					        type: 'post',
					        url: 'saveRoleMenu.do?menus='+s,
					        data:{"menus":s,"roleId":roleId},
					        success: function (data) {
					        	if(data.success==true){
					        		$('#dg').datagrid('reload');
					        		layer.close(Role.layerIndex);
					        		layer.msg(data.message);
					        	}else
					        		layer.msg(data.message);
					        }
					    });  
	               }
	               
			   },
			   success:function(layero, index){
				   //初始化菜单树
				   Role.initTree(roleId);
			   }
		  });
	   },
	   
	   /**
	    * 初始化菜单树
	    */
	   initTree:function(roleId){
		   $('#menuTree').tree({
				lines:true,
				checkbox:true,
				cascadeCheck:true,
				url:'/menu/getallrolemenu.json?roleId='+roleId,
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
	Role.init();
	
})