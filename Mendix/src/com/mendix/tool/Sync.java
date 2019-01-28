package com.mendix.tool;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mendix.util.HelperUtil;



// TODO: Auto-generated Javadoc
/**
 * The Class Sync.
 */
public class Sync {

	/**
	 * Wait for object.
	 *
	 * @param driver the driver
	 * @param strLogicalName the str logical name
	 * @param element the element
	 * @return true, if successful
	 */
	public static boolean waitForObject(WebDriver driver,String strLogicalName,WebElement element){
		boolean blResult=false;
		try{
			new WebDriverWait(driver,getWaitTime(Constants.OBJECT_WAIT_TIME)).until(ExpectedConditions.visibilityOf(element));
		}
		catch(Exception e){}
		return blResult;

	}

	public static boolean waitForObject(WebDriver driver,WebElement element){
		boolean blResult=false;
		try{
			new WebDriverWait(driver,getWaitTime(Constants.OBJECT_WAIT_TIME)).until(ExpectedConditions.visibilityOf(element));
		}
		catch(Exception e){}
		return blResult;

	}


	/**
	 * Wait for object.
	 *
	 * @param driver the driver
	 * @param strLogicalName the str logical name
	 * @param by the by
	 * @return the web element
	 */
	public static WebElement waitForObject(WebDriver driver,String strLogicalName,By by){
		WebElement element=null;
		try{
			element=new WebDriverWait(driver,getWaitTime(Constants.OBJECT_WAIT_TIME)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by)).get(0);
		}
		catch(Exception e){}
		return element;

	}

	public static WebElement waitForObject(WebDriver driver,By by){
		WebElement element=null;
		try{
			element=new WebDriverWait(driver,getWaitTime(Constants.OBJECT_WAIT_TIME)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by)).get(0);
		}
		catch(Exception e){}
		return element;

	}

	public static WebElement waitForObjectFluent(WebDriver driver,WebElement element) {
		//	WebElement element=null;
		Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(300)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		WebElement foo = gWait.until(new Function<WebDriver, WebElement>() 
		{
			public WebElement apply(WebDriver driver) {
				return element;
			}
		});

		return foo;
	}

/*	public void FluentWait() {
        WebElement foo = driver.findElement(By.id("foo"));
        new FluentWait<WebElement>(foo)
            .withTimeout(Duration.ofSeconds(300))
            .pollingEvery(Duration.ofMillis(600))
                    .until(new Function<WebElement, String>() {
                        @Override
                        public String apply(WebElement element) {
                            return element.getText();
                        }
                    });
    }*/

	/**
	 * Gets the wait time.
	 *
	 * @param strWaitType the str wait type
	 * @return the wait time
	 */
	private static long getWaitTime(String strWaitType){
		long lngWaitTime = 0;

		try{
			String strWaitTime=HelperUtil.executionConfigMap.get(strWaitType);
			String strWaitNumber=HelperUtil.executionConfigMap.get(Constants.WAIT_NUMBER);
			lngWaitTime=Long.parseLong(strWaitTime)*Long.parseLong(strWaitNumber);
		}
		catch(Exception e){

		}

		return lngWaitTime;
	}

	/**
	 * Wait for seconds.
	 *
	 * @param strWaitName the str wait name
	 */
	public static void waitForSeconds(String strWaitName){
		try{

			Thread.sleep(getWaitTime(strWaitName)*500);
		}
		catch(Exception e){

		}
	}

	/**
	 * Wait until object disappears.
	 *
	 * @param driver the driver
	 * @param strLogicalName the str logical name
	 * @param by the by
	 * @return true, if successful
	 */
	public static boolean waitUntilObjectDisappears(WebDriver driver,String strLogicalName,By by){
		boolean blResult=false;
		try{
			new WebDriverWait(driver,getWaitTime("60")).until(ExpectedConditions.invisibilityOfElementLocated(by));
		}
		catch(Exception e){}
		return blResult;

	}

	public static boolean waitForElementToBeClickable(WebDriver driver,WebElement element) {
		boolean blResult = false;
		try {
			long lngWaitTime = Long.parseLong(Constants.OBJECT_WAIT_TIME);

			long lngWaitUnit=Long.parseLong(Constants.WAIT_2);

			new WebDriverWait(driver, lngWaitTime * lngWaitUnit)
			.until(ExpectedConditions.elementToBeClickable(element));
			blResult = true;
		} catch (Exception e) {
			return blResult;
		}
		return blResult;
	}


	public static WebElement fluentWaitForObject(WebDriver driver,WebElement elementWait,By by){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(200)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class); 
		// Then - declare the webElement and use a function to find it
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}


		});
		return element;
	}
	
	
	public static boolean WaitForPageLoad(WebDriver driver)
	{
		boolean blResult = false;
	
		try{
	     new WebDriverWait(driver, getWaitTime(Constants.SCRIPT_WAIT_TIME)).until(
	          webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	
		}
		catch(Exception e){}
		return blResult;
	
	}
	
}
