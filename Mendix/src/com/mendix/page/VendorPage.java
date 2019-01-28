package com.mendix.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.time.Duration;

import org.openqa.selenium.support.ui.FluentWait;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mendix.tool.Button;
import com.mendix.tool.Constants;
import com.mendix.tool.SharedDriver;
import com.mendix.tool.Sync;
import com.mendix.tool.Textbox;
import com.mendix.util.ExcelUtil;
import com.mendix.util.DataProviderUtil.staticProviderClass;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class VendorPage {
	
	/** The driver. */
	WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'Vendors')]")
	WebElement textVendor;
	
	@FindBy(how=How.XPATH, using="(//*[starts-with(text(),' Create')])[2]")
	WebElement menuCreateVendor;
	
	@FindBy(how=How.XPATH, using="(//*[starts-with(text(),' Dashboard')])[2]")
	WebElement menuDashboard;
	
	@FindBy(how=How.XPATH, using="//*[starts-with(text(),'Global ID')]/../../td[4]/div/input")
	WebElement GlobalIdSearch;
	
	@FindBy(how=How.XPATH, using="//*[starts-with(text(),'Header')]/../button[1]")
	WebElement GlobalIdSearchButton;
	
	@FindBy(how=How.XPATH, using="//button[text()='Get Full Vendor Data']")
	WebElement GetFullVendorData;
	
	@FindBy(how=How.XPATH, using="//span[contains(text(),'Y001, 3rd Party Vendor')]")
	WebElement btnvendorTypeSelect;
	
	@FindBy(how=How.XPATH, using="//*[@class='glyphicon glyphicon-forward']")
	WebElement btnCreate;
	
	@FindBy(how=How.XPATH, using="//*[text()='Local Data']")
	WebElement textLocalData;
	
	@FindBy(how=How.XPATH, using="//*[text()='Bank Data']")
	WebElement textBankData;

	@FindBy(how=How.XPATH, using=".//*[@class='glyphicon glyphicon-flash']")
	WebElement	btnLocalActions;

	@FindBy(how=How.XPATH, using="//*[text()='Disable Local Request']")
	WebElement	btnDisableLocalRequest;
	
	@FindBy(how=How.XPATH, using="//*[text()='Disable Bank Request']")
	WebElement	btnDisableBankRequest;

	@FindBy(how=How.XPATH, using="//*[text()='Proceed']")
	WebElement	btnProceed;
	
	//************** Filling create vendor data*************//
	
	@FindBy(how=How.XPATH, using=".//*[text()='Name 1']/../div/input")
	WebElement textName;
	
	@FindBy(how=How.XPATH, using=".//*[text()='Search Term 1']/../div/input")
	WebElement textSearchterm1;
	
	@FindBy(how=How.XPATH, using=".//*[text()='Street']/../div/input")
	WebElement textStreet;
	
	@FindBy(how=How.XPATH, using=".//*[text()='House Number']/../div/input")
	WebElement textHouseNumber;
	
	@FindBy(how=How.XPATH, using=".//*[text()='Postal Code']/../div/input")
	WebElement textPostalCode;
	
	@FindBy(how=How.XPATH, using=".//*[text()='City']/../div/input")
	WebElement textCity;
	
	@FindBy(how=How.XPATH, using=".//*[text()='Country']/../div/div/select")
	WebElement textCountry;
	
	@FindBy(how=How.XPATH, using=".//*[text()='Region']/../div/div/select")
	WebElement textRegion;
	
	@FindBy(how=How.XPATH, using=".//*[text()='Language Key']/../div/div/select")
	WebElement textLanguageKey;
	
	@FindBy(how=How.XPATH, using=".//*[text()='Credit Information Number']/../div/input")
	WebElement textCreditInformationNumber;
	
	@FindBy(how=How.XPATH, using=".//*[text()='Industry Key']/../div/div/select")
	WebElement textIndustryKey;
	
	@FindBy(how=How.XPATH, using=".//*[text()='Corporate Group']/../div/div/select")
	WebElement textCorporateGroup;
	
	@FindBy(how=How.XPATH, using=".//*[text()='Request complies to all Validations']")
	WebElement txtValidationMsg;
	
	//**********************************Global Actions*****************************************************************
	
	@FindBy(how=How.XPATH, using=".//button[text()='Submit Global Request']")
	WebElement btnGlobalRequest;
	
	@FindBy(how=How.XPATH, using=".//button[text()='Save As Draft']")
	WebElement btnSaveAsDraft;
	
	@FindBy(how=How.XPATH, using="//*[text()='Validate']")
	WebElement btnValidate;
	
	@FindBy(how=How.XPATH, using="//*[text()='Duplicate Check']")
	WebElement btnDuplicateCheck;
	
	@FindBy(how=How.XPATH, using="//*[text()='Reject Global Request']")
	WebElement btnRejectGlobalRequest;
	
	@FindBy(how=How.XPATH, using="//*[text()='Discard Create']")
	WebElement btnDiscardCreate;
	
	//****************************************************************************************************************
	
	@FindBy(how=How.XPATH, using=".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p")
	WebElement msgRequestSuccess;
	
	@FindBy(how=How.XPATH, using="//*[text()='No matches / possible duplicates have been found.']")
	WebElement msgDuplicateInfo;
	
	@FindBy(how=How.XPATH, using="//*[text()='OK']")
	WebElement btnOK;
	
	@FindBy(how=How.XPATH, using="(//*[text()='OK'])[2]")
	WebElement btnOK1;
	
	@FindBy(how=How.XPATH, using="//*[text()='Edit']")
	WebElement btnEdit;
	
	@FindBy(how=How.XPATH, using="//*[text()='Edit Global Data']/../div/input")
	WebElement EditGlobalData;
	//
		
	@FindBy(how=How.XPATH, using="//*[text()='Request ID']/../../td[4]/div/input")
	WebElement txtboxReqIdEnter;
	
	@FindBy(how=How.XPATH, using="(//*[starts-with(text(),' Dashboard')])[2]")
	WebElement menuMaterialDashboard;

	@FindBy(how=How.XPATH, using="//*[@class='glyphicon glyphicon-plus']")
	WebElement btnAdvancedSearch;

	@FindBy(how=How.XPATH, using="//button[text()='Search']")
	WebElement btnReqIdEnter;

	@FindBy(how=How.XPATH, using="//*[text()='Created On']/../../td[4]/div/div/div/input")
	WebElement txtboxCreateOnEnter;

	@FindBy(how=How.XPATH, using="//button[text()='Get Full Material Data']")
	WebElement btnFullMaterailData;
	
	@FindBy(how=How.CSS, using=".btn.btn-primary")
	WebElement btnMsgReqIdOk;
	
	//************ Reject Button action to give comments
	@FindBy(how=How.XPATH, using="(.//*[starts-with(@id,'uniqName') And text()='New'])[4]")
	WebElement btnCommentNew;
	
	@FindBy(how=How.XPATH, using="(.//*[starts-with(@id,'mxui_widget_TextArea')])[5]")
	WebElement textComment;
	
	@FindBy(how=How.XPATH, using="//*[text()='Save']")
	WebElement btnSave;

