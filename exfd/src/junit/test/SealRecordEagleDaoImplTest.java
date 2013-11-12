package junit.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.exfd.dao.impl.SealRecordEagleDaoImpl;
import com.exfd.domain.SealRecord;
import com.exfd.util.EagleGPSUtils;

public class SealRecordEagleDaoImplTest {

	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// 不测试数据库，测试网络获取。
	@Test
	public void testFind0() throws ParseException, FileNotFoundException{
		
		SealRecordEagleDaoImpl impl = new SealRecordEagleDaoImpl();
	
		Calendar rightnow = Calendar.getInstance();
		Date endDate = rightnow.getTime();
		
		rightnow.add(Calendar.DAY_OF_YEAR, -2);
		Date beginDate = rightnow.getTime();
		
		// 注意这里超过12个小时可能会被截断。
		beginDate = df.parse("2013-03-26 0:0:0");
		endDate = df.parse("2013-03-26 12:0:0");
		
		String str = EagleGPSUtils.trackHistory("2899", df.format(beginDate), df.format(endDate));
		//String str = EagleGPSUtils.trackHistory2("2899", df.format(beginDate), df.format(endDate));
		//String str = EagleGPSUtils.trackHistory("2899", "2013-03-26 0:0:0", "2013-03-27 0:0:0");
		System.out.println(str.substring(0,100));
		System.out.println(str.length());
		
		PrintWriter out = new PrintWriter(new File("/Users/david/Developer/TestData/" + "2899" + ".txt"));
		out.print(str);
		out.flush();
		out.close();
		
		ArrayList<SealRecord> records = new ArrayList<SealRecord>();
		impl.xml2history("2899", str, records);

		for (SealRecord sealHistoryRecord : records) {
			System.out.println(sealHistoryRecord);
		}
		System.out.println(records.size());
		
	}
	
	@Test
	public void testFind1() throws ParseException, FileNotFoundException{
		SealRecordEagleDaoImpl impl = new SealRecordEagleDaoImpl();
	
		Calendar rightnow = Calendar.getInstance();
		Date endDate = rightnow.getTime();
		
		rightnow.add(Calendar.DAY_OF_YEAR, -2);
		Date beginDate = rightnow.getTime();
		
		beginDate = df.parse("2013-11-10 0:0:0");
		endDate = df.parse("2013-11-11 0:0:0");
		
		ArrayList<SealRecord> records = impl.find("2127", beginDate, endDate);

		for (SealRecord sealHistoryRecord : records) {
			System.out.println(sealHistoryRecord);
		}
		System.out.println("record=" + records.size());
		
	}
}
