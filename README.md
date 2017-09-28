NYU Sakai Test Suite
=======================
This project's aim is to create a comprehensive testing suite for NYU's customized version of Sakai. It performs actions via direct browser interactions.

The tests are feature or behavior driven (BDD) using cucumber. These tests are implemented in Java via cucumber-java and cucumber-jvm.

The test uses Selenium integration, which also uses the Selenium WebDriver, with the help of WebDriverManager for driver binary version control.

Requirements
---
Maven

Java 8

Firefox Installation

Chrome Installation

Running the Tests
---
Go to the command line, navigate to the project folder, and execute the following:
```
mvn clean test
```

Changing WebDriver Versions
---
Navigate to the following directory: src/main/resources

Edit webdrivermanager.properties to whatever your heart desires.

