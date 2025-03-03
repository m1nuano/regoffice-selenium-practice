package com.test.pages;

import com.test.components.AdminTableRows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class AdminTablePage {
    private WebDriver driver;

    public AdminTablePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<AdminTableRows> getAllRequestRows() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("tr:not(.MuiTableRow-head)")));
        return rows.stream()
                .map(row -> new AdminTableRows(driver, row))
                .collect(Collectors.toList());
    }
}
