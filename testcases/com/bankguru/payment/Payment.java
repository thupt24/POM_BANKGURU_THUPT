package com.bankguru.payment;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.account.CustomerDataJson;
import com.bankguru.account.EditCustomerData;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import bankguru.AbstractPageUI;
import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObject.BalanceEnquiryPageObject;
import pageObject.DeleteAccountPageObject;
import pageObject.DeleteCustomerAccountObject;
import pageObject.EditAccountPageObject;
import pageObject.EditCustomerPageObject;
import pageObject.FundTranferPageObject;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.NewAccoutPageObject;
import pageObject.NewCustomerPageObject;
import pageObject.RegisterPageObject;
import pageObject.TranferMoneyPageObject;
import pageObject.WithdrawalPageObject;

public class Payment extends AbstractTest {
    private WebDriver driver;
    private CustomerDataJson customerData;
    private EditCustomerData editCustomerData;
    String loginPageUrl, email;
    String accountID;
    LoginPageObject loginPage;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    NewCustomerPageObject newCustomerPage;
    EditCustomerPageObject editCustomerPage;
    NewAccoutPageObject newAccountPage;
    EditAccountPageObject editAccountPage;
    TranferMoneyPageObject depositPage;
    WithdrawalPageObject withDrawalPage;
    FundTranferPageObject fundTranferPage;
    BalanceEnquiryPageObject balanceEnquiryPage;
    DeleteAccountPageObject deleteAccountPage;
    DeleteCustomerAccountObject deleteCustomer;

    String editEmail = "editautomation" + randomNumber() + "@gmail.com";
    String currentDay = getCurrentDay();

    public static String UserID;

    @Parameters({ "brower", "customer", "editCustomer" })

    @BeforeClass
    public void beforeClass(String browserName, String customerDataPath, String edtiCustomerPath)
	    throws JsonParseException, JsonMappingException, IOException {
	driver = openMultiBrowser(browserName);
	email = "Alisa" + randomNumber() + "@gmail.com";
	loginPage = PageFactoryManage.getLoginPage(driver);
	customerData = CustomerDataJson.get(customerDataPath);
	editCustomerData = EditCustomerData.get(edtiCustomerPath);
	log.info("Login - Step 02 : Verify Login Form displayed ");
	verifyTrue(loginPage.isLoginFormDisplayed());

	log.info("Login - Step 03 : Input username and password ");
	System.out.println("userID" + RegisterPage.USER_ID_INFO);
	System.out.println("pass" + RegisterPage.PASS_WORD_INFO);
	loginPage.sendKeyToDynamicTextboxTextArea(driver, "uid", RegisterPage.USER_ID_INFO);
	loginPage.sendKeyToDynamicTextboxTextArea(driver, "password", RegisterPage.PASS_WORD_INFO);

	log.info("Login - Step 04 : Click to Login button ");
	homePage = loginPage.clickToLoginButton();

	log.info("Login - Step 05 : Verify Welcome message displayed");
	verifyTrue(homePage.isWelcomeMessageDisplayed());

	log.info("Login - Step 06 : Verify MangerID displayed ");
	verifyTrue(homePage.isMangerIdDisplayed(RegisterPage.USER_ID_INFO));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
	closeBrowserAndDriver(driver);
    }

    @Test
    public void Payment_01_NewCustomer() {
	log.info("NewCustomer- Step 01 : Open New Customer link ");
	homePage.openMultiplePage(driver, "New Customer");
	newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);

	log.info("NewCustomer- Step 02 : Verify New Customer form displayed ");
	verifyTrue(newCustomerPage.isPageTitleDisplayed(driver, "Add New Customer"));

