package junit.test;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.exfd.util.MarkerUitls;

public class MarkerUtilsTest {

	@Test
	public void testGetMarker(){
		Date beginDate = new Date(11,22,33,44,55,43);
		Date endDate = new Date(11,22,44,44,55,43);
		ArrayList<Date> arDates = MarkerUitls.getMidTime(beginDate, endDate, 7);
		for (Date date : arDates) {
			System.out.println(date);
		}
	}
	
	@Test
	public void testRoundHour(){
		Date beginDate = new Date(11,22,33,44,55,43);
		Date endDate = new Date(11,22,44,44,55,43);
		ArrayList<Date> arDates = MarkerUitls.getMidTime(beginDate, endDate, 7);
		for (Date date : arDates) {
			System.out.println(date);
		}
		System.out.println("-------------------");
		MarkerUitls.roundHour(arDates);
		for (Date date : arDates) {
			System.out.println(date);
		}
	}
	
	@Test
	public void testGetBound(){
		Date beginDate = new Date(11,22,33,44,55,43);
		Date endDate = new Date(11,22,44,44,55,43);
		ArrayList<Date> arDates = MarkerUitls.getMidTime(beginDate, endDate, 7);
		MarkerUitls.roundHour(arDates);
		for (Date date : arDates) {
			System.out.println(date);
		}
		System.out.println("-------------------");
		ArrayList<Date> beginDates = new ArrayList<Date>();
		ArrayList<Date> endDates = new ArrayList<Date>();
		MarkerUitls.getBound(arDates, beginDates, endDates);
		for (int i = 0; i < arDates.size(); i++) {
			System.out.print("No." + i + "    ");
			System.out.print(arDates.get(i));
			System.out.print("-------");
			System.out.print(beginDates.get(i));
			System.out.print("-------");
			System.out.println(endDates.get(i));
		}
		
	}
}
