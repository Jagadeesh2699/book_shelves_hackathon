Feature: giftCards customization

  @regression
  Scenario: creating a gift Card
    Given navigate to giftCards page
    When choose occasion as -birthday/anniversary-
    And fill -customize your gift card- with valid price & date and click on -Next- button
    And fill -To & From- details under -who is the lucky person- with invalid email Id
      | recipientsName   | tom        |
      | recipientsEmail  | tom^123    |
      | recipientsNumber | 9876543212 |
      | yourName         | dora       |
      | yourEmail        | dora@123   |
      | yourNumber       | 9876543217 |
      | address          | @3-123     |
      | pin              |     600001 |
      | message          | giftcard   |
      | amount           |      10000 |
    And capture and display the error message in console output
    Then fill valid email Id and confirm
      | validEmail | tom@123 |
    And validate all the given details in -confirm Details- section
