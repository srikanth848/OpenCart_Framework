package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_04_SearchProductTest extends BaseClass {

	@Test(groups="Master")
	public void verify_productSearch() {
		logger.info("**** Starting TC_04_SearchProductTest ****");
		
		HomePage hp = new HomePage(driver);
		hp.enterProductName("Mac");
		logger.info("Product name entered in search box");
		hp.clickSearch();
		logger.info("Clicked on search button");
		
		SearchPage sp = new SearchPage(driver);
		logger.info("Validating the product existence");
		Assert.assertTrue(sp.isProductExists("MacBook Pro"));
		
		logger.info("**** Finished TC_04_SearchProductTest ****");
	}
}
