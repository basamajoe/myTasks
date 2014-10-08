package com.javalabs.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.javalabs.web.service.TaskService;

public class TaskPropertyEditor extends PropertyEditorSupport {
		 
	    private TaskService taskService;
	    
	    @Autowired
	    public void setTaskService(TaskService taskService) {
	        this.taskService = taskService;
	    }
	    
	    @Override
	    public void setAsText(String text) throws IllegalArgumentException {
	        super.setValue(taskService.get(Long.parseLong(text)));
	    }
}