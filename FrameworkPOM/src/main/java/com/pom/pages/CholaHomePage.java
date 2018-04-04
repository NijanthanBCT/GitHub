package com.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.pom.wrappers.ProjectWrappers;
import com.relevantcodes.extentreports.ExtentTest;

public class CholaHomePage extends ProjectWrappers {
	public CholaHomePage(RemoteWebDriver driver, ExtentTest test) throws InterruptedException {
		this.driver = driver;
		this.test = test;
		Thread.sleep(5000);

		/*
		 * if (!verifyTitle("Welcome to BCT Intranet")) {
		 * reportStep("This is not the BCT Internet Home page", "FAIL"); }
		 */
		
		if(verifyTextByName(prop.getProperty("Login.Submit"), "Login")){
			reportStep("ROC application Launched Successfully", "PASS");
		}
		else{
			reportStep("ROC application not Launched", "FAIL");
		}
		
	}

	public TimeSheetPage openTimeSheet() throws InterruptedException {
		
		click(locatorProp.getProperty("Home.TimeSheet"), prop.getProperty("Home.TimeSheet"));
		switchToLastWindow();
		return new TimeSheetPage(driver, test);
	}

	public HomePage getUserName() throws InterruptedException {
		getTextByXpath(prop.getProperty("Home.UserName"));
		return new HomePage(driver, test);
	}
	public LoginPage clickLogout() throws InterruptedException {
		click(locatorProp.getProperty("Home.Logout"), prop.getProperty("Home.Logout"));
		return new LoginPage(driver, test);
	}
}
