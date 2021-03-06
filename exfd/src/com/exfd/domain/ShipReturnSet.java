package com.exfd.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

// 这个类是为了解析返回Array的结果JSON数据。
public class ShipReturnSet {
	
	@SerializedName("return") 
	private List<Ship> ships;

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShipReturnSet [ships=").append(ships).append("]");
		return builder.toString();
	}
}
