package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.ItemPage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_07_CheckOutProductTest extends BaseClass {
	
	@Test(groups="Sanity")
	public void verify_checkoutProcess() {
		logger.info("**** Starting TC_07_CheckOutProductTest ****");
		
		SoftAssert sf = new SoftAssert();
		
		HomePage hmPage = new HomePage(driver);
		hmPage.clickMyAccount();
		logger.info("Clicked on MyAccount link..");
		hmPage.clickLogin();
		logger.info("Clicked on LogIn link..");
		
		LogInPage logInPage = new LogInPage(driver);
		
		logger.info("Providing customer details...");
		logInPage.setEmail(properties.getProperty("email"));
		logInPage.setPassword(properties.getProperty("password"));
		logInPage.clickLogin();
		logger.info("Clicked on Login button...");
		
		MyAccountPage myAcPage = new MyAccountPage(driver);
		
		logger.info("Validating My Account page...");
		sf.assertTrue(myAcPage.isMyAccountPageExists());
		
		ShoppingCartPage shCart = new ShoppingCartPage(driver);
		hmPage.clickShoppingCart();
		try {
			shCart.emptyShoppingCart();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		hmPage.enterProductName("HP LP3065");
		logger.info("Product entered in search box");
		hmPage.clickSearch();
		logger.info("Clicked on search icon");
		
		SearchPage sp = new SearchPage(driver);
		double productPrice = 0;
		
		logger.info("Validating if product is available");
		if(sp.isProductExists("HP LP3065")) {
			sp.selectProduct("HP LP3065");
			
			ItemPage ip = new ItemPage(driver);
			productPrice = ip.getProductPrice();
			ip.setQuantity("2");
			logger.info("Added quantity");
			ip.clickAddToCart();
			logger.info("Clicked on Add To Cart");
			logger.info("Validating if desired product added to cart successfully");
			if(ip.isAddToCartSuccess())
				sf.assertTrue(ip.isDesiredProductAddedToCart("HP LP3065"));
		}
		
		hmPage.clickShoppingCart();
		logger.info("Clicked on Shopping Cart..");
		
		logger.info("Product is available in cart...");
		sf.assertTrue(shCart.isProductInCart("HP LP3065", "2", productPrice, productPrice*2));
		
		logger.info("Estimating the Shipping Charges based on country, state and shipping method...");
		shCart.clickEstimateShippingCharges();
		shCart.setCountry(properties.getProperty("country"));
		logger.info("Country selected...");
		shCart.setState(properties.getProperty("state"));
		logger.info("State selected...");
		shCart.setPostCode(properties.getProperty("postCode"));
		shCart.clickGetQuote();
		logger.info("Clicked on Get Quote...");
		shCart.SelectShippingMethod();
		logger.info("Selected flat shipping method...");
		double shippingCharge = shCart.getShippingCharges();
		shCart.clickApplyShippingCharges();
		logger.info("Clicked on Apply Shipping charges..");
		logger.info("Validating if the shipping charges are applied successfully...");
		sf.assertTrue(shCart.isShippingEstimateApplied());
		logger.info("Validating the total price...");
		sf.assertTrue(shCart.getTotalPriceIncludingShippingCharges()==(productPrice*2+shippingCharge));
		
		shCart.clickCheckout();
		logger.info("Clicked on checkout...");
		
		CheckoutPage ch = new CheckoutPage(driver);
		logger.info("Validating if the product checked out successfully...");
		sf.assertTrue(ch.isCheckoutPageExists());
		
		ch.clickContinue_billingDetails();
		logger.info("Clicked on continue for Billing details...");
		ch.clickContinue_deliveryDetails();
		logger.info("Clicked on continue for Delivery details...");
		ch.clickContinue_deliveryMethod();
		logger.info("Clicked on continue for delivery method...");
		ch.checkTermsAndConditions();
		logger.info("Checked terms and conditions checkbox...");
		ch.clickContinue_paymentMethod();
		logger.info("Clicked on continue for Payment method...");
		logger.info("Validating the total price....");
		sf.assertTrue(ch.getTotalPriceIncludingShippingCharges()==(productPrice*2+shippingCharge));
		ch.clickConfirmOrder();
		logger.info("Clicked on confirm order...");
		logger.info("Validating if order placed successfully...");
		sf.assertTrue(ch.isOrderPlacementsuccess());
		
		sf.assertAll();
		
		logger.info("**** Finished TC_07_CheckOutProductTest ****");
	}

}
