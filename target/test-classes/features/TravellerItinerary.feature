Feature:

  Scenario: Create itinerary and share to traveler
    Given I have logged into the agentsite
    When I Create an itinerary and share to traveler
    And Login into traveler site
    Then Validate the itinerary details in traveler page