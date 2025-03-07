package com.test.steps;

import com.test.pages.ApplicantFormPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.test.constants.TestConstants.BUTTON_NEXT;

public class ApplicantPageSteps {
    private final ApplicantFormPage applicantFormPage;

    public ApplicantPageSteps(WebDriver driver) {
        applicantFormPage = new ApplicantFormPage(driver);
    }

    @Step("Filling applicant form page with correct values: " +
            "last name: '{lastName}', " +
            "first name: '{firstName}', " +
            "middle name: '{middleName}', " +
            "telephone number: '{telephone}', " +
            "passport number: '{passport}', " +
            "address: '{address}'")
    public ApplicantFormPage fillApplicantFormPage(String lastName, String firstName, String middleName,
                                                   String telephone, String passport, String address){
        applicantFormPage.fillApplicantData(
                lastName,
                firstName,
                middleName,
                telephone,
                passport,
                address
        );
        applicantFormPage.clickButton(BUTTON_NEXT);
        return applicantFormPage;
    }
}
