package com.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.pom.wrappers.ProjectWrappers;
import com.relevantcodes.extentreports.ExtentTest;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class HomePage extends ProjectWrappers {

	public HomePage(RemoteWebDriver driver, ExtentTest test) throws InterruptedException {
		this.driver = driver;
		this.test = test;
		Thread.sleep(5000);

		/*
		 * if (!verifyTitle("Welcome to BCT Intranet")) {
		 * reportStep("This is not the BCT Internet Home page", "FAIL"); }
		 */
	}	public HomePage verifyHeader() throws InterruptedException {
		
		if(driver.findElement(By.id(prop.getProperty("Home.Header"))).isDisplayed()) {
		System.out.println("Logged in succesfully...");
		ATUReports.add("Verify Element Present","Login Successful", "Logged in to Order page",   LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		else
		{
			ATUReports.add("Verify Element Not Present","UnSuccessful Login", "Failed to open Order page",   LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));		}
		return this;
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
