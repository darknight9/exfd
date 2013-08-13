package com.exfd.domain;

import java.util.ArrayList;

public class ContainerStatus {
	private String code;
	private String size;
	private String company;
	private ContainerRecord statusTitle;
	private ContainerRecord historyTitle;
	private ContainerRecord statusRecord;
	private ArrayList<ContainerRecord> historyRecords;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public ContainerRecord getStatusRecord() {
		return statusRecord;
	}
	public void setStatusRecord(ContainerRecord statusRecord) {
		this.statusRecord = statusRecord;
	}
	public ArrayList<ContainerRecord> getHistoryRecords() {
		return historyRecords;
	}
	public void setHistoryRecords(ArrayList<ContainerRecord> historyRecords) {
		this.historyRecords = historyRecords;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContainerStatus [code=").append(code).append(", size=")
				.append(size).append(", company=").append(company)
				.append(", statusTitle=").append(statusTitle)
				.append(", historyTitle=").append(historyTitle)
				.append(", statusRecord=").append(statusRecord)
				.append(", historyRecords=").append(historyRecords).append("]");
		return builder.toString();
	}
	public ContainerRecord getStatusTitle() {
		return statusTitle;
	}
	public void setStatusTitle(ContainerRecord statusTitle) {
		this.statusTitle = statusTitle;
	}
	public ContainerRecord getHistoryTitle() {
		return historyTitle;
	}
	public void setHistoryTitle(ContainerRecord historyTitle) {
		this.historyTitle = historyTitle;
	}
}
