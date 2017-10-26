@login @all
Feature: Login
  This feature deals with the login and logout functionality of Sakai

  Scenario: Log in to Sakai
    Given I navigate to the login page
    When I login as student
    And I close new feature popup if needed
    Then I should see Sakai logo

  Scenario: Log out of Sakai
    Given I am logged in as "student"
    And I close new feature popup if needed
    Then I should see Sakai logo
    When I log out
    Then I should see logged out