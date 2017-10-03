Feature: MyOverview
  This feature tests the functionality of My Overview Page once logged in to Sakai

  Scenario: My Overview information
    Given I am logged in
      And I close new feature popup if needed
    Then I should see "NYU Classes Info"
    And I should see "Calendar"
    And I should see "Recent Announcements"