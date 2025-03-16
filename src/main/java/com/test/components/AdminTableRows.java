package com.test.components;

import com.test.waiters.Waiters;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.test.constants.TestConstants.*;

@Log4j2
public class AdminTableRows {
    private WebElement rowElement;
    private Waiters waiters;

    private static final By REQUEST_NUMBER = By.cssSelector("td:nth-of-type(1)");
    private static final By APPLICATION_STATUS = By.cssSelector("td:nth-of-type(5)");

    private static final String BUTTON_LOCATOR_TEMPLATE = "td:nth-of-type(6) button svg[data-testid='%s']";

    public AdminTableRows(WebDriver driver, WebElement rowElement) {
        this.rowElement = rowElement;
        this.waiters = new Waiters(driver);
    }

    @Step("Getting application number from row of table")
    public String getRequestNumber() {
        String requestNumber = rowElement.findElement(REQUEST_NUMBER).getText();
        log.info("The application number was received: {}", requestNumber);
        return requestNumber;
    }

    @Step("Getting application status from row of table")
    public String getApplicationStatus() {
        String applicationStatus = rowElement.findElement(APPLICATION_STATUS).getText();
        log.info("The status of the application was received: {}", applicationStatus);
        return applicationStatus;
    }

    @Step("Choosing action '{actionType}' and choosing expected status '{expectedStatus}' for application")
    public void performAction(String actionType, String expectedStatus) {
        By buttonLocator = By.cssSelector(String.format(BUTTON_LOCATOR_TEMPLATE, actionType));
        log.info("Search and pressing the action button '{}'", actionType);

        try {
            rowElement.findElement(buttonLocator).click();
            log.info("Action button '{}' pressed", actionType);
            waiters.waitForTextAppears(APPLICATION_STATUS, expectedStatus, Duration.ofSeconds(LONG_INTERVAL));
            log.info("The status is successfully changed to '{}'", expectedStatus);
        } catch (Exception e) {
            log.error("An error in performing an action '{}': {}", actionType, e.getMessage(), e);
            throw e;
        }
    }
}
