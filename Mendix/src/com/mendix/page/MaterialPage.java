package com.mendix.page;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mendix.tool.Button;
import com.mendix.tool.Constants;
import com.mendix.tool.Sync;
import com.mendix.tool.Textbox;
import com.mendix.util.ExcelUtil;

public class MaterialPage {

	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new home page changes
	 *
	 * @param driver the driver
	 */
	public MaterialPage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	@FindBy(how=How.XPATH, using="//a[contains(text(),'Materials')]")
	WebElement textMaterial;

	@FindBy(how=How.XPATH, using="(//*[starts-with(text(),' Create')])[1]")
	WebElement menuCreate;

	@FindBy(how=How.XPATH, using="//span[contains(text(),'Production | Raw material, ingredient or processing aid')]")
	WebElement btnMaterialTypeSelect;

	@FindBy(how=How.XPATH, using=".//div[@class='mx-referenceselector-input-wrapper']/select")
	WebElement slctOpCo;

	@FindBy(how=How.XPATH, using="//*[@class='glyphicon glyphicon-forward']")
	WebElement btnCreate;

	@FindBy(how=How.XPATH, using="//*[text()='Local Data']")
	WebElement textLocalData;

	@FindBy(how=How.XPATH, using=".//*[@class='mx-layoutcontainer-wrapper mx-scrollcontainer-wrapper']/div[2]/button/span")
	WebElement	btnLocalActions;

	@FindBy(how=How.XPATH, using="//*[text()='Disable Local Request']")
	WebElement	btnDisableLocalRequest;

	@FindBy(how=How.XPATH, using="//*[text()='Proceed']")
	WebElement	btnProceed;

	@FindBy(how=How.XPATH, using="(.//*[@class='glyphicon glyphicon-edit'])[1]")
	WebElement	btnEditDesc;

	@FindBy(how=How.CSS, using ="div[id^='mxui_widget_TextInput_'][class*='col-sm-8'] :nth-child(1)")
	WebElement txtboxDesc;


	@FindBy(how=How.CSS, using ="button[id^='mxui_widget_ActionButton'][class='btn mx-button mx-name-actionButton1 editableByCondition btn-success']")
	WebElement btnMaterialDescSave;

	@FindBy(how=How.XPATH, using="//*[text()='Material Group']/../div/button/span")
	WebElement btnMaterialGrpselection;

	@FindBy(how=How.XPATH, using=".//*[@id='mxui_widget_SearchInput_0']/div[2]/input")
	WebElement txtboxMaterialGrpSearch;

	@FindBy(how=How.XPATH, using="//button[text()='Search']")
	WebElement btnMaterialGrpSearch;

	@FindBy(how=How.XPATH, using="(//div[contains(text(),'CMG')]/../../../../../../table[2]/tbody/tr[1]/td/div)[1]")
	WebElement txtCMGSelect;

	@FindBy(how=How.XPATH, using="//button[text()='Select']")
	WebElement btnMaterialGroupSelect;

	@FindBy(how=How.XPATH, using="//*[text()='Gross Weight Base UoM']/../div/input")
	WebElement txtBoxGrossWeightUOM;

	@FindBy(how=How.XPATH, using="//*[text()='Unit of Weight']/../div/button/span")
	WebElement btnUnitofWeight;

	@FindBy(how=How.XPATH, using="//label[text()='Commercial Unit']/../../div[2]/input")
	WebElement txtboxUnitofWeightInput;

	@FindBy(how=How.XPATH, using="//button[text()='Search']")
	WebElement btnUnitofWeightSearch;

	@FindBy(how=How.XPATH, using="//div[contains(text(),'Display value')]/../../../../../../table[2]/tbody/tr[1]/td/div")
	WebElement txtUnitofWeightDisplay;

	@FindBy(how=How.XPATH, using=".//button[text()='Select']")
	WebElement btnUnitofWeightSelect;

