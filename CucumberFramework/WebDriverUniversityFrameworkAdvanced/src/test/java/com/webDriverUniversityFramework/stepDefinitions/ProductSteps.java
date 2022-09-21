package com.webDriverUniversityFramework.stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import com.webDriverUniversityFramework.utils.DriverFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductSteps extends DriverFactory {
	
	String className = "ProductSteps";
	
	WebDriver driver;

	@Before
	public void setup() throws Exception {
		System.out.println("Before: Setting up the driver.");
		SessionId sessionId = null;
		
		driver = getDriver();
		sessionId = ((RemoteWebDriver) driver).getSessionId();
		System.out.println("Before:Have set up the driver with sessionId " + sessionId.toString());
	}

	@After
	public void tearDown() {
		SessionId sessionId = null;

		System.out.println("After: Closing the driver.");

		if (driver != null) {
			try {
				sessionId = ((RemoteWebDriver) driver).getSessionId();
				System.out.println("The sessionId = " + sessionId.toString());
				driver.manage().deleteAllCookies();
				driver.quit();
				driver = null;
				System.out.println("After: Have closed + quit the driver.\n");
			} catch (Exception e) {
				System.out.println("The handle to the drive is now null. Cannot quit anymore.");
			}
		} else {
			System.out.println("After: the driver is null.");
		}
	}
		
	@Given("user navivates to {string} website")
	public void user_navivates_to_website(String url) throws Exception {
	    System.out.println("user navivates to " + url + " website");
	    getDriver().get(url);
	    System.out.println(className + ": I have accessed " + url);
	}

	@When("user clicks on {string}")
	public void user_clicks_on(String button) throws Exception {
	    System.out.println("user clicks on " + button);
	    getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    // /html/body/div[1]/div/div/div/div[1]/a/div/div[1]/p
	    // #container-special-offers
	    
	    WebElement specialOffersButton = getDriver().findElement(By.cssSelector(button));
	    specialOffersButton.click();
//	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	    driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
//	    driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
	    Thread.sleep(2000); // Nothing else worked.....
	    System.out.println("user has clicked on " + button);
	}

	@Then("user should be presented with a promo alert")
	public void user_should_be_presented_with_a_promo_alert() throws Exception {
		System.out.println("user should be presented with a promo alert");
		Thread.sleep(2000);
		// /html/body/div[2]/div/div/div/div[3]/button[1]
		// /html/body/div[2]/div/div/div/div[3]/button[1]
		// //button[text()]='Proceed'
		String xPathLocator = "//button[text()='Proceed']";
		System.out.println("xPathLocator = " + xPathLocator);
		
		WebElement proceedButton = getDriver().findElement(By.xpath( xPathLocator ));
		proceedButton.click();
		System.out.println("The proceed button has been clicked.");
	}
	
}
