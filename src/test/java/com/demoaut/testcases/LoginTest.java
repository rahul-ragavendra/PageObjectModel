package com.demoaut.testcases;

import java.io.IOException;

import org.apache.commons.mail.EmailException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demoaut.base.TestBase;
import com.demoaut.uipages.LoginPage;
import com.demoaut.utils.SendEmail;
import com.demoaut.utils.TestUtil;
import com.itextpdf.text.DocumentException;


public class LoginTest extends TestBase {
	LoginPage loginPage;
	TestUtil testUtil;
	SendEmail sendemail;
	String sheetName = "login";
	String sheetName_1 = "login_falsecred";

	//Initializing the constructor
	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void SetUp()throws Exception {
		startbrowser();
		sendemail = new SendEmail();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
	}

	@Test(priority=1,dataProvider = "getData")
	public void Logintest(String Username, String Password) throws Exception, InterruptedException {
		loginPage.login(Username, Password);
			Assert.assertTrue(driver.getCurrentUrl().startsWith("http://newtours.demoaut.com/mercuryreservation.php"));
	}
	
	@Test(priority=2,dataProvider = "getData_false")
	public void Logintest_falseValidate(String Username, String Password) throws Exception, InterruptedException {
		loginPage.login(Username, Password);
			Assert.assertTrue(driver.getCurrentUrl().contains("http://newtours.demoaut.com/mercurysignon.php"));
	}
	
	@AfterTest
	public void report() throws IOException, DocumentException, EmailException{
		testUtil.pdf();
	    sendemail.mail();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@DataProvider
	public Object[][] getData_false() {
		Object[][] data = TestUtil.getTestData(sheetName_1);
		return data;
	}
}
