package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
	
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@id='content']//div[3]//img")
	List<WebElement> products;
	
	@FindBy(xpath="//div[@class='button-group']//button[1]")
	List<WebElement> btnsAddToCart;
	
	@FindBy(xpath="//div[contains(@class,'alert-success')]")
	WebElement msgSuccess;
	
	@FindBy(xpath="//div[contains(@class,'alert-success')]//a")
	List<WebElement> successMsgLinks;
	
	
	public boolean isProductExists(String productName) {
		boolean flag = false;
		for(WebElement product:products) {
			if(product.getAttribute("title").equals(productName)) {
				flag = true;
				break;
				}
		}
		return flag;
	}
	
	public void clickAddToCart(String productName) {
		for(int i=0; i<products.size(); i++) {
			if(products.get(i).getAttribute("title").equals(productName))
				btnsAddToCart.get(i).click();
		}
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
	
	public void selectProduct(String productName) {
		for(WebElement product:products) {
			if(product.getAttribute("title").equals(productName))
				product.click();
		}
	}

}
