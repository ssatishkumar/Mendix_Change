package com.mendix.tool;

import org.openqa.selenium.WebDriver;

import com.mendix.util.BrowserUtil;
import com.mendix.util.HelperUtil;



// TODO: Auto-generated Javadoc
/**
 * The Class SharedDriver.
 */
public class SharedDriver {
	
	/** The driver. */
	public static WebDriver driver;
	
	/** The page container. */
	public static PageContainer pageContainer;
	
	/**
	 * Creates the driver.
	 */
	public static void createDriver(){
		driver=BrowserUtil.getDriver(HelperUtil.executionConfigMap.get(Constants.BROWSER), HelperUtil.executionConfigMap.get(Constants.URL));
		pageContainer=new PageContainer(driver);
	}
}
