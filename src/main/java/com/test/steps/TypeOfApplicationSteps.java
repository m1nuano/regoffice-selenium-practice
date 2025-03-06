package com.test.steps;

import com.test.pages.TypeOfApplicationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class TypeOfApplicationSteps {
    private final TypeOfApplicationPage typeOfApplicationPage;

    public TypeOfApplicationSteps(WebDriver driver) {
        typeOfApplicationPage = new TypeOfApplicationPage(driver);
    }

    @Step("Choosing '{application}'")
    public TypeOfApplicationPage chooseApplication(String application) {
        typeOfApplicationPage.chooseApplicationType(application);
        return typeOfApplicationPage;
    }
}
