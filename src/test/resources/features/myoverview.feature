@profile @all
Feature: MyOverview
  This feature tests the functionality of My Overview Page once logged in to Sakai

  Scenario: My Overview information
    Given I am logged in as "student"
      And I close new feature popup if needed
    Then I should see various content on home page
    When I log out
    Then I should see logged out