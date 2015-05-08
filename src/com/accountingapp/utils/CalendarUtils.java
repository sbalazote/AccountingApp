package com.accountingapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarUtils {

	private static CalendarUtils instance = null;
	
	public static CalendarUtils getInstance() {
		if (instance == null) {
			return new CalendarUtils();
		} else {
			return instance;
		}
	}
	
	protected CalendarUtils() {
		
	}
	
	public String getLastDayOfMonth(Date date) {
		Calendar cal = new GregorianCalendar(date.getYear()+1900, date.getMonth()+1, 0);
		Date newDate = cal.getTime();
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Date : " + sdf.format(newDate));
		return sdf.format(newDate);
	}
}
