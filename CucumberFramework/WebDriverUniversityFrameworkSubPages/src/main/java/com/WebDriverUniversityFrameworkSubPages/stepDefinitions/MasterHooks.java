package com.WebDriverUniversityFrameworkSubPages.stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.WebDriverUniversityFrameworkSubPages.pageObjects.BasePage;
import com.WebDriverUniversityFrameworkSubPages.utils.DriverFactory;

public class MasterHooks extends DriverFactory {

	@Before
	public void setup() {
		driver = getDriver();
	}

	@After
	public void tearDown(Scenario scenario) {
		try {
			
			if (driver != null && scenario.isFailed()) {
				System.out.println("\n*** driver != null && scenario.isFailed() ***\n");
				/**
				 * The following will ensure that a screen is saved if a step fails.
				 * Make sure that a proper png handler plugin is available.
				 * 
				 * This works:
                 * Do not view the resulting report via Eclipse but via a normal browser. 
                 * For some reason a cache in Eclipse prevents you from seeing the correct images if they exist.
				 * 
				 */
				scenario.embed( ( (TakesScreenshot)driver ).getScreenshotAs(OutputType.BYTES), "image/png");
				
			} else {
				System.out.println("\n*** NO FAILURE ***\n");
			}
			
			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
				driver = null;
			}
		} catch (Exception e) {
			System.out.println("Method tearDown: Exception: " + e.getMessage());
		}
	}

}