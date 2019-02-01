package com.mendix.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import com.mendix.tool.SharedDriver;
import com.mendix.util.DataProviderUtil.staticProviderClass;

public class MaterialChangeScript {

	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Material_Change_Syndication_Check (Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		SharedDriver.pageContainer.homePage.navigateToWorkflow();
		SharedDriver.pageContainer.materialPage.switchToPopup();
		SharedDriver.pageContainer.materialPage.navigateToDashboard();
		SharedDriver.pageContainer.materialPage.advancedSearch();
		SharedDriver.pageContainer.materialPage.scrolltoGlobalSearch();
    	//SharedDriver.pageContainer.materialPage.getCurrDate();
		SharedDriver.pageContainer.materialPage.globalSearch(dataMap.get("Global_ID"));
		//		SharedDriver.pageContainer.materialPage.getGlobalId();
		SharedDriver.pageContainer.materialPage.clickFullMaterialData();
		//SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();
		SharedDriver.pageContainer.material_Change_Page.clickEditCheckBox();
		SharedDriver.pageContainer.material_Change_Page.clickEditbutton();


	}
	@Test(dataProvider="CreateMaterial_Fill_In",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Fill_In_Data_Change(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		//		SharedDriver.pageContainer.materialPage.disableLocaData();
		//		SharedDriver.pageContainer.materialPage.materialDescCreate(dataMap.get("Description"));
		SharedDriver.pageContainer.materialPage.materialGrpSelectionTest("CMG0012");
		SharedDriver.pageContainer.materialPage.grossWeightEntestTest("200");
		SharedDriver.pageContainer.materialPage.unitOfWeightSelectionTest("KG");
		SharedDriver.pageContainer.materialPage.baseUOMSelectionTest("KG");
		SharedDriver.pageContainer.materialPage.netWeightEnterTest("200");
		SharedDriver.pageContainer.materialPage.uomPrimarySelectionTest();

	}

	@Test
	public void Material_RequestId_Generation() throws InterruptedException, FileNotFoundException, IOException  
	{
		//SharedDriver.pageContainer.materialPage.clickLocalAction();
		SharedDriver.pageContainer.materialPage.validateTestCreate();
		SharedDriver.pageContainer.materialPage.submitGlobalRequestTest();
		SharedDriver.pageContainer.materialPage.getRequestId();
	}
	
	@Test(dataProvider="Process_Information_Check",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Reference_Syndication_Check (Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		SharedDriver.pageContainer.homePage.navigateToWorkflow();
		SharedDriver.pageContainer.materialPage.switchToPopup();
		SharedDriver.pageContainer.materialPage.navigateToDashboard();
//		SharedDriver.pageContainer.materialPage.advancedSearch();
//		SharedDriver.pageContainer.materialPage.scrolltoGlobalSearch();
//		SharedDriver.pageContainer.materialPage.getCurrDate();
		SharedDriver.pageContainer.materialPage.globalSearch(dataMap.get("Global_ID"));
//		SharedDriver.pageContainer.materialPage.getGlobalId();
		SharedDriver.pageContainer.materialPage.clickFullMaterialData();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();
		SharedDriver.pageContainer.material_Change_Page.clickEditCheckBox();
		SharedDriver.pageContainer.material_Change_Page.clickReferencebutton();
		SharedDriver.pageContainer.materialApprovalPage.submitRequestOkBtnClick();
	}
	
	@Test(dataProvider="CreateMaterial_Fill_In",dataProviderClass=staticProviderClass.class)
	public void Material_Create_Fill_In_Data_Reference(Map<String,String> dataMap) throws InterruptedException, FileNotFoundException, IOException 
	{
		//		SharedDriver.pageContainer.materialPage.disableLocaData();
		//		SharedDriver.pageContainer.materialPage.materialDescCreate(dataMap.get("Description"));
		SharedDriver.pageContainer.materialPage.materialGrpSelectionTest("CMG0012");
		SharedDriver.pageContainer.materialPage.grossWeightEntestTest("100");
		SharedDriver.pageContainer.materialPage.unitOfWeightSelectionTest("KG");
		SharedDriver.pageContainer.materialPage.baseUOMSelectionTest("KG");
		SharedDriver.pageContainer.materialPage.netWeightEnterTest("100");
		SharedDriver.pageContainer.materialPage.uomPrimarySelectionTest();

	}
	
	
}
