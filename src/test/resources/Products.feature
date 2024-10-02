Feature: Products scenarios

  @test
  Scenario Outline: Validate product info on Products page
    Given I'm logged in
    Then the product is listed with title "<title>" and price "<price>"
    Examples:
      | title                         | price  |
      | Sauce Labs Backpack           | $29.99 |
#      | Sauce Labs Bolt T-shirt       | $15.99 |
#      | Sauce Labs Onesie             | $7.99  |
#      | Test.allThings() Tshirt (Red) | $15.99 |


  @test
  Scenario Outline: Validate product info on Products Details page
    Given I'm logged in
    When I click product title "<title>"
    Then I should on product details page with title "<title>", price "<price>" and description "<description>"
    Examples:
      | title                         | price  | description |
      | Sauce Labs Backpack           | $29.99 | carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.|
#      | Sauce Labs Bolt T-shirt       | $15.99 | Get your testing superhero on with the Sauce Labs |