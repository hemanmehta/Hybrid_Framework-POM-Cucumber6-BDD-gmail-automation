Feature: Compose and Inbox Option in Gmail inbox

  Background: 
    Given user has already logged in to Gmail web client.
      | username                          | password       |
      | selenium.testuser012021@gmail.com | testuser012021 |

  @SedingEmail
  Scenario Outline: Sending an email to valid recipient with pdf file attached
    Given I have an email draft addressed to "<recipient>" with "<cc>" as Cc
    When I choose to compose a new email
    And I enter a email address as recipient "<recipient>" and Cc "<cc>"
    And I enter email subject line as "<subject>"
    And I enter message body as "<message>"
    And I attach a pdf file in the email
    And I send the email
    Then the inbox should display only One Unread message count

    Examples: 
      | recipient                         | cc                          | subject            | message                   |
      | selenium.testuser012021@gmail.com | testbeta.falcon01@gmail.com | Test-Email-Subject | Hi, This is a Test Email. |

  @InvalidEmailErrorMessage
  Scenario Outline: Sending an email with invalid recipient shows error message
    Given I have an email draft addressed to "<recipient>" with "<cc>" as Cc
    When I choose to compose a new email
    And I enter a email address as recipient "<recipient>" and Cc "<cc>"
    And I enter email subject line as "<subject>"
    And I enter message body as "<message>"
    And I send the email
    Then the error message pops up

    Examples: 
      | recipient | cc                          | subject            | message                   |
      | aaabbdfsf | testbeta.falcon01@gmail.com | Test-Email-Subject | Hi, This is a Test Email. |
