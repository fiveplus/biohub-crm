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
					<a href="${contextPath}/admin/statistics/select_time">数据统计</a>
				</li>
				<li>数据统计</li> 
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
						<h1>数据统计详情
						<small>
							<i class="icon-double-angle-right">
								Statistics Information
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
									项目统计 (Project Statistics)
								</a>
							</li>
						</ul>
						
						<div class="tab-content">
							<div id="home" class="tab-pane active">
								<!-- 这里是项目统计 -->
								<div>
									<table border="1" width="100%" cellspacing="0" cellpadding="10" >
										<tr>
											<td colspan="2">
												<div style="height:300px;width:100%" id="project_stat">
												</div>	
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<h4>录入统计</h4>
												（在选择时间段内创建项目的统计）
											</td>
										</tr>
										<tr>
											<td colspan="2">总数：${projectCount}</td>
										</tr>
										<c:forEach items="${projectDatastats}" var="pd">
											<tr>
												<td width="15%">${pd.name}</td>
												<td>${pd.count}</td>
											</tr>
										
										</c:forEach>
										<tr>
											<td colspan="2">
												<div style="height:300px;width:100%" id="project_update_stat">
												</div>	
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<h4>更新统计</h4>
												（在选择时间段内项目跟进人的统计）
											</td>
										</tr>
										<tr>
											<td colspan="2">总数：${projectUpdateCount}</td>
										</tr>
										<c:forEach items="${followDatastats}" var="fd">
											<tr>
												<td width="15%">${fd.name}</td>
												<td>${fd.count}</td>
											</tr>
										</c:forEach>
										<tr>
											<td colspan="2">
												<h4>个人更新日志</h4>
												（个人更新日志详情）
											</td>
										</tr>
										<tr>
											<td colspan="2" id="process_div">
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						
					</div>
					
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
		<script type="text/javascript">
			var ca,cb;
			function load_presses(page){
				$("#process_div").load("${contextPath}/admin/statistics/processes/"+page,{"dateRangePicker":'${dateRangePicker}'});
			}
			
			function load_projs(){
				ca = echarts.init(document.getElementById('project_stat'));
				var dateRangePicker = '${dateRangePicker}';
				$.ajax({
					url:"${contextPath}/admin/statistics/projs.json",
					data:{dateRangePicker:dateRangePicker},
					dataType:"json",
					type:"POST",
					success:function(data){
						var datas = data.datas;
						var xdata = new Array();
						var ydata = new Array();
						for(var i = 0;i < datas.length;i++){
							xdata.push(datas[i].name);
							ydata.push(datas[i].count);
						}
						var option = {
								title:{
									text:'录入统计图'
								},
								color:['#3398DB'],
								tooltip:{
									 trigger:'axis',
								     axisPointer:{            // 坐标轴指示器，坐标轴触发有效
								     	type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
								     }
								},
								legend:{
									data:[]
								},
								xAxis:{
									data:xdata
								},
					            yAxis: {},
					            series: [{
					                name: '录入量',
					                type: 'bar',
					                data: ydata
					            }]
						};	
						ca.setOption(option);
					},
					error:function(data){
						//console.log(data);
					}
				});
			}
			
			function load_procs(){
				cb = echarts.init(document.getElementById('project_update_stat'));
				var dateRangePicker = '${dateRangePicker}';
				$.ajax({
					url:"${contextPath}/admin/statistics/process.json",
					data:{dateRangePicker:dateRangePicker},
					dataType:"json",
					type:"POST",
					success:function(data){
						var datas = data.datas;
						var xdata = new Array();
						var ydata = new Array();
						for(var i = 0;i < datas.length;i++){
							xdata.push(datas[i].name);
							ydata.push(datas[i].count);
						}
						var option = {
								title:{
									text:'更新统计图'
								},
								color:['#3398DB'],
								tooltip:{
									 trigger:'axis',
								     axisPointer:{            // 坐标轴指示器，坐标轴触发有效
								     	type : 'shadow'     // 默认为直线，可选为：'line' | 'shadow'
								     }
								},
								legend:{
									data:[]
								},
								xAxis:{
									data:xdata
								},
					            yAxis: {},
					            series: [{
					                name: '更新量',
					                type: 'bar',
					                data: ydata
					            }]
						};	
						cb.setOption(option);
					},
					error:function(data){
						//console.log(data);
					}
				});
			}
		
			$(document).ready(function(){
				
				$("#process_div").load("${contextPath}/admin/statistics/processes/1",{"dateRangePicker":'${dateRangePicker}'});
				load_projs();
				load_procs();
				
				var a = document.getElementById('project_stat');
				var b = document.getElementById('project_update_stat');
				var home = document.getElementById('home');
			    window.onresize = function () {
			       var width = home.clientWidth - 20;
			       a.style.width = width + "px";
			       ca.resize();
			       b.style.width = width + "px";
			       cb.resize();
			    };
				
			});
		
			
			
		</script>
	</fms:Content>
</fms:ContentPage>