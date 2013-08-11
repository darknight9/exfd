package junit.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Test;

import com.exfd.util.EagleGPSUtils;

public class EagleGPSUtilsTest {

	@Test
	public void testTrackVehicle() throws FileNotFoundException {
		String resString = EagleGPSUtils.trackVehicle("3004");
		System.out.println(resString);
		PrintWriter out = new PrintWriter(new File("/Users/david/Developer/TestData/" + "3004" + ".txt"));
		out.print(resString);
		out.flush();
		out.close();
	}
	
	@Test
	public void testTrackVehicle2() throws FileNotFoundException {
		String resString = EagleGPSUtils.trackVehicle2("3004");
		System.out.println(resString);
		PrintWriter out = new PrintWriter(new File("/Users/david/Developer/TestData/" + "3004" + ".txt"));
		out.print(resString);
		out.flush();
		out.close();
	}
	
	@Test
	public void testTrackHistory() throws FileNotFoundException {
		String resString = EagleGPSUtils.trackHistory("2899", "2013-03-26 0:0:0", "2013-03-27 0:0:0");
		System.out.println(resString);
		PrintWriter out = new PrintWriter(new File("/Users/david/Developer/TestData/" + "2899" + ".txt"));
		out.print(resString);
		out.flush();
		out.close();
	}
	
	@Test
	public void testTrackHistory2() throws FileNotFoundException {
		String resString = EagleGPSUtils.trackHistory2("2899", "2013-03-26 0:0:0", "2013-03-27 0:0:0");
		System.out.println(resString);
		PrintWriter out = new PrintWriter(new File("/Users/david/Developer/TestData/" + "2899" + ".2.txt"));
		out.print(resString);
		out.flush();
		out.close();
	}
	
}
