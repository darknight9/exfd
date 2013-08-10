package com.exfd.domain;

import java.util.Date;

public class Seal {

	private String code; // 铅封号，目前是4位数字
	private int status; // 状态：0未出厂，1出厂，2使用中，3
	private double longitude; // 经度
	private double latitude; // 纬度
	private Date ctime; // 记录生成时间
	private Date mtime; // 记录修改时间
	private boolean markdel; // 是否记录标记删除
	private String remark; // 备注

	private String plate; // 车牌号
	private Date gpstime; // GPS定位报告时间
	private int speed; // 车速
	private double direction; // 方位角
	private double daymiles; // 日里程
	private double monthmiles; // 月里程
	private int oil; // 油量
	private String engst; // 发动机状态
	private String poi; // 文字的POI点信息描述

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

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Date getGpstime() {
		return gpstime;
	}

	public void setGpstime(Date gpstime) {
		this.gpstime = gpstime;
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

	public double getDaymiles() {
		return daymiles;
	}

	public void setDaymiles(double daymiles) {
		this.daymiles = daymiles;
	}

	public double getMonthmiles() {
		return monthmiles;
	}

	public void setMonthmiles(double monthmiles) {
		this.monthmiles = monthmiles;
	}

	public int getOil() {
		return oil;
	}

	public void setOil(int oil) {
		this.oil = oil;
	}

	public String getEngst() {
		return engst;
	}

	public void setEngst(String engst) {
		this.engst = engst;
	}

	public String getPoi() {
		return poi;
	}

	public void setPoi(String poi) {
		this.poi = poi;
	}

	@Override
	public String toString() {
		return "Seal [code=" + code + ", status=" + status + ", longitude="
				+ longitude + ", latitude=" + latitude + ", ctime=" + ctime
				+ ", mtime=" + mtime + ", markdel=" + markdel + ", remark="
				+ remark + ", plate=" + plate + ", gpstime=" + gpstime
				+ ", speed=" + speed + ", direction=" + direction
				+ ", daymiles=" + daymiles + ", monthmiles=" + monthmiles
				+ ", oil=" + oil + ", engst=" + engst + ", poi=" + poi + "]";
	}

}
