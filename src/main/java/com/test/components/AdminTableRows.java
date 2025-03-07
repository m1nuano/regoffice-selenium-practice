package com.test.components;

import com.test.waiters.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.test.constants.TestConstants.*;

public class AdminTableRows {
    private WebElement rowElement;
    private Waiters waiters;

    private static final By REQUEST_NUMBER = By.cssSelector("td:nth-of-type(1)");
    private static final By APPLICANT_NAME = By.cssSelector("td:nth-of-type(2)");
    private static final By APPLICATION_TYPE = By.cssSelector("td:nth-of-type(3)");
    private static final By APPLICATION_TIME = By.cssSelector("td:nth-of-type(4)");
    private static final By APPLICATION_STATUS = By.cssSelector("td:nth-of-type(5)");

    private static final String BUTTON_LOCATOR_TEMPLATE = "td:nth-of-type(6) button svg[data-testid='%s']";

    public AdminTableRows(WebDriver driver, WebElement rowElement) {
        this.rowElement = rowElement;
        this.waiters = new Waiters(driver);
    }

    @Step("Getting application number from row of table")
    public String getRequestNumber() {
        return rowElement.findElement(REQUEST_NUMBER).getText();
    }

    @Step("Getting applicant name from row of table")
    public String getApplicantName() {
        return rowElement.findElement(APPLICANT_NAME).getText();
    }

    @Step("Getting application type from row of table")
    public String getApplicationType() {
        return rowElement.findElement(APPLICATION_TYPE).getText();
    }

    @Step("Getting application creation time from row of table")
    public String getApplicationTime() {
        return rowElement.findElement(APPLICATION_TIME).getText();
    }

    @Step("Getting application status from row of table")
    public String getApplicationStatus() {
        return rowElement.findElement(APPLICATION_STATUS).getText();
    }

    @Step("Choosing action '{actionType}' and choosing expected status '{expectedStatus}' for application")
    public void performAction(String actionType, String expectedStatus) {
        By buttonLocator = By.cssSelector(String.format(BUTTON_LOCATOR_TEMPLATE, actionType));
        rowElement.findElement(buttonLocator).click();
        waiters.waitForTextAppears(APPLICATION_STATUS, expectedStatus, Duration.ofSeconds(LONG_INTERVAL));
    }
}
