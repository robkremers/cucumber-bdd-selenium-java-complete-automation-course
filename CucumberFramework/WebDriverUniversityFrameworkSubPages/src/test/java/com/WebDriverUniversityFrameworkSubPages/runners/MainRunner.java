package com.WebDriverUniversityFrameworkSubPages.runners;

import org.junit.runner.RunWith;

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
//		   , "com.cucumber.listener.ExtendCucumberFormatter: target/report.html" 
		   , "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:output/report.html"
		   })
public class MainRunner extends AbstractTestNGCucumberTests {
		
}