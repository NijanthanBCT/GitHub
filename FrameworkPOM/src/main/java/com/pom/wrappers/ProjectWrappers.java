package com.pom.wrappers;

import java.io.IOException;
import java.util.HashMap;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.pom.utils.DataInputProvider;
import com.pom.utils.GridConfig;
import com.pom.utils.MobileConfigration;
import com.pom.utils.ObjectRepository;
import com.pom.utils.TestCaseDetailsProvider;

public class ProjectWrappers extends GenericWrappers {

	public String browserName;
	public String dataSheetName;
	public HashMap<String, String> map = new HashMap<String, String>();

	@BeforeSuite
	public void beforSuite() {
		startResult();
	}

	@BeforeTest
	public void beforeTest() {
		loadObjects();
	}

	@BeforeMethod
	public void beforeMethod() {
		test = startTestCase(prop.getProperty("TestCaseName"), prop.getProperty("TestDescription"));
		test.assignCategory(prop.getProperty("Category"));
		test.assignAuthor(prop.getProperty("Authors"));
		invokeApp(prop.getProperty("BrowserName"));
	}

	@AfterMethod
	public void afterMethod() {
		endTestCase();
		closeAllBrowser();
	}

	@AfterTest
	public void afterTest() {
		unloadObjects();
	}

	@AfterSuite
	public void afterSuite() {
		endResult();
	}
	@BeforeClass
	public void setData() {		
		prop.putAll(map);
		testCaseName = map.get("testCaseName");
		testDescription = map.get("testDescription");
		browserName = map.get("browserName");
		category = map.get("category");
		authors = map.get("authors");
	}


	@BeforeTest
	public void setTestData() throws IOException {
		String[][] data = TestCaseDetailsProvider.getAllTestDetails(dataSheetName);

		String key = "";
		String value = "";
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < 2; j++) {

				if (j == 0) {
					key = data[i][j];

				} else if (j == 1) {
					value = data[i][j];

				}
			}
			map.put(key, value);
		}

	}

	@BeforeTest
	public void getGridConfig() throws IOException {
		String[][] data = GridConfig.getGridConfig(dataSheetName);

		String key = "";
		String value = "";
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < 2; j++) {

				if (j == 0) {
					key = data[i][j];

				} else if (j == 1) {
					value = data[i][j];

				}
			}
			map.put(key, value);
		}

	}

	@BeforeTest
	public void getMobileConfig() throws IOException {
		String[][] data = MobileConfigration.getMobileConfig(dataSheetName);

		String key = "";
		String value = "";
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < 2; j++) {

				if (j == 0) {
					key = data[i][j];

				} else if (j == 1) {
					value = data[i][j];

				}
			}
			map.put(key, value);
		}

	}

	@BeforeTest
	public void zgetAllObjects() throws IOException {
		String[][] data = ObjectRepository.getAllObjects(dataSheetName, browserName);

		String key = "";
		String value = "";
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < 2; j++) {

				if (j == 0) {
					key = data[i][j];

				} else if (j == 1) {
					value = data[i][j];

				}
			}
			map.put(key, value);
		}

	}

	@DataProvider(name = "fetchData")
	public Object[][] getData() throws IOException {
		return DataInputProvider.getAllSheetData("./data/" + dataSheetName + ".xlsx");

	}

}