/**********************************************************************************************************
	/**
	 * Instantiates a new home page.
	 *
	 * @param driver the driver
	 */
	
	public VendorPage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
		
	}	
	
///********************* Methods for each Action ****************
/*****************************************************************************/
	
	public void  switchToMDMPortal()	throws InterruptedException 
	{
	String mainWindowHandl = driver.getWindowHandle();
	
	//Switch to child window and close it
		for (String childWindowHandle : driver.getWindowHandles()) 
		{
			//If window handle is not main window handle then close it 
			if(!childWindowHandle.equals(mainWindowHandl))
			{
					driver.switchTo().window(childWindowHandle);
			}
		} 
			
		Sync.waitUntilObjectDisappears(driver, "Loading Indicator", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, textVendor);
		driver.manage().window().maximize();
	}
		
/*****************************************************************************/
	public void clickVendor() throws InterruptedException
	{
	Sync.waitForSeconds(Constants.WAIT_6);
	Sync.waitForSeconds(Constants.WAIT_6);
	Sync.waitForObject(driver ,"Vendors", textVendor);
		Button.NewmouseOver("Vendors", driver, textVendor);
		Sync.waitForSeconds(Constants.WAIT_1);	
		Button.click("clicking on vendor link", textVendor);
		
	}
	
/*****************************************************************************/
	public boolean clickVendorDashboard() throws InterruptedException
	{
		if(Button.verifyObject(menuDashboard)){
			Sync.waitForObject(driver ,"Create", menuDashboard);
			Sync.waitForSeconds(Constants.WAIT_1);		
			return Button.click("Dashboard", menuDashboard);
		}else{
			return Button.click("Dashboard", menuDashboard);
		}
	}

