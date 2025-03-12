import com.test.entity.Applicant;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DatabaseApplicantTests extends DbBaseTest{

    @Epic("Database tests")
    @Feature("Applicant creation and saving")
    @Test
    @Step("Creating applicant and checking this applicant is created correctly")
    void testCreateApplicant() {
        Applicant applicant = dbApplicantSteps.createAndSaveApplicant();
        Applicant fromDb = dbApplicantSteps.getApplicantFromDb(applicant.getApplicantId());

        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(fromDb, "Applicant was not found in the database");
        soft.assertEquals(applicant.getSurname(), fromDb.getSurname(), "The surname does not match");
        soft.assertEquals(applicant.getName(), fromDb.getName(), "The name does not match");
        soft.assertEquals(applicant.getMiddleName(), fromDb.getMiddleName(), "The middle name does not match");
        soft.assertEquals(applicant.getPassportNumber(), fromDb.getPassportNumber(), "Passport number does not match");
        soft.assertEquals(applicant.getPhoneNumber(), fromDb.getPhoneNumber(), "Phone number does not match");
        soft.assertEquals(applicant.getRegistrationAddress(), fromDb.getRegistrationAddress(), "The registration address does not match");
        soft.assertAll();
    }
}
