package com.test.pages;

import com.test.waiters.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static com.test.constants.TestConstants.MID_INTERVAL;

public class ApplicationStatusPage {

    private WebDriver driver;
    private Waiters waiters;

    private static final String NUMBER_OF_APPLICATION = "//span[contains(text(), '№')]";
    private static final String BUTTON_XPATH = "//button[contains(text(), '%s')]";

    public ApplicationStatusPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waiters = new Waiters(driver);
    }

    private WebElement getText() {
        waiters.waitForTextAppears(By.xpath(NUMBER_OF_APPLICATION), "№", Duration.ofSeconds(MID_INTERVAL));
        return driver.findElement(By.xpath(NUMBER_OF_APPLICATION));
    }

    private WebElement getButton(String buttonText) {
        String xpath = String.format(BUTTON_XPATH, buttonText);
        return driver.findElement(By.xpath(xpath));
    }

    public boolean isApplicationNumberVisible() {
        return getText().isDisplayed();
    }

    public void clickButton(String buttonText) {
        getButton(buttonText).click();
    }
}
