package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage selectProduct(String product) {
        By productSel = By.xpath("//a[contains(.,'" + product + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(productSel));
        driver.findElement(productSel).click();
        return new ProductPage(driver);
    }
}
