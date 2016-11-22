function get_url(url){
	window.location.href=url;
}
function to(page){
	var form = document.getElementById("search-form");
	form.action += "?p="+page;
	form.submit();
}
function search_focus(flag){
	var div = $("#search-div");
	if(flag){
		div.css("border","1px solid #4285f4");
	}else{
		div.css("border","1px solid #eee");
	}
}