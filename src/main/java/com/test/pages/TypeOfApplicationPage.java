package com.test.pages;

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

    public void chooseApplicationType(String type) {
        String xpath = String.format(BUTTON_XPATH, type);
        WebElement button = driver.findElement(By.xpath(xpath));
        button.click();
    }
}
