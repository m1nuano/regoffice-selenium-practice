package com.test.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminTableRows {
    private WebElement rowElement;
    private WebDriver driver;

    public AdminTableRows(WebDriver driver, WebElement rowElement) {
        this.driver = driver;
        this.rowElement = rowElement;
    }

    public String getRequestNumber() {
        return rowElement.findElement(By.cssSelector("td:nth-of-type(1)")).getText();
    }

    public String getApplicantName() {
        return rowElement.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
    }

    public String getApplicationType() {
        return rowElement.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
    }

    public String getApplicationTime() {
        return rowElement.findElement(By.cssSelector("td:nth-of-type(4)")).getText();
    }

    public String getApplicationStatus() {
        return rowElement.findElement(By.cssSelector("td:nth-of-type(5)")).getText();
    }

    public void approve() {
        WebElement approveButton = rowElement.findElement(By.cssSelector("td:nth-of-type(6) button svg[data-testid='ThumbUpIcon']"));
        approveButton.click();
        waitForStatusChange("Одобрена");
    }

    public void reject() {
        WebElement rejectButton = rowElement.findElement(By.cssSelector("td:nth-of-type(6) button svg[data-testid='ThumbDownIcon']"));
        rejectButton.click();
        waitForStatusChange("Отклонена");
    }


    private void waitForStatusChange(String expectedStatus) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("td:nth-of-type(5)"), expectedStatus));
    }
}
