package com.test.steps;

import com.test.components.AdminTableRows;
import com.test.pages.AdminTablePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class AdminTableSteps {
    private final AdminTablePage adminTablePage;

    public AdminTableSteps(WebDriver driver) {
        adminTablePage = new AdminTablePage(driver);
    }

    @Step("Getting first row from admin table")
    public AdminTableRows getFirstRow() {
        return adminTablePage.getAllRequestRows().get(0);
    }
}
