<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/master-tag" prefix="fms" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/date-tag" prefix="date" %>
<div class="table-header">
	共有${pu.total}条数据&nbsp;(A total of ${pu.total} records)
</div>
<div class="table-responsive">
	<table id="sample-table-2" class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>客户姓名</th>
				<th>项目名称</th>
				<th>内容</th>
				<th>
					<i class="icon-time bigger-110 hidden-480"></i>
					时间
				</th>
				<th class="hidden-480">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pu.list}" var="p">
				<tr>
					<td>
						${p.customName}
					</td>
					<td>
						${p.projectName}
					</td>
					<td>
						${p.information}
					</td>
					<td><date:date value="${p.createTime}" /></td>
					<td class="hidden-480">
						<span class="label label-sm label-success">创建成功</span>
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
				<a href="javascript:load_presses(1)">
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
					<a href="javascript:load_presses(${p})">${p}</a>
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
				<a href="javascript:load_presses(${pu.lastPage})">
					<i class="icon-double-angle-right"></i>
				</a>
			</li>
		</c:if>
	</ul>
</div>
