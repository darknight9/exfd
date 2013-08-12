package com.exfd.ship.first;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftConditionalCommentTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

import org.apache.commons.configuration.SubnodeConfiguration;

import com.exfd.domain.Container;
import com.exfd.domain.ContainerRecord;


/**
 * 中远	COSCO		CBHU 、COSU
 * CBHU1835330、CBHU8234537
 * http://www.cosco.com/cn/
 *
 */
public class LineCOSCO extends LineBase {
	public Container GetContainerByPage(String code, String page) {

		// 获取公司名称, 去掉前面的"Line".
		String company = this.getClass().getSimpleName().substring(4);

		Container container = new Container();
		container.setCode(code);
		container.setCompany(company);

		// 获取当前的船公司的配置信息.
		SubnodeConfiguration sub = config
				.configurationAt("/container[@classname='"
						+ this.getClass().getSimpleName() + "']/parse");

		// 检查是否没有找到箱子.
		String notfoundString = sub.getString("notfound");
		if (page.indexOf(notfoundString) >= 0) {
			logger.info("container [{}] not found.", code);
			container.setNotfound(1);
			return container;
		}

		// 寻找结果.
		MicrosoftConditionalCommentTagTypes.register();
		PHPTagTypes.register();
		PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this
											// example otherwise they override
											// processing instructions
		MasonTagTypes.register();
		Source source = new Source(page);
		String hintString = sub.getString("hint");
		hintString = hintString.replace("EXFDCODE", code);
		logger.debug(hintString);

		int flagPos = page.indexOf(hintString);

		// 找不到标记点，返回.
		if (flagPos < 0) {
			container.setParseerror(1);
			return container;
		}
		String dirString = sub.getString("dir");
		StartTag start = null;
		if (dirString.equals("next")) {
			start = source.getNextStartTag(flagPos, "table");
		} else {
			start = source.getPreviousStartTag(flagPos, "table");
		}
		logger.debug(start);
		Element tableElement = start.getElement();
		Element resultElement = null;
		List<Element> trList = null;
		String rowclass = sub.getString("rowclass");
		String rowclassPattern = sub.getString("rowclassPattern");
		if (rowclass != null) {

		} else if (rowclassPattern != null) {
			Pattern valueRegexPattern = Pattern.compile(rowclass);
			trList = tableElement.getAllElements("class", valueRegexPattern);
		} else {
			trList = tableElement.getAllElements(HTMLElementName.TR);
		}

		ArrayList<ArrayList<String>> aLists = new ArrayList<ArrayList<String>>();
		ArrayList<ContainerRecord> records = new ArrayList<ContainerRecord>();

		// 默认记录是record，而不是header.
		String defaultRecord = sub.getString("defaultRecord");
		String recordClassStart = sub.getString("recordClassStart");
		String headerClassStart = sub.getString("headerClassStart");
		int usetdclass = sub.getInt("usetdclass");
		int timeIndex = sub.getInt("time");
		int eventIndex = sub.getInt("event");
		int locationIndex = sub.getInt("location");
		int vesselIndex = sub.getInt("vessel");
		int voyageIndex = sub.getInt("voyage");

		boolean isHeaderDefault = false;
		if (defaultRecord.equals("false")) {
			isHeaderDefault = true;
		}
		for (int i = 0; i < trList.size(); i++) {
			Element trElement = trList.get(i);
			boolean isHeader = isHeaderDefault;
			
			// 如果标记在tr上，删除不符合条件的tr行.
			String classAttribute = trElement.getAttributeValue("class");
			if (usetdclass == 0) {
				if (classAttribute != null && recordClassStart != null
						&& classAttribute.startsWith(recordClassStart)) {
					isHeader = false;
				} else if (classAttribute != null && headerClassStart != null
						&& classAttribute.startsWith(headerClassStart)) {
					isHeader = true;
				} else {
					continue; // 跳过这行.
				}
			}
			
			// 如果标记在td class上，可能要删除不符合条件的tr行.
			boolean deleteTr = false;
			List<Element> tdList = trElement.getAllElements(HTMLElementName.TD);
			ArrayList<String> arrayList = new ArrayList<String>();
			ContainerRecord record = new ContainerRecord();
			record.setHeader(isHeader);

			for (int j = 0; j < tdList.size(); j++) {
				resultElement = tdList.get(j);
				
				// 有些header设置是在td标签上.
				if (j == 0 && usetdclass != 0) {
					classAttribute = resultElement.getAttributeValue("class");
					if (classAttribute != null && recordClassStart != null
							&& classAttribute.startsWith(recordClassStart)) {
						isHeader = false;
					} else if (classAttribute != null && headerClassStart != null
							&& classAttribute.startsWith(headerClassStart)) {
						isHeader = true;
					} else {
						deleteTr = true;
						break;
					}
					record.setHeader(isHeader);
				}
				String conString = CharacterReference
						.decodeCollapseWhiteSpace(resultElement.getContent()
								.getTextExtractor().toString());
				// logger.debug("i={},j={},{}", i, j, conString);
				arrayList.add(conString);
			}
			if (!deleteTr) {
				record.setTime(arrayList.get(timeIndex));
				record.setEvent(arrayList.get(eventIndex));
				record.setLocation(arrayList.get(locationIndex));
				record.setVessel(arrayList.get(vesselIndex));
				record.setVoyage(arrayList.get(voyageIndex));
				
				aLists.add(arrayList);
				records.add(record);
			}
		}
		String tableString = Record2Table(records, code);
		container.setTableString(tableString);

		String jsonString = Record2Json(records, code);
		container.setJsonString(jsonString);

		return container;

	}
	
}
