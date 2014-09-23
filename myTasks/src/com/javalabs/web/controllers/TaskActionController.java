package com.javalabs.web.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.javalabs.web.dao.TaskActionVO;
import com.javalabs.web.dao.User;
import com.javalabs.web.dao.UserPropertyEditor;
import com.javalabs.web.service.TaskActionService;
import com.javalabs.web.service.UserService;

@Controller
public class TaskActionController {

	private TaskActionService taskActionService;
	private UserService userService;

	private static Logger logger = Logger.getLogger(TaskController.class);

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
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
		UserPropertyEditor upe = new UserPropertyEditor();
		upe.setUserService(userService);
		binder.registerCustomEditor(User.class, "user", upe);
	}

	@RequestMapping(value = "/taskaction/upd", method = RequestMethod.POST)
	public String postTaskUpdate(Model model,
			@ModelAttribute("action") @Valid TaskAction taskaction,
			BindingResult result, Principal principal) {

		logger.info("Task controller taskaction upd POST...");

		String notifications = "";

		if (result.hasErrors()) {
			logger.info("-------" + result.getAllErrors());
			notifications = "-Task can not be updated, fix the errors."
					+ result.getAllErrors();
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
	public Map<String, Object> getAction(Principal principal,
			@PathVariable Long id) {
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
	public Map<String, Object> sendAction(Principal principal,
			@RequestBody TaskActionVO ta) {
		logger.info("Task controller /taskaction/send ...");
		// , @PathVariable Long id
		String actionname = (String) ta.getActionname();

		System.out.println("**TaskActionController**>>>>>>" + ta.getActionname() + ">" + ">"
				+ ta.getIdTaskAction() + ">" + ta.getDescription() + ">"
				+ ta.getDescription() + ">" + ta.getIdTask());

		Map<String, Object> rdata = new HashMap<String, Object>();

		TaskAction action = null;

		rdata.put("success", true);

		return rdata;
	}
}