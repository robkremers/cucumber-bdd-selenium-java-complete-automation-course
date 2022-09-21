package WebDriverUniversityFramework.steps;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepsScenarioOutline {

	WebDriver driver;
	String className = "LoginStepsScenarioOutline";

	@Before()
	public void setup() throws IOException {
		// Implementing the setup of the browser.
		System.out.println("Starting up for " + className);
		System.setProperty("webdriver.gecko.driver",
				"/Users/rkremers/Documents/My files/Documenten/Studie/Cucumber/CucumberFramework/WebDriverUniversityFramework/src/test/java/WebDriverUniversityFramework/resources/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
		System.out.println(className + ": Starting a new window with sessionId " + sessionId);
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

	@Given("Outline user navigates to {string}")
	public void outline_user_navigates_to(String url) {
		System.out.println(className + ": Outline user navigates to " + url);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.get(url);
		System.out.println(className + ": Outline user has navigated to " + url);
	}

	@When("Outline user clicks on the login portal button")
	public void outline_user_clicks_on_the_login_portal_button() {
		System.out.println("Outline user clicks on the login portal button");

		// First I need to go to 'CONTACT US' because otherwise 'LOGIN PORTAL'
		// will be shielded bij the pop-up.
		WebElement loginPortalElement = driver.findElement(By.id("login-portal"));
		System.out.println("The text of the element: " + loginPortalElement.getText());
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement loginPortalTextElement = driver
				.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/a/div/div[1]/h1"));
		loginPortalTextElement.click();
		System.out.println("Clicked on the loginPortalTextElement " + loginPortalTextElement.getText());
	}

	@When("Outline user enters the {string} username")
	public void outline_user_enters_the_username(String username) {
		System.out.println("Outline user enters the " + username + " username");

		// Store the current window handle (the method will be called twice, so
		// this is necessary to identify the different web pages).
		@SuppressWarnings("unused")
		String winHandleBefore = driver.getWindowHandle();
		// Perform the click operation that opens the new window.
		// Switch to the new window.
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.findElement(By.id("text")).sendKeys(username);

	}

	@When("Outline user enters the {string} password")
	public void outline_user_enters_the_password(String password) {
		System.out.println("Outline user enters the " + password + " password");
		driver.findElement(By.id("password")).sendKeys(password);
	}

	@When("Outline user clicks on the login button")
	public void outline_user_clicks_on_the_login_button() {
		System.out.println("Outline user clicks on the login button");
		driver.findElement(By.id("login-button")).click();
	}

	@Then("Outline user should be presented with the following prompt alert {string}")
	public void outline_user_should_be_presented_with_the_following_prompt_alert(String message) {
		System.out.println("Outline user should be presented with the following prompt alert " + message);
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert text = " + alert.getText());
		System.out.println("Expected message = " + message);
		Assert.assertEquals(alert.getText(), message);
		alert.accept(); // Only place this at the end of the functionality. I
						// will close the
		System.out.println("End of test with expected prompt alert " + message);

	}

}
