<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
	<label class="col-sm-3 control-label no-padding-right" for="form-field-1">负责人 (Charge User)</label>
		<div class="col-sm-9" style="margin-top: 5px;">
			<label class="col-xs-10 col-sm-5">${project.chargeUser}</label>
		</div>
</div>&nbsp;
<div class="space-4"></div>
	
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
		跟进人 (Follow User)
	</label>
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