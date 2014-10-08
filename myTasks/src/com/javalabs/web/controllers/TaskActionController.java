package com.javalabs.web.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javalabs.web.dao.TaskAction;
import com.javalabs.web.dao.TaskActionPropertyEditor;
import com.javalabs.web.dao.TaskActionVO;
import com.javalabs.web.dao.TaskPropertyEditor;
import com.javalabs.web.dao.User;
import com.javalabs.web.dao.UserPropertyEditor;
import com.javalabs.web.service.TaskActionService;
import com.javalabs.web.service.TaskService;
import com.javalabs.web.service.UserService;

@Controller
public class TaskActionController {

	private TaskService taskService;
	private TaskActionService taskActionService;
	private UserService userService;

	private static Logger logger = Logger.getLogger(TaskController.class);

	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	public void setTaskActionService(TaskActionService taskActionService) {
		this.taskActionService = taskActionService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void initBinder(WebDataBinder binder, HttpServletRequest req) {
		logger.info("Task controller initBinder...");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		TaskPropertyEditor tpe = new TaskPropertyEditor();
		tpe.setTaskService(taskService);
		binder.registerCustomEditor(TaskAction.class, "idTask", tpe);
		TaskActionPropertyEditor tape = new TaskActionPropertyEditor();
		tape.setTaskActionService(taskActionService);
		binder.registerCustomEditor(TaskAction.class, "idTaskAction", tape);
		UserPropertyEditor upe = new UserPropertyEditor();
		upe.setUserService(userService);
		binder.registerCustomEditor(User.class, "idUser", upe);
	}

	@RequestMapping(value = "/taskaction/upd", method = RequestMethod.POST)
	public String postTaskUpdate(	Model model,
									@ModelAttribute("action") @Valid TaskAction taskaction,
									BindingResult result,
									Principal principal) {

		logger.info("Task controller taskaction upd POST...");

		String notifications = "";

		if (result.hasErrors()) {
			logger.info("-------" + result.getAllErrors());
			notifications = "-Task can not be updated, fix the errors." + result.getAllErrors();
			model.addAttribute("notifications", notifications);

			return "taskupd";
		} else {
			taskActionService.saveOrUpdate(taskaction);
			notifications = "+Task succesfully updated";
			model.addAttribute("notifications", notifications);

			return "tasklst";
		}
	}

	@RequestMapping(value = "/taskaction/get/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getAction(Principal principal, @PathVariable Long id) {
		logger.info("Task controller /taskaction/get...");

		TaskAction action = null;

		if (principal == null) {
			action = new TaskAction();
		} else {
			action = taskActionService.get(id);
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("action", action);

		return data;
	}

	@RequestMapping(value = "/taskaction/send", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendAction(Principal principal, @RequestBody TaskActionVO tavo) {
		logger.info("Task controller /taskaction/send ...");
		TaskAction action;
		if (tavo.getIdTaskAction() != 0) {
			action = taskActionService.get(tavo.getIdTaskAction());

			action.setActionname(tavo.getActionname());
			action.setDescription(tavo.getDescription());
			action.setDuration(tavo.getDuration());
		} else {
			action = new TaskAction(taskService.get(tavo.getIdTask()), tavo.getDate(),
					tavo.getActionname(), tavo.getDescription(), userService.get(tavo.getIdUser()),
					tavo.getDuration());
		}
		System.out.println("|TaskActionController>>>>" + tavo.getIdTask() + 
				" object: " + taskService.get(tavo.getIdTask()) +
				" actionname: " + tavo.getActionname() +
				" description: " + tavo.getDescription() +
				" duration: " + tavo.getDuration());
		taskActionService.saveOrUpdate(action);

		Map<String, Object> rdata = new HashMap<String, Object>();

		rdata.put("success", true);

		return rdata;
	}
}