package com.demoaut.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demoaut.base.TestBase;
import com.demoaut.uipages.BookFlightPage;
import com.demoaut.uipages.FlightFinderPage;
import com.demoaut.uipages.LoginPage;
import com.demoaut.uipages.SelectFlightPage;
import com.demoaut.utils.TestUtil;

public class BookReturnTicketTest extends TestBase {
	LoginPage loginPage;
	FlightFinderPage flightfinderpage;
	SelectFlightPage selectflightpage;
	BookFlightPage bookflightpage;
	String sheetName = "bookFlight";

	//Initializing the constructor
	public BookReturnTicketTest() {
		super();
	}

	@BeforeMethod
	public void SetUp() throws Exception {
		startbrowser();
		loginPage = new LoginPage();
		flightfinderpage = new FlightFinderPage();
		selectflightpage = new SelectFlightPage();
		bookflightpage = new BookFlightPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(dataProvider = "getData")
	public void BookReturnticketTest(String Firstname, String Lastname, String ccType, String ccNumber, String fname,
			String lname) throws InterruptedException {
		flightfinderpage.FillDetails();
		selectflightpage.selectflight();
		bookflightpage.bookFlight(Firstname, Lastname, ccType, ccNumber, fname, lname);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider
	public Object[][] getData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

}
