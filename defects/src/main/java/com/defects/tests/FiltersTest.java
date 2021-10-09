package com.defects.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.defects.utils.ReadExcelDataFile;

public class FiltersTest extends SuiteClass {

	@BeforeClass
	public void openDefect() {

		if (!(driver.getTitle().equalsIgnoreCase("Defects List"))) {
			driver.get(prop.getProperty("baseURL"));
			mousehover("menu");
			elementClick("projectLink");
			mousehover("executeMenu");
			elementClick("defectsLink");
		}
	}
	
	/************************
	 * Defect Home Page Functionalities
	 * 
	 ********************************/

	@Test(priority = 3)
	public void defectHomePageFunctionalities() throws InterruptedException {
		logger = report.createTest("Defect Home Page Functionalities");

		elementClick("nextPage");
		elementClick("nextPage");
		Thread.sleep(2000);
		takeScreenshot("NextPage_Navigation");
		reportPass("The next page is displayed sucessfully");

		/*elementClick("lastPage");
		Thread.sleep(2000);
		takeScreenshot("LastPage_Navigation");
		reportPass("The last page is displayed sucessfully");*/

		elementClick("previousPage");
		Thread.sleep(2000);
		takeScreenshot("previousPage_Navigation");
		reportPass("The previous page is displayed sucessfully");

		elementClick("firstPage");
		Thread.sleep(2000);
		takeScreenshot("firstPage_Navigation");
		reportPass("The first page is displayed sucessfully");

		elementClick("refreshPage");
		takeScreenshot("refreshPage_Navigation");
		Thread.sleep(2000);
		reportPass("The page is refreshed sucessfully");

	}

	/************************Defect Home Page Filter Functionalities ********************************/
	@Test(priority = 4)
	public void CreateInvalidFilter() throws InterruptedException {

		logger = report.createTest("Create Filter with Invalid data");

		// Store the current window handle
		String winHandleBefore1 = driver.getWindowHandle();
		elementClick("filterIcon");

		elementClick("createFilter");

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			driver.manage().window().maximize();
		}

		// Store the current window handle
		Thread.sleep(3000);
		String winHandleBefore2 = driver.getWindowHandle();
		elementClick("filterSaveApply");
		
		try {
			Alert alert = driver.switchTo().alert();
			alert.getText();
			alert.accept();
		} catch (UnhandledAlertException e) {
			e.printStackTrace();
		}		

		driver.switchTo().window(winHandleBefore2);
		tearDown();
		driver.switchTo().window(winHandleBefore1);
		
		  reportPass("Alert Messages Captured");
		 
	}

	@Test(priority = 5)
	public void CreateFilter() {

		logger = report.createTest("Create Filter with valid data and Apply");

		elementClick("filterIcon");

		// Store the current window handle
		String winHandleBefore1 = driver.getWindowHandle();
		elementClick("createFilter");

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			driver.manage().window().maximize();
		}

		getElement("filterName").clear();
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\objectRepository\\InputData.xlsx";
		ReadExcelDataFile readExcel = new ReadExcelDataFile(path);

		String fltrName = readExcel.getCellData("CreateFilter", 0, 2);
		enterText("filterName", fltrName);

		String fltrDescr = readExcel.getCellData("CreateFilter", 1, 2);
		enterText("filterDescription", fltrDescr);

		String fltrOpen = readExcel.getCellData("CreateFilter", 2, 2);
		selectDropdown("filterOpen", fltrOpen);

		String fltrPrp = readExcel.getCellData("CreateFilter", 3, 2);
		selectDropdown("filterProperty", fltrPrp);

		String fltrCond = readExcel.getCellData("CreateFilter", 4, 2);
		selectDropdown("filterCondition", fltrCond);

		// Store the current window handle
		String winHandleBefore2 = driver.getWindowHandle();

		elementClick("filterCriteriaSearch");

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			driver.manage().window().maximize();
		}

		String fltrSearchUser = readExcel.getCellData("CreateFilter", 5, 2);
		enterText("searchCurrentUser", fltrSearchUser);

		elementClick("resultCurrentUser");

		elementClick("searchOK");

		// Switch back to original browser
		driver.switchTo().window(winHandleBefore2);

		String fltrClose = readExcel.getCellData("CreateFilter", 6, 2);
		selectDropdown("filterClose", fltrClose);

		takeScreenshot("Filter_details_filled");

		elementClick("filterSaveApply");

		// Switch back to original browser
		driver.switchTo().window(winHandleBefore1);

		reportPass("New Filter Created Sucessfully");

	}

	@Test(priority = 5)
	public void EditFilter() {

		logger = report.createTest("Edit and delete Filter");

		elementClick("filterIcon");

		// Store the current window handle
		String winHandleBefore3 = driver.getWindowHandle();
		elementClick("filterEdit");

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			driver.manage().window().maximize();
		}

		// Change Name
		getElement("filterName").clear();
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\objectRepository\\InputData.xlsx";
		ReadExcelDataFile readExcel = new ReadExcelDataFile(path);
		String fltrName = readExcel.getCellData("CreateFilter", 0, 3);
		enterText("filterName", fltrName);

		// add criteria
		elementClick("addCriteria");

		String fltrOpen = readExcel.getCellData("CreateFilter", 2, 3);
		selectDropdown("filterOpen2", fltrOpen);

		String fltrPrp = readExcel.getCellData("CreateFilter", 3, 3);
		selectDropdown("filterProperty2", fltrPrp);

		String fltrCond = readExcel.getCellData("CreateFilter", 4, 3);
		selectDropdown("filterCondition2", fltrCond);

		// Store the current window handle
		String winHandleBefore4 = driver.getWindowHandle();

		elementClick("filterCriteriaSearch2");

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			driver.manage().window().maximize();
		}

		String fltrSearchUser = readExcel.getCellData("CreateFilter", 5, 3);
		enterText("searchCurrentUser", fltrSearchUser);

		elementClick("resultCurrentUser");

		elementClick("searchOK");

		// Switch back to original browser
		driver.switchTo().window(winHandleBefore4);

		String fltrClose = readExcel.getCellData("CreateFilter", 6, 3);
		selectDropdown("filterClose2", fltrClose);

		takeScreenshot("Filter1");

		elementClick("filterCriteriaDelete");

		takeScreenshot("Filter2");

		elementClick("filterReset");

		takeScreenshot("Filter3");

		elementClick("filterSaveApply");

		// Switch back to original browser
		driver.switchTo().window(winHandleBefore3);

		reportPass("Filter Edit Sucessfull");

	}

	@Test(priority = 6)
	public void clearFilter() {

		logger = report.createTest("Clear Filters");

		elementClick("clearFilterIcon");

		takeScreenshot("Filter3");
		reportPass("Clear Filter Sucessful");

	}

}
