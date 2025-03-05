package Insighter.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Insighter.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="td:nth-child(3)")
	List <WebElement> orders;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public List<WebElement> productsInOrderHistory() {
		return orders;
	}
	
	public Boolean validatingOrders(String item) {
	Boolean foundOrder = productsInOrderHistory().stream().anyMatch(match -> match.getText().equalsIgnoreCase(item));
	return foundOrder;
	}


}
