package com.bankguru.account;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Account_Level_01_StepByStep_TopDown {
	WebDriver driver;
	String userIdInfo;
	String passWordInfo;
	String loginPageUrl;
	String email;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		email = "selenium10" +randomNumber()+ "@gmail.com";
	}

	@Test
	public void TC_01_RegisterToSystem() {
		/* Step 01 - Check login page displayed */
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());

		/* Step 02 - Click to 'here' -> Open Register Page */
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();

		/* Step 03 - Check Register Page displayed */
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='emailid']")).isDisplayed());

		/* Step 04 - sendKey to email textBox */
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);

		/* Step 05 - click submit button */
		driver.findElement(By.xpath("//input[@name='btnLogin']")).submit();

		/* Step 06 - get value of User id and password */
		userIdInfo = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWordInfo = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_LoginToSystem() {
		/* Step 01 - Open login Page*/
		driver.get(loginPageUrl);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='uid']")).isDisplayed());
		
		/* Step 02 - input user and password */
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userIdInfo);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passWordInfo);
		
		/* Step 03 - click login button */
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		/* Step 04 - Check homePage page displayed */
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		/* Step 05 - Check userId info displayed */
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : "+ userIdInfo +"']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public int randomNumber() {
		Random rd = new Random();
		return rd.nextInt(999999);
	}

}
