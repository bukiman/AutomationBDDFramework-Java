package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {

    @FindBy(name = "usernameRegisterPage")
    private WebElement regUserName;

    @FindBy(name = "emailRegisterPage")
    private WebElement regEmail;

    @FindBy(name = "passwordRegisterPage")
    private WebElement regPassword;

    @FindBy(name = "confirm_passwordRegisterPage")
    private WebElement regConfirmPassword;

    @FindBy(name = "i_agree")
    private WebElement agreeConditionsCheckBox;

    @FindBy(id = "register_btn")
    private WebElement regButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillForm(String user, String email, String pass) {
        writeText(regUserName, user);
        writeText(regEmail, email);
        writeText(regPassword, pass);
        writeText(regConfirmPassword, pass);
        click(agreeConditionsCheckBox);
    }

    public void clickRegisterButton() {
        Actions actions = new Actions(driver);
        actions.scrollToElement(regButton).perform();
        click(regButton);
    }
}
