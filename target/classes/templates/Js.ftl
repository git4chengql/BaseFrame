${'$'?html}(function(){

	${modelName?cap_first} = {
	   layerIndex:0,
	   init:function(){
		   ${modelName?cap_first}.bindUI();
		   ${modelName?cap_first}.initGrid();
		    $(window).resize(function(){
			   $('#dg').datagrid('resize', {
				   width:function(){
					   return window.innerWidth;
				   }
			   });
		   });
	   },
	   
	   /**
	    * 绑定事件
	    */
	   bindUI:function(){
	   
	   	  /**
		    *绑定查询按钮
		    */
		 $('#searchBtn').click(function(){
		   ${modelName?cap_first}.reloadGrid();
		 });
		 
		  /**
		    * 绑定新增按钮
		    */
		 $('#addBtn').click(function(){
		 
		 });
		 
		  /**
		    * 绑定编辑按钮
		    */
		 $('#editBtn').click(function(){
		 	${modelName?cap_first}.before${modelName?cap_first}();
		 });
		 
		  /**
		    * 绑定删除按钮
		    */
		 $('#delBtn').click(function(){
		    ${modelName?cap_first}.delete${modelName?cap_first}();
		 });
		 
		 
	   },
	   
	   
	   /**
	    * 清空文本框的值
	    */
	   clearInput:function(){
		   $('input').val("");  
	   },
	   
	   /**
	    * 初始化表格
	    */
	   initGrid:function(){
		   $("#dg").jqGrid({
				type : "post",
				url:"${modelName}/******.do",
				 pagination:true,
				 selectOnCheck:true,
				    /*singleSelect:true,*/
				 queryParams: {
								 pageSize:20
						       },
				loadMsg:'请稍等,数据正在加载中……',
				emptyMsg:'无记录',
				rownumbers:true,
				width:window.innerWidth,
				height:window.innerHeight-79,
				striped:true,
				colNames:[[
					<#list fltem as ftm>
					{name:"${ftm.fieldName}",align:"center",width:50},
					</#list>
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
		* 重新加载表格
		*/
		reloadGrid:function(){
			${modelName?cap_first}.initGrid();
		},
	   
	   /**
	    * 增加
	    */
	   add${modelName?cap_first}:function(){
		   
	   },
	   
	  /** 
	   * 编辑前保证选中一条记录
	   */
	   before${modelName?cap_first}:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length!=1){
			   layer.msg('请选择一条记录！');
		   }else{
			   ${modelName?cap_first}.update${modelName?cap_first}();
		   }
	   },
	   
	   /**
	    * 更新
	    */
	   update${modelName?cap_first}:function(){
		   
	   },
	   
	   /**
	    * 删除
	    */
	   delete${modelName?cap_first}:function(){
		   var selections = $('#dg').datagrid('getSelections');
		   if(selections.length>=1){
			    var ${modelName}list1 = [];
				for(var index = 0; index <selections.length; index++){
					${modelName}list1.push(parseInt(selections[index].主键id));
				}
			  ${modelName?cap_first}.layerIndex = layer.confirm('确定删除？', {
	                   title:'提示',
					   btn: ['确定','取消']
					 }, function(){
						 $.ajax({
						        type: 'post',
						        url: '*****.do',
						        traditional: true,
						        data:{${modelName}list:${modelName}list1},
						        success: function (data) {
						        	if(data.success==true){
						        		$('#dg').datagrid('reload');
						        		layer.close(${modelName?cap_first}.layerIndex);
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
	   }
	   
	   
	   
	   
	}
	
	${modelName?cap_first}.init();
});