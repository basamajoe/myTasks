<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>
	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/home"/>">My Tasks</a>
			</div>
			<div class="navbar-collapse collapse">
				<!--<ul class="nav navbar-nav">
					<li class="active"><a href="<c:url value="/home"/>">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Dropdown <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Nav header</li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="../navbar/">Default</a></li>
					<li><a href="../navbar-static-top/">Static top</a></li>
					<li class="active"><a href="./">Fixed top</a></li>
				</ul>-->
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="!isAuthenticated()">
						<li><a href="<c:url value='/login'/>">Log in</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><sec:authentication
									property="principal.username" /> <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><sec:authorize access="isAuthenticated()">
										<a href="<c:url value="/j_spring_security_logout"/>">Log
											out</a>
									</sec:authorize></li>
							</ul></li>
					</sec:authorize>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<!-- end Fixed navbar -->
</header>