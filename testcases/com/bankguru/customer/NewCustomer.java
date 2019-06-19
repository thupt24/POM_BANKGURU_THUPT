package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
    String numberNotAllowed = "Numbers are not allowed";
    String specialNotAllowed = "Special characters are not allowed";
    String firstCharacter = "First character can not have space";
    @Parameters("brower")

    @BeforeClass
    public void beforeClass(String browserName) {
	driver = openMultiBrowser(browserName);
	loginPage = PageFactoryManage.getLoginPage(driver);
	loginPage.sendKeyToDynamicTextboxTextArea(driver, "uid", "mngr202044");
	loginPage.sendKeyToDynamicTextboxTextArea(driver, "password", "YbYzepe ");
	log.info("Login - Step 04 : Click to Login button ");
	homePage = loginPage.clickToLoginButton();

	log.info("Login - Step 05 : Verify Welcome message displayed");
	verifyTrue(homePage.isWelcomeMessageDisplayed());
	homePage.openMultiplePage(driver, "New Customer");
	newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
	closeBrowserAndDriver(driver);
    }

    @Test
    public void NewCustomer_01_VerifyNameFeild_CannotBeEmpty() {
	log.info("VerifyNameFeild_CannotBeEmpty - Step 01 : Send empty name to customer name textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "name", "");

	log.info("VerifyNameFeild_CannotBeEmpty - Step 02 : Verify error message name must not be blank");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Customer Name"),
		"Customer name must not be blank");
    }

    @Test
    public void NewCustomer_02_VerifyNameFeild_CannotBeNumberic() {
	log.info("VerifyNameFeild_CannotBeNumberic - Step 01 : Send numberic to customer name textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "name", "1234");

	log.info("VerifyNameFeild_CannotBeNumberic - Step 02 : Verify error message Numbers are not allowed");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Customer Name"), numberNotAllowed);

    }

    @Test
    public void NewCustomer_03_VerifyNameFeild_CannotSpecialCharacters() {
	log.info(
		"VerifyNameFeild_CannotSpecialCharacters - Step 01 : Send special characters to customer name textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "name", "name!@#");

	log.info(
		"VerifyNameFeild_CannotSpecialCharacters - Step 02 : Verify error message Special characters are not allowed");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Customer Name"),
		specialNotAllowed);

    }

    @Test
    public void NewCustomer_04_VerifyNameFeild_FirstCharacterBlankSpace() {
	log.info(
		"VerifyNameFeild_FirstCharacterBlankSpace - Step 01 : Send first characters have space to customer name textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "name", " 123");

	log.info(
		"VerifyNameFeild_FirstCharacterBlankSpace - Step 02 : Verify error message First character can not have space");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Customer Name"),
		firstCharacter);

    }

    @Test
    public void NewCustomer_05_VerifyAddressFeild_CannotBeEmpty() {
	log.info("ValidateAddressTextArea_CannotBeEmpty - Step 01 : Send empty value to Address textarea");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "addr", "");

	log.info(
		"ValidateAddressTextArea_CannotBeEmpty - Step 02 : Verify error message Address Field must not be blank");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Address"), "Address Field must not be blank");

    }

    @Test
    public void NewCustomer_06_VerifyAddressFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first blank space character to Address textarea");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "addr", " 12344");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message Address Field must not be blank");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Address"), firstCharacter);

    }

    @Test
    public void NewCustomer_07_VerifyCityFeild_CannotBeEmpty() {
	log.info("ValidateCityTextBox_CannotBeEmpty - Step 01 : Send empty value to City textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "city", "");

	log.info("ValidateCityTextBox_CannotBeEmpty - Step 02 : Verify error message City Field must not be blank");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "City"), "City Field must not be blank");

    }

    @Test
    public void NewCustomer_08_VerifyCityField_CannotBeNumberic() {
	log.info("ValidateCityTextBox_CannotBeNumberic - Step 01 : Send numberic value to City textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "city", "123");

	log.info("ValidateCityTextBox_CannotBeNumberic - Step 02 : Verify error message Numbers are not allowed");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "City"), numberNotAllowed);

    }

    @Test
    public void NewCustomer_09_VerifyCityField_CannotSpecialCharacters() {
	log.info("CannotSpecialCharacters - Step 01 : Send special characters to City textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "city", "@!@##");

	log.info("CannotSpecialCharacters - Step 02 : Verify error message Special characters are not allowed");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "City"), specialNotAllowed);

    }

    @Test
    public void NewCustomer_10_VerifyCityFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first characters blank space to City textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "city", " HaNoi");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message First character can not have space");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "City"), firstCharacter);

    }

    @Test
    public void NewCustomer_11_VerifyStateFeild_CannotBeEmpty() {
	log.info("ValidateStateTextBox_CannotBeEmpty - Step 01 : Send empty value to City textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "state", "");

	log.info("ValidateStateTextBox_CannotBeEmpty - Step 02 : Verify error message State must not be blank");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "State"), "State must not be blank");

    }

    @Test
    public void NewCustomer_12_VerifyStateField_CannotBeNumberic() {
	log.info("ValidateStateTextBox_CannotBeNumberic - Step 01 : Send Numberic value to State textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "state", "123");

	log.info("ValidateStateTextBox_CannotBeNumberic - Step 02 : Verify error message Numbers are not allowed");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "State"), numberNotAllowed);

    }

    @Test
    public void NewCustomer_13_VerifyStateField_CannotSpecialCharacters() {
	log.info("CannotSpecialCharacters - Step 01 : Send special characters to State textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "state", "@!@##");

	log.info("CannotSpecialCharacters - Step 02 : Verify error message Special characters are not allowed");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "State"), specialNotAllowed);

    }

    @Test
    public void NewCustomer_14_VerifyStateFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first characters blank space to State textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "state", " HaNoi");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message First character can not have space");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "State"), firstCharacter);

    }

    @Test
    public void NewCustomer_15_VerfiyPinFeild_CannotBeEmpty() {
	log.info("CannotBeEmpty - Step 01 : Send empty value to City textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", "");

	log.info("CannotBeEmpty - Step 02 : Verify error message PIN Code must not be blank");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "PIN"), "PIN Code must not be blank");

    }

    @Test
    public void NewCustomer_16_VerifyPinField_MustBe6digits() {
	log.info("MustBe6digits - Step 01 : Send <6 characters value to State textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", "123");

	log.info("MustBe6digits - Step 02 : Verify error message PIN Code must have 6 Digits");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "PIN"), "PIN Code must have 6 Digits");

    }

    @Test
    public void NewCustomer_17_VerifyPinField_CannotSpecialCharacters() {
	log.info("CannotSpecialCharacters - Step 01 : Send special characters to Pin textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", "@!@##");

	log.info("CannotSpecialCharacters - Step 02 : Verify error message Special characters are not allowed");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "PIN"), specialNotAllowed);

    }

    @Test
    public void NewCustomer_18_VerifyPinFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first characters blank space to Pin textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", " HaNoi");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message First character can not have space");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "PIN"), firstCharacter);

    }

    @Test
    public void NewCustomer_19_VerifyPinField_CannotSpecialCharacters() {
	log.info("CannotSpecialCharacters - Step 01 : Send special characters to State textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", "@!@##");

	log.info("CannotSpecialCharacters - Step 02 : Verify error message Special characters are not allowed");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "PIN"), specialNotAllowed);

    }

    @Test
    public void NewCustomer_20_VerifyStateFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first characters blank space to State textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", " HaNoi");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message First character can not have space");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "PIN"), firstCharacter);

    }

    @Test
    public void NewCustomer_21_VerifyStateField_CannotCharacters() {
	log.info("CannotCharacters - Step 01 : Send characters to Pin textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", "PIN");

	log.info("CannotCharacters - Step 02 : Verify error message Characters are not allowed");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "PIN"), "Characters are not allowed");

    }

    @Test
    public void NewCustomer_22_VerifyTelephoneFeild_CannotBeEmpty() {
	log.info("CannotBeEmpty - Step 01 : Send empty name to Mobile textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "telephoneno", "");

	log.info("CannotBeEmpty - Step 02 : Verify error message name must not be blank");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Mobile Number"), "Mobile no must not be blank");
    }

    @Test
    public void NewCustomer_23_VerifyTelephoneFeild_BlankSpaceMobile() {
	log.info("BlankSpaceMobile - Step 01 : Send numberic to customer name textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "telephoneno", "123 423");

	log.info("BlankSpaceMobile - Step 02 : Verify error message Characters are not allowed");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Mobile Number"), "Characters are not allowed");

    }

    @Test
    public void NewCustomer_24_VerifyTelephoneFeild_CannotSpecialCharacters() {
	log.info("CannotSpecialCharacters - Step 01 : Send special characters to Mobile textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "telephoneno", "!@#");

	log.info("CannotSpecialCharacters - Step 02 : Verify error message Special characters are not allowed");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Mobile Number"),
		specialNotAllowed);

    }

    @Test
    public void NewCustomer_25_VerifyTelephoneFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first characters have space to Mobile textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "telephoneno", " 123");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message First character can not have space");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Mobile Number"),
		firstCharacter);

    }

    @Test
    public void NewCustomer_26_VerifyEmailFeild_CannotBeEmpty() {
	log.info("CannotBeEmpty - Step 01 : Send empty name to Email textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "emailid", "");

	log.info("CannotBeEmpty - Step 02 : Verify error message Email-ID must not be blank");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "E-mail"), "Email-ID must not be blank");
    }

    @Test
    public void NewCustomer_27_VerifyEmailFeild_IncorrectFormat() {
	log.info("IncorrectFormat - Step 01 : Send incorrect Email textbox");
	newCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "emailid", "phanthithu@");

	log.info("IncorrectFormat - Step 02 : Verify error message Email-ID is not valid");
	verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "E-mail"), "Email-ID is not valid");
    }

}
