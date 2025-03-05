
@tag
Feature: Purchase the product from ECommerce Website

Background: 
	Given Land on Ecommerce Page
  
  @Regression
  Scenario Outline: Positive test of submiting the order
    Given Loggin with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  									| password 		 | productName |
      | example1234@example.com | Example@1234 | ZARA COAT 3 |
