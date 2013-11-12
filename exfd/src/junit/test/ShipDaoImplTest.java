package junit.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.exfd.dao.ShipDao;
import com.exfd.dao.impl.ShipDaoImpl;
import com.exfd.domain.Ship;

public class ShipDaoImplTest {

	// "mmsi":"370188000"
	// "mmsi":"370188016"
	@Test
	public void testFind() {
		ShipDaoImpl dao = new ShipDaoImpl();
		Ship ship = dao.find("370188016");
		System.out.println(ship);
	}
	
	// "mmsi":"370188000"
	// "mmsi":"370188016"
	@Test
	public void testFindDetail() {
		ShipDaoImpl dao = new ShipDaoImpl();
		List<Ship> ships = dao.findDetail("", "中远非洲", "name", 0, 20);
		for (Ship ship : ships) {
			System.out.println(ship);
		}
		
	}
	
	// "mmsi":"370188000"
	// "mmsi":"477617800" 中远鞍钢
	@Test
	public void testAdd() {
		ShipDaoImpl dao = new ShipDaoImpl();
		Ship ship = dao.find("477617800");
		System.out.println(ship);
		
		// 纯为测试，修改ID.
		ship.setId(null);
		ship.setMmsi("110");
		dao.add(ship);
		
		Ship ship2 = dao.find("110");
		System.out.println(ship2);
		
	}
	
	// 和testADD连体测试.
	@Test
	public void testDelete() {
		ShipDaoImpl dao = new ShipDaoImpl();
		Ship ship = dao.find("110");
		System.out.println(ship);
		
		dao.delete(ship);
		Ship ship2 = dao.find("110");
		System.out.println(ship2);
		
	}
	
	// "mmsi":"477617800" 中远鞍钢
	@Test
	public void testUpdate() {
		ShipDaoImpl dao = new ShipDaoImpl();
		Ship ship = dao.find("477617800");
		System.out.println(ship);
		
		String shipid = ship.getShipid();
		ship.setShipid("XXXXAABBXXXX");
		dao.update(ship);
		
		Ship ship2 = dao.find("477617800");
		System.out.println(ship2);
		ship2.setShipid(shipid);
		dao.update(ship2);
		
		Ship ship3 = dao.find("477617800");
		System.out.println(ship3);
	}
	
	@Test
	public void testList() {
		ShipDaoImpl dao = new ShipDaoImpl();
		ArrayList<Ship> ships = dao.list();
		for (Ship ship : ships) {
			System.out.println(ship);
		}
	}
	
	// "mmsi":"370188000"
	// "mmsi":"370188016"
	@Test
	public void testFindDetail2() {
		ShipDao dao = new ShipDaoImpl();
		List<Ship> ships = dao.findDetail("", "370188016", "mmsi", 0, 2);
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
	
	@Test
	public void testFindDetail4() {
		ShipDao dao = new ShipDaoImpl();
		List<Ship> ships = dao.findDetail("", "中AGC", "name", 1, 20);
		for (Ship ship : ships) {
			System.out.println(ship);
		}
		
	}
}
