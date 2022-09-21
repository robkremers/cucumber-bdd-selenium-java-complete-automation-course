# Testing the login of https://stackoverflow.com.
Feature: Log into account
	Existing user should be able to login to account using correct credentials
	
# 2nd feature file for illustration purposes.
#
# After a change in the corresponding java file press 'space' and save.
# Now the warning signs 'no features found' will disappear.
#
	
Scenario: Login into account with correct credentials2
Given User navigates to stackoverflow website2
And User clicks on the login button on homepage2
And User enters a valid username2
And User enters a valid password2
And User clicks on the login button2
Then User should be taken to the successful login page2
 