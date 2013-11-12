package com.exfd.domain;

import java.util.Date;

public class SealRecord {

	private Long id; // id.
	private String code; // 铅封号，目前是4位数字
	private double longitude; // 经度
	private double latitude; // 纬度

	private Date gpstime; // GPS定位报告时间
	private String poi; // 文字的POI点信息描述

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
		builder.append("SealRecord [id=").append(id).append(", code=")
				.append(code).append(", longitude=").append(longitude)
				.append(", latitude=").append(latitude).append(", gpstime=")
				.append(gpstime).append(", poi=").append(poi).append("]");
		return builder.toString();
	}
}