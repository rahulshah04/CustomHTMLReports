package com.rsk.CustomHTMLReport.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rks.CustomHTMLReport.Utility.Status;
import com.rks.CustomHTMLReport.Utility.writeResult;
import com.rks.CustomeHTMLReport.Config.ConfigFileReader;
import com.rsk.CustomHTMLReport.Constants.e2ETestConstants;
import com.rsk.CustomHTMLReport.Pages.FlightFinderPage;
import com.rsk.CustomHTMLReport.Pages.LoginPage;

public class e2ETest {
	public static WebDriver driver;
	public static writeResult Report = new writeResult();
	public static ConfigFileReader confFileReader = new ConfigFileReader();
	
	@BeforeTest
	public static void Setup() {
		System.setProperty("webdriver.chrome.driver", confFileReader.getDriverPath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(confFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		driver.get(confFileReader.getApplicationUrl());
	}
	
	
	@Test
	public static void MercuryToursLogin() {
		
		WebElement findFlight = driver.findElement(LoginPage.findflight);
		if(findFlight.isDisplayed()) {
			System.out.println("User is on Login Page");
			Report.updateTestValidation("User is on Login Page", "e2ETest", Status.PASS, driver);
			
			driver.findElement(LoginPage.userName).sendKeys(e2ETestConstants.UserName);
			driver.findElement(LoginPage.password).sendKeys(e2ETestConstants.password);
			Report.updateTestValidation("UserName and Password is entered", "e2ETest", Status.PASS, driver);
			
			driver.findElement(LoginPage.LogInBttn).click();
			
			WebElement flightFinder = driver.findElement(FlightFinderPage.flightFinderCaption);
			if(flightFinder.isDisplayed()) {
				System.out.println("User is on Flight Finder Launch Page");
				Report.updateTestValidation("User is on Flight Finder Launch Page", "e2ETest", Status.PASS, driver);
				WebElement tripTypeRound = driver.findElement(FlightFinderPage.tripTypeRadioBttn);
				if(tripTypeRound.isSelected()) {
					System.out.println("Round Trip is selected");
					Report.updateTestValidation("Round Trip is selected", "e2ETest", Status.PASS, driver);
					WebElement passengerCount = driver.findElement(FlightFinderPage.passengerCount);
					Select oselectPassCounty = new Select(passengerCount);
					oselectPassCounty.selectByIndex(e2ETestConstants.passengerCountryDrpdwnOptions);
					Report.updateTestValidation("Passenger Country Dropdown Option is selected", "e2ETest", Status.FAIL, driver);
				}
				else {
					tripTypeRound.click();
					Report.updateTestValidation("Round Trip is clicked", "e2ETest", Status.PASS, driver);
				}
			}
			
			else {
				System.out.println("User is not Flight Finder Launch Page");
				Report.updateTestValidation("User is NOT on Flight Finder Launch Page", "e2ETest", Status.FAIL, driver);
			}
		}
		else {
			System.out.println("User is not on Login Page");
			Report.updateTestValidation("User is NOT on Login Page", "e2ETest", Status.FAIL, driver);
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.get(System.getProperty("user.dir")+"/HTMLTestReport.html");
		
	}

}
