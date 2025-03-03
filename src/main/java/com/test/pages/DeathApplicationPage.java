package com.test.pages;

import com.test.models.DeathData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeathApplicationPage {
    @FindBy(xpath = "//div[label[contains(text(), 'Дата смерти')]]/following-sibling::input")
    private WebElement dateOfDeathField;
    @FindBy(xpath = "//div[label[contains(text(), 'Место смерти')]]/following-sibling::input")
    private WebElement placeOfDeathField;

    @FindBy(xpath = "//button[contains(text(), 'Завершить')]")
    private WebElement finishButton;

    public DeathApplicationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillDeathApplicationData(DeathData deathData){
        dateOfDeathField.sendKeys(deathData.getDateOfDeath());
        placeOfDeathField.sendKeys(deathData.getPlaceOfDeath());
    }

    public void submitApplication() {
        finishButton.click();
    }
}
