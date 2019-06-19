package commons;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import bankguru.AbstractPageUI;

public class AbstractPage {
    WebElement element;
    WebDriverWait waitExplicit;
    List<WebElement> elements;
    Actions action;

    public void openAnyUrl(WebDriver driver, String url) {
	driver.get(url);
    }

    public String getCurrentUrl(WebDriver driver) {
	return driver.getCurrentUrl();
    }

    public String getPageTitle(WebDriver driver) {
	return driver.getTitle();
    }

    public String getPageSoureCode(WebDriver driver) {
	return driver.getPageSource();
    }

    public void backToPreviousPage(WebDriver driver) {
	driver.navigate().back();
    }

    public void refreshCurrentPage(WebDriver driver) {
	driver.navigate().refresh();
    }

    public void forwardToNextPage(WebDriver driver) {
	driver.navigate().forward();
    }

    public void acceptAlert(WebDriver driver) {
	driver.switchTo().alert().accept();
    }

    public void cancelAlert(WebDriver driver) {
	driver.switchTo().alert().dismiss();
    }

    public String getTextAlert(WebDriver driver) {
	return driver.switchTo().alert().getText();
    }

    public void sendKeyToAlert(WebDriver driver, String value) {
	driver.switchTo().alert().sendKeys(value);
    }

    public void clickToElement(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	element.click();
    }

    public void clickToElement(WebDriver driver, String locator, String... values) {
	locator = String.format(locator, (Object[]) values);
	highlightElement(driver, locator);
	element = driver.findElement(By.xpath(locator));
	element.click();
    }

    public void submitToElement(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	element.submit();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String value) {
	highlightElement(driver, locator);
	element = driver.findElement(By.xpath(locator));
	element.clear();
	element.sendKeys(value);
    }

    public void sendKeyToElement(WebDriver driver, String locator, String valueToSendKey, String... values) {
	locator = String.format(locator, (Object[]) values);
	highlightElement(driver, locator);
	element = driver.findElement(By.xpath(locator));
	element.clear();
	element.sendKeys(valueToSendKey);
    }
    
    public void sendKeyToElementVerify(WebDriver driver, String locator, String valueToSendKey,String... values) {
   	locator = String.format(locator, (Object[]) values);
   	highlightElement(driver, locator);
   	element = driver.findElement(By.xpath(locator));
   	element.clear();
   	element.sendKeys(valueToSendKey);
   	element.sendKeys(Keys.TAB);
       }

    public void selectItemInDropdown(WebDriver driver, String locator, String value) {
	element = driver.findElement(By.xpath(locator));
	Select select = new Select(element);
	select.selectByVisibleText(value);
    }
    
    public void selectItemInDropdown(WebDriver driver, String locator, String value, String...values) {
	locator = String.format(locator, (Object[]) values);
	element = driver.findElement(By.xpath(locator));
	Select select = new Select(element);
	select.selectByVisibleText(value);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	Select select = new Select(element);
	return select.getFirstSelectedOption().getText();
    }

