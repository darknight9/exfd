package com.exfd.domain;

import java.util.Date;

public class User {
	private Long id;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private Date createtime; // 建立帐号时间
	private Date logintime; // 本次登录时间
	private Date lasttime; // 上次登录时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Date getLasttime() {
		return lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", username=")
				.append(username).append(", password=").append(password)
				.append(", email=").append(email).append(", nickname=")
				.append(nickname).append(", createtime=").append(createtime)
				.append(", logintime=").append(logintime).append(", lasttime=")
				.append(lasttime).append("]");
		return builder.toString();
	}

}
