package com.mendix.page;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


public class Material_Nav_Page {

	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new home page changes
	 *
	 * @param driver the driver
	 */
	public Material_Nav_Page(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	

	@FindBy(how=How.XPATH, using="//*[text()='Local Data']")
	WebElement textLocalData;
	
	@FindBy(how=How.XPATH, using="(.//*[@class='glyphicon glyphicon-edit'])[5]")
	WebElement BtnEditPlantData;
	
	@FindBy(how=How.XPATH, using="(.//*[@class='glyphicon glyphicon-new-window'])[6]")
	WebElement BtnAddPlantData;
	
	@FindBy(how=How.XPATH, using="//button[text()='Select']")
	WebElement BtnPlantSelect;
	
	@FindBy(how=How.XPATH, using="(.//button[text()='Edit'])[1]")
	WebElement BtnPlantEdit;
	
	@FindBy(how=How.CSS, using=".btn.btn-primary")
	WebElement btnMsgReqIdOk;
	
	public void enterLocalData() {

//		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitUntilObjectDisappears(driver, "Waiting of Create page to Load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, textLocalData);
		Button.click("Local Data", textLocalData);
		Sync.waitForObject(driver, BtnAddPlantData);
		/*return Button.click("Click Edit button", BtnAddPlantData);*/
		
	}
	
	public boolean clickAddPlantData() {

		Sync.waitForObject(driver, BtnAddPlantData);
		return Button.click("Click Edit button", BtnAddPlantData);
		
	}
	
	
	public boolean enterPlantData(String strValue) {

		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitUntilObjectDisappears(driver, "Waiting of Create page to Load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, BtnAddPlantData);
		Button.click("Local Plant Data", BtnAddPlantData);
		
		
		WebDriverWait wait= new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+strValue+"']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+strValue+"']")));
		driver.findElement(By.xpath("//div[text()='DZ10']")).click();
		return Button.click("Click Select Button", BtnPlantSelect);
	
	}
	
	public void clickEditPlanningData() throws AWTException, IOException
	{
		Sync.waitForSeconds(Constants.WAIT_6);
		driver.findElement(By.xpath("(//button[text()='Edit'])[5]")).click();
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		/*JavascriptExecutor jse = (JavascriptExecutor)driver;
		Sync.waitForSeconds(Constants.WAIT_6);
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("(//*[text()='OK'])[2]")));
		Sync.waitForSeconds(Constants.WAIT_3);
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("(//*[text()='OK'])[1]")));*/
	}
	
	public void selectRoundingPrecision(String strValue)
	{
		
		WebElement dropdown =driver.findElement(By.xpath(".//*[text()='Rounding Precision']/../div/div/select"));
		Sync.waitForObjectFluent(driver, dropdown);
		Select roundPrecisiondropDown= new Select(dropdown);
		roundPrecisiondropDown.selectByVisibleText(strValue);
		
	}
	public void clickLocalAction()
	{
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement waitElement = null;
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
		        .withTimeout(Duration.ofMinutes(3))
		        .pollingEvery(Duration.ofSeconds(600))
		        .ignoring(NoSuchElementException.class)
		        .ignoring(TimeoutException.class);
		 
		//First checking to see if the loading indicator is found
		// we catch and throw no exception here in case they aren't ignored
		try {
		  waitElement = fwait.until(new Function<WebDriver, WebElement>() {
		   public WebElement apply(WebDriver driver) {
		      return driver.findElement(By.xpath(".//*[@id='mxui_widget_Progress_0']"));
		   }
		 });
		    } catch (Exception e) {
		   }
		 
		//checking if loading indicator was found and if so we wait for it to
		//disappear
		  if (waitElement != null) {
		      WebDriverWait wait = new WebDriverWait(driver, 120);
		      wait.until(ExpectedConditions.visibilityOfElementLocated(
		    		  By.xpath(".//*[@class='glyphicon glyphicon-flash']"))
		            );
		        }
//		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		Sync.waitForElementToBeClickable(driver, driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-flash']")));
		driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-flash']")).click();
	}
	
	public void clickValidatLocalData()
	{
		
		Sync.waitForSeconds(Constants.WAIT_5);
		driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-check']")).click();
		
	
	}
	
	public void clickValidateLocalData_Planning()
	{
		
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForObject(driver, "Wait for Planning Data to Load", driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-check']")));
		driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-check']")).click();
		
	
	}
	
	public void clickSaveButton()
	{
		Sync.WaitForPageLoad(driver);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Save button", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, "Wait for Click Save button", driver.findElement(By.xpath(".//button[text()='Save']")));
		Button.jsclick("Click Save Button", driver.findElement(By.xpath(".//button[text()='Save']")), driver);
	
	}
	
	public void clickPlanningSaveButton()
	{
		Sync.WaitForPageLoad(driver);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Save button", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, "Wait for Click Save button", driver.findElement(By.xpath(".//button[text()='Save']")));
		Button.jsclick("Click Save Button", driver.findElement(By.xpath(".//button[text()='Save']")), driver);
	
	}
	
	
	
	public void clickFinancetab() throws InterruptedException
	{
		Sync.waitUntilObjectDisappears(driver, "Wait for Save button", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.WaitForPageLoad(driver);
		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitUntilObjectDisappears(driver, "Wait for Save button", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
//		Sync.waitForObject(driver, "Wait for Click element", driver.findElement(By.cssSelector("div[id^='mxui_widget_TabContainer']:nth-child(1) > ul:nth-child(1) >li:nth-child(2)>a")));
		Sync.waitForElementToBeClickable(driver, driver.findElement(By.cssSelector("div[id^='mxui_widget_TabContainer']:nth-child(1) > ul:nth-child(1) >li:nth-child(2)>a")));
		Button.jsclick("Click Finance Tab", driver.findElement(By.cssSelector("div[id^='mxui_widget_TabContainer']:nth-child(1) > ul:nth-child(1) >li:nth-child(2)>a")), driver);
	}
	
	public void clickEditFinanceData() throws AWTException, IOException
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Save button", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForElementToBeClickable(driver, driver.findElement(By.xpath("(//*[text()='Add'])[2]/../button[2]/span")));
		Button.jsclick("Click Edit Finance Button", driver.findElement(By.xpath("(//*[text()='Add'])[2]/../button[2]/span")), driver);
//		driver.findElement(By.xpath("(//*[text()='Add'])[2]/../button[2]/span")).click();
		/*Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Sync.waitForSeconds(Constants.WAIT_6);
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("(//*[text()='OK'])[2]")));
		Sync.waitForSeconds(Constants.WAIT_3);
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("(//*[text()='OK'])[1]")));*/
	}
	
	
	
	
	
	public void selectVATPostingGroup(String strValue)
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='VAT Prod. Posting Group']/../div/div/select"));
		Sync.waitForObject(driver, "Wait for VAT posting group Select", dropdown);
		Sync.waitForElementToBeClickable(driver, dropdown);
		Button.click("Wait for VAT posting group Select", dropdown);
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText(strValue);
		
	}
	public void selectGenProdPostingGroup(String strValue)
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='Gen. Prod. Posting Group']/../div/div/select"));
		Sync.waitForObject(driver, "Wait for VAT posting group Select", dropdown);
		Sync.waitForElementToBeClickable(driver, dropdown);
		Button.click("Wait for VAT posting group Select", dropdown);
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText(strValue);
		
	}
	
	
	public void selectItemDepositGroupCode(String strValue)
	{
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='Item Deposit Group Code']/../div/div/select"));
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText(strValue);
		
	}
	
