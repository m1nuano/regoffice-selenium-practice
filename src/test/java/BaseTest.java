import com.test.drivers.WebDriverSingleton;
import com.test.steps.*;
import com.test.utils.TestListener;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;

import static com.test.constants.TestConstants.DRIVER_VARIABLE;
import static com.test.constants.TestConstants.MID_INTERVAL;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;

    AdminPageSteps adminPageSteps;
    AdminTableSteps adminTableSteps;
    ApplicantPageSteps applicantPageSteps;
    AppStatusSteps appStatusSteps;
    BirthAppSteps birthAppSteps;
    CitizenSteps citizenSteps;
    DeathAppSteps deathAppSteps;
    MainPageSteps mainPageSteps;
    MarriageAppSteps marriageAppSteps;
    TypeOfApplicationSteps typeOfApplicationSteps;

    @BeforeMethod
    public void setup(ITestContext context) {
        String username = System.getenv("APP_USERNAME");
        String password = System.getenv("APP_PASSWORD");
        String baseUrl = "https://%s:%s@regoffice.senla.eu/";
        String formattedUrl = String.format(baseUrl, username, password);
        driver = WebDriverSingleton.getDriver(context, DRIVER_VARIABLE);
        driver.get(formattedUrl);
        Duration duration = Duration.ofSeconds(MID_INTERVAL);
        log.info("Driver setting implicit wait to: {}", duration.getSeconds());
        driver.manage().timeouts().implicitlyWait(duration);
        log.debug("Initialization of test steps");
        initSteps();
    }

    @AfterTest
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    public void initSteps(){
        log.debug("Creating instances of test steps");
        adminPageSteps = new AdminPageSteps(driver);
        adminTableSteps = new AdminTableSteps(driver);
        applicantPageSteps = new ApplicantPageSteps(driver);
        appStatusSteps = new AppStatusSteps(driver);
        birthAppSteps = new BirthAppSteps(driver);
        citizenSteps = new CitizenSteps(driver);
        deathAppSteps = new DeathAppSteps(driver);
        mainPageSteps = new MainPageSteps(driver);
        marriageAppSteps = new MarriageAppSteps(driver);
        typeOfApplicationSteps = new TypeOfApplicationSteps(driver);
    }
}