	log.info("NewCustomer- Step 03 : input Data to form created customer ");
	newCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "name", customerData.getCustomerName());
	newCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "dob", customerData.getDateOfBirth());
	newCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "addr", customerData.getAddress());
	newCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "city", customerData.getCity());
	newCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "state", customerData.getState());
	newCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "pinno", customerData.getPin());
	newCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "telephoneno", customerData.getNumber());
	newCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "emailid", email);
	newCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "password", customerData.getPassWord());
	newCustomerPage.clickToDynamicButtonTextboxTextArea(driver, "sub");

	log.info("NewCustomer- Step 04 : Verify CustomerRegister succsesfully ");
	verifyTrue(newCustomerPage.isPageTitleDisplayed(driver, "Customer Registered Successfully!!!"));

	log.info("NewCustomer - Step 5 : get UserID of newCustomer");
	UserID = newCustomerPage.getDynamicDataTable(driver, "Customer ID");

	log.info("NewCustomer- Step 06 : Verify Data in table");
	verifyEquals(newCustomerPage.getDynamicDataTable(driver, "Customer Name"), customerData.getCustomerName());
	verifyEquals(newCustomerPage.getDynamicDataTable(driver, "Gender"), customerData.getGender());
	verifyEquals(newCustomerPage.getDynamicDataTable(driver, "Birthdate"), customerData.getDateOfBirth());
	verifyEquals(newCustomerPage.getDynamicDataTable(driver, "Address"), customerData.getAddress());
	verifyEquals(newCustomerPage.getDynamicDataTable(driver, "City"), customerData.getCity());
	verifyEquals(newCustomerPage.getDynamicDataTable(driver, "State"), customerData.getState());
	verifyEquals(newCustomerPage.getDynamicDataTable(driver, "Pin"), customerData.getPin());
	verifyEquals(newCustomerPage.getDynamicDataTable(driver, "Mobile No."), customerData.getNumber());
	verifyEquals(newCustomerPage.getDynamicDataTable(driver, "Email"), email);

    }

    @Test
    public void Payment_02_EditCustomer() {
	newCustomerPage.openMultiplePage(driver, "Edit Customer");
	editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);

	log.info("EditCustomer - Step 01 : Verify EditForm displayed");
	verifyTrue(editCustomerPage.isPageTitleDisplayed(driver, "Edit Customer Form"));

	log.info("EditCustomer - Step 02 : input data to Customer ID textbox and click Submit button");
	editCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "cusid", UserID);
	editCustomerPage.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");

	log.info("EditCustomer - Step 03 : Verify EditCustomer title displayed");
	verifyTrue(editCustomerPage.isPageTitleDisplayed(driver, "Edit Customer"));

	log.info("EditCustomer - Step 04 : Verify data before edit");
	verifyEquals(editCustomerPage.getDynamicTextValueInTextBox(driver, "name"), customerData.getCustomerName());
	verifyEquals(editCustomerPage.getDynamicTextValueInTextBox(driver, "gender"), customerData.getGender());
	verifyEquals(editCustomerPage.getDynamicTextValueInTextBox(driver, "dob"), customerData.getDateOfBirth());
	verifyEquals(editCustomerPage.getDynamicTextValueInTextArea(driver, "addr"), customerData.getAddress());
	verifyEquals(editCustomerPage.getDynamicTextValueInTextBox(driver, "city"), customerData.getCity());
	verifyEquals(editCustomerPage.getDynamicTextValueInTextBox(driver, "state"), customerData.getState());
	verifyEquals(editCustomerPage.getDynamicTextValueInTextBox(driver, "pinno"), customerData.getPin());
	verifyEquals(editCustomerPage.getDynamicTextValueInTextBox(driver, "telephoneno"), customerData.getNumber());
	verifyEquals(editCustomerPage.getDynamicTextValueInTextBox(driver, "emailid"), email);

	log.info("EditCustomer - Step 05 : Edit Customer");
	editCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "addr", editCustomerData.getAddress());
	editCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "city", editCustomerData.getCity());
	editCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "state", editCustomerData.getState());
	editCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "pinno", editCustomerData.getPin());
	editCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "telephoneno", editCustomerData.getNumber());
	editCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "emailid", editEmail);
	editCustomerPage.clickToDynamicButtonTextboxTextArea(driver, "sub");

	log.info("EditCustomer - Step 06 : Verify title displayed");
	verifyTrue(editCustomerPage.isPageTitleDisplayed(driver, "Customer details updated Successfully!!!"));

	log.info("EditCustomer - Step 07 : Verify data after edit");
	verifyEquals(editCustomerPage.getDynamicDataTable(driver, "Customer ID"), UserID);
	verifyEquals(editCustomerPage.getDynamicDataTable(driver, "Customer Name"), customerData.getCustomerName());
	verifyEquals(editCustomerPage.getDynamicDataTable(driver, "Gender"), customerData.getGender());
	verifyEquals(editCustomerPage.getDynamicDataTable(driver, "Birthdate"), customerData.getDateOfBirth());
	verifyEquals(editCustomerPage.getDynamicDataTable(driver, "Address"), editCustomerData.getAddress());
	verifyEquals(editCustomerPage.getDynamicDataTable(driver, "City"), editCustomerData.getCity());
	verifyEquals(editCustomerPage.getDynamicDataTable(driver, "State"), editCustomerData.getState());
	verifyEquals(editCustomerPage.getDynamicDataTable(driver, "Pin"), editCustomerData.getPin());
	verifyEquals(editCustomerPage.getDynamicDataTable(driver, "Mobile No."), editCustomerData.getNumber());
	verifyEquals(editCustomerPage.getDynamicDataTable(driver, "Email"), editEmail);

    }

    @Test
    public void Payment_03_NewAccount() {
	editCustomerPage.openMultiplePage(driver, "New Account");
	newAccountPage = PageFactoryManage.getNewAccountPage(driver);

	log.info("NewAccount - Step 01 : Verify NewAccount form displayed");
	verifyTrue(newAccountPage.isPageTitleDisplayed(driver, "Add new account form"));

	log.info("NewAccount - Step 02 : Input data to form");
	newAccountPage.sendKeyToDynamicTextboxTextArea(driver, "cusid", UserID);
	newAccountPage.selectItemInDropdown(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST, "Current", "selaccount");
	newAccountPage.sendKeyToDynamicTextboxTextArea(driver, "inideposit", "50000000");
	newAccountPage.clickToDynamicButtonTextboxTextArea(driver, "button2");

	log.info("NewAccount - Step 03 : Get Acoount ID");
	accountID = newAccountPage.getDynamicDataTable(driver, "Account ID");

	log.info("NewAccount - Step 04 : Verify message title successfully ");
	verifyTrue(newAccountPage.isPageTitleDisplayed(driver, "Account Generated Successfully!!!"));

	log.info("NewAccount - Step 07 : Verify data after creat new account");
	verifyEquals(newAccountPage.getDynamicDataTable(driver, "Customer ID"), UserID);
	verifyEquals(newAccountPage.getDynamicDataTable(driver, "Customer Name"), customerData.getCustomerName());
	verifyEquals(newAccountPage.getDynamicDataTable(driver, "Email"), editEmail);
	verifyEquals(newAccountPage.getDynamicDataTable(driver, "Account Type"), "Current");
	verifyEquals(newAccountPage.getDynamicDataTable(driver, "Date of Opening"), currentDay);
	verifyEquals(newAccountPage.getDynamicDataTable(driver, "Current Amount"), "50000000");

    }

    @Test
    public void Payment_04_EditAccount() {
	newAccountPage.openMultiplePage(driver, "Edit Account");
	editAccountPage = PageFactoryManage.getEditAccountPage(driver);

	log.info("EditAccount - Step 01 : Verify EditAccount form displayed");
	verifyTrue(editAccountPage.isPageTitleDisplayed(driver, "Edit Account Form"));

	log.info("EditAccount - Step 02 : input accountID and click submit button");
	editAccountPage.sendKeyToDynamicTextboxTextArea(driver, "accountno", accountID);
	editAccountPage.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");

	log.info("EditAccount - Step 03 : Verify message title successfully");
	verifyTrue(editAccountPage.isPageTitleDisplayed(driver, "Edit Account Entry Form"));

	log.info("EditAccount - Step 04 : Verify data before edit");
	verifyEquals(editCustomerPage.getDynamicTextValueInTextBox(driver, "txtcid"), UserID);
	verifyEquals(editCustomerPage.getDynamicTextValueInTextBox(driver, "txtinitdep"), "50000000");

	log.info("EditAccount - Step 05 : Edit account and click submit button");
	editAccountPage.selectItemInDropdown(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST, "Savings", "a_type");
	editAccountPage.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");

	log.info("EditAccount - Step 06 : Verify Account details updated Successfully!!!");
	verifyTrue(editAccountPage.isPageTitleDisplayed(driver, "Account details updated Successfully!!!"));

	log.info("EditAccount - Step 07 : Verify data after edit account");
	verifyEquals(editAccountPage.getDynamicDataTable(driver, "Account ID"), accountID);
	verifyEquals(editAccountPage.getDynamicDataTable(driver, "Customer ID"), UserID);
	verifyEquals(editAccountPage.getDynamicDataTable(driver, "Customer Name"), customerData.getCustomerName());
	verifyEquals(editAccountPage.getDynamicDataTable(driver, "Email"), editEmail);
	verifyEquals(editAccountPage.getDynamicDataTable(driver, "Account Type"), "Savings");
	verifyEquals(editAccountPage.getDynamicDataTable(driver, "Date of Opening"), currentDay);
	verifyEquals(editAccountPage.getDynamicDataTable(driver, "Current Amount"), "50000000");

    }

    @Test
    public void Payment_05_TranferMoneyToCurrentAccount() {
	editAccountPage.openMultiplePage(driver, "Deposit");
	depositPage = PageFactoryManage.getTranferMoneyPage(driver);

	log.info("Deposit - Step 01 : Verify Deposit form displayed");
	verifyTrue(depositPage.isPageTitleDisplayed(driver, "Amount Deposit Form"));

	log.info("Deposit - Step 02 : Input data to Deposit form");
	depositPage.sendKeyToDynamicTextboxTextArea(driver, "accountno", accountID);
	depositPage.sendKeyToDynamicTextboxTextArea(driver, "ammount", "50000");
	depositPage.sendKeyToDynamicTextboxTextArea(driver, "desc", "alise");
	depositPage.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");

	log.info("Deposit - Step 03: Verify Transaction details of Deposit for Account ");
	verifyTrue(depositPage.isPageTitleDisplayed(driver, "Transaction details of Deposit for Account " + accountID));

	log.info("Deposit - Step 04: Verify data after deposit");
	verifyEquals(depositPage.getDynamicDataTable(driver, "Account No"), accountID);
	verifyEquals(depositPage.getDynamicDataTable(driver, "Amount Credited"), "50000");
	verifyEquals(depositPage.getDynamicDataTable(driver, "Type of Transaction"), "Deposit");
	verifyEquals(depositPage.getDynamicDataTable(driver, "Description"), "alise");
	verifyEquals(depositPage.getDynamicDataTable(driver, "Current Balance"), "50050000");
    }

    @Test
    public void Payment_06_Withdrawal() {
	depositPage.openMultiplePage(driver, "Withdrawal");
	withDrawalPage = PageFactoryManage.getWithdrawalPage(driver);

	log.info("withDrawalPage - Step 01 : Verify Amount Withdrawal Form displayed");
	verifyTrue(withDrawalPage.isPageTitleDisplayed(driver, "Amount Withdrawal Form"));

	log.info("withDrawalPage - Step 02 : Input data to Amount Withdrawal Form");
	withDrawalPage.sendKeyToDynamicTextboxTextArea(driver, "accountno", accountID);
	withDrawalPage.sendKeyToDynamicTextboxTextArea(driver, "ammount", "50000");
	withDrawalPage.sendKeyToDynamicTextboxTextArea(driver, "desc", "withDra");
	withDrawalPage.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");

	log.info("withDrawalPage - Step 03 : Verify Transaction details of Withdrawal for Account displayed");
	verifyTrue(withDrawalPage.isPageTitleDisplayed(driver,
		"Transaction details of Withdrawal for Account " + accountID));

	log.info("withDrawalPage - Step 04 : Verify data transaction details of Withdrawal for Account ");
	verifyEquals(depositPage.getDynamicDataTable(driver, "Account No"), accountID);
	verifyEquals(depositPage.getDynamicDataTable(driver, "Amount Debited"), "50000");
	verifyEquals(depositPage.getDynamicDataTable(driver, "Type of Transaction"), "Withdrawal");
	verifyEquals(depositPage.getDynamicDataTable(driver, "Description"), "withDra");
	verifyEquals(depositPage.getDynamicDataTable(driver, "Current Balance"), "50000000");
    }

    @Test
    public void Payment_07_FundTranfer_Into_Another_Account() {
	withDrawalPage.openMultiplePage(driver, "Fund Transfer");
	fundTranferPage = PageFactoryManage.getFundTranferPage(driver);

	log.info("fundTranferPage - Step 01 : Verify Fund transfer Form displayed");
	verifyTrue(fundTranferPage.isPageTitleDisplayed(driver, "Fund transfer"));

	log.info("fundTranferPage - Step 02 : Input data to Fund transfer Form and submit");
	fundTranferPage.sendKeyToDynamicTextboxTextArea(driver, "payersaccount", accountID);
	fundTranferPage.sendKeyToDynamicTextboxTextArea(driver, "payeeaccount", "62476");
	fundTranferPage.sendKeyToDynamicTextboxTextArea(driver, "ammount", "10000000");
	fundTranferPage.sendKeyToDynamicTextboxTextArea(driver, "desc", "fundtran");
	fundTranferPage.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");

    }

    @Test
    public void Payment_08_CheckCurrentAccountBalance() {
	fundTranferPage.openMultiplePage(driver, "Balance Enquiry");
	balanceEnquiryPage = PageFactoryManage.getBalanceEnquiryPage(driver);

	log.info("balanceEnquiryPage - Step 01 : Verify Balance Enquiry Form displayed");
	verifyTrue(fundTranferPage.isPageTitleDisplayed(driver, "Balance Enquiry Form"));

	log.info("balanceEnquiryPage - Step 02: input AccountID and submit");
	balanceEnquiryPage.sendKeyToDynamicTextboxTextArea(driver, "accountno", accountID);
	balanceEnquiryPage.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");

	log.info("balanceEnquiryPage - Step 03: Verify Balance Details for Account displayed");
	verifyTrue(balanceEnquiryPage.isPageTitleDisplayed(driver, "Balance Details for Account " + accountID));
	verifyEquals(depositPage.getDynamicDataTable(driver, "Account No"), accountID);
	verifyEquals(depositPage.getDynamicDataTable(driver, "Type of Account"), "Savings");
	verifyEquals(depositPage.getDynamicDataTable(driver, "Balance"), "40000000");

    }

    @Test
    public void Payment_09_DeleteCurrentAccountAndVeriy() {
	balanceEnquiryPage.openMultiplePage(driver, "Delete Account");
	deleteAccountPage = PageFactoryManage.getDeleteAccountPage(driver);

	log.info("deleteAccountPage - Step 01 : Verify Delete Account Form displayed");
	verifyTrue(deleteAccountPage.isPageTitleDisplayed(driver, "Delete Account Form"));

	log.info("deleteAccountPage - Step 02: input AccountID and submit");
	deleteAccountPage.sendKeyToDynamicTextboxTextArea(driver, "accountno", accountID);
	deleteAccountPage.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");

	log.info("deleteAccountPage - Step 03: Close Alert accept delete Account");
	verifyEquals(deleteAccountPage.getTextAlert(driver), "Do you really want to delete this Account?");
	deleteAccountPage.acceptAlert(driver);
	verifyEquals(deleteAccountPage.getTextAlert(driver), "Account Deleted Sucessfully");
	deleteAccountPage.acceptAlert(driver);

	log.info("deleteAccountPage - Step 04: Check Account really not exit");
	homePage.openMultiplePage(driver, "Edit Account");
	editAccountPage = PageFactoryManage.getEditAccountPage(driver);
	editAccountPage.sendKeyToDynamicTextboxTextArea(driver, "accountno", accountID);
	editAccountPage.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");
	verifyEquals(editAccountPage.getTextAlert(driver), "Account does not exist");
	editAccountPage.acceptAlert(driver);

    }

    @Test
    public void Payment_10_DeleteCurrentCustomerIDAndVerify() {
	deleteAccountPage.openMultiplePage(driver, "Delete Customer");
	deleteCustomer = PageFactoryManage.getDeleteCustomerAccount(driver);

	log.info("deleteCustomer - Step 01 : Verify Delete Customer Form displayed");
	verifyTrue(deleteCustomer.isPageTitleDisplayed(driver, "Delete Customer Form"));

	log.info("deleteCustomer - Step 02: input Customer ID and submit");
	deleteCustomer.sendKeyToDynamicTextboxTextArea(driver, "cusid", UserID);
	deleteCustomer.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");

	log.info("deleteCustomer - Step 03: Close Alert accept delete Customer");
	verifyEquals(deleteCustomer.getTextAlert(driver), "Do you really want to delete this Customer?");
	deleteCustomer.acceptAlert(driver);
	verifyEquals(deleteCustomer.getTextAlert(driver), "Customer deleted Successfully");
	deleteCustomer.acceptAlert(driver);

	log.info("deleteCustomer - Step 04: Check account really not exit");
	homePage.openMultiplePage(driver, "Edit Customer");
	editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
	editCustomerPage.sendKeyToDynamicTextboxTextArea(driver, "cusid", UserID);
	editCustomerPage.clickToDynamicButtonTextboxTextArea(driver, "AccSubmit");
	verifyEquals(editCustomerPage.getTextAlert(driver), "Customer does not exist!!");
	editCustomerPage.acceptAlert(driver);

    }
}
