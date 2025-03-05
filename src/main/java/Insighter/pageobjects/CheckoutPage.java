package Insighter.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Insighter.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".cartSection h3")
	List <WebElement> cart;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public List<WebElement> productsAddedToCart() {
		return cart;
	}
	
	public Boolean validatingCart(String item) {
	Boolean foundMatch = productsAddedToCart().stream().anyMatch(match -> match.getText().equalsIgnoreCase(item));
	return foundMatch;
	}
	public PaymentPage checkoutButton() {
		checkout.click();
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
	}

}
