package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicationStatusPage {

    @FindBy(xpath = "//span[contains(text(), '№')]")
    private WebElement applicationNumberLabel;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    private WebElement close;

    public ApplicationStatusPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isApplicationSubmitted() {
        return applicationNumberLabel.isDisplayed();
    }

    public void clickClose() {
        close.click();
    }
}
