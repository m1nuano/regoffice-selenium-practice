package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.test.constants.TestConstants.*;

public class ApplicantFormPage {

    private WebDriver driver;

    private static final String INPUT_FIELD_XPATH = "//div[label[contains(text(), '%s')]]/following-sibling::input";
    private static final String BUTTON_XPATH = "//button[contains(text(), '%s')]";

    public ApplicantFormPage(WebDriver driver) {
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

    public void fillField(String labelText, String value) {
        WebElement field = getInputField(labelText);
        field.sendKeys(value);
    }

    public void fillApplicantData(String lastName, String firstName, String middleName,
                                  String telephone, String passport, String address) {
        fillField(FORM_LASTNAME, lastName);
        fillField(FORM_FIRSTNAME, firstName);
        fillField(FORM_MIDDLENAME, middleName);
        fillField(FORM_PHONE, telephone);
        fillField(FORM_PASSPORT, passport);
        fillField(FORM_ADDRESS, address);
    }

    public void clickButton(String buttonText) {
        getButton(buttonText).click();
    }
}
