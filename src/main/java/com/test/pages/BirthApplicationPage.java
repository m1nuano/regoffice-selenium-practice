package com.test.pages;

import com.test.models.BirthData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BirthApplicationPage {
    @FindBy(xpath = "//div[label[contains(text(), 'Место рождения')]]/following-sibling::input")
    private WebElement placeOfBirthField;
    @FindBy(xpath = "//div[label[contains(text(), 'Мать')]]/following-sibling::input")
    private WebElement MotherField;
    @FindBy(xpath = "//div[label[contains(text(), 'Отец')]]/following-sibling::input")
    private WebElement FatherField;
    @FindBy(xpath = "//div[label[contains(text(), 'Бабушка')]]/following-sibling::input")
    private WebElement GrandmaField;
    @FindBy(xpath = "//div[label[contains(text(), 'Дедушка')]]/following-sibling::input")
    private WebElement GrandpaField;

    @FindBy(xpath = "//button[contains(text(), 'Завершить')]")
    private WebElement finishButton;

    public BirthApplicationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillBirthApplicationData(BirthData birthData) {
        placeOfBirthField.sendKeys(birthData.getBirthAddress());
        MotherField.sendKeys(birthData.getMother());
        FatherField.sendKeys(birthData.getFather());
        GrandmaField.sendKeys(birthData.getGrandma());
        GrandpaField.sendKeys(birthData.getGrandpa());
    }

    public void submitApplication() {
        finishButton.click();
    }
}
