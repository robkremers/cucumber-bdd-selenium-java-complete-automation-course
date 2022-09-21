package WebDriverUniversityFramework.steps;

import java.util.List;
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
import io.cucumber.datatable.DataTable;

public class ContactUs {
	
	private WebDriver driver;
	String className = "ContactUs";
	
	@Before
	public void setup() {
		System.out.println("Starting up for " + className);
		System.setProperty("webdriver.gecko.driver",
				"/Users/rkremers/Documents/My files/Documenten/Studie/Cucumber/CucumberFramework/WebDriverUniversityFramework/src/test/java/WebDriverUniversityFramework/resources/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		
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
				System.out.println("Quits this driver, closing every associated window for sessionId " + strSessionId);
			} else {
				System.out.println("The handle to the drive is now null. Cannot quit anymore.");
			}

		} catch (Exception e) {
			System.out.println(className + ": tearDown() gone wrong when " + action + " for the sessionId " + strSessionId);
//			throw new RuntimeException(className + ": tearDown() gone wrong when " + action + " for the sessionId " + strSessionId + ": " + e, e);
		}
	}
	
	@Given("I access webdriveruniversity")
	public void i_access_webdriveruniversity() {
		System.out.println(className + ": I access webdriveruniversity");
		driver.get("http://www.webdriveruniversity.com");
		System.out.println(className + ": I have accessed webdriveruniversity");
	}

	@When("I click on the contact us button")
	public void i_click_on_the_contact_us_button() {
		System.out.println("I click on the contact us button");
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		
		// Looking for By.id("contact-us") shows an entire area, not just the button. Therefore the 'click' action will fail.
//		WebElement contactUsElement = driver.findElement(By.id("contact-us"));
		WebElement contactUsElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/a/div/div[1]/h1"));
		System.out.println("The text of the element: " + contactUsElement.getText());
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		contactUsElement.click();
		System.out.println("The element " + contactUsElement.getText() + " has been clicked.");
	}

	/**
	 * Now the new window with the Contact us form is opened.
	 */
	@When("I enter a valid first name")
	public void i_enter_a_valid_first_name() {
		System.out.println("I enter a valid first name");
		// Store the current window handle.
		@SuppressWarnings("unused")
		String winHandleBefore = driver.getWindowHandle();
		// Perform the click operation that opens the new window.
		// Switch to the new window.
		for (String winHandle: driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		WebElement firstNameElement = driver.findElement(By.name("first_name"));
		System.out.println("The text of the element: " + firstNameElement.getText());
		driver.findElement(By.name("first_name")).sendKeys("Tom");
	}

	@When("I enter a valid last name")
	public void i_enter_a_valid_last_name(DataTable dataTable) {
		System.out.println("I enter a valid last name");
		// Below an example of using a comment, i.e. a number of names is sent and 
		// in this case one is chosen.
		List<String> lastNames = dataTable.asList();
		for (String lastName: lastNames) {
			System.out.println("lastName = " + lastName);
		}
		driver.findElement(By.name("last_name")).sendKeys(lastNames.get(1));
	}

	@When("I enter a valid email address")
	public void i_enter_a_valid_email_address() {
		System.out.println("I enter a valid email address");
		driver.findElement(By.name("email")).sendKeys("webdriveruniversity@outlook.com");
	}

	@When("I enter comments")
	public void i_enter_comments(DataTable dataTable) {
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
			driver.findElement(By.name("message")).sendKeys(element + "\n");
		}		
	}

	@When("I click on the submit button")
	public void i_click_on_the_submit_button() {
		System.out.println("I click on the submit button");
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("input[value='SUBMIT']")).click();
	}

	@Then("the information should successfully be submitted via the contact us form")
	public void the_information_should_successfully_be_submitted_via_the_contact_us_form() {
		System.out.println("the information should successfully be submitted via the contact us form");
		WebElement message = driver.findElement(By.xpath("/html/body/div/div/div/h1"));
		System.out.println("message = " + message.getText());
		Assert.assertEquals(message.getText(), "Thank You for your Message!");
		System.out.println("Successful end of test.");
	}

}
