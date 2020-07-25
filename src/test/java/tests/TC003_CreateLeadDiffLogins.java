package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import hooks.TestNgHooks;
import pages.HomePage;
import pages.LoginPage;

public class TC003_CreateLeadDiffLogins extends TestNgHooks{
	
	@BeforeSuite
	public void setData() {
		testCaseName = "TC003 - Create Lead (with diff lang)";
		testDescription = "Login to Leaftaps";
		category = "smoke";
		authors = "sarath";  
		dataSheetName = "TC003";
		nodes = "Leads";
	}
	
	@BeforeMethod()
	public void startLogin() {
		
		// do nothing - just to avoid login 
	}
	
	@BeforeMethod()
	public void beforeMethod() {
		
		// do nothing - just to avoid launch of browser 
	}
	

	@Test(dataProvider="fetchData")
	public void createLead(String lang, String browserName, String username, String password, String companyName, String firstName, String lastName) throws FileNotFoundException, IOException {
		
		// Initialize the browser
		init(browserName);
		
		// Set the language of the user choice
		setLanguage(lang);
		
		new LoginPage()
		.loginLeaftaps(username, password)		
		.clickCRMSFA()
		.clickLeadsTab()
		.clickCreateLeadLink()
		.typeCompanyName(companyName)
		.typeFirstName(firstName)
		.typeLastName(lastName)
		.clickCreateLead()
		.verifyTitle();
	}
	
	
}