/*****************************************************************************/
	public boolean createVendorNavigate() throws InterruptedException 
	{

		if(Button.verifyObject(menuCreateVendor)){
			Sync.waitForObject(driver ,"Create", menuCreateVendor);
			Sync.waitForSeconds(Constants.WAIT_1);		
			return Button.click("Create", menuCreateVendor);
		}else{
			return Button.click("Create", menuCreateVendor);
		}
	}
/*****************************************************************************/
	public boolean VendorTypeSelection() throws InterruptedException 
	{

		Sync.waitForSeconds(Constants.WAIT_6);
		if(Button.verifyObject(btnvendorTypeSelect)){
			Sync.waitForObject(driver ,"Material Type Select", btnvendorTypeSelect);
			Sync.waitForSeconds(Constants.WAIT_1);		
			return Button.click("Material Type Select", btnvendorTypeSelect);
		}else{
			return Button.click("Material Type Selection", btnvendorTypeSelect);
		}
	}
/*****************************************************************************/
	public boolean createButtonClick() throws InterruptedException 
	{

		Sync.waitForSeconds(Constants.WAIT_2);
		if(Button.verifyObject(btnCreate)){
			Sync.waitForObject(driver ,"Create Button Click", btnCreate);
			Sync.waitForSeconds(Constants.WAIT_1);		
			return Button.click("Create Button Click", btnCreate);
		}else{
			return Button.click("Create Button Click", btnCreate);
		}
		
	}
