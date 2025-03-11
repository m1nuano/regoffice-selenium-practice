import com.test.models.AppData;
import com.test.models.BirthData;
import com.test.pojo.*;
import com.test.steps.*;
import com.test.utils.TestListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.test.constants.TestConstants.*;

@Listeners({TestListener.class})
public class ApiAdminTests {

    private Integer applicationId;
    private Integer staffId;

    @BeforeMethod
    @Step("Create user application before admin tests")
    public void testSendUserBirthRequest() {
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

        SendUserRequest request = SendUserSteps.createApplicationRequest(MODE_BIRTH, data);
        SendUserResponse response = SendUserSteps.createAndValidateUser(request);

        applicationId = response.getData().getApplicationid();
    }

    @Epic("API")
    @Feature("Send admin request")
    @Test
    @Step("POST sendAdminRequest")
    public void testSendAdminRequest() {
        SendAdminRequest request = SendAdminSteps.createAdminRequest();
        SendAdminResponse response = SendAdminSteps.createAndValidateAdmin(request);

        staffId = response.getData().getStaffId();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(response.getData(), "Data cannot be null");
        softAssert.assertNotNull(response.getRequestId(), "RequestId cannot be null");
        softAssert.assertEquals(response.getRequestId().length(), 36, "RequestId must be UUID format");

        softAssert.assertAll();
    }

    @Epic("API")
    @Feature("Send process request")
    @Test
    @Step("POST processRequest")
    public void testProcessRequest() {
        RequestProcess request = ProcessSteps.createProcessRequest(applicationId, staffId, "approved");
        ResponseProcess response = ProcessSteps.createAndValidateProcess(request);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotNull(response.getData(), "Data cannot be null");
        softAssert.assertNotNull(response.getRequestId(), "RequestId cannot be null");
        softAssert.assertEquals(response.getRequestId().length(), 36, "RequestId must be UUID format");

        softAssert.assertEquals(response.getData().getApplicationId(), applicationId, "Application ID should match");

        softAssert.assertAll();
    }

    @Epic("API")
    @Feature("Send get applications request")
    @Test
    @Step("GET getApplications")
    public void testGetApplications() {
        GetApplicationsResponse response = GetApplicationsSteps.sendGetApplicationsRequest();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotNull(response.getData(), "Data cannot be null");
        softAssert.assertNotNull(response.getRequestId(), "RequestId cannot be null");
        softAssert.assertEquals(response.getRequestId().length(), 36, "RequestId must be UUID format");

        softAssert.assertEquals(response.getData().get(0).getApplicationId(), applicationId, "Application IDs should match");

        softAssert.assertAll();
    }

    @Epic("API")
    @Feature("Send get application status request")
    @Test
    @Step("GET getApplicationStatus/applId")
    public void testGetApplicationStatus() {
        GetApplStatusResponse response = GetApplStatusSteps.createAndValidateStatus(applicationId);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotNull(response.getData(), "Data cannot be null");
        softAssert.assertNotNull(response.getRequestId(), "Request ID cannot be null");

        softAssert.assertAll();
    }
}
