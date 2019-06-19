package pageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class EditAccountPageObject extends AbstractPage {
    public EditAccountPageObject(WebDriver driver) {
	this.driver = driver;
    }

    private WebDriver driver;

}
