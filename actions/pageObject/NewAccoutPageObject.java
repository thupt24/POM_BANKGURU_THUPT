package pageObject;

import org.openqa.selenium.WebDriver;

import bankguru.NewAccountPageUI;
import commons.AbstractPage;

public class NewAccoutPageObject extends AbstractPage {
    public NewAccoutPageObject(WebDriver mappingDriver) {
	this.driver = mappingDriver;
    }

    private WebDriver driver;

    public void selectAccountType(String value) {
	waitForElementVisible(driver, NewAccountPageUI.ACCOUNT_TYPE_PARENT);
	selectDropdownList(driver, NewAccountPageUI.ACCOUNT_TYPE_PARENT, NewAccountPageUI.ACCOUNT_TYPE_CHILD,value);
    }
}
