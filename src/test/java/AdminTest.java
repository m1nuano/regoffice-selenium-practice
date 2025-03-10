import com.test.components.AdminTableRows;
import com.test.models.BirthData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.test.constants.TestConstants.*;

public class AdminTest extends BaseTest {
    private String applicationNumber;

    @BeforeMethod
    @Step("Creating birth application and getting application number from administrator table")
    public void createBirthApplication() {
        mainPageSteps.chooseRole(TEST_USERROLE);

        applicantPageSteps.fillApplicantFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);

        typeOfApplicationSteps.chooseApplication(APP_BIRTH);

        citizenSteps.fillCitizenFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);

        BirthData birthData = new BirthData(TEST_ADDRESS, TEST_NAME, TEST_NAME, TEST_NAME, TEST_NAME);
        birthAppSteps.fillBirthApplicationPage(birthData);

        appStatusSteps.performAction(BUTTON_CLOSE);

        mainPageSteps.chooseRole(TEST_ADMINROLE);

        adminPageSteps.fillAdminFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_DATE);

        AdminTableRows adminTableRows = adminTableSteps.getFirstRow();
        applicationNumber = adminTableRows.getRequestNumber();
    }

    @Epic("Admin table tests")
    @Feature("Test of the last application for display in the table")
    @Test
    @Step("Checking the last application is displayed in the administrator table")
    public void testAdminTable() {
        AdminTableRows row = adminTableSteps.getFirstRow();
        String requestNumber = row.getRequestNumber();

        Assert.assertEquals(requestNumber, applicationNumber);
    }

    @Epic("Admin table tests")
    @Feature("Checking a change in application status")
    @Test(dataProvider = "applicationStatusChanges")
    @Step("Verification of changes in the status of an application: {0} -> {1}")
    public void testAdminChangeApplicationStatus(String actionType, String expectedStatus) {
        AdminTableRows row = adminTableSteps.getFirstRow();

        row.performAction(actionType, expectedStatus);

        String actualStatus = row.getApplicationStatus();

        Assert.assertEquals(actualStatus, expectedStatus);
    }

    @DataProvider(name = "applicationStatusChanges")
    public Object[][] applicationStatusChanges() {
        return new Object[][] {
                {APPROVE_APP, "Одобрена"},
                {REJECT_APP, "Отклонена"}
        };
    }
}
