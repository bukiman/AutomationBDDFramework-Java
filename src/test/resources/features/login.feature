@UIs
@LoginFeature
Feature: Login in Advantage Demo

    @LoginSuccessful
    Scenario: Successful login
        Given user opens Advantage Demo site
        When user clicks on the user icon
        And inserts username "nuevoUser" and password "Pass123"
        And presses the login button
        Then user should be on the Home page
        And the username should be displayed

    @LoginFailed
    Scenario: Failed login
        Given user opens Advantage Demo site
        When user clicks on the user icon
        And inserts username "testUser1" and password "pASS456"
        And presses the login button
        Then the failed login message "Incorrect user name or password." should be displayed

    Scenario Outline: Login with different credentials
        Given user opens Advantage Demo site
        When user clicks on the user icon
        And inserts username "<user>" and password "<pass>"
        And presses the login button
        Then user should be on the Home page
        Then the username should be displayed
        Examples:
            | user       | pass        |
            | testuser   | Password123 |
            | test2      | Abc456      |
            | testdata   | testData1   |
