package junit.test;

import java.util.List;

import org.junit.Test;

import com.exfd.dao.impl.ShipMyshipsDaoImpl;
import com.exfd.domain.Ship;
import com.exfd.util.MyshipsUtils;

public class MyshipsUtilsTest {

	@Test
	public void testFindDetail() throws Exception{
		
		String jsonString = MyshipsUtils.getSearchRecByKeyAndTypeInShipBaseInfo("", "中远非洲", "name", 1, 10);
		System.out.println(jsonString);
	}
	
	@Test
	public void testFindDetail2() throws Exception{
		
		String jsonString = MyshipsUtils.getSearchRecByKeyAndTypeInShipBaseInfo("", "中ABC", "name", 1, 10);
		System.out.println(jsonString);
		if (jsonString == null || jsonString.trim().equals("")) {
			System.out.println("json is empty!");
		}
		else {
			System.out.println("lenght="+jsonString.length()+"string="+jsonString);
			System.out.println("lenght="+jsonString.trim().length()+"string="+jsonString.trim());
		}
	}
}
