package factory;

import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the thradlocal driver on the basis of given
	 * browser
	 * 
	 * @param browser
	 * @return this will return tldriver.
	 */
	public WebDriver init_driver(String browser) {

		System.out.println("browser value is: " + browser);

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions option = new ChromeOptions();
			//option.addArguments("start-maximized");
			option.addArguments("--disable-infobars");
			option.addArguments("--disable-extensions");
			option.addArguments("--disable-popup-blocking");
			option.addArguments("--incognito");
			
			//option.setExperimentalOption("debuggerAddress","localhost:9222");
			option.setExperimentalOption("useAutomationExtension", false);
			option.setExperimentalOption("excludeSwitches", Collections.singleton("enable-automation"));
			
			DesiredCapabilities chrome = DesiredCapabilities.chrome();
			chrome.setJavascriptEnabled(true);
			chrome.setCapability(ChromeOptions.CAPABILITY, option);
			
			
			tlDriver.set(new ChromeDriver(option));
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		} else {
			System.out.println("Please pass the correct browser value: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		return getDriver();

	}

	/**
	 * this is used to get the driver with ThreadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}
