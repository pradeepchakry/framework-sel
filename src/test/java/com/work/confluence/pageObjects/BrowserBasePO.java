package com.work.confluence.pageObjects;

import com.work.framework.base.DriverBase;
import com.work.framework.utils.Global_VARS;
import com.work.framework.utils.JavaScriptUtils;
import com.work.framework.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public abstract class BrowserBasePO <M extends WebElement> {
    public int elementWait = Global_VARS.TIMEOUT_ELEMENT;
    public String pageTitle = "";
    WebDriver driver = DriverBase.getInstance().getDriver();

    //constructor
    public BrowserBasePO() throws Exception {
        PageFactory.initElements(driver, this);
    }

    public abstract void setElementWait(int elementWait);
    public abstract int getElementWait();
    public abstract void setPageTitle(String pageTitle);
    public abstract String getPageTitle();

    public String getTitle() throws Exception {
        return driver.getTitle();
    }

    public void loadPage(String pageURL,
                         M element) throws Exception {
        driver.navigate().to(pageURL);

        JavaScriptUtils.isPageReady(driver);
        TestUtils.waitFor(element, Global_VARS.TIMEOUT_MINUTE);
    }

    public void loadPage(String pageURL,
                         String endPointUrl) throws Exception {
        driver.navigate().to(pageURL);

        JavaScriptUtils.isPageReady(driver);
        TestUtils.waitForUrl(endPointUrl, Global_VARS.TIMEOUT_MINUTE);
    }
}
