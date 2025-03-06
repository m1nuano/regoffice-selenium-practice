package com.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class TypeOfApplicationPage {

    private WebDriver driver;

    private static final String BUTTON_XPATH = "//button[contains(text(), '%s')]";


    public TypeOfApplicationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Find the button '{type}' by xpath")
    public void chooseApplicationType(String type) {
        String xpath = String.format(BUTTON_XPATH, type);
        WebElement button = driver.findElement(By.xpath(xpath));
        button.click();
    }
}
