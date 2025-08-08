package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	// WebElements
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement msgHeader;
	
	@FindBy(xpath="//div/a[text()='Logout']")
	WebElement logOutLink;
	
	// Actions
	public boolean isMyAccountPageExists() {
		try {
			return msgHeader.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogOut() {
		logOutLink.click();
	}

}
