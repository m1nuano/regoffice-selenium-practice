import com.test.entity.Applicant;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Epic("Database tests")
public class DatabaseApplicantTests extends DbBaseTest{

    @Feature("Applicant creation and saving")
    @Test(description = "Creating applicant and checking this applicant is created correctly")
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
