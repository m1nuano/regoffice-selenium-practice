package com.test.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverSingleton {
    private static WebDriver driver;

    private WebDriverSingleton() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
