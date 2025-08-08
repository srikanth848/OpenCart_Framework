package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_01_AccountRegistrationTest extends BaseClass {
		
	@Test(groups= {"Regression", "Master"})
	public void verify_account_registration() {
		
		logger.info("***** Started TC_01_AccountRegistrationTest *****");
		
		try {
			
			HomePage hmPage = new HomePage(driver);
			hmPage.clickMyAccount();
			logger.info("Clicked on MyAccount link..");
			hmPage.clickRegister();
			logger.info("Clicked on Register link..");
			
			AccountRegistrationPage acRegPage = new AccountRegistrationPage(driver);
			
			logger.info("Providing customer details..");
			acRegPage.setFirstName(randomString().toUpperCase());
			acRegPage.setLastName(randomString().toUpperCase());
			acRegPage.setEmail(randomString()+"@gmail.com");
			acRegPage.setTelephone(randomNumber());
			
			String password = randomAlphaNumeric();
			acRegPage.setPassword(password);
			acRegPage.setConfirmPassword(password);
			
			acRegPage.checkPrivacyPolicy();
			logger.info("Checked and agreed to privacy policy...");
			
			acRegPage.clickContinue();
			logger.info("Clicked on Continue button...");
			
			logger.info("Validating account creation message..");
			String confirmMsg = acRegPage.getConfirmationMsg();
			if(confirmMsg.equals("Your Account Has Been Created!")) {
				logger.info("Account created...");
				Assert.assertTrue(true);
			}
			else {
				logger.error("Account creation failed...");
				logger.info("Test failed....");
				Assert.fail();
			}
		}
		catch(Exception e) {
			Assert.fail();
		}
			
		//Assert.assertEquals(confirmMsg, "Your Account Has Been Created!");
		
		logger.info("***** Finished TC_01_AccountRegistrationTest *****");
	}
	
}
