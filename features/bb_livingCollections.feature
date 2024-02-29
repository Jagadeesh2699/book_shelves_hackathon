Feature: Collections

  @sanity @regression
  Scenario: retriveing all submenus from Livinng collections
    Given user should be on homepage
    When navigate to Living collections
    Then fetch and submenus under Seating & Chairs
