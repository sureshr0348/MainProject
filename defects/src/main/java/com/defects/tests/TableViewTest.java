package com.defects.tests;

import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TableViewTest extends SuiteClass {

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
    
	@Test(priority=1)
	public void createInvalidTable() {
		elementClick("tableIcon");

		// Store the current window handle
		String winHandleBefore1 = driver.getWindowHandle();

		elementClick("createTable");
		
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			driver.manage().window().maximize();
		}
		
		enterText("tableViewName","Test_Table");
		
		elementClick("tableViewSave");
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		driver.switchTo().window(winHandleBefore1);

	}
	
	@Test(priority=2)
	public void createValidTable() {
		elementClick("tableIcon");

		// Store the current window handle
		String winHandleBefore1 = driver.getWindowHandle();

		elementClick("createTable");
		
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			driver.manage().window().maximize();
		}
		
		enterText("tableViewName","Test_Table");
		
		enterText("tableViewDescription","Test table description");
		
		elementClick("tableOption1");
		
		elementClick("tableOptionAdd");
		
		elementClick("tableOption2");
		
		elementClick("tableOptionAdd");
		
		elementClick("tableOption3");
		
		elementClick("tableOptionAdd");
		
		elementClick("tableOption4");
		
		elementClick("tableOptionAdd");
		
        elementClick("tableOptionCheckBox");
        
		elementClick("tableViewSave");
		

		driver.switchTo().window(winHandleBefore1);		
		
		

	}
	
	@Test(priority=3)
	public void EditTable() {
		
		elementClick("tableIcon");

		// Store the current window handle
		String winHandleBefore1 = driver.getWindowHandle();

		elementClick("editTable");
		
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			driver.manage().window().maximize();
		}
		
		getElement("tableViewName").clear();
		enterText("tableViewName","Test_Table_edit");
		
		getElement("tableViewDescription").clear();
		enterText("tableViewDescription","Edit table description");
		
		
		elementClick("tableOptionAddAll");
		
		elementClick("tableSelectedOption1");
		
		elementClick("tableOptionRemove");
		
		elementClick("tableSelectedOption2");
		
		elementClick("tableOptionMoveUp");
		
		elementClick("tableOptionMoveDown");
		
		elementClick("tableViewReset");
		
        elementClick("tableViewDelete");
        
		
		Alert alert = driver.switchTo().alert();
		alert.accept();

		tearDown();

		driver.switchTo().window(winHandleBefore1);		
		
		

	}
	
	

}
