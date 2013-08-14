package com.exfd.domain;

public class ContainerRecord {

	private String time;		// 时间
	private String event;		// 事件
	private String location;	// 地点
	private String vessel;		// 舰船
	private String voyage;		// 行程
	private Boolean header;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContainerRecord [time=").append(time)
				.append(", event=").append(event).append(", location=")
				.append(location).append(", vessel=").append(vessel)
				.append(", voyage=").append(voyage).append(", header=")
				.append(header).append("]");
		return builder.toString();
	}

	public Boolean getHeader() {
		return header;
	}

	public void setHeader(Boolean header) {
		this.header = header;
	}
}
