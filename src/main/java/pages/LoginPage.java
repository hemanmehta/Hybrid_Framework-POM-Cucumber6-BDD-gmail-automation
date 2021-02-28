package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	//Login Page locators
	private By emailField = By.id("identifierId");
	private By emailNextButton = By.xpath("//div[@id=\"identifierNext\"]/div/button");
	private By passwordField =By.xpath("//div[@id=\"password\"]/div[1]/div/div[1]/input");
	private By passwordNextButton = By.xpath("//div[@id=\"passwordNext\"]");
	
	//InboxPage locator
	private By primaryLabel = By.xpath("//div[contains(@data-tooltip,'Person-to-person')]");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Page action methods
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean isPasswordFieldEnabled() {
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		return driver.findElement(passwordField).isDisplayed();
	}

	public void setUserName(String username) {
		driver.findElement(emailField).sendKeys(username);
	}
	
	public void clickOnNextButton() {
		driver.findElement(emailNextButton).click();
	}

	public void setPassword(String pwd) {
		wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
		driver.findElement(passwordField).sendKeys(pwd);
	}

	public InboxPage clickOnSubmitButton() {
		wait.until(ExpectedConditions.presenceOfElementLocated(passwordNextButton));
		driver.findElement(passwordNextButton).click();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(primaryLabel));
		return new InboxPage(driver);
		
	}
	
	public InboxPage doLogin(String un, String pwd) {
		driver.findElement(emailField).sendKeys(un);
		driver.findElement(emailNextButton).click();
		
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
		driver.findElement(passwordField).sendKeys(pwd);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(passwordNextButton));
		driver.findElement(passwordNextButton).click();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(primaryLabel));
		
		return new InboxPage(driver);
		
		/*
		 * System.out.println("login with: " + un + " and " + pwd);
		 * driver.findElement(emailId).sendKeys(un);
		 * driver.findElement(password).sendKeys(pwd);
		 * driver.findElement(signInButton).click();
		 */
		
	}
	
}
