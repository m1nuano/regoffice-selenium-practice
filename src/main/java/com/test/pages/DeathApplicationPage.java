package com.test.pages;

import com.test.models.DeathData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class DeathApplicationPage {

    private WebDriver driver;

    private static final String INPUT_FIELD_XPATH = "//div[label[contains(text(), '%s')]]/following-sibling::input";
    private static final String BUTTON_XPATH = "//button[contains(text(), '%s')]";

    public DeathApplicationPage(WebDriver driver) {
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

    public void fillDeathApplicationData(DeathData deathData){
        fillField("Дата смерти", deathData.getDateOfDeath());
        fillField("Место смерти", deathData.getPlaceOfDeath());
    }

    public void clickButton(String buttonText) {
        getButton(buttonText).click();
    }
}
