package com.mendix.tool;

import org.openqa.selenium.WebDriver;

import com.mendix.page.LoginPage;
import com.mendix.page.MaterialApprovalPage;
import com.mendix.page.HomePage;
import com.mendix.page.MaterialPage;
import com.mendix.page.Material_Change_Page;
import com.mendix.page.Material_JDE_Page;
import com.mendix.page.Material_Nav_Page;
import com.mendix.page.ProceesInfoPage;
import com.mendix.page.VendorPage;
import com.mendix.page.VendorPage_NAV;
import com.mendix.page.Vendor_JDE_Page;



public class PageContainer {

public WebDriver driver;
public LoginPage loginPage;
public MaterialPage materialPage;
public HomePage homePage;
public ProceesInfoPage processInfoPage;
public MaterialApprovalPage materialApprovalPage;
public Material_Nav_Page materialNavPage;
public Material_JDE_Page materialJdePage;
public VendorPage vendorPage;
public VendorPage_NAV vendorPageNAV;
public Vendor_JDE_Page vendor_JDE_Page;
public Material_Change_Page material_Change_Page;

public PageContainer(WebDriver driver){
	this.driver=driver;
	initPages();
}


private void initPages() {
	loginPage = new LoginPage(driver);
	materialPage = new MaterialPage(driver);	
    homePage = new HomePage(driver);
    processInfoPage = new ProceesInfoPage(driver);
    materialApprovalPage = new MaterialApprovalPage(driver);
    materialNavPage = new Material_Nav_Page(driver);
    materialJdePage =new Material_JDE_Page(driver);
    vendorPage = new VendorPage(driver);
    vendorPageNAV = new VendorPage_NAV(driver);
    vendor_JDE_Page =new Vendor_JDE_Page(driver);
    material_Change_Page =new Material_Change_Page(driver);
	
}

}
