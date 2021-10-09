package com.defects.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CommentsTest extends SuiteClass{

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
	public void navigateToCommentsTab() throws InterruptedException {
		logger = report.createTest("Add_Comment_Navigate");
		
		gotoFrame("contentframe");
		elementClick("commentsTab");
		
		Thread.sleep(2000);
		takeScreenshot("Comments_tab");
		
		reportPass("Comments tab opened successfully");
	}
	
	
	@Test(priority = 2)
	public void postComment() throws InterruptedException {
		logger = report.createTest("Add_Comment_Post");
		
		gotoFrame("eform_seg_1966238");	
		
		enterText("commentBox", "This is a test comment added by a automation test");
		elementClick("addCommentButton");
		
		Thread.sleep(2000);
		takeScreenshot("Comments_Posted");
		
		reportPass("Comments posted in the comments tab successfully");
	}
	
	@Test(priority = 3)
	public void replyComment() throws InterruptedException {
		logger = report.createTest("Reply_Comment_Post");
		
		List<WebElement> replyLinks = getElements("commentsReplyLinksList");
		if (replyLinks.size() == 0) {
			return;
		}
		
		WebElement element = replyLinks.get(0);
		element.click();
		
		enterText("replyCommentBox", "Automated Reply");
		elementClick("replyPostButton");
		
		Thread.sleep(2000);
		takeScreenshot("Comments_Reply_Posted");
		
		reportPass("Reply Comment posted successfully");
	}
}
