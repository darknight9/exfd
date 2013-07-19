package com.exfd.dao.impl;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.exfd.domain.User;
import com.exfd.util.XmlUtils;

public class UserDaoImpl {

	public void add(User user) {
		try {
			Document document = XmlUtils.getDocument();
			Element root = document.getRootElement();
			
			Element user_tag = root.addElement("user");
			user_tag.setAttributeValue("id", user.getId());
			user_tag.setAttributeValue("username", user.getUsername());
			user_tag.setAttributeValue("password", user.getPassword());
			user_tag.setAttributeValue("email", user.getEmail());
			user_tag.setAttributeValue("birthday", user.getBirthday()==null?"":user.getBirthday().toLocaleString());
			user_tag.setAttributeValue("nickname", user.getNickname());

			XmlUtils.write2Xml(document);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public User find(String username, String password) {
		
		try {
			Document document = XmlUtils.getDocument();
			document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");
		} catch (DocumentException e) { 
			throw new RuntimeException(e);
		}
		return null;
	}
	
	// 查找注册的用户是否在数据库中存在.
	public User find(String username) {
		
		return null;
	}
}
