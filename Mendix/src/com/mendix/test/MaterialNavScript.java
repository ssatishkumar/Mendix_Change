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



public class MaterialNavScript {

	WebDriver driver;

	/**
	 * Create Material with Nav.
	 * @throws AWTException 
	 */

	@Test(dataProvider="CreateMaterial_Fill_In_Nav",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Fill_In_Data(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		SharedDriver.pageContainer.materialPage.materialDescCreate(dataMap.get("Description"));
		SharedDriver.pageContainer.materialPage.materialGrpSelectionTest(dataMap.get("Material Group"));
		SharedDriver.pageContainer.materialPage.grossWeightEntestTest(dataMap.get("Gross Weight Base UoM"));
		SharedDriver.pageContainer.materialPage.unitOfWeightSelectionTest(dataMap.get("Unit of Weight"));
		SharedDriver.pageContainer.materialPage.baseUOMSelectionTest(dataMap.get("Base UoM"));
		SharedDriver.pageContainer.materialPage.netWeightEnterTest(dataMap.get("Net Weight Base UoM"));
		SharedDriver.pageContainer.materialPage.uomPrimarySelectionTest();
	}
	@Test(dataProvider="CreateMaterial_Fill_In_Nav",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Fill_In_Data_JDE_Planning(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException, AWTException 
	{
		SharedDriver.pageContainer.materialNavPage.enterLocalData();
    	SharedDriver.pageContainer.materialNavPage.enterPlantData(dataMap.get("Plant"));
		SharedDriver.pageContainer.materialNavPage.clickEditPlanningData();
		SharedDriver.pageContainer.materialNavPage.scrolltoRoundingPrecision();
		SharedDriver.pageContainer.materialNavPage.selectRoundingPrecision(dataMap.get("Rounding Precision"));
		SharedDriver.pageContainer.materialNavPage.clickLocalAction();
		SharedDriver.pageContainer.materialNavPage.clickValidatLocalData();
		SharedDriver.pageContainer.materialNavPage.clickPlanningSaveButton();
	}

	@Test(dataProvider="CreateMaterial_Fill_In_Nav",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Fill_In_Data_JDE_Finance(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException, AWTException 
	{
		SharedDriver.pageContainer.materialNavPage.clickFinancetab();
		SharedDriver.pageContainer.materialNavPage.clickEditFinanceData();
		SharedDriver.pageContainer.materialNavPage.selectGenProdPostingGroup(dataMap.get("Gen. Prod. Posting Group"));
		SharedDriver.pageContainer.materialNavPage.selectVATPostingGroup(dataMap.get("VAT Prod. Posting Group"));
		SharedDriver.pageContainer.materialNavPage.selectItemDepositGroupCode(dataMap.get("Item Deposit Group Code"));
		SharedDriver.pageContainer.materialNavPage.clickLocalAction();
		SharedDriver.pageContainer.materialNavPage.clickValidatLocalData();
		SharedDriver.pageContainer.materialNavPage.clickSaveButton();
	}

	@Test(dataProvider="CreateMaterial_Fill_In_Nav",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Fill_In_Data_JDE_Site(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException, AWTException 
	{
		SharedDriver.pageContainer.materialNavPage.clickSiteNewTab();
		SharedDriver.pageContainer.materialNavPage.clickEditSiteData();
		SharedDriver.pageContainer.materialNavPage.clickSiteNewButton();
		SharedDriver.pageContainer.materialNavPage.selectLocationCode(dataMap.get("Location Code"));
		SharedDriver.pageContainer.materialNavPage.selectReplenishmentSystem(dataMap.get("Replenishment System"));
		SharedDriver.pageContainer.materialNavPage.clickSiteValidateButton();
		SharedDriver.pageContainer.materialNavPage.clickSiteSaveButton();
		SharedDriver.pageContainer.materialNavPage.clickLocalAction();
		SharedDriver.pageContainer.materialNavPage.clickSaveButton();
		SharedDriver.pageContainer.materialNavPage.clickLocalAction();
		SharedDriver.pageContainer.materialNavPage.submitGlobalRequestTest();
		SharedDriver.pageContainer.materialPage.getRequestId();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();
	}
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Process_Information_Check_Global(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{

		SharedDriver.pageContainer.processInfoPage.processInfoSearch();
		SharedDriver.pageContainer.processInfoPage.reqIdSearch_Global(dataMap.get("RequestId"));
		SharedDriver.pageContainer.processInfoPage.getState_New(dataMap.get("RequestId"));
		SharedDriver.pageContainer.processInfoPage.browserClose();
	}
	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Process_Information_Check_Local(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{

		SharedDriver.pageContainer.processInfoPage.processInfoSearch();
		SharedDriver.pageContainer.processInfoPage.reqIdSearch_Local(dataMap.get("RequestId"));
		SharedDriver.pageContainer.processInfoPage.getState_New(dataMap.get("RequestId"));
		SharedDriver.pageContainer.processInfoPage.browserClose();
	}

	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Review_Global_Data_Approve_LDS(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.materialPage.switchToPopup();
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		
		SharedDriver.pageContainer.materialApprovalPage.approvalBtnClick_Local();
		SharedDriver.pageContainer.materialApprovalPage.markViewsBtnClick_Local();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();
		SharedDriver.pageContainer.materialApprovalPage.approveLocalRequest();
//		SharedDriver.pageContainer.materialPage.getRequestId();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();
		/*SharedDriver.pageContainer.materialApprovalPage.approvalBtnClick_Global();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();*/
//		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();
//		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();
	/*	SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();*/

	}
	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Review_Global_Data_Approve_LBDA(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.materialPage.switchToPopup();
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		SharedDriver.pageContainer.materialApprovalPage.approvalBtnClick_Local();
		SharedDriver.pageContainer.materialApprovalPage.approveLocalRequest();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();

	}
	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Review_Global_Data_Approve_GDA(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.materialPage.switchToPopup();
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		SharedDriver.pageContainer.materialApprovalPage.approvalBtnClick();
		SharedDriver.pageContainer.materialApprovalPage.duplicateCheck();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();

	}
	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Syndication_Check (Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		SharedDriver.pageContainer.homePage.navigateToWorkflow();
		SharedDriver.pageContainer.materialPage.switchToPopup();
		SharedDriver.pageContainer.materialPage.navigateToDashboard();
		SharedDriver.pageContainer.materialPage.advancedSearch();
		SharedDriver.pageContainer.materialPage.scrolltoGlobalSearch();
		SharedDriver.pageContainer.materialPage.reqIdSearchGlobal(dataMap.get("RequestId"));
		SharedDriver.pageContainer.materialPage.getGlobalId();
//		SharedDriver.pageContainer.materialPage.clickFullMaterialData();
//		SharedDriver.pageContainer.materialPage.getMaterial_Number();
		SharedDriver.pageContainer.processInfoPage.browserClose();	
//		SharedDriver.pageContainer.materialApprovalPage.launchUFT();
	}
}

