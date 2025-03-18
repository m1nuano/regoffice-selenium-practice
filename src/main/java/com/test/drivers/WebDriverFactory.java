package com.test.drivers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void createDriver(String browser) throws Exception {
        URL url = new URL("http://localhost:4444/wd/hub");
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", false);

        switch(browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                chromeOptions.setCapability("browserVersion", "128.0");
                chromeOptions.setCapability("selenoid:options", selenoidOptions);
                driver.set(new RemoteWebDriver(url, chromeOptions));
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.setCapability("browserVersion", "125.0");
                firefoxOptions.setCapability("selenoid:options", selenoidOptions);
                driver.set(new RemoteWebDriver(url, firefoxOptions));
                break;
            case "opera":
                ChromeOptions operaOptions = new ChromeOptions();
                operaOptions.setCapability("browserName", "opera");
                operaOptions.setCapability("browserVersion", "109.0");
                operaOptions.setCapability("selenoid:options", selenoidOptions);
                driver.set(new RemoteWebDriver(url, operaOptions));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            log.info("Closing WebDriver");
            driver.get().quit();
            driver.remove();
            log.info("WebDriver is successfully closed");
        }
    }
}