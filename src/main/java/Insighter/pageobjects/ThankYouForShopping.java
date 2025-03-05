package Insighter.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Insighter.AbstractComponents.AbstractComponent;

public class ThankYouForShopping extends AbstractComponent {

	WebDriver driver;
	
	public ThankYouForShopping(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement message;
	
	@FindBy(xpath="(//button[contains(@class, 'btn btn-custom')])[4]")
	WebElement signOut;
	
	

	public String confirmationMessage() {
		return message.getText();
	}
	
	public void signOut() {
		signOut.click();
	}
	
}
