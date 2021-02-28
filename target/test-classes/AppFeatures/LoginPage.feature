Feature: Login page feature

  Scenario: Login page title
    Given user is on login page
    When user gets the title of the page
    Then page title should be "Gmail"

  @Login
  Scenario: Login with correct credentials
    Given user is on login page
    When user enters email "selenium.testuser012021@gmail.com"
    And user clicks on Next button
    Then user gets the password field on page
    And user enters password "testuser012021"
    And user clicks on submit button
    And page title contains "Inbox" in it
