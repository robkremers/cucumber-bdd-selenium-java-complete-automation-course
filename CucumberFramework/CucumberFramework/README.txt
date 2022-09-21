Generating Reports.

Sources:
- https://github.com/email2vimalraj/CucumberExtentReporter/issues/75
- http://extentreports.com/docs/versions/4/java/cucumber4.html
- http://extentreports.com/docs/versions/4/java/extentservice.html
  - Describes how to initialize the properties file.
    See below the implementation.
- https://www.toolsqa.com/
	- Learning site for Selenium / Cucumber.
- https://www.toolsqa.com/selenium-cucumber-framework/cucumber-extent-report/

Under your src/test/resources/, you need an extent.properties so the adapter knows which reporters you are using:
https://github.com/extent-framework/extentreports-cucumber2-adapter/blob/master/src/test/resources/com/aventstack/adapter/extent.properties
Similarly, in the same extent.properties file, you can refer to any configuration you would like to load.

In a nutshell:

Add the reference to the adapter in your runner
Mark extent.properties with the needed reporters
Specify path of configuration XMLs (if needed)

- Description of @CucumberOptions
	- https://testingneeds.wordpress.com/2015/09/15/junit-runner-with-cucumberoptions/
	
---------------------------------------------------------------------------------------------------
2019-04-16:

Section 20: Integration Selenium WebDriver with Cucumber BDD.

Ch. 30 How we can Integrate Selenium WebDriver.

Selenium integration:
- Feature Files
	--> Step Classes	--> Selenium.
		<-- Runner Class --> Excel, JVM

So Selenium related code will be placed in the Steps Classes.

Example:
@Given("^User navigates to the mail website$")
public void user_navigates_to_the_mail_website() throws Throwable {
	driver.get("https://www.mail.com/int/");
}

@given("^User click on the login button$")
public void user_click_on_the_login_button() throws Throwable {
	driver.findElement(By.xpath(".//*[@id='login-button']")).click;
}


Sources:
- wwww.WebDriverUniversity.com
	- Can be used for experiments (if the site is up)
	
---------------------------------------------------------------------------------------------------
2019-05-16:
	
Section 36. How to Use TestNG

Ch. 83. TestNG - Introduction & Explanation.
Ch. 84. TestNG - Code Example & Explanation.

Purpose:
- In case of multiple runner classes all these classes need to be started in one go.
	- Use for this testNG.

Sources:
- https://www.journaldev.com/21304/testng-xml
- https://www.journaldev.com/21219/testng-tutorial

See: testng.xml

