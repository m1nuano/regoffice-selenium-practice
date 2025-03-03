import com.test.components.AdminTableRows;
import com.test.drivers.WebDriverSingleton;
import com.test.models.MarriageData;
import com.test.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AdminTest {
    private static WebDriver driver;

    private String applicationNumber;

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

        statusPage.clickClose();
        mainPage.loginAsAdmin();

        AdminFormPage adminForm = new AdminFormPage(driver);
        adminForm.fillAdminData(testName, testName, testName, testPhone, testPassport, testDate);
        adminForm.clickNext();

        AdminTablePage adminTable = new AdminTablePage(driver);
        List<AdminTableRows> rowsList = adminTable.getAllRequestRows();
        if (!rowsList.isEmpty()) {
            AdminTableRows adminTableRows = rowsList.get(0);
            applicationNumber = adminTableRows.getRequestNumber();
        } else {
            System.out.println("Нет заявок в таблице");
        }
    }

    @Test
    public void testAdminTable() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginAsAdmin();

        AdminFormPage adminForm = new AdminFormPage(driver);
        adminForm.fillAdminData(testName, testName, testName, testPhone, testPassport, testDate);
        adminForm.clickNext();

        AdminTablePage adminTable = new AdminTablePage(driver);
        List<AdminTableRows> rowsList = adminTable.getAllRequestRows();
        if (!rowsList.isEmpty()) {
            AdminTableRows adminTableRows = rowsList.get(0);
            System.out.println("Номер заявки: " + adminTableRows.getRequestNumber());
            System.out.println("Заявитель: " + adminTableRows.getApplicantName());
            System.out.println("Тип: " + adminTableRows.getApplicationType());
            System.out.println("Время: " + adminTableRows.getApplicationTime());
            System.out.println("Статус: " + adminTableRows.getApplicationStatus());

            Assert.assertEquals(adminTableRows.getRequestNumber(), applicationNumber);
        } else {
            System.out.println("Нет заявок в таблице");
        }
    }

    @Test(dependsOnMethods = "testCreateMarriageApplication")
    public void testAdminApplicationIsDisplayed() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginAsAdmin();

        AdminFormPage adminForm = new AdminFormPage(driver);
        adminForm.fillAdminData(testName, testName, testName, testPhone, testPassport, testDate);
        adminForm.clickNext();

        AdminTablePage adminTable = new AdminTablePage(driver);
        List<AdminTableRows> rowsList = adminTable.getAllRequestRows();

        boolean isApplicationPresent = false;
        for (AdminTableRows row : rowsList) {
            if (row.getRequestNumber().equals(applicationNumber)) {
                isApplicationPresent = true;
                break;
            }
        }

        Assert.assertTrue(isApplicationPresent, "Заявка с номером " + applicationNumber + " не отображается в таблице администратора");
    }

    @Test(dependsOnMethods = "testCreateMarriageApplication")
    public void testAdminChangeApplicationStatus() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginAsAdmin();

        AdminFormPage adminForm = new AdminFormPage(driver);
        adminForm.fillAdminData(testName, testName, testName, testPhone, testPassport, testDate);
        adminForm.clickNext();

        AdminTablePage adminTable = new AdminTablePage(driver);
        List<AdminTableRows> rowsList = adminTable.getAllRequestRows();

        for (AdminTableRows row : rowsList) {
            if (row.getRequestNumber().equals(applicationNumber)) {
                row.approve();
                String approvedStatus = row.getApplicationStatus();
                Assert.assertEquals("Одобрена", approvedStatus, "Статус заявки был изменён на 'Одобрена'");

                row.reject();
                String rejectedStatus = row.getApplicationStatus();
                Assert.assertEquals("Отклонена", rejectedStatus, "Статус заявки был изменён на 'Отклонена'");
            }
        }
    }
}
