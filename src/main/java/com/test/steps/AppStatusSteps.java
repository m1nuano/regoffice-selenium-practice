package com.test.steps;

import com.test.pages.ApplicationStatusPage;
import org.openqa.selenium.WebDriver;

public class AppStatusSteps {
    private final ApplicationStatusPage appStatusPage;

    public AppStatusSteps(WebDriver driver) {
        appStatusPage = new ApplicationStatusPage(driver);
    }

    public boolean checkAppStatusIsPresent() {
       return appStatusPage.isApplicationNumberVisible();
    }

    public void performAction(String action) {
        appStatusPage.clickButton(action);
    }
}
