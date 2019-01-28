package com.mendix.test;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mendix.tool.SharedDriver;
import com.mendix.util.DataProviderUtil.staticProviderClass;

public class VendorScript {
	
	WebDriver driver;

	@Test
	public void Vendor_Create_Fill_In_Questionnaire() throws InterruptedException
	{
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.vendorPage.switchToMDMPortal();
		SharedDriver.pageContainer.vendorPage.clickVendor();
		SharedDriver.pageContainer.vendorPage.createVendorNavigate();
		SharedDriver.pageContainer.vendorPage.VendorTypeSelection();
		SharedDriver.pageContainer.vendorPage.createButtonClick();
	}
/****************************************************************************************************/	
	@Test(dataProvider="Vendor_Create_Global_Disable_Bank_and_LocalData",dataProviderClass=staticProviderClass.class)
	public void Vendor_Create_Fill_In_Data(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException, AWTException 
	{
		SharedDriver.pageContainer.vendorPage.disableLocaData();
		SharedDriver.pageContainer.vendorPage.disableBankData();
		SharedDriver.pageContainer.vendorPage.VendorName(dataMap.get("Name1"));
		SharedDriver.pageContainer.vendorPage.AddressStreet("Hauptstrasse");//dataMap.get("Street")
		SharedDriver.pageContainer.vendorPage.AddresHouseNumber("11"); //dataMap.get("House number")
		SharedDriver.pageContainer.vendorPage.AddresPostalCode("2551"); //dataMap.get("Postal Code")
		SharedDriver.pageContainer.vendorPage.AddresCity("Enzesfeld-Lindabrunn");
		SharedDriver.pageContainer.vendorPage.ScrollDown();
		//'DropDowns
		SharedDriver.pageContainer.vendorPage.AddresCountry("AT, Austria"); //dataMap.get("Country")
		SharedDriver.pageContainer.vendorPage.AddresRegion("NOE, Lower Austria");  //dataMap.get("Region")
		SharedDriver.pageContainer.vendorPage.AddresLanguageKey("EN, English");
		SharedDriver.pageContainer.vendorPage.AddressCreditInformationNumber("301536819");
		SharedDriver.pageContainer.vendorPage.AddresIndustryKey("Y001, Trade/Serv/Transport");
		SharedDriver.pageContainer.vendorPage.AddresCorporateGroup("PR-PACK, PR-Packaging materials");
		SharedDriver.pageContainer.vendorPage.validateTestCreate();
		
		SharedDriver.pageContainer.vendorPage.submitGlobalRequestTest();
		SharedDriver.pageContainer.vendorPage.getRequestId();
				
	}

/****************************************************************************************************/
	@Test(dataProvider="Vendor_Create_Global_Disable_Bank_and_LocalData",dataProviderClass=staticProviderClass.class)
	public void Vendor_Create_Fill_In_Data_SavaASDraft(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException, AWTException 
	{
		SharedDriver.pageContainer.vendorPage.disableLocaData();
		SharedDriver.pageContainer.vendorPage.disableBankData();
		SharedDriver.pageContainer.vendorPage.VendorName(dataMap.get("Name1"));
		SharedDriver.pageContainer.vendorPage.AddressStreet("Hauptstrasse");//dataMap.get("Street")
		SharedDriver.pageContainer.vendorPage.AddresHouseNumber("11"); //dataMap.get("House number")
		SharedDriver.pageContainer.vendorPage.AddresPostalCode("2551"); //dataMap.get("Postal Code")
		SharedDriver.pageContainer.vendorPage.AddresCity("Enzesfeld-Lindabrunn");
		SharedDriver.pageContainer.vendorPage.ScrollDown();
		//'DropDowns
		SharedDriver.pageContainer.vendorPage.AddresCountry("AT, Austria"); //dataMap.get("Country")
		SharedDriver.pageContainer.vendorPage.AddresRegion("NOE, Lower Austria");  //dataMap.get("Region")
		SharedDriver.pageContainer.vendorPage.AddresLanguageKey("EN, English");
		SharedDriver.pageContainer.vendorPage.AddressCreditInformationNumber("301536819");
		SharedDriver.pageContainer.vendorPage.AddresIndustryKey("Y001, Trade/Serv/Transport");
		SharedDriver.pageContainer.vendorPage.AddresCorporateGroup("PR-PACK, PR-Packaging materials");
		SharedDriver.pageContainer.vendorPage.validateTestCreate();
		
		SharedDriver.pageContainer.vendorPage.SaveAsDraft();
		SharedDriver.pageContainer.vendorPage.getRequestId_Draft();
		SharedDriver.pageContainer.processInfoPage.browserClose();
				
	}
	
/****************************************************************************************************/	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Vendor_Create_Fill_in_Data_Save_as_Draft_Submit (Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.vendorPage.switchToMDMPortal();
		SharedDriver.pageContainer.processInfoPage.processInfoSearch();
		SharedDriver.pageContainer.processInfoPage.reqIdSearch_Global(dataMap.get("RequestId"));
		SharedDriver.pageContainer.processInfoPage.getState(dataMap.get("RequestId"));
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		SharedDriver.pageContainer.vendorPage.Localactionbutton();
		SharedDriver.pageContainer.vendorPage.validateTestCreate();
		SharedDriver.pageContainer.vendorPage.submitGlobalRequestTest();
		SharedDriver.pageContainer.vendorPage.submitRequestPopup();
		
	}
/****************************************************************************************************/	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Process_Information_Check(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		
		SharedDriver.pageContainer.processInfoPage.processInfoSearch();
		SharedDriver.pageContainer.processInfoPage.reqIdSearch_Global(dataMap.get("RequestId"));
		SharedDriver.pageContainer.processInfoPage.getState(dataMap.get("RequestId"));
		SharedDriver.pageContainer.processInfoPage.browserClose();
	}
/****************************************************************************************************/	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Vendor_Create_Review_Global_Data_Approve_GDA(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.vendorPage.switchToMDMPortal();
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		SharedDriver.pageContainer.materialApprovalPage.approvalBtnClick();
		SharedDriver.pageContainer.vendorPage.duplicateCheck();
	
	}
/****************************************************************************************************/	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Vendor_Create_Review_Global_Data_Reject_GDA(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException, AWTException 
	{
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.vendorPage.switchToMDMPortal();
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		SharedDriver.pageContainer.vendorPage.RejectGDA();
	}
	
/****************************************************************************************************/	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Create_Vendor_Rejections_with_Discard  (Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		try{
		System.out.println("Start:Create_Vendor_Rejections_with_Discard");

		SharedDriver.pageContainer.homePage.navigateToWorkflow();
		SharedDriver.pageContainer.vendorPage.switchToMDMPortal();
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		SharedDriver.pageContainer.vendorPage.DiscardCreateLDR();
		
		System.out.println("End:Create_Vendor_Rejections_with_Discard-Done");
		
	}catch(Exception e){
		
		System.out.println("Create_vendor_Rejections_with_Discard is not completed");
		driver.close();
	}
	}
	
/****************************************************************************************************/	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Vendor_Create_Syndication_Check (Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		SharedDriver.pageContainer.homePage.navigateToWorkflow();
		SharedDriver.pageContainer.vendorPage.switchToMDMPortal();
		SharedDriver.pageContainer.vendorPage.navigateToDashboard();
		SharedDriver.pageContainer.vendorPage.advancedSearch();
		SharedDriver.pageContainer.vendorPage.scrolltoGlobalSearch();
		SharedDriver.pageContainer.vendorPage.reqIdSearchGlobal(dataMap.get("RequestId"));
		SharedDriver.pageContainer.vendorPage.getGlobalId();
		SharedDriver.pageContainer.processInfoPage.browserClose();	
//		SharedDriver.pageContainer.materialApprovalPage.launchUFT();
	}
/****************************************************************************************************/	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Process_Information_Check_Discard (Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		System.out.println("Start:Process_Information_Check_Discard");
	try{
		SharedDriver.pageContainer.processInfoPage.processInfoSearch();
		SharedDriver.pageContainer.processInfoPage.reqIdSearch_Global(dataMap.get("RequestId"));
		SharedDriver.pageContainer.processInfoPage.getState(dataMap.get("RequestId"));
		SharedDriver.pageContainer.processInfoPage.ValidateStateDiscarded(dataMap.get("RequestId"));
		SharedDriver.pageContainer.processInfoPage.browserClose();
		System.out.println("Process_Information_Check_Discard-Done");
		
	}catch(Exception e){
		
		System.out.println("Process_Information_Check_Discard is not completed");
		driver.close();
	}
				
	}
/****************************************************************************************************/	
	@Test
	public void Vendor_Change_DashBoard() throws InterruptedException
	{
		System.out.println("test");
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.vendorPage.switchToMDMPortal();
		SharedDriver.pageContainer.vendorPage.clickVendor();
		SharedDriver.pageContainer.vendorPage.clickVendorDashboard();
	}
	
/****************************************************************************************************/	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Vendor_Change_DashBoard_SearchWith_GlobalID(Map<String,String> dataMap) throws InterruptedException
	{
		SharedDriver.pageContainer.vendorPage.SearchGlobalID(dataMap.get("GlobalId"));
		SharedDriver.pageContainer.vendorPage.GetFullVendorData();
		SharedDriver.pageContainer.vendorPage.EditVendorData();
	}
/****************************************************************************************************/	
	@Test(dataProvider="Vendor_Create_Global_Disable_Bank_and_LocalData",dataProviderClass=staticProviderClass.class)
	public void Vendor_Change_Fill_In_Data(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException, AWTException 
	{
		/*SharedDriver.pageContainer.vendorPage.disableLocaData();
		SharedDriver.pageContainer.vendorPage.disableBankData();*/
		SharedDriver.pageContainer.vendorPage.VendorName(dataMap.get("Name1"));
		SharedDriver.pageContainer.vendorPage.AddressStreet("Change Hauptstrasse");//dataMap.get("Street")
		SharedDriver.pageContainer.vendorPage.AddresHouseNumber("12"); //dataMap.get("House number")
		SharedDriver.pageContainer.vendorPage.AddresPostalCode("3551"); //dataMap.get("Postal Code")
		SharedDriver.pageContainer.vendorPage.AddresCity("Change Enzesfeld");
		SharedDriver.pageContainer.vendorPage.ScrollDown();
		//'DropDowns
		SharedDriver.pageContainer.vendorPage.AddresCountry("AT, Austria"); //dataMap.get("Country")
		SharedDriver.pageContainer.vendorPage.AddresRegion("NOE, Lower Austria");  //dataMap.get("Region")
		SharedDriver.pageContainer.vendorPage.AddresLanguageKey("EN, English");
		SharedDriver.pageContainer.vendorPage.AddressCreditInformationNumber("201536819");
		SharedDriver.pageContainer.vendorPage.AddresIndustryKey("Y001, Trade/Serv/Transport");
		SharedDriver.pageContainer.vendorPage.AddresCorporateGroup("PR-RAW, PR-Raw Materials");
		SharedDriver.pageContainer.vendorPage.BtnLocalActions();
		SharedDriver.pageContainer.vendorPage.validateTestCreate();
		
		SharedDriver.pageContainer.vendorPage.submitGlobalRequestTest();
		SharedDriver.pageContainer.vendorPage.getRequestId();
				
	}
}

