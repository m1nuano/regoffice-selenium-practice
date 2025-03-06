package com.test.steps;

import com.test.pages.MainPage;
import org.openqa.selenium.WebDriver;

public class MainPageSteps {
    private final MainPage mainPage;

    public MainPageSteps(WebDriver driver) {
        mainPage = new MainPage(driver);
    }

    public MainPage chooseRole(String role) {
        mainPage.loginAs(role);
        return mainPage;
    }
}
