package com.exfd.domain;

import java.util.Date;

public class Seal {

	private Long id; // id.
	private String code; // 铅封号，目前是4位数字
	private boolean markdel; // 是否记录标记删除
	private int status; // 状态：0未出厂，1出厂，2使用中，3
	private double longitude; // 经度
	private double latitude; // 纬度
	private Date ctime; // 记录生成时间
	private Date mtime; // 记录修改时间
	private Date gpstime; // GPS定位报告时间
	private String poi; // 文字的POI点信息描述
	private Long uid;	// 厂家id.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isMarkdel() {
		return markdel;
	}

	public void setMarkdel(boolean markdel) {
		this.markdel = markdel;
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

	public Date getGpstime() {
		return gpstime;
	}

	public void setGpstime(Date gpstime) {
		this.gpstime = gpstime;
	}

	public String getPoi() {
		return poi;
	}

	public void setPoi(String poi) {
		this.poi = poi;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Seal [id=").append(id).append(", code=").append(code)
				.append(", markdel=").append(markdel).append(", status=")
				.append(status).append(", longitude=").append(longitude)
				.append(", latitude=").append(latitude).append(", ctime=")
				.append(ctime).append(", mtime=").append(mtime)
				.append(", gpstime=").append(gpstime).append(", poi=")
				.append(poi).append("]");
		return builder.toString();
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

}