/*****************************************************************************/
	public void SearchGlobalID(String strValue) 
	{

		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitUntilObjectDisappears(driver, "Waiting of Create page to Load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, GlobalIdSearch);
		Button.click("Global ID text button", GlobalIdSearch);
		Textbox.enterValue("enter global id", GlobalIdSearch, strValue);
		Button.click("Global ID search button",GlobalIdSearchButton );
	}
	
/*****************************************************************************/
	public void GetFullVendorData() 
	{
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitUntilObjectDisappears(driver, "Waiting of Create page to Load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, GetFullVendorData);
		Button.click("GetFullVendorData", GetFullVendorData);
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitUntilObjectDisappears(driver, "Waiting of Create page to Load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Button.click("Click on OK", btnOK1);
		Sync.waitForSeconds(Constants.WAIT_1);
		Button.click("Click on OK", btnOK);
		Sync.waitForSeconds(Constants.WAIT_1);
	
	}
		
/*****************************************************************************/
	public void EditVendorData() 
	{
		Button.click("Select EDit button", EditGlobalData);
		Button.click("Click EDit button", btnEdit);
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForElementToBeClickable(driver, btnOK1);
		Button.click("Click on OK", btnOK1);
		Sync.waitForSeconds(Constants.WAIT_1);
		Button.click("Click on OK", btnOK);
		Sync.waitForSeconds(Constants.WAIT_1);
	}
/*****************************************************************************/
	public boolean disableLocaData() 
	{

		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitUntilObjectDisappears(driver, "Waiting of Create page to Load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, textLocalData);
		Button.click("Local Data", textLocalData);
		Button.click("Local Actions button", btnLocalActions);
		Button.click("Disable Local Request", btnDisableLocalRequest);
		return Button.click("Proceed", btnProceed);
	}
/*****************************************************************************/	
	public boolean disableBankData() 
	{

		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitUntilObjectDisappears(driver, "Waiting of Create page to Load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		Sync.waitForObject(driver, textBankData);
		Button.click("Local Data", textBankData);
		//Button.click("Local Actions button", btnLocalActions);
		Sync.waitForSeconds(Constants.WAIT_1);
		Button.click("Disable Bank Request", btnDisableBankRequest);
		return Button.click("Proceed", btnProceed);
	}
/*****************************************************************************/		

	public void VendorName(String strValue)
	{
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForObject(driver, textName);
		Textbox.enterValue("Name to create vendor",textName, strValue);
		Sync.waitForSeconds(Constants.WAIT_1);
		Textbox.click("clicking name1", textSearchterm1);		
	}
	
/********************Address section*********************************************************/		

	public void AddressStreet(String strValue)
	{
		Sync.waitForSeconds(Constants.WAIT_1);
		Sync.waitForObject(driver, textStreet);
		Textbox.enterValue("Enter Street",textStreet, strValue);	
	}
	
	public void AddresHouseNumber(String strValue)
	{
		Sync.waitForSeconds(Constants.WAIT_1);
		Sync.waitForObject(driver, textHouseNumber);
		Textbox.enterValue("Enter Street",textHouseNumber, strValue);	
	}
	
	public void AddresPostalCode(String strValue)
	{
//		Sync.waitForSeconds(Constants.WAIT_1);
		Sync.waitForObject(driver, textPostalCode);
		Textbox.enterValue("Enter Street",textPostalCode, strValue);	
	}
	
	public void AddresCity(String strValue)
	{
//		Sync.waitForSeconds(Constants.WAIT_1);
		Textbox.enterValue("Enter Street",textCity, strValue);	
	}
	
	public void AddresCountry(String strValue)
	{
//		Sync.waitForSeconds(Constants.WAIT_1);
		Select Country= new Select(textCountry);
	//	Country.selectByVisibleText(strValue);
//		 Sync.waitForSeconds(Constants.WAIT_1);
		 List <WebElement> elementCount = Country.getOptions();
		 int iSize = elementCount.size();
	//	 System.out.println(iSize);
		 for(int i =0; i<iSize ; i++)
		 {
			String sValue = elementCount.get(i).getText();
			if(sValue.equals(strValue))
			 {
			 Country.selectByIndex(i);
			 break;
			 }
		 }
	}
	
	public void AddresRegion(String strValue)
	{
		Sync.waitForSeconds(Constants.WAIT_1);
		Select region= new Select(textRegion);
		Sync.waitForSeconds(Constants.WAIT_1);
		region.selectByVisibleText(strValue);
	}
	
	public void AddresLanguageKey(String strValue)
	{
		Sync.waitForSeconds(Constants.WAIT_1);
		Select LanguageKey= new Select(textLanguageKey);
//		LanguageKey.selectByVisibleText(strValue);
		Sync.waitForSeconds(Constants.WAIT_1);
		LanguageKey.selectByVisibleText(strValue);
/*		 List <WebElement> elementCount = LanguageKey.getOptions();
		 int iSize = elementCount.size();
//		 System.out.println(iSize);
		 for(int i =0; i<iSize ; i++)
		 {
			String sValue = elementCount.get(i).getText();
			if(sValue.equals(strValue))
			 {
				LanguageKey.selectByIndex(i);
			 break;
			 }
		 }*/
	}
	
	public void AddressCreditInformationNumber(String strValue)
	{
		Sync.waitForSeconds(Constants.WAIT_1);
		Textbox.enterValue("Enter Credit No",textCreditInformationNumber, strValue);	
	}
	
	public void AddresIndustryKey(String strValue)
	{
		Sync.waitForSeconds(Constants.WAIT_1);
		Select IndustryKey= new Select(textIndustryKey);
//		IndustryKey.selectByVisibleText(strValue);
		Sync.waitForSeconds(Constants.WAIT_1);
		 List <WebElement> elementCount = IndustryKey.getOptions();
		 int iSize = elementCount.size();
		 System.out.println(iSize);
		 for(int i =0; i<iSize ; i++)
		 {
			String sValue = elementCount.get(i).getText();
			if(sValue.equals(strValue))
			 {
				IndustryKey.selectByIndex(i);
			 break;
			 }
		 }
	}
	
	public void AddresCorporateGroup(String strValue)
	{
		Sync.waitForSeconds(Constants.WAIT_1);
		Select CorporateGroup= new Select(textCorporateGroup);
//		CorporateGroup.selectByVisibleText(strValue);
		Sync.waitForSeconds(Constants.WAIT_1);
		 List <WebElement> elementCount = CorporateGroup.getOptions();
		 int iSize = elementCount.size();
		 System.out.println(iSize);
		 for(int i =0; i<iSize ; i++)
		 {
			String sValue = elementCount.get(i).getText();
			if(sValue.equals(strValue))
			 {
				CorporateGroup.selectByIndex(i);
			 break;
			 }
		 }
	}
	
	public void ScrollDown() throws InterruptedException, AWTException
	{
		Robot robot = new Robot();  // Robot class throws AWT Exception	
	    Thread.sleep(2000); // Thread.sleep throws InterruptedException	
	    robot.keyPress(KeyEvent.VK_PAGE_DOWN);
	    Thread.sleep(1000);
	    robot.keyRelease(KeyEvent.VK_PAGE_DOWN);  
	}
	
	public void Localactionbutton() {
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForObject(driver, btnLocalActions);
		Button.click("Local Actions button", btnLocalActions);
		Sync.waitForSeconds(Constants.WAIT_2);
	}
/********************END*********************************************************/	

	public boolean validateTestCreate() {

		Sync.waitForSeconds(Constants.WAIT_3);
		WebDriverWait wait=new WebDriverWait(driver, 60);
		Sync.waitForSeconds(Constants.WAIT_1);
		wait.until(ExpectedConditions.elementToBeClickable(btnValidate));
		return Button.click("Click Validate button on local action ", btnValidate);
	}
	
/****************************************************************************************************/	
	public boolean BtnLocalActions() {
		Sync.waitForSeconds(Constants.WAIT_1);
		return Button.click("Local Actions button", btnLocalActions);
	}
	
	
/****************************************************************************************************/
	public void submitGlobalRequestTest() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForObject(driver, "Verify Validate message", txtValidationMsg);
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(btnLocalActions));
		Button.click("Local Actions button", btnLocalActions);
		Sync.waitForSeconds(Constants.WAIT_2);
		Button.click("Local Actions button", btnLocalActions);
		Sync.waitForSeconds(Constants.WAIT_1);
		wait.until(ExpectedConditions.elementToBeClickable(btnGlobalRequest));
		Button.click("Click Global submit Global Request", btnGlobalRequest);
		Sync.waitForSeconds(Constants.WAIT_2);
		Thread.sleep(8000);
	}

/****************************************************************************************************/
	public void SaveAsDraft() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForObject(driver, "Verify Validate message", txtValidationMsg);
		WebDriverWait wait=new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(btnLocalActions));
		Button.click("Local Actions button", btnLocalActions);
		Sync.waitForSeconds(Constants.WAIT_2);
		Button.click("Local Actions button", btnLocalActions);
		Sync.waitForSeconds(Constants.WAIT_1);
		wait.until(ExpectedConditions.elementToBeClickable(btnSaveAsDraft));
		Button.click("Click Save as Draft button", btnSaveAsDraft);
		Sync.waitForSeconds(Constants.WAIT_2);
		Thread.sleep(8000);
	}
/****************************************************************************************************/	
	public  String getRequestId() throws InterruptedException, FileNotFoundException, IOException {
		
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS) ;
		Sync.waitForObject(driver, "Wait of Dialog Box Success Message", msgRequestSuccess);
		Sync.waitForSeconds(Constants.WAIT_3);
		
		WebDriverWait wait=new WebDriverWait(driver, 60);
//		WebElement text = driver.findElement(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p"));
//		wait.until(ExpectedConditions.elementToBeSelected(text));
		String reqId=driver.findElement(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p")).getText();
		String[] parts = reqId.split(" ");
		String Id = parts[2];
		System.out.println("RequestId is: " + Id);
		ExcelUtil.excelWrite(Id);
		System.out.println("Excel write is done");
		wait.until(ExpectedConditions.elementToBeClickable(btnOK));
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForElementToBeClickable(driver, btnOK);
		
		Button.click("Click Ok Button", btnOK);
		return Id;
	}
	
/****************************************************************************************************/	
	public  void submitRequestPopup() {
		
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS) ;
		Sync.waitForObject(driver, "Wait of Dialog Box Success Message", msgRequestSuccess);
		Sync.waitForSeconds(Constants.WAIT_3);
		
		WebDriverWait wait=new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(btnOK));
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForElementToBeClickable(driver, btnOK);
		
		Button.click("Click Ok Button", btnOK);
	}
		
	/****************************************************************************************************/	
	public  String getRequestId_Draft() throws InterruptedException, FileNotFoundException, IOException {
		
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS) ;
		Sync.waitForObject(driver, "Wait of Dialog Box Success Message", msgRequestSuccess);
		Sync.waitForSeconds(Constants.WAIT_3);
		
		WebDriverWait wait=new WebDriverWait(driver, 60);
