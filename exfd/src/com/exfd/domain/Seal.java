package com.exfd.domain;

import java.util.Date;

public class Seal {

	private String code;
	private int status;
	private double longitude;
	private double latitude;
	private Date ctime;
	private Date mtime;
	private boolean markdel;
	private String remark;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
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

	public boolean isMarkdel() {
		return markdel;
	}

	public void setMarkdel(boolean markdel) {
		this.markdel = markdel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Seal [code=" + code + ", status=" + status + ", longitude="
				+ longitude + ", latitude=" + latitude + ", ctime=" + ctime
				+ ", mtime=" + mtime + ", markdel=" + markdel + ", remark="
				+ remark + "]";
	}

}
