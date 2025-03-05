package Insighter.Test;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Insighter.TestComponents.BaseTest;
import Insighter.TestComponents.Retry;
import Insighter.pageobjects.CheckoutPage;
import Insighter.pageobjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginValidation() throws IOException, InterruptedException {
		
		landingPage.loginApplication("example1234@example.com", "xample@1234");
		Assert.assertEquals("Incorrect or password.", landingPage.getErrorMessage());

	}
	@Test
	public void productValidation() throws IOException, InterruptedException {

		String item = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("example1234@example.com", "Example@1234");
		//List<WebElement> products = productCatalog.getProductsList();
		productCatalog.addProductToCart(item);
		CheckoutPage checkoutPage = productCatalog.goToCart();
		Boolean foundMatch = checkoutPage.validatingCart("ZARA COAT 33");
		Assert.assertFalse(foundMatch);
		

	}
}