//		WebElement text = driver.findElement(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p"));
//		wait.until(ExpectedConditions.elementToBeSelected(text));
		String reqId=driver.findElement(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p")).getText();
		String[] parts = reqId.split(" ");
		String Id = parts[16];
		String Idnum = Id.replaceAll("\\.", "");
		System.out.println("RequestId is: " + Idnum);
		
		ExcelUtil.excelWrite(Idnum);
		System.out.println("Excel write is done");
		wait.until(ExpectedConditions.elementToBeClickable(btnOK));
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForElementToBeClickable(driver, btnOK);
		
		Button.click("Click Ok Button", btnOK);
		return Id;
	}
/****************************************************************************************************/	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Vendor_Create_Review_Global_Data_Approve_GDA(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.materialPage.switchToPopup();
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		SharedDriver.pageContainer.materialApprovalPage.approvalBtnClick();
	}
	
/****************************************************************************************************/	
	public boolean navigateToDashboard() {

		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		Sync.waitForObject(driver, "Wait until the vendor appears", textVendor);
		Button.click("Click Materials Menu", textVendor);
		return Button.click("Click Dashboard Menu", menuMaterialDashboard);
	}
	
/****************************************************************************************************/
	public void advancedSearch() throws InterruptedException
	{

		TimeUnit.SECONDS.sleep(8);
		driver.switchTo().window("Application");
		TimeUnit.SECONDS.sleep(7);
		Button.click("Click Advanced Search", btnAdvancedSearch);
		TimeUnit.SECONDS.sleep(3);
	}
	
