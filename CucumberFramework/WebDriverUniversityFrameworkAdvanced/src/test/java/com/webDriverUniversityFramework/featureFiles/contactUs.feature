Feature: Submit data to www.webdriveruniversity.com using Contact Us form.

Scenario: Submit valid data via Contact Us form.
Given     I access webdriveruniversity Contact Us form
When      I enter a valid first name
And       I enter a valid last name
| Woods | Jackson | Jones |
And       I enter a valid email address
And       I enter comments
| example comment one   | example comment two  |
| example comment three | example comment four |
When      I click on the submit button
Then      The information should successfully be submitted via the contact us form