package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_03_LogIn_DataDrivenTest extends BaseClass {
	
	@Test(dataProvider="LogInData", dataProviderClass=DataProviders.class, groups="DataDriven")
	public void verify_logIn_dataDrivenTest(String email, String pass, String res) {
		
		logger.info("***** Started TC_03_LogIn_DataDrivenTest *****");
		
		try {
			
			HomePage hmPage = new HomePage(driver);
			hmPage.clickMyAccount();
			logger.info("Clicked on My Account link...");
			hmPage.clickLogin();
			logger.info("Clicked on LogIn link...");
			
			LogInPage logInPage = new LogInPage(driver);
			
			logger.info("Providing customer details...");
			logInPage.setEmail(email);
			logInPage.setPassword(pass);
			logInPage.clickLogin();
			logger.info("Clicked on Login button...");
			
			MyAccountPage myAcPage = new MyAccountPage(driver);
			logger.info("Validating My Account page...");
			boolean targetPage = myAcPage.isMyAccountPageExists();
			
			if(res.equalsIgnoreCase("Valid")) {
				if(targetPage) {
					myAcPage.clickLogOut();
					Assert.assertTrue(true);
				}
				else
					Assert.fail();
			}
			
			
			if(res.equalsIgnoreCase("Invalid")) {
				if(targetPage) {
					myAcPage.clickLogOut();
					Assert.fail();
				}
				else
					Assert.assertTrue(true);
			}
			
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("***** Finished TC_03_LogIn_DataDrivenTest *****");
	}

}
