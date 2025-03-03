package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(xpath = "//button[contains(text(), 'Войти как пользователь')]")
    private WebElement userButton;

    @FindBy(xpath = "//button[contains(text(), 'Войти как администратор')]")
    private WebElement adminButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void loginAsUser() {
        userButton.click();
    }

    public void loginAsAdmin() {
        adminButton.click();
    }
}
