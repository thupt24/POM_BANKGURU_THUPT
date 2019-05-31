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
		email = "selenium10" + randomNumber() + "@gmail.com";
	}

	@Test
	public void TC_01_RegisterToSystem() {
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());

		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='emailid']")).isDisplayed());

		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);

		driver.findElement(By.xpath("//input[@name='btnLogin']")).submit();

		userIdInfo = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWordInfo = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_LoginToSystem() {

		driver.get(loginPageUrl);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='uid']")).isDisplayed());

		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userIdInfo);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passWordInfo);

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userIdInfo + "']")).isDisplayed());
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
