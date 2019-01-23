package com.mendix.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mendix.tool.Constants;
import com.mendix.tool.SharedDriver;



// TODO: Auto-generated Javadoc
/**
 * The Class BrowserUtil.
 */
public class BrowserUtil {

/**
 * Gets the driver.
 *
 * @param strBrowser the str browser
 * @param strURL the str url
 * @return the driver
 */
@SuppressWarnings("deprecation")
public static WebDriver getDriver(String strBrowser,String strURL){
	WebDriver driver=null;
	try{
		if(strBrowser.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
			
			DesiredCapabilities ieCap=new DesiredCapabilities();
        	ieCap.setCapability("ignoreZoomSetting", true);
        	ieCap.setCapability("requireWindowFocus", true);
        	ieCap.setCapability("enablePersistentHover", false);
//        	ieCap.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, strURL);
        	ieCap.setCapability("requireWindowFocus", false);
        	
            driver = new InternetExplorerDriver(ieCap);
            driver.manage().window().maximize();
		}
		else if(strBrowser.equalsIgnoreCase("FIREFOX")){
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if(strBrowser.equalsIgnoreCase("CHROME")){
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		driver.get(strURL);
//		ResultUtil.report(Constants.STATUS_PASS, "Opened "+strURL+" on "+strBrowser);
	}
	catch(Exception e){
		ResultUtil.report(Constants.STATUS_FAIL, "Unable to open "+strURL+" on "+strBrowser+" Exception occurred:"+e.getMessage());
	}
	
	return driver;
}

/**
 * Close browser.
 */
public static void closeBrowser(){
	try{
		SharedDriver.driver.quit();
		ResultUtil.report(Constants.STATUS_INFO, "Closed browser(s)");
	}
	catch(Exception e){
		ResultUtil.report(Constants.STATUS_INFO, "Unable to close browser(s)-Exception occurred:"+e.getMessage());
	}
}




}
