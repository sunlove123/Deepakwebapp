package com.selenium.parking;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Case9EmptyDate extends junit.framework.TestCase{
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
  public void testCase8EmptyDateOrTime() throws Exception {
    driver.get(baseUrl);
    
    //Enter empty entry time
    new Select(driver.findElement(By.id("Lot"))).selectByVisibleText("Short-Term Parking");
    driver.findElement(By.id("EntryDate")).clear();
    driver.findElement(By.id("EntryDate")).sendKeys("");
    driver.findElement(By.id("ExitDate")).clear();
    driver.findElement(By.id("ExitDate")).sendKeys("");
    driver.findElement(By.name("Submit")).click();

    // Since the proper error to be thrown was not included in the spec, this is a way to catch the unexpected behavior
    // without knowledge of the proper error message.
    try {
      assertThat("(0 Days, 0 Hours, 0 Minutes)", is(not(driver.findElement(By.cssSelector("span.BodyCopy > font > b")).getText().trim())));
    } catch (Error e) {
      verificationErrors.append("Form did not properly handle empty date values. ");
      verificationErrors.append(e.toString());
    }
    
    // This is an error message designed by me that should be displayed if the date field is empty.
//    try {
//      assertEquals("ERROR! YOUR ENTRY DATE OR EXIT DATE IS EMPTY", driver.findElement(By.cssSelector("b")).getText());
//    } catch (Error e) {
//      verificationErrors.append(e.toString());
//    }
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
