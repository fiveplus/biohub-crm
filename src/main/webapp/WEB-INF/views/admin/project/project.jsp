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
				<li class="active">项目资料</li>
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
						<h1>项目详情
						<small>
							<i class="icon-double-angle-right">
							Project Information
							</i>
						</small>
						<button class="btn" style="float:right;margin-top: -8px;" onclick="go_back() " ><i class="icon-pencil align-top bigger-125"></i>Back</button>
						</h1>
					</div>
					
					<div class="tabbable">
						<ul class="nav nav-tabs" id="myTab">
							<li class="active">
								<a data-toggle="tab" href="#home">
									<i class="green icon-home bigger-110"></i>
									项目资料 (Project Info)
								</a>
							</li>
							<!--  profile -->
							<li class="">
								<a data-toggle="tab" href="#process">
									跟踪信息 (Project Proceses)
									<!-- 
									<span class="badge badge-danger">4</span> -->
								</a>
							</li>

							<li class="dropdown">
								<a data-toggle="dropdown" class="dropdown-toggle" href="#">
									文档资料 (Project Documents) &nbsp;
									<i class="icon-caret-down bigger-110 width-auto"></i>
								</a>
										
								<ul class="dropdown-menu dropdown-info">
									<li>
										<a data-toggle="tab" href="#dropdown1">@全部文档资料 (All Documents)</a>
									</li>
									<!-- 
									<li>
										<a data-toggle="tab" href="#dropdown2">@mdo</a>
									</li> -->
								</ul>
							</li>
						</ul>
						
						<div class="tab-content">
							<div id="home" class="tab-pane active">
								<!-- <p>这里显示客户基本资料</p>  -->
								<div>
									<div class="page-header">
										<h1>基本信息
										<small>
											<i class="icon-double-angle-right">
											General Information
											</i>
										</small>
										<c:forEach items="${user.pers}" var="p">
											<c:if test="${p.id == 'updateCustom'}">
											<button class="btn" style="float:right;margin-top: -12px;" onclick="form_update_init('${contextPath}/admin/project/updateInitCustom/${project.customId}','custom') " ><i class="icon-pencil align-top bigger-125"></i>修改 (Update)</button>
											</c:if>
										</c:forEach>
										</h1>
									</div>
									<div class="row">
										<div class="col-xs-12" id="custom">
											<!-- 客户基本信息 -->
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 客户姓名 (Custom Name)</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-10"><a href="${contextPath}/admin/custom/select/${project.customId}">${project.customName}</a></label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 联系电话 (Telephone)</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-10">${project.telephone}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 电子邮箱 (E-mail)</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-10">${project.email}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> QQ</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-10">${project.qq}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 微信 (Wechat)</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-10">${project.wechat}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Skype</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-10">${project.skype}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Linked-in</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-10">${project.linkedin}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 单位 (Company)</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-10">${project.company}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 网址 (Websize)</label>													<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-10">${project.websize}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
										</div>
									</div>
								</div>
								
								<div>
									<h4  class="ligher smaller" style="cursor:pointer;" onclick="showOrHide('custom_user')">
										<i class="icon-rss orange">
											修改人 (Update Peoples)
										</i>
									</h4>
									<div id="custom_user" style="display: none;" >
										<div>
											<div class="clearfix">
												<!-- 用户给修改信息 -->
												<c:forEach items="${cus}" var="u">
													<div class="itemdiv memberdiv">
														<div class="user">
															<img alt="${u.userName}" src="${contextPath}/<c:if test="${u.picture == ''}" >assets/avatars/user.jpg</c:if><c:if test="${u.picture != ''}" >${u.picture}</c:if>" />
														</div>
														<div class="body">
															<div class="name">
																<a href="javascript:void(0)">${u.userName}</a>
															</div>
															<div class="time">
																<!-- <i class="icon-time"></i> -->
																<span class="green">[ ${u.deptName} ]</span>
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
								<!-- 项目信息 -->
								<div>
									<div class="page-header">
										<h1>项目信息
										<small>
											<i class="icon-double-angle-right">
											The Project
											</i>
										</small>
										</h1>
										<c:forEach items="${user.pers}" var="p">
											<c:if test="${p.id == 'updateProject'}">
												<button class="btn" style="float:right;margin-top: -35px;" onclick="form_update_init('${contextPath}/admin/project/updateInitProject/${project.id}','project')" ><i class="icon-pencil align-top bigger-125"></i>修改 (Update)</button>
											</c:if>
										</c:forEach>
									</div>
									<div class="row">
										<div class="col-xs-12" id="project">
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目编号 (Project Number) </label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-5">${project.projectNum}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 创建人 (Create User)</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-5">${project.createUser}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 负责人 (Charge User) </label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-5">${project.chargeUser}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 跟进人 (Follow User) </label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-5">${project.followUser}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
													
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目类型 (Project Type) </label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-5">${project.typeName}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
													
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目分类级别 (Project Rate)</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-5">${project.rate}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
													
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目领域 (Project Domain) </label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-5">${project.domainName}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
													
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目名称 (Project Name)</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-5">${project.name}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
													
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目简介 (Project Brief) </label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-5">${project.brief}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
													
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目阶段 (Project Stage) </label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-5">${project.stage}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
												
													
											<!-- 因需求重复，暂时关闭 -->
											<!-- 
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目特点 (Project Trait)</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-5">${project.trait}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
											 -->
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目需求 (Project Demand)</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-6">${project.demand}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
													
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目标签 (Project Tags)</label>
												<div class="col-sm-9" style="margin-top: 5px;">
													<label class="col-xs-10 col-sm-6">${project.projectTag}</label>
												</div>
											</div>&nbsp;
											<div class="space-4"></div>
													
										</div>
									</div>
								</div>
								
								<div>
									<h4 class="ligher smaller" style="cursor:pointer;" onclick="showOrHide('project_user')" >
										<i class="icon-rss orange">
											修改人 (Update Peoples)
										</i>
									</h4>
									<div id="project_user" style="display: none;" >
										<div>
											<div class="clearfix">
												<!-- 用户给修改信息 -->
												<c:forEach items="${pus}" var="u">
													<div class="itemdiv memberdiv">
														<div class="user">
															<img alt="${u.userName}" src="${contextPath}/<c:if test="${u.picture == ''}" >assets/avatars/user.jpg</c:if><c:if test="${u.picture != ''}" >${u.picture}</c:if>" />
														</div>
														<div class="body">
															<div class="name">
																<a href="javascript:void(0)">${u.userName}</a>
															</div>
															<div class="time">
																<!-- <i class="icon-time"></i> -->
																<span class="green">[ ${u.deptName} ]</span>
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
										</div>
									</div>
									
								</div>
							</div>
									
							<!-- profile -->
							<div id="process" class="tab-pane">
								<!--  <p>这里发布跟踪信息</p>-->
								<c:forEach items="${user.pers}" var="p">
									<c:if test="${p.id == 'addProcess'}">
										<div>
											<div class="alert alert-info" style="display: none;" id ="process_msg_div";>
											<button type="button" class="close" data-dismiss="alert">
												<i class="icon-remove"></i>
											</button>
											<strong>[提示信息]</strong>
											<span id="process_msg"></span>
											<br>
											</div>
										
											<div class="page-header">
												<h1>发布跟踪信息
												<small>
													<i class="icon-double-angle-right">
													The Process 
													</i>
												</small>
												<button class="btn" style="float:right;margin-top: -8px;margin-left: 15px;"  onclick="downloadFile('${contextPath}/downloads/process_temple.xlsx')" ><i class="icon-pencil align-top bigger-125"></i>模板下载&nbsp;(Template Download)</button>	
												<button class="btn" style="float:right;margin-top: -8px;" onclick="fileSelect()" ><i class="icon-pencil align-top bigger-125"></i>上传跟踪信息&nbsp;(Upload Process Info)</button>
												<form action="" method="post" enctype="multipart/form-data" style="width:auto;" >
													<input type="file" name="upload" id="upload" onchange="fileSelected(this)" style="display:none;" />
												</form>
												<script type="text/javascript">
													function fileSelect(){
														document.getElementById("upload").click();
													}
													function fileSelected(obj){
														if(obj.value == ''){
															alert("请选择文件!");
															return;
														}
														var ext = obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
														if(ext == '.xls' || ext == '.xlsx'){
															$.ajaxFileUpload({
																url:'${contextPath}/process/upload?projectId=${project.id}',
																type:'post',
																secureuri:false,
																fileElementId:'upload',
																success:function(data,status){
																	data = eval("("+data+")");
																	$("#process_msg").html(data.message);
																	$("#process_msg_div").show();
																	$("#list").load("../process/list.htm?pu.pageNum=1&id=${project.id}")
																}
															});
														}else{
															alert("请选择一个Excel文件上传!");
														}
													}
												</script>
												</h1>
											</div>
											<div class="row">
												<div class="col-xs-12">
													<form action="../process/add.htm" role="form" class="form-horizontal" method="post" id="process_post" >
													
														<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="form-field-3"> 会谈对象</label>
														<div class="col-sm-9" >
															<input type="hidden" name="process.project.id" value="${project.id}" />
															<label style="margin-top:4px;color: red;" ><b>${project.customName}</b></label>
															<!-- 
															<select name="process.custom.id" class="width-20 chosen-select" data-placeholder="Choose a Custom..."  >
																<option value="">请选择</option>
																<c:forEach items="${customs}" var="c">
																	<option value="${c.id}">${c.name}</option>
																</c:forEach>
															</select>
															 -->
														</div>
														</div>
														<div class="space-4"></div>
													
														<div class="form-group" style="display: none;">
														<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 跟进人</label>
														<div class="col-sm-9" id="select-user" >
															<select onchange="selectUser(this)">
																<option value="">请选择</option>
																<c:forEach items="${depts}" var="d">
																	<option value="${d.id}">${d.name}</option>
																</c:forEach>
															</select> 
															<select id="process-user" name="process.processUser.id" class="width-20 chosen-select" data-placeholder="Choose a User..." >
																<option value="">请选择</option>
															</select>
															<script type="text/javascript">
																function selectUser(obj){
																	var list = [];
																	var index = obj.selectedIndex;
																	<c:forEach items="${depts}" var="d">
																		var child = [];
																		<c:forEach items="${d.users}" var="u">
																		child.push({"id":"${u.id}","name":"${u.userName}"});
																		</c:forEach>
																		list.push(child);
																	</c:forEach>
																	var text = "<select id='process-user' name='process.processUser.id' class='width-20 chosen-select' data-placeholder='Choose a User...' ><option value=''>请选择</option>";
																	//遍历list
																	if(index > 0){
																		for(var i=0;i<list[index-1].length;i++){
																			var child = list[index-1][i];
																			text += "<option value='"+child.id+"' >"+child.name+"</option>";
																		}
																	}
																	text += "</select>";
																	$("#process-user").remove();
																	$("#process_user_chosen").remove();
																	
																	$("#select-user").append(text);
																	
																	$("#process-user").chosen();
																	$(".chosen-container").css({"width":"200px","margin-top":"-3px"});
																}
															</script>
														</div>
														</div>
														<div class="space-4"></div>
														
														<div class="form-group">
															<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目需求</label>
															<div class="col-sm-9" >
																<select name="process.demand">
																	<option value="" >请选择</option>
																	<c:forEach items="${DEMANDS}" var="d">
																	<option value="${d.key}" >${d.value}</option>
																	</c:forEach>
																</select>
																<span style="color:#b399a6">&nbsp;&nbsp;Project Demand</span>
															</div>
														</div>
														<div class="space-4"></div>
														
														<div class="form-group">
															<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 会谈方式</label>
															<div class="col-sm-9" >
																<select name="process.method">
																	<option value="" >请选择</option>
																	<c:forEach items="${METHODS}" var="d">
																	<option value="${d.key}" >${d.value}</option>
																	</c:forEach>
																</select>
																<span style="color:#b399a6">&nbsp;&nbsp;Process Method</span>
															</div>
														</div>
														<div class="space-4"></div>
													
														<div class="form-group" style="padding:0 15px;">
														<h4 class="header green clearfix" >
															请输入会谈内容
															<small>Process Information </small>
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
														<div class="wysiwyg-editor" id="editor1" name="process.information"></div>
														</div>
														<div class="space-4"></div>
														
														<div class="clearfix form-actions" align="right">
															<div class="col-md-offset-3 col-md-9">
																<button class="btn btn-info" type="button" onclick="form_submit('process_post','list')">
																	<i class="icon-ok bigger-110"></i>
																	发布跟踪信息(Submit)
																</button>
															</div>
														</div>
														
													</form>
												</div>
											</div>
										</div>
										</c:if>
										</c:forEach>
										
										<div id="list">
											<!-- 我来组成列表 -->
										</div>
										
									</div>

									<div id="dropdown1" class="tab-pane">
										<!-- <p>这里显示所有文档资料</p> -->
										<div id="file_list">
										</div>
									</div>
									<!-- 
									<div id="dropdown2" class="tab-pane">
										<p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin.</p>
									</div> -->
								</div>
							</div>
					
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
		<script type="text/javascript">
			//批量上传
			function fileSubmit(formid,fileid,listid){
				var form = $("#"+formid);
				var sub_url = form.attr("action");
				var index = sub_url.lastIndexOf("id=");
				var idp = sub_url.substring(index);
				var index2 = idp.lastIndexOf("&");
				if(index2 > 0){
					idp = idp.substring(index2);
				}
				var file_input = form.find('input[type=file]');
				if(!file_input.data('ace_input_files')) return false;
				var deferred;
				
				if("FormData" in window){
					var fd = new FormData(form.get(0));
					if(file_input.data('ace_input_method')=='drop'){
						var files = file_input.data('ace_input_files');
						if(files && files.length > 0){
							fd.append(file_input.attr('name'),files[0]);
						}
					}
					upload_in_progress = true;
					
					deferred = $.ajax({
							url: sub_url,
							type: form.attr('method'),
							processData: false,
							contentType: false,
							dataType: 'json',
							data: fd,
							xhr: function() {
								var req = $.ajaxSettings.xhr();
								if (req && req.upload) {
									req.upload.addEventListener('progress', function(e) {
										if(e.lengthComputable) {	
											var done = e.loaded || e.position, total = e.total || e.totalSize;
											var percent = parseInt((done/total)*100) + '%';
											//percentage of uploaded file
										}
									}, false);
								}
								return req;
							},
							beforeSend : function() {
							},
							success : function() {
							}
						});
				}else{
					upload_in_progress = true;
					deferred = new $.Deferred;
					var iframe_id = 'temporary-iframe-'+(new Date()).getTime()+'-'+(parseInt(Math.random()*1000));
					$form.after('<iframe id="'+iframe_id+'" name="'+iframe_id+'" frameborder="0" width="0" height="0" src="about:blank" style="position:absolute;z-index:-1;"></iframe>');
					$form.append('<input type="hidden" name="temporary-iframe-id" value="'+iframe_id+'" />');
					$form.next().data('deferrer' , deferred);//save the deferred object to the iframe
					$form.attr({'method' : 'POST', 'enctype' : 'multipart/form-data',
								'target':iframe_id, 'action':sub_url});
					$form.get(0).submit();

					//if we don't receive the response after 60 seconds, declare it as failed!
					setTimeout(function(){
						var iframe = document.getElementById(iframe_id);
						if(iframe != null) {
							iframe.src = "about:blank";
							$(iframe).remove();	
							deferred.reject({'status':'fail','message':'Timeout!'});
						}
					} , 60000);
				}
							
				deferred.done(function(result){
					upload_in_progress = false;
					if(result.message == 'ok') {
						alert("恭喜您，文件上传成功!");
						$("#"+listid).load("../upload/list.htm?pu.pageNum=1&"+idp);
					}else {
						//发生错误
						alert("文件["+result.message+"]已存在，请更名后重新上传!");
					}
				}).fail(function(res){
					upload_in_progress = false;
					alert("There was an error");						
					//console.log(result.responseText);
				});
				deferred.promise();
							
				/*
				var loginName = "${loginName}";
				$.ajaxFileUpload({  
					url : 'upload/file.htm?loginName='+loginName,   //submit to UploadFileServlet  
			     	type: 'post',
					secureuri : false,  
					fileElementId : fileid,  
					dataType : 'json', //or json xml HTML whatever you like~
					success : function(data, status) { 
						$("#"+listid).load("upload/list.htm?pu.pageNum=1&loginName="+loginName);
				 	},  
			  		error : function(data, status, e) {  
					  	alert(e);
					}
				});  */
			}
			
			function project_update(formid,ajaxid,url){
				var rate = $("#"+formid+" select[name='project.rate']").val();
				var send = 0;
				if(rate == 'A'){
					if(confirm("当前项目为A类项目，是否需要发送通知邮件？")){
						send = 1;
					}
				}
				
				var action = $("#"+formid).attr("action")+"?send="+send;
				var alldata = $('#'+formid).serialize();
				$.ajax({
					type:"POST",
					url:action,
					data:alldata,
					error:function(request){
						alert("Connection Error");
					},
					success:function(data){
						var result = eval("("+data+")");
						alert(result.message);
						form_select(url,ajaxid);
					}
				});		
			}
			
		
			function form_update(formid,ajaxid,url){
				var action = $("#"+formid).attr("action");
				var alldata = $('#'+formid).serialize();
				$.ajax({
					type:"POST",
					url:action,
					data:alldata,
					dataType:'json',
					error:function(request){
						alert("Connection Error");
					},
					success:function(data){
						if(data.code == 0){
							ace_msg.success(data.msg);
							form_select(url,ajaxid);
						}else{
							ace_msg.danger(data.msg);
						}
						
					}
				});		
			}
		
			function form_update_init(url,id){
				if(typeof($("#project_post").attr("action")) != "undefined"){
					alert("清先保存修改!");
					return;
				}
				var scroll_offset = $("#"+id).offset();
				$("#"+id).empty();
				$("#"+id).load(url,function(){
					$("body,html").animate({
						scrollTop:scroll_offset.top
					},0);
				});
			}
			
			function form_select(url,id){
				var scroll_offset = $("#"+id).offset();
				$("#"+id).empty();
				$("#"+id).load(url,function(){
					$("body,html").animate({
						scrollTop:scroll_offset.top
					},0);
				});
			}
		
			function form_submit(id,ajaxid){
				var form = $("#"+id);
				if($("#"+id+" [name='process.demand']").val() == ''){
					alert("请选择项目需求!");
					return;
				}
				if($("#"+id+" [name='process.method']").val() == ''){
					alert("请选择会谈方式!");
					return;
				}
				submit_ajax(id,ajaxid);
			}
			
			function submit_ajax(id,ajaxid){
				var action = $("#"+id).attr("action");
				var alldata = $("#"+id).serialize();
				if($("#editor1")){
					var ename = $("#editor1").attr("name");
					var evalue = $("#editor1").html();
					alldata += "&" + ename + "=" + evalue;
				}
				$.ajax({
					type:"POST",
					url:action,
					data:alldata,
					error:function(request){
						alert("Connection Error");
					},
					success:function(data){
						alert("恭喜您，数据添加成功!");
						if($("#editor1")){
							$("#editor1").html("");
						}
						$("#"+ajaxid).html(data);
					}
				});
			}
			
			function showOrHide(idname){
				$("#"+idname).toggle();
			}	
			
			$(document).ready(function(){
				$("#list").load("../process/list.htm?pu.pageNum=1&id=${project.id}");
				$("#file_list").load("../upload/list.htm?pu.pageNum=1&id=${project.id}");
				
				$(".chosen-select").chosen(); 
				$(".chosen-container").css({"width":"200px","margin-top":"-3px"});
				
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