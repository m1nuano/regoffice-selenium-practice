package com.test.utils;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

import static com.test.constants.TestConstants.DRIVER_VARIABLE;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.info("==================== STARTING TEST '{}' ====================", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("==================== FINISHED TEST '{}' Duration: {} ms ====================", result.getName(), getExecutionTime(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("==================== FAILED TEST '{}' Duration: {} ms ====================", result.getName(), getExecutionTime(result));
        takeScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("==================== SKIPPING TEST '{}' ====================", result.getName());
        takeScreenshot(result);
    }

    @Attachment(value = "Last screen state", type = "image/png")
    private byte[] takeScreenshot(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        try {
            WebDriver driver = (WebDriver) context.getAttribute(DRIVER_VARIABLE);
            if (driver != null) {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } else {
                return new byte[]{};
            }
        } catch (NoSuchSessionException | IllegalStateException ex) {
            return new byte[]{};
        }
    }

    private long getExecutionTime(ITestResult result){
        return TimeUnit.MILLISECONDS.toMillis(result.getEndMillis() - result.getStartMillis());
    }
}
