# Testing the login of https://stackoverflow.com.
Feature: Log into account
	Existing user should be able to login to account using correct credentials
	
# - In case of error warning signals will be visible in front of the scenario details.
#   The effect can slightly vary per environment.
# - After implementation or upgrade in a Java class press 'space' and the warning signs
#   will disappear if everything is okay.
# - If everything is okay, so a corresponding test class has been created and 
#   the created test code has been implemented the following will be visible when
#   running the feature file again:
#
#   cucumber.api.PendingException: TODO: implement me
#    	at steps.LoginSteps.user_navigates_to_stackoverflow_website(LoginSteps.java:19)
#
# Background:
# - https://docs.cucumber.io/gherkin/reference/
#   --> Overview of keywords.
#
	
Scenario: Login into account with correct credentials
Given User navigates to stackoverflow website
And User clicks on the login button on homepage
And User enters a valid username
And User enters a valid password
# Uncommenting the following would cause an error in this feature file.
# --> cucumber.api.PendingException
When User clicks on the login button
Then User should be taken to the successful login page
