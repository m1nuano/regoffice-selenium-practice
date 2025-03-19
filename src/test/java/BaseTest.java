import com.test.config.ConfigProperties;
import com.test.drivers.WebDriverFactory;
import com.test.steps.*;
import com.test.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;

import static com.test.constants.TestConstants.MID_INTERVAL;

@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected AdminPageSteps adminPageSteps;
    protected AdminTableSteps adminTableSteps;
    protected ApplicantPageSteps applicantPageSteps;
    protected AppStatusSteps appStatusSteps;
    protected BirthAppSteps birthAppSteps;
    protected CitizenSteps citizenSteps;
    protected DeathAppSteps deathAppSteps;
    protected MainPageSteps mainPageSteps;
    protected MarriageAppSteps marriageAppSteps;
    protected TypeOfApplicationSteps typeOfApplicationSteps;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) throws Exception {
        WebDriverFactory.createDriver(browser);
        driver = WebDriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(MID_INTERVAL));

        String url = ConfigProperties.getProperty("APP_URL");
        String username = ConfigProperties.getProperty("APP_USERNAME");
        String password = ConfigProperties.getProperty("APP_PASSWORD");
        String formattedUrl = String.format(url, username, password);
        driver.get(formattedUrl);

        initSteps();
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.quitDriver();
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
