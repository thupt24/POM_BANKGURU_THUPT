package com.bankguru.payment;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObject.LoginPageObject;
import pageObject.RegisterPageObject;

public class RegisterPage extends AbstractTest {
    private WebDriver driver;
    public static String USER_ID_INFO;
    public static String PASS_WORD_INFO;
    RegisterPageObject registerPage;
    LoginPageObject loginPage;
    String email, loginPageUrl;

    @Parameters("brower")

    @BeforeClass
    public void beforeClass(String browserName) {
	driver = openMultiBrowser(browserName);
	email = "selenium10" + randomNumber() + "@gmail.com";
	loginPage = PageFactoryManage.getLoginPage(driver);
    }

    @Test
    public void TC_01_RegisterToSystem() {
	log.info("Register- Step 01 : Verify Login from displayed ");
	verifyTrue(loginPage.isLoginFormDisplayed());

	log.info("Register- Step 02 : Get Login Page URL ");
	loginPageUrl = loginPage.getLoginPageUrl();

	log.info("Register- Step 03 : Click to 'here' link");
	registerPage = loginPage.clickToHereLink();

	log.info("Register- Step 04 : Input data in Email textbox ");
	registerPage.sendKeyToDynamicTextboxTextArea(driver, "emailid", email);

	log.info("Register- Step 05 : Click to login button ");
	registerPage.clickToDynamicButtonTextboxTextArea(driver, "btnLogin");

	log.info("Register- Step 06 : Get value of username and password ");
	USER_ID_INFO = registerPage.getDynamicDataTable(driver, "User ID :");
	PASS_WORD_INFO = registerPage.getDynamicDataTable(driver, "Password :");
	System.out.println("userID" + USER_ID_INFO);
	System.out.println("pass" + PASS_WORD_INFO);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
	closeBrowserAndDriver(driver);
    }

    public int randomNumber() {
	Random rd = new Random();
	return rd.nextInt(999999);
    }
}
