<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="date" uri="/date-tag" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<div class="navbar-container" id="navbar-container">
	<div class="navbar-header pull-left">
		<a href="javascript:void(0)" class="navbar-brand">
		<small><i class="icon-leaf"></i>CRM Admin</small>
		</a><!-- /.brand -->
	</div><!-- /.navbar-header -->
	
<div class="navbar-header pull-right" role="navigation">
	<ul class="nav ace-nav">
		<li class="green">
			<a data-toggle="dropdown" class="dropdown-toggle" href="#">
				<i class="icon-envelope icon-animated-vertical"></i>
				Chinese-English Helps
			</a>
		<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close" >
			<li class="dropdown-header">
				<i class="icon-envelope-alt"></i>
				Chinese-English Helps
			</li>
			<li>
				<a href="javascript:see_help('custom-location-help')" >
					<img src="${contextPath}/assets/head/custom-location-help.jpg" class="msg-photo" alt="" />
					<span class="msg-body">
						<span class="msg-title">
							<span class="blue">Customer's Area:</span>
							客户区域
						</span>
					</span>
				</a>
			</li>
			<li>
				<a href="javascript:see_help('project-type-help')">
					<img src="${contextPath}/assets/head/project-type-help.jpg" class="msg-photo" alt="" />
					<span class="msg-body">
						<span class="msg-title">
							<span class="blue">Project Type:</span>
							项目类别
						</span>
					</span>
				</a>
			</li>
			<li>
				<a href="javascript:see_help('project-domain-help')">
					<img src="${contextPath}/assets/head/project-domain-help.jpg" class="msg-photo" alt="" />
					<span class="msg-body">
						<span class="msg-title">
							<span class="blue">Project Domain:</span>
							项目领域
						</span>
					</span>
				</a>
			</li>
			<li>
				<a href="javascript:see_help('project-stage-help')">
					<img src="${contextPath}/assets/head/project-stage-help.jpg" class="msg-photo" alt="" />
					<span class="msg-body">
						<span class="msg-title">
							<span class="blue">Project Stage:</span>
							项目阶段
						</span>
					</span>
				</a>
			</li>
			<li>
				<a href="javascript:see_help('project-demand-help')">
					<img src="${contextPath}/assets/head/project-demand-help.jpg" class="msg-photo" alt="" />
					<span class="msg-body">
						<span class="msg-title">
							<span class="blue">Project Demand:</span>
							项目需求
						</span>
					</span>
				</a>
			</li>
			<li>
				<a href="javascript:see_help('meeting-way-help')">
					<img src="${contextPath}/assets/head/meeting-way-help.jpg" class="msg-photo" alt="" />
					<span class="msg-body">
						<span class="msg-title">
							<span class="blue">Meeting Way :</span>
							会谈方式
						</span>
					</span>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					See all helps
					<i class="icon-arrow-right"></i>
				</a>
			</li>
		</ul>
	</li>
						<!-- 
						<li class="grey">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-tasks"></i>
								<span class="badge badge-grey">4</span>
							</a>

							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="icon-ok"></i>
									4 Tasks to complete
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Software Update</span>
											<span class="pull-right">65%</span>
										</div>

										<div class="progress progress-mini ">
											<div style="width:65%" class="progress-bar "></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Hardware Upgrade</span>
											<span class="pull-right">35%</span>
										</div>

										<div class="progress progress-mini ">
											<div style="width:35%" class="progress-bar progress-bar-danger"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Unit Testing</span>
											<span class="pull-right">15%</span>
										</div>

										<div class="progress progress-mini ">
											<div style="width:15%" class="progress-bar progress-bar-warning"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Bug Fixes</span>
											<span class="pull-right">90%</span>
										</div>

										<div class="progress progress-mini progress-striped active">
											<div style="width:90%" class="progress-bar progress-bar-success"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										See tasks with details
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
						
						
						<li class="purple">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-bell-alt icon-animated-bell"></i>
								<span class="badge badge-important">8</span>
							</a>

							<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="icon-warning-sign"></i>
									8 Notifications
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
												New Comments
											</span>
											<span class="pull-right badge badge-info">+12</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<i class="btn btn-xs btn-primary icon-user"></i>
										Bob just signed up as an editor ...
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
												New Orders
											</span>
											<span class="pull-right badge badge-success">+8</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-info icon-twitter"></i>
												Followers
											</span>
											<span class="pull-right badge badge-info">+11</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										See all notifications
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
 						-->
 						
 						<!-- 暂代开发 -->
						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-envelope icon-animated-vertical"></i>
								<span class="badge badge-success" id="logsize_span">${logCount}</span>
							</a>

							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close" id="temp_message">
								<li class="dropdown-header">
									<i class="icon-envelope-alt"></i>
									${logCount} Messages
								</li>
								<c:forEach items="${logs}" var="log">
									<li>
									<a href="javascript:void(0)">
										<img src="<c:if test="${log.picture != ''}">${log.picture}</c:if><c:if test="${log.picture == ''}">assets/avatars/avatar.png</c:if>" class="msg-photo" alt="" />
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">${log.userName}:</span>
												${fn:substring(log.information,fn:indexOf(log.information,']')+1,23)}...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												 <span><date:date value="${log.createTime}" /></span>
											</span>
										</span>
									</a>
									</li>
								</c:forEach>
								<li>
									<a href="javascript:void(0)">
										See all messages
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
						 
	<li class="light-blue">
		<a data-toggle="dropdown" href="#" class="dropdown-toggle">
			<img class="nav-user-photo" src="../<c:if test="${user.picture == ''}" >assets/avatars/user.jpg</c:if><c:if test="${user.picture != ''}" >${user.picture}</c:if>" alt="" />
			<span class="user-info">
				<small>Welcome,</small>
				${user.userName}
			</span>
			<i class="icon-caret-down"></i>
		</a>
		<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
			<li>
				<a  href="../user/updateUserInit.htm?id=${user.id}"  >
					<i class="icon-cog"></i>
					Settings
				</a>
			</li>
			<li>
				<a href="../project/mylist.htm?pu.pageNum=1" >
					<i class="icon-user"></i>
					Projects
				</a>					
			</li>
			<li class="divider"></li>
			<li>
				<a href="javascript:logout()">
					<i class="icon-off"></i>
					Logout
				</a>
			</li>
		</ul>
	</li>
