package com.work.framework.utils;

import com.work.framework.base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Selenium JavaScript Executor Utility Class
 */
public class JavaScriptUtils {

    // constructor
    public JavaScriptUtils() {
    }

    /**
     * execute - method to run JavaScript commands
     *
     * @param command
     */
    public static void execute(String command) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        js.executeScript(command);
    }

    /**
     * overloaded execute - method to run JavaScript commands
     *
     * @param command
     */
    public static void execute(String command,
                               WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        js.executeScript(command, element);
    }

    /**
     * click - method to click an element using JavaScript
     *
     * @param element
     */
    public static void click(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * overloaded click - method to click an element using JavaScript
     *
     * @param by
     */
    public static void click(By by) {
        WebElement element = DriverBase.getInstance().getCurrentDriver().findElement(by);

        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * sendKeys - method to send keys using JavaScript
     *
     * @param keys
     * @param element
     */
    public static void sendKeys(String keys,
                                WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        js.executeScript("arguments[0].value='" + keys + "';", element);
    }

    /**
     * isPageReady - method to confirm the page has loaded with JavaScript
     *
     * @param driver
     * @return
     */
    public static boolean isPageReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        return js.executeScript("return document.readyState").equals("complete");
    }

    /**
     * isAjaxReady - method to confirm the page has loaded with Ajax
     *
     * @param driver
     * @return
     */
    public static boolean isAjaxReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        return (Boolean) js.executeScript("return document.jQuery.active == 0");
    }
}
