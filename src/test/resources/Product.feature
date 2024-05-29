Feature: To Validate Add To Cart Feature

  Scenario: To validate adding and removing products in add to cart
    Given User launched the browser and flipkart
    When User searched the product through search box
    And User adding product into cart
    When User place order and login to the application
    And User has to select address and payment option
    And User need remove the product from the cart
    Then User closes the browser
