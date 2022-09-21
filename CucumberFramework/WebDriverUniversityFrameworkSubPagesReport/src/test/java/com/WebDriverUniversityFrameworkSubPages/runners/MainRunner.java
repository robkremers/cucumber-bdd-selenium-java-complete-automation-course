package com.WebDriverUniversityFrameworkSubPages.runners;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.WebDriverUniversityFrameworkSubPages.pageObjects.BasePage;
import com.WebDriverUniversityFrameworkSubPages.utils.Constant;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)

@CucumberOptions(features = { "src/test/java/com/WebDriverUniversityFrameworkSubPages/features/" }
, glue = { "com.WebDriverUniversityFrameworkSubPages.stepDefinitions" }
, monochrome = true
, tags = {}
, plugin = { "pretty"
		   , "html:target/cucumber-html-report"
		   , "json:target/cucumber.json"
		   , "pretty:target/cucumber-pretty.txt"
		   , "usage:target/cucumber-usage.json"
		   , "junit:target/cucumber-results.xml"
		   /* The following is out of date.
		    * It was used in the course which used +/- 2016 era libraries, but I have updated all libraries when I started the course.
		    * Per 2016 many libraries have been updated / moved to other repositories on Maven Repository.
		    */
//		   , "com.cucumber.listener.ExtentCucumberFormatter:output/report.html" 
		   , "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:output"
		   })
public class MainRunner extends AbstractTestNGCucumberTests {
		
	@AfterClass
	public static void writeExtentReport() {
		
		File reportsConfigFile = new File(Constant.REPORTS_CONFIG_XML);
		if (reportsConfigFile.exists()  & reportsConfigFile.isFile()) {
			System.out.println("\n*** The file " + Constant.REPORTS_CONFIG_XML + " exists. ***\n");
		} else {
			throw new RuntimeException("The file " + Constant.REPORTS_CONFIG_XML + " does not exist or is not a file.");
		}
		
//		try {
//		  Reporter.loadXMLConfig(reportsConfigFile);
//		} catch (Exception e) {
//			throw new RuntimeException( "\n*** Class MainRunner, method writeExtentReport(): could not load the ReportsConfig.xml ***\n", e);
//		}
//		
		try {
			BasePage.copyLatestExtentReport();
		} catch (Exception e) {
			throw new RuntimeException("\n*** Class MainRunner, method writeExtentReport(): could not copy the latest ExtentReport ***\n",e);
		}
	}
}