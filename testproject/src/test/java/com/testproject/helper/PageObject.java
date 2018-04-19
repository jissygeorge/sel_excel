package com.testprojects.helper;

import static com.testprojects.helper.Helper.getWebDriver;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PageObject {
	//private static final int DRIVER_WAIT_TIME=10;
	
	private static String mainWindowId;
	
	protected WebDriver webdriver;
	
	protected WebDriverWait wait;
	
	protected String MainBrowserHandle;

	
//	public PageObject()
//	{
//		this.webdriver=getWebDriver();
//		//this.wait=new WebDriverWait(webdriver,DRIVER_WAIT_TIME);
//	}
	
	protected void swtichToMainFrame()
	{
		getWebDriver().switchTo().defaultContent();
		getWebDriver().switchTo().frame("main");
	}
	
	protected void swtichToDatasetIFrame()
	{
		swtichToMainFrame();
		//getWebDriver().switchTo().defaultContent();
		getWebDriver().switchTo().frame("datasetIFrame");
	}
	
	protected void swtichToLeftFrame()
	{
		getWebDriver().switchTo().defaultContent();
		getWebDriver().switchTo().frame("leftnav");
	}
	
	protected void swtichToDashboardIFrame()
	{
		 swtichToMainFrame();			
		 getWebDriver().switchTo().frame("dashboardIframe");
	}
	
	protected void swtichToMailFrame()
	{
		getWebDriver().switchTo().defaultContent();
		getWebDriver().switchTo().frame("mail");
	}
	
	protected void swtichToOtherPage() 
	{ 
		wait(2);
		Set<String> winIds=getWebDriver().getWindowHandles();			
		Iterator<String>  iterator=winIds.iterator(); 
		mainWindowId=iterator.next();			
		String dialogboxId=iterator.next();			
		getWebDriver().switchTo().window(dialogboxId);
	}

	protected void swtichToModalDialog() 
	{ 
		wait(2);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();
		jsExecutor.executeScript("window.showModalDialog = window.open");
		swtichToOtherPage();
		
	}
	
	protected void swtichBacktoMainPagefromModalDialog() 
	{ 
		getWebDriver().switchTo().window(mainWindowId);
		
	}
	public void wait(int numberOfSeconds)
	{
		try {
			Thread.sleep(numberOfSeconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected WebElement element(By by)
	{
		return getWebDriver().findElement(by);
	}
	
	

	protected Select dropdown(By by)
	{
		return new Select(element(by));
	}

	protected void pressEnterKey()
	{
		   Robot r;
			try {
				   r = new Robot();
				   r.keyPress(KeyEvent.VK_ENTER);
				   r.keyRelease(KeyEvent.VK_ENTER);
			} catch (AWTException e) {
				e.printStackTrace();
			}
	}
	
	protected void pressTabKey()
	{
		   Robot r;
			try {
				   r = new Robot();
				   r.keyPress(KeyEvent.VK_TAB);
				   r.keyRelease(KeyEvent.VK_TAB);
			} catch (AWTException e) {
				e.printStackTrace();
			}
	}
	
	protected int getIndexOfSelectedDropdownValue( By by)
	{
	    List<WebElement> list = dropdown(by).getOptions();

	    for(int i=0;i<list.size();i++){
	        if(list.get(i).getText().equals(dropdown(by).getFirstSelectedOption().getText())){
	            return i;
	            }
	    }
	    return -1;
		  
	}
	

	public void closeWindow()
	{
	String base = Helper.getWebDriver().getWindowHandle();
      
	  System.out.println ("Base :" + base);
      
	  Set <String> set = Helper.getWebDriver().getWindowHandles();
	  System.out.println(" String Set" + set);
	  
	  System.out.flush();
              
      set.remove(base);
      assert set.size()==1;
      
      String[] y = set.toArray(new String[0]);
      
      System.out.println(" closing window" + y[0]);
      
      System.out.flush();
      Helper.getWebDriver().switchTo().window(y[0]);
      
      //Helper.getWebDriver().getCurrentUrl();
      
      Helper.getWebDriver().close();
      Helper.getWebDriver().switchTo().window(base);
	}
}
