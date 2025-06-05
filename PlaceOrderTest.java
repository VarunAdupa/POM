package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pages.LoginPage;
import utils.Base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class PlaceOrderTest extends Base{
	ExtentSparkReporter spark;
	ExtentReports extReports;
	ExtentTest test;
	
	@BeforeClass
	public void setUpReports() {
		spark = new ExtentSparkReporter("reports\\ExtentReport.html");
		extReports = new ExtentReports();
		extReports.attachReporter(spark);
	}

	@AfterClass
	public void writeReports() {
		extReports.flush();
	}
	public void setUp() {
		launchBrowser();
	}



  @Test
  public void testPlaceOrder() {
	  test = extReports.createTest("Place Order");
	  LoginPage loginPage = new LoginPage(driver,test);
	  loginPage.validateLogin("standard_user", "secret_sauce");
  }
  @BeforeMethod
  public void beforeMethod() {
	  launchBrowser();
  }

  @AfterMethod
  public void afterMethod() {
	  sleep(5000);
	  driver.quit();
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  

}