	@FindBy(how=How.XPATH, using="//*[text()='Base UoM']/../div/button/span")

	WebElement btnBaseUOMSelection;

	@FindBy(how=How.XPATH, using="//label[text()='Commercial Unit']/../../div[2]/input")
	WebElement txtboxBaseUOM;

	@FindBy(how=How.XPATH, using="//button[text()='Search']")
	WebElement btnBaseUOMSearch;


	@FindBy(how=How.XPATH, using="//div[contains(text(),'Display value')]/../../../../../../table[2]/tbody/tr[1]/td/div")
	WebElement txtBaseUOMDisplay;

	@FindBy(how=How.XPATH, using="(//*[text()='Select'])[1]")
	WebElement btnBaseUOMSelect;

	@FindBy(how=How.XPATH, using="//*[text()='Net Weight Base UoM']/../div/input")
	WebElement txtboxNetWeight;

	@FindBy(how=How.XPATH, using=".//*[text()='UoM - Primary']/../div/div/select")
	WebElement slctUOMPrimary;

	@FindBy(how=How.XPATH, using="//*[text()='Validate']")
	WebElement btnValidate;

	@FindBy(how=How.XPATH, using="//div[@class='mx-placeholder']/button")
	WebElement btnlocalAction;

	@FindBy(how=How.XPATH, using=".//*[text()='Request complies to all Validations']")
	WebElement txtValidationMsg;




	//**********************************Global Actions*****************************************************************

	@FindBy(how=How.XPATH, using=".//button[text()='Submit Global Request']")
	WebElement btnGlobalRequest;

	@FindBy(how=How.XPATH, using=".//button[text()='Save As Draft']")
	WebElement btnSaveAsDraft;

	//*************************************************************************************************
	@FindBy(how=How.XPATH, using=".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p")
	WebElement msgRequestSuccess;

	@FindBy(how=How.CSS, using=".btn.btn-primary")
	WebElement btnMsgReqIdOk;

	@FindBy(how=How.XPATH, using="//*[text()='OK']")
	WebElement btnMsgReqIdOkdraft;

	@FindBy(how=How.XPATH, using="(//*[starts-with(text(),' Dashboard')])[1]")
	WebElement menuMaterialDashboard;


	@FindBy(how=How.XPATH, using="//*[@class='glyphicon glyphicon-plus']")
	WebElement btnAdvancedSearch;


	@FindBy(how=How.XPATH, using="//*[text()='Request ID']/../../td[4]/div/input")
	WebElement txtboxReqIdEnter;



	@FindBy(how=How.XPATH, using="//button[text()='Search']")
	WebElement btnReqIdEnter;

	/*@FindBy(how=How.XPATH, using="//*[text()='Created On']/../../tr[3]td[4]/div/div/div/input")
	WebElement txtboxCreateOnEnter;
*/	
	
	@FindBy(how=How.XPATH, using="//*[text()='Created On']/../../tr[4]td[4]/div/div/div/input")
	WebElement txtboxCreateOnEnter;
	

	@FindBy(how=How.XPATH, using="//button[text()='Get Full Material Data']")
	WebElement btnFullMaterailData;
	
	
	@FindBy(how=How.XPATH, using="//*[text()='Global ID']/../../td[4]/div/input")
	WebElement txtboxGlobalIdEnter; 
	/**
	 * Enter UserName.
	 * Enter Password
	 * 
	 * Click Login
	 *
	 * @param strMenuName the str menu name
	 * @return true, if successful
	 */

