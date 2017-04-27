<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 客户基本信息 -->
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 客户姓名 (Custom Name)</label>
	<div class="col-sm-9" style="margin-top: 5px;">
		<label class="col-xs-10 col-sm-10"><a href="${contextPath}/admin/custom/select/${custom.id}">${custom.name}</a></label>
	</div>
</div>&nbsp;
<div class="space-4"></div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 联系电话 (Telephone)</label>
	<div class="col-sm-9" style="margin-top: 5px;">
		<label class="col-xs-10 col-sm-10">${custom.telephone}</label>
	</div>
</div>&nbsp;
<div class="space-4"></div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 电子邮箱 (E-mail)</label>
	<div class="col-sm-9" style="margin-top: 5px;">
		<label class="col-xs-10 col-sm-10">${custom.email}</label>
	</div>
</div>&nbsp;
<div class="space-4"></div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> QQ</label>
	<div class="col-sm-9" style="margin-top: 5px;">
		<label class="col-xs-10 col-sm-10">${custom.qq}</label>
	</div>
</div>&nbsp;
<div class="space-4"></div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 微信 (Wechat)</label>
	<div class="col-sm-9" style="margin-top: 5px;">
		<label class="col-xs-10 col-sm-10">${custom.wechat}</label>
	</div>
</div>&nbsp;
<div class="space-4"></div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Skype</label>
	<div class="col-sm-9" style="margin-top: 5px;">
		<label class="col-xs-10 col-sm-10">${custom.skype}</label>
	</div>
</div>&nbsp;
<div class="space-4"></div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Linked-in</label>
	<div class="col-sm-9" style="margin-top: 5px;">
		<label class="col-xs-10 col-sm-10">${custom.linkedin}</label>
	</div>
</div>&nbsp;
<div class="space-4"></div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 单位 (Company)</label>
	<div class="col-sm-9" style="margin-top: 5px;">
		<label class="col-xs-10 col-sm-10">${custom.company}</label>
	</div>
</div>&nbsp;
<div class="space-4"></div>
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 网址 (Websize)</label>													<div class="col-sm-9" style="margin-top: 5px;">
		<label class="col-xs-10 col-sm-10">${custom.websize}</label>
	</div>
</div>&nbsp;
<div class="space-4"></div>