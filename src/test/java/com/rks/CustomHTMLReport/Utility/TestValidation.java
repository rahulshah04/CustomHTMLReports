package com.rks.CustomHTMLReport.Utility;

import org.openqa.selenium.WebDriver;


public interface TestValidation {
	
	//abstract method
    void updateTestValidation(String Expected, String ClassName, Status sta, WebDriver driver);
   
    public default void display() {
        
    }

}
