package bdd;

import com.test.drivers.WebDriverSingleton;
import com.test.utils.TestListener;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import java.time.Duration;

import static com.test.constants.TestConstants.MID_INTERVAL;
import static com.test.constants.UrlConstants.BASE_URL;

@Listeners(TestListener.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"bdd.step_definitions"},
        tags = "@regression"
)
@Log4j2
public class TestRunner extends AbstractTestNGCucumberTests {

    protected WebDriver driver;

    @Before
    public void initDriver() {
        log.debug("Initializing WebDriver");
        String username = System.getenv("APP_USERNAME");
        String password = System.getenv("APP_PASSWORD");
        String formattedUrl = String.format(BASE_URL, username, password);
        driver = WebDriverSingleton.getDriver();
        driver.get(formattedUrl);
        Duration duration = Duration.ofSeconds(MID_INTERVAL);
        driver.manage().timeouts().implicitlyWait(duration);
    }

    @After
    public void quitDriver() {
        log.debug("Close browser");
        WebDriverSingleton.quitDriver();
    }
}
