package com.defects.tests;


import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.defects.utils.TestDataProvider;

public class LoginTest extends SuiteClass {
	
	@Test(priority = 1)														
	public void Login() throws InterruptedException {			
		Scanner sc = new Scanner(System.in);
		logger = report.createTest("Login");
		Thread.sleep(3000);
		
		if(!(driver.getTitle().equalsIgnoreCase("mainspring"))) {
			
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
		}
		
		Thread.sleep(5000);
		takeScreenshot("mainspring_homepage");
		sc.close();
		reportPass("Login Completed");
	}

	@DataProvider
	public Object[][] getTestOneData(){
		return TestDataProvider.getTestData("TestData_TestManagement", "SampleData", "Test One");
	}

	
	@Test(priority = 2)
	public void navigateToDefectsPage() throws InterruptedException {
		logger = report.createTest("Navigate_To_Defects");
		
		mousehover("menu");
		elementClick("projectLink");
		mousehover("executeMenu");
		elementClick("defectsLink");
		
		Thread.sleep(3000);
		takeScreenshot("Defects_page");
		reportPass("Navigate_To_Defects completed");
	}
	
	
	//@Test(priority = 3)
	public void selectDefect() throws InterruptedException {
		
		List<WebElement> defects = driver.findElements(By.xpath(prop.getProperty("defectsList")));
		WebElement element = defects.get(0);
		element.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains("AutomationTestDummy"));
		
		gotoFrame("contentframe");
		elementClick("attachmentsTab");
		gotoFrame("contentframe");
		gotoFrame(3);
		
		
		Thread.sleep(5000);
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		String currentFrame = (String) jsExecutor.executeScript("return self.name");
		System.out.println(currentFrame);

		//elementClick("addAttachment");
		//driver.findElement(By.xpath(prop.getProperty("addAttachment"))).sendKeys("C:\\\\Users\\\\922137\\\\OneDrive - Cognizant\\\\Desktop\\\\Config.properties");
		//enterText("addAttachment", "C:\\Users\\922137\\OneDrive - Cognizant\\Desktop\\Config.properties");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/div/div/div[1]/div/div"));
	}
}