/****************************************************************************************************/
	public void scrolltoGlobalSearch() {
		JavascriptExecutor js;
		js = (JavascriptExecutor) driver;
		js.executeScript("$(\".mx-layoutcontainer-wrapper.mx-scrollcontainer-wrapper\").animate({ scrollBottom: \"100px\" })");
	}

/****************************************************************************************************/	
	public  void reqIdSearchGlobal(String strValue) throws InterruptedException {
		Sync.waitForSeconds(Constants.WAIT_2);
		WebElement elementSearch=driver.findElement(By.xpath("//*[text()='Request ID']/../../td[4]/div/input"));
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
	
/****************************************************************************************************/
	public  String getGlobalId() throws FileNotFoundException, IOException {
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForObject(driver, "Wait for Global Vendor Id", driver.findElement(By.xpath("//*[text()='Global Vendor ID']/../../../../../../table[2]/tbody/tr/td[4]/div")));
		String globalId=driver.findElement(By.xpath("//*[text()='Global Vendor ID']/../../../../../../table[2]/tbody/tr/td[4]/div")).getText();
		System.out.println(globalId);
		ExcelUtil.excelWriteGlobalId(globalId);;
		return globalId;
	}

/****************************************************************************************************/
	public void duplicateCheck() {
		try {
						
			WebElement waitElement = null;
			FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMinutes(3))
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
			    		  By.xpath(".//*[text()='Open Record']"))
			            );
			        }

			driver.manage().window().setPosition(new Point(-2000, 0)) ;
			driver.findElement(By.xpath(".//*[text()='Open Record']")).sendKeys(Keys.TAB);
			driver.findElement(By.xpath(".//*[text()='Export to Excel']")).sendKeys(Keys.TAB);
			driver.findElement(By.xpath("//*[text()='Confirm and Approve']")).sendKeys(Keys.TAB);
			driver.findElement(By.xpath("//*[text()='Confirm and Approve']")).sendKeys(Keys.RETURN);
			driver.findElement(By.xpath("//*[text()='Proceed']")).click();
			Sync.waitForSeconds(Constants.WAIT_3);


			driver.manage().window().maximize();
			Actions actions = new Actions(driver);
			actions.moveToElement(btnMsgReqIdOk);
			actions.perform();

			Button.click("Click Ok Button", btnMsgReqIdOk);

			
		}
		catch(Exception e) {
			System.err.println(e.getMessage());


		}
	}
	
	public void RejectGDA() throws InterruptedException, AWTException {

		Thread.sleep(3000);
		System.out.println("Scrolling");
		Robot robot = new Robot();  // Robot class throws AWT Exception	
        Thread.sleep(1000); // Thread.sleep throws InterruptedException	
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_DOWN);
        
        Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(1000);
	
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(1000);
		
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(1000);
		
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(1000);
        
		Sync.waitForObject(driver, btnCommentNew);
		Thread.sleep(5000);
		System.out.println("checking for new button");
		
		driver.findElement(By.xpath("(.//*[@class='btn mx-button mx-name-newButton2 btn-default'])[1]")).click();
		
		System.out.println("clicked new button");
		Thread.sleep(2000);
		
