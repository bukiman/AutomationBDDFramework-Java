package listeners;

import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(TestNGListeners.class);

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            log.error("Test {} failed", result.getMethod().getMethodName(), throwable);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("==================== STARTING EXECUTION ===================");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("**********  ########   ##   ##    ##   ##   ########   ##    ##   ########   #####     **********");
        log.info("**********  ##         ##   ##    ##   ##   ##         ##    ##   ##         ##  ##    **********");
        log.info("**********  ##         ##   ###   ##   ##   ##         ##    ##   ##         ##    ##  **********");
        log.info("**********  ####       ##   ####  ##   ##   ########   ########   ####       ##    ##  **********");
        log.info("**********  ##         ##   ##  ####   ##         ##   ##    ##   ##         ##    ##  **********");
        log.info("**********  ##         ##   ##   ###   ##         ##   ##    ##   ##         ##  ##    **********");
        log.info("**********  ##         ##   ##    ##   ##   ########   ##    ##   ########   #####     **********");
    }

    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }
}
