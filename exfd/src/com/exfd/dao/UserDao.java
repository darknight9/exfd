package com.exfd.dao;

import com.exfd.domain.User;

public interface UserDao {

	// 添加一个用户.
	void add(User user);

	// 查找用户.
	User find(String username, String password);

	// 查找注册的用户是否在数据库中存在.
	boolean find(String username);

}