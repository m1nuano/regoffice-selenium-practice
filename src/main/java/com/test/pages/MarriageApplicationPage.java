package com.test.pages;

import com.test.models.MarriageData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarriageApplicationPage {

    @FindBy(xpath = "//div[label[contains(text(), 'Дата регистрации')]]/following-sibling::input")
    private WebElement dateOfRegistrationField;
    @FindBy(xpath = "//div[label[contains(text(), 'Новая фамилия')]]/following-sibling::input")
    private WebElement newFamilyField;
    @FindBy(xpath = "//div[label[contains(text(), 'Фамилия супруга')]]/following-sibling::input")
    private WebElement lastNameOfSpouseField;
    @FindBy(xpath = "//div[label[contains(text(), 'Имя супруга')]]/following-sibling::input")
    private WebElement firstNameOfSpouseField;
    @FindBy(xpath = "//div[label[contains(text(), 'Отчество супруга')]]/following-sibling::input")
    private WebElement middleNameOfSpouseField;
    @FindBy(xpath = "//div[label[contains(text(), 'Дата рождения супруга')]]/following-sibling::input")
    private WebElement dateOfBirthOfSpouseField;
    @FindBy(xpath = "//div[label[contains(text(), 'Номер паспорта супруга')]]/following-sibling::input")
    private WebElement passportNumberOfSpouseField;
    @FindBy(xpath = "//button[contains(text(), 'Завершить')]")
    private WebElement finishButton;

    public MarriageApplicationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillApplicationData(MarriageData marriageData) {
        dateOfRegistrationField.sendKeys(marriageData.getDateOfRegistration());
        newFamilyField.sendKeys(marriageData.getNewFamily());
        lastNameOfSpouseField.sendKeys(marriageData.getLastNameOfSpouse());
        firstNameOfSpouseField.sendKeys(marriageData.getFirstNameOfSpouse());
        middleNameOfSpouseField.sendKeys(marriageData.getMiddleNameOfSpouse());
        dateOfBirthOfSpouseField.sendKeys(marriageData.getDateOfBirthOfSpouse());
        passportNumberOfSpouseField.sendKeys(marriageData.getPassportNumberOfSpouse());
    }

    public void submitApplication() {
        finishButton.click();
    }
}