	public void  switchToPopup()
			throws InterruptedException {
		String mainWindowHandl = driver.getWindowHandle();
		for (String childWindowHandle : driver.getWindowHandles()) {
			if(!childWindowHandle.equals(mainWindowHandl)){
				driver.switchTo().window(childWindowHandle);
			}
		} 
		Sync.WaitForPageLoad(driver);
		Sync.waitForObject(driver, textMaterial);
		Sync.waitUntilObjectDisappears(driver, "Loading Indicator", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		driver.manage().window().maximize();
	}

	public boolean clickMaterial(String strPageName) throws InterruptedException{
		/*Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitForSeconds(Constants.WAIT_6);*/
		//Sync.waitForSeconds(Constants.WAIT_6);
		Sync.WaitForPageLoad(driver);
		if(Button.verifyObject(textMaterial)){
			Sync.waitForObject(driver ,"Materials", textMaterial);
			Button.NewmouseOver("Materials", driver, textMaterial);

			Sync.waitForSeconds(Constants.WAIT_1);		
			return Button.click(strPageName, Sync.waitForObject(driver, strPageName, By.xpath("//a[normalize-space(.)='"+strPageName+"']")));
		}else{
			Sync.waitForObject(driver ,"Materials", textMaterial);
			return Button.click(strPageName, Sync.waitForObject(driver, strPageName, By.xpath("//a[normalize-space(.)='"+strPageName+"']")));
		}
	}

	public boolean createMaterialNavigate() throws InterruptedException {

		if(Button.verifyObject(menuCreate)){
			Sync.waitForObject(driver ,"Create", menuCreate);
//			Sync.waitForSeconds(Constants.WAIT_1);		
			return Button.click("Create", menuCreate);
		}else{
			return Button.click("Create", menuCreate);
		}
	}

	public boolean materialTypeSelection() throws InterruptedException {

		/*Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitForSeconds(Constants.WAIT_3);*/
		/*Sync.waitForObject(driver ,"Material Type Select", btnMaterialTypeSelect);
		Sync.waitForSeconds(Constants.WAIT_6);		
		return Button.click("Material Type Select", btnMaterialTypeSelect);*/
		Sync.waitForObject(driver ,"Material Type Select", btnMaterialTypeSelect);
		if(Button.verifyObject(btnMaterialTypeSelect)){
			/*Sync.waitForObject(driver ,"Material Type Select", btnMaterialTypeSelect);
			Sync.waitForSeconds(Constants.WAIT_3);*/		
			return Button.click("Material Type Select", btnMaterialTypeSelect);
		}else{
			return Button.click("Material Type Selection", btnMaterialTypeSelect);
		}
	}

	public boolean createButtonClick() throws InterruptedException {

	/*	Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForSeconds(Constants.WAIT_3);*/
		Sync.waitForObject(driver ,"Create Button Click", btnCreate);
		if(Button.verifyObject(btnCreate)){
			Sync.waitForObject(driver ,"Create Button Click", btnCreate);
			Sync.waitForSeconds(Constants.WAIT_3);		
			return Button.click("Create Button Click", btnCreate);
		}else{
			return Button.click("Create Button Click", btnCreate);
		}

	}

	public boolean disableLocaData() {

//		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.WaitForPageLoad(driver);
		Sync.waitUntilObjectDisappears(driver, "Waiting of Create page to Load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, textLocalData);
		Button.click("Local Data", textLocalData);
		Button.click("Local Actions button", btnLocalActions);
		Button.click("Disable Local Request", btnDisableLocalRequest);
	    Button.click("Proceed", btnProceed);
		return Button.click("Local Actions button", btnLocalActions);
	}

	public boolean materialDescCreate(String strValue) throws InterruptedException {
		JavascriptExecutor js;
		js = (JavascriptExecutor) driver;
		js.executeScript("$(\".mx-layoutcontainer-wrapper.mx-scrollcontainer-wrapper\").animate({ scrollBottom: \"100px\" })");
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.WaitForPageLoad(driver);
		Sync.waitForObject(driver, btnEditDesc);
		Button.jsclick("Edit Description button Click", btnEditDesc, driver);
		Sync.waitForSeconds(Constants.WAIT_1);
		Sync.waitForObject(driver, txtboxDesc);
		Button.mouseClick(driver, "Click Material Desc Field", txtboxDesc);
		Textbox.enterValue("Enter Material Desc", txtboxDesc, strValue);
		return Button.click("Material Description Save", btnMaterialDescSave);
	}

	public boolean materialGrpSelectionTest(String strValue) throws InterruptedException {
		
		
		JavascriptExecutor js;
		js = (JavascriptExecutor) driver;
		js.executeScript("$(\".mx-layoutcontainer-wrapper.mx-scrollcontainer-wrapper\").animate({ scrollBottom: \"50px\" })");
		Sync.waitForElementToBeClickable(driver, btnMaterialGrpselection);
		Button.jsclick("Click Material Group Selection button", btnMaterialGrpselection, driver);
		Sync.waitForObject(driver, "Wait for Material Group Selection Text Box", txtboxMaterialGrpSearch);
		Textbox.click("Enter Material Group", txtboxMaterialGrpSearch);
		Textbox.clear("Enter Material Group", txtboxMaterialGrpSearch);
		Textbox.enterValue("Enter Material Group", txtboxMaterialGrpSearch, strValue);
		Button.click("Click Search Button", btnMaterialGrpSearch);
		Button.click("Click CMG Search result", txtCMGSelect);
		Sync.waitForObject(driver, "Wait for Material Group Selection Text Box", btnMaterialGroupSelect);
		return Button.click("Click Material Group Select", btnMaterialGroupSelect);

	}

	public boolean grossWeightEntestTest(String strValue) {

//		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForElementToBeClickable(driver, txtBoxGrossWeightUOM);
		Textbox.click("Click Gross Weight Base UOM", txtBoxGrossWeightUOM);
//		Sync.waitForSeconds(Constants.WAIT_1);
		Sync.waitForObject(driver, "Wait for Gross Weight Base UOM TextBox", txtBoxGrossWeightUOM);
		return Textbox.enterValue("Enter Gross Weight Base UOM", txtBoxGrossWeightUOM, strValue);
	}

	public boolean unitOfWeightSelectionTest(String strValue) throws InterruptedException {

		Sync.waitForElementToBeClickable(driver, btnUnitofWeight);
		Button.click("Click Unit of Weight selecction button", btnUnitofWeight);
		Sync.waitForObject(driver, "Wait for UOM popup", txtboxUnitofWeightInput);
		Textbox.enterValue("Enter Unit of Weight", txtboxUnitofWeightInput, strValue);
		Sync.waitForObject(driver, "Wait for UOM popup", btnUnitofWeightSearch);
		Button.click("Click Search Unit of Weight button", btnUnitofWeightSearch);
		Sync.waitForSeconds(Constants.WAIT_5);
	   /* Sync.waitForObjectFluent(driver, driver.findElement(By.xpath("//div[@class='mx-datagrid-head-caption' and text()='Commercial Unit']/../../../../../../table[2])")));
//		Sync.waitForElementToBeClickable(driver, driver.findElement(By.xpath("//*[text()='"+strValue+"']")));
		
	    Actions btnselect = new Actions(driver);
		btnselect.moveToElement(driver.findElement(By.xpath("//*[text()='"+strValue+"']")));
		btnselect.build();
		btnselect.perform();
		btnselect.click();*/
	    
	    
	    driver.findElement(By.xpath("//*[text()='"+strValue+"']")).click();
	    Sync.waitForSeconds(Constants.WAIT_5);
		return Button.click("Click Unit of Weight Select button", btnUnitofWeightSelect);

	}

	public void baseUOMSelectionTest(String strValue) throws InterruptedException {
		JavascriptExecutor js;
		js = (JavascriptExecutor) driver;
		js.executeScript("$(\".mx-layoutcontainer-wrapper.mx-scrollcontainer-wrapper\").animate({ scrollTop: \"60px\" })");

		Sync.waitForElementToBeClickable(driver, btnBaseUOMSelection);
		Sync.waitForSeconds(Constants.WAIT_5);
		Button.click("Click Base UOM selection button", btnBaseUOMSelection);
		Sync.waitForObject(driver, "Wait for Base UOM Text Box Enter" , txtboxBaseUOM);
		Textbox.enterValue("Enter Base UOM", txtboxBaseUOM, strValue);
		Sync.waitForElementToBeClickable(driver, btnBaseUOMSearch);
		Button.click("Click Base UOM Search button", btnBaseUOMSearch);
		Sync.waitForElementToBeClickable(driver, txtBaseUOMDisplay);
		Button.click("Click Base UOM Display", txtBaseUOMDisplay);
		Sync.waitForElementToBeClickable(driver, btnBaseUOMSelect);
		
//		Actions act = new Actions(driver);
//		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(child_menu));
//		act.moveToElement(main_menu).moveToElement(child_menu).click().build().perform();
		
		Actions btnselect = new Actions(driver);
		btnselect.moveToElement(btnBaseUOMSelect);
		btnselect.build();
		btnselect.perform();
		Button.click("Click Base UOM select button", btnBaseUOMSelect);
	}


	public boolean netWeightEnterTest(String strValue) throws InterruptedException {

//		Sync.waitForSeconds(Constants.WAIT_1);
		Sync.waitForElementToBeClickable(driver, txtboxNetWeight);
		Textbox.clear("Enter Net Weight", txtboxNetWeight);
		Button.click("Click Net Weight Enter TextBox", txtboxNetWeight);
		
		return Textbox.enterValue("Enter Net Weight", txtboxNetWeight, strValue);
	}

	public void uomPrimarySelectionTest() throws InterruptedException {
		/*JavascriptExecutor js;
		js = (JavascriptExecutor) driver;
		js.executeScript("$(\".mx-layoutcontainer-wrapper.mx-scrollcontainer-wrapper\").animate({ scrollBottom: \"100px\" })");*/
      
		//Sync.waitForObject(driver, "Wait for UOM Primary Select", slctUOMPrimary);
		System.out.println("Click drop down");
		Sync.waitForSeconds(Constants.WAIT_5);
		//driver.findElement(By.cssSelector(".//*[text()='UoM - Primary']/../div/div/select")).click();
		
		Select dropdownUOM= new Select(slctUOMPrimary);
		dropdownUOM.selectByIndex(1);
	}

	public boolean validateTestCreate() {
		Button.click("Local Actions button", btnLocalActions);
		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitForSeconds(Constants.WAIT_1);
		return Button.click("Click Validate", btnValidate);

	}

	public void submitGlobalRequestTest() throws InterruptedException {


		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForObject(driver, "Verify Validate message", txtValidationMsg);
		Button.click("Click Global submit Global Request", btnGlobalRequest);
		Sync.waitForSeconds(Constants.WAIT_2);
		Thread.sleep(8000);
	}

	public  String getRequestId()
			throws InterruptedException, FileNotFoundException, IOException {

		Sync.waitForSeconds(Constants.WAIT_3);
		String buttonColor = btnMsgReqIdOk.getCssValue("background-color");
        String buttonTextColor = btnMsgReqIdOk.getCssValue("color");
        System.out.println("Button color: " + buttonColor);
        System.out.println("Text color " + buttonTextColor);
//		Sync.waitUntilObjectDisappears(driver, "Waiting of Create page to Load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
//        Sync.waitForObjectFluent(driver, msgRequestSuccess);

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
		      return driver.findElement(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p"));
		   }
		 });
		    } catch (Exception e) {
		   }
		 
