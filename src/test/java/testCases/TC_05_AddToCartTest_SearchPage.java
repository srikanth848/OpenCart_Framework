package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_05_AddToCartTest_SearchPage extends BaseClass {
	
	@Test(groups="Regression")
	public void verify_addToCart() {
		logger.info("**** Starting TC_05_AddToCartTest ****");
		
		HomePage hp = new HomePage(driver);
		hp.enterProductName("Mac");
		logger.info("Product entered in search box");
		hp.clickSearch();
		logger.info("Clicked on search icon");
		
		SearchPage sp = new SearchPage(driver);
		logger.info("Validating if product is available");
		if(sp.isProductExists("MacBook")) {
			logger.info("Clicking on Add To Cart");
			sp.clickAddToCart("MacBook");
			logger.info("Validating if desired product added to cart successfully");
			if(sp.isAddToCartSuccess()) {
				Assert.assertTrue(sp.isDesiredProductAddedToCart("MacBook"));
			}
			else {
				Assert.fail();
			}
		}
		
		logger.info("**** Finished TC_05_AddToCartTest ****");
	}

}
