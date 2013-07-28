package com.exfd.domain;

public class Container {
	private String code;
	private String company;
	private String httpresult;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getHttpresult() {
		return httpresult;
	}
	public void setHttpresult(String httpresult) {
		this.httpresult = httpresult;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Container [code=").append(code).append(", company=")
				.append(company).append(", httpresult=").append(httpresult)
				.append("]");
		return builder.toString();
	}
}
