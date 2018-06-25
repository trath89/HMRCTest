
  Feature: As a new user 
    I want to create a new account 
    So that I can do online shopping 

  Scenario: Create a user new account with valid details 
    Given  user is on landing page 
    When   user chooses to Sign in 
    And   user enters an email address to create an account
    And   user clicks on Register 
    Then   user lands on create an account page
    When   user provides personal information
    And   click on Register 
    Then   the new user account is created 
 
   Scenario: Sort dresses by price
	 When Customer clicks on the Menu Dresses
	 Then user lands on dresses landing page
     When customer sorts dresses on price highest to lowest
     Then The dresses are sorted in the order of highest to lowest
  	
   Scenario: User selects an item on dresses page
	 When Customer clicks on the Menu Dresses
	 Then user lands on dresses landing page
	 When User clicks on Pink colour
	 And User clicks on Add to Cart for the dress displayed
	 Then Message ‘Product successfully added to your shopping cart’ is seen
	 When user clicks on Sign Out
	 Then user land on landing page
	 
   Scenario: User logs in to AutomationPractice site
     Given user is on landing page
     When user chooses to Sign in
	 When User enters valid username and password
	 And clicks on Sign In button
	 Then Home page is displayed 
	 Then User confirms the total displayed for items added in cart
	 
	 
	 
    

