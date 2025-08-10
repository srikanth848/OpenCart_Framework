package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCartPage extends BasePage {
	
	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@id='content']/form//tbody//tr")
	List<WebElement> products;
	
	@FindBy(xpath="//div[@id='accordion']//a[text()='Estimate Shipping & Taxes ']")
	WebElement estimateShippingAndTaxeslnk;
	
	@FindBy(xpath="//select[@id='input-country']")
	WebElement countryDrpdown;
	
	@FindBy(xpath="//select[@id='input-zone']")
	WebElement stateDrpdown;
	
	@FindBy(xpath="//input[@name='postcode']")
	WebElement txtPostcode;
	
	@FindBy(xpath="//button[@id='button-quote']")
	WebElement btnGetQuote;
	
	@FindBy(xpath="//input[@name='shipping_method']")
	WebElement radioShippingMethod;
	
	@FindBy(xpath="//div[@class='radio']//label")
	WebElement txtShippingCharges;
	
	@FindBy(xpath="//input[@value='Apply Shipping']")
	WebElement btnApplyShipping;
	
	@FindBy(xpath="//div[contains(text(),'Success: Your shipping estimate has been applied!')]")
	WebElement msgEstimateAppliedSuccess;
	
	@FindBy(xpath="//div[@id='content']//div[2]//tr[3]//td[2]")
	WebElement txtTotalPriceIncludingShippingCharges;
	
	@FindBy(xpath="//a[text()='Checkout']")
	WebElement btnCheckout;
	
	@FindBy(xpath="//div[@id='content']//p")
	WebElement msgCartEmpty;

	
	public boolean isProductInCart(String productName, String qunatity, double unitPrice, double totalUnitsPrice) {
		boolean flag = false;
		for(WebElement product:products) {
			List<WebElement> cells = product.findElements(By.tagName("td"));
			if(!cells.isEmpty()) {
				String productText = cells.get(1).findElement(By.tagName("a")).getText();
				String units = cells.get(3).findElement(By.xpath("//input[contains(@name,'quantity')]")).getAttribute("value");
				WebElement price = cells.get(4);
				double productPrice = Double.parseDouble(price.getText().replaceAll("[^\\d.]", ""));
				WebElement totalPrice = cells.get(5);
				double totalProductsPrice = Double.parseDouble(totalPrice.getText().replaceAll("[^\\d.]", ""));
				if(productText.equals(productName) && units.equals(qunatity) && productPrice==unitPrice && totalProductsPrice==totalUnitsPrice) {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	public void clickEstimateShippingCharges() {
		estimateShippingAndTaxeslnk.click();
	}
	
	public void setCountry(String country) {
		new Select(countryDrpdown).selectByVisibleText(country);
	}
	
	public void setState(String state) {
		new Select(stateDrpdown).selectByVisibleText(state);
	}
	
	public void setPostCode(String postCode) {
		txtPostcode.sendKeys(postCode);
	}
	
	public void clickGetQuote() {
		btnGetQuote.click();
	}
	
	public void SelectShippingMethod() {
		radioShippingMethod.click();
	}
	
	public double getShippingCharges() {
		return Double.parseDouble(txtShippingCharges.getText().replaceAll("[^\\d.]", ""));
	}
	
	public void clickApplyShippingCharges() {
		btnApplyShipping.click();
	}
	
	public boolean isShippingEstimateApplied() {
		try {
			return msgEstimateAppliedSuccess.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public double getTotalPriceIncludingShippingCharges() {
		return Double.parseDouble(txtTotalPriceIncludingShippingCharges.getText().replaceAll("[^\\d.]", ""));
	}
	
	public void clickCheckout() {
		btnCheckout.click();
	}
	
	public void emptyShoppingCart() {
		for(int i=0; i<products.size(); i++) {
			List<WebElement> cells = products.get(0).findElements(By.tagName("td"));
			WebElement removeProduct = cells.get(3).findElement(By.xpath("//button[@class='btn btn-danger']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", removeProduct);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
