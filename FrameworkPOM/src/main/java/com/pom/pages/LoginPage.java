package com.pom.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.pom.wrappers.ProjectWrappers;
import com.relevantcodes.extentreports.ExtentTest;

public class LoginPage extends ProjectWrappers {

	public LoginPage(RemoteWebDriver driver, ExtentTest test) throws InterruptedException {

		this.driver = driver;
		this.test = test;

	Thread.sleep(5000);
		/*if (!verifyTitle("Login")) {
			reportStep("This is not the Login Page", "FAIL");
		}*/
	}

	public LoginPage enterUserId(String userId) {
		enter("id",prop.getProperty("Login.UserID.Id"), userId);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		enter("id", prop.getProperty("Login.Password.Id"), password);
		return this;
	}
	
	public HomePage clickSubmit() throws InterruptedException {
		click("xpath", prop.getProperty("Login.Submit.Xpath"));
		Thread.sleep(10000);
		return new HomePage(driver, test);
	}
	
	public LoginPage enterPasswordWrong(String password) {
		enter("id", prop.getProperty("Login.Password.Xpath"), password);
		return this;
	}
}
