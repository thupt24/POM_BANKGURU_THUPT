package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.payment.RegisterPage;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.NewCustomerPageObject;

public class NewCustomer extends AbstractTest {
    LoginPageObject loginPage;
    NewCustomerPageObject newCustomerPage;
    HomePageObject homePage;
    private WebDriver driver;
    String email;
    String newCustomerPageURL;
    @Parameters("brower")

    @BeforeClass
    public void beforeClass(String browserName) {
	driver = openMultiBrowser(browserName);
	email = "selenium10" + randomNumber() + "@gmail.com";
	loginPage = PageFactoryManage.getLoginPage(driver);
	log.info("Login - Step 02 : Verify Login Form displayed ");
	verifyTrue(loginPage.isLoginFormDisplayed());

	log.info("Login - Step 03 : Input username and password ");
	loginPage.sendKeyToDynamicTextboxTextArea(driver, "uid", RegisterPage.USER_ID_INFO);
	loginPage.sendKeyToDynamicTextboxTextArea(driver, "password", RegisterPage.PASS_WORD_INFO);

	log.info("Login - Step 04 : Click to Login button ");
	homePage = loginPage.clickToLoginButton();

	log.info("Login - Step 05 : Verify Welcome message displayed");
	verifyTrue(homePage.isWelcomeMessageDisplayed());

	log.info("Login - Step 06 : Verify MangerID displayed ");
	verifyTrue(homePage.isMangerIdDisplayed(RegisterPage.USER_ID_INFO));
	newCustomerPageURL = "http://demo.guru99.com/v4/manager/addcustomerpage.php";
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
	closeBrowserAndDriver(driver);
    }

    @Test
    public void TC_01_ValidateCustomerNameTextBox() {
	newCustomerPage.openAnyUrl(driver,newCustomerPageURL);
	
    }
    @Test
    public void TC_02_ValidateDOBTextBox() {
	newCustomerPage.openAnyUrl(driver,newCustomerPageURL);
	
    }
    @Test
    public void TC_03_ValidateAddressTextArea() {
	newCustomerPage.openAnyUrl(driver,newCustomerPageURL);
	
    }
    @Test
    public void TC_04_ValidateCityTextBox() {
	newCustomerPage.openAnyUrl(driver,newCustomerPageURL);
	
    }
    @Test
    public void TC_05_ValidateStateTextBox() {
	newCustomerPage.openAnyUrl(driver,newCustomerPageURL);
	
    }
    @Test
    public void TC_06_ValidatePinTextBox() {
	newCustomerPage.openAnyUrl(driver,newCustomerPageURL);
	
    }
    @Test
    public void TC_07_ValidateMobileTextBox() {
	newCustomerPage.openAnyUrl(driver,newCustomerPageURL);
	
    }
    @Test
    public void TC_08_ValidateEmailTextbox() {
	newCustomerPage.openAnyUrl(driver,newCustomerPageURL);
	
    }
    @Test
    public void TC_09_ValidatePasswordTextbox() {
	newCustomerPage.openAnyUrl(driver,newCustomerPageURL);
	
    }
    
}
