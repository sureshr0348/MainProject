package com.defects.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SortAndSearchTest extends SuiteClass {

	public void sort(String primsort, String order1) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"multipleSortButton-btnIconEl\"]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('primaryColumn-inputEl').setAttribute('value', '" + primsort + "')");
		js.executeScript("document.getElementById('primaryOrderBy-inputEl').setAttribute('value', '" + order1 + "')");
		
		WebElement element = driver.findElement(By.xpath("/html/body/div[13]/div[2]/div/div[2]/div/div/a[1]/span/span/span[2]"));
		js.executeScript("arguments[0].click()", element);
		//js.executeScript("document.getElementById('button-1104-btnInnerEl').click()");
		Thread.sleep(5000);
	}

	public void sort(String primsort, String order1, String secsort, String order2) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"multipleSortButton-btnIconEl\"]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('primaryColumn-inputEl').setAttribute('value', '" + primsort + "')");
		js.executeScript("document.getElementById('primaryOrderBy-inputEl').setAttribute('value', '" + order1 + "')");
		js.executeScript("document.getElementById('secondaryColumn-inputEl').setAttribute('value', '" + secsort + "')");
		js.executeScript("document.getElementById('secondaryOrderBy-inputEl').setAttribute('value', '" + order2 + "')");
		WebElement element = driver.findElement(By.xpath("/html/body/div[13]/div[2]/div/div[2]/div/div/a[1]/span/span/span[2]"));
		js.executeScript("arguments[0].click()", element);
		//js.executeScript("document.getElementById('button-1104-btnInnerEl').click()");
		Thread.sleep(5000);
	}

	public void sort(String primsort, String order1, String secsort, String order2, String tersort, String order3)
			throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"multipleSortButton-btnIconEl\"]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('primaryColumn-inputEl').setAttribute('value', '" + primsort + "')");
		js.executeScript("document.getElementById('primaryOrderBy-inputEl').setAttribute('value', '" + order1 + "')");
		js.executeScript("document.getElementById('secondaryColumn-inputEl').setAttribute('value', '" + secsort + "')");
		js.executeScript("document.getElementById('secondaryOrderBy-inputEl').setAttribute('value', '" + order2 + "')");
		js.executeScript("document.getElementById('tertiaryColumn-inputEl').setAttribute('value', '" + tersort + "')");
		js.executeScript("document.getElementById('tertiaryOrderBy-inputEl').setAttribute('value', '" + order3 + "')");
		WebElement element = driver.findElement(By.xpath("/html/body/div[13]/div[2]/div/div[2]/div/div/a[1]/span/span/span[2]"));
		js.executeScript("arguments[0].click()", element);
		//js.executeScript("document.getElementById('button-1104-btnInnerEl').click()");
		Thread.sleep(5000);
	}

	public void clearsort() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='multipleSortButton-btnIconEl']")).click();
		Thread.sleep(2000);
		// clear button
		driver.findElement(By.id("button-1113-btnInnerEl")).click();
		Thread.sleep(2000);
		// go button
		driver.findElement(By.xpath("//*[@id='button-1112-btnInnerEl']")).click();
		Thread.sleep(2000);
	}

	public void search(String id) {
		driver.findElement(By.id("searchTextBox-inputEl")).clear();
		driver.findElement(By.id("searchTextBox-inputEl")).sendKeys(id);
		driver.findElement(By.xpath("//*[@id='searchButton-btnEl']")).click();
	}

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

	//@Test
	public void sortTest() throws InterruptedException {

		logger = report.createTest("Sorting");
//
//		sort("Name", "Ascending");
//		Thread.sleep(2000);
//		takeScreenshot("SingleSort");
//		clearsort();
//
//		Thread.sleep(2000);
//		sort("Name", "Ascending", "ID", "Descending");
//		Thread.sleep(2000);
//		takeScreenshot("DoubleSort");
//		clearsort();
		
		sort("Name", "Ascending", "ID", "Descending", "Priority", "Descending");
		Thread.sleep(2000);
		takeScreenshot("TripleSort");

		clearsort();
		
		reportPass("Sorting test completed");
	}
	
	@Test(priority = 2)
	public void searchTest() throws InterruptedException {
		
		logger = report.createTest("Defect Search");
		
		search("DEF2198");
		Thread.sleep(2000);
		takeScreenshot("SearchID");
		
		search("Suresh");
		Thread.sleep(2000);
		takeScreenshot("SearchName");
		
		reportPass("test completed");
	}
}
