package steps;

import drivers.DriverManager;
import flows.LoginFlow;
import io.cucumber.java.en.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class LoginSteps {

    private static final Logger log = LoggerFactory.getLogger(LoginSteps.class);
    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;

    @Given("^user logs in with user name \"(.*)\" and password \"(.*)\"$")
    public void userLogsInWithUserNameAndPassword(String userName, String password) {
        LoginFlow loginFlow = new LoginFlow(DriverManager.getDriver());
        homePage = loginFlow.loginWithCredentials(userName, password);
    }

    @Given("^user opens Advantage Demo site$")
    public void openSite() {
        homePage = new HomePage(DriverManager.getDriver()).goTo();
    }

    @When("^user clicks on the user icon$")
    public void clickUserIcon() {
        loginPage = homePage.clickUserIcon();
    }

    @And("^inserts username \"(.*)\" and password \"(.*)\"$")
    public void insertCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("^presses the login button$")
    public void pressLoginButton() {
        loginPage.clickLogin();
    }

    @When("^navigates to the register form$")
    public void navigateToRegisterForm() {
        registerPage = loginPage.createNewAccount();
    }

    @Then("user should be on the Home page")
    public void userShouldBeOnTheHomePage() {
        homePage = loginPage.waitForLoginPage();
        Assert.assertFalse(loginPage.isLoginContainerDisplayed());
    }

    @Then("^the username should be displayed$")
    public void validateLogin() {
        Assert.assertTrue(homePage.isUserNameDisplayed());
    }

    @Then("^the failed login message \"(.*)\" should be displayed$")
    public void theFailedLoginMessageShouldBeDisplayed(String message) {
        System.out.println("message: " + loginPage.getFailedMessageText());
        Assert.assertEquals(loginPage.getFailedMessageText(), message);
    }
}
