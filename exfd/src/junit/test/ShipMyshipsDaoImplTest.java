package junit.test;

import java.util.List;

import org.junit.Test;

import com.exfd.dao.ShipDao;
import com.exfd.dao.impl.ShipDaoImpl;
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
	
	// "mmsi":"370188000"
	// "mmsi":"370188016"
	@Test
	public void testFindDetail() {
		ShipMyshipsDaoImpl dao = new ShipMyshipsDaoImpl();
		List<Ship> ships = dao.findDetail("", "中远非洲", "name", 1, 20);
		for (Ship ship : ships) {
			System.out.println(ship);
		}
		
	}
	
	// "mmsi":"370188000"
	// "mmsi":"370188016"
	@Test
	public void testFindDetail2() {
		ShipDao dao = new ShipDaoImpl();
		List<Ship> ships = dao.findDetail("", "370188016", "mmsi", 1, 2);
		for (Ship ship : ships) {
			System.out.println(ship);
		}
		
	}
	
	// "mmsi":"370188000"
	// "mmsi":"370188016"
	@Test
	public void testFindDetail3() {
		ShipDao dao = new ShipDaoImpl();
		List<Ship> ships = dao.findDetail("", "中远鞍钢", "name", 1, 20);
		for (Ship ship : ships) {
			System.out.println(ship);
		}
		
	}
}
