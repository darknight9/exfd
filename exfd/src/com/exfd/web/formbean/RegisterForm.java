package com.exfd.web.formbean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class RegisterForm {
	private String username;
	private String password;
	private String password2;
	private String email;
	//private String birthday;
	private String nickname;

	private Map errors = new HashMap();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
	}

	// 用户名不能为空，并且要是3-8位字母.
	// 密码不能为空，并且是3-8位数字.
	// 确认密码不能为空，并且和密码一致.
	// 电子邮箱不能为空，并且要是一个格式合法的邮箱
	// 生日可以为空.不为空时，必须要是一个日期.
	// 昵称不可以为空，并且要是汉字.
	public boolean validate() {
		boolean isOK = true;
		
		// 用户名不能为空，并且要是3-8位字母.
		if (this.username == null || this.username.trim().equals("")) {
			isOK = false;
			errors.put("username", "用户名不能为空！");
		} else {
			if (!this.username.matches("[A-za-z]{3,8}")) {
				isOK = false;
				errors.put("username", "用户名必须是3-8位字母");
			}
		}

		// 密码不能为空，并且是3-8位数字.
		if (this.password == null || this.password.trim().equals("")) {
			isOK = false;
			errors.put("password", "密码不能为空！");
		} else {
			if (!this.password.matches("\\d{3,8}")) {
				isOK = false;
				errors.put("password", "密码必须是3-8位数字");
			}
		}
		
		// 确认密码不能为空，并且和密码一致.
		if (this.password2 == null || this.password2.trim().equals("")) {
			isOK = false;
			errors.put("password2", "确认密码不能为空！");
		} else {
			if (!this.password.equals(this.password2)) {
				isOK = false;
				errors.put("password2", "两次密码不一致！");
			}
		}

		// 电子邮箱不能为空，并且要是一个格式合法的邮箱
		if (this.email == null || this.email.trim().equals("")) {
			isOK = false;
			errors.put("email", "电子邮箱不能为空！");
		} else {
			// aa@sina.com aaa@sina.com.cn aa_bb.cc@sina.com.cn
			// \\w+@\\w+(\\.\\w+)+
			if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
				isOK = false;
				errors.put("email", "电子邮箱格式不正确！");
			}
		}
		
		// 生日可以为空.不为空时，必须要是一个日期.
		/*
		if(this.birthday!=null && !this.birthday.trim().equals("")){
			try {
				DateLocaleConverter dlc = new DateLocaleConverter();
				dlc.convert(this.birthday, "yyyy-MM-dd");
			} catch (Exception e) {
				isOK = false;
				errors.put("birthday", "日期格式不正确！");
			}
		}
		*/
		
		// 昵称不可以为空，并且要是汉字.
		if (this.nickname == null || this.nickname.trim().equals("")) {
			isOK = false;
			errors.put("nickname", "昵称不能为空！");
		} else {
			// aa@sina.com aaa@sina.com.cn aa_bb.cc@sina.com.cn
			// \\w+@\\w+(\\.\\w+)+
			if (!this.nickname.matches("^([\u4e00-\u9fa5]+)$")) {
				isOK = false;
				errors.put("nickname", "昵称必须是汉字！");
			}
		}		
		return isOK;
	}


}
