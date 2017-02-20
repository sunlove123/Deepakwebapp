package com.selenium.parking;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Case2LTPOneMonth extends junit.framework.TestCase{
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	
    driver = new FirefoxDriver();
    baseUrl = "http://adam.goucher.ca/parkcalc/index.php";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCase2LTPOneMonth() throws Exception {
    driver.get(baseUrl);
    new Select(driver.findElement(By.id("Lot"))).selectByVisibleText("Long-Term Surface Parking");

    //Store the current window handle
    String parentWin = driver.getWindowHandle();
   
    //Perform the click operation that opens Date Picker for Entry Date
    WebElement element = driver.findElement(By.cssSelector("img[alt=\"Pick a date\"]"));
    element.click();
    
    //Switch to new window opened
    for(String winHandle : driver.getWindowHandles()){
        driver.switchTo().window(winHandle);
    }
    
    //Select Month and Day
    new Select(driver.findElement(By.name("MonthSelector"))).selectByVisibleText("January");
    driver.findElement(By.linkText("1")).click();
    //Switch to parent window
    driver.switchTo().window(parentWin);

    //Repeat above steps for Leaving Date
    driver.findElement(By.xpath("(//img[@alt='Pick a date'])[2]")).click();
    driver.switchTo().window(parentWin);
    for(String winHandle : driver.getWindowHandles()){
        driver.switchTo().window(winHandle);
    }
    new Select(driver.findElement(By.name("MonthSelector"))).selectByVisibleText("February");
    driver.findElement(By.linkText("1")).click();
    driver.switchTo().window(parentWin);
    driver.findElement(By.name("Submit")).click();

    //Confirm correct LTP Cost
    try {
      assertEquals("$ 270.00", driver.findElement(By.cssSelector("b")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    //Confirm correct LTP Duration
    try {
      assertEquals("(31 Days, 0 Hours, 0 Minutes)", driver.findElement(By.cssSelector("span.BodyCopy > font > b")).getText().trim());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
