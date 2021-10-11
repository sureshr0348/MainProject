package com.defects.tests;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WorkFlowTest extends SuiteClass {

	@BeforeClass
	public void openDefect() {

		if (!(driver.getTitle().equalsIgnoreCase("Defects List"))) {
			driver.get(prop.getProperty("baseURL"));
			mousehover("menu");
			elementClick("projectLink");
			mousehover("executeMenu");
			elementClick("defectsLink");
		}

		List<WebElement> defects = getElements("defectsList");
		WebElement element = defects.get(0);
		element.click();
		explicitWaitTitle("Defect : Details");
	}

	@Test(priority = 1)
	public void assignParticipants() {
		
		logger = report.createTest("Assign Participants");
		
		gotoFrame("contentframe");
		elementClick("workflowTab");
		gotoFrame("eform_seg_1966235");

		selectDropdown("initiatorDropdown", "Suresh R");
		selectDropdown("performerDropdown", "Suresh R");
		selectDropdown("approverDropdown", "Suresh R");
		
		takeScreenshot("AssignedPeople");
	}

	@Test(priority = 2)
	public void RouteToPerformer() throws InterruptedException {
		
		logger = report.createTest("Route to performer");
		driver.switchTo().parentFrame();
		//gotoFrame("contentframe");
		elementClick("saveBtn");
		Thread.sleep(5000);
		String parent = driver.getWindowHandle();
		elementClick("RouteBtn");
		Thread.sleep(5000);

		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {
			String child_window = I1.next();
			driver.switchTo().window(child_window);
			if (driver.getTitle() == "Add Comment for Routing") {
				System.out.println(driver.getTitle());
				break;
			}
		}
		enterText("newTabDescription", "This is routed to performer");
		elementClick("newTabOkBtn");
		driver.switchTo().window(parent);
		Thread.sleep(3000);
		
	}

	@Test(priority = 3)
	public void RouteToApprover() throws InterruptedException {
		
		logger = report.createTest("Route to Approver");
		
//		elementClick("returnButton");
//		Thread.sleep(2000);
		driver.navigate().refresh();
		
		mousehover("menu");
		elementClick("projectLink");
		mousehover("executeMenu");
		elementClick("defectsLink");
		
		List<WebElement> defects = getElements("defectsList");
		WebElement element = defects.get(0);
		element.click();
		
		explicitWaitTitle("Defect : Details");
		
		gotoFrame("contentframe");
		
		selectDropdown("statusBtnInDetails", "Resolved");
		selectDropdown("typeOfDefectInDetails", "Automation Error");
		enterText("resolutionTextAreaInDetails", "This is resolution in Performer");
		selectDropdown("defectCauseInDetails", "Miscommunication");
		elementClick("saveBtn");
		Thread.sleep(5000);
		String parent = driver.getWindowHandle();
		elementClick("RouteBtn");
		Thread.sleep(5000);
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {
			String child_window = I1.next();
			driver.switchTo().window(child_window);
			if (driver.getTitle() == "Add Comment for Routing") {
				System.out.println(driver.getTitle());
				break;
			}
		}
		enterText("newTabDescription", "This is routed to Approver");
		elementClick("newTabOkBtn");
		driver.switchTo().window(parent);
	}
	
	@Test(priority = 4)
	public void closeDefect() throws InterruptedException {
		
		logger = report.createTest("Close Defect");
		
//		elementClick("returnButton");
//		Thread.sleep(2000);
        driver.navigate().refresh();
		
		mousehover("menu");
		elementClick("projectLink");
		mousehover("executeMenu");
		elementClick("defectsLink");
		
		List<WebElement> defects = getElements("defectsList");
		WebElement element = defects.get(0);
		element.click();
		
		explicitWaitTitle("Defect : Details");
		
		gotoFrame("contentframe");
		
//		selectDropdown("statusBtnInDetails", "Resolved");
//		selectDropdown("typeOfDefectInDetails", "Automation Error");
//		enterText("resolutionTextAreaInDetails", "This is resolution in Performer");
//		selectDropdown("defectCauseInDetails", "Miscommunication");
		
//		elementClick("saveBtn");
//		Thread.sleep(5000);
		
		String parent = driver.getWindowHandle();
		
		elementClick("RouteBtn");
		Thread.sleep(5000);
		
		driver.switchTo().alert().accept();
		Thread.sleep(5000);
		// driver.switchTo().window(parent);
		
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();
		
		while (I1.hasNext()) {
			String child_window = I1.next();
			driver.switchTo().window(child_window);
			if (driver.getTitle() == "Add Comment for Routing") {
				System.out.println(driver.getTitle());
				break;
			}
		}
		
		enterText("newTabDescription", "This is final description");
		elementClick("newTabOkBtn");
		//driver.switchTo().window(parent);
	}
	
}
