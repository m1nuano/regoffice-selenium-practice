package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicantFormPage {

    @FindBy(xpath = "//div[label[contains(text(), 'Фамилия')]]/following-sibling::input")
    private WebElement lastNameField;
    @FindBy(xpath = "//div[label[contains(text(), 'Имя')]]/following-sibling::input")
    private WebElement firstNameField;
    @FindBy(xpath = "//div[label[contains(text(), 'Отчество')]]/following-sibling::input")
    private WebElement middleNameField;
    @FindBy(xpath = "//div[label[contains(text(), 'Телефон')]]/following-sibling::input")
    private WebElement telephoneField;
    @FindBy(xpath = "//div[label[contains(text(), 'Номер паспорта')]]/following-sibling::input")
    private WebElement passportField;
    @FindBy(xpath = "//div[label[contains(text(), 'Адрес прописки')]]/following-sibling::input")
    private WebElement addressField;
    @FindBy(xpath = "//button[contains(text(), 'Далее')]")
    private WebElement nextButton;

    public ApplicantFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillApplicantData(String lastName, String firstName, String middleName,
                                  String telephone, String passport, String address) {
        lastNameField.sendKeys(lastName);
        firstNameField.sendKeys(firstName);
        middleNameField.sendKeys(middleName);
        telephoneField.sendKeys(telephone);
        passportField.sendKeys(passport);
        addressField.sendKeys(address);
    }

    public void clickNext() {
        nextButton.click();
    }
}
