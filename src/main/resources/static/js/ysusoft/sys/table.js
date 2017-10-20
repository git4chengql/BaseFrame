(function(_this){
	layui.use(['form','layer'], function(){
		   var form = layui.form()
		   ,layer = layui.layer
		   
	Table={
		init:function(){
			Table.bindUI();	
			Table.initGrid();
		},
			
		/**
		 * 绑定事件
		 */
		bindUI:function(){
			
			/**
			 * 绑定新增列按钮
			 */
			$('#insertBtn').bind('click',function(){
        	    Table.insertRow();
        	});

       	     /**
       	      * 绑定保存按钮
       	      */
       	    $('#saveBtn').bind('click',function(){
				Table.confirmRow();
			});
       	    
			/**
			 * 绑定生成代码功能按钮
			 */
		   $('#generatBtn').bind('click',function(){
				var row = $('#dg').datagrid('getRows');
				if(row.length==0){
					 layer.msg('请先增加列！');
				}else{
				var index = layer.open({
					   type: 1,
					   title:'模板',
					   area: ['700px', '300px'], //宽高
					   content: $('#popContainer').html(),
				    });
				 Table.formSubmit(index);
				}
			});

		},

		/**
		 * 保存列
		 */
		confirmRow:function(){
			var row = $('#dg').datagrid('getRows');
			if(row.length==0){
				 layer.msg('请先增加列！');
			}
			for(var index = 0; index <row.length; index++){
				$('#dg').datagrid('endEdit',index);
			}
			$('#dg').datagrid('clearSelections');
		},

		/**
		 * 插入列
		 */
		insertRow:function(table_name){
			$('#dg').datagrid('appendRow',{});
			var editIndex = $('#dg').datagrid('getRows').length-1;
			$('#dg').datagrid('selectRow', editIndex)
				.datagrid('beginEdit', editIndex);
		},

		
		 /**
		  * 确定生成按钮
		  * @param index
		  */
		formSubmit:function(index){
		 form.on('submit(demo1)', function(data){
	   		var modelName = $('#modelText').val();
	   		var rootText = $('#rootText').val();
	   		var descText = $('#descText').val();
	   		var tableName = $('#tableText').val();
	   		var rowChoose = $('#dg').datagrid('getRows');
	   		var item=[];
	   		if(rowChoose.length!=0){
	   				item=rowChoose;
	   		}
	   		$.ajax({
					type : "post",
					url : 'getTableColumn.json',
					dataType: "json", 
					data : {
						modelName : modelName,
						rootText:rootText,
						descText:descText,
						items:JSON.stringify(item),
						tableName:tableName
					},
					success : function(rs) {
						if(rs == true){
 						  layer.msg('操作成功！');
 						  layer.close(index);
 						 $("#dg").datagrid('loadData', []);
 					}
					
					},
					
				});
	   	 return false;
		 });
		  $('#closeBtn').click(function(){
				layer.close(index);
			});
		 
	   	},
		
      /**
       * 表格
       */
	  initGrid:function(){
			var  products = [
		        {data_type:'Integer',name:'整型'},
		        {data_type:'String',name:'字符型'},
		        {data_type:'Date',name:'日期类型'}
		    ];
			var _Grid=$('#dg').datagrid({
				pagination:false,
				rownumbers:true,
				fitColumns: true,
				singleSelect:false,
				fit:true,
				loadMsg:'请稍等,数据正在加载中……',
				columns:[
					         [
					          {field:'fid',title:'',width:40,align:'center',checkbox:true},
					          {field:'column_name',title:'列名(必填)',width:100,align:'center',editor:'textbox'},
					          {field:'column_comment',title:'注释',width:100,align:'center',editor:'textbox'},
					          {field:'data_type',title:'类型(必填)',width:100,align:'center',
					        	  formatter:function(value){
					        		  for(var i=0; i<products.length; i++){
					        			  if (products[i].data_type == value) return products[i].name;
					        		  }
					        		  return value;
					        	  },
					        	  editor:{
					        		  type:'combobox',
					        		  options:{
					        			  valueField:'data_type',
					        			  textField:'name',
					        			  data:products,
					        			  panelHeight:80,
					        			  required:false
					        		  }
					        	  }}
					        	 ]
					         ]
				});
			}
	  //end 	
	   	
	}

	Table.init();
	});
})(this);
