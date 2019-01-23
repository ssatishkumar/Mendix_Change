package com.mendix.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReportUpdate {
	
	static Properties props = new Properties();

	public static String get(String key){
		return ExcelReportUpdate.props.getProperty(key.toLowerCase());
	}
	public static void put(String key, String val){
		props.put(key.toLowerCase(), val);
	}
	
	public static void load(String envExcelFile, String sheetName){
		props.put("home", System.getProperty("user.dir"));
		FileInputStream file = null;;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = deleteEmptyRows(envExcelFile, sheetName);
		try {
			file = new FileInputStream(new File(envExcelFile));
			try {
				workbook = new XSSFWorkbook(file);
				//sheet = workbook.getSheet(sheetName);
				System.out.println("last row in Env sheet====="+sheet.getLastRowNum());
			} catch (IOException e) {
				System.out.println("Environment file not found, please check: "+envExcelFile);
				System.out.println("Stopping execution ...");
				System.exit(-1);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Environment file not found, please check: "+envExcelFile);
			System.out.println("Stopping execution ...");
			System.exit(-1);
		}
		
		Iterator<Row> rowIterator = null;
		try {
			rowIterator = sheet.iterator();
		} catch (Exception e) {
			System.out.println("Environment file not found, please check: "+envExcelFile);
			System.out.println("Stopping execution ...");
			System.exit(-1);
		}
		
		while (rowIterator.hasNext()) {
			Row currentRow = rowIterator.next();
			Cell propertyCell = currentRow.getCell(0);
			Cell valueCell = currentRow.getCell(1);
			String currentCellValue = "";
			if (valueCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				currentCellValue = String.valueOf(valueCell.getNumericCellValue());
			} else if (valueCell.getCellType() == Cell.CELL_TYPE_STRING) {
				currentCellValue = valueCell.getStringCellValue();
			} else if (valueCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				currentCellValue = String.valueOf(valueCell.getBooleanCellValue());
			} else if (valueCell.getCellType() == Cell.CELL_TYPE_BLANK) {
				currentCellValue = "";
			} else if (valueCell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				currentCellValue = String.valueOf(valueCell.getCellFormula());
			} else if (valueCell.getCellType() == Cell.CELL_TYPE_ERROR) {
				currentCellValue = "Error reading data";
			}
			
			props.put(propertyCell.getStringCellValue().toLowerCase().trim(), currentCellValue.trim());
		}
	}
	
	public static XSSFSheet deleteEmptyRows(String configFile, String sheetName)
	{
		FileInputStream file;
		int count = 0;
		XSSFSheet sheet = null;
		try {
			file = new FileInputStream(new File(configFile));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
			 boolean stop = false;
	            boolean nonBlankRowFound;
	            short c;
	            XSSFRow lastRow = null;
	            XSSFCell cell = null;
	            
	            while (stop == false) {
	                nonBlankRowFound = false;
	                lastRow = sheet.getRow(sheet.getLastRowNum());
	                System.out.println("last row in sheet---"+sheet.getLastRowNum());
	                for (c = lastRow.getFirstCellNum(); c <= lastRow.getLastCellNum(); c++) {
	                    cell = lastRow.getCell(c);
	                    if (cell != null && lastRow.getCell(c).getCellType() != XSSFCell.CELL_TYPE_BLANK) {
	                        nonBlankRowFound = true;
	                    }
	                }
	                if (nonBlankRowFound == true) {
	                    stop = true;
	                    System.out.println("stopped at row---"+sheet.getLastRowNum());
	                    count =  sheet.getLastRowNum();
	                } else {
	                	System.out.println("deleting row -----"+sheet.getLastRowNum());
	                    sheet.removeRow(lastRow);
	                }
	            }
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sheet;
	}

	public static void updateResult(String configFile, String testCaseName, String status) throws IOException {
//		String sheetName = "Result";
		FileInputStream file = new FileInputStream(new File(configFile));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		int sheetCount = workbook.getNumberOfSheets();
		XSSFSheet sheet= workbook.createSheet("Result");

		for (int i = 0; i < sheetCount; i++) {
//			XSSFSheet sheet = workbook.getSheet(sheetName);
			
//			Row row1 = sheet.createRow((short) 0);
//			int rowCount = sheet.getPhysicalNumberOfRows();

			Iterator<Row> rowIterator = sheet.iterator();

//			rowIterator.next();
			
			while (rowIterator.hasNext()) {
				Row currentRow = rowIterator.next();

				String testCase = "";
				Cell testcaseCell = currentRow.createCell(2);
				if (testcaseCell == null) {
					break;
				}

				if (testcaseCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					testCase = String.valueOf(testcaseCell.getNumericCellValue());
				} else if (testcaseCell.getCellType() == Cell.CELL_TYPE_STRING) {
					testCase = testcaseCell.getStringCellValue();
				} else if (testcaseCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
					testCase = String.valueOf(testcaseCell.getBooleanCellValue());
				} else if (testcaseCell.getCellType() == Cell.CELL_TYPE_BLANK) {
					testCase = "";
				} else if (testcaseCell.getCellType() == Cell.CELL_TYPE_FORMULA) {
					testCase = String.valueOf(testcaseCell.getCellFormula());
				} else if (testcaseCell.getCellType() == Cell.CELL_TYPE_ERROR) {
					testCase = "Error reading data";
				}

				if (testCase.trim().equalsIgnoreCase(testCaseName)) {
					Cell resultCell = currentRow.getCell(3);
					Cell resultCell2=currentRow.getCell(2);
					// CellStyle style = resultCell.getCellStyle();
					// style.setFillBackgroundColor(IndexedColors.YELLOW.index);
					// style.setFillPattern(CellStyle.ALIGN_LEFT);
					// resultCell.setCellStyle(style);
					resultCell2.setCellValue(testCaseName);
					resultCell.setCellValue(status);
					break;
				}
				// }
			}
		}

		file.close();

		FileOutputStream fos = new FileOutputStream(new File(configFile));
		workbook.write(fos);
		
		//workbook.close();
		fos.close();
	}

	public static void main(String args[]) throws IOException {
//		ExcelReportUpdate.load("C:\\MDM_Workspace\\MDM_POC_Upgrade\\report\\excel\\Mendix-DROP1-IE-19_11_2018.xlsm", "Result");
		ExcelReportUpdate.updateResult("C:\\MDM_Workspace\\MDM_POC_Upgrade\\report\\excel\\Mendix-DROP1-IE-19_11_2018.xlsm",
				"Result", "Pass");
//		createTestScriptExcelReport();
		
	}

	public static void createTestScriptExcelReport() throws IOException {
		//TO-DO : Block this call
		try {
			String src = "";
			String templateFile = "";
			if (ExcelReportUpdate.get("report_excel_template").trim().contains("/")) {
				String[] tokens = ExcelReportUpdate.get("report_excel_template").split("/", -1);
				templateFile = tokens[tokens.length - 1];
				src = ExcelReportUpdate.get("report_excel_template");
			} else if (ExcelReportUpdate.get("report_excel_template").trim().contains("\\")) {
				String[] tokens = ExcelReportUpdate.get("report_excel_template").split("\\", -1);
				templateFile = tokens[tokens.length - 1];
				src = ExcelReportUpdate.get("report_excel_template");
			} else {
				templateFile = ExcelReportUpdate.get("report_excel_template");
				src = ExcelReportUpdate.get("report_path") + "/" + ExcelReportUpdate.get("report_excel_template");
			}

			String dest = ExcelReportUpdate.get("report_path") + "/" + ExcelReportUpdate.get("report_excel_filename") + "-" + ExcelReportUpdate.get("execution_environment")
			+ "-" + ExcelReportUpdate.get("execution_build")+".xlsm";

			File f = new File(dest);

			if(!f.exists()){
				FileChannel sourceChannel = null;
				FileChannel destChannel = null;
				try {
					sourceChannel = new FileInputStream(src).getChannel();
					destChannel = new FileOutputStream(dest).getChannel();
					destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
				} catch (Exception e) {
				} finally {
					sourceChannel.close();
					destChannel.close();
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
