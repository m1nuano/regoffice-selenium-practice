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
public class ApiUserTests {

    @Epic("API")
    @Feature("Send user application request with '{mode}' mode")
    @Test(dataProvider = "sendEveryUserRequest", dataProviderClass = DataProviders.class)
    @Step("POST sendUserRequest")
    public void testSendUserRequest(String mode, AppData data) {
        SoftAssert softAssert = new SoftAssert();

        SendUserRequest request = SendUserSteps.createApplicationRequest(mode, data);
        SendUserResponse response = SendUserSteps.createAndValidateUser(request);

        softAssert.assertNotNull(response.getData(), "Data cannot be null");
        softAssert.assertEquals(response.getRequestId().length(), 36, "RequestId must be UUID format");

        softAssert.assertAll();
    }
}
