package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.time.Duration;

public class WebDriverFactory {
    public static final WebDriverFactory instance = new WebDriverFactory();
    public static ThreadLocal<String> threadedBrowser = new ThreadLocal<>();

    private WebDriverFactory() {}

    private static final Logger log = LogManager.getLogger(WebDriverFactory.class.getName());
    public static WebDriverFactory getInstance() {
        return instance;
    }
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    public WebDriver getDriver(String browser) {
        WebDriver driver = null;
        setDriver(browser);
        threadedBrowser.set(browser);
        if (threadDriver.get() == null) {
            try {
                if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
                    FirefoxOptions firefoxOptions = setFirefoxOptions();
                    driver = new FirefoxDriver(firefoxOptions);
                    threadDriver.set(driver);
                }
                if (browser.equalsIgnoreCase(Constants.CHROME)) {
                    ChromeOptions chromeOptions = setChromeOptions();
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    threadDriver.set(driver);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            threadDriver.get().manage().window().maximize();
        }
        return threadDriver.get();
    }

    public String getBrowser() {
        return threadedBrowser.get();
    }

    private void setDriver(String browser) {
        String driverPath = "";
        String os = Constants.OS_NAME.toLowerCase().substring(0,3);
        String directory = Constants.USER_DIRECTORY + Constants.DRIVER_DIRECTORY;
        String driverKey = "";
        String driverValue = "";

        if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
            driverKey = Constants.GECKO_DRIVER_KEY;
            driverValue = Constants.GECKO_DRIVER_VALUE;
        } else if (browser.equalsIgnoreCase(Constants.CHROME)) {
            driverKey = Constants.CHROME_DRIVER_KEY;
            driverValue = Constants.CHROME_DRIVER_VALUE;
        } else {
            log.info("Browser type not supported");
        }

        driverPath = directory + driverValue + (os.equals("win") ? ".exe" : "");
        log.info("Driver Binary :: " + driverPath);
        System.setProperty(driverKey, driverPath);
    }

    private FirefoxOptions setFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.BROWSER_VERSION, false);
        return options;
    }

    private ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        return options;
    }

    public void quitDriver() {
        threadDriver.get().quit();
        threadDriver.set(null);
    }
}
