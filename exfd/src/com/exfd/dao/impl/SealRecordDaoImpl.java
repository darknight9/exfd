package com.exfd.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.exfd.dao.SealRecordDao;
import com.exfd.domain.Seal;
import com.exfd.domain.SealRecord;
import com.exfd.util.HibernateUtils;

public class SealRecordDaoImpl implements SealRecordDao {

	private static Logger logger = LogManager.getLogger();

	// 1980-09-09 12:03:04
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void add(SealRecord sRecord) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(sRecord);
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
	public void add(ArrayList<SealRecord> sRecords) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (SealRecord record : sRecords) {
				session.save(record);
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
	public void delete(String code) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from SealRecord as s where s.code=:code");
			query.setString("code", code);
			List results = query.list();
			Iterator it = results.iterator();
			while (it.hasNext()) {
				SealRecord record = (SealRecord) it.next();
				session.delete(record);
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
	public ArrayList<SealRecord> find(String code) {

		ArrayList<SealRecord> records = new ArrayList<SealRecord>();

		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		SealRecord sRecord = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from SealRecord as s where s.code=:code order by s.gpstime desc");
			query.setString("code", code);
			List results = query.list();
			Iterator it = results.iterator();
			while (it.hasNext()) {
				sRecord = (SealRecord) it.next();
				records.add(sRecord);
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
		return records;
	}

	@Override
	public ArrayList<SealRecord> find(String code, Date beginDate, Date endDate) {

		ArrayList<SealRecord> records = new ArrayList<SealRecord>();

		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		SealRecord sRecord = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from SealRecord as s where s.code=:code and s.gpstime between :begintime and :endtime order by s.gpstime desc");
			query.setString("code", code);
			query.setTimestamp("begintime", beginDate);
			query.setTimestamp("endtime", endDate);
			List results = query.list();
			Iterator it = results.iterator();
			while (it.hasNext()) {
				sRecord = (SealRecord) it.next();
				records.add(sRecord);
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
		return records;
	}

	@Override
	public ArrayList<SealRecord> find(String code, String beginDate,
			String endDate) throws ParseException {
		return find(code, df.parse(beginDate), df.parse(endDate));
	}

	public SealRecord findOne(String code, Date beginDate,
			Date endDate, boolean isNew) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		SealRecord sRecord = null;
		try {
			tx = session.beginTransaction();
			Query query = null;
			if (isNew) {
				query = session
						.createQuery("from SealRecord as s where s.code=:code and s.gpstime between :begintime and :endtime order by s.gpstime desc");
			} else {
				query = session
						.createQuery("from SealRecord as s where s.code=:code and s.gpstime between :begintime and :endtime order by s.gpstime");
			}
			query.setString("code", code);
			query.setTimestamp("begintime", beginDate);
			query.setTimestamp("endtime", endDate);
			
			// 只要1条结果.
			query.setMaxResults(1);
			sRecord = (SealRecord) query.uniqueResult();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.catching(e);
		} finally {
			session.close();
		}
		return sRecord;
	}
}
