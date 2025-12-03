package flows;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class LoginFlow extends BasePage {

    HomePage homePage;
    LoginPage loginPage;

    public LoginFlow(WebDriver driver) {
        super(driver);
        homePage = new HomePage(driver);
    }

    public HomePage loginWithCredentials(String userName, String password) {
        homePage.goTo();
        loginPage = homePage.clickUserIcon();
        loginPage.enterUsername(userName);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        return new HomePage(driver);
    }
}
