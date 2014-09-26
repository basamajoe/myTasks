<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--<form:hidden path="action.idTaskAction" />
<form:hidden path="task.idTask" />
<div class="form-group">--%>
	<span>Actionname: {{action.actionname}}</span>
	<span>Duration: {{action.duration}}</span>
	<span>Description: {{action.description}}</span>
	<span>Date: {{action.date}}</span>
	<span>User: {{action.user.username}}</span>
	<button ng-click="getaction(action.idTaskAction)"
			data-toggle="modal" data-target="#action${action.idTaskAction}"
			class="btn btn-primary btn-sm">Edit</button>
<%-- 	<label for="actionname" class="col-sm-2 control-label input-sm">Actionname</label>
	<div class="col-sm-4">
		<form:input path="action.actionname" type="text"
			cssClass="form-control input-sm" id="actionname" />
	</div>
	<label for="date" class="col-sm-2 control-label input-sm">Date</label>
	<div class="col-sm-2">
		<form:input id="date" path="action.date" type="datetime"
			value="${date }" readOnly="true" />
	</div>
</div>
<div class="form-group">
	<label for="user" class="col-sm-2 control-label input-sm">User</label>
	<div class="col-sm-2">
		<form:input path="action.user.username" type="text"
			cssClass="form-control input-sm" id="user" readOnly="true" />
	</div>
	<label for="duration"
		class="col-sm-2 col-sm-offset-1 control-label input-sm">Duration</label>
	<div class="col-sm-2">
		<form:input path="action.duration" type="text"
			cssClass="form-control input-sm" id="duration" />
	</div>
</div>
<div class="form-group">
	<label for="description" class="col-sm-2 control-label input-sm">Description</label>
	<div class="col-sm-6">
		<form:textarea path="action.description" rows="3"
			cssClass="form-control input-sm" id="description" />
	</div>
	<div class="col-sm-offset-2 col-sm-2">
		<button ng-click="getaction(${action.idTaskAction})"
			data-toggle="modal" data-target="#action${action.idTaskAction}"
			class="btn btn-primary btn-sm">Edit</button>
	</div>
</div>
<hr /> --%>
<jsp:include page="taskupd.actions.modal.jsp" />