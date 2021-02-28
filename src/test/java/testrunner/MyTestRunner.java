package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/AppFeatures"},
		glue = {"stepdefinitions", "AppHooks"},
		//tags = "@SedingEmail",
		plugin = {"pretty",
				//adapter for ExtentCucumber
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"							
				}
		)
public class MyTestRunner {

}
