package com.pom.wrappers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

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
import com.pom.utils.MailConfig;
import com.pom.utils.MailUtils;
import com.pom.utils.MobileConfigration;
import com.pom.utils.ObjectRepository;
import com.pom.utils.OsUtils;
import com.pom.utils.PDFReporter;
import com.pom.utils.TestCaseDetailsProvider;
import com.pom.utils.PropertyUtils;
import com.pom.core.*;

public class ProjectWrappers extends GenericWrappers {

	
	public String startTime, endTime;
	public String browserName;
	public String dataSheetName;
	public long executionEndTime, executionStartTime, executionTime, executionHour, executionMin, executionSec;
	public HashMap<String, String> map = new HashMap<String, String>();
	//private static Logger LOGGER = Logger.getLogger(ProjectWrappers.class);
	@BeforeSuite
	public void beforSuite() {
		
		
		
		
		
		

/*		File log4jfile = new File("./src/main/resources/LicenseLog4j.properties");
		PropertyConfigurator.configure(log4jfile.getAbsolutePath());
*///		String propertyFileName = System.getProperty("user.dir")
//				+ "//resources//config//MasterConfiguration.properties";
//		configPropertyMap = new PropertyUtils().getMap(propertyFileName);
		Calendar cal = Calendar.getInstance();
		String iniPropertyFileName = System.getProperty("user.dir") + "//resources//lic//Ini.properties";

		String macAddr = OsUtils.getMacAddress();
		String encryptMac = "";
		String encryptDate = "";
		Authen key = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar endCal = Calendar.getInstance();
		endCal.add(Calendar.DATE, 7);
		Date expDate = endCal.getTime();
		try {
			key = new Authen();
			encryptMac = key.encrypt(macAddr);
			encryptDate = key.encrypt(dateFormat.format(expDate));
		} catch (Exception e) {

			System.out.println("Error in license. Please check admin");
			//LOGGER.error("Error in license. Please check admin");
		}

		HashMap<String, String> licProps = null;
		File iniFile = new File(iniPropertyFileName);
		if (!iniFile.exists()) {
			try {
				Properties properties = new Properties();
				properties.setProperty("key", encryptDate);
				properties.setProperty("hkey", encryptMac);

				FileOutputStream fileOut = new FileOutputStream(iniFile);
				properties.store(fileOut, "Do not change this file");
				fileOut.close();
			} catch (FileNotFoundException e) {
				//LOGGER.error("Error in license file. Please check admin");
			} catch (IOException e) {
				//LOGGER.error("Error in reading license. Please check admin");
			}

		} else {

			licProps = new PropertyUtils().getMap(iniPropertyFileName);

			if (!encryptMac.equals(licProps.get("hkey"))) {
				System.out.println("Expired");
				System.out.println("License Expired. Please renew/ contact admin.....");
				//LOGGER.error("License Expired. Please renew/ contact admin.....");
				System.exit(0);
			}
		}

		String expiryTime = "";
		if (licProps == null) {
			licProps = new PropertyUtils().getMap(iniPropertyFileName);

		}
		expiryTime = key.decrypt(licProps.get("key"));
		Date end = null;
		try {
			end = dateFormat.parse(expiryTime);
		} catch (ParseException e) {
			System.out.println("License date exception. Please contact admin.....");
			//LOGGER.error("License date exception. Please contact admin.....");
		}
		Date current = cal.getTime();
		if (current.after(end)) {
			System.out.println("Expired");
			System.out.println("License Expired. Please renew/ contact admin.....");
			//LOGGER.error("License Expired. Please renew/ contact admin.....");
			System.exit(0);
		}

		System.out.println("Test Suite execution starts here");
		/*if (new File(System.getProperty("user.dir") + "/reports").exists()) {
			try {
				FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + "/reports"));
			} catch (IOException e) {
				System.out.println("Issue deleteing current report. Report may not contain current run report. Please check with Admin");
			}
		}*/
//		extent = new com.relevantcodes.extentreports.ExtentReports(System.getProperty("user.dir") + "/reports/TestReport.html", true);
//		pdfFileNamePath = System.getProperty("user.dir") + "/reports/TestResultsReport.pdf";
//		extent.loadConfig(new File(System.getProperty("user.dir") + "/resources/config/extent-config.xml"));
//		startTime = getTimeStamp("dd-MMM-yyyy HH:mm:ss");
//		String mailPropertyFileName = System.getProperty("user.dir")
//				+ "//resources//config//MailConfiguration.properties";
//		emailProps = new PropertyUtils().getMap(mailPropertyFileName);
//		masterSheetMap = TestBaseHelper.getMasterSheetConfig(
//				System.getProperty("user.dir") + "//resources//testdata//" + configPropertyMap.get("TestDataFile"),
//				"Config");
//		DateFormat videoDateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
//		Date date = new Date();
//		// Created object of ATUTestRecorder
//		// Provide path to store videos and file name format.
//		String pathName = System.getProperty("user.dir") + "//VideoReports";
//
//		try {
//			File recorderFolder = new File(pathName);
//			if (recorderFolder.exists()) {
//				LOGGER.info("Video directory exists" + recorderFolder.getAbsolutePath());
//			} else {
//				recorderFolder.mkdir();
//				LOGGER.info("Make Video directory" + recorderFolder.getAbsolutePath());
//			}
//			recorder = new ATUTestRecorder(pathName, videoDateFormat.format(date), false);
//			// To start video recording.
//			recorder.start();
//			videoRecording = true;
//			LOGGER.info("Video Recording Started");
//		} catch (ATUTestRecorderException e) {
//			LOGGER.warn("Video Recording - Record action Cancelled");
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		startResult();

	}

	@BeforeTest
	public void beforeTest() {
		loadObjects();
		
		startTime = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());
		executionStartTime = System.currentTimeMillis();
				
		prop.setProperty("StartTime" , startTime);
		// return startTime;
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
	public void afterTest() throws InterruptedException {
		endTime = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());
		executionEndTime = System.currentTimeMillis();
		prop.put("EndTime",endTime);
		executionTime = executionEndTime - executionStartTime;
		executionSec = executionTime / 1000 % 60;
		executionMin = executionTime / ( 1000 * 60 ) % 60;
		executionHour = executionTime / ( 1000 * 60 * 60 ) % 24;
		//prop.put("FailedTestCount", failedTestCount);
		
		// return endTime;
		prop.put("ExecutionTime", String.format( "%02d" , executionHour ) + ":" + String.format( "%02d" , executionMin ) + ":" + String.format( "%02d" , executionSec ) );
		
		System.out.println("Failed Count inside Project Wrappers : " + failedTestCount );
		PDFReporter.createPDFReport();
		
		unloadObjects();
	}
	


	@AfterSuite
	public void afterSuite() {
		/*try
		{
		MailUtils.sendEmailWithAttachment(prop.getProperty("HostName"), prop.getProperty("PortName"), prop.getProperty("SMPTauth"), prop.getProperty("UserName"), prop.getProperty("PassWord"), prop.getProperty("FromAddress"), prop.getProperty("ToAddress"), prop.getProperty("Subject"), prop.getProperty("pdfFileName"));
		}catch (Exception e) {
			System.out.println("Unable to send mail...");
			e.printStackTrace();
		}
		*/
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
	public void getMailConfig() throws IOException {
		String[][] data = MailConfig.getMailConfig(dataSheetName);

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
