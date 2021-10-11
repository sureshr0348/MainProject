package com.defects.tests;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PreviousVersionsTest extends SuiteClass {

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
	public void navigateToPreviousVersions() throws InterruptedException {

		logger = report.createTest("Previous_Page_Navigate");

		gotoFrame("contentframe");
		elementClick("previousVersionTab");

		Thread.sleep(2000);

		takeScreenshot("Previous_Versions_tab");

		reportPass("Navigated to Previous Version tab successfully");
	}

	@Test(priority = 2)
	public void invalidVersionDifference() throws InterruptedException {

		logger = report.createTest("Invalid_Version_Difference");

		gotoFrame("eform_seg_1966239");
		elementClick("versionDifference");

		Thread.sleep(2000);

		Alert alert = driver.switchTo().alert();
		alert.accept();

		takeScreenshot("Invalid_Version_Difference");

		reportPass("Alert message displayed successfully");
	}

	@Test(priority = 3)
	public void VersionDifference() throws InterruptedException {

		logger = report.createTest("Version_Difference");

		List<WebElement> defects = getElements("versionCheckboxList");
		WebElement element1 = defects.get(0);
		element1.click();

		WebElement element2 = defects.get(1);
		element2.click();

		// Store the current window handle
		String winHandleBefore1 = driver.getWindowHandle();

		elementClick("versionDifference");

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			driver.manage().window().maximize();
		}

		Thread.sleep(2000);

		takeScreenshot("Version_Difference");

		elementClick("versionDifferenceReturn");

		driver.switchTo().window(winHandleBefore1);

		reportPass("Version Difference Displayed successfully");
	}

}
