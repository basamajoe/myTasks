package com.javalabs.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("userDao")
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void saveOrUpdate(User user) {
		session().saveOrUpdate(user);
	}

	public User get(long id) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(id));
		User u = (User)crit.uniqueResult();
		return u;
	}

	public User get(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		User u = (User)crit.uniqueResult();
		Hibernate.initialize(u.getRoles());
		return u;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
	}

	public boolean exists(String username) {
		User user = this.get(username);
		return user != null;
	}
	
	public void delete(long idUser) {
		User userToDel = (User) session().load(User.class, idUser);
		session().delete(userToDel);
	}
}