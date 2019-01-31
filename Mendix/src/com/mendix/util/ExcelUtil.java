package com.mendix.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mendix.tool.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelUtil.
 */
public class ExcelUtil {

	private static XSSFWorkbook workbook;

	/**
	 * Gets the test data.
	 *
	 * @param strWorkbookPath the str workbook path
	 * @param strWorksheetName the str worksheet name
	 * @return the test data
	 */
	public static Iterator<Object[]> getTestData(String strWorkbookPath,String strWorksheetName){
		List<Object[]> data = new ArrayList<Object[]>();

		try{
			FileInputStream file = new FileInputStream(new File(strWorkbookPath));

			workbook = new XSSFWorkbook(file);

			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(strWorksheetName);

			//Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.rowIterator();

			Row firstRow=rowIterator.next();

			Map<String,String> columnNamesMap=getColumnNames(firstRow);

			while(rowIterator.hasNext()){
				Iterator<Cell> cellIterator=rowIterator.next().cellIterator();
				Map<String,String> rowMap=new LinkedHashMap<String, String>();
				for(Entry<?, ?> entry:columnNamesMap.entrySet()){
					String strColumnName=entry.getKey().toString();
					String strValue="";
					try{
						Cell cell=cellIterator.next();
						if(cell!=null){strValue=cell.toString();}
					}catch(Exception e){}
					rowMap.put(strColumnName, strValue.trim());
				}

				if(rowMap.get("Execute").equalsIgnoreCase("Y")){	
					//rowMap.put("Iteration", ""+inRowCounter);
					data.add(new Object[]{rowMap});
					//inRowCounter++;

				}
			}

			file.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data.iterator();
	}

	public static Iterator<Object[]> getTestDataNew(String strWorkbookPath1,String strWorksheetName1,String strWorkbookPath2,String strWorksheetName2){
		List<Object[]> data = new ArrayList<Object[]>();

		try{
			FileInputStream file1 = new FileInputStream(new File(strWorkbookPath1));
			FileInputStream file2 = new FileInputStream(new File(strWorkbookPath2));
			
			XSSFWorkbook workbook1 = new XSSFWorkbook(file1);
			XSSFWorkbook workbook2 = new XSSFWorkbook(file2);
			//Get first sheet from the workbook
			XSSFSheet sheet1 = workbook1.getSheet(strWorksheetName1);
			XSSFSheet sheet2 = workbook2.getSheet(strWorksheetName2);

			//Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator1 = sheet1.rowIterator();
			Iterator<Row> rowIterator2 = sheet2.rowIterator();

			Row firstRow1=rowIterator1.next();
			Row firstRow2=rowIterator2.next();

			Map<String,String> columnNamesMap1=getColumnNames(firstRow1);
			Map<String,String> columnNamesMap2=getColumnNames(firstRow2);

			while(rowIterator1.hasNext()){
				Iterator<Cell> cellIterator1=rowIterator1.next().cellIterator();
				Map<String,String> rowMap1=new LinkedHashMap<String, String>();
				for(Entry<?, ?> entry:columnNamesMap1.entrySet()){
					String strColumnName1=entry.getKey().toString();
					String strValue1="";
					try{
						Cell cell1=cellIterator1.next();
						if(cell1!=null){strValue1=cell1.toString();}
					}catch(Exception e){}
					rowMap1.put(strColumnName1, strValue1.trim());
				}
             
				while(rowIterator2.hasNext()){
					Iterator<Cell> cellIterator2=rowIterator2.next().cellIterator();
					Map<String,String> rowMap2=new LinkedHashMap<String, String>();
					for(Entry<?, ?> entry:columnNamesMap2.entrySet()){
						String strColumnName2=entry.getKey().toString();
						String strValue2="";
						try{
							Cell cell2=cellIterator2.next();
//							if(cell2=null){
								strValue2=cell2.toString();
//								}
						}catch(Exception e){}
						rowMap2.put(strColumnName2, strValue2.trim());
					}
				if(rowMap2.get("Execute").equalsIgnoreCase("Y")){
//					if(rowMap1.get("Test_Case").equals(rowMap2.get("Test_Case"))){
//						if(rowMap1.get("OpCo").equals(rowMap2.get("OpCo"))){	
							//rowMap.put("Iteration", ""+inRowCounter);
							
							data.add(new Object[]{rowMap2});
							//inRowCounter++;
//							break;

//						}
//					}
//				}

			}
				}
			file1.close();
			file2.close();
		}
			}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return data.iterator();
	}




	/**
	 * Gets the column names.
	 *
	 * @param row the row
	 * @return the column names
	 */
	private static Map<String,String> getColumnNames(Row row){
		Map<String,String> columnNamesMap=new LinkedHashMap<String, String>();

		Iterator<Cell> cells=row.cellIterator();

		while(cells.hasNext()){
			String strColumnName=cells.next().toString();
			columnNamesMap.put(strColumnName, strColumnName);
		}

		return columnNamesMap;
	}

	public static void excelWrite(String globalId)
			throws FileNotFoundException, IOException {
		try {
			FileInputStream fis = new FileInputStream("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("MaterialPage");
			Cell cell = sheet.getRow(1).getCell(3);
			cell.setCellValue(globalId);
			FileOutputStream fos = new FileOutputStream("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void excelWriteGlobalId(String globalId)
			throws FileNotFoundException, IOException {
		try {
			FileInputStream fis = new FileInputStream("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("MaterialPage");
			Cell cell = sheet.getRow(1).getCell(4);
			cell.setCellValue(globalId);
			FileOutputStream fos = new FileOutputStream("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void excelWriteMaterialNum(String materialNum)
			throws FileNotFoundException, IOException {
		try {
			FileInputStream fis = new FileInputStream("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("MaterialPage");
			Cell cell = sheet.getRow(1).getCell(5);
			cell.setCellValue(materialNum);
			FileOutputStream fos = new FileOutputStream("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void excelWriteState(String globalId)
			throws FileNotFoundException, IOException {
		try {
			FileInputStream fis = new FileInputStream("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//call the getSheet() method of Workbook and pass the Sheet Name here. 
			//In this case I have given the sheet name as “TestData” 
			//or if you use the method getSheetAt(), you can pass sheet number starting from 0. Index starts with 0.
			XSSFSheet sheet = workbook.getSheet("TestPlan");
			//XSSFSheet sheet = workbook.getSheetAt(0);
			//Now create a row number and a cell where we want to enter a value. 
			//Here im about to write my test data in the cell B2. It reads Column B as 1 and Row 2 as 1. Column and Row values start from 0.
			//The below line of code will search for row number 2 and column number 2 (i.e., B) and will create a space. 
			//The createCell() method is present inside Row class.
			//		Row row = sheet.c
			//		Cell cell = row.createCell(1);
			Cell cell = sheet.getRow(1).getCell(3);
			//Now we need to find out the type of the value we want to enter. 
			//If it is a string, we need to set the cell type as string 
			//if it is numeric, we need to set the cell type as number
			//		cell.setCellType(cell.CELL_TYPE_STRING);
			cell.setCellValue(globalId);
			FileOutputStream fos = new FileOutputStream("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setCellData(String sheetName, String colName, int rowNum, String value)
	{
		try
		{
			FileInputStream fis = new FileInputStream("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int col_Num = -1;
			XSSFSheet sheet = workbook.getSheet(sheetName);

			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
				{
					col_Num = i;
				}
			}

			sheet.autoSizeColumn(col_Num);
			row = sheet.getRow(rowNum - 1);
			if(row==null)
				row = sheet.createRow(rowNum - 1);

			XSSFCell cell = row.getCell(col_Num);
			if(cell == null)
				cell = row.createCell(col_Num);
			System.out.println(value);
			cell.setCellValue(value);
			
			FileOutputStream fos = new FileOutputStream("input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX);
			workbook.write(fos);
			fos.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			//            return  false;
		}
		//        return true;
	}

	public static void main(String args[]) throws IOException {
		Runtime.getRuntime().exec("AutoIt UFT Launch/UFT.exe");

	}

}
