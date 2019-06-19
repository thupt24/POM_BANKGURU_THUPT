package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObject.EditCustomerPageObject;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.NewCustomerPageObject;

public class EditCustomer extends AbstractTest {
    LoginPageObject loginPage;
    NewCustomerPageObject newCustomerPage;
    HomePageObject homePage;
    EditCustomerPageObject editCustomerPage;
    private WebDriver driver;
    public static String UserID;
    @Parameters("brower")

    @BeforeClass
    public void beforeClass(String browserName) {
	driver = openMultiBrowser(browserName);
	loginPage = PageFactoryManage.getLoginPage(driver);
	loginPage.sendKeyToDynamicTextboxTextArea(driver, "uid", "mngr202044");
	loginPage.sendKeyToDynamicTextboxTextArea(driver, "password", "YbYzepe ");
	log.info("Login - Step 04 : Click to Login button ");
	homePage = loginPage.clickToLoginButton();

	homePage.openMultiplePage(driver, "Edit Customer");
	editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
	editCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "cusid", "35171");
	editCustomerPage.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
	closeBrowserAndDriver(driver);
    }

    @Test
    public void EditCustomer_01_VerifyAddressFeild_CannotBeEmpty() {
	log.info("ValidateAddressTextArea_CannotBeEmpty - Step 01 : Send empty value to Address textarea");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "addr", "");

	log.info(
		"ValidateAddressTextArea_CannotBeEmpty - Step 02 : Verify error message Address Field must not be blank");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "Address"), "Address Field must not be blank");

    }

    @Test
    public void EditCustomer_02_VerifyAddressFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first blank space character to Address textarea");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "addr", " 12344");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message Address Field must not be blank");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "Address"), "First character can not have space");

    }

    @Test
    public void EditCustomer_03_VerifyCityField_CannotBeNumberic() {
	log.info("ValidateCityTextBox_CannotBeNumberic - Step 01 : Send numberic value to City textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "city", "123");

	log.info("ValidateCityTextBox_CannotBeNumberic - Step 02 : Verify error message Numbers are not allowed");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "City"), "Numbers are not allowed");

    }

    @Test
    public void EditCustomer_04_VerifyCityField_CannotSpecialCharacters() {
	log.info("CannotSpecialCharacters - Step 01 : Send special characters to City textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "city", "@!@##");

	log.info("CannotSpecialCharacters - Step 02 : Verify error message Special characters are not allowed");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "City"), "Special characters are not allowed");

    }

    @Test
    public void EditCustomer_05_VerifyCityFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first characters blank space to City textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "city", " HaNoi");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message First character can not have space");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "City"), "First character can not have space");

    }

    @Test
    public void EditCustomer_06_VerifyStateFeild_CannotBeEmpty() {
	log.info("ValidateStateTextBox_CannotBeEmpty - Step 01 : Send empty value to City textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "state", "");

	log.info("ValidateStateTextBox_CannotBeEmpty - Step 02 : Verify error message State must not be blank");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "State"), "State must not be blank");

    }

    @Test
    public void EditCustomer_07_VerifyStateField_CannotBeNumberic() {
	log.info("ValidateStateTextBox_CannotBeNumberic - Step 01 : Send Numberic value to State textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "state", "123");

	log.info("ValidateStateTextBox_CannotBeNumberic - Step 02 : Verify error message Numbers are not allowed");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "State"), "Numbers are not allowed");

    }

    @Test
    public void EditCustomer_08_VerifyStateField_CannotSpecialCharacters() {
	log.info("CannotSpecialCharacters - Step 01 : Send special characters to State textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "state", "@!@##");

	log.info("CannotSpecialCharacters - Step 02 : Verify error message Special characters are not allowed");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "State"), "Special characters are not allowed");

    }

    @Test
    public void EditCustomer_09_VerifyStateFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first characters blank space to State textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "state", " HaNoi");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message First character can not have space");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "State"), "First character can not have space");

    }

    @Test
    public void EditCustomer_10_VerfiyPinFeild_CannotBeEmpty() {
	log.info("CannotBeEmpty - Step 01 : Send empty value to City textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", "");

	log.info("CannotBeEmpty - Step 02 : Verify error message PIN Code must not be blank");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "PIN"), "PIN Code must not be blank");

    }

    @Test
    public void EditCustomer_11_VerifyPinField_MustBe6digits() {
	log.info("MustBe6digits - Step 01 : Send <6 characters value to State textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", "123");

	log.info("MustBe6digits - Step 02 : Verify error message PIN Code must have 6 Digits");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "PIN"), "PIN Code must have 6 Digits");

    }

    @Test
    public void EditCustomer_12_VerifyPinField_CannotSpecialCharacters() {
	log.info("CannotSpecialCharacters - Step 01 : Send special characters to Pin textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", "@!@##");

	log.info("CannotSpecialCharacters - Step 02 : Verify error message Special characters are not allowed");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "PIN"), "Special characters are not allowed");

    }

    @Test
    public void EditCustomer_13_VerifyPinFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first characters blank space to Pin textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", " HaNoi");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message First character can not have space");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "PIN"), "First character can not have space");

    }

    @Test
    public void EditCustomer_14_VerifyPinField_CannotSpecialCharacters() {
	log.info("CannotSpecialCharacters - Step 01 : Send special characters to State textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", "@!@##");

	log.info("CannotSpecialCharacters - Step 02 : Verify error message Special characters are not allowed");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "PIN"), "Special characters are not allowed");

    }

    @Test
    public void EditCustomer_15_VerifyStateFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first characters blank space to State textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", " HaNoi");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message First character can not have space");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "PIN"), "First character can not have space");

    }

    @Test
    public void EditCustomer_16_VerifyStateField_CannotCharacters() {
	log.info("CannotCharacters - Step 01 : Send characters to Pin textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "pinno", "PIN");

	log.info("CannotCharacters - Step 02 : Verify error message Characters are not allowed");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "PIN"), "Characters are not allowed");

    }

    @Test
    public void EditCustomer_17_VerifyTelephoneFeild_CannotBeEmpty() {
	log.info("CannotBeEmpty - Step 01 : Send empty name to Mobile textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "telephoneno", "");

	log.info("CannotBeEmpty - Step 02 : Verify error message name must not be blank");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "Mobile Number"), "Mobile no must not be blank");
    }

    @Test
    public void EditCustomer_18_VerifyTelephoneFeild_BlankSpaceMobile() {
	log.info("BlankSpaceMobile - Step 01 : Send numberic to customer name textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "telephoneno", "123 423");

	log.info("BlankSpaceMobile - Step 02 : Verify error message Characters are not allowed");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "Mobile Number"), "Characters are not allowed");

    }

    @Test
    public void EditCustomer_19_VerifyTelephoneFeild_CannotSpecialCharacters() {
	log.info("CannotSpecialCharacters - Step 01 : Send special characters to Mobile textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "telephoneno", "!@#");

	log.info("CannotSpecialCharacters - Step 02 : Verify error message Special characters are not allowed");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "Mobile Number"),
		"Special characters are not allowed");

    }

    @Test
    public void EditCustomer_20_VerifyTelephoneFeild_FirstCharacterBlankSpace() {
	log.info("FirstCharacterBlankSpace - Step 01 : Send first characters have space to Mobile textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "telephoneno", " 123");

	log.info("FirstCharacterBlankSpace - Step 02 : Verify error message First character can not have space");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "Mobile Number"),
		"First character can not have space");

    }

    @Test
    public void EditCustomer_21_VerifyEmailFeild_CannotBeEmpty() {
	log.info("CannotBeEmpty - Step 01 : Send empty name to Email textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "emailid", "");

	log.info("CannotBeEmpty - Step 02 : Verify error message Email-ID must not be blank");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "E-mail"), "Email-ID must not be blank");
    }

    @Test
    public void EditCustomer_22_VerifyEmailFeild_IncorrectFormat() {
	log.info("IncorrectFormat - Step 01 : Send incorrect Email textbox");
	editCustomerPage.sendKeyToDynamicTextboxTextAreaVerify(driver, "emailid", "phanthithu@");

	log.info("IncorrectFormat - Step 02 : Verify error message Email-ID is not valid");
	verifyEquals(editCustomerPage.getDynamicErrorMessage(driver, "E-mail"), "Email-ID is not valid");
    }

}
