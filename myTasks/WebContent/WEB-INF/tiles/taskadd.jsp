<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="body" class="col-md-10">
	<h1>Create task</h1>
	<%@ include file="/WEB-INF/tiles/notifications.jsp"%>
	<form:form method="POST" class="form-horizontal"
		action="${pageContext.request.contextPath}/task/add"
		commandName="task">
		<div class="form-group">
			<label for="taskname" class="col-sm-2 control-label">Taskname</label>
			<div class="col-sm-10">
				<form:input path="taskname" name="taskname" type="text"
					class="form-control" id="taskname" placeholder="task" />
				<div id="task.error">
					<span class="text-danger"><form:errors path="taskname" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-2 control-label">Description</label>
			<div class="col-sm-10">
				<form:input path="description" name="description" type="text"
					class="form-control" id="description" placeholder="description" />
				<div id="description.error">
					<span class="text-danger"><form:errors path="description" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="deadline" class="col-sm-2 control-label">Deadline</label>
			<div class="col-sm-10">
				<form:input path="deadline" name="deadline" type="date"
					class="form-control" id="deadline" placeholder="dd/mm/yyyy" />
				<div id="deathline.error">
					<span class="text-danger"><form:errors path="deadline" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="category" class="col-sm-2 control-label">Category</label>
			<div class="col-sm-10">
				<form:select path="category" items="${categories}"
					itemValue="idTaskCategory" itemLabel="categoryname"
					class="form-control" />
				<div id="category.error">
					<span class="text-danger"><form:errors path="category" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="priority" class="col-sm-2 control-label">Priority</label>
			<div class="col-sm-10">
				<form:select path="priority" name="priority" class="form-control">
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
			<label for="state" class="col-sm-2 control-label">State</label>
			<div class="col-sm-10">
				<form:select path="state" name="state" class="form-control">
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
			<label for="pendingby" class="col-sm-2 control-label">Pending
				by</label>
			<div class="col-sm-10">
				<form:select path="pending" name="pending" class="form-control">
					<form:option value="1" selected="selected">Responsible</form:option>
					<form:option value="0">Applicant</form:option>
				</form:select>
				<div id="pending.error">
					<span class="text-danger"><form:errors path="pending" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="userresponsible" class="col-sm-2 control-label">User
				responsible</label>
			<div class="col-sm-10">
				<form:select path="userResponsible" name="userResponsible"
					class="form-control">
					<form:option value="0" label="Select" />
					<form:options items="${users}" itemValue="idUser"
						itemLabel="username" />
				</form:select>
				<div id="state.error">
					<span class="text-danger"><form:errors
							path="userResponsible" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="evaluation" class="col-sm-2 control-label">Evaluation</label>
			<div class="col-sm-10">
				<form:textarea path="evaluation" name="evaluation" type="text"
					class="form-control" id="evaluation" placeholder="evaluation" />
				<div id="evaluation.error">
					<span class="text-danger"><form:errors path="evaluation" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary">Create new
					Task</button>
			</div>
		</div>
	</form:form>
</div>