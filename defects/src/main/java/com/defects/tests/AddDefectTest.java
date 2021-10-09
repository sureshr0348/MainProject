package com.defects.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddDefectTest extends SuiteClass{

	@BeforeClass
	public void before() {
		if (!(driver.getTitle().equalsIgnoreCase("Defects List"))) {
			driver.get(prop.getProperty("baseURL"));
			mousehover("menu");
			elementClick("projectLink");
			mousehover("executeMenu");
			elementClick("defectsLink");
		}
	}
	
	@Test(priority = 1)
	public void openAddDefectPage() throws InterruptedException {
		logger = report.createTest("Add_Defect_Page");
		
		elementClick("addDefectButton");
		
		explicitWaitTitle("Defect Details :");
		takeScreenshot("Add_Defect_page");
		
		reportPass("Add Defect Page opened");
	}
	
	@Test(priority = 2)
	public void fillDetails() {
		logger = report.createTest("Add_Defect_Fill_Details");
		
		gotoFrame("contentframe");
		
		enterText("defectName", "AutomationTestDummy");
		enterText("description", "This is a Defect created by automation test");
		selectDropdown("severity", "Sev2");
		selectDropdown("priority", "Medium");
		selectDropdown("release", "1235");
		selectDropdown("sprint", "Madura");
		selectDropdown("appraisalType", "Testing");
		selectDropdown("reportingSource", "Cognizant QE&A");
		selectDropdown("detectionCode", "Training");
		selectDropdown("category", "Defect");
		enterText("dueDate", "25-Oct-2021");
		
		elementClick("saveButton");

		explicitWaitTitle("AutomationTestDummy");
		
		takeScreenshot("Add_Defect_AfterSubmit");
		
		reportPass("New Defect Added Successfully");
	}
	
	//@Test(priority = 3)
	public void saveAndGetDetails() {
		
		
	}
}
