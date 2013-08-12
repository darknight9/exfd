package com.exfd.dao;

import java.util.List;
import java.util.Map;

import com.exfd.domain.Ship;

// Ship Dao层.
public interface ShipDao {

	// 添加一个记录.
	void add(Ship ship);

	// 添加多个记录.
	void add(List<Ship> ships);
	
	// 删除一个记录.
	void delete(Ship ship);
	
	// 删除一个记录(通过code).
	void delete(String code);
	
	// 删除多个记录.
	void delete(List<String> codes);
	
	// 更新一条记录.
	void update(Ship ship);
	
	// 更新多条记录.
	void update(List<Ship> ships);
	
	// 查找一个记录.
	Ship find(String code);
	
	// 查找多个记录.
	Map<String, Ship> find(List<String> codes);
	
	/**
	 * 根据关键字和类型对船舶基本信息表进行搜索，并根据开始结束位置返回记录
	 * @param operid：用户ID
	 * @param keystr：关键字
	 * @param type ，分为：name，mmsi，imo，callsign
	 * @param start_ship： 开始位置，第一条记录是从1开始的
	 * @param end_ship：结束位置
	 * @return：船舶详情对象集合，具体定义见“对象键值参数说明”
	 */
	public List<Ship> findDetail(String operid,String keystr,String type,int start_ship,int end_ship);

	// 列出全部的记录.
	public List<Ship> list();

	public abstract void updateOrAdd(List<Ship> ships);

	public abstract void updateOrAdd(Ship ship);

}
