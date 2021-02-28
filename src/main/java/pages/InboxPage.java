package pages;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxPage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By composeIdentifier = By.xpath("//div[@role='button'][contains(text(),'Compose')]");
	private By newMessageWindowTitle = By.xpath("//*[contains(text(),'New Message')]");
	private By toEmail = By.xpath("//textarea[@role='combobox'][contains(@aria-label,'To')]");
	private By ccLink = By.xpath("//*[contains(@data-tooltip,'Add Cc')]");
	private By ccEmail = By.xpath("//textarea[@role='combobox'][contains(@aria-label,'Cc')]");
	private By subjectEmail = By.xpath("//input[@name='subjectbox'][contains(@aria-label,'Subject')]");
	private By messageField = By.xpath("//div[@role='textbox'][contains(@aria-label,'Message Body')]");
	private By sendButton = By.xpath("//div[@role='button'][contains(@aria-label,'Send')]");
	private By attachFileLink = By.xpath("//div[@role='button'][contains(@aria-label,'Attach files')]");
	private By uploadFileWindow = By.xpath("//input[@type='file'][contains(@name,'Filedata')]");
	private By errorMessagePopup = By.xpath("//div[@role='alertdialog']/div[contains(text(),'field was not recognized')]");
	private By errorMessage_ok_button = By.xpath("//button[@name=\"ok\"]");
	private By sentmail_subject;
	private By sentmail_body_text;
	private By inboxMailCountLabel = By.xpath("//a[contains(@href,'https://mail.google.com/mail/u/0/#inbox')][contains(text(),'Inbox')]/parent::span/following-sibling::div");

	public InboxPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnCompose() {
		driver.findElement(composeIdentifier).click();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(newMessageWindowTitle));
		System.out.println("Compose Email Window title: " + driver.findElement(newMessageWindowTitle).getText());
	}

	public void setRecipientEmail(String ToEmail, String CcEmail) {
		// To field
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(toEmail));
		WebElement textboxToField = driver.findElement(toEmail);
		textboxToField.clear();
		textboxToField.sendKeys(ToEmail);

		// Cc field
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(ccLink));
		driver.findElement(ccLink).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ccEmail));
		WebElement textboxCcField = driver.findElement(ccEmail);
		textboxCcField.clear();
		textboxCcField.sendKeys(CcEmail);

	}

	public void setEmailSubject(String subjectTitle) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(subjectEmail));
		WebElement textboxSubjectField = driver.findElement(subjectEmail);
		textboxSubjectField.clear();
		textboxSubjectField.sendKeys(subjectTitle);
	}

	public void setMessageBody(String messageData) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(messageField));
		WebElement textboxMessageField = driver.findElement(messageField);
		textboxMessageField.clear();
		textboxMessageField.sendKeys(messageData);
	}

	public void clickOnSend() {
		driver.findElement(sendButton).click();
	}

	public void uploadSingleFile() {
		try {
			wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(attachFileLink));
			driver.findElement(attachFileLink).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(uploadFileWindow));
			// WebElement uploadFileWindowField = driver.findElement(uploadFileWindow);

			// Created compiled uploading executable using AutoIT Tool
			Thread.sleep(1000);
			String autoITScript_path = "./src/test/resources/autoITScript/Fileupload.exe";
			Runtime.getRuntime().exec(autoITScript_path);
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isEmailSent() {
		/*
		 * In the feature file, we are sending only 1 email so on fly we will verify
		 * with unread email count that should be 1.
		 */
		wait.until(ExpectedConditions.visibilityOfElementLocated(inboxMailCountLabel));
		String text = driver.findElement(inboxMailCountLabel).getText();
		int ureademailCount = Integer.parseInt(text);
		if (ureademailCount == 1)
			return true;
		else
			return false;

	}

	public boolean isErrorMessagePopupDisplyed() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessagePopup));
		String errorMessage = driver.findElement(errorMessagePopup).getText();
		System.out.println("Error Message From Popup: " + errorMessage);
		driver.findElement(errorMessage_ok_button).click();
		if (errorMessage.length() != 0)
			return true;
		else
			return false;
	}

}