		//checking if loading indicator was found and if so we wait for it to
		//disappear
		 /* if (waitElement != null) {
		      WebDriverWait wait = new WebDriverWait(driver, 60);
		      wait.until(ExpectedConditions.visibilityOfElementLocated(
		    		  By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p"))
		    		           
		            );
		        }*/
		
		
        /*Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)                            
        		.withTimeout(Duration.ofSeconds(400))          
        		.pollingEvery(Duration.ofMillis(600))          
        		.ignoring(NoSuchElementException.class);
        
        WebElement msgRequestSuccess=wait.until(new Function<WebDriver, WebElement>() {       
        	public WebElement apply(WebDriver driver) { 
        		return driver.findElement(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p"));     
        		 }  
        		});*/
        		
        		
        System.out.println(msgRequestSuccess.getText());
		Sync.waitForObject(driver, "Wait of Dialog Box Success Message", msgRequestSuccess);
		String reqId=driver.findElement(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p")).getText();
		String[] parts = reqId.split(" ");
		String Id = parts[2];
		System.out.println("RequestId is: " + Id);
		ExcelUtil.setCellData("TestPlan","RequestId",5,Id);
//		Sync.waitForSeconds(Constants.OBJECT_WAIT_TIME);
		/*System.out.println(btnMsgReqIdOk.getCssValue("color"));
		Actions actions = new Actions(driver);
		actions.moveToElement(btnMsgReqIdOk);
		actions.perform();

		Button.click("Click Ok Button", btnMsgReqIdOk);*/
		return Id;
	}
	