/*		Button.jsclick("Click Comment Text Boc", textComment, driver);
//		Textbox.jsEnterValue("Enter Comments ", driver, textComment, "Material data");
		Thread.sleep(2000);
//		WebElement txtArea=driver.findElement(By.cssSelector("#mxui_widget_TextArea_2_input"));
//		 ((JavascriptExecutor) driver).executeScript("document.getElementById('mxui_widget_TextArea_3_input').focus");
		 String script ="arguments[0].setAttribute('value','Set to this text.')";
		 ((JavascriptExecutor) driver).executeScript(script,textComment);
//		Textbox.enterValue("typing comment", txtArea, "material data");
*/		 
		
		Thread.sleep(2000);
		Textbox.enterValue("typing comment", textComment, "Vendor data");
		Textbox.click("Click on Save Button", btnSave);
		Thread.sleep(2000);
		Button.click("Local Actions button click", btnLocalActions);
		Thread.sleep(2000);
		Sync.waitForObject(driver, btnRejectGlobalRequest);
		Textbox.click("Click on reject button in locl action", btnRejectGlobalRequest);
		Thread.sleep(3000);
		Sync.waitForObject(driver, btnOK);
		Button.click("Click On OK button", btnOK);
		Thread.sleep(2000);
	}
	
	public void DiscardCreateLDR() throws InterruptedException {

		Thread.sleep(6000);
		Button.click("Local Actions button click", btnLocalActions);
		Thread.sleep(2000);
		Sync.waitForObject(driver, btnDiscardCreate);
		Textbox.click("Click on reject button in locl action", btnDiscardCreate);
		Thread.sleep(4000);
		Sync.waitForObject(driver, btnOK);
		Button.click("Click On OK button", btnOK);
		Thread.sleep(2000);
	}
	
	
	}

