<%@ page contentType="text/html;charset=utf-8" %>

<div class="sidebar" data-background-color="brown"
	data-active-color="danger">
	<div class="logo" style="text-align:center;">
		<!-- <a href="/" class="simple-text">
			WaGoodLiker</a> -->
		<a href="/"><img src="/resources/img/logo.png" style="height:45px;"/></a>
	</div>
	<div class="sidebar-wrapper">
		<div class="user">
			<div class="info">
				<a data-toggle="collapse" href="#collapseExample" class="collapsed">
					${userId} <b class="caret"></b>
				</a>
				<div class="collapse" id="collapseExample">
					<ul class="nav">
						<li><a href="">Profile</a></li>
						<li><a href="/auth/logout">Logout</a></li>
					</ul>
				</div>
			</div>
		</div>
		<ul class="nav">
			<li class="<% if(request.getAttribute("menu") == "Main"){ %>active<%} %>">
			<a href="/admin">
			<i class="ti-user"></i>
					<p>메인</p>
			</a></li>
			<%-- <li class="<% if(request.getAttribute("menu") == "Editor"){ %>active<%} %>">
			<a href="/admin/editor">
			<i class="ti-user"></i>
					<p>공지사항 작성</p>
			</a></li> --%>
		</ul>
	</div>
</div>
