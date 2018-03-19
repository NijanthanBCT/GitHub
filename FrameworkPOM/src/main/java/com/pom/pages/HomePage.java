package com.pom.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.pom.wrappers.ProjectWrappers;
import com.relevantcodes.extentreports.ExtentTest;

public class HomePage extends ProjectWrappers {

	public HomePage(RemoteWebDriver driver, ExtentTest test) throws InterruptedException {
		this.driver = driver;
		this.test = test;
		Thread.sleep(5000);

		/*
		 * if (!verifyTitle("Welcome to BCT Intranet")) {
		 * reportStep("This is not the BCT Internet Home page", "FAIL"); }
		 */
	}

	public TimeSheetPage openTimeSheet() throws InterruptedException {
		
		click("xpath", prop.getProperty("Home.TimeSheet.Xpath"));
		switchToLastWindow();
		return new TimeSheetPage(driver, test);
	}

	public HomePage getUserName() throws InterruptedException {
		getTextByXpath(prop.getProperty("Home.UserName.Xpath"));
		return new HomePage(driver, test);
	}
	public LoginPage clickLogout() throws InterruptedException {
		click("xpath", prop.getProperty("Home.Logout.Xpath"));
		return new LoginPage(driver, test);
	}
}
