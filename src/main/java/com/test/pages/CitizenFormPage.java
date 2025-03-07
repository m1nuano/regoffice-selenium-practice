package com.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.test.constants.TestConstants.*;

public class CitizenFormPage {

    private WebDriver driver;

    private static final String INPUT_FIELD_XPATH = "//div[label[contains(text(), '%s')]]/following-sibling::input";
    private static final String BUTTON_XPATH = "//button[contains(text(), '%s')]";

    public CitizenFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebElement getInputField(String labelText) {
        String xpath = String.format(INPUT_FIELD_XPATH, labelText);
        return driver.findElement(By.xpath(xpath));
    }

    private WebElement getButton(String buttonText) {
        String xpath = String.format(BUTTON_XPATH, buttonText);
        return driver.findElement(By.xpath(xpath));
    }

    @Step("Filling citizen form: {labelText}: '{value}'")
    public void fillField(String labelText, String value) {
        WebElement field = getInputField(labelText);
        field.sendKeys(value);
    }

    public void fillCitizenData(String lastName, String firstName, String middleName,
                                String dateOfBirth, String numberOfPassports, String gender, String residenceAddress) {
        fillField(FORM_LASTNAME, lastName);
        fillField(FORM_FIRSTNAME, firstName);
        fillField(FORM_MIDDLENAME, middleName);
        fillField(FORM_DATEOFBIRTH, dateOfBirth);
        fillField(FORM_PASSPORT, numberOfPassports);
        fillField(FORM_GENDER, gender);
        fillField(FORM_ADDRESS, residenceAddress);
    }

    @Step("Clicking the '{buttonText}' button")
    public void clickButton(String buttonText) {
        getButton(buttonText).click();
    }
}
