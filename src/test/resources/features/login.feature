Feature: LoginFeature
  This feature deals with the login and logout functionality of Sakai

  Scenario: Log in to Sakai
    Given I navigate to the login page
    When I login as student
    Then I should see NYU Classes logo

  Scenario: Log out of Sakai
    Given I am logged in
    When I log out
    Then I should see logged out