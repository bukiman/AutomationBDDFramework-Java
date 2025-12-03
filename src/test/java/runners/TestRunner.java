package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import listeners.TestNGListeners;
import org.testng.annotations.Listeners;

@Listeners(TestNGListeners.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "hooks"},

        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
