package com.test.steps;

import com.test.models.MarriageData;
import com.test.pages.MarriageApplicationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.test.constants.TestConstants.BUTTON_FINISH;

public class MarriageAppSteps {
    private final MarriageApplicationPage marriageApplicationPage;

    public MarriageAppSteps(WebDriver driver) {
        marriageApplicationPage = new MarriageApplicationPage(driver);
    }

    @Step("Filling marriage application form page with correct values")
    public MarriageApplicationPage fillMarriageApplicationPage(MarriageData marriageData) {
        marriageApplicationPage.fillApplicationData(marriageData);
        marriageApplicationPage.clickButton(BUTTON_FINISH);
        return marriageApplicationPage;
    }
}
