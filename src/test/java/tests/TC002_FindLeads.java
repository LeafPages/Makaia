package tests;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import hooks.TestNgHooks;
import pages.HomePage;
import pages.LoginPage;

public class TC002_FindLeads extends TestNgHooks{
	
	@BeforeTest
	public void setData() {
		testCaseName = "TC002 - Find Leads";
		testDescription = "Login to Leaftaps";
		category = "smoke";
		authors = "sarath";  
		dataSheetName = "TC002";
		nodes = "Leads";
	}
	

	@Test(dataProvider="fetchData")
	public void createLead(String lang, String firstName) {
		
		setLanguage(lang);
		
		new HomePage()
		.clickCRMSFA()
		.clickLeadsTab()
		.clickFindLeadsLink()
		.typeFirstName(firstName)
		.clickFindLeadsButton();
	}
	
	
}
