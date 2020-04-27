package com.demoaut.uipages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.demoaut.base.TestBase;

public class BookFlightPage extends TestBase {
	
	//Initializing the constructor
	public BookFlightPage(){
		PageFactory.initElements(driver, this);
	}

	//Locating the WebElements using PageFactory
	@FindBy(xpath = "//input[@name='passFirst0']")
	@CacheLookup
	public WebElement firstName;

	@FindBy(xpath = "//input[@name='passLast0']")
	@CacheLookup
	public WebElement lastName;

	@FindBy(xpath = "//select[@name='pass.0.meal']")
	@CacheLookup
	public WebElement meal;

	@FindBy(xpath = "//select[@name='creditCard']")
	@CacheLookup
	public WebElement creditCard;

	@FindBy(xpath = "//input[@name='creditnumber']")
	@CacheLookup
	public WebElement creditNumber;

	@FindBy(xpath = "//select[@name='cc_exp_dt_mn']")
	@CacheLookup
	public WebElement expiryMonth;

	@FindBy(xpath = "//select[@name='cc_exp_dt_yr']")
	@CacheLookup
	public WebElement expiryYear;

	@FindBy(xpath = "//input[@name='cc_frst_name']")
	@CacheLookup
	public WebElement cardFirstName;

	@FindBy(xpath = "//input[@name='cc_last_name']")
	@CacheLookup
	public WebElement cardLastName;

	@FindBy(xpath = "//input[@name='buyFlights']")
	@CacheLookup
	public WebElement book;

	public void selectMeal() {
		Select select = new Select(meal);
		select.selectByVisibleText("Vegetarian");
	}

	public void selectCard(String ccType) {
		Select select = new Select(creditCard);
		select.selectByVisibleText(ccType);
	}

	public void selectMonth() {
		Select select = new Select(expiryMonth);
		select.selectByVisibleText("05");
	}

	public void selectYear() {
		Select select = new Select(expiryYear);
		select.selectByVisibleText("2010");
	}

	//Method to fill in the details
	public void bookFlight(String firstname, String lastname,String ccType,String ccNumber,String ccfname, String cclname) {
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		selectMeal();
		selectCard(ccType);
		creditNumber.sendKeys(ccNumber);
		selectMonth();
		selectYear();
		cardFirstName.sendKeys(ccfname);
		cardLastName.sendKeys(cclname);
		book.click();

	}

}
