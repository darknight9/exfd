package com.exfd.domain;

import java.util.Date;

public class SealHistoryRecord {
	private String code; // 铅封号，目前是4位数字
	private double longitude; // 经度
	private double latitude; // 纬度
	private int speed; // 车速
	private double direction; // 方位角
	
	private Date gpstime; // GPS定位报告时间
	private String poi; // 文字的POI点信息描述
	private String engst; // 发动机状态
	private int oil; // 油量
	private double monthmiles; // 月里程

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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
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

	public String getEngst() {
		return engst;
	}

	public void setEngst(String engst) {
		this.engst = engst;
	}

	public int getOil() {
		return oil;
	}

	public void setOil(int oil) {
		this.oil = oil;
	}

	public double getMonthmiles() {
		return monthmiles;
	}

	public void setMonthmiles(double monthmiles) {
		this.monthmiles = monthmiles;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SealHistoryRecord [code=").append(code)
				.append(", longitude=").append(longitude).append(", latitude=")
				.append(latitude).append(", speed=").append(speed)
				.append(", direction=").append(direction).append(", gpstime=")
				.append(gpstime).append(", poi=").append(poi)
				.append(", engst=").append(engst).append(", oil=").append(oil)
				.append(", monthmiles=").append(monthmiles).append("]");
		return builder.toString();
	}
}
