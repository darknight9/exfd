package com.exfd.util;

import java.util.ArrayList;
import java.util.Date;

public class MarkerUitls {
	
	// 根据beginDate和endDate给出全部的时间标点值.
	// count的值代表中间需要插入的点的个数，count >= 0.
	static public ArrayList<Date> getMidTime(Date beginDate, Date endDate, int count) {
		ArrayList<Date> arDates = new ArrayList<Date>();
		
		arDates.add(beginDate);
		
		long last = endDate.getTime() - beginDate.getTime();
		long interval = last / (count + 1);
		
		for (int i = 0; i < count; i++) {
			Date midDate = new Date();
			midDate.setTime(beginDate.getTime() + interval * (i+1));
			arDates.add(midDate);
		}
		arDates.add(endDate);
		return arDates;
	}
	
	static public void roundHour(ArrayList<Date> arDates) {
		
		// 首尾不调整
		for (int i = 1; i < arDates.size()-1; i++) {
			Date date = arDates.get(i);
			date.setMinutes(0);
			date.setSeconds(0);
		}
	}
	
	static public void getBound(ArrayList<Date> arDates, ArrayList<Date> beginDates, ArrayList<Date> endDates) {

		for (int i = 0; i < arDates.size(); i++) {
			Date date = arDates.get(i);
			Date begin = new Date();
			Date end = new Date();
			if (i == 0) {
				begin.setTime(date.getTime() - 1*60*1000);
				end.setTime(begin.getTime() + 5*60*1000);
			} else if (i != arDates.size() - 1){
				begin.setTime(date.getTime());
				end.setTime(begin.getTime() + 5*60*1000);
			} else {
				begin.setTime(date.getTime() - 1*60*1000);
				end.setTime(begin.getTime() + 5*60*1000);
			}
			beginDates.add(begin);
			endDates.add(end);
		}
	}

}
