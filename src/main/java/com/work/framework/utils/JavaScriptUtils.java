package com.work.framework.utils;

import com.work.framework.base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Selenium JavaScript Executor Utility Class
 *
 */
public class JavaScriptUtils {

    // constructor
    public JavaScriptUtils() {}

    public static void execute(String command) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        js.executeScript(command);
    }

    public static void execute(String command,
                               WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        js.executeScript(command, element);
    }

    public static void click(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public static void click(By by) {
        WebElement element = DriverBase.getInstance().getCurrentDriver().findElement(by);

        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public static void sendKeys(String keys,
                                WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        js.executeScript("arguments[0].value='" + keys + "';", element);
    }

    public static boolean isPageReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        return (Boolean)js.executeScript("return document.readyState").equals("complete");
    }

    public static boolean isAjaxReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) DriverBase.getInstance().getCurrentDriver();
        return (Boolean)js.executeScript("return document.jQuery.active == 0");
    }
}
