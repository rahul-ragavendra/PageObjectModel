package com.demoaut.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.demoaut.utils.TestUtil;
import com.demoaut.utils.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver efw_driver;
	public static WebEventListener webeventlistener;

	//Constructor will get initialized
	public TestBase() {
		try {
			prop = new Properties();
			prop.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
    // Starting the browser
	public static void startbrowser() {
		String browsername = prop.getProperty("browser");

		if (browsername.equals("chrome")) {
			System.setProperty(prop.getProperty("driver"), prop.getProperty("path"));
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
		}
		
		//WebEventListener used to record each step in console
		/*efw_driver = new EventFiringWebDriver(driver);
		webeventlistener = new WebEventListener();
		efw_driver.register(webeventlistener);
		driver = efw_driver;*/

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGELOADTIME, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICITWAITTIME, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

}
