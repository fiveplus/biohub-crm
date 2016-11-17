<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<form action="${contextPath}/admin/project/update" role="form"
	class="form-horizontal" method="post" id="project_post">
	<!-- 项目信息 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			项目编号
		</label>
		<div class="col-sm-9">
			<input type="hidden" name="id" value="${project.id}" />
			<input type="text"  placeholder="项目编号" readonly="readonly" class="col-xs-10 col-sm-5" name="projectNum" value="${project.projectNum}"/>
			<span style="color:#b399a6">&nbsp;&nbsp;Project Number</span>
		</div>
	</div>
	<div class="space-4"></div>
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 
			创建人
		</label>
		<div class="col-sm-9" style="margin: 5px 0 0 -10px;">
			<label class="col-xs-10 col-sm-5">${project.createUser}</label>
			<span style="color:#b399a6">&nbsp;&nbsp;</span>
		</div>
	</div>
	<div class="space-4"></div>
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
			负责人
		</label>
		<div class="col-sm-9">
			<input type="text" placeholder="负责人" class="col-xs-10 col-sm-5" name="chargeUser" value="${project.chargeUser}"  />
			<span style="color:#b399a6">&nbsp;&nbsp;Charge User</span>
		</div>
	</div>
	<div class="space-4"></div>
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
			跟进人
		</label>
		<div class="col-sm-9" style="margin: 5px 0 0 -10px;">
			<label class="col-xs-10 col-sm-5">${project.followUser}</label>
			<span style="color:#b399a6">&nbsp;&nbsp;</span>
		</div>
	</div>
	<div class="space-4"></div>
	
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请选择项目类别 </label>
			<div class="col-sm-9">
				<select name="typeId">
					<c:forEach items="${types}" var="t">
						<c:if test="${project.typeId == t.id}">
							<option value="${t.id}" selected="selected">${t.name}</option>
						</c:if>
						<c:if test="${project.typeId != t.id}">
							<option value="${t.id}">${t.name}</option>
						</c:if>
					</c:forEach>
				</select>
				<span style="color:#b399a6">&nbsp;&nbsp;Project Type</span>
			</div>
		</div>
		<div class="space-4"></div>
		
		
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 项目分类级别 (Project Rate)</label>
		<div class="col-sm-9">
				<select name="rate">
					<c:forEach items="${RATES}" var="r">
						<c:if test="${project.rate == r.key}">
							<option value="${r.key}" selected="selected">${r.value}</option>
						</c:if>
						<c:if test="${project.rate != r.key}">
							<option value="${r.key}">${r.value}</option>
						</c:if>
					</c:forEach>
				</select>
				<span style="color:#b399a6">&nbsp;&nbsp;Project Rate</span>
		</div>
	</div>
	<div class="space-4"></div>	
	
	
	
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			 项目领域
		</label>
		<div class="col-sm-9">
			<select onchange="selectDomain(this)">
				<option value="">请选择</option>
				<c:forEach items="${parents}" var="p">
					<c:if test="${domain.parentId == p.id}">
						<option value="${p.id}" selected="selected">${p.name}</option>
					</c:if>
					<c:if test="${domain.parentId != p.id}">
						<option value="${p.id}" >${p.name}</option>
					</c:if>
				</c:forEach>
			</select>
			<select name="domainId" id="project-domain" >
				<option value="">请选择</option>
			</select>
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
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			项目名称
		</label>
		<div class="col-sm-9">
			<input type="text" placeholder="项目名称" class="col-xs-10 col-sm-5"  name="name" value="${project.name}" />
			<span style="color:#b399a6">&nbsp;&nbsp;Project Name</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			项目简介
		</label>
		<div class="col-sm-9">
			<input type="text" placeholder="项目简介" class="col-xs-10 col-sm-5" name="brief" value="${project.brief}" />
			<span style="color:#b399a6">&nbsp;&nbsp;Project Brief</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			项目阶段
		</label>
		<div class="col-sm-9">
			<select name="stage">
				<option value="">请选择</option>
				<c:forEach items="${STAGES}" var="s">
					<c:if test="${project.stage == s.key}">
						<option value="${s.key}" selected="selected">${s.value}</option>
					</c:if>
					<c:if test="${project.stage != s.key}">
						<option value="${s.key}" >${s.value}</option>
					</c:if>
				</c:forEach>
			</select>
			<span style="color:#b399a6">&nbsp;&nbsp;Project Stage</span>
		</div>
	</div>
	<div class="space-4"></div>
	<!-- 因需求重复，暂时关闭 -->
	<!-- 
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			项目特点
		</label>
		<div class="col-sm-9">
			<input type="text" placeholder="项目特点" class="col-xs-10 col-sm-5" name="project.trait" value="${project.trait}"/>
			<span style="color:#b399a6">&nbsp;&nbsp;Please enter project trait</span>
		</div>
	</div>
	<div class="space-4"></div>
	 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="form-field-1">
			项目需求
		</label>
		<div class="col-sm-9" style="height:25px;   line-height:25px;  ">
			<c:forEach items="${DEMANDS}" var="d">
				<c:if test="${fn:contains(project.demand,d.key)}">
					<input type="checkbox" name="demand" value="${d.key}" class="ace" checked="checked" /><span class="lbl"> ${d.value}</span>&nbsp;
				</c:if>
				<c:if test="${!fn:contains(project.demand,d.key)}">
					<input type="checkbox" name="demand" value="${d.key}" class="ace" /><span class="lbl"> ${d.value}</span>&nbsp;
				</c:if>
			</c:forEach>
			<span style="color:#b399a6">&nbsp;&nbsp;Project Demand</span>
		</div>
	</div>
	<div class="space-4"></div>
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-tags">请输入项目标签</label>
		<div class="col-sm-9">
			<input  type="text" name="projectTag" class="col-xs-10 col-sm-5" id="form-field-tags" value="${project.projectTag}" placeholder="Enter tags ..." />
		</div>
	</div>
	<div class="space-4"></div>
	
	
	<div class="clearfix form-actions">
		<div class="col-md-offset-3 col-md-9">
			<button class="btn btn-info" type="button" onclick="project_update('project_post','project','${contextPath}/admin/project/projectSelect/${project.id}')">
				<i class="icon-ok bigger-110"></i>
				提交(Submit)
			</button>
			&nbsp; &nbsp; &nbsp;
			<button class="btn" type="button" onclick="form_select('${contextPath}/admin/project/projectSelect/${project.id}','project')">
				<i class="icon-undo bigger-110"></i>
				返回(Back)
			</button>
		</div>
	</div>
</form>

<script type="text/javascript">
	$(document).ready(function(){
		var domainId = '${domain.id}';
		var parentId = '${domain.parentId}';
		$.ajax({
			url:"${contextPath}/admin/projectdomain/childs.json",
			data:{pid:parentId},
			type:"POST",
			dataType:'json',
			success:function(data){
				var childs = data.childs;
				var text = "<option value=''>请选择</option>";
				//遍历list
				for(var i = 0;i<childs.length;i++){
					var child = childs[i];
					if(child.id == domainId){
						text += "<option value='"+child.id+"' selected='selected' >"+child.name+"</option>";
					}else{
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