package com.mendix.test;


import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mendix.page.ProceesInfoPage;
import com.mendix.tool.Constants;
import com.mendix.tool.SharedDriver;
import com.mendix.util.DataProviderUtil.staticProviderClass;
import com.mendix.util.HelperUtil;
import com.mendix.util.ResultUtil;





public class LoginScript { 



	/**
	 * kill the task.
	 * @throws IOException 
	 */

	/*public WebDriver driver;

	public LoginScript(WebDriver driver){
		this.driver=driver;

	}*/

	@Test
	public static void tasKill() {
		ResultUtil.reporter.startTest("Kill All Running Task");
		try {
			Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
			Runtime.getRuntime().exec("taskkill /T /F /IM UFT.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Open browser.
	 */
	@Test
	public void openBrowser(){
		ResultUtil.reporter.startTest("Open Browser");
		SharedDriver.createDriver();
	}

	/**
	 * Login as LDR.
	 */


	@Test(dataProvider="HeiPort_Login",dataProviderClass=staticProviderClass.class)
	public void loginAsLDR(Map<String,String> dataMap){


		ResultUtil.reporter.startTest("Login As LDR");
		String opcoLogin= dataMap.get("OpCo");
		String Opco=opcoLogin.replaceAll("\\d","");
		String OpL=Opco.length() < 2 ? Opco : Opco.substring(0, 2);
		SharedDriver.pageContainer.loginPage.login("MDMM_"+OpL+"01_LDR","Heineken01");
	}

	@Test(dataProvider="HeiPort_Login",dataProviderClass=staticProviderClass.class)
	public void loginAsApprover(Map<String,String> dataMap){
		ResultUtil.reporter.startTest("Login As Next Level");
		String state = null;
		WebDriver driver= null;
		String opcoLogin= dataMap.get("OpCo");
		String Opco=opcoLogin.replaceAll("\\d","");
		String OpL=Opco.length() < 2 ? Opco : Opco.substring(0, 2);
		ProceesInfoPage myState = new ProceesInfoPage(driver);
		String stateInput= myState.state;
		if(stateInput.equalsIgnoreCase("GDA")) {
			Assert.assertTrue(SharedDriver.pageContainer.loginPage.login("MDM_GDA", "Heineken01"));
		}
		SharedDriver.pageContainer.loginPage.login("MDMM_"+OpL+"01_"+stateInput+"","Heineken01");
	}

	@Test(dataProvider="HeiPort_Login",dataProviderClass=staticProviderClass.class)
	public void loginAsLDS(Map<String,String> dataMap){
		ResultUtil.reporter.startTest("Login As LDS");
		String opcoLogin= dataMap.get("OpCo");
		String Opco=opcoLogin.replaceAll("\\d","");
		String OpL=Opco.length() < 2 ? Opco : Opco.substring(0, 2);
		SharedDriver.pageContainer.loginPage.login("MDMM_"+OpL+"01_LDS","Heineken01");
	}

	@Test
	public void loginAsLBDA(){
		ResultUtil.reporter.startTest("Login As LBDA");
		SharedDriver.pageContainer.loginPage.login("MDMM_"+HelperUtil.executionConfigMap.get(Constants.OPCO)+"01_LBDA","Heineken01");
	}

	@Test
	public void loginAsGBDA(){
		ResultUtil.reporter.startTest("Login As GBDA");
		//SharedDriver.createDriver();
		Assert.assertTrue(SharedDriver.pageContainer.loginPage.login("MDMM_GBDA", "Heineken01"));		

	}

	@Test
	public void loginAsGDA(){
		ResultUtil.reporter.startTest("Login As GBDA");
		//SharedDriver.createDriver();
		Assert.assertTrue(SharedDriver.pageContainer.loginPage.login("MDM_GDA", "Heineken01"));		

	}


	/**
	 * Sets the up.
	 *
	 * @param context the new up
	 */
	@BeforeSuite
	public void setup(ITestContext context )
	{
		System.out.println("===========BeforeSuite "+context.getSuite().getName());
		ResultUtil.reporter.config()
		.documentTitle(context.getSuite().getName())
		.reportHeadline(context.getSuite().getName())
		.useExtentFooter(false);	

	}

	/*@BeforeSuite
	public static void toDelete(){
		  try{
	           File file = new File("D:\\project\\result\\executionInfo.txt");

	            if (file.exists()) {
	            	  System.out.println("file exists");
	            	  file.delete();
	            	  System.out.println("file deleted");
	            	}else{
		                System.out.println("Delete operation is failed.");
		            }


	        }catch(Exception e){ 

	            }

	     }*/
}


