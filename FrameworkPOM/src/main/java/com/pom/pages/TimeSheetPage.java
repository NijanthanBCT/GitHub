package com.pom.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.pom.wrappers.ProjectWrappers;
import com.relevantcodes.extentreports.ExtentTest;

public class TimeSheetPage extends ProjectWrappers {

	public TimeSheetPage(RemoteWebDriver driver, ExtentTest test) throws InterruptedException {

		this.driver = driver;
		this.test = test;
		Thread.sleep(5000);

		/*if (!verifyTitle("Login")) {
			reportStep("This is not the Login Page", "FAIL");
		}*/
	}
	
	public HomePage closeTimeSheet() throws InterruptedException {
		closeBrowser();
		switchToParentWindow();		
		return new HomePage(driver, test);
	}

	
}