</ul><!-- /.ace-nav -->
</div><!-- /.navbar-header -->
</div><!-- /.container -->
<div id="help-hide" style="display: none;"></div>
			
<script type="text/javascript">
	$(document).ready(function(){
		//init();
		/*$("#help-hide").load("help/project-domain.html",function(){
			var html = $("#help-hide").html();
			$('#gritter-regular').on(ace.click_event, function(){
		$.gritter.add({
			title: 'Chinese-English Help',
			text: html,
			image: 'assets/avatars/avatar1.png',
			sticky: false,
			time: '',
			class_name: ('')
			});
			return false;
		});
		});*/
	});
        		
	function see_help(value){
		$("#help-hide").load("../help/"+value+".html",function(){
			var html = $("#help-hide").html();
			$.gritter.add({
				title: 'Chinese-English Help',
				text: html,
				image: '../assets/head/'+value+'.jpg',
				sticky: false,
				time: '',
				class_name: ('')
			});
		});
	}
        		
        		
	/* 
	* 消息监听初始化 
	**/
	function init(){
		var logsize_span = document.getElementById('logsize_span');
		var temp_message = document.getElementById('temp_message');
		JS.Engine.stop();
		JS.Engine.on({
			msg_time : function(data){//侦听一个channel
			var res = eval("("+data+")");
			var size = res.count == 0 ? "" : ""+res.count;
			logsize_span.innerHTML = size;
							
			var top = "<li class='dropdown-header'><i class='icon-envelope-alt'></i>"+res.count+" Messages</li>";
			var bottom = "<li><a href='javascript:void(0)'>See all messages<i class='icon-arrow-right'></i></a></li>";
							
			temp_message.innerHTML = top + getlilist(res.logs) + bottom;
		},
			test2 : function(bb){
				//kbDom2.innerHTML = bb;
			}
		});
		JS.Engine.start('comet'); 
	}
        		
	function getlilist(logs){
		var list = "";
			for(var i = 0;i<logs.length;i++){
				list += getli(logs[i]);
			}
		return list;
	}
	function getli(log){
		var time = log.info.substr(0,19);
		var info = log.info.substr(log.info.indexOf(']')+1,30);
		var li = "<li>"
				+"<a href='javascript:void(0)'>"
				+"<img src='"+log.picture+"' class='msg-photo' alt='"+log.userName+"' />"
				+"<span class='msg-body'>"
				+"<span class='msg-title'>"
				+"<span class='blue'>"+log.userName+"：</span>"
				+info+"..."
				+"</span>"
				+"<span class='msg-time'>"
				+"<i class='icon-time'></i> "
				+"<span>"+time+"</span>"
				+"</span>"
				+"</span>"
				+"</a>"
				+"</li>";
		return li;
	}	
	function logout(){
		//JS.Engine.stop();
		window.location.href = "${contextPath}/admin/logout";
	}
</script>