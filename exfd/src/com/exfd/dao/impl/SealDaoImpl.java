package com.exfd.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.exfd.dao.SealDao;
import com.exfd.domain.Seal;
import com.exfd.util.HibernateUtils;

// 铅封Dao实现.
public class SealDaoImpl implements SealDao {

	private static Logger logger = LogManager.getLogger();

	// 1980-09-09 12:03:04
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 添加一个seal记录.
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.exfd.dao.impl.SealDao#add(com.exfd.domain.Seal)
	 */
	@Override
	public void add(Seal seal) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(seal);
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
	public void add(ArrayList<Seal> seals) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Seal seal : seals) {
				session.save(seal);
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
	}

	@Override
	public void delete(Seal seal) {
		delete(seal.getCode());
	}

	@Override
	public void delete(String code) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from Seal as s where s.code=:code");
			query.setString("code", code);
			List results = query.list();
			Iterator it = results.iterator();
			if (it.hasNext()) {
				Seal seal = (Seal) it.next();
				session.delete(seal);
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
	}

	@Override
	public void delete(ArrayList<String> codes) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (String code : codes) {
				Query query = session
						.createQuery("from Seal as s where s.code=:code");
				query.setString("code", code);
				List results = query.list();
				Iterator it = results.iterator();
				if (it.hasNext()) {
					Seal seal = (Seal) it.next();
					session.delete(seal);
				}
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
	}

	// 更新一条seal的记录.
	@Override
	public void update(Seal seal) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(seal);
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
	public void update(ArrayList<Seal> seals) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Seal seal : seals) {
				session.update(seal);
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
	}

	// 查找一个seal记录.
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.exfd.dao.impl.SealDao#find(java.lang.String)
	 */
	@Override
	public Seal find(String code) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		Seal seal = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from Seal as s where s.code=:code");
			query.setString("code", code);
			List results = query.list();
			Iterator it = results.iterator();
			if (it.hasNext()) {
				seal = (Seal) it.next();
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
		return seal;
	}

	// 返回的至少是一个空Map.
	// 如果有的code没有找到，对应的Map中有<code, null>的pair.
	// 但是可能在查找的时候出现异常，这个时候返回的Map可能是不完整的。
	@Override
	public Map<String, Seal> find(ArrayList<String> codes) {
		Map<String, Seal> sealMap = new HashMap<String, Seal>();
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		Seal seal = null;
		Query query = null;
		try {
			tx = session.beginTransaction();
			for (String code : codes) {

				query = session
						.createQuery("from Seal as s where s.code=:code");
				query.setString("code", code);
				List results = query.list();
				Iterator it = results.iterator();
				if (it.hasNext()) {
					seal = (Seal) it.next();
					sealMap.put(code, seal);
				} else {
					sealMap.put(code, null);
				}
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
		return sealMap;
	}

	public ArrayList<Seal> list() {
		ArrayList<Seal> arrayList = new ArrayList<Seal>();
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		Seal seal = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Seal");
			List results = query.list();
			Iterator it = results.iterator();
			while (it.hasNext()) {
				seal = (Seal) it.next();
				arrayList.add(seal);
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
		return arrayList;
	}

	@Override
	public ArrayList<Seal> findMany(Long uid, int first, int count) {
		
		ArrayList<Seal> seals = new ArrayList<Seal>();
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		Seal seal = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from Seal as s where s.uid=:uid");
			query.setLong("uid", uid);
			query.setFirstResult(first);
			query.setMaxResults(count);
			List results = query.list();
			Iterator it = results.iterator();
			while (it.hasNext()) {
				seal = (Seal) it.next();
				seals.add(seal);
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
		return seals;
	}

}
