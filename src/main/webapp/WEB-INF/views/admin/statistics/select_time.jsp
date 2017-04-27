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
				<li class="active">数据统计</li> 
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
					<br />
					<form action="${contextPath}/admin/statistics/statistics" method="post" id="form_post">
						<div class="well well-lg">
							<h4 class="blue">数据统计（请选择起止时间）</h4>
							<div>
								<div class="input-group">
									<span class="input-group-addon">
										<i class="icon-calendar bigger-110"></i>
									</span>
									<input style="width:180px;" class="form-control" type="text" readonly="readonly" name="dateRangePicker" id="id-date-range-picker-1" value="${dateRangePicker}" />
									 <span style="color:#b399a6">&nbsp;&nbsp; (Time Period.)</span>
								</div>
							</div>
							<br>
							
							<div>
								<button class="btn btn-info" type="button" onclick="form_submit('form_post')">
									<i class="icon-ok bigger-110"></i>
									数据统计(statistics)
								</button>
							</div>
						</div>
					</form>
					
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
		<script type="text/javascript">
			function form_submit(id){
				var form = $("#"+id);
				
				if($("#"+id+" [name='dateRangePicker']").val() == ''){
					ace_msg.danger("错误！先选择起止时间！");
					return;
				}
				
				form.submit();
			}
			$(document).ready(function(){
				$('input[name=dateRangePicker]').daterangepicker().prev().on(ace.click_event, function(){
					$(this).next().focus();
				});
			});
		</script>
	</fms:Content>
</fms:ContentPage>