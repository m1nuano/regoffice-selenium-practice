package com.test.waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waiters {

    WebDriver driver;
    WebDriverWait wait;

    public Waiters(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForTextAppears(By element, String expectedText, Duration duration) {
        wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(element, expectedText));
    }

    public List<WebElement> waitForElementsToBeVisible(By elementLocation, Duration duration) {
        wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocation));
        return driver.findElements(elementLocation);
    }
}
