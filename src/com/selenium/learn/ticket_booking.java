package com.selenium.learn;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.Select;

public class ticket_booking {

public static void main(String[] args) {
    // TODO Auto-generated method stub

    System.setProperty("webdriver.gecko.driver", "/home/nayan/selenium_driver/geckodriver");

    WebDriver driver = new FirefoxDriver();
    
    searchFlightsErr(driver, 1);
    searchFlights(driver,"30", 2);
    searchFlights(driver, "2", 3);
    searchFlightsErr(driver, 4);
    searchHotels(driver);
    driver.close();
    logInfo("Browser closed");
   // LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
}
public static void searchFlights(WebDriver driver, String date, int num) {
	driver.get("http://www.spicejet.com/");

    driver.manage().window().maximize();
    logInfo("-------------------Test Case "+num+"-------------------");
    logInfo("SpiceJet website portal launched");

    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    driver.findElement(By.xpath("//input[@id='ctl00_mainContent_rbtnl_Trip_1']")).click();
    logInfo("Round trip button found!");

    //Select origin
    
    driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).sendKeys("DEL");
    logInfo("Source - Delhi");

    //driver.findElement(By.linkText("Delhi (DEL)")).click();
    sleepPause(1200);
    //Select destination
    driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).sendKeys("BOM");

    logInfo("Destination - Mumbai");
   // driver.findElement(By.linkText("Mumbai (BOM)")).click();

    WebElement DateWidget = driver.findElement(By.id("ui-datepicker-div"));
    List<WebElement> columns = DateWidget.findElements(By.tagName("td"));

    for (WebElement cell: columns)
    {
        if (cell.getText().equals(date))
        {
        	try
        	{
        		cell.findElement(By.linkText(date)).click();
                logInfo("To and from date set");
                driver.findElement(By.id("divpaxinfo")).click();
                Select AdultDropdown = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Adult")));

                AdultDropdown.selectByValue("2");
               

                Select ChildrenDropdown = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Child")));

                ChildrenDropdown.selectByValue("1");


                Select InfantDropdown = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Infant")));

                InfantDropdown.selectByValue("1");


                Select CurrencyDropdown = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));

                CurrencyDropdown.selectByValue("INR");

                logInfo("No. of passesngers specified");
                driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
                logInfo("Flight search button found");
                logInfo("Test Case 2 - Search flights passed!");
        	}
        	catch(Exception e)
            {
            	logInfo("cannot set date prior to current date");
            	logInfo("Test case 3 Failed!");
            }
            
            break;
        }
    }
    
}
public static void searchFlightsErr(WebDriver driver, int num) {
	driver.get("http://www.spicejet.com/");

    driver.manage().window().maximize();

    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    if(num == 1) {
    logInfo("Browser launched");
    logInfo("-------------------Test Case 1-------------------");
    logInfo("SpiceJet website portal launched");
    
    driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
    
    logInfo("Flight search button found");
    logInfo("Test Case 1 - Search flights failed! - Source and destination fields empty.");
    }
    else
    {
    	logInfo("Browser launched");
        logInfo("-------------------Test Case 4-------------------");
        logInfo("SpiceJet website portal launched");
        
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).sendKeys("DEL");
        logInfo("Source - Delhi");

        //driver.findElement(By.linkText("Delhi (DEL)")).click();
        sleepPause(1200);
        //Select destination
        driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).sendKeys("DEL");

        logInfo("Destination - Delhi");
        
        logInfo("Flight search button found");
        logInfo("Test Case 4 - Search flights failed! - Source and destination fields should not be same.");
    }
}
public static void searchHotels(WebDriver driver) {
	driver.get("http://www.spicejet.com/");

    driver.manage().window().maximize();

    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    logInfo("-------------------Test Case 5-------------------");
    logInfo("SpiceJet website portal launched");
    
    driver.findElement(By.xpath("//a[@title='Hotels']")).click();
    
    logInfo("Hotels tab clicked.");
    
    driver.findElement(By.id("ctl00_mainContent_txtOriginStation1_MST")).sendKeys("Kolkata");
    
    logInfo("Lodging city entered - Kolkata");

    driver.findElement(By.linkText("Kolkata")).click();
   
    driver.findElement(By.id("ctl00_mainContent_ButtonSubmit_MST")).click();
    
    logInfo("Search hotels button found.");
    logInfo("Search hotels button clicked.");
    logInfo("Test case 5 - Search hotels : Passed!");
    

}
public static void sleepPause(int ms) {
	
	try {
		Thread.sleep(ms);
	} catch (Exception e) {
		logInfo("Exception : " + e.toString() );
	}
	
}
public static void logInfo(String msg) {
	
	System.out.println( msg );
	
}
}
