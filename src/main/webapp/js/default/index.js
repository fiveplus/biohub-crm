function get_url(url){
	window.location.href=url;
}
function search_focus(flag){
	var div = $("#search-div");
	if(flag){
		div.css("border","1px solid #4285f4");
	}else{
		div.css("border","1px solid #eee");
	}
}
$(document).ready(function(){
	var navH = $(".search-index").offset().top;
	//滚动条事件
	$(window).scroll(function(){
		//获取滚动条的滑动距离
		var scroH = $(this).scrollTop();
		//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
		if(scroH==0){
			$(".search-index").css({"position":"fixed","-webkit-box-shadow":"0 0 0"});
		}else{
			if(scroH>=navH){
				$(".search-index").css({"-webkit-box-shadow":"0 0 5px rgba(81, 203, 238, 1)"});
			}else if(scroH < navH){
				$(".search-index").css({"position":"fixed","-webkit-box-shadow":"0 0 0"});
			}
		}
		
	});
});