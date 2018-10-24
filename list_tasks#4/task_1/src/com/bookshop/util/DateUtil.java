package com.bookshop.util;

import java.util.Date;

public class DateUtil {
	
	@SuppressWarnings("deprecation")
	public static int monthsBetween(Date min, Date max) { 
		Integer months = (max.getYear() - min.getYear()) * 12 + (max.getMonth() - min.getMonth());
		return months;
	}
}
