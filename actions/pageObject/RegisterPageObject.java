package pageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;

public class RegisterPageObject extends AbstractPage {
    private WebDriver driver;

    public RegisterPageObject(WebDriver mappingDriver) {
	this.driver = mappingDriver;
    }

    public LoginPageObject openLoginPage(String loginPageUrl) {
	openAnyUrl(driver, loginPageUrl);
	return PageFactoryManage.getLoginPage(driver);

    }

}
