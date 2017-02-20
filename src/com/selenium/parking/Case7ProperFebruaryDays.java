package com.selenium.parking;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Case7ProperFebruaryDays extends junit.framework.TestCase {
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
  public void testCase7ProperFebruaryDays() throws Exception {
    driver.get(baseUrl);
    
    //Set duration of parking to last entire month of February.
    new Select(driver.findElement(By.id("Lot"))).selectByVisibleText("Short-Term Parking");
    driver.findElement(By.id("EntryDate")).clear();
    driver.findElement(By.id("EntryDate")).sendKeys("02/01/2014");
    driver.findElement(By.id("ExitDate")).clear();
    driver.findElement(By.id("ExitDate")).sendKeys("03/01/2014");
    driver.findElement(By.name("Submit")).click();
    
    //Make sure form reflects that February has 28 days
    try {
      assertEquals("(28 Days, 0 Hours, 0 Minutes)", driver.findElement(By.cssSelector("span.BodyCopy > font > b")).getText().trim());
    } catch (Error e) {
      verificationErrors.append("Form did not properly account for February having 28 days. ");
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
