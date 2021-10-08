package com.defects.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.defects.base.BaseUI;

public class SuiteClass extends BaseUI {

	@BeforeSuite
	public void setup() {
		invokeBrowser();
		implicitWait(30);
		driver.get(prop.getProperty("baseURL"));
	}
	
	@AfterSuite
	public void close() {
		report.flush();
		tearDown();
	}
}
