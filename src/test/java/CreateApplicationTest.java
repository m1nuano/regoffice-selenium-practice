import com.test.drivers.WebDriverSingleton;
import com.test.models.BirthData;
import com.test.models.DeathData;
import com.test.models.MarriageData;
import com.test.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class CreateApplicationTest {
    private static WebDriver driver;

    String testName = "aaaaa";
    String testPhone = "12345678";
    String testPassport = "12345678";
    String testAddress = "12345678";
    String testDate = "1111-11-11";
    String testGender = "1111";
    String testFamilyName = "11111";

    @BeforeMethod
    public static void setup() {
        String username = System.getenv("APP_USERNAME");
        String password = System.getenv("APP_PASSWORD");
        String baseUrl = "https://%s:%s@regoffice.senla.eu/";
        String formattedUrl = String.format(baseUrl, username, password);
        driver = WebDriverSingleton.getDriver();
        driver.get(formattedUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public static void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    @Test
    public void testCreateMarriageApplication() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginAsUser();

        ApplicantFormPage applicantForm = new ApplicantFormPage(driver);
        applicantForm.fillApplicantData(testName, testName, testName,
                testPhone, testPassport, testAddress);
        applicantForm.clickNext();

        TypeOfApplicationPage typeOfApplicationPage = new TypeOfApplicationPage(driver);
        typeOfApplicationPage.clickMarryButton();

        CitizenFormPage citizenForm = new CitizenFormPage(driver);
        citizenForm.fillCitizenData(testName, testName, testName, testDate,
                testPassport, testGender, testAddress);
        citizenForm.clickNext();

        MarriageApplicationPage marriageApplicationPage = new MarriageApplicationPage(driver);
        MarriageData marriageData = new MarriageData(testDate, testFamilyName, testName,
                testName, testName, testDate, testPassport);
        marriageApplicationPage.fillApplicationData(marriageData);
        marriageApplicationPage.submitApplication();

        ApplicationStatusPage statusPage = new ApplicationStatusPage(driver);
        Assert.assertTrue(statusPage.isApplicationSubmitted(), "Заявка зарегистрирована");
    }
    @Test
    public void testCreateBirthApplication() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginAsUser();

        ApplicantFormPage applicantForm = new ApplicantFormPage(driver);
        applicantForm.fillApplicantData(testName, testName, testName,
                testPhone, testPassport, testAddress);
        applicantForm.clickNext();

        TypeOfApplicationPage typeOfApplicationPage = new TypeOfApplicationPage(driver);
        typeOfApplicationPage.clickBirthButton();

        CitizenFormPage citizenForm = new CitizenFormPage(driver);
        citizenForm.fillCitizenData(testName, testName, testName, testDate,
                testPassport, testGender, testAddress);
        citizenForm.clickNext();

        BirthApplicationPage birthApplicationPage = new BirthApplicationPage(driver);
        BirthData birthData = new BirthData(testAddress, testName, testName, testName, testName);
        birthApplicationPage.fillBirthApplicationData(birthData);
        birthApplicationPage.submitApplication();

        ApplicationStatusPage statusPage = new ApplicationStatusPage(driver);
        Assert.assertTrue(statusPage.isApplicationSubmitted(), "Заявка зарегистрирована");
    }
    @Test
    public void testCreateDeathApplication() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginAsUser();

        ApplicantFormPage applicantForm = new ApplicantFormPage(driver);
        applicantForm.fillApplicantData(testName, testName, testName,
                testPhone, testPassport, testAddress);
        applicantForm.clickNext();

        TypeOfApplicationPage typeOfApplicationPage = new TypeOfApplicationPage(driver);
        typeOfApplicationPage.clickDeathButton();

        CitizenFormPage citizenForm = new CitizenFormPage(driver);
        citizenForm.fillCitizenData(testName, testName, testName, testDate,
                testPassport, testGender, testAddress);
        citizenForm.clickNext();

        DeathApplicationPage deathApplicationPage = new DeathApplicationPage(driver);
        DeathData deathData = new DeathData(testDate, testAddress);
        deathApplicationPage.fillDeathApplicationData(deathData);
        deathApplicationPage.submitApplication();

        ApplicationStatusPage statusPage = new ApplicationStatusPage(driver);
        Assert.assertTrue(statusPage.isApplicationSubmitted(), "Заявка зарегистрирована");
    }
}
