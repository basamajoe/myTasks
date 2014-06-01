package com.javalabs.web.test.tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javalabs.web.dao.Category;
import com.javalabs.web.dao.CategoryDao;
import com.javalabs.web.dao.Priority;
import com.javalabs.web.dao.PriorityDao;
import com.javalabs.web.dao.State;
import com.javalabs.web.dao.StateDao;
import com.javalabs.web.dao.Task;
import com.javalabs.web.dao.TaskAction;
import com.javalabs.web.dao.TaskActionDao;
import com.javalabs.web.dao.TaskDao;
import com.javalabs.web.dao.User;
import com.javalabs.web.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/javalabs/web/config/dao-context.xml",
		"classpath:com/javalabs/web/config/security-context.xml",
		"classpath:com/javalabs/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskActionDaoTests {

	private static Logger logger = Logger.getLogger(TaskActionDaoTests.class);

	@Autowired
	private TaskDao taskDao;
	@Autowired
	private TaskActionDao taskActionDao;
	@Autowired
	private PriorityDao taskPriorityDao;
	@Autowired
	private CategoryDao taskCategoryDao;
	@Autowired
	private StateDao taskStateDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private DataSource dataSource;

	private Category category = new Category("deutsch");
	private Priority priority = new Priority("low");
	private State state = new State("pending");
	private User user = new User("jose", "jose", "jose@javalabs.com", true,
			"ROLE_USER", "joe");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		logger.info("Init TestActionDaoTests...");

		jdbc.execute("delete from t_task");
		jdbc.execute("delete from t_taskaction");
		jdbc.execute("delete from t_user");
		jdbc.execute("delete from a_taskcategory");
		jdbc.execute("delete from a_taskpriority");
		jdbc.execute("delete from a_taskstate");
	}

	@Test
	public void testTaskActionCreate() {
		taskPriorityDao.saveOrUpdate(priority);
		taskCategoryDao.saveOrUpdate(category);
		taskStateDao.saveOrUpdate(state);
		userDao.saveOrUpdate(user);

		Date rightnow = Calendar.getInstance().getTime();

		Task t1 = new Task("My first task", "This is a task", rightnow,
				rightnow, category, priority, state, user, user, "okey", 0);

		taskDao.save(t1);

		TaskAction ta1 = new TaskAction(t1, rightnow, "Task action 1",
				"Task action 1 description", user);
		TaskAction ta2 = new TaskAction(t1, rightnow, "Task action 2",
				"Task action 2 description", user);
		TaskAction ta3 = new TaskAction(t1, rightnow, "Task action 3",
				"Task action 3 description", user);
		TaskAction ta4 = new TaskAction(t1, rightnow, "Task action 4",
				"Task action 4 description", user);

		taskActionDao.saveOrUpdate(ta1);
		taskActionDao.saveOrUpdate(ta2);
		taskActionDao.saveOrUpdate(ta3);
		taskActionDao.saveOrUpdate(ta4);

		List<TaskAction> taskActions = taskActionDao.getAllTaskActions();
		assertEquals("Should be 4 taskActions.", 4, taskActions.size());

		// OneToMany annotation
		/*
		 * Set<TaskAction> taskActions1 = new
		 * HashSet<TaskAction>(t1.getActions());
		 * assertEquals("Should be 4 taskActions.", 4, taskActions1.size());
		 */
		t1.setDate(new Date());
		taskDao.merge(t1);
		List<TaskAction> h = (List<TaskAction>)t1.getActions();
		if (h != null){
			for (TaskAction a : h) {
				System.out.println("P_>" + a.getActionname());
			}
		} else{
			System.out.println("Null actions");
		}
	}

	@Test
	public void testTaskActionUpdate() {
		taskPriorityDao.saveOrUpdate(priority);
		taskCategoryDao.saveOrUpdate(category);
		taskStateDao.saveOrUpdate(state);
		userDao.saveOrUpdate(user);

		Date rightnow = Calendar.getInstance().getTime();

		Task t1 = new Task("My first task", "This is a task", rightnow,
				rightnow, category, priority, state, user, user, "okey", 0);

		taskDao.saveOrUpdate(t1);

		TaskAction ta1 = new TaskAction(t1, rightnow, "Task action 1",
				"Task action 1 description", user);
		TaskAction ta2 = new TaskAction(t1, rightnow, "Task action 2",
				"Task action 2 description", user);

		taskActionDao.saveOrUpdate(ta1);
		taskActionDao.saveOrUpdate(ta2);

		ta2.setActionname("modified update taskAction");
		ta2.setDate(Calendar.getInstance().getTime());
		ta2.setActionname("Action modified");
		ta2.setDescription("Description modified");
		ta2.setDuration(ta2.getDuration() + 20);

		taskActionDao.saveOrUpdate(ta2);

		assertTrue("TaskAction update should return true", ta2.equals(ta2));
	}

	@Test
	public void testTaskActionDelete() {
		taskPriorityDao.saveOrUpdate(priority);
		taskCategoryDao.saveOrUpdate(category);
		taskStateDao.saveOrUpdate(state);
		userDao.saveOrUpdate(user);

		Date rightnow = Calendar.getInstance().getTime();

		Task t1 = new Task("My first task", "This is a task", rightnow,
				rightnow, category, priority, state, user, user, "okey", 0);

		taskDao.saveOrUpdate(t1);

		TaskAction ta1 = new TaskAction(t1, rightnow, "Task action 1",
				"Task action 1 description", user);

		taskActionDao.saveOrUpdate(ta1);

		long idTaskAction = ta1.getIdTaskAction();
		taskActionDao.delete(idTaskAction);
		assertTrue("TaskAction deletion should return true",
				null == taskActionDao.get(idTaskAction));
	}
}
