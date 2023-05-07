# Fix NoSuchWindowException in Selenium and JAVA


## What is a NoSuchWindowException?
NoSuchWindowException is raised when the driver is unable to find the desired window or browser tab with the given window handle. Encountering this exception means that the window you are attempting to interact with is either not present at all or has been already closed. 

## Reasons for NoSuchWindowException in Selenium
- Closure of Window
- Driver focus on another window
- Invalid Window handle
- Page is not fully loaded
- WebDriver is closed

## Best Practices to handle NoSuchWindowException
- Using explicit waits
- Using valid window handles
- Switching to the correct window
- Using try-catch block
- Regularly updating window handles

## About Project
It is created using Selenium with Java, TestNG and Maven.

This is the list of tools, being used in this framework:
1. Apache Maven
2. Java 8
3. Selenium Cloud Grid - [LambdaTest](http://www.lambdatest.com?fp_ref=vipul51) Platform
4. TestNG Framework

## Steps for Local Execution
1. Import this project in Eclipse/IntelliJ as “Existing Maven Project”
2. Go to the test file and Run test case for your desired case.
3. You can see the logs coming up on Console as your execution progresses.
4. Since we are using RemoteWebDriver and executing on Cloud Grid platform, [LambdaTest](http://www.lambdatest.com?fp_ref=vipul51), you can login to same and view detailed logs on dashboard.
