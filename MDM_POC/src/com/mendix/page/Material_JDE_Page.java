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


public class Material_JDE_Page {

	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new home page changes
	 *
	 * @param driver the driver
	 */
	public Material_JDE_Page(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	

	@FindBy(how=How.XPATH, using="//*[text()='Local Data']")
	WebElement textLocalData;
	
	@FindBy(how=How.XPATH, using="(.//*[@class='glyphicon glyphicon-edit'])[5]")
	WebElement BtnEditPlantData;
	
	@FindBy(how=How.XPATH, using="(.//*[@class='glyphicon glyphicon-new-window'])[6]")
	WebElement BtnAddPlantData;
	
	@FindBy(how=How.XPATH, using="(.//*[@class='glyphicon glyphicon-new-window'])[13]")
	WebElement BtnAddPlant;
	
	@FindBy(how=How.XPATH, using="//button[text()='Select']")
	WebElement BtnPlantSelect;
	
	@FindBy(how=How.XPATH, using="(.//button[text()='Edit'])[1]")
	WebElement BtnPlantEdit;
	
	@FindBy(how=How.CSS, using=".btn.btn-primary")
	WebElement btnMsgReqIdOk;
	
	public boolean enterLocalData() {

//		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitUntilObjectDisappears(driver, "Waiting of Create page to Load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, textLocalData);
		return Button.click("Local Data", textLocalData);
		/*Sync.waitForObject(driver, BtnAddPlant);
		return Button.click("Click Edit button", BtnAddPlant);*/
		
	}
	
	public boolean clickAddPlantData() {

		Sync.waitForObject(driver, BtnAddPlantData);
		return Button.click("Click Edit button", BtnAddPlantData);
		
	}
	
	
	public boolean enterPlantData() {

		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitUntilObjectDisappears(driver, "Waiting of Create page to Load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, BtnAddPlant);
		Button.click("Local Plant Data", BtnAddPlant);
		
		
		WebDriverWait wait= new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='10000']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='10000']")));
		driver.findElement(By.xpath("//div[text()='10000']")).click();
		return Button.click("Click Select Button", BtnPlantSelect);
	
	}
	
	public void clickEditPlanningData() throws AWTException, IOException
	{
		Sync.waitForSeconds(Constants.WAIT_6);
		driver.findElement(By.xpath("(//button[text()='Edit'])[5]")).click();
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		
	}
	
	public void selectRoundingPrecision()
	{
		
		WebElement dropdown =driver.findElement(By.xpath(".//*[text()='Rounding Precision']/../div/div/select"));
		Sync.waitForObjectFluent(driver, dropdown);
		Select roundPrecisiondropDown= new Select(dropdown);
		roundPrecisiondropDown.selectByVisibleText("0.01");
		Sync.waitForSeconds(Constants.WAIT_1);
		
	}
	
	
	
	public void selectStockingTypeLocal()
	{
		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitForSeconds(Constants.WAIT_6);
		WebElement dropdown =driver.findElement(By.xpath(".//*[text()='Stocking Type Local']/../div/div/select"));
		Sync.waitForObjectFluent(driver, dropdown);
//		dropdown.click();
		Select stockingTypeLocaldropDown= new Select(dropdown);
		Sync.waitForSeconds(Constants.WAIT_1);
		stockingTypeLocaldropDown.selectByVisibleText("P, Purchased");
		Sync.waitForSeconds(Constants.WAIT_1);
	}
	
