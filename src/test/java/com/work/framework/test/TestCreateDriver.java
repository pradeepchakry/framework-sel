package com.work.framework.test;

import com.work.framework.base.DriverBase;
import com.work.framework.listeners.TestNG_Listener;
import com.work.framework.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNG_Listener.class)
public class TestCreateDriver {
    WebDriver driver;
    DriverBase driverBase;
    Logger logger = LoggerFactory.getLogger(TestCreateDriver.class);

    @BeforeClass
    public void setup() throws Exception {
        driverBase = DriverBase.getInstance();
    }

    @Test
    public void testChromeDriver() throws Exception {
        driverBase.setDriver("chrome", "local", "linux");
        driver = driverBase.getCurrentDriver();
        driver.get("https://cricinfo.com");
        driverBase.driverWait(5);
        logger.info("Title --> " + driver.getTitle());
        Assert.assertEquals(TestUtils.getBrowserInfo(driver), "chrome");
        Assert.assertTrue(driver.getTitle().contains("ESPNcricinfo"));
    }

    @Test
    public void testFirefoxDriver() throws Exception {
        driverBase.setDriver("firefox", "local", "linux");
        driver = driverBase.getCurrentDriver();
        driver.get("https://cricinfo.com");
        driverBase.driverWait(5);
        logger.info("Title --> " + driver.getTitle());
        Assert.assertEquals(TestUtils.getBrowserInfo(driver), "firefox");
        Assert.assertTrue(driver.getTitle().contains("ESPNcricinfo"));
    }

    //@Test
    public void testIEDriver() throws Exception {
        driverBase.setDriver("ie", "local", "windows");
        driver = driverBase.getCurrentDriver();
        driver.get("https://cricinfo.com");
        driverBase.driverWait(5);
        logger.info("Title --> " + driver.getTitle());
        Assert.assertEquals(TestUtils.getBrowserInfo(driver), "internetexplorer");
        Assert.assertTrue(driver.getTitle().contains("ESPNcricinfo"));
    }

    @AfterMethod
    public void closeDriver() {
        driverBase.closeDriver();
    }
}
