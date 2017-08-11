package com.timeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeFormat {

	public static void main(String[] args) {
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date();
		
		String id = TimeZone.getDefault().getID();
		System.out.println(id);
		
		System.out.println(format.format(curDate));

		format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		
		System.out.println(format.format(curDate));
	}
}
