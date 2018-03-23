package com.pom.testcases;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pom.pages.LoginPage;
import com.pom.pages.LoginPageChola;
import com.pom.wrappers.ProjectWrappers;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.utils.Utils;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })

public class CholaOpen extends ProjectWrappers {
	{
		System.setProperty("atu.reporter.config", "./resources/atu.properties");
		ATUReports.currentRunDescription = "Test cases for ROC login Pass, Fail and Skip";
	}

	@BeforeSuite
	public void setSheetName() {
		dataSheetName = "Chola";
		
		
	}

	@Test(dataProvider = "fetchData")

	public void loginPass(String userId, String password) throws InterruptedException {

		ATUReports.setAuthorInfo(prop.getProperty("Authors"), Utils.getCurrentTime(), "1.0");
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Test case for ROC Login pass");

		new LoginPageChola(driver, test).enterUserId(userId).enterPassword(password).clickSubmit();

	}

	@Test(dataProvider = "fetchData")
	
	public void loginFail(String userId, String password) throws InterruptedException {

		ATUReports.setAuthorInfo(prop.getProperty("Authors"), Utils.getCurrentTime(), "1.0");
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Test case for ROC Login fail");

		new LoginPage(driver, test).enterUserId(userId).enterPasswordWrong(password).clickSubmit();

	}

	@Test(dataProvider = "fetchData", dependsOnMethods = "loginFail")
	
	public void loginSkip(String userId, String password) throws InterruptedException {

		ATUReports.setAuthorInfo(prop.getProperty("Authors"), Utils.getCurrentTime(), "1.0");
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Test case for ROC Login Skip");

		new LoginPage(driver, test).enterUserId(userId).enterPasswordWrong(password).clickSubmit();

	}

}
