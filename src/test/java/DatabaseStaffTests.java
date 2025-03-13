import com.test.entity.Staff;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DatabaseStaffTests extends DbBaseTest {

    @Epic("Database tests")
    @Feature("Staff creation and saving")
    @Test
    @Step("Creating staff and checking this staff is created correctly")
    void testCreateStaff() {
        Staff staff = dbStaffSteps.createAndSaveStaff();
        Staff fromDb = dbStaffSteps.getStaffFromDb(staff.getStaffId());

        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(fromDb, "Staff was not found in the database");
        soft.assertEquals(staff.getSurname(), fromDb.getSurname(), "The surname does not match");
        soft.assertEquals(staff.getName(), fromDb.getName(), "The name does not match");
        soft.assertEquals(staff.getMiddleName(), fromDb.getMiddleName(), "The middle name does not match");
        soft.assertEquals(staff.getDateOfBirth(), fromDb.getDateOfBirth(), "The date of birth does not match");
        soft.assertEquals(staff.getPassportNumber(), fromDb.getPassportNumber(), "The passport number does not match");
        soft.assertEquals(staff.getPhoneNumber(), fromDb.getPhoneNumber(), "The phone number does not match");
        soft.assertAll();
    }
}
