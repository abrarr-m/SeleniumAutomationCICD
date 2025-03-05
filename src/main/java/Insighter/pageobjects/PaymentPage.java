package Insighter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Insighter.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
	
		PageFactory.initElements(driver, this);
	}
	
	By dropDown = By.cssSelector(".ta-results");
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement btnInd;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	
	
	public  void proceedToPayment(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(dropDown);
		btnInd.click();
		
	}
	
	public ThankYouForShopping submitOrder() {
		placeOrder.click();
		
		ThankYouForShopping thankYouForShopping = new ThankYouForShopping(driver);
		return thankYouForShopping;
	}

}
