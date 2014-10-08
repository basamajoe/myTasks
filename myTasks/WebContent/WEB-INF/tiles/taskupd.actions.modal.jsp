<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Modal -->
<div class="modal fade" id="action{{action.idTaskAction}}" tabindex="-1"
	role="dialog" aria-labelledby="action{{action.idTaskAction}}"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Edit action</h4>
			</div>
			<div class="modal-body">
				<div class="table-responsive">
					<input type="hidden" ng-model="action.idTaskAction" />
					<input type="hidden" ng-model="action.idTask" />
					<input type="hidden" ng-model="action.user.idUser" />
					<table class="table">
						<tr>
							<td>Actionname</td>
							<td><input type="text" ng-model="action.actionname" /></td>
						</tr>
						<tr>
							<td>Username</td>
							<td><input type="text" ng-model="action.user.username" /></td>
						</tr>
						<tr>
							<td>Date</td>
							<td><input type="date" name="fecha"
									placeholder="dd-MM-yyyy HH:mm:ss" size="16"
									ng-model="data.action.date" /></td>
						</tr>
						<tr>
							<td>Duration</td>
							<td><input type="text" size="10" ng-model="action.duration" /></td>
						</tr>
						<tr>
							<td>Description</td>
							<td><input type="text" ng-model="action.description" /></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				{{action.idTask}}
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button type="button" ng-click="sendaction($index);closeModal($index);getactions(${task.idTask})"
					class="btn btn-primary">Save changes{{action.idTask}}</button>
			</div>
		</div>
	</div>
</div>