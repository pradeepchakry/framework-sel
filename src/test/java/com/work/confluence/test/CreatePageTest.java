package com.work.confluence.test;

import com.work.confluence.pageObjects.GlobalFunctionality;
import com.work.confluence.pageObjects.HomePagePO;
import com.work.confluence.pageObjects.LoginPagePO;
import com.work.framework.base.DriverBase;
import com.work.framework.listeners.TestNG_Listener;
import com.work.framework.utils.Global_VARS;
import com.work.framework.utils.JavaScriptUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNG_Listener.class)
public class CreatePageTest {
    private WebDriver driver;
    private DriverBase driverBase;
    private Logger logger = LoggerFactory.getLogger(CreatePageTest.class);
    private String homePageURL = "https://sh1pf1984.atlassian.net";
    private LoginPagePO loginPagePO;
    private HomePagePO homePagePO;

    @BeforeClass
    public void setup() throws Exception {
        driverBase = DriverBase.getInstance();
        driverBase.setDriver(Global_VARS.BROWSER, Global_VARS.ENVIRONMENT, Global_VARS.PLATFORM);
        driver = driverBase.getCurrentDriver();
    }

    @Test
    public void homePageLoaded() {
        driver.get(homePageURL);
        Assert.assertTrue(JavaScriptUtils.isPageReady(driver));
    }

    @Test (dependsOnMethods = {"homePageLoaded"})
    public void login() throws Exception {
        loginPagePO = new LoginPagePO(driver);
        loginPagePO.login("sh1pf1984@gmail.com", "sh1pfr1z");
    }

    @Test (dependsOnMethods = {"login"})
    public void createBlankPage() throws Exception {
        GlobalFunctionality functionality = new GlobalFunctionality();
        functionality.createBlankPage();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
