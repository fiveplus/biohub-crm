<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/master-tag" prefix="fms" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/date-tag" prefix="date" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
				<!-- 
				<li>
					<a href="#">Other Pages</a>
				</li> -->
				<li class="active">客户管理</li>
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
					<div class="row">
						<div class="col-xs-12">
							<h3 class="header smaller lighter blue">
							<span>客户列表</span> <small>Custom List</small>
							
							<c:forEach items="${user.pers}" var="p">
								<c:if test="${p.id == 'addCustom'}">
								<button class="btn" style="float:right;margin-top: -12px;" onclick="go_url('${contextPath}/admin/custom/add')" ><i class="icon-pencil align-top bigger-125"></i>客户新增 (Add Customer)</button>
								</c:if>
							</c:forEach>
							</h3>
							
							<div>
								<form action="${contextPath}/admin/custom/list" role="form" class="form-horizontal" method="post" id="form_post">
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"  for="form-field-select-3"> 请输入客户姓名/单位 </label>
									<div class="col-sm-9">
										<input type="text"  placeholder="Customers' Name/Company" class="col-xs-10 col-sm-5" name="name" id="dataInput" value="${param.name}" />
										<!-- <span style="color:#b399a6">&nbsp;&nbsp; (Please enter a custom name.)</span>  -->
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-select-3"> 创建人</label>
									<div class="col-sm-9" >
									<select class="width-20 chosen-select" data-placeholder="Choose a CreateUser..." name="createUser" id="select_user">
									<option value="">请选择</option>
									<c:forEach items="${users}" var="c">
										<c:if test="${param.createUser == c.userName}">
											<option value="${c.userName}" selected="selected">${c.userName}</option>
										</c:if>
										<c:if test="${param.createUser != c.userName}">
											<option value="${c.userName}">${c.userName}</option>
										</c:if>
									</c:forEach>
									</select>
									<span style="color:#b399a6">&nbsp;&nbsp; (CreateUser)</span>
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"  for="form-field-select-3"> 请选择时间段</label>
									<div class="col-sm-9">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="icon-calendar bigger-110"></i>
											</span>
											<input style="width:180px;" class="form-control" type="text" readonly="readonly" name="dateRangePicker" id="id-date-range-picker-1" value="${param.dateRangePicker}" />
											 <span style="color:#b399a6">&nbsp;&nbsp; (Time Period.)</span>
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"  for="form-field-select-3">
									</label>
									<div class="col-sm-9">
										<span class="input-group-btn" >
											<button type="button" class="btn btn-purple btn-sm" onclick="form_submit('form_post',1)">
											数据查询&nbsp;(Data Query)
											<i class="icon-search icon-on-right bigger-110"></i>
											
											</button>
											&nbsp;
											<c:forEach items="${user.pers}" var="p">
												<c:if test="${p.id == 'exportCustom'}">
													<button type="button" class="btn btn-purple btn-sm" onclick="exportJSON('${contextPath}/admin/custom/export','form_post')" >
													<i class="icon-pencil align-top bigger-125"></i>
													数据导出 &nbsp;(Data Export)
													</button>
												</c:if>
											</c:forEach>
											
										</span>
										
									</div>
								</div>
								</form>
							</div>
							
							<div class="table-header">
								共有${pu.total}条数据&nbsp;(A total of ${pu.total} records)
							</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">
												<label>
													<input type="checkbox" class="ace" onclick="checkAll(this)"   />
													<span class="lbl"></span>
												</label>
											</th>
											<th>客户姓名(Custom Name)</th>
											<th>客户类别(Custom Type)</th>
											<th>客户区域(Custom Area)</th>
											<th class="hidden-480">联系电话(Telephone)</th>
											
											<th>创建人(Create User)</th>

											<th>
												<i class="icon-time bigger-110 hidden-480"></i>
												更新时间(Update Time)
											</th>
											<th class="hidden-480">状态(Status)</th>
											<th>操作(Operation)</th>
										</tr>
									</thead>
									<c:set var="checkarr" value="${fn:split(checks,',') }"  />
									<tbody>
										<c:forEach items="${pu.list}" var="c" >
										<tr>
											<td class="center">
												<label>
													<input type="checkbox" class="ace" name="checks" value="${c.id}" onchange="check_ajax(this)" <c:forEach items="${checkarr}" var="ca"><c:if test="${ca==c.id}">checked='checked'</c:if></c:forEach> />
													<span class="lbl"></span>
												</label>
											</td>
											<td>
												<a href="${contextPath}/admin/custom/select/${c.id}">${c.name}</a>
											</td>
											<td>${c.typeName}</td>
											<td class="hidden-480">${c.locationName}</td> 
											<td >${c.telephone}</td> 
											<td >${c.createUser}</td> 
											
											<td><date:date value="${c.modifyTime}" /></td>

											<td class="hidden-480">
												<c:if test="${c.status == 'Y'}">
													<span class="label label-sm label-success">已创建</span>
												</c:if>
												<c:if test="${c.status == 'D'}">
													<span class="label label-sm label-warning">删除审核中</span>
												</c:if>
											</td>

											<td>
												<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
													<a class="blue" href="${contextPath}/admin/custom/select/${c.id}">
														<i class="icon-zoom-in bigger-130"></i>
													</a>

													<a class="green" href="${contextPath}/admin/custom/upt/${c.id}">
														<i class="icon-pencil bigger-130"></i>
													</a>

													<a class="red" href="javascript:deleteHTML('${contextPath}/admin/custom/del/${c.id}')">
														<i class="icon-trash bigger-130"></i>
													</a>
												</div>

												<div class="visible-xs visible-sm hidden-md hidden-lg">
													<div class="inline position-relative">
														<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
															<i class="icon-caret-down icon-only bigger-120"></i>
														</button>

														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
															<li>
																<a href="${contextPath}/admin/custom/select/${c.id}" class="tooltip-info" data-rel="tooltip" title="View">
																	<span class="blue">
																		<i class="icon-zoom-in bigger-120"></i>
																	</span>
																</a>
															</li>

															<li>
																<a href="${contextPath}/admin/custom/upt/${c.id}" class="tooltip-success" data-rel="tooltip" title="Edit">
																	<span class="green">
																		<i class="icon-edit bigger-120"></i>
																	</span>
																</a>
															</li>

															<li>
																<a href="javascript:deleteHTML('${contextPath}/admin/custom/del/${c.id}')" class="tooltip-error" data-rel="tooltip" title="Delete">
																	<span class="red">
																		<i class="icon-trash bigger-120"></i>
																	</span>
																</a>
															</li>
														</ul>
													</div>
												</div>
											</td>
										</tr>
										</c:forEach>
										
										</tbody>
								</table>
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
											<a href="javascript:form_submit('form_post',1)">
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
												<a href="javascript:form_submit('form_post',${p})">${p}</a>
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
											<a href="javascript:form_submit('form_post',${pu.lastPage})">
												<i class="icon-double-angle-right"></i>
											</a>
										</li>
									</c:if>
								</ul>
							</div>
							<div>
								<button class="btn" style="float:right;margin-top: 12px;margin-left: 15px;"  onclick="downloadFile('${contextPath}/downloads/custom_temple.xlsx')" ><i class="icon-pencil align-top bigger-125"></i>模板下载&nbsp;(Template Download)</button>
							</div>
							<c:forEach items="${user.pers}" var="p">
								<c:if test="${p.id == 'addCustom'}">
								<div>
									<button class="btn" style="float:right;margin-top: 12px;" onclick="fileSelect()" ><i class="icon-pencil align-top bigger-125"></i>客户资料上传&nbsp;(Upload Customer Info)</button>
									<form action="${contextPath}/admin/custom/upload" method="post" enctype="multipart/form-data" style="width:auto;" id="file_upload">
										<input type="file" name="file" id="file" onchange="fileSelected(this)" style="display:none;" />
									</form>
								</div>
								</c:if>
							</c:forEach>
							<div>
								<button class="btn" style="float:right;margin-top: 12px;margin-right: 15px;"  onclick="go_url('${contextPath}/admin/custom/mailinit')" >
									<i class="icon-pencil align-top bigger-125"></i>
									邮件发送 &nbsp;(Send Email)
								</button>
							</div>
						</div>
					</div>
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
		<script type="text/javascript">
		
			function checkAll(obj){
				var t = document.getElementsByName("checks");
				var checks = "";
				var action = "";
				if(!obj.checked){
					for(var i = 0;i<t.length;i++){
						t[i].checked = false;
					}
					action = "../custom/session.custom.remove.htm";
				}else{
					for(var i = 0;i<t.length;i++){
						t[i].checked = true;
					}
					action = "../custom/session.custom.add.htm";
				}
				for(var i = 0;i<t.length;i++){
					if(i == t.length - 1){
						checks += t[i].value;
					}else{
						checks += t[i].value + ",";
					}
				}
				$.ajax({
					type:"POST",
					url:action,
					data:"checks="+checks,
					error:function(request){
					},
					success:function(data){
					}
				});
			}
			
			//单选
			function check_ajax(obj){
				var action = "";
				var checks = obj.value;
				if(obj.checked){
					action = "custom/session.custom.add.htm";
				}else{
					action = "custom/session.custom.remove.htm";
				}
				$.ajax({
					type:"POST",
					url:action,
					data:"checks="+checks,
					error:function(request){
					},
					success:function(data){
					}
				});
			}
			
		
			function form_submit(id,page){
				var form = $("#"+id);
				var action = form.attr("action")+"/"+page;
				form.attr("action",action);
				form.submit();
			}
			function deleteHTML(url){
				bootbox.confirm("确认删除该客户吗？",function(result){
					if(result){
						$.ajax({
							url:url,
							type:"POST",
							dataType:'json',
							success:function(data){
								if(data.code == 0){
									ace_msg.success(data.msg);
									window.location.reload();
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
				$('input[name=dateRangePicker]').daterangepicker().prev().on(ace.click_event, function(){
					$(this).next().focus();
				});
				
				$(".chosen-select").chosen();
				
			});
			
			function fileSelect(){
				document.getElementById("file").click();
			}
			function fileSelected(obj){
				if(obj.value == ''){
					alert("请选择文件!");
					return;
				}
				var ext = obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
				if(ext == '.xls' || ext == '.xlsx'){
					$.ajaxFileUpload({
						url:'${contextPath}/admin/custom/upload',
						type:'post',
						secureuri:false,
						fileElementId:'file',
						dataType:'json',
						success:function(data,status){
							if(data.code==0){
								ace_msg.success(data.msg);
								window.location.reload();
							}else{
								ace_msg.danger(data.msg);
							}
							
						}
					});
				}else{
					ace_msg.warning("请选择一个Excel文件上传!");
				}
			}
			
			function downloadFile(path){
				bootbox.confirm("确认下载?",function(result){
					if(result){
						window.location.href = path;
					}
				});
			}
			
			function exportJSON(url,formid){
				bootbox.confirm("确认导出?",function(result){
					if(result){
						var time = $("#id-date-range-picker-1").val();
						var vdata = $("#"+formid).serialize();
						var action = url;
						var checksarr = new Array();
						var checks = "";
						var cname = document.getElementsByName("checks");
						if(cname){
							for(var i = 0;i<cname.length;i++){
								if(cname[i].checked){
									checksarr.push(cname[i].value);
								}
							}
							checks = checksarr.toString();
						}
						//伪造form提交
						var form = $('<form></form>');
						form.attr('action',url);
						form.attr('method', 'post');
				    	//form.attr("target","_blank");
						var checks_input = $('<input type="hidden" name="checks" />');
						checks_input.attr("value",checks);
						form.append(checks_input);
						$("#"+formid+" input").each(function(e){
							var val = $(this).val();
							if(val != ''){
								var input = $('<input type="hidden" />');
								input.attr("name",$(this).attr("name"));
								input.attr("value",$(this).attr("value"));
								form.append(input);
							}
						});
						form.submit();
						
					}
				});
				
			}
			
			

			
		</script>							
	
	</fms:Content>
</fms:ContentPage>