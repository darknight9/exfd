package com.exfd.domain;

import java.util.Date;

public class Container {
	private String code;
	private String trycompany;
	private String company;
	private Date ctime; // 记录生成时间
	private Date mtime; // 记录修改时间
	private Date foundtime; // 记录发现信息时间.
	private int download; // 0:init. 1:download OK.
	private int found; // 0:init. 1:found. 2:not found.
	private int error; // 0:not error.
	private ContainerStatus status;
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
		builder.append("Container [code=").append(code).append(", trycompany=")
				.append(trycompany).append(", company=").append(company)
				.append(", ctime=").append(ctime).append(", mtime=")
				.append(mtime).append(", foundtime=").append(foundtime)
				.append(", download=").append(download).append(", found=")
				.append(found).append(", error=").append(error)
				.append(", status=").append(status).append(", jsonString=")
				.append(jsonString).append(", httpresult=").append(httpresult)
				.append("]");
		return builder.toString();
	}

	public String getTrycompany() {
		return trycompany;
	}

	public void setTrycompany(String trycompany) {
		this.trycompany = trycompany;
	}

	public Date getFoundtime() {
		return foundtime;
	}

	public void setFoundtime(Date foundtime) {
		this.foundtime = foundtime;
	}

	public int getFound() {
		return found;
	}

	public void setFound(int found) {
		this.found = found;
	}
}
