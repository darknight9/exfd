package com.exfd.service.impl;

import com.exfd.dao.UserDao;
import com.exfd.dao.impl.UserDaoImpl;
import com.exfd.domain.User;
import com.exfd.exception.UserExistException;
import com.exfd.util.ServiceUtils;

// 对web层提供所有的业务服务.
public class BusinessServieImpl {

	private UserDao dao = new UserDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	
	// 对web层提供注册服务.
	public void register(User user) throws UserExistException{
		
		// 先判断当前要注册的用户是否存在.
		boolean b = dao.find(user.getUsername());
		if(b){
			throw new UserExistException();	// 发现要注册的用户已存在，则给web层抛一个编译时异常，提醒web层处理这个异常，给用户一个友好提示。
		}else{
			user.setPassword(ServiceUtils.md5(user.getPassword()));
			dao.add(user);
		}
	}
	
	// 对web层提供登陆服务.
	public User login(String username, String password){	
		
		// TODO 暂时使用明文保存密码.
		//password = ServiceUtils.md5(password);
		return dao.find(username, password);
	}
}
