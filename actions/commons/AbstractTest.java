package commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractTest {
    private WebDriver driver;
    protected final Log log;

    protected AbstractTest() {
	log = LogFactory.getLog(getClass());
    }

    protected WebDriver openMultiBrowser(String browserName) {
	if (browserName.equalsIgnoreCase("firefox")) {
	    // WebDriverManager.chromedriver().arch64().setup();
	    driver = new FirefoxDriver();
	}
	if (browserName.equalsIgnoreCase("chrome")) {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	}
	if (browserName.equalsIgnoreCase("chromeheadless")) {
	    WebDriverManager.chromedriver().setup();
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("headless");
	    options.addArguments("window-size=2880x1800");
	    driver = new ChromeDriver(options);
	}
	driver.manage().timeouts().implicitlyWait(Constants.LONG_TIME_OUT, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get(Constants.PRODUCTION_APP_URL);
	return driver;
    }

    private boolean checkPassed(boolean condition) {
	boolean pass = true;
	try {
	    if (condition == true)
		log.info("===PASSED==");
	    else
		log.info("===FAILED==");
	    Assert.assertTrue(condition);
	} catch (Throwable e) {
	    pass = false;

	    // Add bug on ReportNG
	    VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
	    Reporter.getCurrentTestResult().setThrowable(e);
	}
	return pass;
    }

    protected boolean verifyTrue(boolean condition) {
	return checkPassed(condition);
    }

    private boolean checkFailed(boolean condition) {
	boolean pass = true;
	try {
	    if (condition == false)
		log.info("===PASSED===");
	    else
		log.info("===FAILED===");
	    Assert.assertFalse(condition);
	} catch (Throwable e) {
	    pass = false;
	    VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
	    Reporter.getCurrentTestResult().setThrowable(e);
	}
	return pass;
    }

    protected boolean verifyFalse(boolean condition) {
	return checkFailed(condition);
    }

    private boolean checkEquals(Object actual, Object expected) {
	boolean pass = true;
	boolean status;
	try {
	    if (actual instanceof String && expected instanceof String) {
		actual = actual.toString().trim();
		log.info("Actual = " + actual);
		expected = expected.toString().trim();
		log.info("Expected = " + expected);
		status = (actual.equals(expected));
	    } else {
		status = (actual == expected);
	    }

	    log.info("Compare value = " + status);
	    if (status) {
		log.info("===PASSED===");
	    } else {
		log.info("===FAILED===");
	    }
	    Assert.assertEquals(actual, expected, "Value is not matching!");
	} catch (Throwable e) {
	    pass = false;
	    VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
	    Reporter.getCurrentTestResult().setThrowable(e);
	}
	return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
	return checkEquals(actual, expected);
    }

    protected void closeBrowserAndDriver(WebDriver driver) {
	try {
	    // get name of OS and convert to lower case
	    String osName = System.getProperty("os.name").toLowerCase();
	    log.info("OS name = " + osName);

	    // define variable cmd
	    String cmd = "";
	    if (driver != null) {
		driver.quit();
	    }

	    if (driver.toString().toLowerCase().contains("chrome")) {
		if (osName.toLowerCase().contains("mac")) {
		    cmd = "pkill chromedriver";
		} else if (osName.toLowerCase().contains("windows")) {
		    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
		}
		Process process = Runtime.getRuntime().exec(cmd);
		process.waitFor();
	    }

	    if (driver.toString().toLowerCase().contains("internetexplorer")) {
		if (osName.toLowerCase().contains("window")) {
		    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
		    Process process = Runtime.getRuntime().exec(cmd);
		    process.waitFor();
		}
	    }
	    log.info("---------- QUIT BROWSER SUCCESS ----------");
	} catch (Exception e) {
	    log.info(e.getMessage());
	}
    }

    protected int randomNumber() {
	Random rd = new Random();
	return rd.nextInt(999999);
    }

    public String getCurrentDay() {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	System.out.println(dateFormat.format(date));
	return dateFormat.format(date);
    }

}
