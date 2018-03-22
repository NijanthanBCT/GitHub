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
		enter("id",prop.getProperty("Login.UserID.Id"), userId);
		return this;
	}
	
	public LoginPageChola enterPassword(String password) {
		enter("id", prop.getProperty("Login.Password.Id"), password);
		return this;
	}
	
	public HomePage clickSubmit() throws InterruptedException {
		click("id", prop.getProperty("LoginSubmit.Id"));
		Thread.sleep(10000);
		return new HomePage(driver, test);
	}
	
	public LoginPageChola enterPasswordWrong(String password) {
		enter("id", prop.getProperty("Login.Password.Id"), password);
		return this;
	}
}
