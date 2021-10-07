package com.defects.tests;


import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.defects.base.BaseUI;
import com.defects.utils.TestDataProvider;

public class LoginTest extends BaseUI{
	
	//@Test(dataProvider = "getTestOneData")
	public void testOne(Hashtable<String, String> dataTable) {
		
		logger = report.createTest("LoginTest");
		invokeBrowser();
		openURL("websiteURL");
		elementClick("signinPageBtn_xpath");
		enterText("userNameTextbox_xpath", dataTable.get("UserName"));
		enterText("passwordTextbox_xpath", dataTable.get("Password"));
		elementClick("signinBtn_xpath");
		takeScreenshot();
		quitBrowser();
	}
	
	@Test
	public void checkDropdown() {
		logger = report.createTest("Dropdown Test");
		invokeBrowser();
		openURL("dropDownURL");
		selectDropdown("dropDown_xpath", "Angola");
	}
	
	@Test
	public void checkCheckBox() {
		logger = report.createTest("CheckBox Test");
		invokeBrowser();
		openURL("checkBoxURL");
		elementClick("checkBox1_xpath");
	}
	
	public void checkDragDrop() {
		
	}
	
	
	@AfterTest
	public void endReport() {
		report.flush();
	}
	
	@DataProvider
	public Object[][] getTestOneData(){
		
		return TestDataProvider.getTestData("TestData_TestManagement", "SampleData", "Test One");
	}

}

