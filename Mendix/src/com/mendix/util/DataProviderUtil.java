package com.mendix.util;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.mendix.tool.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class DataProviderUtil.
 */
public class DataProviderUtil {
	
	/**
	 * The Class staticProviderClass.
	 */
	public static class staticProviderClass{
		
		/**
		 * Material creation.
		 *
		 * @return the iterator
		 */
		/*@DataProvider(name="HeiPort_Login",parallel=false)
		public static Iterator<Object[]> HeiPort_Login(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("input/Mendix-"+Constants.ENV+Constants.EXCEL_FORMAT, "MaterialPage");
			return testData;
		}*/
		
		@DataProvider(name="HeiPort_Login",parallel=false)
		public static Iterator<Object[]> HeiPort_Login(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX, "TestPlan");
			return testData;
		}
		
		/**
		 * Material creation Fill in.
		 *
		 * @return the iterator
		 */
		
		@DataProvider(name="CreateMaterial_Fill_In",parallel=false)
		public static Iterator<Object[]> MaterialPage(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("input/data/Global_"+Constants.MDM_TYPE_MATERIAL+"_Data"+Constants.EXCEL_FORMAT_XLSX, "YROH");
			return testData;
		}
		
		@DataProvider(name="CreateMaterial_Fill_In_Nav",parallel=false)
		public static Iterator<Object[]> MaterialNavPage(){
			Iterator<Object[]> testData=ExcelUtil.getTestDataNew("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX, "TestPlan","input/data/Global_"+Constants.MDM_TYPE_MATERIAL+"_Data"+Constants.EXCEL_FORMAT_XLSX, "YROH");
			return testData;
		}
		
		/*@DataProvider(name="CreateMaterial_Fill_In_Nav",parallel=false)
		public static Iterator<Object[]> MaterialNavPage(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("input/data/Global_"+Constants.MDM_TYPE_MATERIAL+"_Data_Nav"+Constants.EXCEL_FORMAT_XLSX, "YROH");
			return testData;
		}*/
		
		@DataProvider(name="CreateMaterial_Fill_In_JDE",parallel=false)
		public static Iterator<Object[]> MaterialJDEPage(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("input/data/Global_"+Constants.MDM_TYPE_MATERIAL+"_Data_JDE"+Constants.EXCEL_FORMAT_XLSX, "YROH");
			return testData;
		}
		/**
		 * Change material.
		 *
		 * @return the iterator
		 */
		@DataProvider(name="Process_Information_Check",parallel=false)
		public static Iterator<Object[]> ProcessInfoPage(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX, "TestPlan");
			return testData;
		}
		
		/*@DataProvider(name="Process_Information_Check_draft",parallel=false)
		public static Iterator<Object[]> ProcessInfoDraftPage(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("C:\\Users\\IBM_ADMIN\\Downloads\\MDM_POC_Upgrade\\MDM_POC_Upgrade\\input\\Mendix-MDM.xlsm", "MaterialPage");
			return testData;
		}
		*/
		
		
	}
	
	
	
}
