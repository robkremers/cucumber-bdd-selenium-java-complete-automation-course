@ContactUs
Feature: Submit data to webdriveruniversity.com

Scenario: Submit valid data via contact us form
Given    I access webdriveruniversity
When     I click on the contact us button
And      I enter a valid first name
And      I enter a valid last name
| Woods | Jackson | Jones |
And      I enter a valid email address
And      I enter comments
| example comment one | example comment two |
| example comment three | example comment four |
When     I click on the submit button
Then     the information should successfully be submitted via the contact us form