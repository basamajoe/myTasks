package com.javalabs.web.test.tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

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
@ContextConfiguration(locations = { "classpath:com/javalabs/web/config/dao-context.xml",
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
	private User user = new User("jose", "jose", "jose@javalabs.com", true, "ROLE_USER", "joe");

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

		Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, category,
				priority, state, user, user, "okey", 0);

		TaskAction ta1 = new TaskAction(t1, rightnow, "Task action 1", "Task action 1 description",
				user, 20);
		TaskAction ta2 = new TaskAction(t1, rightnow, "Task action 2", "Task action 2 description",
				user, 15);
		TaskAction ta3 = new TaskAction(t1, rightnow, "Task action 3", "Task action 3 description",
				user, 25);
		TaskAction ta4 = new TaskAction(t1, rightnow, "Task action 4", "Task action 4 description",
				user, 30);

		t1.addAction(ta1);
		t1.addAction(ta2);
		t1.addAction(ta3);
		t1.addAction(ta4);

		taskDao.save(t1);

		Task t2a = new Task("My first task", "This is a task", rightnow, rightnow, category,
				priority, state, user, user, "okey", 0);
		TaskAction ta21 = new TaskAction(t2a, rightnow, "Task action 1",
				"Task action 21 description", user, 40);
		t2a.addAction(ta21);
		taskDao.save(t2a);

		assertEquals("Should be 4 taskActions with getAllTaskActions.", 4, t1.getActions().size());
		assertEquals("Should be 5 taskActions with getAllTaskActions.", 5, taskActionDao
				.getAllTaskActions().size());

		Task t2 = taskDao.get(t1.getIdTask());
		assertEquals("Should be 4 taskActions with getAllTaskActions.", 4, t2.getActions().size());
		assertEquals("Should be 4 taskActions with getAllTaskActions.", 4, taskActionDao
				.getAllTaskActions(t2.getIdTask()).size());
	}

	@Test
	public void testTaskActionUpdate() {
		taskPriorityDao.saveOrUpdate(priority);
		taskCategoryDao.saveOrUpdate(category);
		taskStateDao.saveOrUpdate(state);
		userDao.saveOrUpdate(user);

		Date rightnow = Calendar.getInstance().getTime();

		Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, category,
				priority, state, user, user, "okey", 0);

		taskDao.saveOrUpdate(t1);

		TaskAction ta1 = new TaskAction(t1, rightnow, "Task action 1", "Task action 1 description",
				user, 40);
		TaskAction ta2 = new TaskAction(t1, rightnow, "Task action 2", "Task action 2 description",
				user, 20);

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

		Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, category,
				priority, state, user, user, "okey", 0);

		TaskAction ta1 = new TaskAction(t1, rightnow, "Task actionn 1",
				"Task action 1 description", user, 20);

		t1.addAction(ta1);
		taskDao.saveOrUpdate(t1);

		assertNotNull("TaskAction with ID " + ta1.getIdTaskAction()
				+ " should not be null (deleted, actual)", ta1);

		long idTaskAction = ta1.getIdTaskAction();
		taskActionDao.delete(idTaskAction);

		TaskAction taR2 = taskActionDao.get(idTaskAction);
		assertNull("TaskAction with ID " + idTaskAction + " should be null (deleted, actual)", taR2);
	}

	@Test
	public void testTaskActionOnetoMany() {
		taskPriorityDao.saveOrUpdate(priority);
		taskCategoryDao.saveOrUpdate(category);
		taskStateDao.saveOrUpdate(state);
		userDao.saveOrUpdate(user);

		Date rightnow = Calendar.getInstance().getTime();

		Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, category,
				priority, state, user, user, "okey", 0);

		TaskAction ta1 = new TaskAction(t1, rightnow, "Task action 1", "Task action 1 description",
				user, 50);
		TaskAction ta2 = new TaskAction(t1, rightnow, "Task action 2", "Task action 2 description",
				user, 40);
		TaskAction ta3 = new TaskAction(t1, rightnow, "Task action 3", "Task action 3 description",
				user, 30);
		TaskAction ta4 = new TaskAction(t1, rightnow, "Task action 4", "Task action 4 description",
				user, 120);

		t1.addAction(ta1);
		t1.addAction(ta2);
		t1.addAction(ta3);
		t1.addAction(ta4);

		taskDao.save(t1);

		System.out.println("id tarea" + t1.getIdTask());
		System.out.println("taskAction size " + taskActionDao.getAllTaskActions().size());

		assertEquals("Should be 4 taskActions.", 4, t1.getActions().size());
	}
}