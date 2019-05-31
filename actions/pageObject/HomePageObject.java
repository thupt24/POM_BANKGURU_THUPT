package pageObject;

import org.openqa.selenium.WebDriver;

import bankguru.HomePageUI;
import commons.AbstractPage;

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
		String USER_ID_FORMAT = String.format(HomePageUI.USER_ID_TEXT,userIdInfo);
		System.out.println(" Value of USER_ID_FORMAT = "+ USER_ID_FORMAT);
		waitForElementVisible(driver, USER_ID_FORMAT);
		return isControlDisplayed(driver, USER_ID_FORMAT);
	}

}
