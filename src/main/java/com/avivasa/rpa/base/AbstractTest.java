package com.avivasa.rpa.base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.avivasa.rpa.util.Configuration;
import com.avivasa.rpa.util.ThreadController;
import com.avivasa.rpa.utiliy.UtilityMethods;
import com.avivasa.rpa.utiliy.log;

public class AbstractTest extends AbstractPage
{
	public AbstractTest() throws MalformedURLException {
		super();
		// TODO Auto-generated constructor stub
	}

	// protected RemoteWebDriver driver;
	protected static Configuration config = Configuration.getInstance();
	public static String reportFilePath;
	public static String sysdate = getSysDateCustom();
	public URL REMOTE_URL;
	public static ThreadController threadController = new ThreadController();
	UtilityMethods um = new UtilityMethods();

	@SuppressWarnings("static-access")
	public static class AutomationVariables
	{
		public static final String ENVIRONMENT = config.getEnv();// AbstractTest.ENVIRONMENT;
		public static final String MACHINE = config.getMachine();// AbstractTest.MACHINE;
		public static final String APP = config.getApp();
		public static final String TEXECKEY = config.getTexeckey();
		public static final String TPLANKEY = config.getTplankey();
		public static final String[] EMAILS = config.getEmails();
		public static final boolean ScreenShot = false;
		public static final boolean SEND_MAIL = false;
	}

	@BeforeSuite
	public void StartSuite( ITestContext ctx ) throws MalformedURLException
	{
		log.startTestCase(ctx.getCurrentXmlTest().getSuite().getName());
		// REMOTE_URL = new URL(GetData.REMOTE_MACHINE_IP);
	}

	@BeforeTest
	public void StartTest( ITestContext ctx ) throws IOException
	{
		log.startTestCase(ctx.getName());
	}

	
	private static void killProcess( )
	{
		Runtime rt = Runtime.getRuntime();
		try
		{
			rt.exec("taskkill /f /im " + "chrome.exe");
			rt.exec("taskkill /f /im " + "chromedriver.exe");
			rt.exec("taskkill /f /im " + "conhost.exe");
		}catch (IOException e)
		{
			log.info("Processler Kill Edilememdi!!!");
		}
	}

	public static String getSysDateCustom( )
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH;mm;ss");
		LocalDateTime now = LocalDateTime.now();
		String sysDate = dtf.format(now);

		return sysDate;
	}
}
