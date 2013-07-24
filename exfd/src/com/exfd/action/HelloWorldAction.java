package com.exfd.action;

public class HelloWorldAction {
	
	private String message;
	
	public String execute(){
		
		message = "尝试一下struts究竟好不好用";
		return "success";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
