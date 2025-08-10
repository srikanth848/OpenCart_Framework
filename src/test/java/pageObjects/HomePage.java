package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	// WebElements
	@FindBy(xpath="//a[@title='My Account']")
	WebElement myAccountLink;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement registerLink;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement logInLink;
	
	@FindBy(xpath="//input[@name='search']")
	WebElement txtSearchBox;
	
	@FindBy(xpath="//div[@id='search']//button[@type='button']")
	WebElement btnSearch;
	
	@FindBy(xpath="//a[@title='Shopping Cart']")
	WebElement shoppingCartlnk;
	
	// Actions
	public void clickMyAccount() {
		myAccountLink.click();
	}
	
	public void clickRegister() {
		registerLink.click();
	}
	
	public void clickLogin() {
		logInLink.click();
	}
	
	public void enterProductName(String product) {
		txtSearchBox.sendKeys(product);
	}
	
	public void clickSearch() {
		btnSearch.click();
	}
	
	public void clickShoppingCart() {
		shoppingCartlnk.click();
	}

}
