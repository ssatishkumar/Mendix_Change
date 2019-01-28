package com.mendix.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;

import com.mendix.tool.Constants;

public class DriverScript {


	public static void main(String[] args) throws IOException {

		List<Object[]> data = new ArrayList<Object[]>();

		String strWorkbookPath="input/Mendix_TestPlan"+Constants.EXCEL_FORMAT_XLSX;
		String strWorksheetName="TestPlan";
		FileInputStream file = new FileInputStream(new File(strWorkbookPath));

		XSSFWorkbook workbook = new XSSFWorkbook(file);

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
//				rowMap.put("Iteration", ""+inRowCounter);
				data.add(new Object[]{rowMap});
//				inRowCounter++;
		  
		  

			if(rowMap.get("Test_Case").equalsIgnoreCase("Create_Material_Draft")){
				TestNG runner=new TestNG();



				// Create a list of String 
			    List<String> suitefiles=new ArrayList<String>();

				// Add xml file which you have to execute
				suitefiles.add("C:\\Mendix_Workspace\\Mendix\\suite\\scenarios\\Create_Material_Draft.xml");

				// now set xml file for execution
				runner.setTestSuites(suitefiles);
				

				// finally execute the runner using run method
				runner.run();

			}
			else if(rowMap.get("Test_Case").equalsIgnoreCase("Create_Material_Global_Local_NAV")){
				TestNG runner=new TestNG();



				// Create a list of String 
			    List<String> suitefiles=new ArrayList<String>();

			   // Add xml file which you have to execute
				suitefiles.add("C:\\Mendix_Workspace\\Mendix\\suite\\scenarios\\Create_Material_Global_Local_NAV.xml");

				// now set xml file for execution
				runner.setTestSuites(suitefiles);

				// finally execute the runner using run method
				runner.run();

			}
			else if(rowMap.get("Test_Case").equalsIgnoreCase("Create_Material")){
				TestNG runner=new TestNG();

				// Create a list of String 
			    List<String> suitefiles=new ArrayList<String>();

			   // Add xml file which you have to execute
				suitefiles.add("C:\\Mendix_Workspace\\Mendix\\suite\\scenarios\\CreateMaterial.xml");

				// now set xml file for execution
				runner.setTestSuites(suitefiles);

				// finally execute the runner using run method
				runner.run();

			}
		}

		/*Iterator it = FileUtils.iterateFiles(new File("C:\\\\Mendix_Workspace\\\\Mendix\\\\suite\\\\scenarios"), null, false);
		while(it.hasNext()){
			System.out.println(((File) it.next()).getName());
		}*/
		}

		/*	
		file.close();
	}*/
		//	catch(Exception e){
		//		e.printStackTrace();
		//	}

		//		private String source;
		// TODO Auto-generated method stub

		/*		// Create object of TestNG Class
		TestNG runner=new TestNG();



		// Create a list of String 
	    List<String> suitefiles=new ArrayList<String>();



		// Add xml file which you have to execute
		suitefiles.add("C:\\Mendix_Workspace\\Mendix\\suite\\scenarios\\CreateMaterial.xml");

		// now set xml file for execution
		runner.setTestSuites(suitefiles);

		// finally execute the runner using run method
		runner.run();*/
	}

	//}

	private static Iterator<Object[]> excelExtract() throws FileNotFoundException, IOException {
		List<Object[]> data = new ArrayList<Object[]>();

		String strWorkbookPath="C:\\Mendix_Workspace\\Mendix\\input\\Mendix_TestPlan.xlsx";
		String strWorksheetName="TestPlan";
		FileInputStream file = new FileInputStream(new File(strWorkbookPath));

		XSSFWorkbook workbook = new XSSFWorkbook(file);

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
		return data.iterator();
	}

	public static Map<String,String> getColumnNames(Row row)

	{
		Map<String,String> columnNamesMap=new LinkedHashMap<String, String>();

		Iterator<Cell> cells=row.cellIterator();

		while(cells.hasNext()){
			String strColumnName=cells.next().toString();
			columnNamesMap.put(strColumnName, strColumnName);
		}

		return columnNamesMap;
	}

	


}





