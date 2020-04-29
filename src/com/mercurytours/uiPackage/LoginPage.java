package com.mercurytours.uiPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	
	@FindBy(how = How.ID_OR_NAME,using="password")
	WebElement password;

	@FindBy(how = How.ID_OR_NAME,using="login")
	WebElement loginButton;
	
	public void login_wordpress(String userN,String pass){
		//username.sendKeys("username");
		password.sendKeys("password");
		loginButton.click();
	}
}
