<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="date" uri="/date-tag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
										<c:if test="${pcount > 0}">
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
													<div id="canvasDiv"></div>
												</div>
											</td>
										</tr>
										</c:if>
										<c:if test="${ccount > 0}">
											<tr>
												<td class="center-top" style="background: #f4f5fa;">
													<div class="custom-stat">
														<div class="stat-top">
															<div class="stat-left">
																&nbsp;&nbsp;&nbsp;客户看板
															</div>
															<div class="stat-right">
																单位：个
															</div>
															<div class="clear"></div>
														</div>
														<div id="canvasDiv2"></div>
													</div>
												</td>
											</tr>
										</c:if>
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
						
					</div><!-- /.page-content -->
					
		<script type="text/javascript">
			$(function(){
			
			var data = [
				<c:forEach items="${proStats}" var="ps" varStatus="status">
					{name : '${ps.name}',value : ${ps.count},color:'${ps.color}'}
					<c:if test="${!status.last}">,</c:if>	
				</c:forEach>
			];
			var count = 0;
			for(var i = 0;i<data.length;i++){
				count += data[i].value;
			}
			
			
			var data2 = [
			<c:forEach items="${cusStats}" var="cs" varStatus="status">
				{name : '${cs.name}',value : ${cs.count},color:'${cs.color}'}
				<c:if test="${!status.last}">,</c:if>	
			</c:forEach>
			];
			var count2 = 0;
			for(var i = 0;i<data2.length;i++){
				count2 += data2[i].value;
			}
				
			if(count > 0){
				var chart = new iChart.Donut2D({
				render : 'canvasDiv',
				border:false,
				center:{
					text:'项目总数\n'+count,
					shadow:true,
					shadow_offsetx:0,
					shadow_offsety:2,
					shadow_blur:2,
					shadow_color:'#b7b7b7',
					color:'#6f6f6f'
				},
				data: data,
				offsetx:-80,
				shadow:true,
				background_color:'#f5f5fa',
				separate_angle:10,//分离角度
				tip:{
					enable:true,
					showType:'fixed'
				},
				legend : {
					enable : true,
					shadow:true,
					background_color:null,
					border:false,
					legend_space:30,//图例间距
					line_height:34,//设置行高
					sign_space:10,//小图标与文本间距
					sign_size:30,//小图标大小
					color:'#6f6f6f',
					fontsize:15//文本大小
				},
				sub_option:{
					label:false,
					color_factor : 0.3
				},
				showpercent:true,
				decimalsnum:2,
				width : 500,
				height : 300,
				radius:140
				});
				chart.draw();
			}
	    	
			
			
			if(count2 > 0){
				var chart2 = new iChart.Donut2D({
				render : 'canvasDiv2',
				border:false,
				center:{
					text:'客户总数\n'+count2,
					shadow:true,
					shadow_offsetx:0,
					shadow_offsety:2,
					shadow_blur:2,
					shadow_color:'#b7b7b7',
					color:'#6f6f6f'
				},
				data: data2,
				offsetx:-80,
				shadow:true,
				background_color:'#f5f5fa',
				separate_angle:10,//分离角度
				tip:{
					enable:true,
					showType:'fixed'
				},
				legend : {
					enable : true,
					shadow:true,
					background_color:null,
					border:false,
					legend_space:30,//图例间距
					line_height:34,//设置行高
					sign_space:10,//小图标与文本间距
					sign_size:30,//小图标大小
					color:'#6f6f6f',
					fontsize:15//文本大小
				},
				sub_option:{
					label:false,
					color_factor : 0.3
				},
				showpercent:true,
				decimalsnum:2,
				width : 500,
				height : 300,
				radius:140
				});
				chart2.draw();
			}
			
			
			

			/**
			 *利用自定义组件构造左侧说明文本。
			 */
			 
		//	chart.plugin(new iChart.Custom({
		//			drawFn:function(){
		//				/**
		//				 *计算位置
		//				 */	
		//				var y = chart.get('originy');
		//				/**
		//				 *在左侧的位置，设置竖排模式渲染文字。
		//				 */
		//				chart.target.textAlign('center')
		//				.textBaseline('middle')
		//				.textFont('600 24px 微软雅黑')
		//				.fillText('攻城师需要掌握的核心技能',100,y,false,'#6d869f', 'tb',26,false,0,'middle');
		//				
		//			}
		//	}));
			
			
			
		});
	

</script>
