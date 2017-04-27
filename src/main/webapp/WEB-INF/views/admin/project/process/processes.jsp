<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="date" uri="/date-tag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="page-header">
	<h1>历史跟踪记录
		<small>
			<i class="icon-double-angle-right">
			The Process List
			</i>
		</small>
		
		<c:forEach items="${user.pers}" var="p">
			<c:if test="${p.id == 'exportProject'}">
				<button style="float: right;" type="button" class="btn btn-purple btn-sm" onclick="exportJSON('${contextPath}/admin/process/export','process_select_post')" >
				<i class="icon-pencil align-top bigger-125"></i>
				数据导出 (Data Export)
				</button>
			</c:if>
		</c:forEach>
		
		
	</h1>
</div>
<form action="${contextPath}/admin/process/export" method="post" id="process_select_post">
	<input type="hidden" name="projectId" value="${projectId}"   />		
</form>
	<div id="accordion" class="accordion-style1 panel-group">
		<c:forEach items="${pu.list}" var="p" varStatus="stat">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse${stat.index}">
							<i class="bigger-110 icon-angle-down" data-icon-hide="icon-angle-down" data-icon-show="icon-angle-right"></i>
							&nbsp;<date:date value="${p.createTime}" /> [${p.createUser}]
						</a>
					</h4>
				</div>
				<div class="panel-collapse <c:if test="${stat.index == 0 }">in</c:if>" id="collapse${stat.index}" style="height: <c:if test="${stat.index == 0 }">auto</c:if><c:if test="${stat.index > 0 }">0</c:if>;">
					<div class="panel-body">
						<div>
							<label>会谈对象：${p.customName}</label>
						</div>
						<div>
							<label>会谈方式：${p.method}</label>
						</div>
						<br/>
						<div>
							<label>项目需求：${p.demand}</label>
						</div>
						<hr/>
						<div>
							${p.information}
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
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
				<a href="javascript:load_list('list','${contextPath}/admin/process/list/1?projectId=${projectId}')">
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
					<a href="javascript:load_list('list','${contextPath}/admin/process/list/${p}?projectId=${projectId}')">${p}</a>
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
				<a href="javascript:load_list('list','${contextPath}/admin/process/list/${pu.lastPage}?projectId=${projectId}')">
					<i class="icon-double-angle-right"></i>
				</a>
			</li>
		</c:if>
	</ul>
</div>