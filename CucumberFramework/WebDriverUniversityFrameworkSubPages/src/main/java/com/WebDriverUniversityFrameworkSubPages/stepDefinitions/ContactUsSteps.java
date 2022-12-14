package com.WebDriverUniversityFrameworkSubPages.stepDefinitions;

import java.io.IOException;

import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import com.WebDriverUniversityFrameworkSubPages.utils.DriverFactory;

public class ContactUsSteps extends DriverFactory {
	
	@Given("^I access webdriveruniversity contact us form$")
	public void i_access_webdriveruniversity_contact_us_form() throws IOException {
		contactUsPage.getContactUsPage();
	}

	@When("^I enter a valid firstname$")
	public void i_enter_a_valid_firstname() throws Exception {
		contactUsPage.enterFirstName("Tom");
	}

	@When("^I enter a valid last name$")
	public void i_enter_a_valid_last_name(DataTable dataTable) throws Exception{
		contactUsPage.enterLasttName(dataTable, 0, 1);
	}

	@And("^i enter a valid email address$")
	public void i_enter_a_valid_email_address() throws Exception  {
		contactUsPage.enterEmailAddress("webdriveruniversity@outlook.com");
	}

	@And("^i enter comments$")
	public void i_enter_comments(DataTable dataTable) throws Exception {
		contactUsPage.enterComments(dataTable, 0, 1);
	}

	@When("^i click on the submit button$")
	public void i_click_on_the_submit_button() throws Exception {
		contactUsPage.clickOnSubmiButton();
	}

	@Then("^the information should successfully be submitted via the contact us form$")
	public void the_information_should_successfully_be_submitted_via_the_contact_us_form() throws Exception  {
//		Assert.fail();
		contactUsPage.confirmContactUsFormSubmissionWasSuccessful();
	}
}
