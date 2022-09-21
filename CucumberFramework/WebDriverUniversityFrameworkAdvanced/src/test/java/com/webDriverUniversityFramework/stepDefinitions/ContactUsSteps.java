package com.webDriverUniversityFramework.stepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import com.webDriverUniversityFramework.utils.DriverFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class ContactUsSteps extends DriverFactory {
	
	String className = "ContactUsSteps";
	
	public ContactUsSteps() {
		super();
	}
	
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
	
	@Given("I access webdriveruniversity Contact Us form")
	public void i_access_webdriveruniversity_Contact_Us_form() throws Exception {
	    System.out.println("I access webdriveruniversity Contact Us form");
		getDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		getDriver().get("http://www.webdriveruniversity.com/Contact-Us/contactus.html");
		System.out.println(className + ": I have accessed webdriveruniversity");
	}

	@And("I enter a valid first name")
	public void i_enter_a_valid_first_name() throws Exception {
		System.out.println("I enter a valid first name");
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// Both methods for finding the inputfield 'first_name' work.
//		WebElement firstNameField = driver.findElement(By.cssSelector("input.feedback-input:nth-child(1)"));
		WebElement firstNameField = getDriver().findElement(By.cssSelector("input[name='first_name']"));
		firstNameField.sendKeys("Rob");
		System.out.println("Value of firstNameField = " + firstNameField.getText());
	}

	@And("I enter a valid last name")
	public void i_enter_a_valid_last_name(DataTable dataTable) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		System.out.println("I enter a valid last name");
		List<String> lastNames = dataTable.asList();
		WebElement lastNameField = getDriver().findElement(By.name("last_name"));
		lastNameField.sendKeys(lastNames.get(0));		
	}

	@And("I enter a valid email address")
	public void i_enter_a_valid_email_address() throws Exception {
		System.out.println("I enter a valid email address");
		WebElement emailField = getDriver().findElement(By.name("email"));
		emailField.sendKeys("woods@test.nl");
		getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	@And("I enter comments")
	public void i_enter_comments(DataTable dataTable) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		System.out.println("I enter comments");
		List<String> comments = dataTable.asList();
		for (String element: comments) {
			System.out.println("element = " + element);
			getDriver().findElement(By.name("message")).sendKeys(element + "\n");
		}
	}

	@When("I click on the submit button")
	public void i_click_on_the_submit_button() throws Exception {
		System.out.println("I click on the submit button");
		// xpath:
		// /html/body/div[1]/div/div/section/div/div[2]/form/div/input[2] or
		// //input[@value='SUBMIT']
//		WebElement submitButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/section/div/div[2]/form/div/input[2]"));
		WebElement submitButton = getDriver().findElement(By.className("contact_button"));
		submitButton.submit();
		getDriver().manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	}

	/**
	 * Now we have not switched to a new web-page.
	 * The page itself has been refreshed and new elements have been added.
	 * Therefore in order to give the step enough time now we don't use 
	 * driver.manage().timeouts().pageLoadTimeout() but driver.manage().timeouts().implicitlyWait().
	 * 
	 * Notice the xpath:
	 * - By.xpath("/html/body/div/div/div/h1") or
	 * - By.xpath(".//*[@id='contact_reply']/h1")
	 * Both will point not to the id but to the following message after <h1>
	 * 
	 * !! Study the various methods to point to elements on a page!
	 * @throws Exception 
	 * 
	 */
	@Then("The information should successfully be submitted via the contact us form")
	public void the_information_should_successfully_be_submitted_via_the_contact_us_form() throws Exception {
		System.out.println("The information should successfully be submitted via the contact us form");
		// xpath: /html/body/div/div/div/h1
		// id="contact-reply"

        System.out.println("Switched to the new window");
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String messageUrl = getDriver().getCurrentUrl();
		System.out.println("messageUrl = " + messageUrl);
		String pageTitle = getDriver().getTitle();
		System.out.println("titlePage = " + pageTitle);
//		String pageSource = driver.getPageSource();
//		System.out.println("pageSource = " + pageSource + "\n");

//		WebElement messageElement = driver.findElement(By.xpath("/html/body/div/div/div/h1"));
		WebElement messageElement = getDriver().findElement(By.xpath(".//*[@id='contact_reply']/h1"));
		System.out.println("Actual text = " + messageElement.getText());
		String message = messageElement.getText().toLowerCase().replaceAll(" ", "");
		System.out.println("Text lowercase and without spaces = " + message);
		Assert.assertEquals("thankyouforyourmessage!", message);
		System.out.println("Successful test of the thank-you reply.");
	}

}
