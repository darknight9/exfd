package junit.test;

import org.junit.Test;

import com.exfd.util.EagleGPSUtils;

public class EagleGPSUtilsTest {

	@Test
	public void testTrackVehicle() {
		EagleGPSUtils eagle = new EagleGPSUtils();
		String resString = eagle.trackVehicle("3004");
		System.out.println(resString);
		eagle
		
	}
	
}
