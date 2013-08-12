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

import com.exfd.dao.impl.SealEagleHistoryDaoImpl;
import com.exfd.domain.SealHistoryRecord;
import com.exfd.util.EagleGPSUtils;

public class SealEagleHistoryDaoImplTest {

	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Test
	public void testFind0() throws ParseException, FileNotFoundException{
		SealEagleHistoryDaoImpl impl = new SealEagleHistoryDaoImpl();
	
		Calendar rightnow = Calendar.getInstance();
		Date endDate = rightnow.getTime();
		
		rightnow.add(Calendar.DAY_OF_YEAR, -2);
		Date beginDate = rightnow.getTime();
		
		beginDate = df.parse("2013-03-26 0:0:0");
		endDate = df.parse("2013-03-27 0:0:0");
		
		String str = EagleGPSUtils.trackHistory("2899", df.format(beginDate), df.format(endDate));
		//String str = EagleGPSUtils.trackHistory2("2899", df.format(beginDate), df.format(endDate));
		//String str = EagleGPSUtils.trackHistory("2899", "2013-03-26 0:0:0", "2013-03-27 0:0:0");
		System.out.print(str);
		
		PrintWriter out = new PrintWriter(new File("/Users/david/Developer/TestData/" + "2899" + ".txt"));
		out.print(str);
		out.flush();
		out.close();
		
		ArrayList<SealHistoryRecord> records = new ArrayList<SealHistoryRecord>();
		impl.xml2history("2899", str, records);

		for (SealHistoryRecord sealHistoryRecord : records) {
			System.out.print(sealHistoryRecord);
		}
		
	}
	
	@Test
	public void testFind1() throws ParseException, FileNotFoundException{
		SealEagleHistoryDaoImpl impl = new SealEagleHistoryDaoImpl();
	
		Calendar rightnow = Calendar.getInstance();
		Date endDate = rightnow.getTime();
		
		rightnow.add(Calendar.DAY_OF_YEAR, -2);
		Date beginDate = rightnow.getTime();
		
		beginDate = df.parse("2013-03-26 0:0:0");
		endDate = df.parse("2013-03-27 0:0:0");
		
		ArrayList<SealHistoryRecord> records = impl.find("2201", beginDate, endDate);

		for (SealHistoryRecord sealHistoryRecord : records) {
			System.out.println(sealHistoryRecord);
		}
		System.out.println("record=" + records.size());
		
	}
}
