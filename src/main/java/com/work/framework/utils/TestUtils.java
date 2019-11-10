package com.work.framework.utils;

import com.work.framework.base.DriverBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Selenium Driver Wait Utility Class
 *
 */
public class TestUtils {

    static Logger logger = LoggerFactory.getLogger(TestUtils.class);

    // constructor
    public TestUtils() {}

    /**
     * getBrowserInfo method gets Capabilities from the given WebDriver driver object
     *
     * @param driver
     * @return browser name
     */
    public static String getBrowserInfo(WebDriver driver) {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        logger.info(browserName);
        return browserName;
    }

    /**
     * isElementDisplayed method to wait for a designated period for the give WebElement before
     * throwing exception if the element is not found
     *
     * @param element
     * @param durationInSecs
     * @return true if element is displayed or false if not displayed
     */
    public static boolean isElementDisplayed(WebElement element, int durationInSecs) {
        boolean result = false;
        try {
            // wait for the element to appear
            WebDriverWait wait = new WebDriverWait(DriverBase.getInstance().getCurrentDriver(), durationInSecs);
            result = wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (NoSuchElementException e) {
            logger.info(e.toString());
        }
        return result;
    }

    /**
     * waitFor method to wait up to a designated period before
     * throwing exception (static locator)
     * @param element
     * @param timer
     */
    public static void waitFor(WebElement element,
                               int timer) {
        try {
            // wait for the element to appear
            WebDriverWait exists = new WebDriverWait(DriverBase.getInstance().getCurrentDriver(), timer);
            exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
        } catch(Exception e) {}
    }


    /**
     * overloaded waitFor method to wait up to a designated period before
     * throwing exception (dynamic locator)
     * @param by
     * @param timer
     */
    public static void waitFor(By by,
                               int timer) {
        try {
            // wait for the element to appear
            WebDriverWait exists = new WebDriverWait(DriverBase.getInstance().getCurrentDriver(), timer);

            //examples: By.id(id), By.name(name), By.xpath(loactor), By.cssSelector(css)
            exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
        } catch(Exception e) {}
    }

    /**
     * waitForGone method to wait up to a designated period before
     * throwing exception if element still exists
     *
     * @param by
     * @param timer
     */
    public static void waitForGone(By by,
                               int timer) {
        try {
            // wait for the element to disappear
            WebDriverWait exists = new WebDriverWait(DriverBase.getInstance().getCurrentDriver(), timer);

            //examples: By.id(id), By.name(name), By.xpath(loactor), By.cssSelector(css)
            exists.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOfElementLocated(by)));
        } catch(Exception e) {}
    }

    /**
     * waitForUrl method to wait up to a designated period before
     * throwing exception if URL is not found
     *
     * @param url
     * @param timer
     */
    public static void waitForUrl(String url,
                                   int timer) {
        try {
            WebDriverWait exists = new WebDriverWait(DriverBase.getInstance().getCurrentDriver(), timer);

            exists.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)));
        } catch(Exception e) {}
    }

    /**
     * elementExists - wrapper around the WebDriverWait method to
     * to return true or false
     *
     * @param element
     * @param timer
     */
    public static boolean elementExists(WebElement element,
                                    int timer) {
        try {
            WebDriverWait exists = new WebDriverWait(DriverBase.getInstance().getCurrentDriver(), timer);

            exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
            return true;
        } catch(StaleElementReferenceException |
                TimeoutException |
                NoSuchElementException e) {
            return false;
        }
    }

    /**
     * waitForTitle method to wait up to a designated period before
     * throwing exception if Title is not found
     *
     * @param title
     * @param timer
     */
    public static void waitForTitle(String title,
                                   int timer) {
        try {
            WebDriverWait exists = new WebDriverWait(DriverBase.getInstance().getCurrentDriver(), timer);

            exists.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
        } catch(Exception e) {}
    }

    //  https://id.atlassian.com/invite/p/confluence?id=chrLBXy4SzSF-zGZQusaDw&atlOrigin=eyJpIjoiYzYxMDU1Y2Q0NDE5NGI1MTkwZDNmNjY2NGMzMzZmMWYiLCJwIjoiYyJ9

    /**
     * pressKeyDown method to press a key on keyboard
     *
     * @param key
     */
    public static void pressKeyDown(Keys key) {
        Actions builder = new Actions(DriverBase.getInstance().getCurrentDriver());
        builder.keyDown(key).build().perform();
    }
}
