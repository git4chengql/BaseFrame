$(function(){
	ChangeDesk = {
	   
	   layerIndex:0,
	   complain:null,
	   complaintype:null,
	   init:function(){
		  ChangeDesk.bindUI();
		  ChangeDesk.getAllSum();
		 /* ChangeDesk.initEcharts();*/
		  ChangeDesk.loadData();
		  
	   },
	   
	   bindUI:function(){
		   
	   },
	   
	   /**
	    * 
	    */
	   initEcharts:function(){
		   ChangeDesk.complain = echarts.init($('#complain')[0]);
		   ChangeDesk.complain.showLoading();
		   ChangeDesk.complaintype = echarts.init($('#complaintype')[0]);
		   ChangeDesk.complaintype.showLoading();
	   },
	   
	   loadData:function(){
		   var value = new  Array();
		   var barvalue = new  Array();
		   $.ajax({
		        type: 'post',
		        url: 'changedesk/getComplainSum.do',
		        success: function (data) {
					for(var index = 0; index <data.counts.length; index++){
						value.push(data.counts[index].comcount);
					}
					for(var index = 0; index <data.types.length; index++){
						barvalue.push(data.types[index].complainTypeId);
					}
					ChangeDesk.renderData(value);
					ChangeDesk.renderBayData(barvalue);
		        }
		    });
		 
	   },
	   
	   /**
	    * 加载柱状图
	    */
	   renderBayData:function(value){
		   var myChart = echarts.init(document.getElementById('complaintype'));
		   option = {
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    legend: {
				        data:['投诉种类']
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis : [
				        {
				            type : 'category',
				            data : ['服务质量','卫生状况','欺客宰客','门票价格','其他']
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'投诉种类',
				            type:'bar',
				            data:value
				        }
				        
				    ]
				};
		   myChart.setOption(option);
	   },
	   
	   
	   /**
	    * 加载折线图
	    * @param count
	    */
	   renderData:function(count){
		   var myChart = echarts.init(document.getElementById('complain'));
		   option = {
				    title: {
				        text: ''
				    },
				    tooltip : {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'cross',
				            label: {
				                backgroundColor: '#6a7985'
				            }
				        }
				    },
				    legend: {
				    	 data:['投诉统计']
				    },
				    
				    grid: {  
	                    y2: 140  
	                },  
	                xAxis: {  
	                    type: 'category',  
	                    boundaryGap: [0, 0.01],  
	                    data:  ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
	                    axisLabel:{  
	                         interval:0,//横轴信息全部显示  
	                         rotate:-30,//-30度角倾斜显示  
	                    }  
	                },  
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'投诉统计',
				            type:'line',
				            stack: '总量',
				            areaStyle: {normal: {}},
				            data:count
				        }
				    ]
				};
		   myChart.setOption(option);
		   
	   },
	   
	   
	   /**
	    * 获取五种类型的数据
	    */
	  getAllSum:function(){
		   $.ajax({
		        type: 'post',
		        url: 'changedesk/getAllSum.do',
		        success: function (data) {
		        	$('#shopping').html(data.desk.shopping);
		        	$('#spot').html(data.desk.spot);
		        	$('#travel').html(data.desk.travel);
		        	$('#hotel').html(data.desk.hotel);
		        	$('#guides').html(data.desk.guides);
		        }
		    });
		  
	  },
	   
	   
	   
	   
	   
	}
	
	//页面入口
	ChangeDesk.init();
	
})