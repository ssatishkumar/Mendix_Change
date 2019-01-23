package com.mendix.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CreateMaterial {

	private static WebDriver driver;


	public static void main(String args[]) throws InterruptedException, IOException {

		//Kill all the task running in backgroud especially IE driver and UFT
		
		Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
		Runtime.getRuntime().exec("taskkill /T /F /IM UFT.exe");

		//1#HeiPort_Login#//

		//Launch the Browser
		WebDriver driver = browserLaunch();
		//Launch the URL
		urlLaunch(driver);
		//Login Page
		loginPageTest(driver);
		//Navigate to MDM workflow
		navigateMDMworkflow(driver);

		//#Material_Create_Fill in questionnaire#//
		//Swtich to Popup
		switchToPopup(driver);
		//Navigate to Create Material page
		createMaterialNavigate(driver);
		//Select Questionnaire for Type
		materialTypeSelectionTest(driver);
		//Disabling Local data
		locaDataDisableTest(driver);
		//Mention Description
		materialDescCreate(driver);
		//Material Group Selection
		materialGrpSelectionTest(driver);
		//Enter Gross Weight 
		grossWeightEntestTest(driver);
		//Enter UOM
		unitOfWeightSelectionTest(driver);
		//Enter Base UOM
		baseUOMSelectionTest(driver);
		//Enter Net Weight
		netWeightEnterTest(driver);
		//Enter UOM selection
		uomPrimarySelectionTest(driver);
		//Validate Request
		validateTestCreate(driver);
		//Submit Validate request
		submitGlobalRequestTest(driver);
		//Get Request Id
		String Id = getRequestId(driver);
		//Click Ok button

		//2#Process_Information Check#//
		
		//Navigate to Process Info Check
		WebDriverWait wait = navigateProcessInfoCheck(driver);
		//ReqId Search
		reqIdSearch(driver, Id, wait);
		//Get the Status
		getState(driver, wait);
		Thread.sleep(6000);
		//Close the Browser
		browserClose(driver);

		//3#Material_Create_Review Global Data - Approve GBDA#//
		
		//Launch the browser
		WebDriver driver2 = browserLaunch();
		//Launch the browser
		urlLaunch(driver2);
		Thread.sleep(5000);
		//Login to GBDA
		loginPageTest2(driver2);
		Thread.sleep(6000);
		//navigate to MDM workflow
		navigateMDMworkflow(driver2);
		Thread.sleep(3000);
		//switch to popup
		switchToPopup(driver2);
		//Navigate to My Tasks
		navigateToMyTasks(driver2);
		//Search the Request Id from My Task
		reqIdSearchMyTask(Id, driver2);
		//validate the Request
		validateTest(driver2);
		//Approve the request 
		approvalRequest(driver2);
		//Click the button
		okBtnClick(driver2);
		//Navigate to Process info search
		processInfoSearch(driver2);
		//Enter the request Id and search
		reqIdSearch(Id, driver2);
		//Check the status
		checkStatus(driver2);
		Thread.sleep(3000);
		//Close the browser
		browserClose(driver2);
		Thread.sleep(3000);
		
		
		//4#Material_Create_Review Global Data - Approve GDA#//
		
		WebDriver driver3 = browserLaunch();
		urlLaunch(driver3);
		Thread.sleep(3000);
		loginPageTest3(driver3);
		Thread.sleep(3000);
		navigateMDMworkflow(driver3);
		waitUntilObjectDisappears(driver3, By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		switchToPopup(driver3);
		waitUntilObjectDisappears(driver3, By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		navigateToMyTasks(driver3);
		waitUntilObjectDisappears(driver3, By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]")); 
		reqIdSearchMyTasks(Id, driver3);
		waitUntilObjectDisappears(driver3, By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]")); 
		approvalBtnClick(driver3);
		waitUntilObjectDisappears(driver3, By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]")); 
		duplicateCheck(driver3);
		waitUntilObjectDisappears(driver3, By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]")); 
		okBtnClick(driver3);
		waitUntilObjectDisappears(driver3, By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));

		//5#Process_Information Check_Syndication#//
		processInfoSearch(driver3);
		waitUntilObjectDisappears(driver3, By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));
		reqIdSearchGlobal(driver3, Id);
		String state3 = getStatus(driver3);
		System.out.println(state3);
		browserClose(driver3);

		//6#Check if record is checked in and Local ID is generated#//
		WebDriver driver4 = browserLaunch();
		urlLaunch(driver4);
		Thread.sleep(5000);
		loginPageTest(driver4);
		Thread.sleep(6000);
		navigateMDMworkflow(driver4);
		//switch to the popup
		switchToPopup(driver4);
		navigateToDashboard(driver4);
		Thread.sleep(3000);
		advancedSearch(driver4);
		scrolltoGlobalSearch(driver4);
		reqIdSearchGlobal(driver4, Id);
		Thread.sleep(8000);
		String globalId = getGlobalId(driver4);
		excelWrite(globalId);
		Thread.sleep(30000);
		browserClose(driver4);
		
		//7#Back end check#//
		//		launchUFT();
	}


	private static String getGlobalId(WebDriver driver4) {
		String globalId=driver4.findElement(By.xpath("(.//*[@id='mxui_widget_DataGrid_0']/div[3]/div/table[2]/tbody/tr/td[4]/div)[1]")).getText();
		System.out.println(globalId);
		return globalId;
	}


	private static void scrolltoGlobalSearch(WebDriver driver4) {
		JavascriptExecutor js;
		js = (JavascriptExecutor) driver4;
		js.executeScript("$(\".mx-layoutcontainer-wrapper.mx-scrollcontainer-wrapper\").animate({ scrollBottom: \"100px\" })");
	}


	private static String getStatus(WebDriver driver3) {
		WebElement stateElement=driver3.findElement(By.xpath("(//*[text()='State'])[2]/../../../../../../table[2]//td[9]/div"));
		waitForObject(driver3, stateElement);
		String state3=stateElement.getText();
		return state3;
	}


	private static void duplicateCheck(WebDriver driver3) {
		try {
			//	((JavascriptExecutor)driver3).executeScript("return jQuery('button:(*\'Confirm and Approve\'*)')");
			WebElement element5 = driver3.findElement(By.xpath("(.//*[@class='glyphicon glyphicon-ok'])[2]"));
			((JavascriptExecutor) driver3).executeScript("arguments[0].scrollIntoView(true);", element5);
			((JavascriptExecutor) driver3).executeScript("arguments[0].click();", element5);

			driver3.findElement(By.xpath("//*[text()='Proceed']")).click();
		}
		catch(Exception e) {
			System.err.println(e.getMessage());


		}
	}


	private static WebDriverWait navigateProcessInfoCheck(WebDriver driver) {
		Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(200)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		WebElement foo = gWait.until(new Function<WebDriver, WebElement>() 
				{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//a[contains(text(),'Process Information')]"));
			}
				});

		WebDriverWait wait = new WebDriverWait(driver,30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Process Information')]")));
		return wait;
	}


	private static void checkStatus(WebDriver driver2) {
		String state1=driver2.findElement(By.xpath("(//*[text()='State'])[2]/../../../../../../table[2]//td[9]")).getText();

		System.out.println(state1);
	}

	
	 public void waitForPageLoaded(WebElement element) {
	        ExpectedCondition<Boolean> expectation = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	                    }
	                };
	        try {
	            Thread.sleep(1000);
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            wait.until(expectation);
	        } catch (Throwable error) {
	            Assert.fail("Timeout waiting for Page Load Request to complete.");
	        }
	    }
	public static boolean waitForElementToBeClickable(WebDriver driver,WebElement element) {
		boolean blResult = false;
		try {
			long lngWaitTime = Long.parseLong("240");

			long lngWaitUnit=Long.parseLong("240");

			new WebDriverWait(driver, lngWaitTime * lngWaitUnit)
			.until(ExpectedConditions.elementToBeClickable(element));
			blResult = true;
		} catch (Exception e) {
			return blResult;
		}
		return blResult;
	}

	public static boolean waitUntilObjectDisappears(WebDriver driver,By by){
		boolean blResult=false;
		try{
			new WebDriverWait(driver,240).until(ExpectedConditions.invisibilityOfElementLocated(by));
		}
		catch(Exception e){}
		return blResult;

	}

	public static boolean waitForObject(WebDriver driver,WebElement element){
		boolean blResult=false;
		try{
			new WebDriverWait(driver,240).until(ExpectedConditions.visibilityOf(element));
		}
		catch(Exception e){}
		return blResult;

	}

	private static void excelWrite(String globalId)
			throws FileNotFoundException, IOException {
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\IBM_ADMIN\\Documents\\Documents\\Mendix_UFT\\Mendix_DataSheet.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//call the getSheet() method of Workbook and pass the Sheet Name here. 
			//In this case I have given the sheet name as “TestData” 
			//or if you use the method getSheetAt(), you can pass sheet number starting from 0. Index starts with 0.
			XSSFSheet sheet = workbook.getSheet("MDM");
			//XSSFSheet sheet = workbook.getSheetAt(0);
			//Now create a row number and a cell where we want to enter a value. 
			//Here im about to write my test data in the cell B2. It reads Column B as 1 and Row 2 as 1. Column and Row values start from 0.
			//The below line of code will search for row number 2 and column number 2 (i.e., B) and will create a space. 
			//The createCell() method is present inside Row class.
			//		Row row = sheet.c
			//		Cell cell = row.createCell(1);
			Cell cell = sheet.getRow(1).getCell(2);
			//Now we need to find out the type of the value we want to enter. 
			//If it is a string, we need to set the cell type as string 
			//if it is numeric, we need to set the cell type as number
			cell.setCellType(cell.CELL_TYPE_STRING);
			cell.setCellValue(globalId);
			FileOutputStream fos = new FileOutputStream("C:\\Users\\IBM_ADMIN\\Documents\\Documents\\Mendix_UFT\\Mendix_DataSheet.xlsx");
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param driver4
	 * @throws InterruptedException 
	 */
	private static void reqIdSearchGlobal(WebDriver driver4,String Id) throws InterruptedException {
		Thread.sleep(2000);
		WebElement elementSearch=driver4.findElement(By.cssSelector("#mxui_widget_NumberInput_0 :nth-child(1)"));
		waitForObject(driver4, elementSearch);
		elementSearch.sendKeys(Id);
		WebElement searchBtn=driver4.findElement(By.xpath("//*[text()='Search']"));
		waitForElementToBeClickable(driver4, searchBtn);
		searchBtn.click();
		Thread.sleep(2000);
	}

	/**
	 * @param driver4
	 * @throws InterruptedException
	 */
	private static void advancedSearch(WebDriver driver4)
			throws InterruptedException {

		Thread.sleep(30000);
		driver4.switchTo().window("Application");

		Thread.sleep(10000);

		driver4.findElement(By.xpath("//*[@class='glyphicon glyphicon-plus']")).click();

		Thread.sleep(3000);
		
	}

	/**
	 * @throws IOException
	 */
	private static void launchUFT() throws IOException {
		Runtime.getRuntime().exec("C:\\Users\\IBM_ADMIN\\Documents\\Documents\\Mendix_UFT\\AutoIT_UFTLaunch\\UFT_Launch.exe");
	}

	/**
	 * @param driver4
	 */
	private static void navigateToDashboard(WebDriver driver4) {

		waitUntilObjectDisappears(driver4, By.xpath(("//a[contains(text(),'Materials')]")));

		driver4.findElement(By.xpath("//a[contains(text(),'Materials')]")).click();

		driver4.findElement(By.xpath("(//*[starts-with(text(),' Dashboard')])[1]")).click();
	}

	/**
	 * @param driver3
	 * @throws InterruptedException
	 */
	private static void approvalBtnClick(WebDriver driver3)
			throws InterruptedException {
		driver3.findElement(By.xpath("//div[@class='mx-placeholder']/button")).click();
		driver3.findElement(By.xpath(".//*[@class='btn mx-button mx-name-actionButton11 btn-success']")).click();
		Thread.sleep(17000);
	}

	/**
	 * @param Id
	 * @param driver3
	 * @throws InterruptedException
	 */
	private static void reqIdSearchMyTasks(String Id, WebDriver driver3)
			throws InterruptedException {

		Thread.sleep(2000);

		driver3.switchTo().window("Application");


		WebElement elementt = driver3.findElement(By.xpath(".//*[text()='Search']"));
		waitForElementToBeClickable(driver3, elementt);
		JavascriptExecutor executorr1 = (JavascriptExecutor)driver3;
		executorr1.executeScript("arguments[0].click();", elementt);

		//		driver3.findElement(By.xpath("")).click();

		driver3.findElement(By.xpath("//label[text()='Request ID']/../../div[2]/input")).click();

		driver3.findElement(By.xpath("//label[text()='Request ID']/../../div[2]/input")).sendKeys(Id);

		driver3.findElement(By.xpath("(.//button[text()='Search'])[1]")).click();

		driver3.findElement(By.xpath(".//button[text()='Open Task']")).click();
	}

	/**
	 * @param driver3
	 * @throws InterruptedException
	 */
	private static void navigateToMyTasks(WebDriver driver3)
			throws InterruptedException {

		Thread.sleep(6000);
		WebElement element= driver3.findElement(By.xpath("//a[text()=' My Tasks']"));

		waitForObject(driver3, element);

		driver3.switchTo().window("Application");

		waitForElementToBeClickable(driver3, element);

		//		WebElement element = driver3.findElement(By.xpath("//a[text()=' My Tasks']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver3;
		executor.executeScript("arguments[0].click();", element);

		//		driver3.findElement(By.xpath("//a[contains(text(),' My Tasks')]")).click();

		Thread.sleep(2000);
	}

	/**
	 * @param Id
	 * @param driver2
	 * @throws InterruptedException
	 */
	private static void reqIdSearch(String Id, WebDriver driver2)
			throws InterruptedException {
		driver2.switchTo().window("Application");

		Thread.sleep(6000);

		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id^='mxui_widget_NumberInput_'][class^='mx-name-textBox2'] :nth-child(1)")));

		WebElement reqIdInput1 =driver2.findElement(By.cssSelector("div[id^='mxui_widget_NumberInput_'][class^='mx-name-textBox2'] :nth-child(1)"));

		reqIdInput1.click();

		reqIdInput1.clear();

		reqIdInput1.sendKeys(Id);


		driver2.findElement(By.xpath("//*[text()='Search']")).click();

		Thread.sleep(8000);
	}

	/**
	 * @param driver2
	 * @throws InterruptedException 
	 */
	private static void okBtnClick(WebDriver driver2) throws InterruptedException {


		WebElement el = driver2.findElement(By.cssSelector(".btn.btn-primary"));
		waitForElementToBeClickable(driver2, el);
		JavascriptExecutor executor = (JavascriptExecutor)driver2;
		executor.executeScript("arguments[0].click();", el);

	}

	/**
	 * @param driver2
	 * @throws InterruptedException
	 */
	private static void approvalRequest(WebDriver driver2)
			throws InterruptedException {
		Thread.sleep(6000);
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Approve Global Request']")));
		driver2.findElement(By.xpath(".//button[text()='Approve Global Request']")).click();
		Thread.sleep(8000);
	}

	/**
	 * @param driver2
	 * @throws InterruptedException
	 */
	private static void validateTest(WebDriver driver2)
			throws InterruptedException {
		Thread.sleep(7000);

		driver2.findElement(By.xpath("//div[@class='mx-placeholder']/button")).click();
		//		driver2.findElement(By.xpath("//div[@class='mx-placeholder']/button")).click();
		driver2.findElement(By.xpath("//*[text()='Validate']")).click();
	}

	private static void validateTestCreate(WebDriver driver2)
			throws InterruptedException {
		Thread.sleep(7000);

		driver2.findElement(By.xpath("//div[@class='mx-placeholder']/button")).click();
		driver2.findElement(By.xpath("//div[@class='mx-placeholder']/button")).click();
		driver2.findElement(By.xpath("//*[text()='Validate']")).click();
	}

	/**
	 * @param Id
	 * @param driver2
	 * @throws InterruptedException
	 */
	private static void reqIdSearchMyTask(String Id, WebDriver driver2)
			throws InterruptedException {

		Thread.sleep(2000);

		driver2.switchTo().window("Application");

		Thread.sleep(3000);

		WebElement elementtt = driver2.findElement(By.xpath(".//*[text()='Search']"));
		JavascriptExecutor executorr = (JavascriptExecutor)driver2;
		executorr.executeScript("arguments[0].click();", elementtt);

		driver2.findElement(By.xpath("//label[text()='Request ID']/../../div[2]/input")).click();

		driver2.findElement(By.xpath("//label[text()='Request ID']/../../div[2]/input")).sendKeys(Id);

		driver2.findElement(By.xpath("(.//button[text()='Search'])[1]")).click();

		driver2.findElement(By.xpath(".//button[text()='Open Task']")).click();
	}

	private static void navigateToMyTasks1(WebDriver driver2)
			throws InterruptedException {
		Thread.sleep(3000);

		driver2.switchTo().window("Application");
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//a[contains(text(),' My Tasks')]"))));

		Thread.sleep(2000);

		driver2.findElement(By.xpath("//a[contains(text(),' My Tasks')]")).click();

		Thread.sleep(2000);
	}

	private static void browserClose(WebDriver driver) {
		driver.close();
		driver.quit();
	}

	private static void getState(WebDriver driver, WebDriverWait wait)
			throws InterruptedException {
		Thread.sleep(8000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='State'])[2]/../../../../../../table[2]//td[9]")));

		checkStatus(driver);
	}

	private static void reqIdSearch(WebDriver driver, String Id,
			WebDriverWait wait) throws InterruptedException {
		driver.switchTo().window("Application");

		Thread.sleep(3000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id^='mxui_widget_NumberInput_'][class^='mx-name-textBox2'] :nth-child(1)")));

		WebElement reqIdInput =driver.findElement(By.cssSelector("div[id^='mxui_widget_NumberInput_'][class^='mx-name-textBox2'] :nth-child(1)"));

		reqIdInput.click();

		reqIdInput.clear();

		reqIdInput.sendKeys(Id);


		driver.findElement(By.xpath("//*[text()='Search']")).click();
	}

	private static void processInfoSearch(WebDriver driver)
			throws InterruptedException {

		Thread.sleep(3000);
		WebElement element=driver.findElement(By.xpath("//a[contains(text(),'Process Information')]"));
		waitForElementToBeClickable(driver, element);
		element.click();

		WebElement elementSearch=driver.findElement(By.xpath("(//*[starts-with(text(),' Process Search')])[1]"));
		waitForElementToBeClickable(driver, elementSearch);
		elementSearch.click();

		Thread.sleep(3000);
	}

	private static String getRequestId(WebDriver driver)
			throws InterruptedException {
		WebDriverWait wait3 = new WebDriverWait(driver,45);

		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p")));

		String reqId=driver.findElement(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p")).getText();

		String Id=reqId.replaceAll("[^0-9]", "");

		System.out.println("RequestId is: " + Id);

		Thread.sleep(2000);

		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		return Id;
	}

	private static void switchToPopup(WebDriver driver)
			throws InterruptedException {
		String mainWindowHandl = driver.getWindowHandle();
		//Switch to child window and close it

		for (String childWindowHandle : driver.getWindowHandles()) {
			//If window handle is not main window handle then close it 
			if(!childWindowHandle.equals(mainWindowHandl)){
				driver.switchTo().window(childWindowHandle);

			}
		} 

		waitUntilObjectDisappears(driver, By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));

		driver.manage().window().maximize();
	}

	private static void navigateMDMworkflow(WebDriver driver)
			throws InterruptedException {
		WebElement elem = driver.findElement(By.cssSelector("#anchor1"));
		mouseHoverJScript(elem, driver);
		WebDriverWait wai = new WebDriverWait(driver, 5);
		WebElement eleme = wai.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='MDM Workflow']")));
		eleme.click();
	}

	private static void urlLaunch(WebDriver driver) {
		driver.get("http://heiportacc.heiway.net:51900/irj/portal");
	}

	private static void processInfoCheck(WebDriver driver) throws InterruptedException {
		Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(200)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		WebElement foo = gWait.until(new Function<WebDriver, WebElement>() 
				{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//a[contains(text(),'Process Information')]"));
			}
				});


		driver.findElement(By.xpath("//a[contains(text(),'Process Information')]")).click();

		driver.findElement(By.xpath("(//*[starts-with(text(),' Process Search')])[1]")).click();

		Thread.sleep(2000);

		driver.switchTo().window("Application");


		//		String reqIdEnter=getReqId(driver);
		WebElement reqIdInput =driver.findElement(By.cssSelector("div[id^='mxui_widget_NumberInput_'][class^='mx-name-textBox2'] :nth-child(1)"));
		reqIdInput.click();
		reqIdInput.clear();
		reqIdInput.sendKeys(getReqId());


		driver.findElement(By.xpath("//*[text()='Search']")).click();

		Thread.sleep(3000);

		String state=driver.findElement(By.xpath(".//*[@id='mxui_widget_DataGrid_0']/div[3]/div/table[2]/tbody/tr/td[9]/div")).getText();

		System.out.println(state);

		Thread.sleep(3000);

		//		Runtime.getRuntime().exec("C:\\Users\\IBM_ADMIN\\Documents\\Documents\\Mendix_UFT\\AutoIT_UFTLaunch\\UFT_Launch.exe");


	}

	public static String getReqId() throws InterruptedException {

		WebDriver driver=browserLaunch();

		String reqId=driver.findElement(By.xpath(".//*[@id='mxui_widget_DialogMessage_0']/div[1]/div[2]/p")).getText();

		String Id=reqId.replaceAll("[^0-9]", "");

		System.out.println("RequestId is: " + Id);

		driver.findElement(By.cssSelector(".btn.btn-primary")).click();

		return Id;
	}

	private static void submitGlobalRequestTest(WebDriver driver) throws InterruptedException {

		/*Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(200)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		WebElement foo = gWait.until(new Function<WebDriver, WebElement>() 
		{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(".//button[text()='Submit Global Request']"));
			}
		});*/

		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Request complies to all Validations']")));
		driver.findElement(By.xpath(".//button[text()='Submit Global Request']")).click();


		//		driver.findElement(By.xpath(".//button[text()='Submit Global Request']")).click();
		Thread.sleep(8000);

		/*String[] parts = reqId.split(" ");
		 String id = parts[2];
		 int a=Integer.parseInt(id);
		 System.out.println(a);*/
		//		System.out.println(reqId);		
	}

	private static void approveGlobalRequestTest1(WebDriver driver) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Approve Global Request']")));
		driver.findElement(By.xpath(".//button[text()='Approve Global Request']")).click();
		Thread.sleep(8000);
	}

	private static void validateTest1(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		//		driver.findElement(By.xpath(".//*[@class='mx-layoutcontainer-wrapper mx-scrollcontainer-wrapper']/div[2]/button/span")).click();
		driver.findElement(By.xpath(".//*[@class='mx-layoutcontainer-wrapper mx-scrollcontainer-wrapper']/div[2]/button/span")).click();
		driver.findElement(By.xpath("//*[text()='Validate']")).click();
		Thread.sleep(6000);
	}

	private static void uomPrimarySelectionTest(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebElement myUOMSelectElement = driver.findElement(By.cssSelector("select[id^='mxui_widget_ReferenceSelector_28_input'][class='form-control']"));
		Select dropdownUOM= new Select(myUOMSelectElement);
		Thread.sleep(2000);
		dropdownUOM.selectByVisibleText("KG, kilogram, KG");
	}

	private static void netWeightEnterTest(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[text()='Net Weight Base UoM']/../div/input")).click();


		driver.findElement(By.xpath("//*[text()='Net Weight Base UoM']/../div/input")).sendKeys("1");
	}

	private static void baseUOMSelectionTest(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[text()='Base UoM']/../div/button/span")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[text()='Commercial Unit']/../../div[2]/input")).sendKeys("KG");

		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Search']")).click();


		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[contains(text(),'Display value')]/../../../../../../table[2]/tbody/tr[1]/td/div")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("(.//*[@class='mx-grid-toolbar'])[19]/button[1]")).click();
	}

	private static void unitOfWeightSelectionTest(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[text()='Unit of Weight']/../div/button/span")).click();

		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Commercial Unit']/../../div[2]/input")));

		driver.findElement(By.xpath("//label[text()='Commercial Unit']/../../div[2]/input")).sendKeys("KG");

		driver.findElement(By.xpath("//button[text()='Search']")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[contains(text(),'Display value')]/../../../../../../table[2]/tbody/tr[1]/td/div")).click();

		driver.findElement(By.xpath("(.//*[@class='mx-grid-toolbar'])[19]/button[1]")).click();
	}

	private static void grossWeightEntestTest(WebDriver driver) {
		driver.findElement(By.xpath("//*[text()='Gross Weight Base UoM']/../div/input")).click();


		driver.findElement(By.xpath("//*[text()='Gross Weight Base UoM']/../div/input")).sendKeys("1");
	}

	private static void materialGrpSelectionTest(WebDriver driver) throws InterruptedException {

		Thread.sleep(2000);
		/*Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(200)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		WebElement foo = gWait.until(new Function<WebDriver, WebElement>() 
		{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("//*[text()='Material Group']/../div/button/span"));
			}
		});*/

		driver.findElement(By.xpath("//*[text()='Material Group']/../div/button/span")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='mxui_widget_SearchInput_0']/div[2]/input")).click();

		driver.findElement(By.xpath(".//*[@id='mxui_widget_SearchInput_0']/div[2]/input")).clear();

		driver.findElement(By.xpath(".//*[@id='mxui_widget_SearchInput_0']/div[2]/input")).sendKeys("CMG0013");

		driver.findElement(By.xpath("//button[text()='Search']")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//div[contains(text(),'CMG')]/../../../../../../table[2]/tbody/tr[1]/td/div)[1]")).click();

		driver.findElement(By.xpath("(.//*[@class='mx-grid-toolbar'])[19]/button[1]")).click();
	}

	private static void materialDescCreate(WebDriver driver) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("(.//*[@class='glyphicon glyphicon-edit'])[1]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		//		switchToNewWindow(3, driver);
		Thread.sleep(1000);
		/*Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(200)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		WebElement foo = gWait.until(new Function<WebDriver, WebElement>() 
		{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div[id^='mxui_widget_TextInput_'][class*='col-sm-8'] :nth-child(1)"));
			}
		});*/

		WebDriverWait wait = new WebDriverWait(driver,30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id^='mxui_widget_TextInput_'][class*='col-sm-8'] :nth-child(1)")));


		/*WebElement mySelectElement = driver.findElement(By.cssSelector("select[id^='mxui_widget_ReferenceSelector_'][class='form-control']"));
		Select dropdown= new Select(mySelectElement);
		dropdown.selectByVisibleText("EN, English [US]");*/

		WebElement descField= driver.findElement(By.cssSelector("div[id^='mxui_widget_TextInput_'][class*='col-sm-8'] :nth-child(1)"));
		Thread.sleep(2000);

		Actions actions = new Actions(driver);
		actions.moveToElement(descField);
		actions.click();
		actions.perform();
		descField.sendKeys("Malte Pale ABC");



		driver.findElement(By.cssSelector("button[id^='mxui_widget_ActionButton'][class='btn mx-button mx-name-actionButton1 editableByCondition btn-success']")).click();
	}

	private static void locaDataDisableTest(WebDriver driver) {
		/*Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(400)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		WebElement foo = gWait.until(new Function<WebDriver, WebElement>() 
		{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//*[text()='Local Data']"));
			}
		});*/

		WebDriverWait wait = new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//*[text()='Local Data']"))));
		driver.findElement(By.xpath("//*[text()='Local Data']")).click();
		driver.findElement(By.xpath(".//*[@class='mx-layoutcontainer-wrapper mx-scrollcontainer-wrapper']/div[2]/button/span")).click();
		driver.findElement(By.xpath("//*[text()='Disable Local Request']")).click();
		driver.findElement(By.xpath("//*[text()='Proceed']")).click();
	}

	private static void materialTypeSelectionTest(WebDriver driver) throws InterruptedException {

		/*Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(200)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		WebElement foo = gWait.until(new Function<WebDriver, WebElement>() 
		{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//span[contains(text(),'Production | Raw material, ingredient or processing aid')]"));
			}
		});*/

		WebDriverWait wait = new WebDriverWait(driver,30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//span[contains(text(),'Production | Raw material, ingredient or processing aid')]"))));


		driver.findElement(By.xpath("//span[contains(text(),'Production | Raw material, ingredient or processing aid')]")).click();

		driver.findElement(By.xpath("//*[text()='Start Create']")).click();

		driver.switchTo().window("Application");

		Thread.sleep(3000);
	}

	private static void createMaterialNavigate(WebDriver driver) throws InterruptedException {
		/*Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(200)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		WebElement foo = gWait.until(new Function<WebDriver, WebElement>() 
		{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//a[contains(text(),'Materials')]"));
			}
		});*/

		WebDriverWait wait = new WebDriverWait(driver,30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//a[contains(text(),'Materials')]"))));

		driver.findElement(By.xpath("//a[contains(text(),'Materials')]")).click();

		driver.findElement(By.xpath("(//*[starts-with(text(),' Create')])[1]")).click();

		Thread.sleep(3000);
	}

	private static void navigateToMDMworkflow(WebDriver driver) throws InterruptedException {

		Thread.sleep(2000);
		Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(200)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
		WebElement foo = gWait.until(new Function<WebDriver, WebElement>() 
				{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("#anchor1"));
			}
				});

		/*	WebDriverWait wait = new WebDriverWait(driver,30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//a[text()='Work"))));
		 */
		Actions act = new Actions(driver);

		WebElement ele =driver.findElement(By.cssSelector("#anchor1"));

		act.moveToElement(ele);

		act.perform();

		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[text()='MDM Workflow']")).click();

		Thread.sleep(3000);

		//Get the main window handle

		switchToPopup(driver);
	}

	private static void loginPageTest(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='logonuidfield']")).click();
		driver.findElement(By.xpath(".//*[@id='logonuidfield']")).clear();
		driver.findElement(By.xpath(".//*[@id='logonuidfield']")).sendKeys("MDMM_BE01_LDR");
		Thread.sleep(2000);

		driver.findElement(By.xpath(".//*[@id='logonpassfield']")).click();
		driver.findElement(By.xpath(".//*[@id='logonpassfield']")).clear();
		driver.findElement(By.xpath(".//*[@id='logonpassfield']")).sendKeys("Heineken01");
		Thread.sleep(2000);

		driver.findElement(By.xpath(".//*[@id='logonForm']/table/tbody/tr[5]/td[2]/input")).click();

		Thread.sleep(2000);
	}

	private static void loginPageTest2(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='logonuidfield']")).click();
		driver.findElement(By.xpath(".//*[@id='logonuidfield']")).clear();
		driver.findElement(By.xpath(".//*[@id='logonuidfield']")).sendKeys("MDMM_GBDA");
		Thread.sleep(2000);

		driver.findElement(By.xpath(".//*[@id='logonpassfield']")).click();
		driver.findElement(By.xpath(".//*[@id='logonpassfield']")).clear();
		driver.findElement(By.xpath(".//*[@id='logonpassfield']")).sendKeys("Heineken01");
		Thread.sleep(2000);

		driver.findElement(By.xpath(".//*[@id='logonForm']/table/tbody/tr[5]/td[2]/input")).click();

		Thread.sleep(2000);
	}

	private static void loginPageTest3(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='logonuidfield']")).click();
		driver.findElement(By.xpath(".//*[@id='logonuidfield']")).clear();
		driver.findElement(By.xpath(".//*[@id='logonuidfield']")).sendKeys("MDM_GDA");

		driver.findElement(By.xpath(".//*[@id='logonpassfield']")).click();
		driver.findElement(By.xpath(".//*[@id='logonpassfield']")).clear();
		driver.findElement(By.xpath(".//*[@id='logonpassfield']")).sendKeys("Heineken01");

		driver.findElement(By.xpath(".//*[@id='logonForm']/table/tbody/tr[5]/td[2]/input")).click();

		Thread.sleep(2000);
	}

	private static final WebDriver browserLaunch() throws InterruptedException {
		File file = new File("C:\\Users\\IBM_ADMIN\\Downloads\\MDM_POC_Upgrade\\MDM_POC_Upgrade\\drivers\\IEDriverServer.exe");

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public static void switchToNewWindow(int windowNumber,WebDriver driver) throws InterruptedException {


		Set < String > s = driver.getWindowHandles();   
		Iterator < String > ite = s.iterator();
		int i = 1;
		while (ite.hasNext() && i < 10) {
			String popupHandle = ite.next().toString();
			driver.switchTo().window(popupHandle);
			System.out.println("Window title is : "+driver.getTitle());
			if (i == windowNumber) break;
			i++;
		}
	}
	public WebDriver getDriver() {
		return driver;
	}

	public void sleep(int seconds) 
	{
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {

		}
	}

	public static void mouseHoverJScript(WebElement HoverElement,WebDriver driver2) {


		try {
			if (isElementPresent(HoverElement)) {

				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver2).executeScript(mouseOverScript,
						HoverElement);

			} else {
				System.out.println("Element was not visible to hover " + "\n");

			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + HoverElement
					+ "is not attached to the page document"
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + HoverElement + " was not found in DOM"
					+ e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering"
					+ e.getStackTrace());
		}
	}

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


}



