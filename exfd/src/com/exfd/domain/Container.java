package com.exfd.domain;

import java.util.Date;

public class Container {
	private String code;
	private String company;
	private Date ctime; // 记录生成时间
	private Date mtime; // 记录修改时间
	private int download; // 0:not download. 1:download fail. 2:download OK.
	private int notfound; // 0:not process. 1:notfound.
	private int error; // 0:not parse. 1:找不到hint position点.
	private ContainerStatus status;
	private String tableString;
	private String jsonString;
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

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public String getTableString() {
		return tableString;
	}

	public void setTableString(String tableString) {
		this.tableString = tableString;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public int getDownload() {
		return download;
	}

	public void setDownload(int download) {
		this.download = download;
	}

	public int getNotfound() {
		return notfound;
	}

	public void setNotfound(int notfound) {
		this.notfound = notfound;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public ContainerStatus getStatus() {
		return status;
	}

	public void setStatus(ContainerStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Container [code=").append(code).append(", company=")
				.append(company).append(", ctime=").append(ctime)
				.append(", mtime=").append(mtime).append(", download=")
				.append(download).append(", notfound=").append(notfound)
				.append(", error=").append(error).append(", status=")
				.append(status).append(", tableString=").append(tableString)
				.append(", jsonString=").append(jsonString)
				.append(", httpresult=").append(httpresult).append("]");
		return builder.toString();
	}
}
