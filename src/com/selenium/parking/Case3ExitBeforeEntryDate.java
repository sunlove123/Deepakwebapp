package com.selenium.parking;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Case3ExitBeforeEntryDate extends junit.framework.TestCase{
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
  public void testCase3ExitBeforeEntryDate() throws Exception {
    driver.get(baseUrl);
    
    //Set entry date to AFTER the exit date.
    new Select(driver.findElement(By.id("Lot"))).selectByVisibleText("Short-Term Parking");
    driver.findElement(By.id("EntryDate")).clear();
    driver.findElement(By.id("EntryDate")).sendKeys("01/02/2014");
    driver.findElement(By.id("ExitDate")).clear();
    driver.findElement(By.id("ExitDate")).sendKeys("01/01/2014");
    driver.findElement(By.name("Submit")).click();
    
    //Make sure proper entry/exit date error is thrown
    try {
      assertEquals("ERROR! YOUR EXIT DATE OR TIME IS BEFORE YOUR ENTRY DATE OR TIME", driver.findElement(By.cssSelector("b")).getText().trim());
    } catch (Error e) {
      verificationErrors.append("Exit DATE before entry DATE yields incorrect error. ");
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
