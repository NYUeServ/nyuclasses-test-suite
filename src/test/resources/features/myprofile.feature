Feature: MyProfile
  This feature tests the functionality of My Profile tool once logged in to Sakai

  Scenario: My Profile overview
    Given I am logged in
    When I click on My Profile tool
    Then I should see My Profile tab with my name "TesterWP F Tester"
     And I should see "Pictures" tab
     And I should see "Connections" tab
     And I should see "Messages" tab
     And I should see "Search" tab
     And I should see "Privacy" tab
     And I should see "Preferences" tab
    When I click on "Pictures" tab
    Then I should see "My pictures" under Pictures tab
     And I should see "Add picture" under Pictures tab
     And I should see button with name of "Upload chosen files"
    When I click on "Connections" tab
    Then I should see "My connections" under Connections tab
     And I should see button with name of "Search for connections"
    When I click on "Messages" tab
    Then I should see button with name of "My messages"
     And I should see button with name of "Compose message"
    When I click on "Search" tab
    Then I should see "Search profiles" under Search tab
     And I should see button with name of "Search"
    When I click on "Privacy" tab
    Then I should see "Privacy settings" under Privacy tab
     And I should see button with name of "Save settings"
    When I click on "Preferences" tab
    Then I should see "Preferences" under Preferences tab
     And I should see button with name of "Save settings"



