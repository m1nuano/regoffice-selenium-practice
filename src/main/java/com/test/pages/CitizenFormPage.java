package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CitizenFormPage {

    @FindBy(xpath = "//div[label[contains(text(), 'Фамилия')]]/following-sibling::input")
    private WebElement lastNameField;
    @FindBy(xpath = "//div[label[contains(text(), 'Имя')]]/following-sibling::input")
    private WebElement firstNameField;
    @FindBy(xpath = "//div[label[contains(text(), 'Отчество')]]/following-sibling::input")
    private WebElement middleNameField;
    @FindBy(xpath = "//div[label[contains(text(), 'Дата рождения')]]/following-sibling::input")
    private WebElement dateOfBirthField;
    @FindBy(xpath = "//div[label[contains(text(), 'Номер паспорта')]]/following-sibling::input")
    private WebElement numberOfPassportsField;
    @FindBy(xpath = "//div[label[contains(text(), 'Пол')]]/following-sibling::input")
    private WebElement genderField;
    @FindBy(xpath = "//div[label[contains(text(), 'Адрес прописки')]]/following-sibling::input")
    private WebElement residenceAdressField;
    @FindBy(xpath = "//button[contains(text(), 'Далее')]")
    private WebElement nextButton;

    public CitizenFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillCitizenData(String lastName, String firstName, String middleName,
                                String dateOfBirth, String numberOfPassports, String gender, String residenceAdress) {
        lastNameField.sendKeys(lastName);
        firstNameField.sendKeys(firstName);
        middleNameField.sendKeys(middleName);
        dateOfBirthField.sendKeys(dateOfBirth);
        numberOfPassportsField.sendKeys(numberOfPassports);
        genderField.sendKeys(gender);
        residenceAdressField.sendKeys(residenceAdress);
    }

    public void clickNext(){
        nextButton.click();
    }
}
