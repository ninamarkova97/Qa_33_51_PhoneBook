package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListenerWD implements WebDriverListener {
    Logger logger = LoggerFactory.getLogger(ListenerWD.class);

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);
        logger.info("Before find element -->" + locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(driver, locator, result);
        logger.info("After find element -->" + locator);
        logger.info("Location of element -->" +result.getTagName());
    }
}
