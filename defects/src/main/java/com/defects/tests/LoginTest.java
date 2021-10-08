package com.defects.tests;


import java.util.Scanner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.defects.utils.TestDataProvider;

public class LoginTest extends SuiteClass {
	
	
	@Test(priority = 1)														
	public void Login() throws InterruptedException {			
		Scanner sc = new Scanner(System.in);
		logger = report.createTest("Login");
		
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
		
		takeScreenshot();
		sc.close();
		reportPass("Login Completed");
	}

	@DataProvider
	public Object[][] getTestOneData(){
		return TestDataProvider.getTestData("TestData_TestManagement", "SampleData", "Test One");
	}

	
	@Test(priority = 2)
	public void navigateToDefectsPage() {
		logger = report.createTest("Navigate_To_Defects");
		
		mousehover("menu");
		elementClick("projectLink");
		mousehover("executeMenu");
		elementClick("defectsLink");
		
		reportPass("Navigate_To_Defects completed");
	}
}

