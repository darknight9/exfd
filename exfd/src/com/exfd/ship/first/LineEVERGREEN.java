package com.exfd.ship.first;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;

import com.exfd.domain.Container;
import com.exfd.domain.ContainerRecord;
import com.exfd.domain.ContainerStatus;



/**
 * 长荣 EVERGREEN http://www.evergreen-marine.com/tw/
 * EMCU、EISU 、EVRU、EVEU
 * 
 * 实际查询地址：http://www.shipmentlink.com/servlet/TDB1_CargoTracking.do 
 * 请求方式：POST
 * 
 * 有效：
 * EMCU3603350、EISU3842168、EISU3839040、EISU9931581
 * 无效：
 * EMCU3295896、、EMCU9343559
 * 
 */
public class LineEVERGREEN extends LineBase {

	public boolean getContainerByTree(String code, String page, Container container) {
		
		ArrayList<String> arrayList = new ArrayList<String>();
		ContainerStatus status = container.getStatus();	
		
		// 获取table元素.
		getTableElement();
		
		// title和record是通过td上的class不同区分的.
		List<Element> trList = tablebaseElement.getAllElements(HTMLElementName.TR);
		
		List<Element> tdList = null;
		Element resultElement;
		
		// 开始记录record.
		ArrayList<ContainerRecord> historyRecords = status.getHistoryRecords();
		for (Element trelement : trList) {
			
			tdList = trelement.getAllElements(HTMLElementName.TD);
			arrayList.clear();
			
			boolean skipTr = false;
			boolean isTitle = false;
			for (int j = 0; j < tdList.size(); j++) {
				resultElement = tdList.get(j);

				// 有些header设置是在td标签上.
				if (j == 0) {
					String classAttribute = resultElement.getAttributeValue("class");
					if (classAttribute.startsWith("f12rown")) {
						isTitle = false;
					} else if (classAttribute.startsWith("f13tabn3")) {
						isTitle = true;
					} else {
						skipTr = true;
						break;
					}
				}
				String conString = CharacterReference
						.decodeCollapseWhiteSpace(resultElement.getContent()
								.getTextExtractor().toString());
				// logger.debug("i={},j={},{}", i, j, conString);
				arrayList.add(conString);
			}
			if (!skipTr) {
				if (isTitle) {
					list2Record(arrayList, status.getHistoryTitle());
				} else {
					ContainerRecord statusRecord = new ContainerRecord();
					list2Record(arrayList, statusRecord);
					historyRecords.add(statusRecord);
					
					// 这里可以特殊获取集装箱Size.
					status.setSize(arrayList.get(1));
				}
			}
		}
		
		// 复制history最后一条到current.
		status.setStatusRecord(historyRecords.get(historyRecords.size()-1));
		
		// 复制title.
		status.setStatusTitle(status.getHistoryTitle());
		
		return true;
	}
}
