package com.mendix.page;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mendix.tool.Button;
import com.mendix.tool.Constants;
import com.mendix.tool.Sync;


public class Material_Change_Page {

	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new home page changes
	 *
	 * @param driver the driver
	 */
	public Material_Change_Page(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	@FindBy(how=How.XPATH, using="//*[text()='Edit Global Data']/../div/input")
	WebElement checkBoxEdit;
	
	@FindBy(how=How.XPATH, using="//button[text()='Edit']")
	WebElement btnEdit;
	
	
	@FindBy(how=How.XPATH, using="//button[text()='Create With Reference']")
	WebElement btnCreateRef; 
	
	public void clickEditCheckBox() {
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		Sync.waitForObject(driver, "Wait until the Material appears", checkBoxEdit);
		Button.click("Click Materials Menu", checkBoxEdit);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
	}
	
	public void clickEditbutton() {
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		Sync.waitForObject(driver, "Wait until the Material appears", btnEdit);
		Button.click("Click Materials Menu", btnEdit);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
	}
	public void clickReferencebutton() {
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		Sync.waitForObject(driver, "Wait until the Material appears", btnCreateRef);
		Button.click("Click Materials Menu", btnCreateRef);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		} 
	
	
	

}
	
	
