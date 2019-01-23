package com.mendix.tool;

import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mendix.util.ResultUtil;

public class Menu {

		
	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed()
					|| element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}
	
	public static boolean clear(String strLogicalName,WebElement element){
		boolean isButtonCleared=false;
		try{
			element.clear();
			element.click();
			isButtonCleared=true;
			ResultUtil.report(Constants.STATUS_PASS, "Clear "+strLogicalName+" button");
			
		}
		catch(Exception e){
			ResultUtil.report(Constants.STATUS_FAIL, "Unable to clear "+strLogicalName+" button Exception occurred:"+e.getMessage());
		}
		
		return isButtonCleared;
	}
	
	public static void mouseHoverJScript(String strLogicalNamet,WebElement element,WebDriver driver) {


		try {
			if (isElementPresent(element)) {

				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript,
						element);

			} else {
				System.out.println("Element was not visible to hover " + "\n");

			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + element
					+ "is not attached to the page document"
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM"
					+ e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering"
					+ e.getStackTrace());
		}
	}
}
