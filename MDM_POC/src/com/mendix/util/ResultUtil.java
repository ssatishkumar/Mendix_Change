package com.mendix.util;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.mendix.tool.SharedDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class ResultUtil.
 */
public class ResultUtil {
	
	static Logger log = LogManager.getLogger(ResultUtil.class.getName());
	

/** The Constant reporter. */
public static final ExtentReports reporter;

static{
	reporter = ExtentReports.get(HelperUtil.class);
	reporter.init("report/Dashboard.html", true);
}

/**
 * Report.
 *
 * @param strStatus the str status
 * @param strDesc the str desc
 */
public static void report(String strStatus,String strDesc){
	
	if(strStatus.equalsIgnoreCase("PASS")){
		reporter.log(LogStatus.PASS, strDesc);
		log.info("PASS- "  + strDesc );
		
	
	}
	else if(strStatus.equalsIgnoreCase("FAIL")){
		reporter.log(LogStatus.FAIL, strDesc,"",takeScreenshot());
		log.error("FAIL-" + strDesc.substring(0, 100) );

	}
	else if(strStatus.equalsIgnoreCase("INFO")){
		reporter.log(LogStatus.INFO, strDesc);
		log.info("INFO- " + strDesc);
		
	}
	
	System.out.println(strStatus+"-"+strDesc);
	
	//Hard Stop
	if(strStatus.equalsIgnoreCase("FAIL")&&HelperUtil.executionConfigMap.get("HARD_STOP").equalsIgnoreCase("Y")){
		BrowserUtil.closeBrowser();
		System.exit(0);
		//WindowsUtils.killByName("IEDriverServer.exe");
		//throw new TestNGException("Failed");
	}
}


/**
 * Take screenshot.
 *
 * @return the string
 */
private static String takeScreenshot(){
	String strFileName=UUID.randomUUID().toString()+".png";
	try{
		File scrFile = ((TakesScreenshot)SharedDriver.driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File("report/screenshots/"+strFileName));
	}
	catch(Exception e){
		
	}
	 
	return "./screenshots/"+strFileName;
}
}
