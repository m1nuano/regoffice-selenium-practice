package bdd;

import com.test.config.ConfigProperties;
import com.test.drivers.WebDriverFactory;
import com.test.utils.TestListener;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

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
    @Parameters("browser")
    public void initDriver(String browser) throws Exception {
        WebDriverFactory.createDriver(browser);
        WebDriverFactory.getDriver();
        String url = ConfigProperties.getProperty("APP_URL");
        String username = ConfigProperties.getProperty("APP_USERNAME");
        String password = ConfigProperties.getProperty("APP_PASSWORD");
        String formattedUrl = String.format(url, username, password);
        driver.get(formattedUrl);
    }

    @After
    public void quitDriver() {
        log.debug("Close browser");
        WebDriverFactory.quitDriver();
    }
}
