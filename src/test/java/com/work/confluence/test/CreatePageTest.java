package com.work.confluence.test;

import com.work.confluence.data.AccountInfo;
import com.work.confluence.data.JSONDataProvider;
import com.work.confluence.data.PageInfo;
import com.work.confluence.functionality.GlobalFunctionality;
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
    GlobalFunctionality globalFunctionality;
    private WebDriver driver;
    private DriverBase driverBase;
    private Logger logger = LoggerFactory.getLogger(CreatePageTest.class);
    private LoginPagePO loginPagePO;
    private AccountInfo accountInfo;
    private PageInfo pageInfo;

    @BeforeClass
    public void setup() throws Exception {
        driverBase = DriverBase.getInstance();
        driverBase.setDriver(Global_VARS.BROWSER, Global_VARS.ENVIRONMENT, Global_VARS.PLATFORM);
        driver = driverBase.getCurrentDriver();
        driver.manage().window().maximize();

        globalFunctionality = new GlobalFunctionality();
    }

    @Test(dataProvider = "accountInfo_JSON", dataProviderClass = JSONDataProvider.class)
    public void openURL(AccountInfo data) {
        this.accountInfo = data;
        driver.get(this.accountInfo.getUrl());
        Assert.assertTrue(JavaScriptUtils.isPageReady(driver));
    }

    @Test(dependsOnMethods = {"openURL"})
    public void login() throws Exception {
        loginPagePO = new LoginPagePO(driver);
        loginPagePO.login(this.accountInfo.getUserName(), this.accountInfo.getPassword());
    }

    @Test(dependsOnMethods = {"login"}, dataProvider = "pageInfo_JSON", dataProviderClass = JSONDataProvider.class)
    public void createBlankPage(PageInfo data) throws Exception {
        this.pageInfo = data;
        globalFunctionality.createBlankPage(this.pageInfo.getTitle());
    }

    @AfterClass
    public void tearDown() {
        DriverBase.getInstance().closeDriver();
    }

}
