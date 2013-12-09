package com.exfd.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.exfd.dao.UserDao;
import com.exfd.domain.User;
import com.exfd.util.HibernateUtils;

public class UserDaoImpl implements UserDao {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void add(User user) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.catching(e);
		} finally {
			session.close();
		}
	}

	@Override
	public User find(String username, String password) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from User as u where u.username=:username and u.password=:password");
			query.setString("username", username);
			query.setString("password", password);
			List results = query.list();
			Iterator it = results.iterator();
			if (it.hasNext()) {
				user = (User) it.next();
			}
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.catching(e);
		} finally {
			session.close();
		}
		return user;
	}

	// 查找注册的用户是否在数据库中存在.
	@Override
	public boolean find(String username) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from User as u where u.username=:username");
			query.setString("username", username);
			List results = query.list();
			Iterator it = results.iterator();
			if (it.hasNext()) {
				user = (User) it.next();
			}
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.catching(e);
		} finally {
			session.close();
		}
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}
}
