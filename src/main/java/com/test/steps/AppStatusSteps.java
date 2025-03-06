package com.test.steps;

import com.test.pages.ApplicationStatusPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class AppStatusSteps {
    private final ApplicationStatusPage appStatusPage;

    public AppStatusSteps(WebDriver driver) {
        appStatusPage = new ApplicationStatusPage(driver);
    }

    @Step("Checking the application number is visible")
    public boolean checkAppStatusIsPresent() {
       return appStatusPage.isApplicationNumberVisible();
    }

    @Step("Pressing {action} button")
    public void performAction(String action) {
        appStatusPage.clickButton(action);
    }
}
