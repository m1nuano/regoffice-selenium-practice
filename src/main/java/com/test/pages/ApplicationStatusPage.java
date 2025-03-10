package com.test.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class ApplicationStatusPage {

    private WebDriver driver;

    private static final String NUMBER_OF_APPLICATION = "//span[contains(text(), '№')]";
    private static final String BUTTON_XPATH = "//button[contains(text(), '%s')]";

    public ApplicationStatusPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Admin form page is initialized");
    }

    private WebElement getRequestNumber() {
        log.debug("Finding '№' on page");
        return driver.findElement(By.xpath(NUMBER_OF_APPLICATION));
    }

    private WebElement getButton(String buttonText) {
        String xpath = String.format(BUTTON_XPATH, buttonText);
        log.debug("Finding button with text '{}': XPath = {}", buttonText, xpath);
        return driver.findElement(By.xpath(xpath));
    }

    public boolean isApplicationNumberVisible() {
        log.info("Checking if '№' displayed");
        return getRequestNumber().isDisplayed();
    }

    @Step("Clicking the '{buttonText}' button")
    public void clickButton(String buttonText) {
        log.info("Clicking button '{}'", buttonText);
        getButton(buttonText).click();
    }
}
