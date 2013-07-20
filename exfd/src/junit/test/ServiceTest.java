package junit.test;

import java.util.Date;

import org.junit.Test;

import com.exfd.dao.UserDao;
import com.exfd.dao.impl.UserDaoImpl;
import com.exfd.domain.User;
import com.exfd.exception.UserExistException;
import com.exfd.service.impl.BusinessServieImpl;

public class ServiceTest {

	@Test
	public void testRegister(){
		User user = new User();
		user.setUsername("cccc");
		user.setBirthday(new Date());
		user.setEmail("cc@sina.com");
		user.setId("333");
		user.setNickname("李三");
		user.setPassword("123");

		BusinessServieImpl service = new BusinessServieImpl();
		try {
			service.register(user);
			System.out.println("注册成功！");
		} catch (UserExistException e) {
			System.out.println("用户已存在!");
		}
	}
	
	@Test
	public void testLogin(){
		BusinessServieImpl service = new BusinessServieImpl();
		User user = service.login("ggggggg", "123456");
		System.out.println(user);
		user = service.login("cccc", "123");
		System.out.println(user);
	}
}
