package com.test.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.test.constants.TestConstants.*;

@Log4j2
public class ApplicantFormPage {

    private WebDriver driver;

    private static final String INPUT_FIELD_XPATH = "//div[label[contains(text(), '%s')]]/following-sibling::input";
    private static final String BUTTON_XPATH = "//button[contains(text(), '%s')]";

    public ApplicantFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Applicant form page is initialized");
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

    @Step("Filling applicant form: {labelText}: '{value}'")
    public void fillField(String labelText, String value) {
        log.info("Filling field '{}' with '{}'", labelText, value);
        WebElement field = getInputField(labelText);
        field.sendKeys(value);
    }

    public void fillApplicantData(String lastName, String firstName, String middleName,
                                  String telephone, String passport, String address) {
        log.info("Filling out applicant data");
        fillField(FORM_LASTNAME, lastName);
        fillField(FORM_FIRSTNAME, firstName);
        fillField(FORM_MIDDLENAME, middleName);
        fillField(FORM_PHONE, telephone);
        fillField(FORM_PASSPORT, passport);
        fillField(FORM_ADDRESS, address);
        log.info("Filling out applicant data completed");
    }

    @Step("Clicking the '{buttonText}' button")
    public void clickButton(String buttonText) {
        log.info("Clicking button '{}'", buttonText);
        getButton(buttonText).click();
    }
}
