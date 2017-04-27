<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<form action="${contextPath}/admin/custom/update" role="form"
	class="form-horizontal" method="post" id="project_post">
	<!-- 客户基本信息 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			客户姓名
		</label>
		<div class="col-sm-9">
			<input type="hidden" name="id" value="${custom.id}"  />
			<input type="text"  placeholder="客户姓名" class="col-xs-10 col-sm-5" name="name" value="${custom.name}"/>
			<span style="color:#b399a6">&nbsp;&nbsp;Customer's Name</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			联系电话
		</label>
		<div class="col-sm-9">
			<input type="text"  placeholder="联系电话" class="col-xs-10 col-sm-5" name="telephone" value="${custom.telephone}" />
			<span style="color:#b399a6">&nbsp;&nbsp;Phone Number</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			电子邮箱
		</label>
		<div class="col-sm-9">
			<input type="text" placeholder="电子邮箱" class="col-xs-10 col-sm-5"  name="email" value="${custom.email}" />
			<span style="color:#b399a6">&nbsp;&nbsp;E-mail Address</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			QQ
		</label>
		<div class="col-sm-9">
			<input type="text" placeholder="QQ" class="col-xs-10 col-sm-5" name="qq" value="${custom.qq}" />
			<span style="color:#b399a6">&nbsp;&nbsp;QQ Number</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			微信
		</label>
		<div class="col-sm-9">
			<input type="text" placeholder="微信" class="col-xs-10 col-sm-5" name="wechat" value="${custom.wechat}"/>
			<span style="color:#b399a6">&nbsp;&nbsp;Wechat ID</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			Skype
		</label>
		<div class="col-sm-9">
			<input type="text" placeholder="Skype" class="col-xs-10 col-sm-5" name="skype" value="${custom.skype}"/>
			<span style="color:#b399a6">&nbsp;&nbsp;Skype ID</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			Linked-in
		</label>
		<div class="col-sm-9">
			<input type="text" placeholder="Linked-in" class="col-xs-10 col-sm-5" name="linkedin" value="${custom.linkedin}" />
			<span style="color:#b399a6">&nbsp;&nbsp;Linked-in Address</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			单位
		</label>
		<div class="col-sm-9">
			<input type="text" placeholder="单位" class="col-xs-10 col-sm-5" name="company" value="${custom.company}"/>
			<span style="color:#b399a6">&nbsp;&nbsp;Company Name</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			网址
		</label>
		<div class="col-sm-9">
			<input type="text" placeholder="网址" class="col-xs-10 col-sm-5" name="websize" value="${custom.websize}"/>
			<span style="color:#b399a6">&nbsp;&nbsp;Company Websize</span>
		</div>
	</div>
	<div class="space-4"></div>
	
	<div class="clearfix form-actions">
		<div class="col-md-offset-3 col-md-9">
			<button class="btn btn-info" type="button" onclick="form_update('project_post','custom','${contextPath}/admin/project/customSelect/${custom.id}')">
				<i class="icon-ok bigger-110"></i>
				提交(Submit)
			</button>
			&nbsp; &nbsp; &nbsp;
			<button class="btn" type="button" onclick="form_select('${contextPath}/admin/project/customSelect/${custom.id}','custom')">
				<i class="icon-undo bigger-110"></i>
				返回(Back)
			</button>
		</div>
	</div>
	<script type="text/javascript">
	</script>
</form>