	public void selectCommitmentDateMethod()
	{
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath(".//*[text()='Commitment Date Method']/../div/div/select"));
		Sync.waitForObjectFluent(driver, dropdown);
		Select commitmentDateMethoddropDown= new Select(dropdown);
		commitmentDateMethoddropDown.selectByVisibleText("3, Best Before Date");
		Sync.waitForSeconds(Constants.WAIT_1);
	}
	
	public void selectLotCalculationAlgorithm()
	{
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath(".//*[text()='Lot Calculation Algorithm']/../div/div/select"));
		Sync.waitForObjectFluent(driver, dropdown);
		Select lotCalculationAlgorithmdropDown= new Select(dropdown);
		lotCalculationAlgorithmdropDown.selectByVisibleText("01, Bulk Product Related Materials");
		Sync.waitForSeconds(Constants.WAIT_1);
	}
	public void selectLotProcessType()
	{
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath(".//*[text()='Lot Process Type']/../div/div/select"));
		Sync.waitForObjectFluent(driver, dropdown);
		Select lotProcessTypedropDown= new Select(dropdown);
		lotProcessTypedropDown.selectByVisibleText("3, Lots must be Assigned Manually");
		Sync.waitForSeconds(Constants.WAIT_1);
	}
	public void selectLotExpiratonDateCalculationMethod()
	{
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath(".//*[text()='Lot Expiraton Date Calculation Method']/../div/div/select"));
		Sync.waitForObjectFluent(driver, dropdown);
		Select lotExpiratonDateCalculationMethoddropDown= new Select(dropdown);
		lotExpiratonDateCalculationMethoddropDown.selectByVisibleText("1, On Hand Date");
		Sync.waitForSeconds(Constants.WAIT_1);
	}
	public void selectMasterPlanningFamily()
	{
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath(".//*[text()='Master Planning Family']/../div/div/select"));
		Sync.waitForObjectFluent(driver, dropdown);
		Select masterPlanningFamilydropDown= new Select(dropdown);
		masterPlanningFamilydropDown.selectByVisibleText("097, Typ. 09 - S&OP - MRP");
		Sync.waitForSeconds(Constants.WAIT_1);
	}
	public void selectPlanningCode()
	{
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath(".//*[text()='Planning Code']/../div/div/select"));
		Sync.waitForObjectFluent(driver, dropdown);
		Select planningCodedropDown= new Select(dropdown);
		planningCodedropDown.selectByVisibleText("0, Not Planned");
		Sync.waitForSeconds(Constants.WAIT_1);
	}
	
	public void clickLocalAction()
	{
//		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
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
		    		  By.xpath("//a[text()='Finance']"))
		            );
		        }
		/*Sync.waitUntilObjectDisappears(driver, "Wait for Save button", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.WaitForPageLoad(driver);
		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitUntilObjectDisappears(driver, "Wait for Save button", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForElementToBeClickable(driver, driver.findElement(By.cssSelector("div[id^='mxui_widget_TabContainer']:nth-child(1) > ul:nth-child(1) >li:nth-child(2)>a")));*/
		Button.jsclick("Click Finance Tab", driver.findElement(By.xpath("//a[text()='Finance']")), driver);
	}
	public void clickBackAction()
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		driver.findElement(By.cssSelector(".glyphicon.glyphicon-backward")).click();
	}
	
	public void clickProceedAction()
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		driver.findElement(By.xpath("//*[text()='Proceed']")).click();
	}
	
	public void clickPlantData()
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
//		driver.findElement(By.xpath("(.//*[text()='Plant'])[1]/../../../../../../table/tbody/tr[1]/td[1]"));
		
		Actions action = new Actions(driver);
		 //Find the targeted element
		 WebElement ele = driver.findElement(By.xpath("//div[text()='10000']"));
		                //Here I used JavascriptExecutor interface to scroll down to the targeted element
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
		                //used doubleClick(element) method to do double click action
		 action.doubleClick(ele).build().perform();
//		 driver.findElement(By.xpath("//div[text()='10000']")).sendKeys(Keys.RETURN);
	}
	
	
	
	public void clickEditFinanceData() throws AWTException, IOException
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Save button", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForElementToBeClickable(driver, driver.findElement(By.xpath("(//*[text()='Add'])[2]/../button[2]/span")));
		Button.jsclick("Click Edit Finance Button", driver.findElement(By.xpath("(//*[text()='Add'])[2]/../button[2]/span")), driver);

	}
	
		public void selectVATPostingGroup()
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='VAT Prod. Posting Group']/../div/div/select"));
		Sync.waitForObject(driver, "Wait for VAT posting group Select", dropdown);
		Sync.waitForElementToBeClickable(driver, dropdown);
		Button.click("Wait for VAT posting group Select", dropdown);
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText("NO_VAT, 0% VAT");
		
	}
	public void selectGLClass()
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='G/L Class']/../div/div/select"));
		Sync.waitForObject(driver, "Wait for VAT posting group Select", dropdown);
		Sync.waitForElementToBeClickable(driver, dropdown);
		Button.click("Wait for VAT posting group Select", dropdown);
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText("RAWM, Raw Materials");
		
	}
	
	public void selectCostingMethodPurchasing()
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='Costing Method - Purchasing']/../div/div/select"));
		Sync.waitForObject(driver, "Wait for VAT posting group Select", dropdown);
		Sync.waitForElementToBeClickable(driver, dropdown);
		Button.click("Wait for VAT posting group Select", dropdown);
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText("07, Standard");
		
	}
	
	public void selectCostingMethodSales()
	{
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_3);
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='Costing Method - Sales']/../div/div/select"));
		Sync.waitForObject(driver, "Wait for VAT posting group Select", dropdown);
		Sync.waitForElementToBeClickable(driver, dropdown);
		Button.click("Wait for VAT posting group Select", dropdown);
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText("08, Purchasing-Base Cost No Adds");
		
	}
	
	
	
	
	public void selectItemDepositGroupCode()
	{
		WebElement dropdown =driver.findElement(By.xpath("//*[text()='Item Deposit Group Code']/../div/div/select"));
		Select roundVATPostingGroupDown= new Select(dropdown);
		roundVATPostingGroupDown.selectByVisibleText("02, Keg");
		
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
	
	public void selectLocationCode()
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
		roundVATPostingGroupDown.selectByVisibleText("DZ01, Brewery Rouiba");
	}
	
	public void selectReplenishmentSystem()
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
		roundVATPostingGroupDown.selectByVisibleText("2, Transfer");
		
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
		Button.jsclick("Click Edit Site Button", driver.findElement(By.xpath("(//*[text()='Add'])[3]/../button[2]/span")), driver);
	}
}
	
	
