package com.test.pages;

import com.test.models.BirthData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BirthApplicationPage {

    private WebDriver driver;

    private static final String INPUT_FIELD_XPATH = "//div[label[contains(text(), '%s')]]/following-sibling::input";
    private static final String BUTTON_XPATH = "//button[contains(text(), '%s')]";

    public BirthApplicationPage(WebDriver driver) {
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

    public void fillBirthApplicationData(BirthData birthData) {
        fillField("Место рождения", birthData.getBirthAddress());
        fillField("Мать", birthData.getMother());
        fillField("Отец", birthData.getFather());
        fillField("Бабушка", birthData.getGrandma());
        fillField("Дедушка", birthData.getGrandpa());
    }

    public void clickButton(String buttonText) {
        getButton(buttonText).click();
    }
}
