package pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends BasePage {
	
	public ItemPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='quantity']")
	WebElement txtQuantity;
	
	@FindBy(xpath="//button[@id='button-cart']")
	WebElement btnAddToCart;
	
	@FindBy(xpath="//div[contains(@class,'alert-success')]")
	WebElement msgSuccess;
	
	@FindBy(xpath="//div[contains(@class,'alert-success')]//a")
	List<WebElement> successMsgLinks;
	
	@FindBy(xpath="//ul//h2")
	WebElement txtPrice;
	
	public void setQuantity(String quantity) {
		txtQuantity.clear();
		txtQuantity.sendKeys(quantity);
	}
	
	public void clickAddToCart() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnAddToCart);
	}
	
	public boolean isAddToCartSuccess() {
		try {
			return msgSuccess.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean isDesiredProductAddedToCart(String productName) {
		boolean flag = false;
		if(successMsgLinks.get(0).getText().equals(productName) && successMsgLinks.get(1).getText().equals("shopping cart")) {
			flag = true;
		}
		return flag;
	}

	public double getProductPrice() {
		return Double.parseDouble(txtPrice.getText().replaceAll("[^\\d.]", ""));
	}

}
