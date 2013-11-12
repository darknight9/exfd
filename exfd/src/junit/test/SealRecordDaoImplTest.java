package junit.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.exfd.dao.impl.SealRecordDaoImpl;
import com.exfd.domain.SealRecord;
import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;

public class SealRecordDaoImplTest {
	// 1980-09-09 12:03:04
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Test
	public void testAdd() {
		SealRecordDaoImpl impl = new SealRecordDaoImpl();
		SealRecord record = new SealRecord();
		record.setCode("TEST001");
		record.setGpstime(new Date());
		record.setLatitude(111.11);
		record.setLongitude(40);
		record.setPoi("测试数据POI.1");
		impl.add(record);

	}

	@Test
	public void testAddList() {
		SealRecordDaoImpl impl = new SealRecordDaoImpl();
		ArrayList<SealRecord> arrayList = new ArrayList<SealRecord>();
		SealRecord record = new SealRecord();
		record.setCode("ARR001");
		record.setGpstime(new Date());
		record.setLatitude(111.11);
		record.setLongitude(40);
		record.setPoi("测试数据POI.1A");
		arrayList.add(record);

		SealRecord record2 = new SealRecord();
		record2.setCode("ARR002");
		record2.setGpstime(new Date());
		record2.setLatitude(111.11);
		record2.setLongitude(40);
		record2.setPoi("测试数据POI.2A");
		arrayList.add(record2);

		impl.add(arrayList);

	}

	@Test
	public void testDelete() {
		SealRecordDaoImpl impl = new SealRecordDaoImpl();
		impl.delete("TEST001");
	}

	@Test
	public void testFind() {
		SealRecordDaoImpl impl = new SealRecordDaoImpl();
		ArrayList<SealRecord> records = impl.find("2899");
		System.out.println(records.size());
		System.out.println(records.get(0));
		System.out.println(records.get(1));
		System.out.println(records.get(2));
		System.out.println(records.get(records.size() - 3));
		System.out.println(records.get(records.size() - 2));
		System.out.println(records.get(records.size() - 1));
	}

	@Test
	public void testFind2() throws ParseException {
		SealRecordDaoImpl impl = new SealRecordDaoImpl();
		
		String beginString = "2013-03-26 12:0:0";
		String endString = "2013-03-26 12:05:00";
		
		Date beginDate = df.parse(beginString);
		Date endDate = df.parse(endString);
		System.out.println(beginDate);
		System.out.println(endDate);
		ArrayList<SealRecord> records = impl.find("2899", beginDate, endDate);
		System.out.println(records.size());
		System.out.println(records.get(0));
		System.out.println(records.get(1));
		System.out.println(records.get(2));
		System.out.println(records.get(records.size()-3));
		System.out.println(records.get(records.size()-2));
		System.out.println(records.get(records.size()-1));
	}
	
	@Test
	public void testFindOne() throws ParseException {
		SealRecordDaoImpl impl = new SealRecordDaoImpl();
		
		String beginString = "2013-03-27 12:0:0";
		String endString = "2013-03-29 12:05:00";
		
		Date beginDate = df.parse(beginString);
		Date endDate = df.parse(endString);
		System.out.println(beginDate);
		System.out.println(endDate);
		SealRecord record = impl.findOne("2899", beginDate, endDate, false);
		System.out.println(record);
	}

}
