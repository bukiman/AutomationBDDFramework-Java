package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    @FindBy(css = "#Description h1")
    private WebElement productName;

    @FindBy(name = "save_to_cart")
    private WebElement addToCartBtn;

    @FindBy(tagName = "table")
    private WebElement table;

    @FindBy(id = "checkOutPopUp")
    private WebElement checkoutBtn;

    @FindBy(css = "table h3")
    private WebElement cartProductName;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return getText(productName);
    }

    public void addToCart() {;
        click(addToCartBtn);
    }

    public String getCartProductName() {
        return getText(cartProductName);
    }

    public OrderPage clickCheckoutBtn() {
        wait.until(ExpectedConditions.visibilityOf(table));
        click(checkoutBtn);
        return new OrderPage(driver);
    }
}
