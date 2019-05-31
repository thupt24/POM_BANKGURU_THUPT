package pageObject;

import org.openqa.selenium.WebDriver;

import bankguru.RegisterPageUI;
import commons.AbstractPage;

public class RegisterPageObject extends AbstractPage {
	private WebDriver driver;
	public RegisterPageObject(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}
	
	public boolean isRegisterPageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
		return isControlDisplayed(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
	}

	public void sendKeyToEmailTexBox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_ID_TEXTBOX, email);
		
	}

	public void clickToLoginButton() {
		waitForElementVisible(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserIdInfo() {
		waitForElementVisible(driver, RegisterPageUI.USER_ID_TEXT);
		return getText(driver, RegisterPageUI.USER_ID_TEXT);
	}

	public void openLoginPage(String loginPageUrl) {
		openAnyUrl(driver, loginPageUrl);
		
	}

	public String getPassWordInfo() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXT);
		return getText(driver, RegisterPageUI.PASSWORD_TEXT);
	}
}
