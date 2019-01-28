package com.mendix.tool;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mendix.util.ResultUtil;



// TODO: Auto-generated Javadoc
/**
 * The Class Button.
 */
public class Button {
	
	/**
	 * Click.
	 *
	 * @param strLogicalName the str logical name
	 * @param element the element
	 * @return true, if successful
	 */
	static WebDriver driver;
	
	public Button(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public static boolean click(String strLogicalName,WebElement element){
		boolean isButtonClicked=false;
		try{
//			(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(element));
			//element.clear();
			element.click();
			isButtonClicked=true;
			ResultUtil.report(Constants.STATUS_PASS, "Clicked "+strLogicalName+" button");
//			ExcelReportUpdate.updateResult("C:\\MDM_Workspace\\MDM_POC_Upgrade\\report\\excel\\Mendix-DROP1-IE-19_11_2018.xlsm", strLogicalName, Constants.STATUS_PASS);
			
		}
		catch(Exception e){
			ResultUtil.report(Constants.STATUS_FAIL, "Unable to click "+strLogicalName+" button Exception occurred:"+e.getMessage());
		}
		
		return isButtonClicked;
	}
	
	public static boolean jsclick(String strLogicalName,WebElement element,WebDriver driver){
		boolean isButtonClicked=false;
		try{
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			isButtonClicked=true;
			ResultUtil.report(Constants.STATUS_PASS, "Clicked "+strLogicalName+" button");
			
		}
		catch(Exception e){
			ResultUtil.report(Constants.STATUS_FAIL, "Unable to click "+strLogicalName+" button Exception occurred:"+e.getMessage());
		}
		
		return isButtonClicked;
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
	
	/**
	 * Js click.
	 *
	 * @param strLogicalName the str logical name
	 * @param driver the driver
	 * @param element the element
	 * @return true, if successful
	 */
	public static boolean verifyObject(WebElement element){
		  boolean isVerified= false;
		  try{
		   if(element.isDisplayed()){
		    isVerified = true;
		   }
		  }catch(Exception e){
		     }
		  return isVerified;
		 }
	
	public static boolean mouseOver(String strLogicalName,WebDriver driver,WebElement element) {
		  boolean isButtonMouseOver=false;
		  try{
		        Actions actions=new Actions(driver);
		        actions.moveToElement(element).build().perform();
		        isButtonMouseOver = true;
		        ResultUtil.report(Constants.STATUS_PASS, "Clicked "+strLogicalName+" button");
		  }
		  catch(Exception e){
			  ResultUtil.report(Constants.STATUS_FAIL, "Unable to click "+strLogicalName+" button Exception occurred:"+e.getMessage());
		  }
		  return isButtonMouseOver;
		  
		    }
	
		
	public static boolean NewmouseOver(String strLogicalName,WebDriver driver,WebElement element) {
		  boolean isButtonMouseOver=false;
		  try{
			  	String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript,element);
		        /*Actions actions=new Actions(driver);
		        actions.moveToElement(element).build().perform();*/
		        isButtonMouseOver = true;
		        ResultUtil.report(Constants.STATUS_PASS, "Clicked "+strLogicalName+" button");
		  }
		  catch(Exception e){
			  ResultUtil.report(Constants.STATUS_FAIL, "Unable to click "+strLogicalName+" button Exception occurred:"+e.getMessage());
		  }
		  return isButtonMouseOver;
		  
		    }
	
	
	 public static boolean mouseDoubleClick(WebDriver driver,String strLogicalName ,By by) {
         boolean blResult=false;
        try{ 
         WebElement element =Sync.waitForObject(driver, by);         
         Actions actions=new Actions(driver);
         actions.moveToElement(element);
         actions.doubleClick(element);
         actions.build().perform();
         blResult = true;
         
         ResultUtil.report(Constants.STATUS_PASS, "Double-Clicked "+strLogicalName+" button");
        }catch(Exception e){
        	
        	 ResultUtil.report(Constants.STATUS_FAIL, "Unable to Double-click "+strLogicalName+" button Exception occurred:"+e.getMessage());
        }

         return blResult;
     }
	 
	 public static boolean mouseClick(WebDriver driver,String strLogicalName ,WebElement element) {
         boolean blResult=false;
        try{ 
//         WebElement element =Sync.waitForObject(driver, by);         
         Actions actions=new Actions(driver);
         actions.moveToElement(element);
         actions.click(element);
         actions.build().perform();
         blResult = true;
         
         ResultUtil.report(Constants.STATUS_PASS, "Double-Clicked "+strLogicalName+" button");
        }catch(Exception e){
        	
        	 ResultUtil.report(Constants.STATUS_FAIL, "Unable to Double-click "+strLogicalName+" button Exception occurred:"+e.getMessage());
        }

         return blResult;
     }
		 
 	}
