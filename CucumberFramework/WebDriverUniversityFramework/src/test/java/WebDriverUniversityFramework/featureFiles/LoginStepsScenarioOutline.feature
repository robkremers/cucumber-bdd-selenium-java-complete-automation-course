@LoginScenarioWithOutline
Feature: Outline Login to account at webdriverunivsity.com using login portal 

Scenario Outline: Outline Login to account with credentials
Given Outline user navigates to "<url>"
When  Outline user clicks on the login portal button
And   Outline user enters the "<username>" username
And   Outline user enters the "<password>" password
When  Outline user clicks on the login button
Then  Outline user should be presented with the following prompt alert "<message>"

Examples:
|  url                              | username  | password     | message              |
|http://www.webdriveruniversity.com | user1     | pass1        | validation failed    |
|http://www.webdriveruniversity.com | webdriver | webdriver123 | validation succeeded |
