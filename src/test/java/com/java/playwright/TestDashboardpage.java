package com.java.playwright;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter; // Import ExtentSparkReporter
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.ExtentXmlReporter;
import com.microsoft.playwright.*;

import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class TestDashboardpage {
	ExtentReports extent;
    ExtentTest test;
  
	@BeforeEach
	void setup() {
		extent = new ExtentReports();
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-report.html");
		extent.attachReporter(htmlReporter);
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.json");
		extent.attachReporter(sparkReporter);
		
//		ExtentXmlReporter xmlReporter = new ExtentXmlReporter("extent.xml");
//		extent.attachReporter(xmlReporter);
	
    }

    @AfterEach
    void cleanup() {
        extent.flush();
    }
   @Test
    void shouldClickButton() throws InterruptedException {

    	  try (Playwright playwright = Playwright.create()) {
    	    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    	    BrowserContext context = browser.newContext();
    	    Page page = context.newPage();
    	    page.navigate("https://app.marxeed.com/");
    	    page.navigate("https://app.marxeed.com/home");
    	    page.getByRole(AriaRole.BANNER).filter(new Locator.FilterOptions().setHasText("ServicesSupportCredits(...)Site Guide")).getByRole(AriaRole.BUTTON).nth(1).click();
    	    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("name@host.com")).click();
    	    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("name@host.com")).fill("omkar.jodh@appliedaiconsulting.com");
    	    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
    	    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("Omkar@12345");
    	    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("submit")).click();
    	    
    	    
    	  test = extent.createTest("Marxeed Logo");
    	  test.log(Status.INFO, "Test started");
    	  // Your existing test code
    	  String currentURL = page.url();
    	  System.out.println("Current page url: " + currentURL);
    	  Thread.sleep(2000);
    	  String currentTitle = page.title();
    	  System.out.println("Current Page Title: " + currentTitle);
    	  // Add the status of the test based on the outcome
    	  if (currentTitle.equals("marXeed")) {
    	      test.log(Status.PASS, "Test Passed");
    	  } else {
    	      test.log(Status.FAIL, "Test Failed");
    	  }
    	} catch (Exception e) {
    	  e.printStackTrace();
    	  // Add test failure status if an exception is caught
    	  test.log(Status.FAIL, "Test Failed");
    	}
    }
}
