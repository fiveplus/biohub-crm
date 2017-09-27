<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<li class="active">
					<i class="icon-home home-icon"></i>
					Home
					<!-- <a href="javascript:void(0)">Home</a> -->
				</li>
				<!-- 
				<li>
					<a href="#">Other Pages</a>
				</li>
				<li class="active">Blank Page</li> -->
			</ul><!-- .breadcrumb -->
			<div class="nav-search" id="nav-search">
				<form class="form-search" action="" method="post" onsubmit="return false;" >
					<span class="input-icon">
						<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
						<i class="icon-search nav-search-icon"></i>
					</span>
				</form>
			</div><!-- #nav-search -->
		</div>
		<div class="page-content" style="padding:0;">
			<!-- PAGE CONTENT BEGINS -->
			<div class="table-responsive" style="padding:20px 20px 0 20px;">
				<table id="sample-table-2" class="table table-striped table-bordered table-hover">
					<tbody>
						<tr>
							<td class="center-top" style="background: #f4f5fa;">
								<div class="project-stat" >
									<div class="stat-top">
										<div class="stat-left">
											&nbsp;&nbsp;&nbsp;项目看板
										</div>
										<div class="stat-right">
											单位：个
										</div>
										<div class="clear"></div>
									</div>
									<br />
									<div id="canvasDiv" style="width:100%;height:300px;"></div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="center-top" style="background: #f4f5fa;">
								<div class="custom-stat">
									<div class="stat-top" style="margin-bottom:15px;">
										<div class="stat-left">
											&nbsp;&nbsp;&nbsp;客户看板
										</div>
										<div class="stat-right">
											单位：个
										</div>
										<div class="clear"></div>
									</div>
									<div id="canvasDiv2" style="width:100%;height:300px;"></div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="center-center">
				<div class="center-title">
					我的操作
				</div>
				<div class="table-responsive">
					<table  width="100%" cellspacing="0" cellpadding="20" frame="below" >
						<c:forEach items="${logs}" var="l" >
						<tr style="border-bottom: 1px solid #ededed;">
							<td>
								<date:date value="${l.createTime}" format="yyyy年MM月dd日 " />
								<date:date value="${l.createTime}" format="HH:mm " />
							</td>
							<td>
								${l.information}
							</td>
							<td style="color: #999;">
								操作成功
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div style="background: #f5f5fa;">
				<div class="bottom"></div>
			</div>
			<!-- PAGE CONTENT ENDS -->
		</div>
		<script type="text/javascript">
		$(document).ready(function(){
			//初始化
			var projChart = echarts.init(document.getElementById("canvasDiv"));
			var cusChart = echarts.init(document.getElementById("canvasDiv2"));
			
			$.get('${contextPath}/admin/custom/stat.json').done(function(data){
				var types = new Array();
				var datas = new Array();
				for(var i = 0;i<data.cusStats.length;i++){
					types.push(data.cusStats[i].name);
					datas.push({name:data.cusStats[i].name,value:data.cusStats[i].count});
				}
				var option = {
						title:{
							text:'百创汇客户来源',
							subtext:'客户分布',
							x:'center'
						},
						tooltip:{
							trigger:'item',
							formatter:"{a} <br>{b} : {c} ({d}%)"
						},
						legend:{
							orient:'vertical',
							left:'left',
							data:types
						},
						series:[{
							name:'客户来源',
							type:'pie',
							radius:'55%',
							center:['50%','60%'],
							data:datas,
							itemStyle:{
								emphasis:{
									shadowBlur:10,
									shadowOffsetX:0,
									showColor:'rgba(0,0,0,0.5)'
								}
							}
						}]
				};
				cusChart.setOption(option);
			});
			
			$.get('${contextPath}/admin/project/stat.json').done(function(data){
				var types = new Array();
				var datas = new Array();
				for(var i = 0;i<data.proStats.length;i++){
					types.push(data.proStats[i].name);
					datas.push({name:data.proStats[i].name,value:data.proStats[i].count});
				}
				var option = {
						title:{
							text:'百创汇项目来源',
							subtext:'领域分布',
							x:'center'
						},
						tooltip:{
							trigger:'item',
							formatter:"{a} <br>{b} : {c} ({d}%)"
						},
						legend:{
							orient:'vertical',
							left:'left',
							data:types
						},
						series:[{
							name:'项目来源',
							type:'pie',
							radius:'55%',
							center:['50%','60%'],
							data:datas,
							itemStyle:{
								emphasis:{
									shadowBlur:10,
									shadowOffsetX:0,
									showColor:'rgba(0,0,0,0.5)'
								}
							}
						}]
				};
				projChart.setOption(option);
			});
			
			window.onresize = function(){
				window.location.reload();
			};

		
		});
		</script>
	</fms:Content>
</fms:ContentPage>