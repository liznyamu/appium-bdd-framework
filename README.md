# Appium - Cucumber BDD Framework Design

## Cucumber Framework plan
- [x] Designed using Page Object Model - for re-usability
- [x] Behavioral driven
- [x] Supports CI/CD integration
- [x] Supports parallel execution using both JUnit and TestNG
- [x] Screenshot capture on scenario failure
- [x] Video recording for each scenario
- [x] Supports both Android and iOS
- [x] Supports parallel logging using Log4J2
- [x] Starts Appium server programmatically
- [x] Supports command line execution using Maven (JUnit)
- [ ] Supports command line execution using Maven (TestNG)
- [ ] Integrating with Git
- [ ] Integrating with Jenkins


## Tools/Technologies
- *Cucumber:*  BDD framework and Reporting
- *Gherkin:* Business Readable, Domain Specific Language
  - define the actions users are going to perform
- *Appium:* Mobile automation library
- *Java:* Programming language
- *Maven:* Build automation tool
- *Log4j2:* Logging framework
- *JUnit:* Unit test management tool
- *TestNG:* Functional test management tool
- *IntelliJ:* IDE


## Run tests 
```
mvn test -DplatformName=Android -Dudid=emulator-5554 -DdeviceName=Medium_Phone_API_35  -DsystemPort=10002 -DchromeDriverPort=11002
```
```
mvn test -DplatformName=iOS -Dudid=70C06531-5518-48CD-82C6-950254E65848 -DdeviceName=iPhone_16_Plus -DwdaLocalPort=10003
```