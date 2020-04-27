package com.demoaut.uipages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.demoaut.base.TestBase;

public class FlightFinderPage extends TestBase{
	
	//Initializing the constructor
	public FlightFinderPage() {
		PageFactory.initElements(driver, this);
	}

	//Locating the WebElements using PageFactory
	@FindBy(xpath = "//input[@value='oneway']")
	@CacheLookup
	public WebElement tripType;

	@FindBy(xpath = "//select[@name='passCount']")
	@CacheLookup
	public WebElement noOfPassengers;

	@FindBy(name = "fromPort")
	@CacheLookup
	public WebElement Departure;

	@FindBy(name = "fromMonth")
	@CacheLookup
	public WebElement fromMonth;

	@FindBy(name = "fromDay")
	@CacheLookup
	public WebElement fromDay;

	@FindBy(name = "toPort")
	@CacheLookup
	public WebElement Arrival;

	@FindBy(name = "toMonth")
	@CacheLookup
	public WebElement toMonth;

	@FindBy(name = "toDay")
	@CacheLookup
	public WebElement toDay;

	@FindBy(xpath = "//input[@value='Business']")
	@CacheLookup
	public WebElement serviceClass;

	@FindBy(name = "airline")
	@CacheLookup
	public WebElement airline;

	@FindBy(xpath = "//input[@name='findFlights']")
	@CacheLookup
	public static WebElement submitbtn;

	public void selectPassenger() {
		Select select = new Select(noOfPassengers);
		//select.selectByVisibleText(noOfpax);
		select.selectByVisibleText("2");
	}

	public void selectDepartFrm() {
		Select select = new Select(Departure);
		//select.selectByVisibleText(depart);
		select.selectByVisibleText("Frankfurt");
	}

	public void selectOnMonth() {
		Select select = new Select(fromMonth);
		//select.selectByVisibleText(frmMonth);
		select.selectByVisibleText("May");
	}

	public void selectOnDate() {
		Select select = new Select(fromDay);
		//select.selectByVisibleText(frmDate);
		select.selectByVisibleText("15");
	}

	public void arrival() {
		Select select = new Select(Arrival);
		//select.selectByVisibleText(arrivalTo);
		select.selectByVisibleText("London");
	}

	public void selectToMonth() {
		Select select = new Select(toMonth);
		//select.selectByVisibleText(toMnth);
		select.selectByVisibleText("May");
	}

	public void selectToDate() {
		Select select = new Select(toDay);
		//select.selectByVisibleText(toDate);
		select.selectByVisibleText("21");
	}

	//Method to fill in the details
	public void FillDetails() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		tripType.click();
		selectPassenger();
		selectDepartFrm();
		selectOnMonth();
		selectOnDate();
		arrival();
		selectToMonth();
		selectToDate();
		serviceClass.click();
		submitbtn.click();
	}

}
