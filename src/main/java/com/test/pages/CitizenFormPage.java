package com.test.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.test.constants.TestConstants.*;

@Log4j2
public class CitizenFormPage {

    private WebDriver driver;

    private static final String INPUT_FIELD_XPATH = "//div[label[contains(text(), '%s')]]/following-sibling::input";
    private static final String BUTTON_XPATH = "//button[contains(text(), '%s')]";

    public CitizenFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Citizen form page is initialized");
    }

    private WebElement getInputField(String labelText) {
        String xpath = String.format(INPUT_FIELD_XPATH, labelText);
        log.debug("Finding input-field with text '{}': XPath = {}", labelText, xpath);
        return driver.findElement(By.xpath(xpath));
    }

    private WebElement getButton(String buttonText) {
        String xpath = String.format(BUTTON_XPATH, buttonText);
        log.debug("Finding button with text '{}': XPath = {}", buttonText, xpath);
        return driver.findElement(By.xpath(xpath));
    }

    @Step("Filling citizen form: {labelText}: '{value}'")
    public void fillField(String labelText, String value) {
        log.info("Filling field '{}' with '{}'", labelText, value);
        WebElement field = getInputField(labelText);
        field.sendKeys(value);
    }

    public void fillCitizenData(String lastName, String firstName, String middleName,
                                String dateOfBirth, String numberOfPassports, String gender, String residenceAddress) {
        log.info("Filling out citizen data");
        fillField(FORM_LASTNAME, lastName);
        fillField(FORM_FIRSTNAME, firstName);
        fillField(FORM_MIDDLENAME, middleName);
        fillField(FORM_DATEOFBIRTH, dateOfBirth);
        fillField(FORM_PASSPORT, numberOfPassports);
        fillField(FORM_GENDER, gender);
        fillField(FORM_ADDRESS, residenceAddress);
        log.info("Filling out citizen data completed");
    }

    @Step("Clicking the '{buttonText}' button")
    public void clickButton(String buttonText) {
        log.info("Clicking button '{}'", buttonText);
        getButton(buttonText).click();
    }
}
