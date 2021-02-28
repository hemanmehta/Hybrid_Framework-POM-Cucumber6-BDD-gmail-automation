package stepdefinitions;

import pages.LoginPage;

import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {
	private static String title;
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		
	@Given("user is on login page")
	public void user_is_on_login_page() {
		DriverFactory.getDriver().get("https://mail.google.com/");
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
	    title = loginPage.getLoginPageTitle();
	    System.out.println("Page title is: " + title);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) {
	    Assert.assertTrue(title.contains(expectedTitleName));
	}

	@When("user enters email {string}")
	public void user_enters_email(String email) {
	   loginPage.setUserName(email);
	}

	@When("user clicks on Next button")
	public void user_clicks_on_next_button() {
	   loginPage.clickOnNextButton();
	}

	@Then("user gets the password field on page")
	public void user_gets_the_password_field_on_page() {
		Assert.assertTrue(loginPage.isPasswordFieldEnabled());
	}

	@Then("user enters password {string}")
	public void user_enters_password(String password) {
	    loginPage.setPassword(password);
	}
	
	@When("user clicks on submit button")
	public void user_clicks_on_submit_button() {
	   loginPage.clickOnSubmitButton();
	}

	@Then("page title contains {string} in it")
	public void page_title_contains_in_it(String expectedTitle) {
		String partialTitle = loginPage.getLoginPageTitle();
		Assert.assertTrue(partialTitle.contains("Inbox"));
	}
}
