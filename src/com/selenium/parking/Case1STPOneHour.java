package com.selenium.parking;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Case1STPOneHour extends junit.framework.TestCase {
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
  public void testCase1STPOneHour() throws Exception {
    driver.get(baseUrl);
    
    new Select(driver.findElement(By.id("Lot"))).selectByVisibleText("Short-Term Parking");
    driver.findElement(By.id("EntryTime")).clear();
    driver.findElement(By.id("EntryTime")).sendKeys("10:00");
    driver.findElement(By.id("EntryDate")).clear();
    driver.findElement(By.id("EntryDate")).sendKeys("01/01/2014");
    driver.findElement(By.id("ExitTime")).clear();
    driver.findElement(By.id("ExitTime")).sendKeys("11:00");
    driver.findElement(By.id("ExitDate")).clear();
    driver.findElement(By.id("ExitDate")).sendKeys("01/01/2014");
    driver.findElement(By.name("Submit")).click();
    
    //Confirm correct cost for one hour of STP
    try {
      assertEquals("$ 2.00", driver.findElement(By.cssSelector("b")).getText());
    } catch (Error e) {
      verificationErrors.append("Short Term Parking one hour yield incorrect cost.");
      verificationErrors.append(e.toString());
    }
    
    //Confirm correct duration for one hour of STP
    try {
      assertEquals("(0 Days, 1 Hours, 0 Minutes)", driver.findElement(By.cssSelector("span.BodyCopy > font > b")).getText().trim());
    } catch (Error e) {
   	  verificationErrors.append("Short Term Parking one hour yield incorrect duration. ");
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
