package com.javalabs.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.javalabs.web.service.TaskActionService;

public class TaskActionPropertyEditor extends PropertyEditorSupport {
		 
	    private TaskActionService taskActionService;
	    
	    @Autowired
	    public void setTaskActionService(TaskActionService taskActionService) {
	        this.taskActionService = taskActionService;
	    }
	    
	    @Override
	    public void setAsText(String text) throws IllegalArgumentException {
	        super.setValue(taskActionService.get(Long.parseLong(text)));
	    }
}