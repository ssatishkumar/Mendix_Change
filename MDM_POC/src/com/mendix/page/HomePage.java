package com.mendix.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.mendix.tool.Button;
import com.mendix.tool.Constants;
import com.mendix.tool.Menu;
import com.mendix.tool.Sync;
import com.mendix.tool.Textbox;

public class HomePage {
	
	WebDriver driver;

	public HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	/** The menu Mouse Hover. */
	@FindBy(how=How.CSS, using="#anchor1")
	WebElement menuWork;
	
	/** The menu MDM WorkFlow. */
	@FindBy(how=How.XPATH, using="//a[text()='MDM Workflow']")
	WebElement menuWorkflow;
	
	/**
	 * Login.
	 *
	 * @param strUsername the str username
	 * @param strPassword the str password
	 * @return 
	 */
	public boolean navigateToWorkflow(){	
		
//		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.WaitForPageLoad(driver);
		Sync.waitForObject(driver, "Work", menuWork);
		Menu.mouseHoverJScript("Work", menuWork, driver);
//		Sync.waitForSeconds(Constants.WAIT_1);
		Sync.waitForObject(driver, "MDM Workflow", menuWorkflow);
		return Button.click("MDM Workflow", menuWorkflow);
		 
	}
	


}
