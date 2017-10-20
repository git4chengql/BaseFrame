var tab;
layui.config({
    base: '/js/layui/',
    version: new Date().getTime()
}).use(['element', 'layer', 'navbar', 'tab'], function () {
    var element = layui.element(),
        $ = layui.jquery,
        layer = layui.layer,
        navbar = layui.navbar();
    
    tab = layui.tab({
        elem: '.admin-nav-card' //设置选项卡容器
        ,
        contextMenu: true,
        onSwitch: function (data) {
      //      console.log(data.id); //当前Tab的Id
      //      console.log(data.index); //得到当前Tab的所在下标
      //      console.log(data.elem); //得到当前的Tab大容器

      //      console.log(tab.getCurrentTabId())
        }
    });
    
    //iframe自适应
    $(window).on('resize', function () {
        var $content = $('.admin-nav-card .layui-tab-content');
        $content.height($(this).height() - 147);
        $content.find('iframe').each(function () {
            $(this).height($content.height());
        });
    }).resize();
    
    if($('.ysusoft:first').attr('menuId')!=undefined){
    	 //设置navbar
        navbar.set({
            spreadOne: true,
            elem: '#admin-navbar-side',
            cached: false,
    		url: '/menu/getmenubyparent.json?parentId='+$('.ysusoft:first').attr('menuId')
        });
        //渲染navbar
        navbar.render();
        //监听点击事件
        navbar.on('click(side)', function (data) {
            tab.tabAdd(data.field);
        });
    }
   
    $('.admin-side-toggle').on('click', function () {
        var sideWidth = $('#admin-side').width();
        if (sideWidth === 200) {
            $('#admin-body').animate({
                left: '0'
            }); //admin-footer
            $('#admin-footer').animate({
                left: '0'
            });
            $('#admin-side').animate({
                width: '0'
            });
        } else {
            $('#admin-body').animate({
                left: '200px'
            });
            $('#admin-footer').animate({
                left: '200px'
            });
            $('#admin-side').animate({
                width: '200px'
            });
        }
    });
    $('.admin-side-full').on('click', function () {
        var docElm = document.documentElement;
        //W3C  
        if (docElm.requestFullscreen) {
            docElm.requestFullscreen();
        }
        //FireFox  
        else if (docElm.mozRequestFullScreen) {
            docElm.mozRequestFullScreen();
        }
        //Chrome等  
        else if (docElm.webkitRequestFullScreen) {
            docElm.webkitRequestFullScreen();
        }
        //IE11
        else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        }
        layer.msg('按Esc即可退出全屏');
    });



    //锁屏
    $(document).on('keydown', function () {
        var e = window.event;
        if (e.keyCode === 76 && e.altKey) {
            lock($, layer);
        }
    });
    $('#lock').on('click', function () {
        lock($, layer);
    });
    
    $('.ysusoft').on('click',function(){
    	navbar.set({
            spreadOne: true,
            elem: '#admin-navbar-side',
            cached: false,
            url: '/menu/getmenubyparent.json?parentId='+$(this).attr('menuId')
        });
  /*  	if($(this).attr('menuId')==44){
    		var obj={href: "/knowledge/index.html", icon: "fa-meetup", title: "知识库"};
    		tab.tabAdd(obj);
    		//$("#main").attr("src","/knowledge/index.html");
    	}*/
    	navbar.render();
    	navbar.on('click(side)', function (data) {
    		if(data.field.href!='null'&&data.field.href!=null)
              tab.tabAdd(data.field);
        });
    });
    

    //手机设备的简单适配
    var treeMobile = $('.site-tree-mobile'),
        shadeMobile = $('.site-mobile-shade');
    treeMobile.on('click', function () {
        $('body').addClass('site-mobile');
    });
    shadeMobile.on('click', function () {
        $('body').removeClass('site-mobile');
    });
    
    
    $('#switch').hover(function(){
        $('#switch').unbind('click');
    }, function(){
    	$('body').bind('click', function(e){
    		var target = $(e.target);
    	    // 如果#overlay或者#btn下面还有子元素，可使用
    	    // !target.is('#btn *') && !target.is('#overlay *')
    	    if(!target.is('#clicka') && !target.is('#switch *')) {
    	       if ( $('#switch').is(':visible') ) {  
    	    	   $('#switch').empty();                                               
    	       }
    	    }
    	}); 
    });
    
});

var isShowLock = false;
function lock($, layer) {
    if (isShowLock)
        return;
    //自定页
    layer.open({
        title: false,
        type: 1,
        closeBtn: 0,
        anim: 6,
        content: $('#lock-temp').html(),
        shade: [0.9, '#393D49'],
        success: function (layero, lockIndex) {
            isShowLock = true;
            //给显示用户名赋值
            layero.find('div#lockUserName').text('管理员');
            layero.find('input[name=lockPwd]').on('focus', function () {
                var $this = $(this);
                if ($this.val() === '输入密码解锁..') {
                    $this.val('').attr('type', 'password');
                }
            })
                .on('blur', function () {
                    var $this = $(this);
                    if ($this.val() === '' || $this.length === 0) {
                        $this.attr('type', 'text').val('输入密码解锁..');
                    }
                });
            //在此处可以写一个请求到服务端删除相关身份认证，因为考虑到如果浏览器被强制刷新的时候，身份验证还存在的情况			
            //do something...
            //e.g. 
			/*
			 $.post(url,params,callback,'json');
			 */
            //绑定解锁按钮的点击事件
            layero.find('button#unlock').on('click', function () {
                var $lockBox = $('div#lock-box');

                var userName = $lockBox.find('div#lockUserName').text();
                var pwd = $lockBox.find('input[name=lockPwd]').val();
                if (pwd === '输入密码解锁..' || pwd.length === 0) {
                    layer.msg('请输入密码..', {
                        icon: 2,
                        time: 1000
                    });
                    return;
                }
                unlock(userName, pwd);
            });
			/**
			 * 解锁操作方法
			 * @param {String} 用户名
			 * @param {String} 密码
			 */
            var unlock = function (un, pwd) {
                //这里可以使用ajax方法解锁
				/*$.post('api/xx',{username:un,password:pwd},function(data){
				 	//验证成功
					if(data.success){
						//关闭锁屏层
						layer.close(lockIndex);
					}else{
						layer.msg('密码输入错误..',{icon:2,time:1000});
					}					
				},'json');
				*/
                isShowLock = false;
                //演示：默认输入密码都算成功
                //关闭锁屏层
                layer.close(lockIndex);
            };
        }
    });
};


function switchMenu(){
	var html='';
	html='<div class="sys-change">'+
			'<ul class="sys-block">'+
    		'<li class="sys-bar a"><img src="/images/menu/data.png" onclick="switchsys();"/></li>'+
    		'<li class="sys-bar b"><img src="/images/menu/tour.png" /></li>'+
    		'<li class="sys-bar c"><img src="/images/menu/belif.png" /></li>'+
    		'<li class="sys-bar d"><img src="/images/menu/Complain.png" /></li>'+
    		'<li class="sys-bar e"><img src="/images/menu/eval.png" /></li>'+
    	'</ul>'+
'</div></div>';
	$("#switch").append(html); 
};

function switchsys(){
	alert("切换系统");
	$('#switch').empty();
};