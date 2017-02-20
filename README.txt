Testing Approach
Dalton Stout, Mindbody STE Homework

	Given the small size of the application under test and the lack of specifications/source code, my testing procedure for the Parking Calculator was fairly straight forward. I employed a black-box testing approach which focused on cause and effect expectations and error guessing. I wanted to test common user input mistakes (e.g. empty fields) combined with specialized errors that can occur given the date/time domain that I was working in (e.g. non-existent dates, negative time values). It should be noted that not all expected error messages were known to me. In the case that I did not know the proper error message to verify, I instead verified the incorrect behavior to fail the test (e.g. Test Case 5 fails if the Duration is still calculated with improper negative time values). To keep consistent with the behavior of the tests that did have an known expected error message, I included additional code that looked for error messages that I created and left them commented out in my source code for easy future implementation. 
	I used the Selenium IDE to record the bulk of the test automation and exported it to a Selenium Webdriver format which I implemented in Java. The Test Suite was implemented and managed using Junit. I used manual tweaking of the Java code where necessary. I also included a few extra tests (Cases 9-11) in the interest of being thorough and to reproduce additional bugs that I found in my own exploration of the application. The runnable jar can be found in the parent folder and the GitHub repository can be found here:
https://github.com/lowfr3q/MindbodyParking


Test Cases 4-11
Case 4 (Exit Time Before Entry Time)
Navigate to http://adam.goucher.ca/parkcalc/index.php
Leave the Short-Term Parking option unchanged from the Choose a Lot dropdown
Enter 2:00 and 01/01/2014 in the Choose Entry Date and Time section
Leave AM option unchanged in the Choose Entry Date and Time section
Enter 1:00 and 01/01/2014 in the Choose Leaving Date and Time section
Leave AM option unchanged in the Choose Leaving Date and Time section
Click Calculate
Check that the following error message appears: ERROR! YOUR EXIT DATE OR TIME IS
BEFORE YOUR ENTRY DATE OR TIME

Case 5 (Negative Time)
Navigate to http://adam.goucher.ca/parkcalc/index.php
Leave the Short-Term Parking option unchanged from the Choose a Lot dropdown
Enter -2:00 and 01/01/2014 in the Choose Entry Date and Time section
Leave AM option unchanged in the Choose Entry Date and Time section
Enter -1:00 and 01/01/2014 in the Choose Leaving Date and Time section
Leave AM option unchanged in the Choose Leaving Date and Time section
Click Calculate
Check that an Invalid Time error message appears and that a Cost/Duration is not calculated. 

Case 6 (Non-existent Date)
Navigate to http://adam.goucher.ca/parkcalc/index.php
Leave the Short-Term Parking option unchanged the Choose a Lot dropdown
Enter 13/32/2014 in the Choose Entry Date and Time section
Leave AM option and Entry Time unchanged in the Choose Entry Date and Time section
Enter 14/32/2014 in the Choose Leaving Date and Time section
Leave AM option and Leaving Time unchanged in the Choose Leaving Date and Time section
Click Calculate
Check that the following error message appears: ERROR! Enter A Correctly Formatted Date

Case 7 (February Days Check)
Navigate to http://adam.goucher.ca/parkcalc/index.php
Leave the Short-Term Parking option unchanged from the Choose a Lot dropdown
Enter 02/01/2014 in the Choose Entry Date and Time section
Leave the AM option and the Entry Time unchanged in the Choose Entry Date and Time section
Enter 03/01/2014 in the Choose Leaving Date and Time section
Leave the AM option and the Leaving Time unchanged in the Choose Leaving Date and Time section
Click Calculate
Check that the duration of stay is equal to (28 Days, 0 Hours, 0 Minutes)

Case 8 (Empty Time)
Navigate to http://adam.goucher.ca/parkcalc/index.php
Leave the Short-Term Parking option unchanged from the Choose a Lot dropdown
Clear the Entry Time (leave text box blank) in the Choose Entry Date and Time section
Enter 01/01/2014 and leave the AM option unchanged in the Choose Entry Date and Time section
Clear the Leaving Time (leave text box blank) in the Choose Leaving Date and Time section
Enter 01/01/2014 and leave the AM option unchanged in the Choose Leaving Date and Time section
Click Calculate
Check that an Empty Time error appears and that a Cost/Duration is not calculated

Case 9 (Empty Date)
Navigate to http://adam.goucher.ca/parkcalc/index.php
Leave the Short-Term Parking option unchanged from the Choose a Lot dropdown
Clear the Entry Date (leave text box blank) in the Choose Entry Date and Time section
Leave the Entry Time and the AM option unchanged in the Choose Entry Date and Time section
Clear the Leaving Date (leave text box blank) in the Choose Entry Date and Time section
Leave the Leaving Time and the AM option unchanged in the Choose Leaving Date and Time section
Click Calculate
Check that an Empty Date error appears and that a Cost/Duration is not calculated

Case 10 (Time Containing Letters)
Navigate to http://adam.goucher.ca/parkcalc/index.php
Leave the Short-Term Parking option unchanged from the Choose a Lot dropdown
Enter aabbcc (for the Entry Time) and 01/01/2014 in the Choose Entry Date and Time section
Leave AM option unchanged in the Choose Entry Date and Time section
Enter 01/01/2014 in the Choose Leaving Date and Time section
Leave Leaving Time and AM option unchanged in the Choose Leaving Date and Time section
Click Calculate
Check that an Invalid Time error message appears and that a Cost/Duration is not calculated. 

Case 11 (Date Containing Letters)
Navigate to http://adam.goucher.ca/parkcalc/index.php
Leave the Short-Term Parking option unchanged from the Choose a Lot dropdown
Enter aabbcc (for the Entry Date) in the Choose Entry Date and Time section
Leave the Entry Time and the AM option unchanged in the Choose Entry Date and Time section
Enter ddeeff (for the Leaving Date) in the Choose Leaving Date and Time section
Leave the  Leaving Time and AM option unchanged in the Choose Leaving Date and Time section
Click Calculate
Check that the following error message appears: ERROR! Enter A Correctly Formatted Date


Bug Report

Bug Name: Application still calculates Cost/Duration with Entry Time coming before Exit Time with same Entry/Exit Date, does not spawn error message.
Bug ID: 01
Severity: HIGH
Priority: HIGH
Reproduction Steps: See Test Case 4

Bug Name: Application still calculates Cost/Duration with Negative Time values, does not spawn error message
Bug ID: 02
Severity: HIGH
Priority: HIGH
Reproduction Steps: See Test Case 5

Bug Name: Application still calculates Cost/Duration with Dates outside the 12 month format, does not spawn error message
Bug ID: 03
Severity: HIGH
Priority: HIGH
Reproduction Steps: See Test Case 6

Bug Name: Application still calculates Cost/Duration with Empty Time values, does not spawn error message
Bug ID: 04
Severity: HIGH
Priority: HIGH
Reproduction Steps: See Test Case 8

Bug Name: Application still calculates Cost/Duration with Empty Date values, does not spawn error message
Bug ID: 05
Severity: HIGH
Priority: HIGH
Reproduction Steps: See Test Case 9

Bug Name: Application still calculates Cost/Duration with Time value containing letters, does not spawn error message
Bug ID: 05
Severity: HIGH
Priority: HIGH
Reproduction Steps: See Test Case 10

Bug Name: Application still calculates Cost/Duration with Date values containing letters, does not spawn error message
Bug ID: 06
Severity: HIGH
Priority: HIGH
Reproduction Steps: See Test Case 11
