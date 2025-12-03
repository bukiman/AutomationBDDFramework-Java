package utils;

import drivers.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Screenshot {

    private static final Logger log = LoggerFactory.getLogger(Screenshot.class);


    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void saveScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("target/screenshots/" + name + ".png"));
        } catch (Exception e) {
            log.error("Error al guardar screenshot: " + e.getMessage());
        }
    }
}
