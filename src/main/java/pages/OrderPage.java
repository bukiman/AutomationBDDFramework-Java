package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends BasePage {

    @FindBy(id = "next_btn")
    private WebElement nextBtn;

    @FindBy(css = "[name='safepay'] + img")
    private WebElement safepayRadio;

    @FindBy(name = "safepay_username")
    private WebElement safepayUser;

    @FindBy(name = "safepay_password")
    private WebElement safepayPassword;

    @FindBy(id = "pay_now_btn_SAFEPAY")
    private WebElement payNowBtn;

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectSafepayRadio() {
        click(safepayRadio);
    }

    public void clickNextBtn() {
        click(nextBtn);
    }

    public void setSafepayData(String userName, String password) {
        safepayUser.clear();
        safepayPassword.clear();
        writeText(safepayUser, userName);
        writeText(safepayPassword, password);
    }

    public ReceiptPage clickPayNowBtn() {
        click(payNowBtn);
        return new ReceiptPage(driver);
    }
}
