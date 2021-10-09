package com.defects.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.defects.utils.ExtentReportManager;

public class BaseUI {

	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports report;
	public static ExtentTest logger;

	SoftAssert softAssert = new SoftAssert();

	/*
	 * Constructor - set up properties file
	 */
	public BaseUI() {
		try {
			if (prop == null) {
				prop = new Properties();

				try {
					FileInputStream file = new FileInputStream(System.getProperty("user.dir")
							+ "\\src\\test\\resources\\objectRepository\\Config.properties");
					prop.load(file);
				} catch (FileNotFoundException e) {
					reportFail(e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					reportFail(e.getMessage());
					e.printStackTrace();
				}
			}
			report = ExtentReportManager.getReportInstance();
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/*
	 *  Invoke the browser - Setup the driver and open browser window and maximise it.
	 *  Page Load Timeout set to 60s.
	 */
	public void invokeBrowser() {

		String browserName = prop.getProperty("browserChoice");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\msedgedriver.exe");
			driver = new EdgeDriver();

		} else {
			System.out.println("Invalid browser name");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}

	
	
	/***************** Handling Browser Window(driver) Methods *****************/
	
	/*
	 *  open the website URL of provided
	 */
	public void openURL(String websiteURLKey) {

		try {
			String websiteURL = prop.getProperty(websiteURLKey);
			driver.get(websiteURL);
			logger.log(Status.PASS, "URL Identified");
		} catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
		}
	}

	
	/*
	 *  Close the browser window 
	 */
	public void tearDown() {
		driver.close();
		logger.log(Status.INFO, "Closed the browser");
	}

	
	/*
	 *  Close all browser windows
	 */
	public void quitBrowser() {
		driver.quit();
		logger.log(Status.INFO, "quits the driver");
	}

	
	/*
	 * Switch to a IFrame in the web page
	 */
	public void gotoFrame(String frameNameOrId) {
		driver.switchTo().frame(frameNameOrId);
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		String currentFrame = (String) jsExecutor.executeScript("return self.name");
		//System.out.println("Frame: " + currentFrame);
		reportInfo("Switched to Frame : " + currentFrame);
	}
	
	public void gotoFrame(int index) {
		driver.switchTo().frame(index);
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		String currentFrame = (String) jsExecutor.executeScript("return self.name");
		System.out.println("Frame: " + currentFrame);
		reportInfo("Switched to Frame : " + currentFrame);
	}
	
	/******************** Handling WebElements Methods ************************/
	
	/*
	 *  Enter text in Input fields
	 *  Key of the element locator from the .properties file and the test data 
	 *  	to be entered is passed as parameters
	 */
	public void enterText(String webElementKey, String data) {
		try {
			WebElement element = getElement(webElementKey);
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			
			element.click();
			element.sendKeys(data);
			logger.log(Status.PASS, "The text is entered");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	
	/*
	 *  To click on a element
	 *  Key of the element locator from the .properties file is passed as parameter.
	 */
	public void elementClick(String webElementKey) {
		try {
			WebElement element = getElement(webElementKey);
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			
			element.click();
			logger.log(Status.PASS, "The button is clicked");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	
	/********************* WebDriver Waits *************************/
	
	public void implicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public void explicitWaitClickable(String webElementKey) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = getElement(webElementKey);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void explicitWaitTitle(String titleExpected) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.titleContains(titleExpected));
	}
	
	
	/******************* Get Element Methods *************************/
	
	public WebElement getElement(String webElementKey) {
		WebElement element=null;
		
		try {
			String locator = prop.getProperty(webElementKey);
			element = driver.findElement(By.xpath(locator));
			logger.log(Status.INFO, "WebElement identified : " + webElementKey);
		}
		catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
			Assert.fail("Failing the testcase, Incorrect locator name : " + webElementKey);
		}
		
		return element;
		/*
		try {
			if (webElementKey.endsWith("_id")) {
				String webElementId = prop.getProperty(webElementKey);
				element = driver.findElement(By.id(webElementId));
				logger.log(Status.INFO, "WebElementId identified : " + webElementId);

			} else if (webElementKey.endsWith("_name")) {
				String webElementName = prop.getProperty(webElementKey);
				element = driver.findElement(By.name(webElementName));
				logger.log(Status.INFO, "WebElementName identified : " + webElementName);

			} else if (webElementKey.endsWith("_xpath")) {
				String webElementXpath = prop.getProperty(webElementKey);
				element = driver.findElement(By.xpath(webElementXpath));
				logger.log(Status.INFO, "WebElementXpath identified : " + webElementXpath);

			} else if (webElementKey.endsWith("_css")) {
				String webElementCss = prop.getProperty(webElementKey);
				element = driver.findElement(By.cssSelector(webElementCss));
				logger.log(Status.INFO, "WebElementCSS identified : " + webElementCss);

			} else if (webElementKey.endsWith("_linkText")) {
				String webElementLinkText = prop.getProperty(webElementKey);
				element = driver.findElement(By.linkText(webElementLinkText));
				logger.log(Status.INFO, "WebElementLinkText identified : " + webElementLinkText);

			} else if (webElementKey.endsWith("_partialLinkText")) {
				String webElementPartialLinkText = prop.getProperty(webElementKey);
				element = driver.findElement(By.partialLinkText(webElementPartialLinkText));
				logger.log(Status.INFO, "WebElementPartialLinkText identified : " + webElementPartialLinkText);

			} else if (webElementKey.endsWith("_className")) {
				String webElementClassName = prop.getProperty(webElementKey);
				element = driver.findElement(By.className(webElementClassName));
				logger.log(Status.INFO, "WebElementClassName identified : " + webElementClassName);

			} else if (webElementKey.endsWith("_tagName")) {
				String webElementTagName = prop.getProperty(webElementKey);
				element = driver.findElement(By.tagName(webElementTagName));
				logger.log(Status.INFO, "WebElementTagName identified : " + webElementTagName);

			} else {
				reportFail("Failing the testcase, Invalid locator : " + webElementKey);
				Assert.fail("Failing the testcase, Invalid locator : " + webElementKey);
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();

			Assert.fail("Failing the testcase, Incorrect locator name : " + webElementKey);
		}*/
	}

	
	
	public List<WebElement> getElements(String webElementKey) {
		List<WebElement> elements = new ArrayList<WebElement>();
		
		try {
			String locator = prop.getProperty(webElementKey);
			elements = driver.findElements(By.xpath(locator));
			logger.log(Status.INFO, "List of WebElements identified : " + locator);
		}
		catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
			Assert.fail("Failing the testcase, Incorrect locator name : " + webElementKey);
		}
		
		return elements;
	}
	
	
	
	/********************* Verify Element *******************/

	public boolean isElementPresent(String webElementKey) {

		try {
			getElement(webElementKey).isDisplayed();
			reportPass(webElementKey + " : Element is displayed");
			return true;
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		return false;

	}

	public boolean isElementEnabled(String webElementKey) {

		try {
			getElement(webElementKey).isEnabled();
			reportPass(webElementKey + " : Element is displayed");
			return true;
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		return false;

	}

	public boolean isElementSelected(String webElementKey) {

		try {
			getElement(webElementKey).isSelected();
			reportPass(webElementKey + " : Element is displayed");
			return true;
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		return false;

	}

	
	
	/********************* Assertion Functions *******************/

	public void assertTrue(boolean flag) {
		softAssert.assertTrue(flag);
	}

	public void assertFalse(boolean flag) {
		softAssert.assertFalse(flag);
	}

	public void assertEquals(String actual, String expected) {
		softAssert.assertEquals(actual, expected);
	}
	
	/********************* Reporting Functions *******************/

	public void reportFail(String reportString) {

		logger.log(Status.FAIL, reportString);

	}

	public void reportPass(String reportString) {

		logger.log(Status.PASS, reportString);
	}
	
	public void reportInfo(String reportString) {

		logger.log(Status.INFO, reportString);
	}

	
	/********************* Take Screenshot Function *******************/
	public void takeScreenshot(String name) {

		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(
				System.getProperty("user.dir") + "\\ScreenShots\\" + name + ".png");

		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "\\ScreenShots\\" + name + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		reportInfo("Screenshot " + name + " captured and saved");
	}

	
	
	/********************* Manage Dropdown and Checkbox *******************/

	public void selectDropdown(String webElementKey, String visibleText) {

		WebElement element = getElement(webElementKey);

		Select select = new Select(element);

		try {
			select.selectByVisibleText(visibleText);
			logger.log(Status.PASS, "dropdown is selected - " + visibleText);
		} catch (Exception e) {
			logger.log(Status.FAIL, "Invalid visible text for element - " + visibleText);
			e.printStackTrace();
		}
	}

	public List<WebElement> getOptions(String webElementKey) {

		WebElement element = getElement(webElementKey);
		Select select = new Select(element);
		List<WebElement> values = select.getOptions();
		return values;
	}

	
	/********** Move Mouse Pointer Over Element **********/
	
	public void mousehover(String webElementKey) {
		WebElement element = getElement(webElementKey);
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
	}
	
	
	/********************* Drag and Drop *******************/

}