    public void getSelectedItemInDropdownCustomer(WebDriver driver, String parentLocator, String allItemLocator,
	    String[] ExpectedvalueItem) {
	WebElement parentDropdown = driver.findElement(By.xpath(parentLocator));
	waitExplicit = new WebDriverWait(driver, 30);
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", parentDropdown);
	waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemLocator)));

	List<WebElement> allItems = driver.findElements(By.xpath(allItemLocator));
	System.out.println(" Size of Element " + allItems.size());
	for (WebElement childElement : allItems) {
	    for (String item : ExpectedvalueItem) {
		if (childElement.getText().equals(item)) {
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", childElement);
		    try {
			Thread.sleep(1000);
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    if (childElement.isDisplayed()) {
			childElement.click();
		    } else {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", childElement);
		    }
		    try {
			Thread.sleep(1000);
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    break;
		}
	    }
	}

    }

    public void selectDropdownList(WebDriver driver, String xpath_root, String xpath_allItem,
	    String ExpectedvalueItem) {
	WebElement parentDropdown = driver.findElement(By.xpath(xpath_root));
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", parentDropdown);

	waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath_allItem)));

	List<WebElement> allItem = driver.findElements(By.xpath(xpath_allItem));
	System.out.println(" Size of Element " + allItem.size());
	for (WebElement check_Dropdown : allItem) {
	    System.out.print(" Value each item is \n " + check_Dropdown.getText());
	    if (check_Dropdown.getText().equals(ExpectedvalueItem)) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", check_Dropdown);
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		if (check_Dropdown.isDisplayed()) {
		    check_Dropdown.click();
		} else {
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", check_Dropdown);
		}
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		break;
	    }
	}
    }

    public String getAtributeValue(WebDriver driver, String locator, String atributeName) {
	element = driver.findElement(By.xpath(locator));
	return element.getAttribute(atributeName);
    }
    
    public String getAtributeValue(WebDriver driver, String locator, String atributeName, String...values) {
	locator = String.format(locator, (Object[]) values);
	element = driver.findElement(By.xpath(locator));
	return element.getAttribute(atributeName);
    }
    
    public String getTextElement(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	return element.getText();
    }

    public String getTextElement(WebDriver driver, String locator, String... values) {
	locator = String.format(locator, (Object[]) values);
	element = driver.findElement(By.xpath(locator));
	return element.getText();
    }

    public int countElementNumber(WebDriver driver, String locator) {
	elements = driver.findElements(By.xpath(locator));
	return elements.size();
    }

    public void checkToCheckbox(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	if (!element.isSelected()) {
	    element.click();
	}
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	if (element.isSelected()) {
	    element.click();
	}
    }

    public boolean isControlUndisplayed(WebDriver driver, String locator) {
	Date date = new Date();
	System.out.println("Start Time = " + date.toString());
	overideTimeout(driver, Constants.SHORT_TIME_OUT);
	List<WebElement> elements = driver.findElements(By.xpath(locator));
	if (elements.size() == 0) {
	    System.out.println("End Time = " + new Date().toString());
	    overideTimeout(driver, Constants.LONG_TIME_OUT);
	    return true;
	}
	if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
	    System.out.println("End Time = " + new Date().toString());
	    overideTimeout(driver, Constants.LONG_TIME_OUT);
	    return true;
	} else {
	    overideTimeout(driver, Constants.LONG_TIME_OUT);
	    return false;
	}

    }

    public boolean isControlUndisplayed(WebDriver driver, String locator, String... values) {
	Date date = new Date();
	System.out.println("Start Time = " + date.toString());
	overideTimeout(driver, Constants.SHORT_TIME_OUT);
	locator = String.format(locator, (Object[]) values);
	List<WebElement> elements = driver.findElements(By.xpath(locator));
	if (elements.size() == 0) {
	    System.out.println("End Time = " + new Date().toString());
	    overideTimeout(driver, Constants.LONG_TIME_OUT);
	    return true;
	}
	if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
	    System.out.println("End Time = " + new Date().toString());
	    overideTimeout(driver, Constants.LONG_TIME_OUT);
	    return true;
	} else {
	    overideTimeout(driver, Constants.LONG_TIME_OUT);
	    return false;
	}

    }

    public void overideTimeout(WebDriver driver, long timeout) {
	driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public boolean isControlDisplayed(WebDriver driver, String locator) {
	boolean status = true;
	try {
	    element = driver.findElement(By.xpath(locator));
	    if (element.isDisplayed()) {
		return status;
	    }
	} catch (Exception e) {
	    Reporter.log("============ Wait for element not visible================");
	    Reporter.log(e.getMessage());
	    System.err.println("\"============ Wait for element not visible================");
	    System.err.println(e.getMessage() + "\n");
	    status = false;
	}

	return status;
    }

    public boolean isControlDisplayed(WebDriver driver, String locator, String... values) {
	locator = String.format(locator, (Object[]) values);
	element = driver.findElement(By.xpath(locator));
	return element.isDisplayed();
    }

    public boolean isControlSelected(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	return element.isSelected();
    }

    public boolean isControlEnable(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	return element.isEnabled();
    }

    public void switchToChildWindowByID(WebDriver driver, String parent) {
	Set<String> allWindows = driver.getWindowHandles();
	for (String runWindow : allWindows) {
	    if (!runWindow.equals(parent)) {
		driver.switchTo().window(runWindow);
		break;
	    }
	}
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
	Set<String> allWindows = driver.getWindowHandles();
	for (String runWindow : allWindows) {
	    driver.switchTo().window(runWindow);
	    String currentWindowTile = driver.getTitle();
	    if (currentWindowTile.equals(expectedTitle)) {
		break;
	    }
	}
    }

    public boolean closeAllWindowWithoutParentWindow(WebDriver driver, String parentWindow) {
	Set<String> allWindows = driver.getWindowHandles();
	for (String runWindow : allWindows) {
	    if (!runWindow.equals(parentWindow)) {
		driver.switchTo().window(runWindow);
		driver.close();
	    }
	}
	driver.switchTo().window(parentWindow);
	if (driver.getWindowHandles().size() == 1) {
	    return true;
	} else
	    return false;
    }

    public void switchToIframe(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	driver.switchTo().frame(element);
    }

    public void backToParentNode(WebDriver driver) {
	driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	action = new Actions(driver);
	action.moveToElement(element).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	action = new Actions(driver);
	action.doubleClick(element).perform();
    }

    public void rightClick(WebDriver driver, String locator) {
	element = driver.findElement(By.xpath(locator));
	action = new Actions(driver);
	action.contextClick(element).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
	action = new Actions(driver);
	WebElement sourceElement = driver.findElement(By.xpath(sourceLocator));
	WebElement targetElement = driver.findElement(By.xpath(targetLocator));
	action.dragAndDrop(sourceElement, targetElement).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
	element = driver.findElement(By.xpath(locator));
	action = new Actions(driver);
	action.sendKeys(element, key);
    }

    public String executeForBrowser(WebDriver driver, String javaSript) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	return (String) js.executeScript(javaSript);
    }

    public Object clickToElementByJS(WebDriver driver, String locator) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	element = driver.findElement(By.xpath(locator));
	return js.executeScript("arguments[0].click();", element);
    }

    public Object sendkeyToElementByJS(WebDriver driver, String locator, String value) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	element = driver.findElement(By.xpath(locator));
	return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attribute, String... values) {
	locator = String.format(locator, (Object[]) values);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	element = driver.findElement(By.xpath(locator));
	js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
    }

    public Object scrollToBottomPage(WebDriver driver) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public Object navigateToUrlByJS(WebDriver driver, String url) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	return js.executeScript("window.location = '" + url + "'");
    }

    public void waitForElementPresense(WebDriver driver, String locator) {
	waitExplicit = new WebDriverWait(driver, Constants.LONG_TIME_OUT);
	waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
	locator = String.format(locator, (Object[]) values);
	//highlightElement(driver, locator);
	waitExplicit = new WebDriverWait(driver, Constants.LONG_TIME_OUT);
	waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
	// highlightElement(driver, locator);
	waitExplicit = new WebDriverWait(driver, Constants.LONG_TIME_OUT);
	try {
	    waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
	} catch (Exception e) {
	    Reporter.log("============ Wait for element not visible================");
	    Reporter.log(e.getMessage());
	    System.err.println("\"============ Wait for element not visible================");
	    System.err.println(e.getMessage() + "\n");
	}

    }

    public void waitForElementClickable(WebDriver driver, String locator) {
	waitExplicit = new WebDriverWait(driver, Constants.LONG_TIME_OUT);
	waitExplicit.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
	Date date = new Date();
	waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIME_OUT);
	overideTimeout(driver, Constants.SHORT_TIME_OUT);
	System.out.println(" Start Time = " + date.toString());
	waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
	System.out.println("End Time = " + new Date().toString());
	overideTimeout(driver, Constants.LONG_TIME_OUT);
    }

    public void waitForAlertPresence(WebDriver driver) {
	waitExplicit = new WebDriverWait(driver, Constants.LONG_TIME_OUT);
	waitExplicit.until(ExpectedConditions.alertIsPresent());
    }

    public void highlightElement(WebDriver driver, String locator) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	element = driver.findElement(By.xpath(locator));
	String originalStyle = element.getAttribute("style");
	js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
		"color: black; border: 4px solid red;");
	try {
	    Thread.sleep(800);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

    }

    public void openMultiplePage(WebDriver driver, String pageName) {
	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
	clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
    }

    public void clickToDynamicButtonTextboxTextArea(WebDriver driver, String feildName) {
	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, feildName);
	clickToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, feildName);
    }

    public void sendKeyToDynamicTextboxTextArea(WebDriver driver, String feildName, String value) {
	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, feildName);
	sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, value, feildName);
    }
 
    public void sendKeyToDynamicTextboxTextAreaVerify(WebDriver driver, String feildName, String value) {
	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, feildName);
	sendKeyToElementVerify(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, value, feildName);
    }
    public String getDynamicErrorMessage(WebDriver driver, String feildName) {
	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE, feildName);
	return getTextElement(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE, feildName);
    }

    public boolean isPageTitleDisplayed(WebDriver driver, String pageTitle) {
	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_PAGE_TITLE, pageTitle);
	return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_PAGE_TITLE, pageTitle);
    }

    public String getDynamicDataTable(WebDriver driver, String feildName) {
	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_VALUE_IN_TABLE, feildName);
	return getTextElement(driver, AbstractPageUI.DYNAMIC_VALUE_IN_TABLE, feildName);
    }

    public String getDynamicTextValueInTextBox(WebDriver driver, String feildName ) {
	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, feildName);
	return getAtributeValue(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, "value", feildName);
    }
    
    public String getDynamicTextValueInTextArea(WebDriver driver, String feildName) {
	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, feildName);
	return getTextElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, feildName);
    }
    public int randomNumber() {
	Random rd = new Random();
	return rd.nextInt(999999);
    }
  
    
}
