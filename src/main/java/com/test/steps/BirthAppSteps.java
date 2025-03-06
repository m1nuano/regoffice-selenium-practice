package com.test.steps;

import com.test.models.BirthData;
import com.test.pages.BirthApplicationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.test.constants.TestConstants.BUTTON_FINISH;

public class BirthAppSteps {
    private final BirthApplicationPage birthApplicationPage;

    public BirthAppSteps(WebDriver driver) {
        birthApplicationPage = new BirthApplicationPage(driver);
    }

    @Step("Filling birth application form page with correct values")
    public BirthApplicationPage fillBirthApplicationPage(BirthData birthData) {
        birthApplicationPage.fillBirthApplicationData(birthData);
        birthApplicationPage.clickButton(BUTTON_FINISH);
        return birthApplicationPage;
    }
}
