Feature: A set of scenarios for testing the "example" module

  Scenario: User use coupon in cart
    Given Coupon exist
    And User in home page
    When User navigate to item
    And User add item to his cart
    And User navigate to his cart
    And User apply coupon
    And Coupon added to cart
    And User click on checkout
    Then Coupon available in checkout Page
    And Close user


  Scenario: Admin delete coupon
    Given Admin in home page
    When Admin navigate to coupons managing
    And Admin logging in
    And Admin select the specific coupon
    And Admin delete coupon
    Then Coupon deleted
    And Close admin


  Scenario: User use coupon in cart and admin delete the coupon
    Given Coupon exist
    And User in home page
    When User navigate to item
    And User add item to his cart
    And User navigate to his cart
    And User apply coupon
    And Coupon added to cart
    And Admin in home page
    And Admin navigate to coupons managing
    And Admin logging in
    And Admin select the specific coupon
    And Admin delete coupon
    And Coupon deleted
    And User click on checkout
    Then Coupon not available in checkout Page
    And Close user
    And Close admin