	public void submitGlobalRequestTest() throws InterruptedException {

//		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.WaitForPageLoad(driver);
//		Sync.waitForObject(driver, "Wait for Submit Global Request", driver.findElement(By.xpath("//*[text()='Local Actions:']/../div[2]/div[2]/div[1]/div[1]/div/div[1]/div[1]/div/div/div/div[1]/div/button/span")));
		Sync.waitForElementToBeClickable(driver, driver.findElement(By.xpath("//*[text()='Local Actions:']/../div[2]/div[2]/div[1]/div[1]/div/div[1]/div[1]/div/div/div/div[1]/div/button/span")));
		Button.jsclick("Click Global submit Global Request", driver.findElement(By.xpath("//*[text()='Local Actions:']/../div[2]/div[2]/div[1]/div[1]/div/div[1]/div[1]/div/div/div/div[1]/div/button/span")), driver);
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForSeconds(Constants.WAIT_5);
		
	}
	
	public void submitGlobalLocalRequestTest() throws InterruptedException {

		TimeUnit.MINUTES.sleep(2);
		Sync.waitForObject(driver, "Wait for Submit Global Request", driver.findElement(By.xpath("(//span[@class='glyphicon glyphicon-save'])[2]")));
		Sync.waitForElementToBeClickable(driver, driver.findElement(By.xpath("(//span[@class='glyphicon glyphicon-save'])[2]")));
		Button.jsclick("Click Global submit Global Request", driver.findElement(By.xpath("(//span[@class='glyphicon glyphicon-save'])[2]")), driver);
//		Button.click("Click Global submit Global Request", driver.findElement(By.xpath("(//span[@class='glyphicon glyphicon-save'])[2]")));
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForSeconds(Constants.WAIT_5);
		
	}
	
