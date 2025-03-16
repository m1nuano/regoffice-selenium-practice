import com.test.components.AdminTableRows;
import com.test.models.AppData;
import com.test.models.BirthData;
import com.test.pojo.SendUserRequest;
import com.test.pojo.SendUserResponse;
import com.test.steps.SendUserSteps;
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
    @Step("Creating birth application via API and getting application number")
    public void createBirthApplication() {
        AppData data = new AppData(
                TEST_NAME,
                TEST_PHONE,
                TEST_PASSPORT,
                TEST_ADDRESS,
                TEST_DATE,
                TEST_GENDER,
                MODE_BIRTH,
                new BirthData(TEST_ADDRESS, TEST_NAME, TEST_NAME, TEST_NAME, TEST_NAME),
                null,
                null
        );

        SendUserRequest birthApplicationRequest = SendUserSteps.createApplicationRequest(MODE_BIRTH, data);
        SendUserResponse birthApplicationResponse = SendUserSteps.createAndValidateUser(birthApplicationRequest);

        applicationNumber = String.valueOf(birthApplicationResponse.getData().getApplicationid());

        mainPageSteps.chooseRole(TEST_ADMINROLE);
        adminPageSteps.fillAdminFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_DATE);
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
