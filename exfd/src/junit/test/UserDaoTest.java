package junit.test;

import org.junit.Test;

import com.exfd.dao.UserDao;
import com.exfd.dao.impl.UserDaoImpl;
import com.exfd.domain.User;

public class UserDaoTest {

	@Test
	public void testAdd() {
		User user = new User();
		user.setUsername("zhangsan");
		user.setPassword("123");
		user.setNickname("张三");
		user.setEmail("zhangsan@sina.com");
		
		UserDao dao = new UserDaoImpl();
		dao.add(user);
	}
	
	@Test
	public void testFind(){
		UserDao dao = new UserDaoImpl();
		dao.find("zhangsan", "123");
	}
	
	@Test
	public void testFindByName(){
		UserDao dao = new UserDaoImpl();
		System.out.println(dao.find("zhangsan"));
	}
}
