package com.test.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

@Log4j2
public class WebDriverSingleton {
    private static WebDriver driver;
    private WebDriverSingleton() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            log.info("WebDriver initialisation (EdgeDriver)");
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.setCapability("useChromium", true);
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            driver = new EdgeDriver(options);
            log.info("WebDriver has been successfully created");
        } else {
            log.debug("Return the existing copy of Webdriver");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            log.info("Closing WebDriver");
            driver.quit();
            driver = null;
            log.info("WebDriver is successfully closed");
        } else {
            log.warn("Attempt to close Webdriver, but it is already null");
        }
    }
}
