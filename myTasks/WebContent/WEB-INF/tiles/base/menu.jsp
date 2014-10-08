<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="menu" class="col-md-2">
	Menu 1<br /> Menu 2<br /> Menu 3<br />
	<p>
		<a href="${pageContext.request.contextPath}/task/lst">Show current
			tasks</a>
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/task/add">Create task</a>
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/user/add">Create user</a>
	</p>
	<p>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="<c:url value='/admin' />">Admin</a>
		</sec:authorize>
	</p>
	<p>
		<sec:authorize access="!isAuthenticated()">
			<a href="<c:url value='/login'/>">Log in</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<a href="<c:url value='/j_spring_security_logout'/>">Log out</a>
		</sec:authorize>
	</p>
</div>