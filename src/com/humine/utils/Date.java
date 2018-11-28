package com.humine.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class Date {

	private static SimpleDateFormat format = new SimpleDateFormat("dd-MM");
	
	public static String getDateTodayInString() {
		return format.format(new java.util.Date());
	}
	
	public static java.util.Date getDateToday() {
		try {
			return format.parse(format.format(new java.util.Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean isTheSameDate(java.util.Date date1, java.util.Date date2) {
		try {
			date1 = format.parse(format.format(date1));
			date2 = format.parse(format.format(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(date1.compareTo(date2) == 0)
			return true;
		else
			return false;
	}
}
