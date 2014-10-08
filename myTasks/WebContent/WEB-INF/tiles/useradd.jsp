<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="body" class="col-md-10">
	<h1>Create New User</h1>
	<%@ include file="/WEB-INF/tiles/notifications.jsp"%>
	<form:form id="details" class="form-horizontal" method="POST"
		action="${pageContext.request.contextPath}/user/add"
		commandName="user" role="form">
		<div class="form-group">
			<label for="username" class="col-sm-2 control-label">Username</label>
			<div class="col-sm-10">
				<form:input path="username" name="username" type="text"
					class="form-control" id="username" placeholder="username" />
				<div id="username.error">
					<span class="text-danger"><form:errors path="username" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="aka" class="col-sm-2 control-label">Aka</label>
			<div class="col-sm-10">
				<form:input path="aka" name="aka" type="text" class="form-control"
					id="aka" placeholder="also known as" />
				<div id="aka.error">
					<span class="text-danger"><form:errors path="aka" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<form:input path="email" name="email" type="email"
					class="form-control" id="email" placeholder="email" />
				<div id="email.error">
					<span class="text-danger"><form:errors path="email" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<form:input name="password" path="password" type="password"
					class="form-control" id="password" placeholder="password" />
				<div id="password.error">
					<span class="text-danger"><form:errors path="password" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="confirmPassword" class="col-sm-2 control-label">Confirm
				Password</label>
			<div class="col-sm-10">
				<input name="confirmpass" type="password" class="form-control"
					id="confirmpass" placeholder="confirm password" />
				<div id="matchpass"></div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</div>
	</form:form>
</div>