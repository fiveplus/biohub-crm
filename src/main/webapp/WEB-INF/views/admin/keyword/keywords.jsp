<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/master-tag" prefix="fms" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/date-tag" prefix="date" %>
<fms:ContentPage masterPageId="master">
	<fms:Content contentPlaceHolderId="title">
		Customer Relationship Management
	</fms:Content>
		
	<fms:Content contentPlaceHolderId="source">
		<!-- 按需加载模块 -->
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
				<li class="active">关键词管理</li>
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
							<span>关键词列表</span>
							<button class="btn" style="float:right;margin-top: -12px;" onclick="go_url('${contextPath}/admin/keyword/add')" >
								<i class="icon-pencil align-top bigger-125"></i>关键词新增 (KeyWord Add)
							</button>
							</h3>
							<div class="table-header">
								共有${pu.total}条数据
							</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">
												<label>
													<input type="checkbox" class="ace" onclick="checkAll(this)" />
													<span class="lbl"></span>
												</label>
											</th>
											<th>ID</th>
											<th>关键词名称</th>
											<th class="hidden-480">使用次数</th>
											<th>
												<i class="icon-time bigger-110 hidden-480"></i>
												创建时间
											</th>
											<th class="hidden-480">状态</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${pu.list}" var="k" >
										<tr>
											<td class="center">
												<label>
													<input type="checkbox" class="ace" name="checks" />
													<span class="lbl"></span>
												</label>
											</td>

											<td>
												<a href="javascript:void(0)">${k.id}</a>
											</td>
											<td>${k.name}</td>
											<td class="hidden-480">${k.useCount}</td>
											<td><date:date value="${k.createTime}" /></td>

											<td class="hidden-480">
												<span class="label label-sm label-success">已创建</span>
											</td>

											<td>
												<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
													<a class="blue" href="javascript:void(0)">
														<i class="icon-zoom-in bigger-130"></i>
													</a>

													<a class="green" href="${contextPath}/admin/keyword/upt/${k.id}">
														<i class="icon-pencil bigger-130"></i>
													</a>

													<a class="red" href="javascript:void(0)">
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
																<a href="javascript:void(0)" class="tooltip-info" data-rel="tooltip" title="View">
																	<span class="blue">
																		<i class="icon-zoom-in bigger-120"></i>
																	</span>
																</a>
															</li>

															<li>
																<a href="${contextPath}/keyword/upt/${k.id}" class="tooltip-success" data-rel="tooltip" title="Edit">
																	<span class="green">
																		<i class="icon-edit bigger-120"></i>
																	</span>
																</a>
															</li>

															<li>
																<a href="javascript:void(0)" class="tooltip-error" data-rel="tooltip" title="Delete">
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
											<a href="${contextPath}/admin/keyword/list/1">
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
												<a href="${contextPath}/admin/keyword/list/${p}">${p}</a>
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
											<a href="${contextPath}/admin/keyword/list/${pu.lastPage}">
												<i class="icon-double-angle-right"></i>
											</a>
										</li>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	
	</fms:Content>
</fms:ContentPage>