package com.pom.keyword;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pom.wrappers.ProjectWrappers;
import com.relevantcodes.extentreports.ExtentTest;

import com.pom.wrappers.GenericWrappers;

public class CallWrappersUsingKeyword extends ProjectWrappers{

	@Parameters({"testCaseName","testDescription","browser","category","authors"})
	@BeforeClass
	public void setup(String testName, String testDesc, String browser, String group, String authorName){
		
		browserName 	= browser;
		testCaseName 	= testName;
		testDescription = testDesc;	
		category 		= group;
		authors 		= authorName;
	}
	
	@Parameters({"testCaseName"})
	@Test
	public void getAndCallKeyword(String fileName) throws Exception{
		FileInputStream file = new FileInputStream(new File("./keywords/"+fileName+".xlsx" ));

		// Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		Class<GenericWrappers> wrapper = GenericWrappers.class;

		// Pass the argument - driver and test
		Constructor<?> cons = wrapper.getConstructor(RemoteWebDriver.class,ExtentTest.class);
	    Object wM = cons.newInstance(driver,test);
	    
		// Get first/desired sheet from the workbook
		XSSFSheet sh = workbook.getSheetAt(0);
		for (int i = 1; i <= sh.getLastRowNum(); i++) {

			String keyword = "" ;
			String locator = "" ;
			String data = "" ;
			try {
				keyword = sh.getRow(i).getCell(0).getStringCellValue();
				locator = sh.getRow(i).getCell(1).getStringCellValue();
				data = sh.getRow(i).getCell(2).getStringCellValue();
			} catch (NullPointerException e) {
				// ignore
			}
			
			Method[] methodName = wrapper.getDeclaredMethods();
			for (Method method : methodName) {
				
				if(method.getName().equalsIgnoreCase(keyword.toLowerCase())){
					if(locator.equals("") && data.equals(""))
							method.invoke(wM);
					else if(locator.equals(""))
							method.invoke(wM,data);
					else if(data.equals(""))
						method.invoke(wM,locator);
					else
						method.invoke(wM,locator,data);					
					break;

				}				
			}		
			
			workbook.close();
		}
		
	}
}
