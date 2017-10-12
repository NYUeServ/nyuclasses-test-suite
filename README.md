NYU Sakai Test Suite
=======================
This project's aim is to create a comprehensive testing suite for NYU's customized version of Sakai. It performs actions via direct browser interactions.

The tests are feature or behavior driven (BDD) using cucumber. These tests are implemented in Java via cucumber-java and cucumber-jvm.

The test uses Selenium integration, which also uses the Selenium WebDriver, with the help of WebDriverManager for driver binary version control.

Requirements
---
Maven 3+, Java 8, Docker, Firefox, and Chrome

[Download requirements](https://github.com/NYUeServ/nyuclasses-test-suite/wiki/System-Requirements)

Running with Docker Script:
---
We have made a script to run your very own instance of our test suite via docker. If you just want to run it
without a care in the world, first configure your own **config.env** by changing our template file **config.env.example**.
This file lives within your working directory.
 
Then, execute the following in your console/terminal:
```bash
$ ./run.sh
```

[Additional run.sh Options](https://github.com/NYUeServ/nyuclasses-test-suite/wiki/run.sh-Flags)

Running standalone (without Docker Script)
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
$ mvn clean verify
```
To run the test cases from a docker container, create a config.env file with the desired environment variables and simply use the docker compose command below:
```
$ docker-compose build && docker-compose up
```
The tests should execute and automate through your browsers in headless mode.

Important Links
---
[Test Suite FAQs](https://github.com/NYUeServ/nyuclasses-test-suite/wiki/FAQs)

[WebDriver Versions](https://github.com/NYUeServ/nyuclasses-test-suite/wiki/WebDriver-Versions)