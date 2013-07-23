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
		
		seal.setCode("testAdd");
		seal.setStatus(1);
		seal.setLongitude(123.123);
		seal.setLatitude(456.456);
		seal.setCtime(new Date(80, 2, 3, 4, 5, 6));
		seal.setMtime(new Date(93, 4, 5, 6, 7, 8));
		seal.setMarkdel(true);
		seal.setRemark("来自测试testAdd的注释");
		
		seal.setGpstime(new Date(80, 2, 3, 4, 5, 6));
		dao.add(seal);
		System.out.println("----- testAdd testAdd-----");
		System.out.println(seal);
	}
	
	@Test
	public void testAddArray() {
		SealDao dao = new SealDaoImpl();
		ArrayList<Seal> seals = new ArrayList<Seal>();
		Seal seal1 = new Seal();
		Seal seal2 = new Seal();
		Seal seal3 = new Seal();
		seals.add(seal1);
		seals.add(seal2);
		seals.add(seal3);
		for (Seal seal : seals) {
			seal.setCode("testAddArray");
			seal.setStatus(1);
			seal.setLongitude(123.123);
			seal.setLatitude(456.456);
			seal.setCtime(new Date(80, 2, 3, 4, 5, 6));
			seal.setMtime(new Date(93, 4, 5, 6, 7, 8));
			seal.setMarkdel(true);
			seal.setRemark("来自测试testAddArray的注释");
			seal.setGpstime(new Date(80, 2, 3, 4, 5, 6));
		}
		seal1.setCode("testAddArray001");
		seal2.setCode("testAddArray002");
		seal3.setCode("testAddArray003");
		
		dao.add(seals);
		System.out.println("----- testAddArray testAddArray-----");
		for (Seal seal : seals) {
			System.out.println(seal);
		}
	}
	

	@Test
	public void testDelete() {
		SealDao dao = new SealDaoImpl();
		Seal seal = new Seal();
		
		seal.setCode("testDelete");
		seal.setStatus(1);
		seal.setLongitude(123.123);
		seal.setLatitude(456.456);
		seal.setCtime(new Date(80, 2, 3, 4, 5, 6));
		seal.setMtime(new Date(93, 4, 5, 6, 7, 8));
		seal.setMarkdel(true);
		seal.setRemark("来自测试testDelete的注释");
		
		seal.setGpstime(new Date(80, 2, 3, 4, 5, 6));
		dao.add(seal);
		dao.delete(seal);		
	}
	
	@Test
	public void testDelete2() {
		SealDao dao = new SealDaoImpl();
		dao.delete("testAdd");
	}
	
	@Test
	public void testDeleteArray(){
		SealDao dao = new SealDaoImpl();
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("testAddArray001");
		arrayList.add("testAddArray002");
		arrayList.add("testAddArray003");
		dao.delete(arrayList);
	}
	
	@Test
	public void testUpdate() {
		SealDao dao = new SealDaoImpl();
		Seal seal = dao.find("testAdd");
		System.out.println("----- testUpdate testAdd Before-----");
		System.out.println(seal);
		
		seal.setRemark(("来自测试testUpdate的注释"));
		dao.update(seal);

		System.out.println("----- testUpdate testAdd After-----");
		Seal newSeal = dao.find("testAdd");
		System.out.println(newSeal);
	}
	
	@Test
	public void testUpdateArray() {
		SealDao dao = new SealDaoImpl();
		ArrayList<Seal> seals = new ArrayList<Seal>();
		Seal seal1 = dao.find("testAddArray001");
		Seal seal2 = dao.find("testAddArray002");
		Seal seal3 = dao.find("testAddArray003");
		seals.add(seal1);
		seals.add(seal2);
		seals.add(seal3);
		System.out.println("----- testUpdateArray testAdd Before-----");
		for (Seal seal : seals) {
			System.out.println(seal);
		}
		seal1.setRemark(("来自测试testUpdate1111的注释"));
		seal2.setRemark(("来自测试testUpdate2222的注释"));
		seal3.setRemark(("来自测试testUpdate3333的注释"));
		dao.update(seals);

		System.out.println("----- testUpdate testAdd After-----");
		ArrayList<String> codeStrings = new ArrayList<String>();
		codeStrings.add("testAddArray001");
		codeStrings.add("testAddArray002");
		codeStrings.add("testAddArray003");
		ArrayList<Seal> newSeals = dao.find(codeStrings);
		for (Seal seal : newSeals) {
			System.out.println(seal);
		}
	}
	
	@Test
	public void testFind() {
		SealDao dao = new SealDaoImpl();
		Seal seal = dao.find("testAdd");
		System.out.println("----- testFind testAdd-----");
		System.out.println(seal);
	}
	
	@Test
	public void testFindArray() {
		SealDao dao = new SealDaoImpl();
		ArrayList<String> codes = new ArrayList<String>();
		codes.add("testAddArray001");
		codes.add("2899");
		codes.add("test002");
		ArrayList<Seal> seals = dao.find(codes);
		System.out.println("----- testFindArray -----");
		for (Seal seal : seals) {
			System.out.println(seal);
		}
	}
	
	
	@Test
	public void testList() {
		SealDao dao = new SealDaoImpl();
		ArrayList<Seal> seals = dao.list();

		System.out.println("----- testList ALL-----");
		for (Seal seal : seals) {
			System.out.println(seal);
		}
	}
}
