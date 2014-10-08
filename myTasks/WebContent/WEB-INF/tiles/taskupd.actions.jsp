<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="form-group">
	<label class="control-label input-sm">Actionname:</label><span>{{action.actionname}}</span>
	<label class="control-label input-sm">IdTask:</label><span>{{action.task.idTask}}</span>
	<label class="control-label input-sm">IdTaskAction:</label><span>{{action.idTaskAction}}</span>
	<label class="control-label input-sm">Duration:</label><span>{{action.duration}}</span>
	<label class="control-label input-sm">Date:</label><span>{{action.date|date:'dd/MM/yyyy hh:mm'}}</span>
	<label class="control-label input-sm">User:</label><span>{{action.user.username}}</span>
</div>
<div class="form-group">
	<label class="control-label input-sm">Description:</label><span>{{action.description}}</span>
	<button data-toggle="modal"
		data-target="#action{{action.idTaskAction}}"
		class="btn btn-primary btn-sm">Edit{{action.isUpdated}}</button>
</div>
<div ng-show="action.isUpdated">
	Your action has been successfully updated.
</div>
<jsp:include page="taskupd.actions.modal.jsp" />