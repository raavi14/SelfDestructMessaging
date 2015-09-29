package com.mfp.services.messaging.service.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class MessageUtil {
	
	/**
	 * Method to convert timestamp to DateTime string format(YYYY-MM-dd HH:mm:ss)
	 * @param timestamp Timestamp to convert
	 * @return DateTime String
	 */
	public static String getExpirationDate(Timestamp timestamp) {
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();				
		cal.setTimeInMillis(timestamp.getTime());
		return dateFormat.format(cal.getTime());
	}	
	
	/**
	 * Method to calculate Expiration DateTime and produce DateTime string
	 * @param timeout Expiration Time of message in seconds
	 * @return DateTime String
	 */
	public static String getExpirationDate(int timeout)
	{
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND,timeout);
		return dateFormat.format(cal.getTime());		
	}
	
	/**
	 * Method to calculate Expiration Date
	 * @param timeout Expiration Time of message in seconds
	 * @return Date
	 */
	public static java.util.Date getTimeout(int timeout)
	{		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND,timeout);
		return cal.getTime();		
	}
	
	/**
	 * Generates a 128-bit Unique key
	 * @return unique key
	 */
	public static String generateUniqueKey()
	{
		return String.valueOf(UUID.randomUUID());
	}

}
