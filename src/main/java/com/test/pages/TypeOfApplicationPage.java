package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TypeOfApplicationPage {

    @FindBy(xpath = "//button[contains(text(), 'Регистрация брака')]")
    private WebElement marryButton;

    @FindBy(xpath = "//button[contains(text(), 'Регистрация рождения')]")
    private WebElement birthButton;

    @FindBy(xpath = "//button[contains(text(), 'Регистрация смерти')]")
    private WebElement deathButton;

    public TypeOfApplicationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickMarryButton() {
        marryButton.click();
    }

    public void clickBirthButton() {
        birthButton.click();
    }

    public void clickDeathButton() {
        deathButton.click();
    }
}
