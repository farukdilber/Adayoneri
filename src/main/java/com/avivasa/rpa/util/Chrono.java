package com.avivasa.rpa.util;

import java.util.Date;

public class Chrono
{

	public static long secondsBetween( Date d1, Date d2 )
	{

		long seconds = (d2.getTime( ) - d1.getTime( )) / 1000;

		return seconds;
	}

}
