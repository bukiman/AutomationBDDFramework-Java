package steps;

import drivers.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.ProductPage;

public class ProductSteps {

    ProductPage productPage = new ProductPage(DriverManager.getDriver());

    @And("user adds it to the cart")
    public void addsToCart() {
        productPage.addToCart();
    }

    @Then("the product should display in the cart preview")
    public void isProductDisplayedInTheCartPreview() {
        Assert.assertEquals(productPage.getCartProductName(), productPage.getProductName());
    }
}
