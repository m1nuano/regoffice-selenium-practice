package com.test.pages;

import com.test.components.AdminTableRows;
import com.test.waiters.Waiters;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.test.constants.TestConstants.LONG_INTERVAL;

@Log4j2
public class AdminTablePage {

    private static final By tableRows = By.cssSelector("tr:not(.MuiTableRow-head)");

    private WebDriver driver;
    private Waiters waiters;

    public AdminTablePage(WebDriver driver) {
        this.driver = driver;
        this.waiters = new Waiters(driver);
        log.info("Admin table page is initialized");
    }

    @Step("Getting all request rows")
    public List<AdminTableRows> getAllRequestRows() {
        Duration duration = Duration.ofSeconds(LONG_INTERVAL);
        log.info("Driver setting explicit wait to: {}", duration.getSeconds());
        List<WebElement> rows = waiters.waitForElementsToBeVisible(tableRows, duration);
        log.info("Number of  lines: {}", rows.size());
        return rows.stream()
                .map(row -> new AdminTableRows(driver, row))
                .collect(Collectors.toList());
    }
}
