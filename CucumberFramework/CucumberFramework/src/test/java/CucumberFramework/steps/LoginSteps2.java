package CucumberFramework.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginSteps2 {
	
	@Given("User navigates to stackoverflow website2")
	public void user_navigates_to_stackoverflow_website() {
	    System.out.println("User navigates to stackoverflow website2");
	}

	@Given("User clicks on the login button on homepage2")
	public void user_clicks_on_the_login_button_on_homepage() {
		System.out.println("User clicks on the login button on homepage2");
	}

	@Given("User enters a valid username2")
	public void user_enters_a_valid_username() {
		System.out.println("User enters a valid username2");
	}

	@Given("User enters a valid password2")
	public void user_enters_a_valid_password() {
		System.out.println("User enters a valid password2");
	}

	@Given("User clicks on the login button2")
	public void user_clicks_on_the_login_button() {
		System.out.println("User clicks on the login button2");
	}

	@Then("User should be taken to the successful login page2")
	public void user_should_be_taken_to_the_successful_login_page() {
		System.out.println("User should be taken to the successful login page2");
	}


}
