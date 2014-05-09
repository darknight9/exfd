package junit.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.exfd.dao.impl.SealRecordEFinderDaoImpl;
import com.exfd.domain.SealRecord;
import com.exfd.util.HibernateUtils;

public class SealRecordEFinderDaoImplTest {

	@Test
	public void test() {
		SealRecordEFinderDaoImpl dao = new SealRecordEFinderDaoImpl();

		String code = "1006";
		String begin = "2014-03-19 11:0:0";
		String end = "2014-03-19 12:0:0";

		ArrayList<SealRecord> records = null;
		try {
			records = dao.find(code, begin, end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(records.size());
		for (SealRecord sealRecord : records) {
			System.out.println(sealRecord);
		}
		System.out.println(records.size());
	}

	@Test
	public void addPoiOne() {
		SealRecordEFinderDaoImpl dao = new SealRecordEFinderDaoImpl();

		ArrayList<SealRecord> records = dao.findAll("1006");
		for (int i = 0; i < records.size(); i++) {
			if (records.get(i).getPoi() == null) {
				System.out.println("poi is null [" + i + "]");
			} else if (records.get(i).getPoi().isEmpty()) {
				System.out.println("[{}] poi is empty [" + i + "]");
			} else {
				System.out.println("[{}] poi is [" + i + "][" + records.get(i).getPoi() + "]");
			}
		}
	}
	
	@Test
	public void addPoi() {
		SealRecordEFinderDaoImpl dao = new SealRecordEFinderDaoImpl();

		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from SealRecord");
			List results = query.list();
			Iterator it = results.iterator();
			while (it.hasNext()) {
				SealRecord record = (SealRecord) it.next();
				if (record.getPoi().isEmpty()) {
					
				}

				session.delete(record);
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}
}
