	layui.use(['form','layer','laypage','laydate'], function(){
		var form = layui.form(),
		layer = layui.layer,
		laydate = layui.laydate;
		
		
	Menu = {
	   
	   //页面入口函数
	   init:function(){
		   Menu.initMenuTree();
		   Menu.bindUI();
	   },
	   
	   initMenuTree:function(){
		  $('#treeC').height(window.innerHeight-88);
          $('#formDiv').width(window.innerWidth-340);
          $('#formDiv').height(window.innerHeight-108);
		  $('#menuTree').tree({
				lines:true,
				url:'/menu/getallmenu.json',
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
		               });
				    }
			    }
			});  
	   },
	   
	   //绑定事件
	   bindUI:function(){
		   
		   //新增一级菜单
		   $('#addFirstBtn').click(function(){
			   Menu.clearInput();
			   $('#formDiv').show();
			   $('#parentmenu').hide();
			   $('#title_xz').text("新增一级菜单");
			   $('#saveBtn').show();
			   $('#updateBtn').hide();
		   });
		   
		   //新增二级菜单
		   $('#addSecondBtn').click(function(){
			   var node = $('#menuTree').tree('getSelected');
			   if (node){
					if(node.level!=0){
						layer.msg('请选择一级菜单');
					}else{
						Menu.clearInput(); 
						$('#formDiv').show();
						$('#parentmenu').show();
						$('#title_xz').text("新增二级菜单");
						$('#parentMenuId').val(node.id);
						$('#parentMenuName').val(node.text);
						console.info(node);
						$('#saveBtn').show();
						$('#updateBtn').hide();
					}
				}else{
					layer.msg('请选择一级菜单');
				}
		   });
		   
		   //编辑
		   $('#editBtn').click(function(){
			   var node = $('#menuTree').tree('getSelected');
			   if (node){
				   $('#menuId').val(node.id);
				   $('#systemId').val(node.systemId);
				   form.render('select');
				   if(node.level==0){
					   $('#title_xz').text("编辑一级菜单");
					   $('#formDiv').show();
					   Menu.putIn(node.id);
				   }else if(node.level==1){
					   $('#title_xz').text("编辑二级菜单");
					   $('#formDiv').show();
					   $('#parentmenu').show();
					   Menu.putIn(node.id);
					   var _node = $('#menuTree').tree('getParent',node.target);
					   $('#parentMenuName').val(_node.text);
				   }
				   $('#saveBtn').hide();
				   $('#updateBtn').show();
			   }else{
				   layer.msg('请选择菜单');
			   }
		   });
		   
		   //保存，一级菜单、二级菜单
		   $('#saveBtn').click(function(){
			   var data = decodeURIComponent($('#myform').serialize(),true);
			   data +="&isFlag="+$("input[name='isFlag']:checked").val();
			   if($('#parentMenuId').val()=='' || $('#parentMenuId').val()==-1){
				   data += "&level=0";
			   }else{
				   data += "&level=1";
			   }
			   $.ajax({
			        type: 'post',
			        url: 'addmenu.do',
			        data : data,
			        success: function (data) {
			        	if(data.success){
			        		layer.msg(data.message);
			        		Menu.clearInput();
			        		$('#menuTree').tree('reload');
			        	}else
			        		layer.msg(data.message);
			        }
			    });
		   });
		   
		   //更新，一级菜单、二级菜单
		   $('#updateBtn').click(function(){
			   var systemId = $('#systemId').val();
			   var parentMenuId = $('#parentMenuId').val();
			   var title = $('#title').val();
			   var href = $('#href').val();
			   var icon = $('#icon').val();
			   var isFlag=$("input[name='isFlag']:checked").val();
			   var level='';
			   if($('#parentMenuId').val()=='' || $('#parentMenuId').val()==-1){
				   level = 0;
			   }else{
				   level = 1;
			   }
			   var menuId = $('#menuId').val();
			   $.ajax({
			        type: 'post',
			        url: 'updatemenu.json',
			        data : {'systemId':systemId,'parentMenuId':parentMenuId,'title':title,'href':href,icon:icon,isFlag:isFlag,level:level,menuId:menuId},
			        success: function (data) {
			        	if(data.success){
			        		layer.msg(data.message);
			        		Menu.clearInput();
			        		$('#menuTree').tree('reload');
			        		//	parent.location.reload();
			        	}else
			        		layer.msg(data.message);
			        }
			    });
		   });
		   
		   //删除
		   $('#delBtn').click(function(){
			   var node = $('#menuTree').tree('getSelected');
			   if (node){
				   Menu.delMenu(node.level,node.id);
			   }else{
				   layer.msg('请选择菜单');
			   }
		   });
		   
		   //清空
		   $('#clearBtn').click(function(){
			   Menu.clearInput();
		   });
	   },
	   
	   /**
	    * 取出选中菜单的信息并赋值
	    */
	   putIn:function(id){
		   $.ajax({
		        type: 'post',
		        url: 'getmenubyId.do?id='+id,
		        success: function (data) {
		        	if(data!=null){
		        		$('#parentMenuId').val(data.parentMenuId);
		        		$('#title').val(data.title);
		        		$('#href').val(data.href);
		        		$('#icon').val(data.icon);
		        		$("input[name='isFlag'][value='"+data.isFlag+"']").attr("checked",true);
		        	}
		        }
		    });
	   },
	   
	   /**
	    * 清空
	    */
	   clearInput:function(){
		   $("#menuId").val("");
		   $("#parentMenuId").val(-1);
		   $("#parentMenuName").val("");
		   $("#title").val("");
		   $("#href").val("");
		   $("#icon").val("");
		   $("input[name='isFlag'][value='0']").attr("checked",true); 
	   },
	   
	   /**
	    * 删除
	    */
	   delMenu:function(level,id){
		   if(level==0){
			   //删除一级菜单
			   layer.confirm('删除一级菜单会同时删除下属的二级菜单，是否删除？', {
			     btn: ['是','否'] 
			   }, function(){
				   $.ajax({
				        type: 'post',
				        url: 'delmenu.do',
				        data : {"menuId":id,"level":level},
				        success: function (data) {
				        	if(data.success){
				        		layer.msg(data.message);
				        		$('#menuTree').tree('reload');
				        	}else
				        		layer.msg(data.message);
				        }
				    });
			   }, function(){});
		   }else{
			   $.ajax({
			        type: 'post',
			        url: 'delmenu.do',
			        data : {"menuId":id,"level":level},
			        success: function (data) {
			        	if(data.success){
			        		layer.msg(data.message);
			        		$('#menuTree').tree('reload');
			        	}else
			        		layer.msg(data.message);
			        }
			    }); 
		   }
	   }
	   
	}; 
	
	//页面入口
	Menu.init();
	
});