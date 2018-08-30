package com.rsk.CustomHTMLReport.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.rks.CustomHTMLReport.Utility.Status;
import com.rks.CustomeHTMLReport.Config.ConfigFileReader;
import com.rsk.CustomHTMLReport.Constants.e2ETestConstants;
import com.rsk.CustomHTMLReport.Pages.FlightFinderPage;
import com.rsk.CustomHTMLReport.Pages.LoginPage;

public class MercuryToursTravels {
	static WebDriver driver;
	ConfigFileReader confFileReader = new ConfigFileReader();
	String filepath = "..\\CustomeHTMLReport\\target\\ExtentReports.html";
	ExtentReports Report = new ExtentReports(filepath);
	ExtentTest Reporter = Report.startTest("Mercury Tours Login", "User should be able to book round trip ticket from Mecury Tours Website");
	public String path = "..//CustomeHTMLReport//Result//TS_";
	
	@BeforeTest
	public void mercuryToursBeforeTest() {
		System.setProperty("webdriver.chrome.driver", confFileReader.getDriverPath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(confFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		driver.get(confFileReader.getApplicationUrl());
		Reporter.log(LogStatus.INFO, "User is able to enter URL");
	}
	
	@Test
	public void mercuryToursLoginPage() {
		WebElement findFlight = driver.findElement(LoginPage.findflight);
		if(findFlight.isDisplayed()) {
			System.out.println("User is on Login Page");
			Reporter.log(LogStatus.PASS,"User is on Login Page");
			driver.findElement(LoginPage.userName).sendKeys(e2ETestConstants.UserName);
			driver.findElement(LoginPage.password).sendKeys(e2ETestConstants.password);
			Reporter.log(LogStatus.PASS, "UserName and Password is entered");
			
			driver.findElement(LoginPage.LogInBttn).click();
			
			WebElement flightFinder = driver.findElement(FlightFinderPage.flightFinderCaption);
			if(flightFinder.isDisplayed()) {
				System.out.println("User is on Flight Finder Launch Page");
				Reporter.log(LogStatus.PASS,"User is on Flight Finder Launch Page");
				WebElement tripTypeRound = driver.findElement(FlightFinderPage.tripTypeRadioBttn);
				if(tripTypeRound.isSelected()) {
					System.out.println("Round Trip is selected");
					Reporter.log(LogStatus.PASS, "Round Trip is selected");
					WebElement passengerCount = driver.findElement(FlightFinderPage.passengerCount);
					Select oselectPassCounty = new Select(passengerCount);
					oselectPassCounty.selectByIndex(e2ETestConstants.passengerCountryDrpdwnOptions);
					Reporter.log(LogStatus.FAIL,"Passenger Country Dropdown Option is selected");
				}
				else {
					tripTypeRound.click();
					Reporter.log(LogStatus.INFO,"Round Trip is clicked");
				}
			}
			
			else {
				System.out.println("User is not Flight Finder Launch Page");
				Reporter.log(LogStatus.FAIL,"User is NOT on Flight Finder Launch Page");
			}
		}
		else {
			System.out.println("User is not on Login Page");
			Reporter.log(LogStatus.FAIL,"User is NOT on Login Page");
		}
	}
	
	@AfterTest
	public void mercuryToursAfterTest() {
		Report.endTest(Reporter);
		Report.flush();
		driver.close();
		driver.quit();		
	}
}
