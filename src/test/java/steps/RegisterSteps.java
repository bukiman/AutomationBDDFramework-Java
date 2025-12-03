package steps;

import drivers.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.HomePage;
import pages.RegisterPage;

import java.util.Map;

public class RegisterSteps {

    HomePage homePage = new HomePage(DriverManager.getDriver());
    RegisterPage registerPage = new RegisterPage(DriverManager.getDriver());

    @And("fills the form with user {string}, email {string} and password {string}")
    public void completaElFormularioConUsuarioEmailPassword(String user, String email, String password) {
        registerPage.fillForm(user, email, password);
    }

    @And("fills the form with the following data:")
    public void fillsTheFormWithTheFollowingData(Map<String, String> data) {
        registerPage.fillForm(data.get("userName"), data.get("email"), data.get("password"));
    }

    @And("submits the form")
    public void submitForm() {
        registerPage.clickRegisterButton();
    }

    @Then("the username should display {string}")
    public void isUserNameDisplayed(String string) {
        Assert.assertEquals(homePage.getUserName(), string);
    }
}
