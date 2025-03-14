package com.test.config;

import com.test.components.AdminTableRows;
import com.test.drivers.WebDriverSingleton;
import com.test.steps.*;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    private final WebDriver driver = WebDriverSingleton.getDriver();

    @Bean
    public MainPageSteps mainPageSteps() {
        return new MainPageSteps(driver);
    }

    @Bean
    public ApplicantPageSteps applicantPageSteps() {
        return new ApplicantPageSteps(driver);
    }

    @Bean
    public AdminPageSteps adminPageSteps() {
        return new AdminPageSteps(driver);
    }

    @Bean
    public TypeOfApplicationSteps typeOfApplicationSteps() {
        return new TypeOfApplicationSteps(driver);
    }

    @Bean
    public CitizenSteps citizenSteps() {
        return new CitizenSteps(driver);
    }

    @Bean
    public BirthAppSteps birthAppSteps() {
        return new BirthAppSteps(driver);
    }

    @Bean
    public AppStatusSteps appStatusSteps() {
        return new AppStatusSteps(driver);
    }

    @Bean
    public AdminTableSteps adminTableSteps() {
        return new AdminTableSteps(driver);
    }
}