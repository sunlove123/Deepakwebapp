package com.selenium.parking;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Case11LettersInDate extends junit.framework.TestCase{
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
  public void testCase5InvalidHours() throws Exception {
    driver.get(baseUrl);
    
    //Set entry/exit dates to month/day numbers that do not exits.
    new Select(driver.findElement(By.id("Lot"))).selectByVisibleText("Short-Term Parking");
    //Enter an entry date containing letters instead of numbers.
    driver.findElement(By.id("EntryDate")).clear();
    driver.findElement(By.id("EntryDate")).sendKeys("aabbcc");
    driver.findElement(By.id("ExitDate")).clear();
    driver.findElement(By.id("ExitDate")).sendKeys("ddeeff");
    driver.findElement(By.name("Submit")).click();
    
    //Make sure proper date format error is thrown
    try {
      assertEquals("ERROR! ENTER A CORRECTLY FORMATTED DATE", driver.findElement(By.cssSelector("b")).getText().trim());
    } catch (Error e) {
      verificationErrors.append("Form did not properly handle non-existant date values.");
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
