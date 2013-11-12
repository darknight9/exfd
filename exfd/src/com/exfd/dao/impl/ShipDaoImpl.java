package com.exfd.dao.impl;

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

import com.exfd.dao.ShipDao;
import com.exfd.domain.Ship;
import com.exfd.util.HibernateUtils;

public class ShipDaoImpl implements ShipDao {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void add(Ship ship) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(ship);
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
	public void add(List<Ship> ships) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Ship ship : ships) {
				session.save(ship);
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
	public void delete(Ship ship) {
		delete(ship.getMmsi());
	}

	@Override
	public void delete(String code) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from Ship as s where s.mmsi=:mmsi");
			query.setString("mmsi", code);
			List results = query.list();
			Iterator it = results.iterator();
			if (it.hasNext()) {
				Ship ship = (Ship) it.next();
				session.delete(ship);
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
	public void delete(List<String> codes) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (String code : codes) {
				Query query = session
						.createQuery("from Ship as s where s.mmsi=:mmsi");
				query.setString("mmsi", code);
				List results = query.list();
				Iterator it = results.iterator();
				if (it.hasNext()) {
					Ship ship = (Ship) it.next();
					session.delete(ship);
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

	@Override
	public void update(Ship ship) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(ship);
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
	public void update(List<Ship> ships) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Ship ship : ships) {
				session.update(ship);
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
	public Ship find(String code) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		Ship ship = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from Ship as s where s.mmsi=:mmsi");
			query.setString("mmsi", code);
			List results = query.list();
			Iterator it = results.iterator();
			if (it.hasNext()) {
				ship = (Ship) it.next();
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
		return ship;
	}

	@Override
	public Map<String, Ship> find(List<String> codes) {
		Map<String, Ship> shipMap = new HashMap<String, Ship>();
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		Ship ship = null;
		Query query = null;
		try {
			tx = session.beginTransaction();
			for (String code : codes) {

				query = session
						.createQuery("from Ship as s where s.mmsi=:code");
				query.setString("code", code);
				List results = query.list();
				Iterator it = results.iterator();
				if (it.hasNext()) {
					ship = (Ship) it.next();
					shipMap.put(code, ship);
				} else {
					shipMap.put(code, null);
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
		return shipMap;
	}

	@Override
	public ArrayList<Ship> findDetail(String operid, String keystr,
			String type, int start_ship, int end_ship) {

		ArrayList<Ship> shipList = new ArrayList<Ship>();
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		Ship ship = null;
		Query query = null;
		try {
			tx = session.beginTransaction();
			if (type.equals("name")) {
				query = session
						.createQuery("from Ship as s where s.shipnamecn=:keystr order by s.gpstime desc");
			} else if (type.equals("mmsi")) {
				query = session
						.createQuery("from Ship as s where s.mmsi=:keystr order by s.gpstime desc");
			} else if (type.equals("imo")) {
				query = session
						.createQuery("from Ship as s where s.imo=:keystr order by s.gpstime desc");
			} else {
				query = session
						.createQuery("from Ship as s where s.callsign=:keystr order by s.gpstime desc");
			}
			query.setString("keystr", keystr);
			query.setFirstResult(start_ship).setMaxResults(
					end_ship - start_ship);
			List results = query.list();
			Iterator it = results.iterator();
			while (it.hasNext()) {
				ship = (Ship) it.next();
				shipList.add(ship);
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
		return shipList;

	}

	@Override
	public ArrayList<Ship> list() {
		ArrayList<Ship> arrayList = new ArrayList<Ship>();
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		Ship ship = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Ship");
			List results = query.list();
			Iterator it = results.iterator();
			while (it.hasNext()) {
				ship = (Ship) it.next();
				arrayList.add(ship);
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
	public void updateOrAdd(Ship ship) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(ship);
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
	public void updateOrAdd(List<Ship> ships) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Ship ship : ships) {
				session.saveOrUpdate(ship);
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

}
