package junit.test;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.exfd.dao.SealDao;
import com.exfd.dao.impl.SealDaoImpl;
import com.exfd.domain.Seal;

public class SealDaoTest {

	@Test
	public void testAdd() {
		SealDao dao = new SealDaoImpl();
		Seal seal = new Seal();
		
		seal.setCode("test002");
		seal.setStatus(1);
		seal.setLongitude(123.123);
		seal.setLatitude(456.456);
		seal.setCtime(new Date(80, 2, 3, 4, 5, 6));
		seal.setMtime(new Date(93, 4, 5, 6, 7, 8));
		seal.setMarkdel(true);
		seal.setRemark("来自测试testAdd的注释");
		
		dao.add(seal);
		seal.setCode("TESTChanged999");
		System.out.println(seal);
		seal = dao.find("test001");
		System.out.println(seal);
	}
	
	@Test
	public void testUpdate() {
		SealDao dao = new SealDaoImpl();
		Seal seal = new Seal();
		
		seal.setCode("test002");
		seal.setStatus(1);
		seal.setLongitude(123.123);
		seal.setLatitude(456.456);
		seal.setCtime(new Date());
		seal.setMtime(new Date());
		seal.setMarkdel(true);
		seal.setRemark(("来自测试testUpdate的注释"));
		
		dao.update(seal);
		seal.setCode("TESTChanged999");
		System.out.println(seal);
		seal = dao.find("test002");
		System.out.println(seal);
	}
	
	@Test
	public void testFind() {
		SealDao dao = new SealDaoImpl();
		Seal seal = dao.find("sid001");
		System.out.println(seal);
	}
	
	@Test
	public void testList() {
		SealDao dao = new SealDaoImpl();
		ArrayList<Seal> array = dao.list();

		for (Seal seal : array) {
			System.out.println(seal);
			
		}
	}
}
