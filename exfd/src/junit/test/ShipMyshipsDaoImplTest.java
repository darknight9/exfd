package junit.test;

import org.junit.Test;

import com.exfd.dao.impl.ShipMyshipsDaoImpl;
import com.exfd.domain.Ship;

public class ShipMyshipsDaoImplTest {

	// "mmsi":"370188000"
	// "mmsi":"370188016"
	@Test
	public void testFind() {
		ShipMyshipsDaoImpl dao = new ShipMyshipsDaoImpl();
		Ship ship = dao.find("370188016");
		System.out.println(ship);
	}
}
