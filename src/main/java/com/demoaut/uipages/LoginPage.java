package com.demoaut.uipages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoaut.base.TestBase;

public class LoginPage extends TestBase {
	
	//Initializing the constructor
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Locating the WebElements using PageFactory
	@FindBy(name = "userName")
	@CacheLookup
	public WebElement userName;

	@FindBy(name = "password")
	@CacheLookup
	WebElement password;

	@FindBy(name = "login")
	@CacheLookup
	WebElement loginBtn;

	@FindBy(xpath = "//a[contains(text(),'Flights')]")
	@CacheLookup
	WebElement flightLnk;

	public String LoginPageTitle() {
		return driver.getTitle();
	}

	//Method to fill in the details
	public void login(String un, String pwd) throws InterruptedException, Exception {
			userName.sendKeys(un);
			password.sendKeys(pwd);
			loginBtn.click();
	}

}
