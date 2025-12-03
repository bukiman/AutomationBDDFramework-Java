package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    //private final By checkoutBtn = By.id("checkOutPopUp");

    @FindBy(id = "checkOutPopUp")
    private WebElement checkoutBtn;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickCheckout() {
        click(checkoutBtn);
    }
}
