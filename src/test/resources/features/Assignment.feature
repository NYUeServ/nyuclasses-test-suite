@assignment @all
Feature: Assignment
	This feature deals with verfication of assignment submission success by a student on NYU Classes

	Scenario: As an Instructor create a new test assignment
		Given I am logged in as "instructor"
		And I close new feature popup if needed
		And I navigate to "Cucumber Test Site" course site
		When I click on "Assignments" tool
		And I navigate to the "Add" tab
		And I create a new assignment "New Test Assignment" with open date "10/23/2017"
		Then I should see "New Test Assignment" on the page
		When I log out
		Then I should see logged out

	Scenario: As a Student submit assignemt
		Given I am logged in as "student"
		And I close new feature popup if needed
		And I navigate to "Cucumber Test Site" course site
		When I click on "Assignments" tool
		Then I should see "New Test Assignment" on the page
		When I submit my assignment "New Test Assignment"
		Then I should see "You have successfully submitted your work." on the page
		When I log out
		Then I should see logged out

	Scenario: As an Instructor delete the new test assignment
		Given I am logged in as "instructor"
		And I close new feature popup if needed
		And I navigate to "Cucumber Test Site" course site
		When I click on "Assignments" tool 
		When I remove the assignment "New Test Assignment"
		When I log out
		Then I should see logged out 