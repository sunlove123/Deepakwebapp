package com.selenium.parking;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.JUnitCore;

public class ParkingCalculatorSuite {

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(Case1STPOneHour.class);
    suite.addTestSuite(Case2LTPOneMonth.class);
    suite.addTestSuite(Case3ExitBeforeEntryDate.class);
    suite.addTestSuite(Case4ExitBeforeEntryTime.class);
    suite.addTestSuite(Case5NegativeHours.class);
    suite.addTestSuite(Case6NonexistentDates.class);
    suite.addTestSuite(Case7ProperFebruaryDays.class);
    suite.addTestSuite(Case8EmptyTime.class);
    suite.addTestSuite(Case9EmptyDate.class);
    suite.addTestSuite(Case10LettersInTime.class);
    suite.addTestSuite(Case11LettersInDate.class);
    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
    JUnitCore.main(
            "com.selenium.parking"); 
  }
}
