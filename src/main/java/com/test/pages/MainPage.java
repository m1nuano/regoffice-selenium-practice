package com.test.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class MainPage {

    private WebDriver driver;

    private static final String BUTTON_XPATH = "//button[contains(text(), 'Войти как %s')]";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Main page is initialized");
    }

    @Step("Clicking the '{role}' button")
    public void loginAs(String role) {
        String xpath = String.format(BUTTON_XPATH, role);
        WebElement button = driver.findElement(By.xpath(xpath));
        log.info("Button 'Войти как {}' is found. Clicking.", role);
        button.click();
    }
}
