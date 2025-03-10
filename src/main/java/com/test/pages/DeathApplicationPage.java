package com.test.pages;

import com.test.models.DeathData;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class DeathApplicationPage {

    private WebDriver driver;

    private static final String INPUT_FIELD_XPATH = "//div[label[contains(text(), '%s')]]/following-sibling::input";
    private static final String BUTTON_XPATH = "//button[contains(text(), '%s')]";

    public DeathApplicationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Death application form page is initialized");
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

    @Step("Filling death application data: {labelText}: '{value}'")
    public void fillField(String labelText, String value) {
        log.info("Filling field '{}' with '{}'", labelText, value);
        WebElement field = getInputField(labelText);
        field.sendKeys(value);
    }

    public void fillDeathApplicationData(DeathData deathData){
        log.info("Filling out death application data");
        fillField("Дата смерти", deathData.getDateOfDeath());
        fillField("Место смерти", deathData.getPlaceOfDeath());
        log.info("Filling out death application data completed");
    }

    @Step("Clicking the '{buttonText}' button")
    public void clickButton(String buttonText) {
        log.info("Clicking button '{}'", buttonText);
        getButton(buttonText).click();
    }
}
