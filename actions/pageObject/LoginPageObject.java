package pageObject;

import org.openqa.selenium.WebDriver;

import bankguru.AbstractPageUI;
import bankguru.LoginPageUI;
import commons.AbstractPage;
import commons.PageFactoryManage;

public class LoginPageObject extends AbstractPage {
    private WebDriver driver;

    public LoginPageObject(WebDriver mappingDriver) {
	this.driver = mappingDriver;
    }

    public boolean isLoginFormDisplayed() {
	waitForElementVisible(driver, LoginPageUI.LOGIN_FORM);
	return isControlDisplayed(driver, LoginPageUI.LOGIN_FORM);

    }

    public String getLoginPageUrl() {
	return getCurrentUrl(driver);
    }

    public RegisterPageObject clickToHereLink() {
	waitForElementVisible(driver, LoginPageUI.HERE_LINK);
	clickToElement(driver, LoginPageUI.HERE_LINK);
	return PageFactoryManage.getRegisterPage(driver);

    }

    public HomePageObject clickToLoginButton() {
	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX,"btnLogin");
	clickToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX,"btnLogin");
	return PageFactoryManage.getHomePage(driver);

    }

}
