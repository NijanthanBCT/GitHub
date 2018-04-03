package com.pom.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestingClass {

	
	public static void main(String[]  args) {
		
		System.setProperty("webdriver.chrome.driver" , "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
		driver.get("http://192.168.5.142:8180/CueTrack/");
		driver.findElement(By.id("textfield-1055-inputEl")).sendKeys("nj112282");
		driver.findElement(By.id("textfield-1056-inputEl")).sendKeys("Kalaiselvi@123");
		driver.findElement(By.id("button-1057-btnEl")).click();
	}
}
