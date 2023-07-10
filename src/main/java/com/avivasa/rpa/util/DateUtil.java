package com.avivasa.rpa.util;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil
{
	public static String getDate( )
	{
		Date date = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat( "dd.MM.yyyy" );
		return ft.format( date );
	}

	public static String getReportDate( )
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
		LocalDateTime now = LocalDateTime.now( );
		String sysDate = dtf.format( now );
		return sysDate;
	}
	
	public static String monthNumberToStr( int month )
	{
		DateFormatSymbols dfrmSymbol = new DateFormatSymbols(Locale.US );
		return dfrmSymbol.getMonths()[ month  - 1 ];
	}
	
	public static String strMonth( )
	{
		Date today = new Date( ); 
		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(today);

		int month = cal.get(Calendar.MONTH );
		
		String monthStr = "";
		
		if( month == 1 )
			monthStr = "Ocak";
		else if(month == 2 )
			monthStr = "Şubat";
		else if(month == 3 )
			monthStr = "Mart";
		else if(month == 4 )
			monthStr = "Nisan";
		else if(month == 5 )
			monthStr = "Mayıs";
		else if(month == 6 )
			monthStr = "Haziran";
		else if(month == 7 )
			monthStr = "Temmuz";
		else if(month == 8 )
			monthStr = "Ağustos";
		else if(month == 9 )
			monthStr = "Eylül";
		else if(month == 10 )
			monthStr = "Ekim";
		else if(month == 11 )
			monthStr = "Kasım";
		else if(month == 12 )
			monthStr = "Aralık";
		
		return monthStr;
	}
	
	public static int getYear( )
	{
		Date today = new Date( ); 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(today);

		int year = cal.get(Calendar.YEAR ) ;   // current year 
		
		return year;
	}

}
