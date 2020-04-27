package com.demoaut.uipages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoaut.base.TestBase;
import com.demoaut.utils.TestUtil;

public class SelectFlightPage extends TestBase {
	
	//Initializing the constructor
	public SelectFlightPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Locating the WebElements using PageFactory
	@FindBy(xpath = "//input[@value='Pangea Airlines$362$274$9:17']")
	@CacheLookup
	public WebElement selectDepartFlight;
	
	@FindBy(xpath = "//input[@src='/images/forms/continue.gif']")
	@CacheLookup
	public WebElement returnFlight;
	
	@FindBy(name = "reserveFlights")
	@CacheLookup
	public WebElement continuebtn;
	
	//Method to fill in the details
	public void selectflight() throws InterruptedException{
		TestUtil.scrollDown();
		Thread.sleep(4000);
		TestUtil.mouseHover(driver, continuebtn);
	}

}
