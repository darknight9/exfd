package com.exfd.ship.first;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.StartTag;

import com.exfd.domain.Container;
import com.exfd.domain.ContainerRecord;
import com.exfd.domain.ContainerStatus;

/**
 * 法国达飞 CMA CMAU、CMCU CMAU1396117 达贸航运 DELMAS（这个公司已被达飞收购，可用达飞网站查询） CAXU、DVRU
 * CAXU2881651 DVRU1473443 正利 CNC（这个公司已被达飞收购，可用达飞网站查询） IPXU、ASLU、ECMU、CAXU
 * IPXU3131473、ASLU2005450、ECMU9138106、CAXU9286394、ECMU9040568
 * http://www.cma-cgm.com/
 * 
 */
public class LineCMA extends LineBase {
	
	public boolean getContainerByTree(String code, String page, Container container) {
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		// 获取table元素.
		getTableElement();
				
		List<Element> trHeaderList = tablebaseElement.getAllElementsByClass("TrackingLabel TrackingPaddingTable");
		List<Element> trList = tablebaseElement.getAllElementsByClass("TrackingRow");
		
		// 开始记录title.
		List<Element> tdList = trHeaderList.get(0).getAllElements(HTMLElementName.TD);

		ContainerStatus status = container.getStatus();		
		
		// 记录title.
		arrayList.clear();
		for (Element element : tdList) {
			String conString = CharacterReference
					.decodeCollapseWhiteSpace(element.getContent()
							.getTextExtractor().toString());
			arrayList.add(conString);
		}
		list2Record(arrayList, status.getHistoryTitle());

		if (trHeaderList.size()>1) {
			tdList = trHeaderList.get(1).getAllElements(HTMLElementName.TD);
			arrayList.clear();
			for (Element element : tdList) {
				String conString = CharacterReference
						.decodeCollapseWhiteSpace(element.getContent()
								.getTextExtractor().toString());
				arrayList.add(conString);
			}
			list2Record(arrayList, status.getStatusTitle());
		} else {
			status.setStatusTitle(status.getHistoryTitle());
		}
		
		// 开始记录record.
		ArrayList<ContainerRecord> historyRecords = status.getHistoryRecords();
		for (Element trelement : trList) {
			ContainerRecord statusRecord = new ContainerRecord();
			tdList = trelement.getAllElements(HTMLElementName.TD);
			arrayList.clear();
			for (Element element : tdList) {
				String conString = CharacterReference
						.decodeCollapseWhiteSpace(element.getContent()
								.getTextExtractor().toString());
				arrayList.add(conString);
			}
			list2Record(arrayList, statusRecord);
			historyRecords.add(statusRecord);
		}
		
		// 复制history最后一条到current.
		status.setStatusRecord(historyRecords.get(historyRecords.size()-1));
		
		return true;
	}
}
