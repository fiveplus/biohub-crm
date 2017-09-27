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
					<a href="${contextPath}/admin/custom/list/1">客户管理</a>
				</li>
				<li class="active">客户新增</li>
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
						<h1>客户新增
						<small>
							<i class="icon-double-angle-right">								
							Please enter your customer details (*为必填项)
							</i>
						</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<form action="${contextPath}/admin/custom/save" role="form" class="form-horizontal" method="post" id="form_post" >
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请选择客户区域 </label>
									<div class="col-sm-9">
										<select name="locationId">
											<option value="">请选择</option>
											<c:forEach items="${locations}" var="l">
												<c:if test="${custom.locationId == l.id}">
													<option value="${l.id}" selected="selected">${l.name}</option>
												</c:if>
												<c:if test="${custom.locationId != l.id}">
													<option value="${l.id}">${l.name}</option>
												</c:if>
											</c:forEach>
										</select>												
										<font style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></font>
										<span style="color:#b399a6">&nbsp;&nbsp;Customer's Area</span>						
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请选择客户类别 </label>
									<div class="col-sm-9">
										<select name="typeId">
											<option value="">请选择</option>
											<c:forEach items="${types}" var="t">
												<c:if test="${custom.typeId == t.id}">
													<option value="${t.id}" selected="selected">${t.name}</option>
												</c:if>
												<c:if test="${custom.typeId != t.id}">
													<option value="${t.id}">${t.name}</option>
												</c:if>
											</c:forEach>
										</select>												
										<font style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></font>
										<span style="color:#b399a6">&nbsp;&nbsp;Customer's Type</span>						
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入客户城市 </label>
									<div class="col-sm-9">
										<select name="country" onchange="getProvince(this)">
											<option value="">请选择</option>
										</select>
									</div>
								</div>
							
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入客户姓名 </label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="Customer's Name " class="col-xs-10 col-sm-5" name="name" value="${custom.name}" />
										<font style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></font>
										<!--  <span style="color:#b399a6;">&nbsp;&nbsp;Please enter custom's name</span>	-->
									</div>
								</div>
								<div class="space-4"></div>
							
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入联系电话 </label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="Phone Number" class="col-xs-10 col-sm-5" name="telephone" value="${custom.telephone}" />
										<!-- <span style="color:#b399a6">&nbsp;&nbsp;Phone Number</span>  -->
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入QQ</label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="QQ Number" class="col-xs-10 col-sm-5" name="qq" value="${custom.qq}" />
										<!--  <span style="color:#b399a6">&nbsp;&nbsp;Please enter qq</span> -->
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入E-mail</label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="E-mail Address" class="col-xs-10 col-sm-5" name="email" value="${custom.email}" />
										<font style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></font>
										<!-- <span style="color:#b399a6">&nbsp;&nbsp;E-mail Address</span>  -->
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入微信</label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="Wechat ID" class="col-xs-10 col-sm-5" name="wechat" value="${custom.wechat}" />
										<!--  <span style="color:#b399a6">&nbsp;&nbsp;Please enter Wechat</span> -->
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入Skype</label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="Skype ID" class="col-xs-10 col-sm-5" name="skype" value="${custom.skype}" />
										<!-- <span style="color:#b399a6">&nbsp;&nbsp;Please enter Skype</span>  -->
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入Linked-in</label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="LinkedIn URL" class="col-xs-10 col-sm-5" name="linkedin" value="${custom.linkedin}" />
										<!-- <span style="color:#b399a6">&nbsp;&nbsp;LinkedIn URL</span>  -->
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入单位名称</label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="Company Name" class="col-xs-10 col-sm-5" name="company" value="${custom.company}" />
										<!--   <span style="color:#b399a6;">&nbsp;&nbsp;Please enter company name</span> -->	
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入网址</label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="Company's Website" class="col-xs-10 col-sm-5" name="websize" value="${custom.websize}" />
										<!-- <span style="color:#b399a6;">&nbsp;&nbsp;Please enter websize</span>  -->
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入客户住址</label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="Customer's Address" class="col-xs-10 col-sm-5" name="customAddress" value="${custom.customAddress}" />
										<!-- <span style="color:#b399a6;">&nbsp;&nbsp;Please enter custom's address</span>  -->
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group" >
									<h4 class="header green clearfix" >
										请输入备注信息&nbsp;<small>Remarks</small>
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
									<div class="wysiwyg-editor" id="editor1">${custom.remark}</div>
									<input type="hidden" name="remark" value="${custom.remark}" />
								</div>
								<div class="space-4"></div>
					
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="button" onclick="form_submit('form_post')">
											<i class="icon-ok bigger-110"></i>
											提交(Submit)
										</button>
										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="button" onclick="history.go(-1)">
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
		
			function getCity(){
				var obj = $("select[name='province']");
				var p = obj.val();
				$.ajax({
					url:"${contextPath}/static/city.xml",
					dataType:"xml",
					type:"POST",
					success:function(data){
						var c = $("<select name='city'><option value=''>请选择</option></select>");
						$(data).find("Location").find("CountryRegion").find("State").each(function(index,ele){
							$("select[name='city']").remove();
							var name = $(this).attr("Name");
							if(name == p){
								$(this).find("City").each(function(){
									var v = $(this).attr("Name");
									c.append("<option value='"+v+"'>"+v+"</option>");
								});
							}
						});
						$(obj).parent().append(c);
					}
				});
			}
		
			function getProvince(obj){
				var c = obj.value;
				$.ajax({
					url:"${contextPath}/static/city.xml",
					dataType:"xml",
					type:"POST",
					success:function(data){
						var flag = true;
						var p = $("<select name='province' onchange='getCity()'><option value=''>请选择</option></select>");
						$("select[name='province']").remove();
						$("select[name='city']").remove();
						$(data).find("Location").find("CountryRegion").each(function(index,ele){
							var name = $(this).attr("Name");
							if(name == c){
								$(this).find("State").each(function(){
									var v = $(this).attr("Name");
									if(v == undefined){
										var c = $("<select name='city'><option value=''>请选择</option></select>");
										$(this).find("City").each(function(){
											var vv = $(this).attr("Name");
											c.append("<option value='"+vv+"'>"+vv+"</option>");
										});
										$(obj).parent().append(c);
										flag = false;
										return false;
									}else{
										p.append("<option value='"+v+"'>"+v+"</option>");
									}
								});
							}
						});
						var length = p.find("option").size();
						if(flag && length > 1){
							$(obj).parent().append(p);
						}
					}
				});
			}
		
			function form_submit(id){
				var form = $("#"+id);
				if($("#"+id+" [name='name']").val() == ''){
					ace_msg.danger("错误！请输入客户姓名!");
					return;
				}
				if($("#"+id+" [name='email']").val() == ''){
					ace_msg.danger("错误！请输入E-mail!");
					return;
				}
				
				bootbox.confirm("确认新增?",function(result){
					if(result){
						var form = $("#"+id);
						
						if($("#editor1")){
							var evalue = $("#editor1").html();
							$("#"+id+" [name='remark']").val(evalue);
						}
						
						$.ajax({
							url:form.attr('action'),
							type:"POST",
							data:form.serialize(),
							dataType:'json',
							success:function(data){
								if(data.code == 0){
									ace_msg.success(data.msg);
									go_back();
								}else{
									ace_msg.danger(data.msg);
								}
							},
							error:function(data){
								//console.log(data);
							}
						});
					}
					
				});
				
			}
			$(document).ready(function(){
				
				//初始化国家
				$.ajax({
					url:"${contextPath}/static/city.xml",
					dataType:"xml",
					type:"POST",
					success:function(data){
						var c = $("select[name='country']");
						$(data).find("Location").find("CountryRegion").each(function(index,ele){
							var name = $(this).attr("Name");
							c.append("<option value='"+name+"'>"+name+"</option>");
						});
					}
				});
				
				
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