@HotelPLP
Feature: Hotel search functionality

  Background:
    Given I have launched agent site application

  Scenario: Search any hotel and validate PLP
    When I login with valid credentials
    And Search for an hotel
    Then Landed in Hotel PLP page

  Scenario: Search any hotel with future check in and check out date
    When I login with valid credentials
    And Enter hotel name
    And Enter check in and CheckOut date and search
    Then Landed in Hotel PLP page
