# AlphaCCO-AutomationChallange

Steps to follow to run this test automation:

Clone this repo.
Go to cloned directory.
Execute the below command in the project director: mvn -DtargetBrowser=chrome test

Assumptions:
You should have JDK17 setuped on your machine. If you have other JDK then please update the POM file.

NOTES:
This automation is written in a way that each test case:
1. Is independent of other test case, until and unless explicityly mentioned.
2. Target browser will be created and closed for each test case.

Maven Options:
targetBrowser=[chrome | Firefox] default is chrome

To run on chrome browser:
mvn test
OR
mvn -DtargetBrowser=chrome
