package utils;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	
	public WebDriver driver;
	public void launchBrowser() {
		Properties prop = PropertyReader.readProperties();
		String browserName = prop.getProperty("Browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}else {
			System.out.println("Invalid Browser name pls check");
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("URL"));
		
	}
	public static void sleep(int msec) {
		try {
			Thread.sleep(msec);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
