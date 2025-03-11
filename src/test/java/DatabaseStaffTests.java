import com.test.entity.Staff;
import com.test.steps.DatabaseStaffSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DatabaseStaffTests {
    private static DatabaseStaffSteps dbSteps;

    @BeforeTest
    static void setup() {
        dbSteps = new DatabaseStaffSteps();
    }

    @Epic("Database tests")
    @Feature("Staff creation and saving")
    @Test
    @Step("Creating staff and checking this staff is created correctly")
    void testCreateStaff() {
        Staff staff = dbSteps.createAndSaveStaff();
        Staff fromDb = dbSteps.getStaffFromDb(staff.getStaffId());

        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(fromDb);
        soft.assertEquals(staff.getSurname(), fromDb.getSurname());
        soft.assertEquals(staff.getName(), fromDb.getName());
        soft.assertEquals(staff.getMiddleName(), fromDb.getMiddleName());
        soft.assertEquals(staff.getDateOfBirth(), fromDb.getDateOfBirth());
        soft.assertEquals(staff.getPassportNumber(), fromDb.getPassportNumber());
        soft.assertEquals(staff.getPhoneNumber(), fromDb.getPhoneNumber());
        soft.assertAll();
    }
}
