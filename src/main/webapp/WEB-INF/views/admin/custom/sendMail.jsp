<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/master-tag" prefix="fms" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/date-tag" prefix="date" %>
<fms:ContentPage masterPageId="master">
	<fms:Content contentPlaceHolderId="title">
		Customer Relationship Management
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
					<a href="${contextPath}/admin/custom/list/1">客户管理</a>
				</li>
				<li class="active">发送邮件</li>
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
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="page-header">
						<h1>发送邮件
						<small>
							<i class="icon-double-angle-right">								
							Please enter your Email details (*为必填项)
							</i>
						</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<form action="${contextPath}/admin/custom/sendmail" role="form" class="form-horizontal" method="post" id="form_post" >
								<input type="hidden" name="checks" value="${checks}" />
								<input type="hidden" name="deptEmail" value="${dept.deptEmail}" />
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 部门邮箱</label>
									<div class="col-sm-9">
										<label style="padding-top: 4px;">百桥国际（BRIDGEBIO）<b>&lt;${dept.deptEmail}&gt;</b> </label>
									</div>
									
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 已选择客户列表 </label>
									<div class="col-sm-9">
										<c:forEach items="${customs}" var="c">
											<label style="padding-top: 3px;">[ ${c.name} <b>&lt;${c.email}&gt;</b> ]</label>&nbsp;
										</c:forEach>
										
										<!--  <span style="color:#b399a6;">&nbsp;&nbsp;Please enter custom's name</span>	-->
									</div>
								</div>
								<div class="space-4"></div>
							
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入邮件标题 </label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="Email's Title " class="col-xs-10 col-sm-5" name="title" value="" />
										<font style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></font>
										<!--  <span style="color:#b399a6;">&nbsp;&nbsp;Please enter custom's name</span>	-->
									</div>
								</div>
								<div class="space-4"></div>
							
								<div class="form-group" >
									<h4 class="header green clearfix" >
										请输入邮件正题&nbsp;<small>Body</small>
										<span class="block pull-right">
											<small class="grey middle">Choose style: &nbsp;</small>
				
											<span class="btn-toolbar inline middle no-margin">
												<span data-toggle="buttons" class="btn-group no-margin">
													<label class="btn btn-sm btn-yellow">
														1
														<input type="radio" value="1" />
													</label>
				
													<label class="btn btn-sm btn-yellow active">
														2
														<input type="radio" value="2" />
													</label>
				
													<label class="btn btn-sm btn-yellow">
														3
														<input type="radio" value="3" />
													</label>
												</span>
											</span>
										</span>
									</h4>
									<div style="padding:8px;">
										Dear <font color="red">[客户姓名]</font> ：
									</div>
									<div class="wysiwyg-editor" id="editor1" ></div>
									<input type="hidden" name="content" value="" />
								</div>
								<div class="space-4"></div>	
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入密码</label>
									<div class="col-sm-9">
										<input type="password" placeholder="邮箱密码" class="col-xs-10 col-sm-5" name="password" />
									</div>
								</div>
								<div class="space-4"></div>
					
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="button" onclick="form_submit('form_post')">
											<i class="icon-ok bigger-110"></i>
											提交(Submit)
										</button>
		
										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset" onclick="history.go(-1)">
											<i class="icon-undo bigger-110"></i>
											返回(Back)
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
		<script type="text/javascript">
			function form_submit(id){
				var form = $("#"+id);
				if($("#"+id+" [name='title']").val() == ''){
					ace_msg.danger("请输入邮件标题!");
					return;
				}
				if($("#"+id+" [name='password']").val() == ''){
					ace_msg.danger("请输入邮箱密码!");
					return;
				}
				bootbox.confirm("确认发送邮件?",function(result){
					if(result){
						var html = $("#editor1").html();
						$("#"+id+" [name='content']").val(html);
						
						$.ajax({
							url:form.attr("action"),
							data:form.serialize(),
							dataType:'json',
							success:function(data){
								if(data.code==0){
									$("#"+id+" [name='title']").val("");
									$("#editor1").html("");
									$("#"+id+" [name='content']").val("");
									$("#"+id+" [name='password']").val("");
									ace_msg.success(data.msg);
								}else{
									ace_msg.danger(data.msg);
								}
							}
						});
						
					}
				});
				
			}
			$(document).ready(function(){
				function showErrorAlert (reason, detail) {
					var msg='';
					if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
					else {
						console.log("error uploading file", reason, detail);
					}
					$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
					 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
				}
				
				$('#editor1').ace_wysiwyg({
					toolbar:
					[
						'font',
						null,
						'fontSize',
						null,
						{name:'bold', className:'btn-info'},
						{name:'italic', className:'btn-info'},
						{name:'strikethrough', className:'btn-info'},
						{name:'underline', className:'btn-info'},
						null,
						{name:'insertunorderedlist', className:'btn-success'},
						{name:'insertorderedlist', className:'btn-success'},
						{name:'outdent', className:'btn-purple'},
						{name:'indent', className:'btn-purple'},
						null,
						{name:'justifyleft', className:'btn-primary'},
						{name:'justifycenter', className:'btn-primary'},
						{name:'justifyright', className:'btn-primary'},
						{name:'justifyfull', className:'btn-inverse'},
						null,
						{name:'createLink', className:'btn-pink'},
						{name:'unlink', className:'btn-pink'},
						null,
						{name:'insertImage', className:'btn-success'},
						null,
						'foreColor',
						null,
						{name:'undo', className:'btn-grey'},
						{name:'redo', className:'btn-grey'}
					],
					'wysiwyg': {
						fileUploadError: showErrorAlert
					}
				}).prev().addClass('wysiwyg-style2');
				
				
			});
		</script>
	</fms:Content>
</fms:ContentPage>