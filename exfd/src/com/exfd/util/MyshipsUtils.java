package com.exfd.util;

import org.apache.axis2.AxisFault;

import com.webservice.GeneralShipWebServiceImplServiceStub;
import com.webservice.GetSearchRecByKeyAndTypeInShipBaseInfo;
import com.webservice.GetSearchRecByKeyAndTypeInShipBaseInfoE;

public class MyshipsUtils {

	/**
	 * 根据关键字和类型对船舶基本信息表进行搜索，并根据开始结束位置返回记录
	 * @param operid：用户ID
	 * @param keystr：关键字
	 * @param type ，分为：name，mmsi，imo，callsign
	 * @param start_ship： 开始位置，第一条记录是从1开始的
	 * @param end_ship：结束位置
	 * @return：船舶详情对象集合，具体定义见“对象键值参数说明”
	 */
	public static String getSearchRecByKeyAndTypeInShipBaseInfo(String operid,
			String keystr, String type, int start_ship, int end_ship) throws Exception {
		
		//初始化Sub类
		GeneralShipWebServiceImplServiceStub stub = new GeneralShipWebServiceImplServiceStub();
		
		GetSearchRecByKeyAndTypeInShipBaseInfo info = new GetSearchRecByKeyAndTypeInShipBaseInfo();
		info.setArg0(operid);
		info.setArg1(keystr);
		info.setArg2(type);
		info.setArg3(start_ship);
		info.setArg4(end_ship);
		
		GetSearchRecByKeyAndTypeInShipBaseInfoE command = new GetSearchRecByKeyAndTypeInShipBaseInfoE();
		command.setGetSearchRecByKeyAndTypeInShipBaseInfo(info);

		//取得返回值
		String name = stub.getSearchRecByKeyAndTypeInShipBaseInfo(command).getGetSearchRecByKeyAndTypeInShipBaseInfoResponse().get_return();
		
		System.out.println(name);
		
		return name;

	}
}
