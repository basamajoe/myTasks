package com.javalabs.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javalabs.web.dao.TaskAction;
import com.javalabs.web.dao.TaskActionDao;

@Service("taskActionService")
public class TaskActionService {

	private TaskActionDao taskActionDao;

	@Autowired
	public void setTaskDao(TaskActionDao taskActionDao) {
		this.taskActionDao = taskActionDao;
	}

	@Transactional(readOnly = true, propagation = Propagation.NESTED)
	public List<TaskAction> getAllTaskActions() {
		return taskActionDao.getAllTaskActions();
	}

	@Transactional(readOnly = true, propagation = Propagation.NESTED)
	public List<TaskAction> getAllTaskActions(long idTask) {
		return taskActionDao.getAllTaskActions(idTask);
	}
	
	public void saveOrUpdate(TaskAction taskAction) {
		taskActionDao.saveOrUpdate(taskAction);
	}

	public TaskAction get(Long id) {
		return taskActionDao.get(id);
	}
}