package commons;

import org.openqa.selenium.WebDriver;

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

public class PageFactoryManage {

    public static LoginPageObject getLoginPage(WebDriver driver) {

	return new LoginPageObject(driver);
    }

    public static HomePageObject getHomePage(WebDriver driver) {

	return new HomePageObject(driver);
    }

    public static RegisterPageObject getRegisterPage(WebDriver driver) {

	return new RegisterPageObject(driver);
    }

    public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
	return new NewCustomerPageObject(driver);
    }

    public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
	return new EditCustomerPageObject(driver);
    }

    public static NewAccoutPageObject getNewAccountPage(WebDriver driver) {
	return new NewAccoutPageObject(driver);
    }

    public static EditAccountPageObject getEditAccountPage(WebDriver driver) {
	return new EditAccountPageObject(driver);
    }

    public static TranferMoneyPageObject getTranferMoneyPage(WebDriver driver) {
	return new TranferMoneyPageObject(driver);
    }

    public static WithdrawalPageObject getWithdrawalPage(WebDriver driver) {
	return new WithdrawalPageObject(driver);
    }
    
    public static FundTranferPageObject getFundTranferPage(WebDriver driver) {
	return new FundTranferPageObject(driver);
    }
    public static BalanceEnquiryPageObject getBalanceEnquiryPage(WebDriver driver) {
	return new BalanceEnquiryPageObject(driver);
    }
    public static DeleteAccountPageObject getDeleteAccountPage(WebDriver driver) {
	return new DeleteAccountPageObject(driver);
    }
    public static DeleteCustomerAccountObject getDeleteCustomerAccount(WebDriver driver) {
	return new DeleteCustomerAccountObject(driver);
    }

}
