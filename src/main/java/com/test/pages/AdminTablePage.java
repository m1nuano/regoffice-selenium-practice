package com.test.pages;

import com.test.components.AdminTableRows;
import com.test.waiters.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.test.constants.TestConstants.LONG_INTERVAL;

public class AdminTablePage {

    private static final By tableRows = By.cssSelector("tr:not(.MuiTableRow-head)");

    private WebDriver driver;
    private Waiters waiters;

    public AdminTablePage(WebDriver driver) {
        this.driver = driver;
        this.waiters = new Waiters(driver);
    }

    @Step("Getting all request rows")
    public List<AdminTableRows> getAllRequestRows() {
        List<WebElement> rows = waiters.waitForElementsToBeVisible(tableRows, Duration.ofSeconds(LONG_INTERVAL));
        return rows.stream()
                .map(row -> new AdminTableRows(driver, row))
                .collect(Collectors.toList());
    }
}