	public boolean submitRequestOkBtnClick()
	{

		Sync.waitForSeconds(Constants.WAIT_2);
		WebElement waitElement = null;
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
		        .withTimeout(Duration.ofMinutes(3))
		        .pollingEvery(Duration.ofSeconds(600))
		        .ignoring(NoSuchElementException.class)
		        .ignoring(TimeoutException.class);
		 
		//First checking to see if the loading indicator is found
		// we catch and throw no exception here in case they aren't ignored
		try {
		  waitElement = fwait.until(new Function<WebDriver, WebElement>() {
		   public WebElement apply(WebDriver driver) {
		      return driver.findElement(By.xpath(".//*[@id='mxui_widget_Progress_0']"));
		   }
		 });
		    } catch (Exception e) {
		   }
		 
		//checking if loading indicator was found and if so we wait for it to
		//disappear
		  if (waitElement != null) {
		      WebDriverWait wait = new WebDriverWait(driver, 60);
		      wait.until(ExpectedConditions.invisibilityOfElementLocated(
		    		  By.cssSelector(".btn.btn-primary"))
		            );
		        }
		
/*		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)                            
				.withTimeout(Duration.ofSeconds(300))          
				.pollingEvery(Duration.ofMillis(600))          
				.ignoring(NoSuchElementException.class);

		WebElement btnMsgReqIdOk=wait.until(new Function<WebDriver, WebElement>() {       
			public WebElement apply(WebDriver driver) { 
				return driver.findElement(By.cssSelector(".btn.btn-primary"));     
			}  
		});*/


/*		Actions btnselect = new Actions(driver);
		btnselect.moveToElement(btnMsgReqIdOk);
		btnselect.build();
		btnselect.perform();*/
//		System.out.println(btnMsgReqIdOk.getCssValue("color"));
		return Button.click("Click Ok Button", btnMsgReqIdOk);
	}
	
	
	public void clickSiteNewTab(){
		

		Sync.waitUntilObjectDisappears(driver, "Wait for Save button", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.WaitForPageLoad(driver);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Save button", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
//		Sync.waitForObjectFluent(driver, driver.findElement(By.cssSelector("div[id^='mxui_widget_TabContainer']:nth-child(1) > ul:nth-child(1) >li:nth-child(3)>a")));
		Button.jsclick("Click Site Tab", driver.findElement(By.cssSelector("div[id^='mxui_widget_TabContainer']:nth-child(1) > ul:nth-child(1) >li:nth-child(3)>a")), driver);
	}
	
	public void clickSiteNewButton(){

		Sync.waitForObject(driver, "Wait for Site New Button", driver.findElement(By.xpath("//button[text()='New']")));
		Sync.waitForSeconds(Constants.WAIT_2);
		Button.click("Click New Button", driver.findElement(By.xpath("//button[text()='New']")));
		Sync.waitForSeconds(Constants.WAIT_2);
	
	}
	
	public void selectLocationCode(String strValue)
	{
		/*WebElement dropdown =driver.findElement(By.xpath("//*[text()='Item Deposit Group Code']/../div/div/select"));
		Select LocationCodeDropDown= new Select(dropdown);
		LocationCodeDropDown.selectByVisibleText("DZ01, Brewery Rouiba");
*/		
		Sync.waitUntilObjectDisappears(driver, "Wait for Location Code Select", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForSeconds(Constants.WAIT_5);
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='Location Code']/../div/div/select"));
		Sync.waitForObject(driver, "Wait for Location Code Select", dropdown);
		Button.click("Wait for Location Code Select", dropdown);
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText(strValue);
	}
	
	public void selectReplenishmentSystem(String strValue)
	{
//		WebElement dropdown =driver.findElement(By.xpath("//*[text()='Replenishment System']/../div/div/select"));
//		Select LocationCodeDropDown= new Select(dropdown);
//		LocationCodeDropDown.selectByVisibleText("2, Transfer");
		
		Sync.waitUntilObjectDisappears(driver, "Wait for Replenishment System", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForSeconds(Constants.WAIT_5);
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='Replenishment System']/../div/div/select"));
		Sync.waitForObject(driver, "Wait for Location Code Select", dropdown);
		Button.click("Wait for Location Code Select", dropdown);
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText(strValue);
		
	}
	
	public void clickSiteValidateButton(){


		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForElementToBeClickable(driver, driver.findElement(By.xpath("//*[text()='Validate Local Data']")));
		Button.click("Click New Button", driver.findElement(By.xpath("//*[text()='Validate Local Data']")));
		Sync.waitForSeconds(Constants.WAIT_2);
	
	}
	
	public void clickSiteSaveButton(){

		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForElementToBeClickable(driver, driver.findElement(By.cssSelector(".glyphicon.glyphicon-ok")));
		Button.click("Click New Button", driver.findElement(By.cssSelector(".glyphicon.glyphicon-ok")));
		Sync.waitForSeconds(Constants.WAIT_2);
	
	}
	
	public void scrolltoRoundingPrecision() throws InterruptedException {
		
		Point hoverItem =driver.findElement(By.xpath(".//*[text()='Rounding Precision']")).getLocation();
		((JavascriptExecutor)driver).executeScript("return window.title;");    
		Thread.sleep(6000);
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
	}
	
	public void clickEditSiteData() throws AWTException, IOException
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Location Code Select", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
//		Sync.waitForElementToBeClickable(driver, driver.findElement(By.xpath("(//*[text()='Add'])[3]/../button[2]/span")));
		Button.jsclick("Click Edit Site Button", driver.findElement(By.xpath("(//*[text()='Add'])[3]/../button[2]/span")), driver);
//		driver.findElement(By.xpath("(//*[text()='Add'])[3]/../button[2]/span")).click();
	}
	
	public void selectLocationCode()
	{
		Sync.waitUntilObjectDisappears(driver, "Wait for Location Code Select", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForSeconds(Constants.WAIT_5);
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='Location Code']/../div/div/select"));
		Sync.waitForObject(driver, "Wait for Location Code Select", dropdown);
		Button.click("Wait for Location Code Select", dropdown);
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText("DZ01, Brewery Rouiba");
	}
	
	public void selectReplenishmentSystem()
	{
		
		Sync.waitUntilObjectDisappears(driver, "Wait for Replenishment System", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForSeconds(Constants.WAIT_5);
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='Replenishment System']/../div/div/select"));
		Sync.waitForObject(driver, "Wait for Location Code Select", dropdown);
		Button.click("Wait for Location Code Select", dropdown);
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText("2, Transfer");
		
	}
}
	
	
