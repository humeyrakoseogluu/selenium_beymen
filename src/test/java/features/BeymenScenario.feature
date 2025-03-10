Feature: Beymen Website Automation Scenario
  Background:
    Given setting driver
  Scenario: go to Beymen website and search product
    Given go to Beymen website
    Then verify that is home website
    When search for sort word
    And clear the search box
    When search and enter for gomlek word
    Then select a random product from search result
    And write the product info and price info to a txt file
    And add this selected product to the cart
    Then verify the price of the product and the price in the cart
    When increase the quantity to 2
    Then verify that the quantity
    When remove the product from the cart
    Then verify that the cart is empty


