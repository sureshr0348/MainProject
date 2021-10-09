package com.defects.tests;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.defects.utils.TestDataProvider;

public class AttachmentsTest extends SuiteClass {

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
	public void attachmentsTabNavigate() {

		gotoFrame("contentframe");
		elementClick("attachmentsTab");
		gotoFrame("eform_seg_1966241");

	}

	@Test(dataProvider = "fileUploadData", priority = 2)
	public void test(Hashtable<String, String> dataTable) {
		logger = report.createTest("Upload Files" + dataTable.get("Format"));

		String fileName = dataTable.get("FileName");
		try {
			enterTextWithoutWait("addAttachment",
					System.getProperty("user.dir") + "\\src\\test\\resources\\uploadFiles\\" + fileName);
		} 
		catch (Exception e) {
			driver.switchTo().alert().accept();
			enterTextWithoutWait("addAttachment",
					System.getProperty("user.dir") + "\\src\\test\\resources\\uploadFiles\\" + fileName);
		}

		System.out.println("In test");

		reportPass("FILE UPLOADED SUCCESSFULLY");
	}

	@DataProvider
	public Object[][] fileUploadData() {
		return TestDataProvider.getTestData("testData", "Sheet1", "Test One");
	}

	@Test(priority = 3)
	public void singleAttachmentDelete() throws InterruptedException {
		logger = report.createTest("Delete Single attachment");

		/*List<WebElement> checkboxList = getElements("attachmentCheckboxList");

		if (checkboxList.size() == 0) {
			return;
		}

		WebElement element = checkboxList.get(0);
		element.click();*/
		
		Thread.sleep(5000);
		elementClick("attachmentCheckboxFirst");
		
		Thread.sleep(2000);
		elementClick("deleteAttachment");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(5000);
		reportPass("FILE DELETED SUCCESSFULLY");
	}

	@Test(priority = 4)
	public void allAttachmentDelete() throws InterruptedException {
		logger = report.createTest("Delete All attachment");

		elementClick("selectAllAttachment");
		elementClick("deleteAttachment");

		driver.switchTo().alert().accept();
		Thread.sleep(5000);
		reportPass("ALL FILES DELETED SUCCESSFULLY");
	}
}
