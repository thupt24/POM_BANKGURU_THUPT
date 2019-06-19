package pageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class EditCustomerPageObject extends AbstractPage {
    public EditCustomerPageObject(WebDriver driver) {
	this.driver = driver;
    }

    private WebDriver driver;

}
