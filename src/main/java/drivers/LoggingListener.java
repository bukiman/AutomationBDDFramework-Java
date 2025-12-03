package drivers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingListener implements WebDriverListener {

    private static final Logger log = LoggerFactory.getLogger(LoggingListener.class);

    @Override
    public void beforeClick(WebElement element) {
        if(element.getText().isEmpty()) {
            log.info("Clicking on {}", element.getTagName());
        } else {
            log.info("Clicking on {}", element.getText());
        }
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("Typing in {} input field string \"{}\"", element.getAttribute("name"), keysToSend);
    }
}
