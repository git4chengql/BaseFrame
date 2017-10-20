$(function(){
	layui.use(['layer'], function(){
	   var layer = layui.layer,
	/**
	 * 系统登录
	 */
	LOGIN = {
		
		/**
		 * 初始化
		 */
	    init:function(){
			LOGIN.bindUI();
		},
		
		/**
		 * 事件绑定
		 */
		bindUI:function(){
			$('#userName').focus();
			
			$('#userName').keydown(function(e){
				if(e.keyCode==13&&$('#userName').val()!=""){
					$("#pwd").focus();
				}else if(e.keyCode==13&&$('#userName').val()==""){
					layer.msg('用户名不能为空');
				}
			});
			
			$('#pwd').keydown(function(e){
				if(e.keyCode==13){
					 if($('#pwd').val()!=""){
						 LOGIN.checkLogin();
					 }else{
						 layer.msg('密码不能为空');
					 }
				}
			});
			
			$('.subbtn').click(function(){
				LOGIN.checkLogin();
			})
		},
		
		/**
		 * 登录检测
		 */
		checkLogin:function(){
			var userName = $('#userName').val();
			var pwd = $.md5($('#pwd').val());
			if(userName!=''){
				LOGIN.doLogin(userName,pwd);
			}else if(userName==''){
				layer.msg('用户名不能为空');
			}else if(pwd==''){
				layer.msg('密码不能为空');
			}
			return;
		},
		
		/**
		 * 登录
		 */
		doLogin:function(userName,pwd){
			$.ajax({
				  type:"POST",
				  url:"user/login.do"+'?loginName='+userName+'&password='+pwd,
				  success:function(msg){
				    if(msg.success){
				    	 window.location.href='syslist.do';
				    }else{
				    	layer.msg('登录失败！');
				    }
				  },
				  error:function(e){
					  layer.msg('登录失败！');
				  }
			    })
		}
	}
	LOGIN.init();
  })
})