package WebDriverUniversityFramework.steps;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class LoginSteps {
	
	WebDriver driver;
	String    className = "LoginSteps";
	
//	@Before()
	public void setup() throws IOException {
		// Implementing the setup of the browser.
		System.out.println("Starting up for " + className);
		System.setProperty("webdriver.gecko.driver",
				"/Users/rkremers/Documents/My files/Documenten/Studie/Cucumber/CucumberFramework/WebDriverUniversityFramework/src/test/java/WebDriverUniversityFramework/resources/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	}
	
//	@After()
	public void tearDown() {
		String action = null;
		try {
			action = "deleting all cookies";
			driver.manage().deleteAllCookies();
			System.out.println("All cookies deleted.");
			action = "closing";
			driver.close();
			System.out.println("Close the current window, quitting the browser if it's the last window currently open.");
			action = "quiting";
			driver.quit();
			System.out.println("Quits this driver, closing every associated window.");
			
		} catch (Exception e) {
			throw new RuntimeException(className + ": tearDown() gone wrong when " + action + ": " + e, e);
		}
	}
	
	/**
	 * Note:
	 * Because this step is part of the background and is used in two scenarios
	 * the method will be accessed twice and therefore will start up a new Firefox browser twice.
	 * 
	 */
	@Given("I access webdriverunivsity.com")
	public void i_access_webdriverunivsity_com() {
		System.out.println("I access webdriveruniversity");
		driver.get("http://www.webdriveruniversity.com");
	}
	
	@When("I scroll to the login portal button")
	public void i_scroll_to_the_login_portal_button() {
		System.out.println("I scroll to the login portal button");
		// xpath: /html/body/div[1]/div/div[2]/div[3]/a/div/div[1]/h1
		// id="login-portal"
		// xpath: for 'LOGIN PORTAL': /html/body/div[1]/div/div[2]/div[3]/a/div/div[1]/h1
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// First I need to go to 'CONTACT US' because otherwise 'LOGIN PORTAL' will be shielded bij the pop-up.
		WebElement loginPortalElement = driver.findElement(By.id("login-portal"));
		System.out.println("The text of the element: " + loginPortalElement.getText());
		
//		WebElement element = driver.findElement(By.id("login-portal"));
//		WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/a/div/div[1]/h1"));
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		System.out.println("The text of the element: " + element.getText());
		System.out.println("login-portal has been scrolled into view (hopefully).");
		
	}

	@When("I click on the login portal button")
	public void i_click_on_the_login_portal_button() {
		System.out.println("I click on the login portal button");
		// xpath: /html/body/div[1]/div/div[2]/div[3]/a/div/div[1]/h1
		// id="login-portal"
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// The following works without clicking!!
//		WebElement element = driver.findElement(By.id("login-portal"));
		WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/a/div/div[1]/h1"));
		element.click();
	}

	@When("I enter a username")
	public void i_enter_a_username() {
		System.out.println("I enter a username");
		// Store the current window handle (the method will be called twice, so this is necessary to identify the different web pages).
		@SuppressWarnings("unused")
		String winHandleBefore = driver.getWindowHandle();
		// Perform the click operation that opens the new window.
		// Switch to the new window.
		for (String winHandle: driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.findElement(By.id("text")).sendKeys("webdriver");		
	}

	/** The following gives the same results.
	 *  The first one is from the course.
	 *  The second one is created by the cucumber functionality.
	 *  
	 * @param string Variable indicating the correctness of the password.
	 */
//	@When("I enter a \"([^\"]*)\" password")
	@When("I enter a {string} password")
	public void i_enter_a_password(String password) {
		System.out.println("I enter a " + password + " password");
		driver.findElement(By.id("password")).sendKeys(password);
	}

	@When("I click on the login button")
	public void i_click_on_the_login_button() {
		System.out.println("I click on the login button");
		driver.findElement(By.id("login-button")).click();
	}

	@Then("I should be presented with a succesfful validation alert")
	public void i_should_be_presented_with_a_succesfful_validation_alert() {
		System.out.println("I should be presented with a succesfful validation alert");
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert text = " + alert.getText());
		
		Assert.assertEquals(alert.getText(), "validation succeeded");
		alert.accept();
	}

	@Then("I should be presented with a unsuccessful validation alert")
	public void i_should_be_presented_with_a_unsuccesfful_validation_alert() {
		System.out.println("I should be presented with a unsuccesfful validation alert");
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert text = " + alert.getText());
		Assert.assertEquals(alert.getText(), "validation failed");
	}

}
