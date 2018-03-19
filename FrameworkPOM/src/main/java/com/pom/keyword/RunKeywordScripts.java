package com.pom.keyword;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import com.pom.utils.DataInputProvider;

public class RunKeywordScripts{


	@Test(dataProvider="keywords")
	public  void runAllTests(String id, String testcase, String testDescription, String browser,
			String category, String authorName, String run) throws IOException {

		try {

			if(run.equalsIgnoreCase("yes")){

				// Create XML Suite
				XmlSuite suite = new XmlSuite();

				// Creating a new Test
				XmlTest test = new XmlTest(suite);

				// Set Test name
				test.setName(testcase);

				// New list for the parameters
				Map<String, String> testParams = new HashMap<String, String>();

				// Add parameter to the list
				testParams.put("testCaseName", testcase);
				testParams.put("testDescription", testDescription);
				testParams.put("browser", browser);
				testParams.put("category", category);
				testParams.put("authors", authorName);

				// Add parameters to test
				test.setParameters(testParams);

				// New list for the classes
				List<XmlClass> classes = new ArrayList<XmlClass>();

				// Putting the classes to the list
				classes.add(new XmlClass("keyword.CallWrappersWithoutReflection"));
				//classes.add(new XmlClass("keyword.CallWrappersUsingKeyword"));

				// Add classes to test
				test.setClasses(classes);

				// New list for the Suites
				List<XmlSuite> suites = new ArrayList<XmlSuite>();

				// Add suite to the list
				suites.add(suite);

				// Creating the xml and run them programmatically
				TestListenerAdapter tla = new TestListenerAdapter();
				TestNG testng = new TestNG();
				testng.setXmlSuites(suites);
				testng.addListener(tla);
				testng.setVerbose(5);
				testng.run();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@DataProvider(name = "keywords",parallel=true)
	public static Object[][] getKeywords() {
		return DataInputProvider.getAllSheetData("./Keywords/KeywordDriver.xlsx");		
	}
}
