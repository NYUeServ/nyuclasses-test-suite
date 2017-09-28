NYU Sakai Test Suite
=======================
This project's aim is to create a comprehensive testing suite for NYU's customized version of Sakai. It performs actions via direct browser interactions.

The tests are feature or behavior driven (BDD) using cucumber. These tests are implemented in Java via cucumber-java and cucumber-jvm.

The test uses Selenium integration, which also uses the Selenium WebDriver, with the help of WebDriverManager for driver binary version control.

Requirements
---
Install Maven (via [manual](https://maven.apache.org/install.html) or [homebrew](https://brew.sh) on MacOS X)

Java 8

Firefox Installation

Chrome Installation

Running the Tests
---
First, export your test account credentials
```bash
$ export sakai_student_username=<student tester username>
$ export sakai_student_password=<student tester password>
$ export sakai_instructor_username=<instructor tester username>
$ export sakai_instructor_password=<instructor tester password>
```

It may be a good idea to test out your environment first before running your test
```
$ mvn test-compile
```

Go to the command line, navigate to the project folder, and execute the following:
```
$ mvn clean test
```
The tests should execute and automate through your browsers.

Changing WebDriver Versions
---
Navigate to the following directory: src/main/resources

Edit webdrivermanager.properties to whatever your heart desires.

