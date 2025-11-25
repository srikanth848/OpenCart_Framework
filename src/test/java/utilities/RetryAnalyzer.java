package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import testBase.BaseClass;

public class RetryAnalyzer extends BaseClass implements IRetryAnalyzer {
	
	int retryCount = 0, maxRetryCount = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			retryCount++;
			System.out.println("Retrying "+ result.getTestClass().getName() +" test again ");
			System.out.println("Retrying "+ result.getName() +" test again ");
			return true;
		}
		return false;
	}
	

}
