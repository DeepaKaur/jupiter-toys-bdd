#Author: Deepa Kaur
  #Keywords Summary : Contact page
@Shop
Feature:  JupiterToys Shop page

  Scenario: Testing shop page for items in cart
    Given A user is on home page and nevigates to shop page
    When User buys '2' 'Funny Cow'
    And User buys '1' 'Fluffy Bunny'
    And User clicks on cart button
    Then verify that there are '3' items in the cart
    And verify that '2' funny cows and  '1' fluffy bunny are in the cart

  Scenario: Testing shop page for items in cart
    Given A user is on home page and nevigates to shop page
    When User buys '3' 'Stuffed Frog'
    And User buys '5' 'Fluffy Bunny'
    And User buys '3' 'Valentine Bear'
    And User clicks on cart button
    Then verify that the subtotal of each product is correct