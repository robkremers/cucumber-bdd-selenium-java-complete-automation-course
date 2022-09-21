package com.webDriverUniversityFramework.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * features  : points to the location of the feature files.
 * glue      : points to the location of the step definition java files.
 * tags      : e.g. {"@ProductsScenario"} placed in the feature files. 
 *             Provides flexibility to target specific tests.
 *             Note that when looking at the logging the ContactUs feature file has actually been started.
 *             However it's tests are not executed.
 * dryRun = true:
 *   Checks that feature files have matching step definitions.
 *   True = will just check whether matching steps are visible but will not execute the code within the steps.
 * monochrome: alters the output of the console window (making readable or not readable)
 * plugin    : Used to create reports
 * 
 * Note:
 * I have renamed the existing package 'featureFiles' to lowercase but the actual directory is still 'FeatureFiles'.
 * Idem the package 'WebDriverUniversityFramework.
 * 
 * @author rkremers
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions( features = { "src/test/java/com/webDriverUniversityFramework/featureFiles/" }
, glue = {"com.webDriverUniversityFramework.stepDefinitions" }
, monochrome = true // false: leads to weird characters in the console output.
//, tags = {"@ProductsScenario"}
, tags = {}
, dryRun = false
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
		   , "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		   })
public class MainRunner {

}
