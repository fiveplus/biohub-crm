<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/master-tag" prefix="fms" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/date-tag" prefix="date" %>
<fms:ContentPage masterPageId="master">
	<fms:Content contentPlaceHolderId="title">
		Customer Relationship Management
	</fms:Content>
		
	<fms:Content contentPlaceHolderId="source">
		<!-- 按需加载模块 -->
	</fms:Content>
	
	<fms:Content contentPlaceHolderId="main">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

			<ul class="breadcrumb">
				<li>
					<i class="icon-home home-icon"></i>
					<a href="${contextPath}/admin/index">Home</a>
				</li>
				<li>
					<a href="${contextPath}/admin/user/list/1">用户管理</a>
				</li> 
				<li class="active">用户授权</li>
			</ul><!-- .breadcrumb -->

			<div class="nav-search" id="nav-search">
				<form action="" method="post" onsubmit="return false;" class="form-search">
					<span class="input-icon">
						<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
						<i class="icon-search nav-search-icon"></i>
					</span>
				</form>
			</div><!-- #nav-search -->
		</div>

		<div class="page-content">
			<div class="page-header">
				<h1>
					用户授权
					<small>
						<i class="icon-double-angle-right"></i>
						${us.userName}
					</small>
				</h1>
			</div><!-- /.page-header -->
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGIN -->
					<div class="row">
						<div class="col-sm-6">
							<div class="widget-box">
								<div class="widget-header header-color-blue2">
									<h4 class="lighter smaller" style="color:white;">权限选择</h4>
								</div>

								<div class="widget-body">
									<div class="widget-main padding-8">
										<div id="tree1" class="tree"></div>
										<div class="hr"></div>
										
										<button id="submit-button" type="button" class="btn btn-sm btn-primary pull-right">
											<i class="icon-ok"></i>
											保存
										</button>
										
										<button class="btn btn-sm btn-primary pull-right" type="reset" onclick="go_back()">
											<i class="icon-undo bigger-110"></i>
											返回
										</button>
										
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div id="modal-tree-items" class="modal" tabindex="-1">
						<div class="modal-dialog">
							<div class="modal-content">
								
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="blue bigger">权限确认</h4>
								</div>
								
								<div class="modal-body">
									<div class="row-fluid">
										待提交的权限列表
									</div>
									
									<div class="space-6"></div>
									
									<div class="row-fluid">
										<input type="hidden" id="tree-input" />
										<textarea spellcheck="false" readonly="readonly" id="tree-value" style="resize: none;"></textarea>
									</div>
								</div>
								
								<div class="modal-footer">
									<button class="btn btn-sm" data-dismiss="modal"><i class="icon-remove"></i> Cancel</button>
									<button class="btn btn-sm btn-primary" id="submit-ok"><i class="icon-ok"></i> OK</button>
								</div>
								
							</div>
						</div>
					</div>
					
					<!-- PAGE CONTENT END -->
				</div>
			</div>
		</div><!-- /.page-content -->
		<script type="text/javascript">
			$(function(){
				var DataSourceTree = function(options){
					this.url = options.url;
				};
				var params = new Array();
				DataSourceTree.prototype.data = function(options,callback){
					var flag = 0;
					var $data = null;
					var param = null;
					if(!("name" in options) && !("type" in options)){
						param = null;//load the first level data
					}
					else if("type" in options && options.type == "folder") {
						if("additionalParameters" in options && "children" in options.additionalParameters){
							param = options.additionalParameters["id"];
							if(params.indexOf(param)==-1){
								params.push(param);
							}else{
								flag = -1;
							}
						}
					}
					var datas = {};
					if(param != null){
						datas = {pid:param};
					}
					if(flag == 0){
						$.ajax({
							url:this.url,
							data:datas,
							type:'POST',
							dataType:'json',
							success:function(response){
								if(response.status == "OK")
									callback({ data: response.data});
								//展开树
								$("#tree1").find(".tree-folder-header").each(function(){
								    if($(this).parent().css("display")=="block"){  
								        $(this).trigger("click");  
								    }  
								});
							},
							error:function(response){
								//console.log(response);
							}
						});
					}
				};
				
				
				$("#tree1").ace_tree({
					dataSource:new DataSourceTree({url:'${contextPath}/admin/user/perlist.json?uid=${us.id}'}),
					multiSelect:true,
					loadingHTML:"<div class='tree-loading'><i class='icon-refresh icon-spin blue'></i></div>",
					'open-icon' : 'icon-minus',
					'close-icon' : 'icon-plus',
					'selectable' : true,
					'selected-icon' : 'icon-ok',
					'unselected-icon' : 'icon-remove',
				});
				
				$("#submit-button").on('click',function(){
					var tree = $("#tree1").data('tree');
					var output = '';
					var items = tree.selectedItems();
					var treeids = new Array();
					for(var i in items) if (items.hasOwnProperty(i)) {
						var item = items[i];
						output += item.additionalParameters['id'] + ":"+ item.name+"\n";
						treeids.push(item.additionalParameters['id']);
					}
					$('#tree-input').val(treeids.toString());
					$('#modal-tree-items').modal('show');
					$('#tree-value').css({'width':'98%', 'height':'200px'}).val(output);
				});
				
				$("#submit-ok").on('click',function(){
					//数据提交
					var pids = $('#tree-input').val();
					var uid = '${us.id}';
					$.ajax({
						url:'${contextPath}/admin/user/savepers',
						data:{uid:uid,pids:pids},
						dataType:"json",
						success:function(data){
							$('#modal-tree-items').modal('hide');
							if(data.code==0){
								ace_msg.success(data.msg);
							}else{
								ace_msg.danger(data.msg);
							}
						},
						error:function(data){
							//console.log(data)
						}
					});
				});
				
				if(location.protocol == 'file:') alert("For retrieving data from server, you should access this page using a webserver.");
				
			});
			
		</script>
	
	</fms:Content>
</fms:ContentPage>