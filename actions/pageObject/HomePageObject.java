package pageObject;

import org.openqa.selenium.WebDriver;

import bankguru.HomePageUI;
import bankguru.LoginPageUI;
import commons.AbstractPage;
import commons.PageFactoryManage;

public class HomePageObject extends AbstractPage {
    public HomePageObject(WebDriver mappingDriver) {
	this.driver = mappingDriver;
    }

    private WebDriver driver;

    public boolean isWelcomeMessageDisplayed() {
	waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
	return isControlDisplayed(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
    }

    public boolean isMangerIdDisplayed(String userIdInfo) {
	String USER_ID_FORMAT = String.format(HomePageUI.USER_ID_TEXT, userIdInfo);
	waitForElementVisible(driver, USER_ID_FORMAT);
	return isControlDisplayed(driver, USER_ID_FORMAT);
    }

    public LoginPageObject clickToLogoutLink() {
	waitForElementVisible(driver, HomePageUI.LOG_OUT_LINK);
	clickToElement(driver, HomePageUI.LOG_OUT_LINK);
	waitForAlertPresence(driver);
	acceptAlert(driver);
	return PageFactoryManage.getLoginPage(driver);
    }
    public boolean isLoginFormUndisplayed() {
	waitForElementVisible(driver, LoginPageUI.LOGIN_FORM);
	return isControlUndisplayed(driver, LoginPageUI.LOGIN_FORM);
	
    }
}
