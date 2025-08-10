package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h1[text()='Checkout']")
	WebElement checkoutHeader;
	
	@FindBy(xpath="//input[@id='button-payment-address']")
	WebElement btnBillingContinue;
	
	@FindBy(xpath="//input[@id='button-shipping-address']")
	WebElement btnDeliveryContinue;
	
	@FindBy(xpath="//input[@id='button-shipping-method']")
	WebElement btnShippingMethodContinue;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkTermsAndConditions;
	
	@FindBy(xpath="//input[@id='button-payment-method']")
	WebElement btnPaymentMethodContinue;
	
	@FindBy(xpath="//div[@id='content']//tfoot//tr[3]//td[2]")
	WebElement txtTotalPriceIncludingShippingCharges;
	
	@FindBy(xpath="//input[@id='button-confirm']")
	WebElement btnConfirmOrder;
	
	@FindBy(xpath="//div[@id='content']/h1")
	WebElement msgOrderPlaced;
	
	WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
	public boolean isCheckoutPageExists() {
		try {
			return checkoutHeader.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickContinue_billingDetails() {
		btnBillingContinue.click();
	}
	
	public void clickContinue_deliveryDetails() {
		btnDeliveryContinue.click();
	}
	
	public void clickContinue_deliveryMethod() {
		btnShippingMethodContinue.click();
	}
	
	public void checkTermsAndConditions() {
		chkTermsAndConditions.click();
	}
	
	public void clickContinue_paymentMethod() {
		btnPaymentMethodContinue.click();
	}
	
	public double getTotalPriceIncludingShippingCharges() {
		myWait.until(ExpectedConditions.visibilityOf(txtTotalPriceIncludingShippingCharges));
		return Double.parseDouble(txtTotalPriceIncludingShippingCharges.getText().replaceAll("[^\\d.]", ""));
	}
	
	public void clickConfirmOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnConfirmOrder);
	}
	
	public boolean isOrderPlacementsuccess() {
		try {
			myWait.until(ExpectedConditions.visibilityOf(msgOrderPlaced));
			return msgOrderPlaced.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}

}
