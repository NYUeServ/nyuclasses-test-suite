Feature: LoginFeature
  This feature deals with the login functionality of NYU Classes, which is integrated with NYU's Shibboleth

  Scenario Outline: Login with valid netid and password
    Given I navigate to the login page
    And I enter <netid> and <password>
    And I press "_eventId_proceed"
    Then I should see NYU Classes logo
    When I log out
    Then I should see logged out

    Examples:
      | netid      | password   |
      | <username> | <password> |