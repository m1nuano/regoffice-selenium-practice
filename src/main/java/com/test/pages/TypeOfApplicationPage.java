package com.test.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class TypeOfApplicationPage {

    private WebDriver driver;

    private static final String BUTTON_XPATH = "//button[contains(text(), '%s')]";


    public TypeOfApplicationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Type of application page is initialized");
    }

    @Step("Find the button '{type}' by xpath")
    public void chooseApplicationType(String type) {
        String xpath = String.format(BUTTON_XPATH, type);
        log.info("Search for a button type of application: '{}'", type);

        try {
            WebElement button = driver.findElement(By.xpath(xpath));
            log.info("Button '{}' is found. Clicking.", type);
            button.click();
        } catch (Exception e) {
            log.error("Button '{}' is not found! XPath: {}", type, xpath, e);
            throw e;
        }
    }
}
