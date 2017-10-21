package com.inter.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 
 * @author user
 * Get China's local time
 */
public class GetTimeUtil {

	private GetTimeUtil() {
	}

	public static String getTime() {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date();

		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return df.format(curDate);
	}
	
	public static String getSpecifiedTimeUnit(String pattern) {

		DateFormat df = new SimpleDateFormat(pattern);
		Date curDate = new Date();

		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return df.format(curDate);
	}
}
