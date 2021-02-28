package stepdefinitions;

import pages.InboxPage;
import pages.LoginPage;

import java.util.List;
import java.util.Map;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class InboxPageSteps {
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private InboxPage inboxPage;
	private String recipient;
	private String cc;
	private String subject;
	private String message;

	@Given("user has already logged in to Gmail web client.")
	public void user_has_already_logged_in_to_gmail_web_client(DataTable credentialTable) {
		List<Map<String, String>> credList = credentialTable.asMaps();
		String userName = credList.get(0).get("username");
		String password = credList.get(0).get("password");

		DriverFactory.getDriver().get("https://mail.google.com/");
		inboxPage = loginPage.doLogin(userName, password);
	}

	@Given("I have an email draft addressed to {string} with {string} as Cc")
	public void i_have_an_email_draft_addressed_to_with_as_cc(String recipientId, String ccId) {
		this.recipient = recipientId;
		this.cc = ccId;
		System.out.println("Recipient Email: "+ recipient);
		System.out.println("Cc Email: "+ cc);
	}

	@When("I choose to compose a new email")
	public void i_choose_to_compose_a_new_email() {
		inboxPage.clickOnCompose();
	}

	@When("I enter a email address as recipient {string} and Cc {string}")
	public void i_enter_a_email_address_as_recipient_and_Cc(String recipientId, String ccId) {
		inboxPage.setRecipientEmail(recipient,cc);
	}

	@When("I enter email subject line as {string}")
	public void i_enter_subject_line_as(String subjectTitle) {
		this.subject = subjectTitle;
		inboxPage.setEmailSubject(subjectTitle);
	}

	@When("I enter message body as {string}")
	public void i_enter_message_body_as(String messageBody) {
		this.message = messageBody;
		inboxPage.setMessageBody(messageBody);
	}

	@When("I attach a pdf file in the email")
	public void i_attach_a_pdf_file_in_the_email() {
		inboxPage.uploadSingleFile();
	}

	@When("I send the email")
	public void i_send_the_email() {
		inboxPage.clickOnSend();
	}

	@Then("the inbox should display only One Unread message count")
	public void the_inbox_should_display_only_One_Unread_message_count() {
		Assert.assertTrue(inboxPage.isEmailSent());
	
	}
	
	@Then("the error message pops up")
	public void the_error_message_pops_up() {
		Assert.assertTrue(inboxPage.isErrorMessagePopupDisplyed());
		
	}
}
