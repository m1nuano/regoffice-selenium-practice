package com.test.utils;

import com.test.drivers.WebDriverFactory;
import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {

    private static final List<String> apiTestLogs = new ArrayList<>();

    @Override
    public void onTestStart(ITestResult result) {
        log.info("==================== STARTING TEST '{}' ====================", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("==================== FINISHED TEST '{}' Duration: {} ms ====================", result.getName(), getExecutionTime(result));

        if (isApiTest(result)) {
            logApiRequestResponse(result);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("==================== FAILED TEST '{}' Duration: {} ms ====================", result.getName(), getExecutionTime(result));

        if (isApiTest(result)) {
            logApiRequestResponse(result);
        }

        takeScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("==================== SKIPPING TEST '{}' ====================", result.getName());

        if (isApiTest(result)) {
            logApiRequestResponse(result);
        }

        takeScreenshot();
    }

    private void logApiRequestResponse(ITestResult result) {
        apiTestLogs.add("Test " + result.getName() + " executed.");
        apiTestLogs.addAll(RequestResponseFilter.getApiLogs());

        attachApiLogs(String.join("\n", apiTestLogs));

        apiTestLogs.clear();
    }

    @Attachment(value = "API Test Logs", type = "text/plain")
    private String attachApiLogs(String apiLogs) {
        return apiLogs;
    }

    private boolean isApiTest(ITestResult result) {
        return result.getTestClass().getName().contains("Api");
    }

    @Attachment(value = "Last screen state", type = "image/png")
    private byte[] takeScreenshot() {
        try {
            WebDriver driver = WebDriverFactory.getDriver();
            if (driver != null) {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } else {
                return new byte[]{};
            }
        } catch (NoSuchSessionException | IllegalStateException ex) {
            return new byte[]{};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private long getExecutionTime(ITestResult result) {
        return TimeUnit.MILLISECONDS.toMillis(result.getEndMillis() - result.getStartMillis());
    }
}
