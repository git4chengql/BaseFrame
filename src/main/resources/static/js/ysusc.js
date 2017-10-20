//=========================================
/**
 * date:2017-3-22
 * author:chengql
 * desc:公共js方法
 */
//==========================================

/**************Layer Begin*****************/
/**
 * title:标题
 * maxmin:开启最大化最小化按钮
 * url:地址
 */
$.openInFrame=function(title,maxmin,url){
	layui.use('layer', function(){
		  var layer = layui.layer;
		  layer.open({
				type : 2,
				title :  [title, 'font-size:9px;'] || 'YsuSc',
				shadeClose : true,
				shade : false,
				offset : [ '70px', '0px' ],
				maxmin : maxmin || false,
				id:title,
				anim:-1,
				area : [ '100%', '100%' ],
				content : url || 'http://www.weibo.com'
			});
		}); 
};

$.openTab=function(url){
	layer.tab({
		  area: ['600px', '300px'],
		  type : 2,
		  tab: [{
		    title: '待办案件', 
		    content: 'http://www.baidu.com'
		  }, {
		    title: '已办案件', 
		    content: '内容2'
		  }, {
		    title: '待核查', 
		    content: '内容3'
		  }]
		});
}