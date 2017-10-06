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
First, export your test account credentials and the environment you want to test in. We made the browser configuration
an environment variable because it is easier for us to dockerize and run in parallel.
```bash
$ export sakai_student_username="<student tester username>"
$ export sakai_student_password="<student tester password>"
$ export sakai_instructor_username="<instructor tester username>"
$ export sakai_instructor_password="<instructor tester password>"
$ export sakai_browser="<browser here, e.g. chrome or firefox>"
```

It may be a good idea to test out your environment first before running your test
```
$ mvn test-compile
```

Go to the command line, navigate to the project folder, and execute the following:
```
$ mvn clean test
```
To run the test cases from a docker container, simply use the docker compose command below:
```
$ docker-compose up
```
The tests should execute and automate through your browsers.

Running the scripts:
run-script.sh is made to run all the test cases via a dockerized continer. You will have to specify the driver which will be used by the docker-machine to run the script.

```
bash ./run-script.sh --driver "driver-name" 
# --driver can be replaced by -d. To run on your machine in command line use 'virtualbox' as the driver-name
```
To remove all the docker images and delete the cucumber docker machine run the below command
```
bash ./run-script.sh --clean
# --clean can be replaced by -c
```

Changing WebDriver Versions
---
Navigate to the following directory: src/main/resources

Edit webdrivermanager.properties to whatever your heart desires.

FAQs
----
Q1: What is this PageWaiter class in your utilities?

A1: PageWaiter is a Javascript and JQuery web driver waiter we adapted because sometimes 
WebDrivers do not wait until thepage is fully loaded before checking for elements.

--

Q2: If you have PageWaiter, then why is there another WebDriverWait in the code?

A2: Well unfortunately, through our testing, the Gecko driver of Firefox actually do not
behave as intended when using PageWaiter, so we have to revert back to the built-in WebDriverWait
to wait for the specific elements to appear on page.

--

Q3: Does this test suite run different browsers in parallel?

A3: The test suite can only be run on a single browser serially. But by using docker-compose we can run on multiple browsers (chrome & firefox) in "parallel"! (They are still sharing the CPU of course.) 

--
