package com.exfd.domain;

import java.util.ArrayList;

public class ContainerStatus {
	private String code; // 集装箱号
	private String size; // 集装箱大小
	private String company; // 集装箱查询时使用的船公司
	private String description; // 集装箱信息描述
	private ContainerRecord statusTitle; // 当前状态拦的各项描述标题
	private ContainerRecord historyTitle; // 历史状态栏的各项描述标题
	private ContainerRecord statusRecord; // 当前状态记录
	private ArrayList<ContainerRecord> historyRecords; // 历史状态记录(多项)

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContainerStatus [code=").append(code).append(", size=")
				.append(size).append(", company=").append(company)
				.append(", description=").append(description)
				.append(", statusTitle=").append(statusTitle)
				.append(", historyTitle=").append(historyTitle)
				.append(", statusRecord=").append(statusRecord)
				.append(", historyRecords=").append(historyRecords).append("]");
		return builder.toString();
	}
}
