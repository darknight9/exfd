package junit.test;

import java.net.URLEncoder;
import java.util.Date;

import org.apache.axis2.dataretrieval.Data;
import org.junit.Test;

public class URLEncoderTest {

	@Test
	public void testEncode() {
	
		System.out.println(URLEncoder.encode("abc"));
		System.out.println(URLEncoder.encode("2014-05-05 03:22:03"));
		System.out.println(URLEncoder.encode("16/04/2014 03:22:03"));
	}
	
	@Test
	public void testDate() {
		
		Date begin = new Date();
		System.out.println(begin);
		long b = begin.getTime();
		System.out.println(b);
		
		long c = b + 1000*60*60*24;
		Date end = new Date();
		end.setTime(c);
		System.out.println(c);
		System.out.println(end);
		
		// 获取第二天的0点：
		long d = (b/(1000*60*60*24) + 1)*1000*60*60*24;
		Date day2 = new Date();
		day2.setTime(d);
		System.out.println(day2);

	}
	
}
