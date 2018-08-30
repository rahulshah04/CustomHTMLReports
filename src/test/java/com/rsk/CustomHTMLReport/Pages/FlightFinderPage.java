package com.rsk.CustomHTMLReport.Pages;

import org.openqa.selenium.By;

public class FlightFinderPage {
	public static final By flightFinderCaption = By.xpath("//*[@src='/images/masts/mast_flightfinder.gif']");
	public static final By tripTypeRadioBttn = By.xpath("//*[@name='tripType' and @value='roundtrip']");
	public static final By passengerCount = By.xpath("//*[@name='passCount']");
	public static final By LogInBttn = By.xpath("//*[@name='login']");

}