	/*public void clickLocalAction()
	{
		Sync.waitForSeconds(Constants.WAIT_2);
		driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-flash']")).click();
	}*/
	
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
//		Sync.waitForElementToBeClickable(driver, driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-flash']")));
		driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-flash']")).click();
	}
	

	//***********************************************************************************

	public void SaveAsDraft() throws InterruptedException {


		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForObject(driver, "Verify Validate message", txtValidationMsg);
		Button.click("Click Save as Draft", btnSaveAsDraft);
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForSeconds(Constants.WAIT_6);
		//		Sync.waitForObjectFluent(driver, element)
	}

	public  String getRequestId_draft()
			throws InterruptedException, FileNotFoundException, IOException {
		
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
		      WebDriverWait wait = new WebDriverWait(driver, 30);
		      wait.until(ExpectedConditions.invisibilityOfElementLocated(
		    		  By.xpath(".//*[@id='mxui_widget_Progress_0']"))
		    		           
		            );
		        }

		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitForObject(driver, "Wait of Dialog Box Success Message", msgRequestSuccess);
		String reqId=driver.findElement(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p")).getText();
		String[] parts = reqId.split(" ");
		String Id = parts[17];
		String IdNum = Id.replaceAll("\\.", "");
		System.out.println("RequestId is: " + Id);
		ExcelUtil.excelWrite(IdNum);
		System.out.println("RequestId is: " + IdNum);
		Sync.waitForSeconds(Constants.WAIT_3);
//		Sync.waitForElementToBeClickable(driver, btnMsgReqIdOk);
		Button.click("Click Ok Button", btnMsgReqIdOk);
		return Id;
	}

	public  String getRequestId_Create()
			throws InterruptedException, FileNotFoundException, IOException {

		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitForObject(driver, "Wait of Dialog Box Success Message", msgRequestSuccess);
		String reqId=driver.findElement(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p")).getText();
		String[] parts = reqId.split(" ");
		String Id = parts[2];
		String IdNum = Id.replaceAll("\\.", "");
		System.out.println("RequestId is: " + Id);
		ExcelUtil.excelWrite(IdNum);
		System.out.println("RequestId is: " + IdNum);
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForElementToBeClickable(driver, btnMsgReqIdOkdraft);
//		Button.click("Click Ok Button", btnMsgReqIdOkdraft);
		return Id;
	}
	
	public boolean navigateToDashboard() {

		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		Sync.waitForObject(driver, "Wait until the Material appears", textMaterial);
		Button.click("Click Materials Menu", textMaterial);
		return Button.click("Click Dashboard Menu", menuMaterialDashboard);
	}

	public void advancedSearch() throws InterruptedException
	{

		TimeUnit.SECONDS.sleep(8);
		driver.switchTo().window("Application");
		TimeUnit.SECONDS.sleep(7);
		Button.click("Click Advanced Search", btnAdvancedSearch);
		TimeUnit.SECONDS.sleep(3);

	}

	public void scrolltoGlobalSearch() {
		JavascriptExecutor js;
		js = (JavascriptExecutor) driver;
		js.executeScript("$(\".mx-layoutcontainer-wrapper.mx-scrollcontainer-wrapper\").animate({ scrollBottom: \"100px\" })");
	}

	public  void reqIdSearchGlobal(String strValue) throws InterruptedException {
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForObject(driver, txtboxReqIdEnter);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		//get current date time with Date()
		Date date = new Date();

		// Now format the date
		String dateFormatted= dateFormat.format(date);
		Textbox.clear("Clear TextBox Value", txtboxReqIdEnter);
		Textbox.enterValue("Enter TextBox Value", txtboxReqIdEnter, strValue);
		Textbox.enterValue("Enter TextBox Value", txtboxCreateOnEnter, dateFormatted);
		Button.click("Click Search button", btnReqIdEnter);
		Sync.waitForSeconds(Constants.WAIT_2);	}
	public  void getCurrDate() throws InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		//get current date time with Date()
		Date date = new Date();

		// Now format the date
		String dateFormatted= dateFormat.format(date);
		

		Textbox.enterValue("Enter TextBox Value", txtboxCreateOnEnter, dateFormatted);
	}


	public  String getGlobalId() throws FileNotFoundException, IOException {
		Sync.waitForSeconds(Constants.WAIT_3);
		
//		Sync.waitForSeconds(Constants.WAIT_2);
		/*WebElement waitElement = null;
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
		      WebDriverWait wait = new WebDriverWait(driver, 30);
		      wait.until(ExpectedConditions.visibilityOfElementLocated(
		    		  By.cssSelector("tr > td.mx-name-column2.mx-right-aligned > div"))
		    		           
		            );
		        }*/
//		Sync.waitForObject(driver, "Wait for Global Material Id", driver.findElement(By.cssSelector("tr > td.mx-name-column2.mx-right-aligned > div")));
		String globalId=driver.findElement(By.cssSelector("tr > td.mx-name-column2.mx-right-aligned > div")).getText();
		System.out.println(globalId);
		ExcelUtil.excelWriteGlobalId(globalId);;
		return globalId;
	}

	public boolean clickFullMaterialData() {
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		Sync.waitForObject(driver, "Wait until the Material appears", btnFullMaterailData);
		return Button.click("Click Materials Menu", btnFullMaterailData);
	}

	public  String getMaterial_Number() throws FileNotFoundException, IOException {
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		String materialNum=driver.findElement(By.xpath("//*[text()='Material number']/../../../../../../table[2]/tbody/tr[2]/td[1]/div")).getText();
		System.out.println(materialNum);
		String numMaterial=materialNum.replaceFirst("^0+(?!$)", "");
		ExcelUtil.excelWriteMaterialNum(numMaterial);;
		return materialNum;
	}
	
	public void globalSearch(String strValue) throws InterruptedException {
		//Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_3);
		
		// Sync.waitForObject(driver, txtboxReqIdEnter);

		//Button.click("Click Search button", btnReqIdEnter);
		/*Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);

		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);*/
		//Sync.waitForSeconds(Constants.WAIT_5);

		Sync.waitForObject(driver, txtboxGlobalIdEnter);
		Sync.waitForSeconds(Constants.WAIT_5);
		//Sync.waitForSeconds(Constants.WAIT_3);
		

		//Textbox.clear("Clear TextBox Value", txtboxGlobalIdEnter);
		Textbox.enterValue("Enter TextBox Value", txtboxGlobalIdEnter, strValue);
		Sync.waitForSeconds(Constants.WAIT_5);
		Button.click("Click Search button", btnReqIdEnter);
		Sync.waitForSeconds(Constants.WAIT_5); } 
	
	



}