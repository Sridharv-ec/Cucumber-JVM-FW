Feature: Hotel booking functionality

  Scenario: Validate hotel booking functionality
    Given I have launched agent site application
    When I login with prepaid valid credentials
    And Enter hotel city
    And Select check in and CheckOut date and search
    And Click On Add to itinerary
    And Enter lead traveller and passenger
    And Click on Book button
    And Click on Confirm and pay later button
    Then Hotel item should get confirmed