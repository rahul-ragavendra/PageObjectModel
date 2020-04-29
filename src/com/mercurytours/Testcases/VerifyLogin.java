package com.mercurytours.Testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.mercurytours.frameworkPackage.BrowserFactory;
import com.mercurytours.uiPackage.LoginPage;

public class VerifyLogin {
    @org.testng.annotations.Test
	public void CheckvalidUser(){
		WebDriver driver = BrowserFactory.startbrowser("chrome","http://demowebshop.tricentis.com");
		driver.findElement(By.linkText("Log in")).click();
		//driver.findElement(By.linkText("Register")).click();
		/*driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Raul");
		driver.findElement(By.id("LastName")).sendKeys("Paul");*/
		driver.findElement(By.id("Email")).sendKeys("mailraga3@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("moto123");
		/*driver.findElement(By.id("ConfirmPassword")).sendKeys("moto123");
		driver.findElement(By.id("register-button")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[2]/input")).click();*/
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")).click();
		driver.findElement(By.id("vote-poll-1")).click();
        List<WebElement> ele = driver.findElements(By.cssSelector("#poll-block-1 > ul"));
        for(WebElement el:ele){
        	System.out.println(el.getText());
        }
       // System.out.println(ele);
        driver.quit();
       // #poll-block-1 > ul
        //#poll-block-1 > ul > li:nth-child(2)
		
	}
	
}
