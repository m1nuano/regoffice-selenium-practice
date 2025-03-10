import com.test.pojo.*;
import com.test.steps.*;
import com.test.models.AppData;
import com.test.test_data.DataProviders;
import com.test.utils.RequestResponseFilter;
import com.test.utils.TestListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Listeners({TestListener.class})
public class ApiTests {

    private Integer applicationId;
    private Integer staffId;

//    static {
//        RestAssured.filters(new RequestResponseFilter());
//    }

    @Epic("API")
    @Feature("Send user application request with '{mode}' mode")
    @Test(dataProvider = "sendEveryUserRequest", dataProviderClass = DataProviders.class)
    @Step("POST sendUserRequest")
    public void testSendUserRequest(String mode, AppData data) {
        SendUserRequest request = SendUserSteps.createApplicationRequest(mode, data);
        SendUserResponse response = SendUserSteps.createAndValidateUser(request);

        applicationId = response.getData().getApplicationid();

        assertNotNull(response.getData(), "Data cannot be null");
        assertEquals(response.getRequestId().length(), 36, "RequestId must be UUID format");
    }

    @Epic("API")
    @Feature("Send admin request")
    @Test
    @Step("POST sendAdminRequest")
    public void testSendAdminRequest() {
        SendAdminRequest request = SendAdminSteps.createAdminRequest();
        SendAdminResponse response = SendAdminSteps.createAndValidateAdmin(request);

        staffId = response.getData().getStaffid();

        assertNotNull(response.getData(), "Data cannot be null");
        assertNotNull(response.getRequestId(), "RequestId cannot be null");
        assertEquals(response.getRequestId().length(), 36, "RequestId must be UUID format");
    }

    @Epic("API")
    @Feature("Send process request")
    @Test(dependsOnMethods = {"testSendUserRequest", "testSendAdminRequest"})
    @Step("POST processRequest")
    public void testProcessRequest() {
        RequestProcess request = ProcessSteps.createProcessRequest(applicationId, staffId, "approved");
        ResponseProcess response = ProcessSteps.createAndValidateProcess(request);

        assertNotNull(response.getData(), "Data cannot be null");
        assertNotNull(response.getRequestId(), "RequestId cannot be null");
        assertEquals(response.getRequestId().length(), 36, "RequestId must be UUID format");

        assertEquals(response.getData().getApplicationid(), applicationId, "Application ID should match");
    }

    @Epic("API")
    @Feature("Send get applications request")
    @Test(dependsOnMethods = {"testSendUserRequest"})
    @Step("GET getApplications")
    public void testGetApplications() {
        GetApplicationsResponse response = GetApplicationsSteps.sendGetRequest();

        assertNotNull(response.getData(), "Data cannot be null");
        assertNotNull(response.getRequestId(), "RequestId cannot be null");
        assertEquals(response.getRequestId().length(), 36, "RequestId должен быть UUID формата");

        assertEquals(response.getData().get(0).getApplicationid(), applicationId, "Application IDs should match");
    }

    @Epic("API")
    @Feature("Send get application status request")
    @Test(dependsOnMethods = {"testSendUserRequest"})
    @Step("GET getApplicationStatus/applId")
    public void testGetApplicationStatus() {
        GetApplStatusResponse response = GetApplStatusSteps.createAndValidateStatus(applicationId);

        assertNotNull(response.getData(), "Data cannot be null");
        assertNotNull(response.getRequestId(), "Request ID cannot be null");
    }
}
