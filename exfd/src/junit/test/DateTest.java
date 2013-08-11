package junit.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class DateTest {

	public static void main(String[] args) throws ParseException{
		
		String d = "1980-12-32";
		
		
		// 这种方法不行： 1980-12-32.
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(d);
		System.out.println(date);
		*/
		
		// 这个日期校验管用.
		//DateLocaleConverter dlc = new DateLocaleConverter();
		//dlc.convert(d, "yyyy-MM-dd");
		
		String d2 = "2011-1-1";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = df2.parse(d2);
		Date date1 = df.parse(d2);
		System.out.print(date2);
	}
}
