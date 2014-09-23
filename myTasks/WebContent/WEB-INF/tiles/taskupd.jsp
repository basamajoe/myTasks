<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/tiles/notifications.jsp"%>
<h1>Create task</h1>
<form:form method="POST" class="form-horizontal"
	action="${pageContext.request.contextPath}/task/upd" commandName="task">
	<div class="form-group">
		<label for="idTask" class="col-sm-2 control-label input-sm">id
			Task</label>
		<div class="col-sm-10">
			<form:input path="idTask" cssClass="form-control input-sm"
				readOnly="true" />
		</div>
	</div>
	<form:hidden path="user.idUser" />
	<form:hidden path="date" />
	<div class="form-group">
		<label for="taskname" class="col-sm-2 control-label input-sm">Taskname</label>
		<div class="col-sm-10">
			<form:input path="taskname" type="text"
				cssClass="form-control input-sm" placeholder="task" />
			<div id="task.error">
				<span class="text-danger"><form:errors path="taskname" /></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="description" class="col-sm-2 control-label input-sm">Description</label>
		<div class="col-sm-10">
			<form:input path="description" type="text"
				cssClass="form-control input-sm" placeholder="description" />
			<div id="description.error">
				<span class="text-danger"><form:errors path="description" /></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="deadline" class="col-sm-2 control-label input-sm">Deadline</label>
		<div class="col-sm-10">
			<form:input path="deadline" type="date"
				cssClass="form-control input-sm" placeholder="dd/mm/yyyy" />
			<div id="deathline.error">
				<span class="text-danger"><form:errors path="deadline" /></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="category" class="col-sm-2 control-label input-sm">Category</label>
		<div class="col-sm-10">
			<form:select path="category" items="${categories}"
				itemValue="idTaskCategory" itemLabel="categoryname"
				cssClass="form-control input-sm" />
			<div id="category.error">
				<span class="text-danger"><form:errors path="category" /></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="priority" class="col-sm-2 control-label input-sm">Priority</label>
		<div class="col-sm-10">
			<form:select path="priority" cssClass="form-control input-sm">
				<form:option value="1" label="Low" />
				<form:options items="${priorities}" itemValue="idTaskPriority"
					itemLabel="priorityname" />
			</form:select>
			<div id="priority.error">
				<span class="text-danger"><form:errors path="priority"
						cssClass="error" /></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="state" class="col-sm-2 control-label input-sm">State</label>
		<div class="col-sm-10">
			<form:select path="state" cssClass="form-control input-sm">
				<form:option value="1" label="Pending" />
				<form:options items="${states}" itemValue="idTaskState"
					itemLabel="statename" />
			</form:select>
			<div id="state.error">
				<span class="text-danger"><form:errors path="state" /></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="pendingby" class="col-sm-2 control-label input-sm">Pending
			by</label>
		<div class="col-sm-10">
			<form:select path="pending" cssClass="form-control input-sm">
				<form:option value="1" selected="selected">Responsible</form:option>
				<form:option value="0">Applicant</form:option>
			</form:select>
			<div id="pending.error">
				<span class="text-danger"><form:errors path="pending" /></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="userresponsible" class="col-sm-2 control-label input-sm">User
			responsible</label>
		<div class="col-sm-10">
			<form:select path="userResponsible" name="userResponsible"
				cssClass="form-control input-sm">
				<form:option value="0" label="Select" />
				<form:options items="${users}" itemValue="idUser"
					itemLabel="username" />
			</form:select>
			<div id="state.error">
				<span class="text-danger"><form:errors path="userResponsible" /></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="evaluation" class="col-sm-2 control-label input-sm">Evaluation</label>
		<div class="col-sm-10">
			<form:textarea path="evaluation" type="text"
				cssClass="form-control input-sm" placeholder="evaluation" />
			<div id="evaluation.error">
				<span class="text-danger"><form:errors path="evaluation" /></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary btn-sm">Save
				Task</button>
		</div>
	</div>
</form:form>
<h2>Actions</h2>
<c:if test="${!empty task.actions}">
	<div id="actions" ng-controller="ActionCtrl as act">
		<table class="table table-striped table-condensed">
			<c:forEach var="tmpaction" items="${task.actions}">
				<c:set var="action" value="${tmpaction}" scope="request" />
				<tr>
					<td><jsp:include page="taskupd.actions.jsp" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</c:if>
<div ng-view></div>