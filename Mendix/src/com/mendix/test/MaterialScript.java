package com.mendix.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mendix.tool.Constants;
import com.mendix.tool.SharedDriver;
import com.mendix.tool.Sync;
import com.mendix.util.DataProviderUtil.staticProviderClass;

public class MaterialScript {

	WebDriver driver;

	/**
	 * Create Material.
	 */

	@Test
	public void Material_Create_Fill_In_Questionnaire() throws InterruptedException{

		SharedDriver.pageContainer.homePage.navigateToWorkflow();
		SharedDriver.pageContainer.materialPage.switchToPopup();
		SharedDriver.pageContainer.materialPage.clickMaterial("Materials");
		SharedDriver.pageContainer.materialPage.createMaterialNavigate();
		SharedDriver.pageContainer.materialPage.materialTypeSelection();
		SharedDriver.pageContainer.materialPage.createButtonClick();

	}

	@Test(dataProvider="CreateMaterial_Fill_In",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Fill_In_Data(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		SharedDriver.pageContainer.materialPage.disableLocaData();
		SharedDriver.pageContainer.materialPage.materialDescCreate(dataMap.get("Description"));
		SharedDriver.pageContainer.materialPage.materialGrpSelectionTest(dataMap.get("Material Group"));
		SharedDriver.pageContainer.materialPage.grossWeightEntestTest(dataMap.get("Gross Weight Base UoM"));
		SharedDriver.pageContainer.materialPage.unitOfWeightSelectionTest(dataMap.get("Unit of Weight"));
		SharedDriver.pageContainer.materialPage.baseUOMSelectionTest(dataMap.get("Base UoM"));
		SharedDriver.pageContainer.materialPage.netWeightEnterTest(dataMap.get("Net Weight Base UoM"));
		SharedDriver.pageContainer.materialPage.uomPrimarySelectionTest();
		
		
	}

	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Process_Information_Check(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{

		SharedDriver.pageContainer.processInfoPage.processInfoSearch();
		//SharedDriver.pageContainer.processInfoPage.reqIdSearch_Global(dataMap.get("RequestId"));
	    //SharedDriver.pageContainer.processInfoPage.getState(dataMap.get("RequestId"));
		SharedDriver.pageContainer.processInfoPage.getState_New(dataMap.get("RequestId"));
		//SharedDriver.pageContainer.processInfoPage.requestCreated_between();
		
		SharedDriver.pageContainer.processInfoPage.browserClose();
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
//		SharedDriver.pageContainer.processInfoPage.browserClose();
//		SharedDriver.pageContainer.materialApprovalPage.duplicateCheck();

	}


	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Review_Global_Data_Approve_GBDA(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		Assert.assertTrue(SharedDriver.pageContainer.homePage.navigateToWorkflow());
		SharedDriver.pageContainer.materialPage.switchToPopup();
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		SharedDriver.pageContainer.materialApprovalPage.approvalBtnClick();

	}
	
	@Test
	public void Material_Create_Validate_Submit_Check() throws InterruptedException, FileNotFoundException, IOException 
	{
		
		SharedDriver.pageContainer.materialPage.validateTestCreate();
		SharedDriver.pageContainer.materialPage.submitGlobalRequestTest();
		SharedDriver.pageContainer.materialPage.getRequestId_Create();
//		SharedDriver.pageContainer.materialPage.getRequestId();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();

	}


	@Test(dataProvider="CreateMaterial_Fill_In",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Validate_Submit_Save_Check(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		/*SharedDriver.pageContainer.materialPage.disableLocaData();
		SharedDriver.pageContainer.materialPage.materialDescCreate(dataMap.get("Description"));
		SharedDriver.pageContainer.materialPage.materialGrpSelectionTest(dataMap.get("Material Group"));
		SharedDriver.pageContainer.materialPage.grossWeightEntestTest(dataMap.get("Gross Weight Base UoM"));
		SharedDriver.pageContainer.materialPage.unitOfWeightSelectionTest(dataMap.get("Unit of Weight"));
		SharedDriver.pageContainer.materialPage.baseUOMSelectionTest(dataMap.get("Base UoM"));
		SharedDriver.pageContainer.materialPage.netWeightEnterTest(dataMap.get("Net Weight Base UoM"));
		SharedDriver.pageContainer.materialPage.uomPrimarySelectionTest();*/
		SharedDriver.pageContainer.materialPage.validateTestCreate();
		SharedDriver.pageContainer.materialPage.SaveAsDraft();
		SharedDriver.pageContainer.materialPage.getRequestId_draft();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();
//		SharedDriver.pageContainer.processInfoPage.browserClose();

	}

	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Fill_in_Data_Save_as_Draft_Submit (Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
//		Thread.sleep(3000);
//		Sync.waitForSeconds(Constants.WAIT_3);
		SharedDriver.pageContainer.homePage.navigateToWorkflow();
		SharedDriver.pageContainer.materialPage.switchToPopup();
		SharedDriver.pageContainer.materialApprovalPage.reqIdSearchMyTasks(dataMap.get("RequestId"));
		SharedDriver.pageContainer.materialPage.clickLocalAction();
		SharedDriver.pageContainer.materialPage.validateTestCreate();
		SharedDriver.pageContainer.materialApprovalPage.submitBtnClick_Global();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();
//		SharedDriver.pageContainer.processInfoPage.browserClose();
		/*	SharedDriver.pageContainer.materialApprovalPage.submitGlobalRequest_draft();
		SharedDriver.pageContainer.materialApprovalPage.approvalBtnClick_Local();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();*/
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
		SharedDriver.pageContainer.materialApprovalPage.launchUFT();
	}

}

