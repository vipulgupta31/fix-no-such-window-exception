package test;

import java.net.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.*;

public class TestNoSuchWindowException {

	public RemoteWebDriver driver = null;

	String username = System.getenv("LT_USERNAME") == null ? "<lambdatest_username>" : System.getenv("LT_USERNAME");
	String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "<lambdatest_accesskey>" : System.getenv("LT_ACCESS_KEY");

	@BeforeTest
	public void setup() {
		try {
			SafariOptions safariOptions = new SafariOptions();
			safariOptions.setPlatformName("MacOS Big sur");
			safariOptions.setBrowserVersion("14.0");
			
			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("build", "NoSuchWindowException in Selenium");
			ltOptions.put("name", "Handling NoSuchWindowException");
			ltOptions.put("w3c", true);
			safariOptions.setCapability("LT:Options", ltOptions);
			
			driver = new RemoteWebDriver(
					new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), safariOptions);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		 driver.get("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testNoSuchWindowException() {
		
		System.out.println("Clicking on Follow button");
		driver.findElement(By.xpath("//*[@title='Follow @Lambdatesting on Twitter']")).click();
		
		Set<String> handles = driver.getWindowHandles();
		for(String handle : handles)
		{
			System.out.println("Valid handle value : " + handle);
			//adding -updated to valid handle value to demonstrate exception 
			//due to invalid handle value
			driver.switchTo().window(handle + "-updated");
		}
	}

	@Test
	public void testNoSuchWindowException_fix() {
		
		System.out.println("Clicking on Follow button");
		driver.findElement(By.xpath("//*[@title='Follow @Lambdatesting on Twitter']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		System.out.println("Verified total windows are 2");
		
		System.out.println("Fetching window handles of all windows");
		Set<String> handles = driver.getWindowHandles();

		System.out.println("Switching to each window one by one to verify if it is required one");
		for(String handle : handles)
		{
			System.out.println("Switching to window with handle : " + handle);
			driver.switchTo().window(handle);
			//using window title to reach to required window
			if(driver.getTitle().equals("Profile / Twitter"))
			{
				System.out.println("Reached required window");
				break;
			}
		}

		driver.findElement(By.xpath("//*[@data-testid='login']")).click();
		System.out.println("Clicked Login button successfully");
	}
	
	@Test
	public void testNoSuchWindowException_fix_tryCatch() {
		
		System.out.println("Clicking on Follow button");
		driver.findElement(By.xpath("//*[@title='Follow @Lambdatesting on Twitter']")).click();
		
		try 
		{
			Set<String> handles = driver.getWindowHandles();
			for(String handle : handles)
			{
				System.out.println("Valid handle value : " + handle);
				//adding -updated to valid handle value to get exception 
				//due to invalid handle value
				System.out.println("Invalid handle value used : " + handle + "-updated");
				driver.switchTo().window(handle + "-updated");
			}
		} catch (NoSuchWindowException ex) {
			System.out.println("We are inside catch block");
			System.out.println("NoSuchWindowException has been caught.");
		}
	}

}
