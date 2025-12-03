package steps;

import drivers.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.testng.Assert;
import pages.OrderPage;
import pages.ReceiptPage;

import java.util.Map;

public class OrderSteps {

    OrderPage orderPage = new OrderPage(DriverManager.getDriver());
    ReceiptPage receiptPage = new ReceiptPage(DriverManager.getDriver());

    @And("^selects the (SafePay|Mastercard) payment type$")
    public void selectsPaymentType(String payingType) {
        orderPage.clickNextBtn();
        if(payingType.equals("Safepay")) {
            orderPage.selectSafepayRadio();
        }
    }

    @And("perform payment with the following SafePay credentials:")
    public void insertsSafePayCredentials(Map<String, String> data) {
        orderPage.setSafepayData(data.get("username"), data.get("password"));
        orderPage.clickPayNowBtn();
    }

    @Then("the message {string} should display")
    public void isSuccessfulPurchaseMessageDisplayed(String sucessfulPurchaseMessage) {
        Assert.assertEquals(receiptPage.getSuccessfulMessageText(), sucessfulPurchaseMessage);
    }
}
