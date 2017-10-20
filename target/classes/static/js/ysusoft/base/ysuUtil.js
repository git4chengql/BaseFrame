/**
 * 公共js
 */
$.extend({
	
	/**
	 * 弹区划树窗口
	 */
	openBaseCodeWinForTree:function(_obj){
		var html='<div><ul class="easyui-tree" id="regionTree"></ul></div>';
		var index = layer.open({
		   type: 1, 
		   title:'请选择',
		   area: ['280px', '460px'],
		   content: html,
		   btn:['确定','取消'],
		   yes:function(index){
			   var text='';
			   var node = $('#regionTree').tree('getSelected');
			   if(node.level==0) text = node.text;
			   else if(node.level==1) {
				   var _node2 = $('#regionTree').tree('getParent',node.target);
				   text = _node2.text+'-'+node.text;
			   }else if(node.level==2){
				   var _node2 = $('#regionTree').tree('getParent',node.target);
				   var _node1 = $('#regionTree').tree('getRoot', node.target);
				   text = _node1.text+'-'+_node2.text+'-'+node.text;
			   }
			   $('#'+_obj).val(text);
			   $("#"+_obj).attr('realValue',node.id);
			   layer.close(index);
		   },
		   success:function(layero, index){
			   $.initTree();
		   }
	   });
	},
	
	//初始化区划树
	initTree:function(){
		   $('#regionTree').tree({
				lines:true,
				url:'/region/getregiontree.do',
				onClick: function(node){
					var target =  node.target;
					var _node = $('#regionTree').tree('getParent',target);
				},
			    onLoadSuccess:function(node,data){
		           var t = $(this);
				   if(data){
					   $(data).each(function(index,d){
						   if(this.state == 'closed' && this.level <1){
						       t.tree('expandAll');
						   }
		               });
				   }
			    }
			});
	   },
	   
	  /**
	   * 弹表格窗口，民族
	   */
	   openBaseCodeNation:function(_obj){
		   var html='<div><table id="nationdg"></table></div>';
			var index = layer.open({
			   type: 1, 
			   title:'请选择',
			   area: ['500px', '460px'],
			   content: html,
			   btn:['确定','取消'],
			   yes:function(index){
				   var row = $('#nationdg').datagrid('getSelected');
				   $('#'+_obj).val(row.nationName);
				   $("#"+_obj).attr('realValue',row.nationId);
				   layer.close(index);
			   },
			   success:function(layero, index){
				   $.initNation();
			   }
		   }); 
	   },
	   
	   initNation:function(){
		   var _Grid =  $('#nationdg').datagrid({
			    url:'/nation/getnation.do',
			    pagination:false,
			    singleSelect:true,
			    queryParams: {
			    },
				loadMsg:'请稍等,数据正在加载中……',
				emptyMsg:'无记录',
				rownumbers:true,
			    width:500,
			    height:350,
			    striped:true,
			    columns:[[
					{field:'nationId',title:'主键',width:100,align:'center',checkbox:true},
					{field:'nationCode',title:'编码',width:100,align:'center'},
					{field:'nationName',title:'名称',width:200,align:'center'},
			    ]]
			});
	   },
	   
	  /**
	   * 弹表格窗口，学历
	   */
	   openBaseCodeEdu:function(_obj){
		   var html='<div><table id="edudg"></table></div>';
			var index = layer.open({
			   type: 1, 
			   title:'请选择',
			   area: ['500px', '460px'],
			   content: html,
			   btn:['确定','取消'],
			   yes:function(index){
				   var row = $('#edudg').datagrid('getSelected');
				   $('#'+_obj).val(row.eduName);
				   $("#"+_obj).attr('realValue',row.eduId);
				   layer.close(index);
			   },
			   success:function(layero, index){
				   $.initEdu();
			   }
		   }); 
	   },
	   
	   initEdu:function(){
		   var _Grid =  $('#edudg').datagrid({
			    url:'/edu/getedu.do',
			    pagination:false,
			    singleSelect:true,
			    queryParams: {
			    },
				loadMsg:'请稍等,数据正在加载中……',
				emptyMsg:'无记录',
				rownumbers:true,
			    width:500,
			    height:350,
			    striped:true,
			    columns:[[
					{field:'eduId',title:'主键',width:100,align:'center',checkbox:true},
					{field:'eduCode',title:'编码',width:100,align:'center'},
					{field:'eduName',title:'名称',width:200,align:'center'},
			    ]]
			});
	   },
});

$(function(){
	/**
	 * 图片加载失败时加载默认图片
	 */
	$("img").error(function () {
	    $(this).attr("src", "/images/placeholder.png");
	});	
});