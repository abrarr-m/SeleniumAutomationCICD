package Insighter.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Insighter.TestComponents.BaseTest;
import Insighter.pageobjects.CheckoutPage;
import Insighter.pageobjects.OrdersPage;
import Insighter.pageobjects.PaymentPage;
import Insighter.pageobjects.ProductCatalog;
import Insighter.pageobjects.ThankYouForShopping;

public class SubmitOrderTest extends BaseTest {
	String item = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		// List<WebElement> products = productCatalog.getProductsList();
		productCatalog.addProductToCart(item);
		CheckoutPage checkoutPage = productCatalog.goToCart();

		Boolean foundMatch = checkoutPage.validatingCart(item);
		Assert.assertTrue(foundMatch);
		PaymentPage paymentPage = checkoutPage.checkoutButton();
		paymentPage.proceedToPayment("India");
		ThankYouForShopping thankYouForShopping = paymentPage.submitOrder();

		String confirmMessage = thankYouForShopping.confirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		thankYouForShopping.signOut();

	}

	@Test(dependsOnMethods = {"submitOrder"})
	public void ordersHistory() {

		ProductCatalog productCatalog = landingPage.loginApplication("example1234@example.com", "Example@1234");
		OrdersPage orders = productCatalog.myOrders();
		Assert.assertTrue(orders.validatingOrders(item));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Insighter\\Data\\PurchaseOrder.json");
			
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		/*
		 * HashMap<String, String> map = new HashMap<String, String>();
		 * map.put("email","emaple12345@example.com"); map.put("password",
		 * "Example@12345");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email","example1234@example.com"); map1.put("password",
		 * "Example@1234");
		 */
		
		/*
		 * @DataProvider public Object[][] getData() {
		 * 
		 * return new Object [][] {{"emaple12345@example.com", "Example@12345"},
		 * {"example1234@example.com","Example@1234"}};
		 * 
		 * }
		 */
		
	}

}



