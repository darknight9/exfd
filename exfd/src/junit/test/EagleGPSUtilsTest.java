package junit.test;

import org.junit.Test;

import com.exfd.util.EagleGPSUtils;

public class EagleGPSUtilsTest {

	@Test
	public void testTrackVehicle() {
		EagleGPSUtils eagle = new EagleGPSUtils();
		String resString = eagle.trackVehicle("3004");
		System.out.println(resString);
		
	}
	
	@Test
	public void testTrackVehicle2() {
		EagleGPSUtils eagle = new EagleGPSUtils();
		String resString = eagle.trackVehicle2("3004");
		System.out.println(resString);
		
	}
	
	@Test
	public void testTrackHistory() {
		EagleGPSUtils eagle = new EagleGPSUtils();
		String resString = eagle.trackHistory("2899", "2013-03-26 0:0:0", "2013-03-27 0:0:0");
		System.out.println(resString);
	}
	
}
