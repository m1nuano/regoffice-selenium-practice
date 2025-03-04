import com.test.drivers.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseUserTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        String username = System.getenv("APP_USERNAME");
        String password = System.getenv("APP_PASSWORD");
        String baseUrl = "https://%s:%s@regoffice.senla.eu/";
        String formattedUrl = String.format(baseUrl, username, password);
        driver = WebDriverSingleton.getDriver();
        driver.get(formattedUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterTest
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }
}
