package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceiptPage extends BasePage {

    @FindBy(css = "#orderPaymentSuccess h2")
    private WebElement successfulMessage;

    public ReceiptPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getSuccessfulMessageText() {
        return getText(successfulMessage);
    }
}
