package com.work.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Singleton class
 */
@SuppressWarnings("varargs")
public class DriverBase {

    private static final int IMPLICIT_TIMEOUT = 0;
    private static final String propertyFile = new File
            ("src/main/resources/selenium.properties").getAbsolutePath();
    private static DriverBase instance = null;
    private String browserHandle = null;
    private Properties driverProps = new Properties();
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    private String getEnv = null;

    private Logger logger = LoggerFactory.getLogger(DriverBase.class);

    // constructor
    private DriverBase() {
    }

    /**
     * getInstance method to retrieve active driver instance
     *
     * @return CreateDriver
     */
    public static DriverBase getInstance() {
        if (instance == null) {
            instance = new DriverBase();
        }
        return instance;
    }

    /**
     * setDriver method
     *
     * @param browser     Chrome, Firefox or Internet Explorer
     * @param environment Linux, Windows or Mac
     * @param platform    Local, Remote or SauceLabs
     * @throws Exception
     */
    public final void setDriver(String browser,
                                String environment,
                                String platform) throws Exception {
        String getPlatform = null;
        DesiredCapabilities caps = null;

        //load properties from file...
        driverProps.load(new FileInputStream(propertyFile));

        if ("firefox".equals(browser)) {
            if (environment.equalsIgnoreCase("local")) {
                if (platform.toLowerCase().contains("windows")) {
                    System.setProperty("webdriver.gecko.driver",
                            driverProps.getProperty("gecko.driver.windows.path"));
                } else if (platform.toLowerCase().contains("linux")) {
                    System.setProperty("webdriver.gecko.driver",
                            driverProps.getProperty("gecko.driver.linux.path"));
                } else if (platform.toLowerCase().contains("mac")) {
                    System.setProperty("webdriver.gecko.driver",
                            driverProps.getProperty("gecko.driver.mac.path"));
                }
            } else {
                // Implement code for environments like Sauce Labs or Remote
            }

            // set firefox capabilities
            caps = DesiredCapabilities.firefox();
            FirefoxOptions options = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.autofocus", true);
            caps.setCapability(FirefoxDriver.PROFILE, profile);
            caps.setCapability("marionette", "true");
            webDriver.set(new FirefoxDriver(options.merge(caps)));
        } else if ("chrome".equals(browser)) {
            if (environment.equalsIgnoreCase("local")) {
                if (platform.toLowerCase().contains("windows")) {
                    System.setProperty("webdriver.chrome.driver",
                            driverProps.getProperty("chrome.driver.win64.path"));
                } else if (platform.toLowerCase().contains("linux")) {
                    System.setProperty("webdriver.chrome.driver",
                            driverProps.getProperty("chrome.driver.linux.path"));
                } else if (platform.toLowerCase().contains("mac")) {
                    System.setProperty("webdriver.chrome.driver",
                            driverProps.getProperty("chrome.driver.mac.path"));
                }
            }

            // set chrome capabilities
            caps = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--disable-plugins",
                    "--disable-extensions",
                    "--disable-popup-blocking");
            caps.setCapability(ChromeOptions.CAPABILITY, options);
            webDriver.set(new ChromeDriver(options.merge(caps)));
        } else if ("ie".equals(browser)) {
            if (environment.equalsIgnoreCase("local")) {
                if (platform.toLowerCase().contains("windows")) {
                    System.setProperty("webdriver.ie.driver",
                            driverProps.getProperty("ie.driver.win64.path"));
                } else {
                    logger.info("Browser not compatible with the given platform");
                }
            }

            // set ie capabilities
            caps = DesiredCapabilities.internetExplorer();
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.requireWindowFocus();
            caps.setCapability("CAPABILITY_BROWSER_SIZE ", "1690X1000");
            webDriver.set(new InternetExplorerDriver(options.merge(caps)));
        }
    }

    /**
     * getDriver method with retrieve the active WebDriver
     *
     * @return WebDriver
     */
    public final WebDriver getDriver() {
        return webDriver.get();
    }

    /**
     * getCurrentDriver method will return the active WebDriver
     *
     * @return WebDriver
     */
    public WebDriver getCurrentDriver() {
        return getInstance().getDriver();
    }

    /**
     * driverWait method pauses the driver in seconds
     *
     * @param seconds to pause
     */
    public void driverWait(long seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            logger.info("Timed out waiting " + seconds + " secs");
        }
    }

    /**
     * driverRefresh method reloads the current browser page
     */
    public void driverRefresh() {
        getCurrentDriver().navigate().refresh();
    }

    /**
     * closeDriver method quits the current active driver
     */
    public void closeDriver() {
        try {
            getCurrentDriver().quit();
        } catch (Exception e) {
            logger.info("Something went wrong " + e);
        }
    }
}
