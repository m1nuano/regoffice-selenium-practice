import com.test.components.AdminTableRows;
import com.test.models.BirthData;
import com.test.pages.AdminTablePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.test.constants.TestConstants.*;

public class AdminTest extends BaseTest {
    String applicationNumber;

    @BeforeMethod
    public void setUp() {
        mainPageSteps.chooseRole(TEST_USERROLE);
        applicantPageSteps.fillApplicantFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);
        typeOfApplicationSteps.chooseApplication(APP_BIRTH);
        citizenSteps.fillCitizenFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);
        BirthData birthData = new BirthData(TEST_ADDRESS, TEST_NAME, TEST_NAME, TEST_NAME, TEST_NAME);
        birthAppSteps.fillBirthApplicationPage(birthData);

        appStatusSteps.performAction(BUTTON_CLOSE);
        mainPageSteps.chooseRole(TEST_ADMINROLE);

        adminPageSteps.fillAdminFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_DATE);

        AdminTablePage adminTable = new AdminTablePage(driver);
        List<AdminTableRows> rowsList = adminTable.getAllRequestRows();
        AdminTableRows adminTableRows = rowsList.get(0);
        applicationNumber = adminTableRows.getRequestNumber();
    }

    @Test
    public void testAdminTable() {
        AdminTableRows row = adminTableSteps.getFirstRow();
        String requestNumber = row.getRequestNumber();

        Assert.assertEquals(requestNumber, applicationNumber);
    }

    @Test(dataProvider = "applicationActions")
    public void testAdminChangeApplicationStatus(String actionType, String expectedStatus) {
        AdminTableRows row = adminTableSteps.getFirstRow();

        row.performAction(actionType, expectedStatus);

        String actualStatus = row.getApplicationStatus();
        Assert.assertEquals(actualStatus, expectedStatus);
    }


    @DataProvider(name = "applicationActions")
    public Object[][] applicationActions() {
        return new Object[][] {
                {APPROVE_APP, "Одобрена"},
                {REJECT_APP, "Отклонена"}
        };
    }
}
