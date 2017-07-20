<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="date" uri="/date-tag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:forEach items="${user.pers}" var="p">
	<c:if test="${p.id == 'addFile'}">
		 

<div class="widget-main">
	<form action="${contextPath}/admin/file/save?projectId=${projectId}"  method="post" id="file_post">
		<input multiple="multiple" type="file" id="file" name="file" />
		<label>
			<input type="checkbox" name="file-format" id="id-file-format" class="ace" />
			<span class="lbl"> Allow only images</span>
		</label>
	</form>
</div>
<div class="clearfix form-actions">
	<div id="progress_div" style="display: none;">
		<div class="progress progress-striped active" data-percent="0%">
			<div class="progress-bar progress-bar-yellow" style="width: 0%"></div>
		</div>
	</div>
	<div class="col-md-offset-3 col-md-9" align="right" style="padding-right:0px;">
		<button class="btn btn-info" type="button" onclick="fileSubmit('file_post','file','file_list')">
		<i class="icon-ok bigger-110"></i>
		文件上传
		</button>
	</div>
</div>
	</c:if>
</c:forEach>

<div>
	<div class="page-header">
		<h1>历史上传记录
			<small>
				<i class="icon-double-angle-right">
				The File List
				</i>
			</small>
		</h1>
	</div>

	<div id="accordion2" class="accordion-style1 panel-group">
			<c:forEach items="${pu.list}" var="f" varStatus="stat">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapsef${stat.index}">
								<i class="bigger-110 icon-angle-down" data-icon-hide="icon-angle-down" data-icon-show="icon-angle-right"></i>
								&nbsp;<date:date value="${f.createTime}" /> [${f.createUser}] ${f.name}
							</a>
						</h4>
					</div>
					<div class="panel-collapse <c:if test="${stat.index == 0 }">in</c:if>" id="collapsef${stat.index}" style="height: <c:if test="${stat.index == 0 }">auto</c:if><c:if test="${stat.index > 0 }">0</c:if>;">
						<div class="panel-body">
							<div>
								<label><a href="javascript:export_file('${f.name}')">文件下载</a></label>
							</div>
							
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	<div class="modal-footer no-margin-top">
		<ul class="pagination pull-right no-margin" id="page">
		<!-- 分页 -->
		<c:if test="${pu.pageNum==1}">
			<li class="prev disabled">
				<a href="javascript:void(0)">
					<i class="icon-double-angle-left"></i>
				</a>
			</li>
		</c:if>
		<c:if test="${pu.pageNum!=1}">
			<li class="prev">
				<a href="javascript:load_list('file_list','${contextPath}/admin/file/list/1?projectId=${projectId}')">
					<i class="icon-double-angle-left"></i>
				</a>
			</li>
		</c:if>
		<c:forEach items="${pc.pageList}" var="p">
			<c:if test="${p==pu.pageNum}">
				<li class="active">
					<a href="javascript:void(0)">${p}</a>
				</li>
			</c:if>
			<c:if test="${p!=pu.pageNum}">
				<li>
					<a href="javascript:load_list('file_list','${contextPath}/admin/file/list/${p}?projectId=${projectId}')">${p}</a>
				</li>
			</c:if>
		</c:forEach>
		<c:if test="${pu.pageNum==pu.lastPage}">
			<li class="next disabled">
				<a href="javascript:void(0)">
					<i class="icon-double-angle-right"></i>
				</a>
			</li>
		</c:if>
		<c:if test="${pu.pageNum!=pu.lastPage}">
			<li class="next">
				<a href="javascript:load_list('file_list','${contextPath}/admin/file/list/${pu.lastPage}?projectId=${projectId}')">
					<i class="icon-double-angle-right"></i>
				</a>
			</li>
		</c:if>
	</ul>
	</div>
</div>

<script type="text/javascript">

	function export_file(name){
		//伪造form
		var form = $('<form></form>');
		form.attr('action','${contextPath}/admin/file/download');
		form.attr('method','post');
		form.append("<input type='hidden' name='name' value='"+name+"' />");
		form.submit();
	}

	jQuery(function($){
		var upload_in_progress = false;
		$('#file').ace_file_input({
			style:'well',
			btn_choose:'Drop files here or click to choose',
			btn_change:null,
			no_icon:'icon-cloud-upload',
			droppable:true,
			thumbnail:'small'//large | fit
			//,icon_remove:null//set null, to hide remove/reset button
			/**,before_change:function(files, dropped) {
				//Check an example below
				//or examples/file-upload.html
				return true;
			}*/
			,before_remove : function() {
				if(upload_in_progress)
					return false;//if we are in the middle of uploading a file, don't allow resetting file input
				return true;
			}
			,
			preview_error : function(filename, error_code) {
				//name of the file that failed
				//error_code values
				//1 = 'FILE_LOAD_FAILED',
				//2 = 'IMAGE_LOAD_FAILED',
				//3 = 'THUMBNAIL_FAILED'
				//alert(error_code);
			}
	
		}).on('change', function(){
			//console.log($(this).data('ace_input_files'));
			//console.log($(this).data('ace_input_method'));
		});
		
		//dynamically change allowed formats by changing before_change callback function
		$('#id-file-format').removeAttr('checked').on('change', function() {
			var before_change
			var btn_choose
			var no_icon
			if(this.checked) {
				btn_choose = "Drop images here or click to choose";
				no_icon = "icon-picture";
				before_change = function(files, dropped) {
					var allowed_files = [];
					for(var i = 0 ; i < files.length; i++) {
						var file = files[i];
						if(typeof file === "string") {
							//IE8 and browsers that don't support File Object
							if(! (/\.(jpe?g|png|gif|bmp)$/i).test(file) ) return false;
						}
						else {
							var type = $.trim(file.type);
							if( ( type.length > 0 && ! (/^image\/(jpe?g|png|gif|bmp)$/i).test(type) )
									|| ( type.length == 0 && ! (/\.(jpe?g|png|gif|bmp)$/i).test(file.name) )//for android's default browser which gives an empty string for file.type
								) continue;//not an image so don't keep this file
						}
						
						allowed_files.push(file);
					}
					if(allowed_files.length == 0) return false;
		
					return allowed_files;
				}
			}
			else {
				btn_choose = "Drop files here or click to choose";
				no_icon = "icon-cloud-upload";
				before_change = function(files, dropped) {
					return files;
				}
			}
			var file_input = $('#file');
			file_input.ace_file_input('update_settings', {'before_change':before_change, 'btn_choose': btn_choose, 'no_icon':no_icon})
			file_input.ace_file_input('reset_input');
		});
	});
	
</script>