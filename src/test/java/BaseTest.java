import com.test.drivers.WebDriverSingleton;
import com.test.steps.*;
import com.test.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;

import static com.test.constants.TestConstants.MID_INTERVAL;

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
        driver = WebDriverSingleton.getDriver(context);
        driver.get(formattedUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(MID_INTERVAL));
        initSteps();
    }

    @AfterTest
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    public void initSteps(){
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
