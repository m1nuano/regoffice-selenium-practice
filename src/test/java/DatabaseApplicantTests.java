import com.test.entity.Applicant;
import com.test.steps.DatabaseApplicantSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DatabaseApplicantTests {
    private static DatabaseApplicantSteps dbSteps;

    @BeforeTest
    static void setup() {
        dbSteps = new DatabaseApplicantSteps();
    }

    @Epic("Database tests")
    @Feature("Applicant creation and saving")
    @Test
    @Step("Creating applicant and checking this applicant is created correctly")
    void testCreateApplicant() {
        Applicant applicant = dbSteps.createAndSaveApplicant();
        Applicant fromDb = dbSteps.getApplicantFromDb(applicant.getApplicantId());

        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(fromDb);
        soft.assertEquals(applicant.getSurname(), fromDb.getSurname());
        soft.assertEquals(applicant.getName(), fromDb.getName());
        soft.assertEquals(applicant.getMiddleName(), fromDb.getMiddleName());
        soft.assertEquals(applicant.getPassportNumber(), fromDb.getPassportNumber());
        soft.assertEquals(applicant.getPhoneNumber(), fromDb.getPhoneNumber());
        soft.assertEquals(applicant.getRegistrationAddress(), fromDb.getRegistrationAddress());
        soft.assertAll();
    }
}
