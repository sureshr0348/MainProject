package com.defects.tests;


import java.util.Scanner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.defects.base.BaseUI;
import com.defects.utils.TestDataProvider;

public class LoginTest extends BaseUI{
	
	@BeforeSuite
	public void setup() {
		invokeBrowser();
		implicitWait(30);
		openURL("baseURL");
	}
	
	@AfterSuite
	public void teardown() {
		quitBrowser();
	}
	
	@Test														//(dataProvider = "getTestOneData")
	public void Login() throws InterruptedException {			//(Hashtable<String, String> dataTable) {
		Scanner sc = new Scanner(System.in);
		logger = report.createTest("LoginTest");
		
		enterText("emailBox", prop.getProperty("email"));
		elementClick("nextButton");
		Thread.sleep(3000);
		enterText("passwordBox", prop.getProperty("pass"));
		elementClick("signInButton");
		
		System.out.println("Enter the OTP:");
		String otp=sc.next();
		
		enterText("otpBox", otp);
		elementClick("verifyButton");
		Thread.sleep(2000);
		elementClick("verifyButton");
		
		Thread.sleep(10000);
		takeScreenshot();
		sc.close();
	}

		
	@DataProvider
	public Object[][] getTestOneData(){
		return TestDataProvider.getTestData("TestData_TestManagement", "SampleData", "Test One");
	}

}

