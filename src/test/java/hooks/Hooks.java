package hooks;

import drivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.messages.types.StepDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Screenshot;

public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before("not @APIs")
    public void setUp() {
        log.info("Setting up web driver");
        DriverManager.initDriver();
    }

    @After("not @APIs")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = Screenshot.takeScreenshot();
            scenario.attach(screenshot, "image/png", "Evidencia_Fallo");
        }
        DriverManager.quitDriver();
    }
}
