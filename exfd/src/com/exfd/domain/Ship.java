package com.exfd.domain;

public class Ship {

	private String shipid;
	private String shipname;// 中文船名
	private String shipnamecn;// 英文船名
	private String mmsi;
	private String imo;

	private String callsign;// 呼号
	private String shipflag;// 船籍
	private String shiptype;// 船舶类型
	private double shiplength;// 船长
	private double shipwidth;// 船宽

	private double draft;// 吃水
	private String gpstime;// 报位时间
	private String latitude; // 纬度，度分秒格式
	private String longitude;// 经度，度分秒格式
	private double lat; // 纬度 小数值
	private double lon; // 经度 小数值

	private double speed;// 航速
	private double direction;// 航向
	private double truehending;// 航首向
	private String reporttype;// 报位方式
	private String state;// 航行状态
	private long updatetime;// 更新时间

	private String gpstimepre = ""; // 上一个位置的定位时间
	private double latpre = 0.00; // 上一个位置的纬度
	private double lonpre = 0.00; // 上一个位置的经度
	private double averagespeed = 0.00; // 平均速度
	private double distanceMoved = 0.00; // 最后行驶距离

	public String getShipid() {
		return shipid;
	}

	public void setShipid(String shipid) {
		this.shipid = shipid;
	}

	public String getShipname() {
		return shipname;
	}

	public void setShipname(String shipname) {
		this.shipname = shipname;
	}

	public String getShipnamecn() {
		return shipnamecn;
	}

	public void setShipnamecn(String shipnamecn) {
		this.shipnamecn = shipnamecn;
	}

	public String getMmsi() {
		return mmsi;
	}

	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}

	public String getImo() {
		return imo;
	}

	public void setImo(String imo) {
		this.imo = imo;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getShipflag() {
		return shipflag;
	}

	public void setShipflag(String shipflag) {
		this.shipflag = shipflag;
	}

	public String getShiptype() {
		return shiptype;
	}

	public void setShiptype(String shiptype) {
		this.shiptype = shiptype;
	}

	public double getShiplength() {
		return shiplength;
	}

	public void setShiplength(double shiplength) {
		this.shiplength = shiplength;
	}

	public double getShipwidth() {
		return shipwidth;
	}

	public void setShipwidth(double shipwidth) {
		this.shipwidth = shipwidth;
	}

	public double getDraft() {
		return draft;
	}

	public void setDraft(double draft) {
		this.draft = draft;
	}

	public String getGpstime() {
		return gpstime;
	}

	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public double getTruehending() {
		return truehending;
	}

	public void setTruehending(double truehending) {
		this.truehending = truehending;
	}

	public String getReporttype() {
		return reporttype;
	}

	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}

	public String getGpstimepre() {
		return gpstimepre;
	}

	public void setGpstimepre(String gpstimepre) {
		this.gpstimepre = gpstimepre;
	}

	public double getLatpre() {
		return latpre;
	}

	public void setLatpre(double latpre) {
		this.latpre = latpre;
	}

	public double getLonpre() {
		return lonpre;
	}

	public void setLonpre(double lonpre) {
		this.lonpre = lonpre;
	}

	public double getAveragespeed() {
		return averagespeed;
	}

	public void setAveragespeed(double averagespeed) {
		this.averagespeed = averagespeed;
	}

	public double getDistanceMoved() {
		return distanceMoved;
	}

	public void setDistanceMoved(double distanceMoved) {
		this.distanceMoved = distanceMoved;
	}

	@Override
	public String toString() {
		return "Ship [shipid=" + shipid + ", shipname=" + shipname
				+ ", shipnamecn=" + shipnamecn + ", mmsi=" + mmsi + ", imo="
				+ imo + ", callsign=" + callsign + ", shipflag=" + shipflag
				+ ", shiptype=" + shiptype + ", shiplength=" + shiplength
				+ ", shipwidth=" + shipwidth + ", draft=" + draft
				+ ", gpstime=" + gpstime + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", lat=" + lat + ", lon=" + lon
				+ ", speed=" + speed + ", direction=" + direction
				+ ", truehending=" + truehending + ", reporttype=" + reporttype
				+ ", state=" + state + ", updatetime=" + updatetime
				+ ", gpstimepre=" + gpstimepre + ", latpre=" + latpre
				+ ", lonpre=" + lonpre + ", averagespeed=" + averagespeed
				+ ", distanceMoved=" + distanceMoved + "]";
	}

}
