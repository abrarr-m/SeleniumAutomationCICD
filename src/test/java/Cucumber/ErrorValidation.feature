
@tag
Feature: Error Validation

  @ErrorValidation
  Scenario Outline: Login Page Error Validation
    Given Land on Ecommerce Page 
    Given Loggin with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  									| password 		 |
      | example1234@example.com | Example@123 | 

