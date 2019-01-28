package com.mendix.tool;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mendix.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Textbox.
 */
public class Textbox {

/**
 * Enter value.
 *
 * @param strLogicalName the str logical name
 * @param element the element
 * @param strValue the str value
 * @return true, if successful
 */
public static boolean enterValue(String strLogicalName,WebElement element,String strValue){
	boolean isValueEntered=false;
	try{
		element.clear();		
		element.sendKeys("");
		element.sendKeys(strValue);
		Sync.waitForSeconds(Constants.WAIT_1);
		isValueEntered=true;
		ResultUtil.report(Constants.STATUS_PASS, "Entered "+strValue+" in "+strLogicalName+" textbox");
	}
	catch(Exception e){
		ResultUtil.report(Constants.STATUS_FAIL, "Unable to enter "+strValue+" value in "+strLogicalName+" textbox Exception occurred:"+e.getMessage());
	}
	
	return isValueEntered;
}

/**
 * Click.
 *
 * @param strLogicalName the str logical name
 * @param element the element
 * @return true, if successful
 */
public static boolean click(String strLogicalName ,WebElement element){
	boolean isClicked=false;
	try{
		element.click();
		isClicked=true;
		ResultUtil.report(Constants.STATUS_PASS, "Clicked "+strLogicalName+" textbox");
	}
	catch(Exception e){
		ResultUtil.report(Constants.STATUS_FAIL, "Unable to click "+strLogicalName+" textbox Exception occurred:"+e.getMessage());
	}
	
	return isClicked;
}

/**
 * Clear.
 *
 * @param strLogicalName the str logical name
 * @param element the element
 * @return true, if successful
 */
public static boolean clear(String strLogicalName ,WebElement element){
	boolean isCleared=false;
	try{
		element.clear();
		isCleared=true;
		ResultUtil.report(Constants.STATUS_PASS, "Cleared "+strLogicalName+" textbox");
		
	}
	catch(Exception e){
		ResultUtil.report(Constants.STATUS_FAIL, "Unable to clear "+strLogicalName+" textbox Exception occurred:"+e.getMessage());
	}
	
	return isCleared;
}


/**
 * Js enter value.
 *
 * @param strLogicalName the str logical name
 * @param driver the driver
 * @param element the element
 * @param strValue the str value
 * @return true, if successful
 */
public static boolean jsEnterValue(String strLogicalName,WebDriver driver,WebElement element,String strValue){
	boolean isValueEntered=false;
	try{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value=arguments[1];",element,strValue);
		isValueEntered=true;
		ResultUtil.report(Constants.STATUS_PASS, "Entered "+strValue+" in "+strLogicalName+" textbox");
	}
	catch(Exception e){
		ResultUtil.report(Constants.STATUS_FAIL, "Unable to enter "+strValue+" value in "+strLogicalName+" textbox Exception occurred:"+e.getMessage());
	}
	
	return isValueEntered;
}
}
