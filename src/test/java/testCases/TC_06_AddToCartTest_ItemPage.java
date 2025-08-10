package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ItemPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_06_AddToCartTest_ItemPage extends BaseClass {
	
	@Test(groups="Regression")
	public void verify_addToCart() {
		logger.info("**** Starting TC_06_AddToCartTest_ItemPage ****");
		
		HomePage hp = new HomePage(driver);
		hp.enterProductName("Mac");
		logger.info("Product entered in search box");
		hp.clickSearch();
		logger.info("Clicked on search icon");
		
		SearchPage sp = new SearchPage(driver);
		logger.info("Validating if product is available");
		if(sp.isProductExists("MacBook Pro")) {
			sp.selectProduct("MacBook Pro");
			
			ItemPage ip = new ItemPage(driver);
			ip.setQuantity("5");
			logger.info("Added quantity");
			ip.clickAddToCart();
			logger.info("Clicked on Add To Cart");
			logger.info("Validating if desired product added to cart successfully");
			if(ip.isAddToCartSuccess())
				Assert.assertTrue(ip.isDesiredProductAddedToCart("MacBook Pro"));
			else
				Assert.fail();
		}
		
		logger.info("**** Finished TC_06_AddToCartTest_ItemPage ****");
		
	}

}
