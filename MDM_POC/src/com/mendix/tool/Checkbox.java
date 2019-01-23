package com.mendix.tool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mendix.util.ResultUtil;



// TODO: Auto-generated Javadoc
/**
 * The Class Checkbox.
 */
public class Checkbox {
	
	/**
	 * Check.
	 *
	 * @param driver the driver
	 * @param strLogicalName the str logical name
	 * @param element the element
	 * @return true, if successful
	 */
	public static boolean check(WebDriver driver,String strLogicalName, WebElement element){
		boolean isValueChecked= false;
		try{
			Sync.waitForObject(driver,strLogicalName,element);
			element.click();
			isValueChecked= true;
			ResultUtil.report(Constants.STATUS_PASS, "Clicked "+strLogicalName+" checkbox");
		}
		catch(Exception e){
			ResultUtil.report(Constants.STATUS_FAIL, "Unable to click "+strLogicalName+" checkbox Exception occurred:"+e.getMessage());
		}
		
		return isValueChecked;
	}

}
