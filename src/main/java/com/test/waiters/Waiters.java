package com.test.waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {

    WebDriver driver;
    WebDriverWait wait;

    public Waiters(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForStatusChange(By element, String expectedElement, Duration duration) {
        wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(element, expectedElement));
    }

    public void waitForElementToBeVisible(By element, Duration duration) {
        wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
