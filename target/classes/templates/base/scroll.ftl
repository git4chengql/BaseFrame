<link  href="/css/scroll.css" rel="stylesheet" type="text/css"  media="all">

<script src="/js/plugins/scrollable.js"></script>

<script type="text/javascript">
$(function(){
	$("#wizard").scrollable({
		onSeek: function(event,i){
			$("#status li").removeClass("active").eq(i).addClass("active");
		},
	});
});
</script>
