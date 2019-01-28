package com.mendix.test;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mendix.tool.SharedDriver;
import com.mendix.util.DataProviderUtil.staticProviderClass;

public class VendorScript_NAV {
	
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
	public void Vendor_Create_Fill_In_Data_Global_Local_NAV(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException, AWTException 
	{
		SharedDriver.pageContainer.vendorPageNAV.disableBankData_NAV();
		SharedDriver.pageContainer.vendorPageNAV.VendorName(dataMap.get("Name1"));
		SharedDriver.pageContainer.vendorPageNAV.AddressStreet("kite");//dataMap.get("Street")
		SharedDriver.pageContainer.vendorPageNAV.AddresHouseNumber("11"); //dataMap.get("House number")
		SharedDriver.pageContainer.vendorPageNAV.AddresPostalCode("56478"); //dataMap.get("Postal Code")
		SharedDriver.pageContainer.vendorPageNAV.AddresCity("holland");
		SharedDriver.pageContainer.vendorPageNAV.ScrollDown();
		//'DropDowns
		SharedDriver.pageContainer.vendorPageNAV.AddresCountry("DZ, Algeria"); //dataMap.get("Country")
		SharedDriver.pageContainer.vendorPageNAV.AddresLanguageKey("EN, English");
		SharedDriver.pageContainer.vendorPageNAV.AddressCreditInformationNumber("465786234");
		SharedDriver.pageContainer.vendorPageNAV.AddresIndustryKey("Y001, Trade/Serv/Transport");
		SharedDriver.pageContainer.vendorPageNAV.AddresCorporateGroup("NPR-BS, NPR-Business Services");
		
		SharedDriver.pageContainer.vendorPageNAV.ClickLocaData_NAV();
		SharedDriver.pageContainer.vendorPageNAV.ClickFinaceNew(); //Clicking on New Button
	//	SharedDriver.pageContainer.vendorPageNAV.ScrollDown();
		SharedDriver.pageContainer.vendorPageNAV.VendorPostingGroup("ICV-Trade, IC Vendor Trade");
		SharedDriver.pageContainer.vendorPageNAV.VendorVATPostingGroup("3PV-Tax, 3rd party vendors Tax");
		SharedDriver.pageContainer.vendorPageNAV.VendorGenPostingGroup("3PV_IMPORT, 3rd Party Vendor Trade - Import");
		SharedDriver.pageContainer.vendorPageNAV.validateLocalTestCreate();
		SharedDriver.pageContainer.vendorPageNAV.LocalFinanceSave();
		
		SharedDriver.pageContainer.vendorPageNAV.ClickPurchasingNew();
		SharedDriver.pageContainer.vendorPageNAV.PaymentTermsCode("V004, Payment within 10 days");
		SharedDriver.pageContainer.vendorPageNAV.PaymentMethodCode("CASH ORDER, Cashier Order");
		SharedDriver.pageContainer.vendorPageNAV.validateLocalTestCreate();
		SharedDriver.pageContainer.vendorPageNAV.LocalFinanceSave();
	
		SharedDriver.pageContainer.vendorPageNAV.submitGlobalLocalRequest();
		SharedDriver.pageContainer.vendorPageNAV.getRequestId();
				
	}

/****************************************************************************************************/	
	@Test(dataProvider="Vendor_Create_Global_Disable_Bank_and_LocalData",dataProviderClass=staticProviderClass.class)
	public void Vendor_Create_Fill_In_Data_Global_Local_Bank_NAV(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException, AWTException 
	{
	//	SharedDriver.pageContainer.vendorPageNAV.disableBankData_NAV();
		SharedDriver.pageContainer.vendorPageNAV.VendorName(dataMap.get("Name1"));
		SharedDriver.pageContainer.vendorPageNAV.AddressStreet("kite");//dataMap.get("Street")
		SharedDriver.pageContainer.vendorPageNAV.AddresHouseNumber("11"); //dataMap.get("House number")
		SharedDriver.pageContainer.vendorPageNAV.AddresPostalCode("56478"); //dataMap.get("Postal Code")
		SharedDriver.pageContainer.vendorPageNAV.AddresCity("holland");
		SharedDriver.pageContainer.vendorPageNAV.ScrollDown();
		//'DropDowns
		SharedDriver.pageContainer.vendorPageNAV.AddresCountry("DZ, Algeria"); //dataMap.get("Country")
		SharedDriver.pageContainer.vendorPageNAV.AddresLanguageKey("EN, English");
		SharedDriver.pageContainer.vendorPageNAV.AddressCreditInformationNumber("465786234");
		SharedDriver.pageContainer.vendorPageNAV.AddresIndustryKey("Y001, Trade/Serv/Transport");
		SharedDriver.pageContainer.vendorPageNAV.AddresCorporateGroup("NPR-BS, NPR-Business Services");
		
		SharedDriver.pageContainer.vendorPageNAV.ClickBankData_NAV();
		
		SharedDriver.pageContainer.vendorPageNAV.ClickBankDetailsNew();
		SharedDriver.pageContainer.vendorPageNAV.VendorBankCountry("DZ, Algeria");
		SharedDriver.pageContainer.vendorPageNAV.SelectBankKey("002");
		SharedDriver.pageContainer.vendorPageNAV.VendorCurrencyCode("DZD, Algerian Dinar");
		SharedDriver.pageContainer.vendorPageNAV.VendorPatnerBankType("DE01");
				
		SharedDriver.pageContainer.vendorPageNAV.ClickLocaData_NAV();
		SharedDriver.pageContainer.vendorPageNAV.ClickFinaceNew(); 
	//	SharedDriver.pageContainer.vendorPageNAV.ScrollDown();
		SharedDriver.pageContainer.vendorPageNAV.VendorPostingGroup("ICV-Trade, IC Vendor Trade");
		SharedDriver.pageContainer.vendorPageNAV.VendorVATPostingGroup("3PV-Tax, 3rd party vendors Tax");
		SharedDriver.pageContainer.vendorPageNAV.VendorGenPostingGroup("3PV_IMPORT, 3rd Party Vendor Trade - Import");
		SharedDriver.pageContainer.vendorPageNAV.validateLocalTestCreate();
		SharedDriver.pageContainer.vendorPageNAV.LocalFinanceSave();
		
		SharedDriver.pageContainer.vendorPageNAV.ClickPurchasingNew();
		SharedDriver.pageContainer.vendorPageNAV.PaymentTermsCode("V004, Payment within 10 days");
		SharedDriver.pageContainer.vendorPageNAV.PaymentMethodCode("CASH ORDER, Cashier Order");
		SharedDriver.pageContainer.vendorPageNAV.validateLocalTestCreate();
		SharedDriver.pageContainer.vendorPageNAV.LocalFinanceSave();
	
		SharedDriver.pageContainer.vendorPageNAV.submitGlobalLocalRequest();
		SharedDriver.pageContainer.vendorPageNAV.getRequestId();
		
		SharedDriver.pageContainer.vendorPageNAV.submitBankRequest();
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
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Vendor_Create_Review_Local_Data_Approve_LDS(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.vendorPage.switchToMDMPortal();
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		SharedDriver.pageContainer.vendorPageNAV.markViewsBtnClick_Local();
		SharedDriver.pageContainer.vendorPageNAV.submitRequestPopup_NAV();
		SharedDriver.pageContainer.vendorPageNAV.approveLocalRequest();
		SharedDriver.pageContainer.vendorPageNAV.submitRequestPopup_NAV();
		/*SharedDriver.pageContainer.vendorPage.duplicateCheck();*/	
	}
	
/***************************************************************************************************/	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Vendor_Create_Review_Local_Data_Reject_LDS(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException, AWTException 
	{
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.vendorPage.switchToMDMPortal();
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		SharedDriver.pageContainer.vendorPageNAV.RejectLocaRequestLDS();
	}
	
/****************************************************************************************************/	
	@Test(dataProvider="Vendor_Create_Global_Disable_Bank_and_LocalData",dataProviderClass=staticProviderClass.class)
	public void Vendor_Change_Fill_In_Data_Global_Local_Bank_NAV(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException, AWTException 
	{
		SharedDriver.pageContainer.vendorPageNAV.VendorName(dataMap.get("Name1"));
		SharedDriver.pageContainer.vendorPageNAV.AddressStreet("Changekite");//dataMap.get("Street")
		SharedDriver.pageContainer.vendorPageNAV.AddresHouseNumber("12"); //dataMap.get("House number")
		SharedDriver.pageContainer.vendorPageNAV.AddresPostalCode("56478"); //dataMap.get("Postal Code")
		SharedDriver.pageContainer.vendorPageNAV.AddresCity("Change holland");
		SharedDriver.pageContainer.vendorPageNAV.ScrollDown();
		//'DropDowns
		SharedDriver.pageContainer.vendorPageNAV.AddresCountry("DZ, Algeria"); //dataMap.get("Country")
		SharedDriver.pageContainer.vendorPageNAV.AddresLanguageKey("EN, English");
		SharedDriver.pageContainer.vendorPageNAV.AddressCreditInformationNumber("565786234");
		SharedDriver.pageContainer.vendorPageNAV.AddresIndustryKey("Y001, Trade/Serv/Transport");
		SharedDriver.pageContainer.vendorPageNAV.AddresCorporateGroup("NPR-BS, NPR-Business Services");
		
		SharedDriver.pageContainer.vendorPageNAV.ClickBankData_NAV();
		
		SharedDriver.pageContainer.vendorPageNAV.ClickBankDetailsNew();
		SharedDriver.pageContainer.vendorPageNAV.VendorBankCountry("DZ, Algeria");
		SharedDriver.pageContainer.vendorPageNAV.SelectBankKey("002");
		SharedDriver.pageContainer.vendorPageNAV.VendorCurrencyCode("DZD, Algerian Dinar");
		SharedDriver.pageContainer.vendorPageNAV.VendorPatnerBankType("DE01");
				
		SharedDriver.pageContainer.vendorPageNAV.ClickLocaData_NAV();
		SharedDriver.pageContainer.vendorPageNAV.ClickFinaceNew(); 
	//	SharedDriver.pageContainer.vendorPageNAV.ScrollDown();
		SharedDriver.pageContainer.vendorPageNAV.VendorPostingGroup("ICV-Trade, IC Vendor Trade");
		SharedDriver.pageContainer.vendorPageNAV.VendorVATPostingGroup("3PV-Tax, 3rd party vendors Tax");
		SharedDriver.pageContainer.vendorPageNAV.VendorGenPostingGroup("3PV_IMPORT, 3rd Party Vendor Trade - Import");
		SharedDriver.pageContainer.vendorPageNAV.validateLocalTestCreate();
		SharedDriver.pageContainer.vendorPageNAV.LocalFinanceSave();
		
		SharedDriver.pageContainer.vendorPageNAV.ClickPurchasingNew();
		SharedDriver.pageContainer.vendorPageNAV.PaymentTermsCode("V004, Payment within 10 days");
		SharedDriver.pageContainer.vendorPageNAV.PaymentMethodCode("CASH ORDER, Cashier Order");
		SharedDriver.pageContainer.vendorPageNAV.validateLocalTestCreate();
		SharedDriver.pageContainer.vendorPageNAV.LocalFinanceSave();
	
		SharedDriver.pageContainer.vendorPageNAV.submitGlobalLocalRequest();
		SharedDriver.pageContainer.vendorPageNAV.getRequestId();
		
		SharedDriver.pageContainer.vendorPageNAV.submitBankRequest();
	}	
	
}

