package com.exfd.ship.first;

import java.util.ArrayList;
import java.util.List;
import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.StartTag;

import com.exfd.domain.Container;
import com.exfd.domain.ContainerRecord;
import com.exfd.domain.ContainerStatus;


/**
 * 中远	COSCO		CBHU 、COSU
 * CBHU1835330、CBHU8234537
 * http://www.cosco.com/cn/
 *
 */
public class LineCOSCO extends LineBase {
	
	// 虽然有"Latest Container Status"表格，但是这个表格的内容都可以从历史记录表格获取，而且信息更加准确.
	// 所以不再解析""Latest Container Status"表格.直接使用历史表格复制信息.
	public boolean getContainerByTree(String code, String page, Container container) {
		
		// 获取"Current shipment cycle status"表格位置.
		int currentPos = page.indexOf("Current shipment cycle status");

		// 找不到标记点，返回.
		if (currentPos < 0) {
			container.setError(1);
			logger.debug("CONT[{}] container page not find lastest pos[{}] postion.", code, currentPos);
			return false;
		}
		
		ArrayList<String> arrayList = new ArrayList<String>();
		StartTag startTable = null;
		startTable = source.getNextStartTag(hintPos, "table");
		Element tableElement = startTable.getElement();
		
		// 实际的表格在第2个table.
		startTable = source.getNextStartTag(tableElement.getEnd(),"table");
		
		// 开始记录title.
		List<Element> trList = tableElement.getAllElementsByClass("header1");
		List<Element> tdList = trList.get(0).getAllElements(HTMLElementName.TD);

		ContainerStatus status = container.getStatus();		
		
		arrayList.clear();
		for (Element element : tdList) {
			String conString = CharacterReference
					.decodeCollapseWhiteSpace(element.getContent()
							.getTextExtractor().toString());
			arrayList.add(conString);
		}
		list2Record(arrayList, status.getHistoryTitle());
		
		// 开始记录record.
		ArrayList<ContainerRecord> historyRecords = status.getHistoryRecords();
		trList = tableElement.getAllElementsByClass("graycolor");
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
		
		// 复制history第一条到current.
		status.setStatusRecord(historyRecords.get(0));
		status.setStatusTitle(status.getHistoryTitle());
		
		return true;
	}
	
}
