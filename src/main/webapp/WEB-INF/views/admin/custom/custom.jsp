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
				<li class="active">
					<i class="icon-home home-icon"></i>
					<a href="${contextPath}/admin/index">Home</a>
				</li>
				
				<li>
					<a href="${contextPath}/admin/custom/list/1">客户管理</a>
				</li>
				<li class="active">客户详情</li> 
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
					<!-- 客户信息 -->
					<div class="page-header">
						<h1>客户详情
						<small>
							<i class="icon-double-angle-right">
							Custom Information
						</i>
						</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="form-group" >
								<label class="col-sm-3 control-label no-padding-right" > 客户姓名(Custom Name)</label>
								<div class="col-sm-9">
									<label class="col-xs-10 col-sm-5" style="margin-top: 4px;">${custom.name}</label>
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" > 联系电话(Telephone)</label>
								<div class="col-sm-9">
									<label class="col-xs-10 col-sm-5" style="margin-top: 4px;">${custom.telephone}</label>
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 电子邮箱(E-mail)</label>
								<div class="col-sm-9">
									<label class="col-xs-10 col-sm-5" style="margin-top: 4px;">${custom.email}</label>
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> QQ</label>
								<div class="col-sm-9">
									<label class="col-xs-10 col-sm-5" style="margin-top: 4px;">${custom.qq}</label>
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 微信(Wechat)</label>
									<div class="col-sm-9">
									<label class="col-xs-10 col-sm-5" style="margin-top: 4px;">${custom.wechat}</label>
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Skype</label>
								<div class="col-sm-9">
									<label class="col-xs-10 col-sm-5" style="margin-top: 4px;">${custom.skype}</label>
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
								<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Linked-in</label>
								<div class="col-sm-9">
									<label class="col-xs-10 col-sm-5" style="margin-top: 4px;">${custom.linkedin}</label>
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 单位(Company)</label>
								<div class="col-sm-9">
									<label class="col-xs-10 col-sm-5" style="margin-top: 4px;">${custom.company}</label>
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 网址(websize)</label>
								<div class="col-sm-9">
									<label class="col-xs-10 col-sm-5" style="margin-top: 4px;">${custom.websize}</label>
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
					
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 拥有项目(Custom's projects)</label>
								<div class="col-sm-9">
									<label class="col-xs-10 col-sm-10" style="margin-top: 4px;">
										<c:forEach items="${projects}" var="p">
											<span onclick="go_url('${contextPath}/admin/project/select/${p.id}')" style="margin:5px;" class="btn btn-info btn-sm tooltip-info" data-rel="tooltip" title="创建时间：<date:date value="${p.createTime}" />">${p.name}</span>
										</c:forEach>
									</label>
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
					
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<c:forEach items="${user.pers}" var="p">
										<c:if test="${p.id == 'addProject'}">
											<button class="btn btn-info" type="button" onclick="go_url('${contextPath}/admin/project/add?customId=${custom.id}')">
											<i class="icon-ok bigger-110"></i>
											创建项目(Create Project)
											</button>
											&nbsp; &nbsp; &nbsp;
										</c:if>
									</c:forEach>
									
									<button class="btn" type="reset" onclick="go_back()">
										<i class="icon-undo bigger-110"></i>
										返回(Back)
									</button>
								</div>
							</div>
							
						</div>
					</div>
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
					
		<script type="text/javascript">
			$(document).ready(function(){
				$('[data-rel=tooltip]').tooltip();
			});
		</script>
	
	</fms:Content>
</fms:ContentPage>