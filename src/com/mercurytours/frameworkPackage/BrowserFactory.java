package com.mercurytours.frameworkPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

	private static WebDriver driver;

	public static WebDriver startbrowser(String browsername, String url) {
		if (browsername.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\SouthWest\\Softwares\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\SouthWest\\Softwares\\chromedriver_win32\\chromedriver.exe");
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\SouthWest\\Softwares\\chromedriver_win32\\chromedriver.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
		TakesScreenshot scr = ((TakesScreenshot)driver);
		

	}
}
