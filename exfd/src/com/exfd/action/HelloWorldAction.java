package com.exfd.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public String execute(){
		
		message = "尝试一下struts究竟好不好用";
		return SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
