package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver driver;

    private static final String BUTTON_XPATH = "//button[contains(text(), 'Войти как %s')]";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginAs(String role) {
        String xpath = String.format(BUTTON_XPATH, role);
        WebElement button = driver.findElement(By.xpath(xpath));
        button.click();
    }
}
