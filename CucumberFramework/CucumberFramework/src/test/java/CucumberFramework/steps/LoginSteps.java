package CucumberFramework.steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Feature: Log into account Existing user should be able to login to account
 * using correct credentials - File: Login.feature - In case of slow processing
 * errors due to timeout may occur. - Possibly use: Thread.sleep(3000); -
 * 2019-04-22: when running I see on the stackoverflow page the message "Caps
 * Lock is on" although this is not the case. - Continue here tomorrow.
 * 
 * When at home connect with BWKI via the VPN. Otherwise I can not log in via Selenium.
 * 
 * Try this tomorrow:
 * https://www.guru99.com/using-cucumber-selenium.html
 * --> Tutorial for logging in.
 * https://www.softwaretestinghelp.com/selenium-webdriver-cucumber-selenium-tutorial-31/
 * --> Idem.
 * 
 * 2019-04-23:
 * - When clicking on the login button I had to use .submit() instead of .click().
 *   Apparently the webpage has been upgraded.
 * - In some cases I have added a time-delay. Apparently this can give the system the time
 *   needed to setup. Otherwise unexplainable errors may occur.
 * 
 * @author rkremers
 *
 */
public class LoginSteps {

	WebDriver driver;
	String className = "LoginStepsScenarioOutline";

	@Before
	public void setup() {
		// Implementing the setup of the browser.
		System.setProperty("webdriver.gecko.driver",
				"/Users/rkremers/Documents/My files/Documenten/Studie/Cucumber/CucumberFramework/CucumberFramework/src/test/java/CucumberFramework/resources/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	}
	
	@After()
	public void tearDown() {
		String action = null;
		SessionId sessionId = null;
		String strSessionId = null;
		try {
			sessionId = ((RemoteWebDriver) driver).getSessionId();
			strSessionId = sessionId.toString();
			action = "deleting all cookies for the session with sessionId " + strSessionId;
			driver.manage().deleteAllCookies();
			System.out.println(className + ": All cookies deleted for the session with sessionId " + strSessionId);
			action = "closing for the session with sessionId " + strSessionId;
			driver.close();
			System.out.println(className + ": Close the current window, quitting the browser if it's the last window currently open for the session with sessionId " + strSessionId);
			
			if (sessionId != null) {
				action = "quiting for session with sessionId " + strSessionId;
				driver.quit();
				System.out.println(className + ": Quits this driver, closing every associated window for sessionId " + strSessionId);
			} else {
				System.out.println(className + ": The handle to the drive is now null. Cannot quit anymore.");
			}

		} catch (Exception e) {
			System.out.println(className + ": tearDown() gone wrong when " + action + " for the sessionId " + strSessionId);
//			throw new RuntimeException(className + ": tearDown() gone wrong when " + action + " for the sessionId " + strSessionId + ": " + e, e);
		}
	}

	@Given("^User navigates to stackoverflow website$")
	public void user_navigates_to_stackoverflow_website() {
		System.out.println("RJWK: User navigates to stackoverflow website");
		driver.get("https://stackoverflow.com/");
	}

	@Given("^User clicks on the login button on homepage$")
	public void user_clicks_on_the_login_button_on_homepage() {
		System.out.println("User clicks on the login button on homepage");
		driver.findElement(By.xpath("//a[contains(text(), 'Log In')]")).click();
	}

	@Given("^User enters a valid username$")
	public void user_enters_a_valid_username() {
		System.out.println("User enters a valid username");
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("robkremers@hotmail.com"); // changed
																								// "email"
																								// to
																								// 'email'
	}

	@Given("^User enters a valid password$")
	public void user_enters_a_valid_password() {
		System.out.println("User enters a valid password");
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("cRb988c1iR0ik6E");
		System.out.println("User has entered a valid password");
	}

	/**
	 * Note:
	 * This went wrong first (probably because Stackoverflow has been upgraded).
	 * Instead of using .click() I had to use .submit().
	 * @see https://stackoverflow.com/questions/17530104/selenium-webdriver-submit-vs-click
	 */
	@When("^User clicks on the login button$")
	public void user_clicks_on_the_login_button() {
		System.out.println("User clicks on the login button");
		// xpath: //*[@id='submit-button']
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
//		driver.findElement(By.xpath(".//*[@id=\"submit-button\"]")).click();
		driver.findElement(By.id("submit-button")).submit();
		System.out.println("User has clicked on the login button");
	}

	@Then("^User should be taken to the successful login page$")
	public void user_should_be_taken_to_the_successful_login_page() {
		System.out.println("User should be taken to the successful login page");
		// xpath: /html/body/div[3]/div[2]/div[1]/div[1]/div/a
		// css-selector: .d-inline-flex
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//		driver.findElement(By.xpath(".//a[contains(text(), 'Ask Question')]")).click();
		WebElement askQuestionsButton = driver.findElement(By.xpath("//a[contains(text(), \"Ask Question\")]"));
		Assert.assertEquals(askQuestionsButton.isDisplayed(), true, "The button 'Ask Questions' could not be found.");
		System.out.println("User has clicked on button 'Ask Question'");
		
		/*
		 * Try this tomorrow:
		 * Set handles = driver.getWindowHandles(); // get all window handles
Iterator iterator = handles.iterator();
while (iterator.hasNext()){
subWindowHandler = iterator.next();
System.out.println(“So2 “+subWindowHandler);
} --> checking on which page I am.
		 */
	}

}
