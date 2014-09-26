package com.javalabs.web.controllers;

import java.io.IOException;
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
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javalabs.web.dao.Category;
import com.javalabs.web.dao.CategoryPropertyEditor;
import com.javalabs.web.dao.Priority;
import com.javalabs.web.dao.PriorityPropertyEditor;
import com.javalabs.web.dao.State;
import com.javalabs.web.dao.StatePropertyEditor;
import com.javalabs.web.dao.Task;
import com.javalabs.web.dao.TaskAction;
import com.javalabs.web.dao.User;
import com.javalabs.web.dao.UserPropertyEditor;
import com.javalabs.web.service.CategoryService;
import com.javalabs.web.service.PriorityService;
import com.javalabs.web.service.StateService;
import com.javalabs.web.service.TaskActionService;
import com.javalabs.web.service.TaskService;
import com.javalabs.web.service.UserService;

@Controller
public class TaskController {

	private TaskService taskService;
	private TaskActionService taskActionService;
	private CategoryService categoryService;
	private UserService userService;
	private StateService stateService;
	private PriorityService priorityService;

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
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}

	@Autowired
	public void setPriorityService(PriorityService priorityService) {
		this.priorityService = priorityService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest req) {
		logger.info("Task controller initBinder...");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
		StatePropertyEditor spe = new StatePropertyEditor();
		spe.setStateService(stateService);
		binder.registerCustomEditor(State.class, spe);
		CategoryPropertyEditor cpe = new CategoryPropertyEditor();
		cpe.setCategoryService(categoryService);
		binder.registerCustomEditor(Category.class, cpe);
		PriorityPropertyEditor ppe = new PriorityPropertyEditor();
		ppe.setPriorityService(priorityService);
		binder.registerCustomEditor(Priority.class, ppe);
		UserPropertyEditor upe = new UserPropertyEditor();
		upe.setUserService(userService);
		binder.registerCustomEditor(User.class, "user", upe);
		UserPropertyEditor uper = new UserPropertyEditor();
		uper.setUserService(userService);
		binder.registerCustomEditor(User.class, "userResponsible", uper);
	}

	@RequestMapping(value = "/task/lst", method = RequestMethod.GET)
	public String getTaskList(Model model) {
		logger.info("Task controller task list...");

		List<Task> tasks = taskService.getAllTasks();
		model.addAttribute("tasks", tasks);

		return "tasklst";
	}

	@RequestMapping(value = "/task/add", method = RequestMethod.GET)
	public String createTask(Model model, Principal principal) {
		logger.info("Task controller task add GET...");

		List<Category> categories = categoryService.getAllCategories();
		List<Priority> priorities = priorityService.getAllPriorities();
		List<State> states = stateService.getAllStates();
		List<User> users = userService.getAllUsers();

		model.addAttribute("task", new Task());
		model.addAttribute("categories", categories);
		model.addAttribute("priorities", priorities);
		model.addAttribute("states", states);
		model.addAttribute("users", users);
		model.addAttribute("principal", principal);

		return "taskadd";
	}

	@RequestMapping(value = "/task/add", method = RequestMethod.POST)
	public String postTaskAdd(Model model, @Valid Task task,
			BindingResult result, Principal principal) {

		logger.info("Task controller task add POST...");
		String notifications = "";

		if (result.hasErrors()) {
			List<Category> categories = categoryService.getAllCategories();
			List<Priority> priorities = priorityService.getAllPriorities();
			List<State> states = stateService.getAllStates();
			List<User> users = userService.getAllUsers();

			model.addAttribute("categories", categories);
			model.addAttribute("priorities", priorities);
			model.addAttribute("states", states);
			model.addAttribute("users", users);

			model.addAttribute("categories", categories);
			model.addAttribute("priorities", priorities);
			model.addAttribute("states", states);
			model.addAttribute("users", users);
			model.addAttribute("task", task);

			notifications = "-" + result.getAllErrors().toString();
			model.addAttribute("notifications", notifications);

			return "taskadd";
		}
		if (principal != null) {
			task.setDate(new Date());
			task.setUser(userService.get(principal.getName()));
			taskService.saveOrUpdate(task);
			notifications = "+Task succesfully created";
		} else {
			notifications = "!Task can not be created";
		}
		model.addAttribute("notifications", notifications);

		List<Task> tasks = taskService.getAllTasks();
		model.addAttribute("tasks", tasks);

		return "tasklst";
	}

	@RequestMapping(value = "/task/upd/{id}", method = RequestMethod.GET)
	public String getTaskUpdate(@PathVariable("id") long idTask, Model model,
			Principal principal) {

		logger.info("Task controller task upd GET...");
		logger.info("-----------(" + taskService.get(idTask).getIdTask() + ") "
				+ taskService.get(idTask).getDate() + " | "
				+ taskService.get(idTask).getUser());

		List<Category> categories = categoryService.getAllCategories();
		List<Priority> priorities = priorityService.getAllPriorities();
		List<State> states = stateService.getAllStates();
		List<User> users = userService.getAllUsers();

		model.addAttribute("task", taskService.get(idTask));
		model.addAttribute("categories", categories);
		model.addAttribute("priorities", priorities);
		model.addAttribute("states", states);
		model.addAttribute("users", users);

		return "taskupd";
	}

	@RequestMapping(value = "/task/upd", method = RequestMethod.POST)
	public String postTaskUpdate(Model model,
			@ModelAttribute("task") @Valid Task task, BindingResult result,
			Principal principal,
			@RequestParam MultiValueMap<String, List<String>> parameters) {

		logger.info("Task controller task upd POST...");
		logger.info("--------------------id (" + task.getIdTask() + ") date "
				+ task.getDate() + " | user " + task.getUser());
		logger.info("-------" + result.getErrorCount());
		logger.info(">>-------" + parameters);

		String notifications = "";

		if (result.hasErrors()) {
			logger.info("-------" + result.getAllErrors());
			notifications = "-Task can not be updated, fix the errors."
					+ result.getAllErrors();
			model.addAttribute("notifications", notifications);

			List<Category> categories = categoryService.getAllCategories();
			List<Priority> priorities = priorityService.getAllPriorities();
			List<State> states = stateService.getAllStates();
			List<User> users = userService.getAllUsers();

			model.addAttribute("categories", categories);
			model.addAttribute("priorities", priorities);
			model.addAttribute("states", states);
			model.addAttribute("users", users);

			model.addAttribute("categories", categories);
			model.addAttribute("priorities", priorities);
			model.addAttribute("states", states);
			model.addAttribute("users", users);
			model.addAttribute("task", task);

			return "taskupd";
		} else {
			taskService.saveOrUpdate(task);
			notifications = "+Task succesfully updated";
			model.addAttribute("notifications", notifications);

			List<Task> tasks = taskService.getAllTasks();
			model.addAttribute("tasks", tasks);

			return "tasklst";
		}
	}

	@RequestMapping(value = "/task/del/{id}", method = RequestMethod.GET)
	public String getTaskDelete(@PathVariable("id") long idTask,
			Principal principal, Model model) {
		logger.info("Task controller task del GET(" + idTask + ")...");
		String notifications = "";

		if (principal == null) {
			notifications = "=You can delete task because any user is authenthicated.";
		} else {
			String user = principal.getName();
			if (user.equals(taskService.get(idTask).getUser().getUsername())) {
				taskService.delete(idTask);

				notifications = "+Task (" + idTask + ") succesfully deleted.";
			} else {
				notifications = "=Task (" + idTask
						+ ") can not be deleted by this user.";
			}
			notifications = "User: " + notifications;
		}

		List<Task> tasks = taskService.getAllTasks();

		model.addAttribute("tasks", tasks);
		model.addAttribute("notifications", notifications);
		logger.info("-----Del--notifications: " + notifications);
		return "tasklst";
	}

	// value = "/getactions/{id}" AND , @PathVariable Long id
	@RequestMapping(value = "/getactions/{idTask}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getActions(Principal principal,
			@PathVariable Long idTask) {
		logger.info("Task controller get actions...");
//List<TaskAction>
		List<TaskAction> actions = null;

		if (principal == null) {
			actions = new ArrayList<TaskAction>();
		} else {
			actions = taskActionService.getAllTaskActions(idTask);
		}

		System.out.println(">>>>>>>>Task Controller - Actions>>>>>>>>>>>>> "
				+ actions.size());
		for (TaskAction ta : actions) {
			System.out.println(">>>>>>>>ta: " + ta.getActionname());
		}
		System.out.println(">>>>>>>>END getAllActions()>>>>>>>>>>>>> ");

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("actions", actions);
		data.put("number", actions.size());
		return data;
	}
}
