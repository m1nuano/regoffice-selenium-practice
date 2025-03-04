import com.test.components.AdminTableRows;
import com.test.constants.TestConstants;
import com.test.drivers.WebDriverSingleton;
import com.test.models.MarriageData;
import com.test.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

public class BaseAdminTest implements TestConstants {
    WebDriver driver;
    String applicationNumber;

    @BeforeMethod
    public void setup() {
        String username = System.getenv("APP_USERNAME");
        String password = System.getenv("APP_PASSWORD");
        String baseUrl = "https://%s:%s@regoffice.senla.eu/";
        String formattedUrl = String.format(baseUrl, username, password);
        driver = WebDriverSingleton.getDriver();
        driver.get(formattedUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        MainPage mainPage = new MainPage(driver);
        mainPage.loginAsUser();

        ApplicantFormPage applicantForm = new ApplicantFormPage(driver);
        applicantForm.fillApplicantData(TEST_NAME, TEST_NAME, TEST_NAME,
                TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);
        applicantForm.clickNext();

        TypeOfApplicationPage typeOfApplicationPage = new TypeOfApplicationPage(driver);
        typeOfApplicationPage.clickMarryButton();

        CitizenFormPage citizenForm = new CitizenFormPage(driver);
        citizenForm.fillCitizenData(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE,
                TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);
        citizenForm.clickNext();

        MarriageApplicationPage marriageApplicationPage = new MarriageApplicationPage(driver);
        MarriageData marriageData = new MarriageData(TEST_DATE, TEST_FAMILYNAME, TEST_NAME,
                TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT);
        marriageApplicationPage.fillApplicationData(marriageData);
        marriageApplicationPage.submitApplication();

        ApplicationStatusPage statusPage = new ApplicationStatusPage(driver);
        statusPage.clickClose();

        mainPage.loginAsAdmin();

        AdminFormPage adminForm = new AdminFormPage(driver);
        adminForm.fillAdminData(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_DATE);
        adminForm.clickNext();

        AdminTablePage adminTable = new AdminTablePage(driver);
        List<AdminTableRows> rowsList = adminTable.getAllRequestRows();
        if (!rowsList.isEmpty()) {
            AdminTableRows adminTableRows = rowsList.get(0);
            applicationNumber = adminTableRows.getRequestNumber();
        } else {
            System.out.println("Нет заявок в таблице");
        }
        adminForm.clickClose();
    }

    @AfterTest
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }
}
