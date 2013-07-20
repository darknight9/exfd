package junit.test;

import java.util.Date;

import org.junit.Test;

import com.exfd.dao.UserDao;
import com.exfd.dao.impl.UserDaoImpl;
import com.exfd.domain.User;

public class UserDaoTest {

	@Test
	public void testAdd() {
		User user = new User();
		user.setBirthday(new Date());
		user.setEmail("bb@sina.com");
		user.setId("222");
		user.setNickname("李二");
		user.setPassword("123");
		user.setUsername("bbbb");
		
		UserDao dao = new UserDaoImpl();
		dao.add(user);
	}
	
	@Test
	public void testFind(){
		UserDao dao = new UserDaoImpl();
		dao.find("aaa", "123");
	}
	
	@Test
	public void testFindByName(){
		UserDao dao = new UserDaoImpl();
		System.out.println(dao.find("aaa"));
	}
}
