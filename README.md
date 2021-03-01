# Hybrid_Framework-POM-Cucumber6-BDD-gmail-automation
This is Hybrid Framework POM Cucumber6 BDD with Selenium and Junit for Gmail Automation Test.

# 📨 Gmail Email Automation using Selenium(Java)-BDD Test

## Background

- Name: Heman Mehta

This assignment was submitted to Incubytes Inc as Assessment Test.

## Table of contents

1. [Story statement](#story-statement)
2. [Gherkin scripts](#gherkin-scripts)
3. [Test environment description (approach, dependencies)](#test-environment-description)
4. [Installation](#installation)
5. [Running tests](#running-tests)
6. [All files delivered](#all-files-delivered)
7. [Link to video and source code](#links)

## Story statement

### Story

```
As a user of the Gmail mail provider,
I would like to be able to send emails with pdf file attachment.
so I can communicate asynchronously to anyone with an internet connection across the world.
```

### Flows

Primary purpose is to create sample selenium automation script with BDD approach for sending email with file attachment from gmail web client. 

Therefore, I have created two feature files for LoginPage and InboxPage. See the [Gherkin scripts](#gherkin-scripts) below for more details 

## Gherkin scripts

These scripts are also accessible via the repository in the `/src/test/resources/AppFeatures/LoginPage.feature` file. ([Link to GitHub here](https://github.com/hemanmehta/Hybrid_Framework-POM-Cucumber6-BDD-gmail-automation/tree/main/src/test/resources/AppFeatures))
### LoginPage.feature
```
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

```
### InboxPage.feature
```
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


```

## Test environment description

### High-level testing approach

My framework is following Page Object Model (POM) and BDD approach using Cucumber6. 
I have used WebDriverManger library for the driver support. 

Drivers related files consolidated in `src/main/java/factory` package. 

Page classes and utilities files can be found in `src/main/java/pages` and `src/main/java/util` packages respectively.

Cucumber Hooks related files located in `src/test/java/AppHooks` package.

Step definitions classes are in `src/test/java/stepdefinitions` package.

TestRunner files are under `src/test/java/testrunner` package.

Feature files can be located in `src/test/resources/AppFeatures` package.

Sample PDF file is placed under `src/test/resources/attachmentfiles` directory.

autoITScript is used to upload file and compiled executable is available under `src/test/resources/autoITScript` directory.

Browser configuration files are clubed in `src/test/resources/config` directory.

Reporting related properties files are available in `src/test/resources/` package.

For Reports,

Extent PDF Report: `test output/PdfReport/ExtentPdf.pdf`

Spark HTML Report: `test-output/SparkReport/Index.html`

LoginPage.feature contains test cases to validate login page and login process.

InboxPage.feature contains test cases for step-by-step execution for sending email with attachment, email attempt with invalid recipient id.

Sole purpose is to cover major aspects and features of cucumber and selenium webdriver with Java along with adhering best standard practices.

I believe that still there are scope to improve the codebase.

### Dependencies

Here are the versions of the dependencies specified by the `pom.xml`:

- `"cucumber": "^6.9.0",` 
- `"Java": "^1.8",` 
- `"maven": "^3.8.1",` 
- `"junit": "^4.13.1",` 
- `"selenium": "^3.14.1",` 
- `"webdrivermanger": "^4.2.2",`
- `"extentreports-cucumber6-adapter": "^2.6.0",`  

## Installation

1. Download build from github repositroy.
2. import as Existing Maven Project in Eclipse IDE.
3. Do Right Click on project. Navigate to `Maven > Update Project` option.

## Running tests

Once all packages are done installing, Run `src/test/java/testrunner/MyTestRunner.java` as JUnit Test.

## All files delivered

Inside the GitHub repository (and this submission), there are the following folders and files

```
.classpath
.project
.settings/org.eclipse.core.resources.prefs
.settings/org.eclipse.jdt.core.prefs
.settings/org.eclipse.m2e.core.prefs
pom.xml
src/main/java/factory/DriverFactory.java
src/main/java/pages/BasePage.java
src/main/java/pages/InboxPage.java
src/main/java/pages/LoginPage.java
src/main/java/util/ConfigReader.java
src/test/java/AppHooks/ApplicationHooks.java
src/test/java/stepdefinitions/InboxPageSteps.java
src/test/java/stepdefinitions/LoginPageSteps.java
src/test/java/testrunner/MyTestRunner.java
src/test/resources/AppFeatures/InboxPage.feature
src/test/resources/AppFeatures/LoginPage.feature
src/test/resources/attchementfiles/sample.pdf
src/test/resources/autoITScript/Fileupload.exe
src/test/resources/config/config.properties
src/test/resources/cucumber.properties
src/test/resources/extent-config.xml
src/test/resources/extent.properties
target/classes/.cucumber/cucumber.glue.tmp
target/classes/.cucumber/cucumber.stepDefinitions.tmp
target/classes/factory/DriverFactory.class
target/classes/pages/BasePage.class
target/classes/pages/InboxPage.class
target/classes/pages/LoginPage.class
target/classes/util/ConfigReader.class
target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst
target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst
target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst
target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst
target/test-classes/AppFeatures/InboxPage.feature
target/test-classes/AppFeatures/LoginPage.feature
target/test-classes/AppHooks/ApplicationHooks.class
target/test-classes/attchementfiles/sample.pdf
target/test-classes/autoITScript/Fileupload.exe
target/test-classes/config/config.properties
target/test-classes/cucumber.properties
target/test-classes/extent-config.xml
target/test-classes/extent.properties
target/test-classes/stepdefinitions/InboxPageSteps.class
target/test-classes/stepdefinitions/LoginPageSteps.class
target/test-classes/testrunner/MyTestRunner.class
test output/PdfReport/ExtentPdf.pdf
test-output/SparkReport/Index.html
test-output/embedded1.png

```
## Links

- 📼 Screen recording: [https://youtu.be/PlM9EVaK74U](https://youtu.be/PlM9EVaK74U)
- 👩‍💻 Code repository: [https://github.com/hemanmehta/Hybrid_Framework-POM-Cucumber6-BDD-gmail-automation](https://github.com/hemanmehta/Hybrid_Framework-POM-Cucumber6-BDD-gmail-automation)

