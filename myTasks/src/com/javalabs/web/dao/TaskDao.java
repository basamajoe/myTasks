package com.javalabs.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("taskDao")
public class TaskDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void save(Task task) {
		session().save(task);
	}

	public void update(Task task) {
		session().update(task);
	}

	public void saveOrUpdate(Task task) {
		session().saveOrUpdate(task);
	}

	public void merge(Task task) {
		session().merge(task);
	}

	@SuppressWarnings("unchecked")
	public List<Task> getAllTasks() {
		return session().createQuery("from Task").list();
	}

	public Task get(long id) {
		Criteria crit = session().createCriteria(Task.class);
		crit.add(Restrictions.idEq(id));
		return (Task) crit.uniqueResult();
	}

	public Task get(String taskname) {
		Criteria crit = session().createCriteria(Task.class);
		crit.add(Restrictions.ilike("taskname", taskname));
		return (Task) crit.uniqueResult();
	}

	public void delete(long id) {
		Task taskToDel = (Task) session().load(Task.class, id);
		session().delete(taskToDel);
	}
}