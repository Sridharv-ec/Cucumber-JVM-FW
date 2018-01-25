Feature: Login feature validation 1

  Scenario: Login with valid credit credentails
    Given I have launched UK agent site application
    When I login with valid credit credentials
    Then landed in the Home page

  Scenario: Login with invalid credentials
    Given I have launched UK agent site application
    When I enter invalid credentials
    Then error thown on the login page