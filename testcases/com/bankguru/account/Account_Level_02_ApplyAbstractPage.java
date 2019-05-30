package com.bankguru.account;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Account_Level_02_ApplyAbstractPage {
	WebDriver driver;
	String userIdInfo, passWordInfo, loginPageUrl, email;
	AbstractPage abstractPage;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		abstractPage = new AbstractPage();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		abstractPage.openAnyUrl(driver, "http://demo.guru99.com/v4/");
		email = "selenium10" + randomNumber() + "@gmail.com";
	}

	@Test
	public void TC_01_RegisterToSystem() {

		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//form[@name='frmLogin']"));

		loginPageUrl = abstractPage.getCurrentUrl(driver);

		abstractPage.clickToElement(driver, "//a[text()='here']");

		abstractPage.sendKeyToElement(driver, "//input[@name='emailid']", email);

		abstractPage.submitToElement(driver, "//input[@name='btnLogin']");

		userIdInfo = abstractPage.getText(driver, "//td[text()='User ID :']/following-sibling::td");

		passWordInfo = abstractPage.getText(driver, "//td[text()='Password :']/following-sibling::td");
	}

	@Test
	public void TC_02_LoginToSystem() {

		abstractPage.openAnyUrl(driver, loginPageUrl);

		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//input[@name='uid']"));

		abstractPage.sendKeyToElement(driver, "//input[@name='uid']", userIdInfo);

		abstractPage.sendKeyToElement(driver, "//input[@name='password']", passWordInfo);
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");

		Assert.assertTrue(abstractPage.isControlDisplayed(driver,
				"//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));

		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//td[text()='Manger Id : " + userIdInfo + "']"));

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
