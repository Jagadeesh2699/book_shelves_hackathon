Feature: Search for the Boook Shelves

  @regression
  Scenario: search for the book shelves with required filters
    Given search for the Book Shelves from Search bar
    When select category as Kids Book Shelves
    And select price range below Rs.15,000
    And select exclude out of stock option
    And sort bookshelves from high price to low price
    Then fetch top 3 products displayed and print in the console
