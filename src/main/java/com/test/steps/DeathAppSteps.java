package com.test.steps;

import com.test.models.DeathData;
import com.test.pages.DeathApplicationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.test.constants.TestConstants.BUTTON_FINISH;

public class DeathAppSteps {
    private final DeathApplicationPage deathApplicationPage;

    public DeathAppSteps(WebDriver driver) {
        deathApplicationPage = new DeathApplicationPage(driver);
    }

    @Step("filling death_application form page with correct values")
    public DeathApplicationPage fillDeathApplicationPage(DeathData deathData) {
        deathApplicationPage.fillDeathApplicationData(deathData);
        deathApplicationPage.clickButton(BUTTON_FINISH);
        return deathApplicationPage;
    }
}
