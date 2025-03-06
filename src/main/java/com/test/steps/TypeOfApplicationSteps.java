package com.test.steps;

import com.test.pages.TypeOfApplicationPage;
import org.openqa.selenium.WebDriver;

public class TypeOfApplicationSteps {
    private final TypeOfApplicationPage typeOfApplicationPage;

    public TypeOfApplicationSteps(WebDriver driver) {
        typeOfApplicationPage = new TypeOfApplicationPage(driver);
    }

    public TypeOfApplicationPage chooseApplication(String application) {
        typeOfApplicationPage.chooseApplicationType(application);
        return typeOfApplicationPage;
    }
}
