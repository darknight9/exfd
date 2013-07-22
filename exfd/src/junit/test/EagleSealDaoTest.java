package junit.test;

import org.junit.Test;

import com.exfd.dao.impl.SealEagleDaoImpl;
import com.exfd.domain.Seal;
import com.exfd.util.EagleGPSUtils;

public class EagleSealDaoTest {
	
	@Test
	public void testTrackVehicle() {
		EagleGPSUtils eagle = new EagleGPSUtils();
		String resString = eagle.trackVehicle("3004");
		System.out.println(resString);
		
		SealEagleDaoImpl dao = new SealEagleDaoImpl();
		Seal seal = dao.xml2seal(resString);
		System.out.println(seal);
	}
	
	@Test
	public void testFind() {
		SealEagleDaoImpl dao = new SealEagleDaoImpl();
		Seal seal = dao.find("2899");
		System.out.println(seal);
	}
}
