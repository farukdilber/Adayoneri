package com.avivasa.rpa.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class StringUtil
{
	 public static String getDate( )
	 {
		 Date date = new Date();
		 SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		 return ft.format(date);
	 }
	 
	 
	 public static String getTime( ) 
	 {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("HH-mm-ss");
		return ft.format(date);
	 }
	 
	public static String dateYearAndMonthToString( )
	{
		String str = "";

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		String year = String.valueOf(localDate.getYear() );  
		String month = String.format("%02d", localDate.getMonthValue() - 1 ); // minus one month !

		str = year + "_" + month;

		return str;

	}
	
	public static String getTimeStr( )
	{
		DateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );

		Calendar cal = Calendar.getInstance( );
		return sdf.format( cal.getTime( ) );
	}
	
	public static String getTimeAsString( )
	{
		DateFormat sdf = new SimpleDateFormat( "yyyyMMdd" );

		Calendar cal = Calendar.getInstance( );
		return sdf.format( cal.getTime( ) );
	}
}
