package com.mendix.tool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementProxy implements InvocationHandler {

    private final WebElement element;
    
    WebDriver driver;

	

    public ElementProxy(WebElement element) {
        this.element = element;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //before invoking actual method check for the popup
        this.checkForPopupAndKill();
        //at this point, popup would have been closed if it had appeared. element action can be called safely now.
        Object result = method.invoke(element, args);
        return result;
    }

    private void checkForPopupAndKill() {
    	/*WebDriverWait wait = new WebDriverWait(driver, 120);
	      wait.until(ExpectedConditions.visibilityOfElementLocated(
	    		  By.cssSelector(".btn.btn-primary"))
	            );*/
    	
    	   WebDriverWait wait = new WebDriverWait(driver, 120);
		      wait.until(ExpectedConditions.visibilityOfElementLocated(
		    		  By.xpath("//p[contains(text,'A connection error occurred, please try again later.')")));
		            

			
//        if (popup.isDisplayed()) {
            System.out.println("You damn popup, you appearded again!!?? I am gonna kill you now!!");
//            popup.close();
            Button.click("Click Ok Button", driver.findElement(By.cssSelector(".btn.btn-primary")));
        }
    }
    
//}