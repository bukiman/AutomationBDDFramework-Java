package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(className = "PopUp")
    private WebElement container;

    @FindBy(name = "username")
    private WebElement userNameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(id = "sign_in_btn")
    private WebElement loginButton;

    @FindBy(className = "create-new-account")
    private WebElement registerLink;

    @FindBy(id = "signInResultMessage")
    private WebElement failedMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        writeText(userNameField, username);
    }

    public void enterPassword(String password) {
        writeText(passwordField, password);
    }

    public boolean isLoginContainerDisplayed() {
        return container.isDisplayed();
    }

    public HomePage waitForLoginPage() {
        wait.until(ExpectedConditions.attributeContains(container, "style", "display: none"));
        return new HomePage(driver);
    }

    public void clickLogin() {
        actions.scrollByAmount(0, -200).perform();
        click(loginButton);
    }

    public String getFailedMessageText() {
        wait.until(ExpectedConditions.attributeContains(failedMessage, "class", "invalid"));
        return getText(failedMessage);
    }

    public RegisterPage createNewAccount() {
        click(registerLink);
        return new RegisterPage(driver);
    }
}
