Feature: MyProfile
  This feature tests the functionality of My Profile tool once logged in to Sakai

  Scenario: My Profile overview
    Given I am logged in
     And I close new feature popup if needed
    When I click on "My Profile" tool
     And I should see my name "TesterWP F Tester" under My Profile tab
     And I should see "Pictures" tab
     And I should see "Connections" tab
     And I should see "Messages" tab
     And I should see "Search" tab
     And I should see "Privacy" tab
     And I should see "Preferences" tab
    When I click on "Pictures" tab
    Then I should see "My pictures" as heading
     And I should see "Add picture" as heading
     And I should see button with name of "Upload chosen files"
    When I click on "Connections" tab
    Then I should see "My connections" as heading
     And I should see button with name of "Search for connections"
    When I click on "Messages" tab
    Then I should see button with name of "My messages"
     And I should see button with name of "Compose message"
    When I click on "Search" tab
    Then I should see "Search profiles" as heading
     And I should see button with name of "Search"
    When I click on "Privacy" tab
    Then I should see "Privacy settings" as heading
     And I should see button with name of "Save settings"
    When I click on "Preferences" tab
    Then I should see "Preferences" as heading
     And I should see button with name of "Save settings"
    When I log out
    Then I should see logged out



