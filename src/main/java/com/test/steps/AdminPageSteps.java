package com.test.steps;

import com.test.pages.AdminFormPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.test.constants.TestConstants.BUTTON_NEXT;

public class AdminPageSteps {
    private final AdminFormPage adminFormPage;

    public AdminPageSteps(WebDriver driver) {
        adminFormPage = new AdminFormPage(driver);
    }

    @Step("filling admin form page with correct values")
    public AdminFormPage fillAdminFormPage(String lastName, String firstName, String middleName,
                                           String telephone, String passport, String dateOfBirth) {
        adminFormPage.fillAdminData(
                lastName,
                firstName,
                middleName,
                telephone,
                passport,
                dateOfBirth
        );
        adminFormPage.clickButton(BUTTON_NEXT);
        return adminFormPage;
    }
}
