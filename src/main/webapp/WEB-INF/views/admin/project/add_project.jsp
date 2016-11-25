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
					<a href="${contextPath}/admin/project/list/1">项目管理</a>
				</li>
				<li class="active">项目新增</li>
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
						<h1>项目新增
						<small>
							<i class="icon-double-angle-right">
							Please enter your project details (*为必填项)
							</i>
						</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<form action="${contextPath}/admin/project/save" role="form" class="form-horizontal" method="post" id="form_post" >
							
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"  for="form-field-select-3"> 请选择项目所有人 </label>
									<div class="col-sm-9">
										<c:if test="${custom == null}">
											<select class="width-20 chosen-select" data-placeholder="Choose a Custom..." name="customId">
											<option value="">请选择</option>
											<c:forEach items="${customs}" var="c">
												<option value="${c.id}">${c.name}</option>
											</c:forEach>
											</select>
										</c:if>
										<c:if test="${custom != null}">
											<label style="color: red;margin-top: 4px;"><b>${custom.name}</b></label>
											<input type="hidden"  name="customId" value="${custom.id}" />
										</c:if>
										
										<font style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></font>
										<span style="color:#b399a6">&nbsp;&nbsp;Customer's Name</span>	
										
									</div>
								</div>
								<div class="space-4"></div>
							
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入项目名称 </label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="Project Name" class="col-xs-10 col-sm-5" name="name" value="${project.name}" />
										<font style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></font>
										<!--<span style="color:#b399a6">&nbsp;&nbsp;Please enter project name.</span>	-->
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请选择项目类别 </label>
									<div class="col-sm-9">
										<select name="typeId">
											<c:forEach items="${types}" var="t">
												<option value="${t.id}">${t.name}</option>
											</c:forEach>
										</select>
										<font style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></font>
										<span style="color:#b399a6">&nbsp;&nbsp;Project Type</span>
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请选择项目分类级别 </label>
									<div class="col-sm-9">
										<select name="rate">
											<c:forEach items="${RATES}" var="r">
												<option value="${r.key}">${r.value}</option>
											</c:forEach>
										</select>
										<font style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></font>
										<span style="color:#b399a6">&nbsp;&nbsp;Project Rate</span>
									</div>
								</div>
								<div class="space-4"></div>
								
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请选择项目领域 </label>
									<div class="col-sm-9">
										<select onchange="selectDomain(this)">
											<option value="">请选择</option>
											<c:forEach items="${parents}" var="p">
												<option value="${p.id}">${p.name}</option>
											</c:forEach>
										</select>
                                        <select id="project-domain" name="domainId">
                                        	<option value=''>请选择</option>
                                        </select>
                                        <font style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></font>
                                        <span style="color:#b399a6">&nbsp;&nbsp;Project Domain</span>
										<script type="text/javascript">
										function selectDomain(obj){
											var list = [];
											var index = obj.selectedIndex;
											$.ajax({
												url:"${contextPath}/admin/projectdomain/childs.json",
												data:{pid:obj.value},
												type:"POST",
												dataType:'json',
												success:function(data){
													var childs = data.childs;
													var text = "<option value=''>请选择</option>";
													//遍历list
													if(index > 0){
														for(var i = 0;i<childs.length;i++){
															var child = childs[i];
															text += "<option value='"+child.id+"' >"+child.name+"</option>";
														}
													}
													text += "";
													$("#project-domain").empty();
													$("#project-domain").html(text);
												},
												error:function(data){
													//console.log(data);
												}
											});
											
										}
										</script>
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入项目简介 </label>
									<div class="col-sm-9">
										<input type="text"  placeholder="Project Brief" class="col-xs-10 col-sm-5" name="brief" value="${project.brief}" />
										<!--<span style="color:#b399a6">&nbsp;&nbsp;Project Brief</span>-->
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入项目阶段 </label>
									<div class="col-sm-9">
										<select name="stage">
											<c:forEach items="${STAGES}" var="s">
												<option value="${s.key}">${s.value}</option>
											</c:forEach>
										</select>
										<font style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></font>
										<span style="color:#b399a6">&nbsp;&nbsp;Project Stage</span>
									</div>
								</div>
								<div class="space-4"></div>
								
								<!-- 因需求重复，暂时关闭 -->
								<!-- 
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入项目特点 </label>
									<div class="col-sm-9">
										<input type="text" placeholder="项目特点" class="col-xs-10 col-sm-5" name="project.trait" value="${project.trait}" />
										<span style="color:#b399a6">&nbsp;&nbsp;Please enter project trait.</span>
									</div>
								</div>
								<div class="space-4"></div>
								 -->
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入项目需求</label>
									<div class="col-sm-9" style="height:25px;   line-height:25px;  ">
										<c:forEach items="${DEMANDS}" var="d">
											<input type="checkbox" name="demand" value="${d.key}" class="ace" /><span class="lbl"> ${d.value}</span>&nbsp;
										</c:forEach>
										<span style="color:#b399a6">&nbsp;&nbsp;Project Demand</span>
									</div>
								</div>
								<div class="space-4"></div>
							
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-tags">请输入项目标签</label>
									<div class="col-sm-9">
										<input  type="text" name="projectTag" class="col-xs-10 col-sm-5" id="form-field-tags" value="" placeholder="Enter tags ..." />
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
										<button class="btn" type="reset" onclick="history.go(-1)" >
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
				if($("#"+id+" [name='customId']").val() == ''){
					ace_msg.danger("请选择项目所有人!");
					return;
				}
				if($("#"+id+" [name='domainId']").val() == ''){
					ace_msg.danger("请选择项目领域!");
					return;
				}
				bootbox.confirm("确认新增?",function(result){
					if(result){
						var form = $("#"+id);
						
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
				$(".chosen-select").chosen(); 
				var keywords = '${keywords}'.split(',');
				
				var tag_input = $('#form-field-tags');
				if(! ( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase())) ) 
				{
					tag_input.tag(
					  {
						placeholder:tag_input.attr('placeholder'),
						//enable typeahead by specifying the source array
						source: keywords,//defined in ace.js >> ace.enable_search_ahead
					  }
					);
				}
				else {
					//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
					tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
					//$('#form-field-tags').autosize({append: "\n"});
				}
				
			});
		</script>
	</fms:Content>
</fms:ContentPage>