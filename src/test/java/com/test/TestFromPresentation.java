package com.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

class TestFromPresentation {

    @Test
    void testFormSubmission(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://testpages.eviltester.com/styled/basic-html-form-test.html");

        WebElement usernameField = driver.findElement(By.cssSelector("input[name='username']"));
        usernameField.sendKeys("testuser");

        WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
        passwordField.sendKeys("Test@1234");

        WebElement commentsField = driver.findElement(By.cssSelector("textarea[name='comments']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(commentsField, "Comments..."));
        commentsField.sendKeys("This is a test comment.");

        WebElement checkbox1 = driver.findElement(By.xpath("//input[@name='checkboxes[]'][@value='cb1']"));
        checkbox1.click();

        WebElement checkbox3 = driver.findElement(By.xpath("//input[@name='checkboxes[]'][@value='cb3']"));
        checkbox3.click();

        WebElement radio2 = driver.findElement(By.xpath("//input[@name='radioval'][@value='rd2']"));
        radio2.click();

        WebElement multipleSelect = driver.findElement(By.cssSelector("select[name='multipleselect[]']"));
        multipleSelect.click();
        multipleSelect.findElement(By.xpath("//option[@value='ms1']")).click();
        multipleSelect.findElement(By.xpath("//option[@value='ms3']")).click();

        WebElement dropdown = driver.findElement(By.cssSelector("select[name='dropdown']"));
        dropdown.click();
        dropdown.findElement(By.xpath("//option[@value='dd2']")).click();

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        WebElement backToFormButton = driver.findElement(By.id("back_to_form"));
        assert backToFormButton.isDisplayed();

        driver.quit();
    }
}
