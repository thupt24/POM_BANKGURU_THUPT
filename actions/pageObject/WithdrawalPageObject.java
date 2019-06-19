package pageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class WithdrawalPageObject extends AbstractPage {
    public WithdrawalPageObject(WebDriver driver) {
	this.driver = driver;
    }

    private WebDriver driver;

}
