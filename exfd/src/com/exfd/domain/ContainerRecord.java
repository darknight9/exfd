package com.exfd.domain;

public class ContainerRecord {

	private String time; // 时间
	private String event; // 事件
	private String location; // 地点
	private String vessel; // 舰船
	private String voyage; // 行程
	private String time2; // 时间2
	private String location2; // 地点2
	private String ext1; // 扩展1
	private String ext2; // 扩展2
	private String ext3; // 扩展3

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVessel() {
		return vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public String getLocation2() {
		return location2;
	}

	public void setLocation2(String location2) {
		this.location2 = location2;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContainerRecord [time=").append(time)
				.append(", event=").append(event).append(", location=")
				.append(location).append(", vessel=").append(vessel)
				.append(", voyage=").append(voyage).append(", time2=")
				.append(time2).append(", location2=").append(location2)
				.append(", ext1=").append(ext1).append(", ext2=").append(ext2)
				.append(", ext3=").append(ext3).append("]");
		return builder.toString();
	}
}
