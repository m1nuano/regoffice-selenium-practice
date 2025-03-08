import com.test.components.AdminTableRows;
import com.test.models.BirthData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.test.constants.TestConstants.*;

@Log4j2
public class AdminTest extends BaseTest {
    private String applicationNumber;

    @BeforeMethod
    @Step("Creating birth application and getting application number from administrator table")
    public void createBirthApplication() {
        log.info("Starting birth application creation process");

        mainPageSteps.chooseRole(TEST_USERROLE);
        log.info("Chosen role for user: {}", TEST_USERROLE);

        applicantPageSteps.fillApplicantFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);
        log.info("Applicant form filled with provided test data");

        typeOfApplicationSteps.chooseApplication(APP_BIRTH);
        log.info("Selected application type: {}", APP_BIRTH);

        citizenSteps.fillCitizenFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);
        log.info("Citizen form filled with provided test data");

        BirthData birthData = new BirthData(TEST_ADDRESS, TEST_NAME, TEST_NAME, TEST_NAME, TEST_NAME);
        birthAppSteps.fillBirthApplicationPage(birthData);
        log.info("Birth application form filled");

        appStatusSteps.performAction(BUTTON_CLOSE);
        log.info("Closed the application");

        mainPageSteps.chooseRole(TEST_ADMINROLE);
        log.info("Chosen admin role: {}", TEST_ADMINROLE);

        adminPageSteps.fillAdminFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_DATE);
        log.info("Admin form filled with provided test data");

        AdminTableRows adminTableRows = adminTableSteps.getFirstRow();
        applicationNumber = adminTableRows.getRequestNumber();
        log.info("Application number for the newly created birth application: {}", applicationNumber);
    }

    @Epic("Admin table tests")
    @Feature("Test of the last application for display in the table")
    @Test
    @Step("Checking the last application is displayed in the administrator table")
    public void testAdminTable() {
        log.info("Starting test for verifying the last application in the table");

        AdminTableRows row = adminTableSteps.getFirstRow();
        String requestNumber = row.getRequestNumber();
        log.info("Retrieved application number from admin table row: {}", requestNumber);

        Assert.assertEquals(requestNumber, applicationNumber);
    }

    @Epic("Admin table tests")
    @Feature("Checking a change in application status")
    @Test(dataProvider = "applicationStatusChanges")
    @Step("Verification of changes in the status of an application: {0} -> {1}")
    public void testAdminChangeApplicationStatus(String actionType, String expectedStatus) {
        log.info("Starting test for changing application status");

        AdminTableRows row = adminTableSteps.getFirstRow();
        log.info("Retrieved first row from admin table");

        row.performAction(actionType, expectedStatus);
        log.info("Performed action '{}' and expected status change to '{}'", actionType, expectedStatus);

        String actualStatus = row.getApplicationStatus();
        log.info("Retrieved application status after action: {}", actualStatus);

        Assert.assertEquals(actualStatus, expectedStatus);
    }

    @DataProvider(name = "applicationStatusChanges")
    public Object[][] applicationStatusChanges() {
        log.info("Providing test data for application status changes");
        return new Object[][] {
                {APPROVE_APP, "Одобрена"},
                {REJECT_APP, "Отклонена"}
        };
    }
}
