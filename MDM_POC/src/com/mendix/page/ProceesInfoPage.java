package com.mendix.page;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.mendix.tool.Button;
import com.mendix.tool.Constants;
import com.mendix.tool.Sync;
import com.mendix.tool.Textbox;

public class ProceesInfoPage {

	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new process Info page.
	 *
	 * @param driver the driver
	 */
	public ProceesInfoPage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}



	@FindBy(how=How.CSS, using="div[id^='mxui_widget_NumberInput_'][class^='mx-name-textBox2'] :nth-child(1)")
	WebElement txtboxRequestId;

	@FindBy(how=How.XPATH, using=".//*[@class='glyphicon glyphicon-search']")
	WebElement btnReqIdSearch;

	@FindBy(how=How.XPATH, using="//a[contains(text(),'Process Information')]")
	WebElement menuProcessInfo;

	@FindBy(how=How.XPATH, using="(//*[starts-with(text(),' Process Search')])[1]")
	WebElement menuProcessInfoSearch;

	@FindBy(how=How.CSS, using=".btn.btn-primary")
	WebElement btnMsgReqIdOk;

	@FindBy(how=How.XPATH, using="//*[@class='glyphicon glyphicon-refresh']")
	WebElement btnReqIDClear;

	@FindBy(how=How.XPATH, using="(//*[text()='State'])[2]/../../../../../../table[2]//td[9]")
	WebElement txtStatus;

	@FindBy(how=How.XPATH, using="(//*[@class='mx-dateinput-input-wrapper'])[1]")
	WebElement txtboxRequestedDate;
	/**
	 * Enter UserName.
	 * Enter Password
	 * 
	 * Click Login
	 *
	 * @param strMenuName the str menu name
	 * @return 
	 * @return true, if successful
	 */


	public void reqIdSearch_Global(String strValue) throws InterruptedException, FileNotFoundException, IOException {

//		driver.switchTo().window("Application");
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForObject(driver, "Wait for Request Id", txtboxRequestId);
		Wait<WebDriver> Wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver arg0) {
				WebElement element = arg0.findElement(By.cssSelector("div[id^='mxui_widget_NumberInput_'][class^='mx-name-textBox2'] :nth-child(1)"));

				if (element.isDisplayed()) {
					return true;
				}
				return false;
			}
		};

		Wait.until(function);
		Sync.waitForSeconds(Constants.WAIT_6);
		Textbox.click("Click Request Id Text Box", txtboxRequestId);
		Sync.waitForSeconds(Constants.WAIT_1);
		Textbox.enterValue("Enter Request Id", txtboxRequestId, strValue);
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForSeconds(Constants.WAIT_2);
		driver.findElement(By.xpath("(.//*[@class='btn mx-button mx-dateinput-select-button'])[1]")).click();
		driver.findElement(By.xpath(".//*[@aria-selected='true']/span")).click();
		Sync.waitForSeconds(Constants.WAIT_5);
		driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-search']")).click();
	}

	public void reqIdSearch_Local(String strValue) throws InterruptedException, FileNotFoundException, IOException {

		driver.switchTo().window("Application");
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForObject(driver, "Wait for Request Id", txtboxRequestId);
		Wait<WebDriver> Wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver arg0) {
				WebElement element = arg0.findElement(By.cssSelector("div[id^='mxui_widget_NumberInput_'][class^='mx-name-textBox2'] :nth-child(1)"));

				if (element.isDisplayed()) {
					return true;
				}
				return false;
			}
		};

		Wait.until(function);
		Sync.waitForSeconds(Constants.WAIT_6);
		Textbox.click("Click Request Id Text Box", txtboxRequestId);
		Sync.waitForSeconds(Constants.WAIT_1);
		Textbox.enterValue("Enter Request Id", txtboxRequestId, strValue);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-search']")).click();
		Sync.waitForSeconds(Constants.WAIT_5);
		driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-search']")).click();
	}

	public boolean processInfoSearch() {

		Sync.waitForObjectFluent(driver, menuProcessInfo);
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForObjectFluent(driver, menuProcessInfo);
		Button.click("Click the Process Info Menu", menuProcessInfo);
		return Button.click("Click the Procees info search menu", menuProcessInfoSearch);
	}

	public String getState(String strValue){
		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForObject(driver, "Wait for the status to display", txtStatus);
		String state=driver.findElement(By.xpath(".//*[text()='"+strValue+"']/../../td[9]/div")).getText();
		System.out.println(state);
		Sync.waitForSeconds(Constants.WAIT_5);
		return state;
	}

	public void browserClose() {

		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		driver.close();
		driver.quit();
	}

}
