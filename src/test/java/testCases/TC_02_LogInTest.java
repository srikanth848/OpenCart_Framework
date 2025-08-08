package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_02_LogInTest extends BaseClass {
	
	@Test(groups= {"Sanity", "Master"})
	public void verify_LogIn() {
		
		logger.info("***** Started TC_02_LogInTest *****");
		
		try {
			
			HomePage hmPage = new HomePage(driver);
			hmPage.clickMyAccount();
			logger.info("Clicked on My Account link...");
			hmPage.clickLogin();
			logger.info("Clicked on LogIn link...");
			
			LogInPage logInPage = new LogInPage(driver);
			
			logger.info("Providing customer details...");
			logInPage.setEmail(properties.getProperty("email"));
			logInPage.setPassword(properties.getProperty("password"));
			logInPage.clickLogin();
			logger.info("Clicked on Login button...");
			
			MyAccountPage myAcPage = new MyAccountPage(driver);
			
			logger.info("Validating My Account page...");
			Assert.assertTrue(myAcPage.isMyAccountPageExists());
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("***** Finished TC_02_LogInTest *****");
		
	}

}
