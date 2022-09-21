package com.webDriverUniversityFramework.stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import com.webDriverUniversityFramework.utils.DriverFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class MasterHooks extends DriverFactory {

	WebDriver driver;
	
//	public MasterHooks() {
//		super();
//	}
//
//	@Before
//	public void setup() throws Exception {
//		System.out.println("Before: Setting up the driver.");
//		SessionId sessionId = null;
//		
//		driver = getDriver();
//		sessionId = ((RemoteWebDriver) driver).getSessionId();
//		System.out.println("Before:Have set up the driver with sessionId " + sessionId.toString());
//	}
//
//	@After
//	public void tearDown() {
//		SessionId sessionId = null;
//
//		System.out.println("After: Closing the driver.");
//
//		if (driver != null) {
//			try {
//				sessionId = ((RemoteWebDriver) driver).getSessionId();
//				System.out.println("The sessionId = " + sessionId.toString());
//				driver.manage().deleteAllCookies();
//
//				driver.quit();
//				driver = null;
//				System.out.println("After: Have closed + quit the driver.\n");
//			} catch (Exception e) {
//				System.out.println("The handle to the drive is now null. Cannot quit anymore.");
//			}
//		} else {
//			System.out.println("After: the driver is null.");
//		}
//	}
}
