package Insighter.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Insighter.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	// Declare a WebDriver instance
	WebDriver driver;

	// Constructor for the LandingPage class
	public LandingPage(WebDriver driver) {
		super(driver); // Call the parent class constructor
		this.driver = driver; // Assign the passed WebDriver instance to the current class variable
		PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory// This binds the WebElements
												// to their corresponding locators
	}
	
	// Page Factory Mechanism
	// Using PageFactory to locate the email input field by its 'id'
	@FindBy(id = "userEmail")//@FindBy annotation at time of compilation converters this to driver.findElement(By.id("userEmail"))
	WebElement userEmail;

	// Using PageFactory to locate the password input field by its 'id'
	@FindBy(id = "userPassword")
	WebElement userPassword;

	// Using PageFactory to locate the login button by its 'id'
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	/**
	 * This method performs the login action.
	 * It enters the provided email and password into the respective fields and clicks the login button.
	 * 
	 * @param email    The email address to enter
	 * @param password The password to enter
	 * @return 
	 */
	public ProductCatalog loginApplication(String email, String password) {
	    userEmail.sendKeys(email);  
	    userPassword.sendKeys(password);  
	    submit.click();  
	    ProductCatalog productCatalog = new ProductCatalog(driver);
	    return productCatalog;
	}

	public void goTo() {
	    driver.get("https://rahulshettyacademy.com/client");  
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
