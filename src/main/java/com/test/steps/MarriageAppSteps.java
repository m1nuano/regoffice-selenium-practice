package com.test.steps;

import com.test.models.MarriageData;
import com.test.pages.MarriageApplicationPage;
import org.openqa.selenium.WebDriver;

import static com.test.constants.TestConstants.BUTTON_FINISH;

public class MarriageAppSteps {
    private final MarriageApplicationPage marriageApplicationPage;

    public MarriageAppSteps(WebDriver driver) {
        marriageApplicationPage = new MarriageApplicationPage(driver);
    }

    public MarriageApplicationPage fillMarriageApplicationPage(MarriageData marriageData) {
        marriageApplicationPage.fillApplicationData(marriageData);
        marriageApplicationPage.clickButton(BUTTON_FINISH);
        return marriageApplicationPage;
    }
}
