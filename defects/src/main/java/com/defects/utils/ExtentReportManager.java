package com.defects.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {
	
	public static ExtentReports report;
	
	public static ExtentReports getReportInstance() {
		
		if(report == null) {
			
			String reportName = DateUtils.getTimeStamp() + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\output\\" + reportName);
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
			
			htmlReporter.config().setDocumentTitle("Test");
		}
		
		
		return report;
	}

}
