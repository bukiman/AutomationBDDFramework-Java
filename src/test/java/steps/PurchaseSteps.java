package steps;

import drivers.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.CategoryPage;
import pages.HomePage;
import pages.OrderPage;
import pages.ProductPage;

public class PurchaseSteps {

    HomePage homePage = new HomePage(DriverManager.getDriver());
    CategoryPage categoryPage;
    ProductPage productPage;
    OrderPage orderPage;

    @When("user chooses the {string} category")
    public void selectCategory(String category) {
        categoryPage = homePage.clickOnCategory(category);
    }

    @And("selects the product {string}")
    public void selectProduct(String productName) {
        productPage = categoryPage.selectProduct(productName);
    }

    @When("user proceeds to checkout")
    public void userProceedsToCheckout() {
        orderPage = productPage.clickCheckoutBtn();
    }
}
