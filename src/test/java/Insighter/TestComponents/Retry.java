package Insighter.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0, MaxRetry = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (count < MaxRetry) {
			count++;
			return true;
		}
		return false;
	}
}
