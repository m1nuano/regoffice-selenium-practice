import com.test.models.AppData;
import com.test.pojo.*;
import com.test.steps.*;
import com.test.test_data.DataProviders;
import com.test.utils.TestListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners({TestListener.class})
@Epic("API")
public class ApiUserTests {

    @Feature("Send user application request with '{mode}' mode")
    @Test(dataProvider = "sendEveryUserRequest", dataProviderClass = DataProviders.class, description = "POST sendUserRequest")
    public void testSendUserRequest(String mode, AppData data) {
        SendUserRequest request = SendUserSteps.createApplicationRequest(mode, data);
        SendUserResponse response = SendUserSteps.createAndValidateUser(request);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(response.getData(), "Data cannot be null");
        softAssert.assertEquals(response.getRequestId().length(), 36, "RequestId must be UUID format");

        softAssert.assertAll();
    }
}
