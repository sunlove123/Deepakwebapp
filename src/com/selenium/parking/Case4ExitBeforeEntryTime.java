package com.selenium.parking;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Case4ExitBeforeEntryTime extends junit.framework.TestCase{
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
  public void testCase4ExitBeforeEntryTime() throws Exception {
    driver.get(baseUrl);
    
    //Set entry time AFTER the exit time.
    new Select(driver.findElement(By.id("Lot"))).selectByVisibleText("Short-Term Parking");
    driver.findElement(By.id("EntryTime")).clear();
    driver.findElement(By.id("EntryTime")).sendKeys("2:00");
    driver.findElement(By.id("EntryDate")).clear();
    driver.findElement(By.id("EntryDate")).sendKeys("01/01/2014");
    driver.findElement(By.id("ExitTime")).clear();
    driver.findElement(By.id("ExitTime")).sendKeys("1:00");
    driver.findElement(By.id("ExitDate")).clear();
    driver.findElement(By.id("ExitDate")).sendKeys("01/01/2014");
    driver.findElement(By.name("Submit")).click();
    
   //Make sure proper entry/exit time error is thrown
    try {
      assertEquals("ERROR! Your Exit Date Or Time Is Before Your Entry Date or Time", driver.findElement(By.cssSelector("b")).getText().trim());
    } catch (Error e) {
      verificationErrors.append("Exit TIME before Entry TIME yields incorrect error. ");
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
