package Insighter.StepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Insighter.TestComponents.BaseTest;
import Insighter.pageobjects.CheckoutPage;
import Insighter.pageobjects.LandingPage;
import Insighter.pageobjects.PaymentPage;
import Insighter.pageobjects.ProductCatalog;
import Insighter.pageobjects.ThankYouForShopping;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ThankYouForShopping thankYouForShopping;
	
	@Given ("Land on Ecommerce Page")
	public void land_on_ecommerce_page() throws IOException {
		
		landingPage = launchApplication();
	}
	
	@Given ("^Loggin with username (.+) and password (.+)$") //^, (.+), $ Represents that this is a regular expression, since we should not hard code data(parameterized value).
	public void loggin_with_username_and_password(String name, String password) {
		productCatalog = landingPage.loginApplication(name, password);
	}
	
	@When ("I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalog.getProductsList();
		productCatalog.addProductToCart(productName);
	}
	
	 @And ("^Checkout (.+) and submit the order$")
	 public void checkout_and_submit_the_order(String productName) {
		 CheckoutPage checkoutPage = productCatalog.goToCart();

			Boolean foundMatch = checkoutPage.validatingCart(productName);
			Assert.assertTrue(foundMatch);
			PaymentPage paymentPage = checkoutPage.checkoutButton();
			paymentPage.proceedToPayment("India");
			thankYouForShopping = paymentPage.submitOrder();
	 }
	 
	 @Then ("{string} message is displayed on ConfirmationPage") // Since we are directly passing the data here, we cannot write this as a regular expression, instead simply pass a {string}.
	 public void message_displayed_ConfirmationPage(String string) {
		 String confirmMessage = thankYouForShopping.confirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			driver.close();
	 }
	 
	 @Then ("{string} message is displayed")
	 public void message_displayed(String string) {
		 
		 Assert.assertEquals(string, landingPage.getErrorMessage());
		 driver.close();
	 }
		 
}
