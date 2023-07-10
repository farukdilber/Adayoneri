package com.avivasa.rpa.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.retry.annotation.Retryable;
import org.testng.SkipException;

import com.avivasa.rpa.base.AbstractTest.AutomationVariables;
import com.avivasa.rpa.data.ExcelOperations;
import com.avivasa.rpa.data.GetData;
import com.avivasa.rpa.data.GetData.Url;
import com.avivasa.rpa.util.Configuration;
import com.avivasa.rpa.util.DataFinder;
import com.avivasa.rpa.utiliy.EmailSend;
import com.avivasa.rpa.utiliy.ExtentTestManager;
import com.avivasa.rpa.utiliy.GetScreenShot;
import com.avivasa.rpa.utiliy.log;
import com.relevantcodes.extentreports.LogStatus;

public class AbstractPageHtmlUnit 
{
	protected WebDriver driver;
	protected Configuration config;
	protected WebDriverWait wait, waitZero, waitLoader;
	protected static final Logger logger = LogManager.getLogger( AbstractTest.class.getName( ) );

	public AbstractPageHtmlUnit( ) throws MalformedURLException
	{
		this.driver = setUpBrowser( );
		this.config = Configuration.getInstance( );
		this.wait = new WebDriverWait( driver, GetData.DEFAULT_WAIT );
		this.waitZero = new WebDriverWait( driver, 0 );
		this.waitLoader = new WebDriverWait( driver, GetData.DEFAULT_WAIT_LOADERBOX );// 90
		DOMConfigurator.configure( "src/test/resources/Log4j.xml" );
	}
	@SuppressWarnings("deprecation")
	public WebDriver setUpBrowser1( ) throws MalformedURLException
	{
		HashMap<String, Object> edgePrefs = new HashMap<String, Object>( );
		edgePrefs.put("profile.default_content_settings.popups", 0);
//		chromePrefs.put("download.default_directory", GetData.DOWNLOAD_FILE_PATH);
		edgePrefs.put("download.default_directory", GetData.DOWNLOAD_FILE_PATH);
		System.setProperty("webdriver.edge.driver", "C:\\RPA\\msedgedriver.exe");
		EdgeOptions options = new EdgeOptions();		
		options.setCapability("prefs", edgePrefs);
		DesiredCapabilities capability = DesiredCapabilities.edge();
//		DesiredCapabilities capability = DesiredCapabilities.htmlUnit();
		capability.setBrowserName("MicrosoftEdge");
		capability.setJavascriptEnabled(true);
		switch (AutomationVariables.MACHINE) {
		case "local":
			driver = (new EdgeDriver(options));
			break;
		case "remote":
//			driver = new FirefoxDriver();
//			driver = new InternetExplorerDriver();
//            driver = new RemoteWebDriver(capability);
//			driver = new ChromeDriver( capability );
//			driver = new HtmlUnitDriver(capability);
			break;
		}
		

		
		driver.manage( ).timeouts( ).implicitlyWait( GetData.DEFAULT_WAIT, TimeUnit.SECONDS );
		driver.manage( ).window( ).maximize( );
		log.info( "Setup started" );
		log.info( "ChromeDriver / HtmlUnitDriver has been set up." );
		return driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public void navigateTo(String url) {

		try {
			driver.get(url);
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
//			Wait(500);
			log.info("Web application launched");
			// LogPASS("Web application launched");
		} catch (Exception e) {
			log.error("Error while getting app url : " + e);
			// LogFAIL("Error while getting app url : " + e);

			throw new RuntimeException(e);
		}
	}
	protected void pageDownElement (By by) {
		WebElement element = driver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	
	public AbstractPageHtmlUnit openUrl(String url) {

		try {

			navigateTo(url);
			
             log.info("openUrl transaction passed");

		} catch (Exception e) {
			log.error("Hata!! Bidünya url baslatilamadi: " + e);
			EmailSend.mailIslemleriHatasi("fdilberx@gmail.com");
			throw new SkipException("Hata!! BiKolay url baslatilamadi.");
		}

		return this;
	}
	
	
	@SuppressWarnings("deprecation")
	public WebDriver setUpBrowser( ) throws MalformedURLException
	{
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>( );
		chromePrefs.put( "profile.default_content_settings.popups", 0 );
//		chromePrefs.put("download.default_directory", GetData.DOWNLOAD_FILE_PATH);

		ChromeOptions options = new ChromeOptions( );
		System.setProperty("webdriver.chrome.driver", "C:\\RPA\\chromedriver.exe");
		options.setExperimentalOption( "prefs", chromePrefs );
		options.setExperimentalOption( "useAutomationExtension", false );
//		options.addArguments("window-size=1024,768");
		DesiredCapabilities capability = DesiredCapabilities.chrome( );
//		DesiredCapabilities capability = DesiredCapabilities.htmlUnit();

		capability.setCapability( ChromeOptions.CAPABILITY, options );
//		capability.setBrowserName("chrome");

//      capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setJavascriptEnabled( true );
//		RemoteWebDriver driver;
		switch( AutomationVariables.MACHINE )
		{
		case "local":
//			driver = new FirefoxDriver();
//			driver = new InternetExplorerDriver();
//            driver = new RemoteWebDriver(capability);
			driver = new ChromeDriver( capability );
//			driver = new HtmlUnitDriver(capability);
			break;
		case "remote":
//			driver = new RemoteWebDriver(new URL(GetData.REMOTE_MACHINE_IP), capability);
			break;
		}
		driver.manage( ).timeouts( ).implicitlyWait( GetData.DEFAULT_WAIT, TimeUnit.SECONDS );
		driver.manage( ).window( ).maximize( );
		log.info( "Setup started" );
		log.info( "ChromeDriver / HtmlUnitDriver has been set up." );
		return driver;
	}

	/**
	 * navigate to url
	 */
	public void navigateTo( Url url )
	{
		try
		{
			driver.get( DataFinder.getUrl( url ) );
			driver.manage( ).timeouts( ).pageLoadTimeout( 40, TimeUnit.SECONDS );
//			Wait(500);
			log.info( "Web application launched" );
//			LogPASS("Web application launched");
		}catch( Exception e )
		{
			log.error( "Error while getting app url : " + e );
//			LogFAIL("Error while getting app url : " + e);

			throw new RuntimeException( e );
		}
	}

	public void navigateTo2( String url )
	{
		try
		{
			driver.get( url );
			driver.manage( ).timeouts( ).pageLoadTimeout( 40, TimeUnit.SECONDS );
//			Wait(500);
			log.info( "Web application launched" );
			// LogPASS("Web application launched");
		}catch( Exception e )
		{
			log.error( "Error while getting app url : " + e );
			// LogFAIL("Error while getting app url : " + e);

			throw new RuntimeException( e );
		}
	}

	/**
	 * Use this method to find element by cssSelector
	 * 
	 * @param by
	 * @param index
	 * @return A WebElement, or an empty if nothing matches
	 * @throws InterruptedException
	 */
	@Retryable(maxAttempts = 1)
	protected WebElement findElement( By by, int... index ) throws InterruptedException
	{
		// driver.manage().timeouts().implicitlyWait(GetData.DEFAULT_WAIT,
		// TimeUnit.SECONDS);

		WebElement element = null;
		untilElementAppear( by );
		try
		{
			// ((JavascriptExecutor) driver).executeScript("window.focus();");
			if( index.length == 0 )
				element = driver.findElement( by );
			else
				element = driver.findElements( by ).get( index[0] );

			((JavascriptExecutor) driver).executeScript( "arguments[0].scrollIntoView(false);arguments[0].focus();",
					element );
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].focus();", element);
			// wait.until(ExpectedConditions.visibilityOf(element));
			wait.until( ExpectedConditions.elementToBeClickable( element ) );
		}catch( Exception e )
		{
			log.error( "Error while clicking webelement : " + e );
			LogFAIL( "Error while clicking webelement : " + e );

			throw new RuntimeException( e );
		}
		return element;
	}

	public void waitForElement( By by, int... index ) throws InterruptedException
	{
		waitLoaderBox( );
		findElement( by, index );
	}

	public void waitLoaderBox( )
	{
		waitLoaderBox( GetData.DEFAULT_WAIT_LOADERBOX );// 90
	}

	public void waitLoaderBox( int time )
	{

		driver.manage( ).timeouts( ).implicitlyWait( 0, TimeUnit.SECONDS );
		if( driver.findElements( By.xpath( "//div[starts-with(@class,'loader')]" ) ).size( ) != 0 )
		{
			driver.manage( ).timeouts( ).implicitlyWait( time, TimeUnit.SECONDS );
			driver.findElement( By.xpath( "//div[@class='loader' and @style='display: none;']" ) );
			driver.findElement( By.xpath( "//div[@class='loader-box' and @style='display: none;']" ) );
		}
		driver.manage( ).timeouts( ).implicitlyWait( GetData.DEFAULT_WAIT, TimeUnit.SECONDS );
	}

	/**
	 * Use this method to find elements by cssSelector
	 * 
	 * @param by
	 * @return A list of WebElements, or an empty if nothing matches
	 */
	protected List<WebElement> findElements( By by )
	{
		List<WebElement> webElements = null;
		untilElementAppear( by );
		try
		{
			webElements = driver.findElements( by );
		}catch( Exception e )
		{
			log.error( "Error while listing webelements by css selector : " + e );
			LogFAIL( "Error while listing webelements by css selector : " + e );
			log.info( "Error while listing webelements by css selector : " + e );

			throw new RuntimeException( e );
		}
		return webElements;
	}

	/**
	 * Use this method click to element
	 * 
	 * @param by
	 * @param index
	 * @throws InterruptedException
	 */
	protected void click( By by, int... index ) throws InterruptedException
	{

		WebElement element;
		try
		{
			element = findElement( by, index );
			element.click( );
		//	element.sendKeys(arg0);
			// LogPASS("Click Button : " + getTextOfElement(element));

		}catch( Exception e )
		{
			log.error( "Error while clicking webelement : " + e );
			LogFAIL( "Error while clicking webelement : " + e );
			log.info( "Error while clicking webelement : " + e );

			throw new RuntimeException( e );
		}
	}
	
	
	
	protected boolean retryingFindClick( By by ) 
	{
		boolean result = false;
		int attempts = 0;
		while( attempts < 2 )
		{
			try
			{
				driver.findElement( by ).click( );
				result = true;
				break;
			}catch( Exception e )
			{
				e.printStackTrace( );
			}
			attempts++;
		}
		return result;
	}

	/**
	 * Use this method click to element
	 * 
	 * @param by
	 * @param index
	 * @throws InterruptedException
	 */
	protected void click( By by, boolean clickable ) throws InterruptedException
	{
		try
		{
			if( !clickable )
				click( by );
			else
			{
				wait.until( ExpectedConditions.visibilityOfElementLocated( by ) );
				WebElement elem = wait.until( ExpectedConditions.visibilityOf( driver.findElement( by ) ) );
				elem.click( );
				// LogPASS("Click Button : " + getTextOfElement(elem));
			}
		}catch( Exception e )
		{
			log.error( "Error while clicking webelement : " + e );
			LogFAIL( "Error while clicking webelement : " + e );
			log.info( "Error while clicking webelement : " + e );

			throw new RuntimeException( e );
		}
	}

	/**
	 * Use this method to simulate typing into an element if it is enable. Send
	 * enter if pressEnter is true, do nothing otherwise. Note : Before sending
	 * operation, element is cleared.
	 * 
	 * @param by
	 * @param text
	 * @param pressEnter
	 * @throws InterruptedException
	 */
	protected void sendKeys( By by, String text, boolean pressEnter, int... index ) throws InterruptedException
	{

		WebElement element = null;
		try
		{
			element = findElement( by, index );
			if( element.isEnabled( ) )
			{
				element.clear( );
				element.sendKeys( text  );
				if( pressEnter )
				{
					waitLoaderBox( );
					element.sendKeys( Keys.ENTER );
				}
			}
			// LogPASS("Value : " + text + " - SendKeys : " + getTextOfElement(element));
			// LogPASS("SendKeys Value : " + text);
		}catch( Exception e )
		{
			log.error( "Error while filling field : " + e );
			LogFAIL( "Error while filling field : " + e );

			throw new RuntimeException( e );
		}
	}

	
	protected void sendKeys1( By by, String text, boolean pressEnter, int... index ) throws InterruptedException
	{

		WebElement element = null;
		try
		{
			element = findElement( by, index );
			if( element.isEnabled( ) )
			{
				element.clear( );
				element.sendKeys( text + "\n" + "Bu basvuru TECHBOT tarafından optımıze edılmıstır");
				if( pressEnter )
				{
					waitLoaderBox( );
					element.sendKeys( Keys.ENTER );
				}
			}
			// LogPASS("Value : " + text + " - SendKeys : " + getTextOfElement(element));
			// LogPASS("SendKeys Value : " + text);
		}catch( Exception e )
		{
			log.error( "Error while filling field : " + e );
			LogFAIL( "Error while filling field : " + e );

			throw new RuntimeException( e );
		}
	}

	/**
	 * Use this method to simulate typing into an element if it is enable. Send
	 * enter if pressEnter is true, do nothing otherwise. Note : Before sending
	 * operation, element is cleared.
	 * 
	 * @param by
	 * @param text
	 * @param pressEnter
	 * @throws InterruptedException
	 */
	protected void sendKeys( By by, Keys key, int... index ) throws InterruptedException
	{
		WebElement element = null;
		try
		{
			element = findElement( by, index );
			if( element.isEnabled( ) )
			{
				element.sendKeys( key );
			}
			// LogPASS("Value : " + key.toString() + " - SendKeys : " +
			// getTextOfElement(element));
		}catch( Exception e )
		{
			log.error( "Error while filling field : " + e );
			LogFAIL( "Error while filling field : " + e );

			throw new RuntimeException( e );
		}
	}

	/**
	 * Use this method to simulate typing into an element if it is enable. Note :
	 * Before sending operation, element is cleared.
	 * 
	 * @param by
	 * @param text
	 * @throws InterruptedException
	 */
	protected void sendKeys( By by, String text, int... index ) throws InterruptedException
	{
		sendKeys( by, text, false, index );
	}

	protected void selectCombobox( By by, String value ) throws InterruptedException
	{
		WebElement element = findElement( by );
		try
		{
			if( element.isEnabled( ) )
			{
				Select selectBox = new Select( driver.findElement( by ) );
				selectBox.selectByValue( value );
			}
			LogPASS( "Value : " + value + " - SelectComboBox : " + getTextOfElement( element ) );
		}catch( Exception e )
		{
			log.error( "Error while filling field : " + e );
			LogFAIL( "Error while filling field : " + e );

			throw new RuntimeException( e );
		}
	}

	/**
	 * Label in yanindaki ComboBox a istedigin degeri atayan method. indexli yapi
	 * çok karmasik oldugu için yapildi.
	 * 
	 * @param label ComboBoxin yanindaki label
	 * @param value ComboBoxta selçilmesi gereken deger
	 * @param index Eger ayni sayfada birden fazla ayni label varsa ve ilk olani
	 *              istenmiyorsa istenen labelin indexi yazilmali.
	 */
	protected void reactSelectDropDownSearchable( String label, String value, int... index ) throws InterruptedException
	{
		try
		{
			int i = 1;
			if( index.length != 0 )
				i = index[0];
			WebElement e = findElement(
					By.xpath( "(//*[text()='" + label + "']/parent::div//div[@class='Select-control'])[" + i + "]" ) );
			e.click( );
			e.findElement( By.xpath( "//input" ) ).sendKeys( value );
			List<WebElement> options = findElements( By.className( "Select-option" ) );
			for( WebElement option : options )
			{
				option.click( );
				break;
			}
			driver.manage( ).timeouts( ).implicitlyWait( 1, TimeUnit.SECONDS );
			if( e.findElements( By.xpath( "//div[@aria-expanded='true']" ) ).size( ) != 0 )
				new Actions( driver ).moveToElement( e ).click( ).perform( );
			driver.manage( ).timeouts( ).implicitlyWait( GetData.DEFAULT_WAIT, TimeUnit.SECONDS );
			LogPASS( "Value : " + value + " - SelectComboBox : " + getTextOfElement( e ) );
		}catch( Exception e )
		{
			log.error( "Error while filling field : " + e );
			LogFAIL( "Error while filling field : " + e );

			throw new RuntimeException( e );
		}

	}

	/**
	 * Label in yanindaki ComboBox a istedigin degeri atayan method. indexli yapi
	 * çok karmasik oldugu için yapildi.
	 * 
	 * @param label ComboBoxin yanindaki label
	 * @param value ComboBoxta selçilmesi gereken deger
	 * @param index Eger ayni sayfada birden fazla ayni label varsa ve ilk olani
	 *              istenmiyorsa istenen labelin indexi yazilmali.
	 */
	protected void reactSelectDropDown( String label, String value, int... index ) throws InterruptedException
	{
		try
		{
			int i = 1;
			if( index.length != 0 )
				i = index[0];
			WebElement e = findElement(
					By.xpath( "(//*[text()='" + label + "']/parent::div//div[@class='Select-control'])[" + i + "]" ) );
			e.click( );
			List<WebElement> options = findElements( By.className( "Select-option" ) );
			for( WebElement option : options )
			{
				if( option.getText( ).contains( value ) )
				{
					option.click( );
					// new
					// Actions(driver).moveToElement(option).click().perform();
					break;
				}
			}
			/*
			 * driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); if
			 * (e.findElements(By.xpath("//div[@aria-expanded='true']")).size() != 0) //
			 * e.click(); new Actions(driver).moveToElement(e).click().perform();
			 * driver.manage().timeouts().implicitlyWait(GetData.DEFAULT_WAIT,
			 * TimeUnit.SECONDS);
			 */LogPASS( "Value : " + value + " - SelectComboBox : " + getTextOfElement( e ) );
		}catch( Exception e )
		{
			log.error( "Error while filling field : " + e );
			LogFAIL( "Error while filling field : " + e );

			throw new RuntimeException( e );
		}
	}

	// TODO: EBRUYA: ROP Lib tasimalisin
	protected void reactSelectDropDownBasvuruNo( String label, String value, int... index ) throws InterruptedException
	{
		try
		{
			int i = 1;
			if( index.length != 0 )
				i = index[0];
			WebElement e = findElement(
					By.xpath( "//label[text()='Basvuru Numarasi']//parent::div/following-sibling::div[1]" ) );
			e.click( );
			List<WebElement> options = findElements( By.className( "Select-option" ) );
			for( WebElement option : options )
			{
				if( option.getText( ).contains( value ) )
				{
					option.click( );

					break;
				}
			}

			LogPASS( "Value : " + value + " - SelectComboBox : " + getTextOfElement( e ) );
		}catch( Exception e )
		{
			log.error( "Error while filling field : " + e );
			LogFAIL( "Error while filling field : " + e );

			throw new RuntimeException( e );
		}
	}

	// inspect i aç crtl + F yap
	// alana //div[@class='Select-control'] yapistir
	// kaçinci index ise onu index e 1 eksilterek yaz
	protected void reactSelectDropDown( int index, String value ) throws InterruptedException
	{
		try
		{
			WebElement e = findElement( By.className( "Select-control" ), index );
			e.click( );
			List<WebElement> options = findElements( By.className( "Select-option" ) );
			for( WebElement option : options )
			{
				if( option.getText( ).contains( value ) )
				{
					option.click( );
					break;
				}
			}
			driver.manage( ).timeouts( ).implicitlyWait( 1, TimeUnit.SECONDS );
			if( e.findElements( By.xpath( "//div[@aria-expanded='true']" ) ).size( ) != 0 )
				// e.click();
				new Actions( driver ).moveToElement( e ).click( ).perform( );
			driver.manage( ).timeouts( ).implicitlyWait( GetData.DEFAULT_WAIT, TimeUnit.SECONDS );
			LogPASS( "Value : " + value + " - SelectComboBox : " + getTextOfElement( e ) );
		}catch( Exception e )
		{
			log.error( "Error while filling field : " + e );
			LogFAIL( "Error while filling field : " + e );

			throw new RuntimeException( e );
		}
	}

	protected void moveToElement( By by )
	{
		try
		{
			Actions action = new Actions( driver );
			WebElement we = driver.findElement( by );
			action.moveToElement( we ).build( ).perform( );
		}catch( Exception e )
		{
			log.error( "Error while filling field : " + e );
			LogFAIL( "Error while filling field : " + e );
			throw new RuntimeException( e );
		}
	}

	/**
	 * Get the visible (i.e. not hidden by CSS) innerText of this element.
	 * 
	 * @param by
	 * @param index
	 * @return The innerText of this element.
	 * @throws InterruptedException
	 */
	protected String getTextOfElement( By by, int... index ) throws InterruptedException
	{

		String text = null;
		untilElementAppear( by );

		try
		{
			if( index.length == 0 )
				text = driver.findElement( by ).getText( );
			else
				text = driver.findElements( by ).get( index[0] ).getText( );

		}catch( Exception e )
		{
			log.error( "Error while getting text of element : " + e );
			LogFAIL( "Error while getting text of element : " + e );

			throw new RuntimeException( e );
		}
		return text;
	}

	@SuppressWarnings("finally")
	protected String getTextOfElement( WebElement elem )
	{
		String text = null;
		try
		{
			text = elem.getText( );
		}finally
		{
			return text;
		}
	}

	protected String getValueOfElement( By by, int... index )
	{
		String value = null;

		try
		{
			if( index.length == 0 )
			{
				value = driver.findElement( by ).getAttribute( "value" );
			}else
			{
				value = driver.findElements( by ).get( index[0] ).getAttribute( "value" );
			}
		}catch( Exception e )
		{
			log.error( "Error while getting text of element : " + e );
			LogFAIL( "Error while getting text of element : " + e );

			throw new RuntimeException( e );
		}
		return value;
	}

	/**
	 * Wait until element appears
	 * 
	 * @param by
	 * @param index
	 */
	protected void untilElementAppear( By by )
	{
		try
		{
			// waitLoaderBox();// , 40
			// Thread.sleep(1000);
			Wait( 100 );
			// driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			wait.until( ExpectedConditions.presenceOfAllElementsLocatedBy( by ) );
			// wait.until(ExpectedConditions.presenceOfElementLocated(by));
		}catch( Exception e )
		{
			log.error( "Error while waiting until element appears : " + e );
			LogFAIL( "Error while waiting until element appears : " + e );
			throw new RuntimeException( e );
		}
	}

	/**
	 * Wait until element disappears
	 * 
	 * @param by
	 */
	protected void untilElementDisappear( By by )
	{
		try
		{
			wait.until( ExpectedConditions.invisibilityOfElementLocated( by ) );
		}catch( Exception e )
		{
			log.error( "Error while waiting until element disappears : " + e );
			LogFAIL( "Error while waiting until element disappears : " + e );
			throw new RuntimeException( e );
		}
	}

	/**
	 * Return true if element exist, false otherwise.
	 * 
	 * @param by
	 * @param index
	 * @return True if element exists, false otherwise.
	 */
	protected boolean isElementExist( By by )
	{
		return isElementExist( by, 10 );
	}

	protected boolean isElementExist( By by, int timeSeconds )
	{
		driver.manage( ).timeouts( ).implicitlyWait( timeSeconds, TimeUnit.SECONDS );
		boolean isExist = driver.findElements( by ).size( ) > 0;
		driver.manage( ).timeouts( ).implicitlyWait( GetData.DEFAULT_WAIT, TimeUnit.SECONDS );
		return isExist;
	}

	public String getProperty( By by, String expectedPropertyName, int... index )
	{
		WebElement elem;

		if( index.length == 0 )
			elem = driver.findElement( by );
		else
			elem = driver.findElements( by ).get( index[0] );

		return elem.getAttribute( expectedPropertyName );
	}

	public String randomNumber( int length )
	{
		Random r = new Random( );
		List<Integer> digits = new ArrayList<Integer>( );
		String number = "";

		for( int i = 0; i < length - 1; i++ )
		{
			digits.add( i );
		}

		for( int i = length - 1; i > 0; i-- )
		{
			int randomDigit = r.nextInt( i );
			number += digits.get( randomDigit );
			digits.remove( randomDigit );
		}
		number = "1" + number;

		return number;
	}

	public boolean isFileDownloaded( String downloadPath, String fileName ) throws InterruptedException
	{
		boolean flag = false;
		File dir = new File( downloadPath );
		for( int tryCount = 30; !flag && tryCount > 0; tryCount-- )
		{
			Wait( 1000 );
			log.info( "İndirilen dosya kontrol ediliyor. Kontrol : " + tryCount );
			File[] dir_contents = dir.listFiles( );
			for( int i = 0; i < dir_contents.length; i++ )
			{
				if( dir_contents[i].getName( ).equals( fileName ) )
					return flag = true;
			}
		}
		return flag;
	}

	public void ClearText( By by, int... index ) throws InterruptedException
	{

		WebElement element = null;

		element = findElement( by, index );
		element.sendKeys( Keys.CONTROL + "a" );
		element.sendKeys( Keys.DELETE );
	}

	public boolean deleteFile( String pathToFile ) throws InterruptedException
	{
		Wait( 2000 );
		boolean flag = false;
		try
		{
			File file = new File( pathToFile );

			if( !file.exists( ) )
				return false;

			flag = file.delete( );

			if( flag )
			{
				Wait( 1500 );
				log.info( file.getName( ) + " dosyasi silindi." );
				LogPASS( file.getName( ) + " dosyasi silindi." );
			}else
			{
				log.error( "Dosya silme islemi basarisiz." );
				LogFAIL( "Dosya silme islemi basarisiz. " );
			}
		}catch( Exception e )
		{
			log.error( "Error : Dosya silme islemi basarisiz." );
			LogFAIL( "Error : Dosya silme islemi basarisiz. " );
		}
		return flag;
	}

	protected boolean isEnable( By by, int... index ) throws InterruptedException
	{
		WebElement element;
		// untilElementAppear(by);
		try
		{
			if( index.length == 0 )
				element = driver.findElement( by );
			else
				element = driver.findElements( by ).get( index[0] );

			if( element.isEnabled( ) )
			{
				return true;
			}else
			{
				return false;
			}
		}catch( Exception e )
		{
			log.error( "Error while clicking webelement : " + e );
			LogFAIL( "Error while clicking webelement : " + e );
			log.info( "Error while clicking webelement : " + e );
			throw new RuntimeException( e );
		}
	}

	// Tablo PageObject ile bulunur.
	// columnInput ile gönderilen deger, columnIndex ile belirtilen sütunda
	// aratilir. columnInput olan satirin elementini döndürür.
	protected WebElement findElementOnTableByColumnInput( int columnIndex, String columnInput )
			throws InterruptedException
	{
		return findElementOnTableByColumnInput( columnIndex, columnInput, columnIndex );
	}

	/**
	 * Tablodan aranan datanin elementi döndürür.
	 * 
	 * @param columnName  Aranan kolon adi
	 * @param columnInput Aranan kolon degeri
	 * @return WebElement
	 */
	protected WebElement findElementOnTableByColumnInput( String columnName, String columnInput )
			throws InterruptedException
	{
		return findElementOnTableByColumnInput( columnName, columnInput, columnName );
	}

	// Tablo PageObject ile bulunur.
	// columnInput ile gönderilen deger, columnIndex ile belirtilen sütunda
	// aratilir. columnInput olan satirin elementini döndürür.
	protected WebElement findElementOnTableByColumnInput( int columnIndex, String columnInput, int getcolumnIndex )
			throws InterruptedException
	{
		return findElementOnTableByColumnInput( columnIndex, columnInput, getcolumnIndex, By.tagName( "td" ) );
	}

	/**
	 * Tablodan aranan datanin satirindaki farkli kolondaki elementi döndürür.
	 * 
	 * @param columnName    Aranan kolon adi
	 * @param columnInput   Aranan kolon degeri
	 * @param getcolumnName Beklenen kolon adi
	 * @return WebElement
	 */
	protected WebElement findElementOnTableByColumnInput( String columnName, String columnInput, String getcolumnName )
			throws InterruptedException
	{
		return findElementOnTableByColumnInput( columnName, columnInput, getcolumnName, By.tagName( "td" ) );
	}

	// Tablo PageObject ile bulunur.
	// columnInput ile gönderilen deger, columnIndex ile belirtilen sütunda
	// aratilir. columnInput olan satirin elementini döndürür.
	// getColumnByTagName -> Or: By.tagName("td")
	protected WebElement findElementOnTableByColumnInput( int columnIndex, String columnInput, int getcolumnIndex,
			By getColumnByTagName ) throws InterruptedException
	{
		return findElementOnTableByColumnInput( columnIndex, columnInput, true, getcolumnIndex, getColumnByTagName );// By.tagName("td"));

	}

	/**
	 * Tablodan aranan datanin satirindaki farkli tag e sahip elementi döndürür.
	 * 
	 * @param columnName         Aranan kolon adi
	 * @param columnInput        Aranan kolon degeri
	 * @param getcolumnName      Beklenen kolon adi
	 * @param getColumnByTagName Beklenen kolonun tag adi
	 * @return WebElement
	 */
	protected WebElement findElementOnTableByColumnInput( String columnName, String columnInput, String getcolumnName,
			By getColumnByTagName ) throws InterruptedException
	{
		return findElementOnTableByColumnInput( columnName, columnInput, true, getcolumnName, getColumnByTagName );// By.tagName("td"));

	}

	protected WebElement findElementOnTableByColumnInput( int columnIndex, String columnInput, boolean coumnInputNot,
			int getcolumnIndex, By getColumnByTagName ) throws InterruptedException
	{

		WebElement table = findElement( By.xpath( "//table[last()]" ) ).findElement( By.tagName( "tbody" ) );

		List<WebElement> allRows = table.findElements( By.tagName( "tr" ) );
		WebElement elem = null, elem2 = null;
		for( WebElement row : allRows )
		{
			elem2 = row.findElements( By.tagName( "td" ) ).get( columnIndex - 1 );
			elem = row.findElements( getColumnByTagName ).get( getcolumnIndex - 1 );

			if( coumnInputNot )
			{
				if( elem2.getText( ).equals( columnInput ) )
				{
					return elem;
				}
			}else
			{
				if( !elem2.getText( ).equals( columnInput ) )
				{
					return elem;
				}
			}
		}
		// click(SozlesmeSorgulamaPage.lastPage.getBy());
		elem = findElementOnTableByColumnInput( columnIndex, columnInput, coumnInputNot, getcolumnIndex,
				getColumnByTagName );
		if( elem != null )
			return elem;
		return null;
	}

	protected WebElement findElementOnTableByColumnInput( String columnName, String columnInput, boolean coumnInputNot,
			String getcolumnName, By getColumnByTagName ) throws InterruptedException
	{
		int columnIndex = 0, getcolumnIndex = 0;
		WebElement table = findElement( By.xpath( "//table[last()]" ) );
		WebElement thead = table.findElement( By.tagName( "thead" ) );
		WebElement tbody = table.findElement( By.tagName( "tbody" ) );
		List<WebElement> headRows = thead.findElements( By.tagName( "th" ) );
		for( WebElement row : headRows )
		{
			if( row.getText( ).equals( "" ) )
				continue;
			if( row.getText( ).equals( columnName ) )
				break;
			columnIndex++;
		}
		for( WebElement row : headRows )
		{
			if( row.getText( ).equals( "" ) && !getcolumnName.equals( "" ) )
				continue;
			if( row.getText( ).equals( getcolumnName ) )
				break;
			getcolumnIndex++;
		}

		List<WebElement> allRows = tbody.findElements( By.tagName( "tr" ) );
		WebElement elem = null, elem2 = null;
		for( WebElement row : allRows )
		{
			elem2 = row.findElements( By.tagName( "td" ) ).get( columnIndex );
			elem = row.findElements( getColumnByTagName ).get( getcolumnIndex );

			if( coumnInputNot )
			{
				if( elem2.getText( ).equals( columnInput ) )
				{
					return elem;
				}
			}else
			{
				if( !elem2.getText( ).equals( columnInput ) )
				{
					return elem;
				}
			}
		}
		// click(SozlesmeSorgulamaPage.nextPage.getBy());
		elem = findElementOnTableByColumnInput( columnName, columnInput, coumnInputNot, getcolumnName,
				getColumnByTagName );
		if( elem != null )
			return elem;
		return null;
	}

	public void authenticateUsing( String userID, String sifre )
	{
		Alert alert = wait.until( ExpectedConditions.alertIsPresent( ) );
		// alert.authenticateUsing(new UserAndPassword(userID, sifre));
	}

	public void closeBrowser( ) throws InterruptedException
	{
		driver.close( );
		driver.quit( );
	}

	public String getScreenShot( ) throws InterruptedException, IOException
	{
		return GetScreenShot.capture( driver );
	}

	public void ThreadStart( String procces )
	{
		AbstractTest.threadController.threadStart( procces );
	}

	public void ThreadStop( String procces )
	{
		AbstractTest.threadController.threadStop( procces );
	}

	public void Wait( int millisecond ) throws InterruptedException
	{
		Thread.sleep( millisecond );
	}

	public void LogPASS( String massege )
	{
		if( !AutomationVariables.ScreenShot )
		{
			ExtentTestManager.getTest( ).log( LogStatus.PASS, massege );
			log.info( massege );
		}else
		{
			String screenShotPath;
			try
			{
				screenShotPath = GetScreenShot.capture( driver );
				ExtentTestManager.getTest( ).log( LogStatus.PASS,
						massege + ExtentTestManager.getTest( ).addBase64ScreenShot( screenShotPath ) );
			}catch( IOException e )
			{
			}
		}
	}

	public void LogFATAL( String massege )
	{
		ExtentTestManager.getTest( ).log( LogStatus.FATAL, massege );
	}

	public void LogFAIL( String massege )
	{
		ExtentTestManager.getTest( ).log( LogStatus.FAIL, massege );
	}

	public void LogERROR( String massege )
	{
		ExtentTestManager.getTest( ).log( LogStatus.ERROR, massege );
	}

	public void LogINFO( String massege )
	{
		ExtentTestManager.getTest( ).log( LogStatus.INFO, massege );
	}

	public void LogWARNING( String massege )
	{
		String screenShotPath;
		try
		{
			screenShotPath = GetScreenShot.capture( driver );
			ExtentTestManager.getTest( ).log( LogStatus.WARNING, massege + "\nSnapshot below: "
					+ ExtentTestManager.getTest( ).addBase64ScreenShot( screenShotPath ) );
		}catch( IOException e )
		{
		}
	}
}


