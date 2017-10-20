<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>数据管理平台</title>
    <#include "/base/meta.ftl">
    <#include "/base/layui.ftl">
    <#include "/base/css4easyui.ftl">
    <link  href="/css/index.css" rel="stylesheet" type="text/css"  media="all">
    <link  href="/css/reset.css" rel="stylesheet" type="text/css"  media="all">
</head>
<body>
  <div id="left"></div>
<div id="right"></div>
<div id="main">
  <div class="title">
  <img src="img/title.png" alt="" onclick="start()">
</div>
  <div class="content">
    <div class="top">
      <div class="jiance_wrap wrap">
        <a href="desk.do?systemId=6&systemName=监测运行系统">
          <div id="jiance">
            <div class="img_jiance"></div>
            <div class="text">监测运行系统</div>
          </div>
        </a>
      </div>
      <div class="chengxin_wrap wrap">
        <a href="desk.do?systemId=4&systemName=诚信监管系统">
          <div id="chengxin">
            <div class="img_chengxin"></div>
            <div class="text">诚信监管系统</div>
          </div>
        </a>
      </div>
      <div class="zixun_wrap wrap">
        <a href="desk.do?systemId=1&systemName=咨询投诉受理系统">
          <div id="zixun">
            <div class="img_zixun"></div>
            <div class="text">咨询投诉受理系统</div>
          </div>
        </a>
      </div>

    </div>
    <div class="bottom">
      <div class="tongji_wrap wrap">
        <a href="desk.do?systemId=5&systemName=旅游统计系统">
          <div id="tongji">
            <div class="img_tongji"></div>
            <div class="text">旅游统计系统</div>
          </div>
        </a>
      </div>
      <div class="shuju_wrap wrap">
        <a href="desk.do?systemId=3&systemName=旅游数据对接系统">
          <div id="shuju">
            <div class="img_shuju"></div>
            <div class="text">旅游数据对接系统</div>
          </div>
        </a>
      </div>
      <div class="pingjia_wrap wrap">
        <a href="desk.do?systemId=2&systemName=涉旅企业评价系统">
          <div id="pingjia">
            <div class="img_pingjia"></div>
            <div class="text">涉旅企业评价系统</div>
          </div>
        </a>
      </div>
    </div>
  </div>
</div>
  <#include "/base/bottom.ftl">
  <#include "/base/js4easyui.ftl">
  <script type="text/javascript">
    function start(){
       var sock = new SockJS("/endpointChat");
       var stomp = Stomp.over(sock);
    }
  </script>
</body>
</html>