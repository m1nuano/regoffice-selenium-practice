package com.test.steps;

import com.test.pages.CitizenFormPage;
import org.openqa.selenium.WebDriver;

import static com.test.constants.TestConstants.BUTTON_NEXT;

public class CitizenSteps {
    private final CitizenFormPage citizenFormPage;

    public CitizenSteps(WebDriver driver) {
        citizenFormPage = new CitizenFormPage(driver);
    }

    public CitizenFormPage fillCitizenFormPage(String lastName, String firstName, String middleName,
                                               String dateOfBirth, String numberOfPassports, String gender, String residenceAddress) {
        citizenFormPage.fillCitizenData(
                lastName,
                firstName,
                middleName,
                dateOfBirth,
                numberOfPassports,
                gender,
                residenceAddress
        );
        citizenFormPage.clickButton(BUTTON_NEXT);
        return citizenFormPage;
    }
}
