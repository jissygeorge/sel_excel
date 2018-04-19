package com.testproject.helper;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Helper {

	private static String IE_DRIVER_PATH;
	private static WebDriver REAL_DRIVER;

	public static void openBrowser()
	{ 
		File file=new File("src//main//resources//com//browserDriver//IEDriverServer.exe");
		IE_DRIVER_PATH=file.getAbsolutePath();
		
		System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
		//System.setProperty("webdriver.chrome.driver", IE_DRIVER_PATH);
		DesiredCapabilities capabilities  = DesiredCapabilities.internetExplorer();
		capabilities .setCapability(
		InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
		    true);
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
		capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,UnexpectedAlertBehaviour.ACCEPT);

		try{
			if(Helper.getWebDriver()!=null){
				//System.out.println("Trying to close any open browser windows: BEGIN");
				Helper.getWebDriver().close();
				Helper.getWebDriver().quit();
				//System.out.println("Trying to close any open browser windows: END");
			}
			}catch (Exception ex){
				System.out.println("Exception while trying to close webdriver before opening");
				ex.printStackTrace();
			}
				
		REAL_DRIVER = new InternetExplorerDriver(capabilities);

		
		
		

		REAL_DRIVER.manage().window().maximize();
		REAL_DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//REAL_DRIVER.get("https://www.gov.uk/get-vehicle-information-from-dvla");
		
		

		
	}
	
	public static void NavgiateToURL(String URL)
	{
		REAL_DRIVER.get(URL);
	}
	
	public static void closeBrowser()
	{
		REAL_DRIVER.close();
	}
	
	public static WebDriver getWebDriver()
	{
		return REAL_DRIVER;
	}
}
