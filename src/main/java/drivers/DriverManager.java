package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.LoggingListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverManager {

    private static WebDriver driver;

    public static void initDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver chromeDriver = new ChromeDriver(setOptions());
        LoggingListener logger = new LoggingListener();
        driver = new EventFiringDecorator<>(logger).decorate(chromeDriver);
        //driver.manage().window().maximize();
    }

    public static ChromeOptions setOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        //options.addArguments("--window-size=1920,1080");
        options.addArguments("--window-size=1366,768");
        return options;
    }

    public static WebDriver getDriver() {
        if(driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
