package com.pom.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.pom.wrappers.ProjectWrappers;
import com.relevantcodes.extentreports.ExtentTest;

public class LoginPageChola extends ProjectWrappers {

	public LoginPageChola(RemoteWebDriver driver, ExtentTest test) throws InterruptedException {

		this.driver = driver;
		this.test = test;

	Thread.sleep(5000);
		/*if (!verifyTitle("Login")) {
			reportStep("This is not the Login Page", "FAIL");
		}*/
	}

	public LoginPageChola enterUserId(String userId) {
		enter(locatorProp.getProperty("Login.UserID"),prop.getProperty("Login.UserID"), userId);
		return this;
	}
	
	public LoginPageChola enterPassword(String password) {
		enter(locatorProp.getProperty("Login.Password"), prop.getProperty("Login.Password"), password);
		return this;
	}
	
	public HomePage clickSubmit() throws InterruptedException {
		click(locatorProp.getProperty("LoginSubmit"), prop.getProperty("Login.Submit"));
		Thread.sleep(10000);
		return new HomePage(driver, test);
	}
	
	public LoginPageChola enterPasswordWrong(String password) {
		enter(locatorProp.getProperty("Login.Password"), prop.getProperty("Login.Password"), password);
		return this;
	}
}
