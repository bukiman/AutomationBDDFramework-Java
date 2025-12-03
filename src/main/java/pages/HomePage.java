package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(className = "loader")
    private WebElement loader;

    @FindBy(className = "PopUp")
    private WebElement loginPopUp;

    @FindBy(className = "containMiniTitle")
    private WebElement userName;

    @FindBy(id = "laptopsImg")
    private WebElement laptopsCategory;

    @FindBy(id = "menuUser")
    private WebElement userIcon;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage goTo() {
        driver.get("https://www.advantageonlineshopping.com/");
        return this;
    }

    public LoginPage clickUserIcon() {
        wait.until(ExpectedConditions.attributeContains(loader, "style", "display: none"));
        click(userIcon);
        return new LoginPage(driver);
    }

    public boolean isUserNameDisplayed() {
        return userName.isDisplayed();
    }

    public String getUserName() {
        return getText(userName);
    }

    public CategoryPage clickOnCategory(String category) {
        wait.until(ExpectedConditions.attributeContains(loginPopUp, "style", "display: none"));
        switch(category) {
            case "Speakers":
                break;
            case "Laptops":
                click(laptopsCategory);
                break;
        }
        return new CategoryPage(driver);
    }
}
