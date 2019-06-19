package commons;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestFailed implements IRetryAnalyzer {
    private int retryCount = 0;

    public boolean retry(ITestResult result) {
	if (retryCount < Constants.MAX_RETRY_COUNT) {
	    System.out.println("Retry test name" + result.getName() + "with" + (retryCount + 1) + "time(s)");
	    retryCount++;
	    return true;

	}
	return false;
    }

}
