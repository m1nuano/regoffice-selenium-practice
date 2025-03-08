import com.test.models.BirthData;
import com.test.models.DeathData;
import com.test.models.MarriageData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.test.constants.TestConstants.*;

@Log4j2
public class CreateEveryApplicationTest extends BaseTest {

    @Epic("Application creation tests")
    @Feature("Marriage application creation")
    @Test
    @Step("Creating marriage application and checking this application is created")
    public void testCreateMarriageApplication() {
        log.info("Starting the creation of marriage application");

        mainPageSteps.chooseRole(TEST_USERROLE);
        log.info("Chosen role for user: {}", TEST_USERROLE);

        applicantPageSteps.fillApplicantFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);
        log.info("Applicant form filled with test data");

        typeOfApplicationSteps.chooseApplication(APP_MARRY);
        log.info("Chosen application type: {}", APP_MARRY);

        citizenSteps.fillCitizenFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);
        log.info("Citizen form filled with test data");

        MarriageData marriageData = new MarriageData(TEST_DATE, TEST_LASTNAME, TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT);
        marriageAppSteps.fillMarriageApplicationPage(marriageData);
        log.info("Marriage application form filled");

        Assert.assertTrue(appStatusSteps.checkAppStatusIsPresent());
    }

    @Epic("Application creation tests")
    @Feature("Birth application creation")
    @Test
    @Step("Creating birth application and checking this application is created")
    public void testCreateBirthApplication() {
        log.info("Starting the creation of birth application");

        mainPageSteps.chooseRole(TEST_USERROLE);
        log.info("Chosen role for user: {}", TEST_USERROLE);

        applicantPageSteps.fillApplicantFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);
        log.info("Applicant form filled with test data");

        typeOfApplicationSteps.chooseApplication(APP_BIRTH);
        log.info("Chosen application type: {}", APP_BIRTH);

        citizenSteps.fillCitizenFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);
        log.info("Citizen form filled with test data");

        BirthData birthData = new BirthData(TEST_ADDRESS, TEST_NAME, TEST_NAME, TEST_NAME, TEST_NAME);
        birthAppSteps.fillBirthApplicationPage(birthData);
        log.info("Birth application form filled");

        Assert.assertTrue(appStatusSteps.checkAppStatusIsPresent());
    }

    @Epic("Application creation tests")
    @Feature("Death application creation")
    @Test
    @Step("Creating death application and checking this application is created")
    public void testCreateDeathApplication() {
        log.info("Starting the creation of death application");

        mainPageSteps.chooseRole(TEST_USERROLE);
        log.info("Chosen role for user: {}", TEST_USERROLE);

        applicantPageSteps.fillApplicantFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);
        log.info("Applicant form filled with test data");

        typeOfApplicationSteps.chooseApplication(APP_DEATH);
        log.info("Chosen application type: {}", APP_DEATH);

        citizenSteps.fillCitizenFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);
        log.info("Citizen form filled with test data");

        DeathData deathData = new DeathData(TEST_DATE, TEST_ADDRESS);
        deathAppSteps.fillDeathApplicationPage(deathData);
        log.info("Death application form filled");

        Assert.assertTrue(appStatusSteps.checkAppStatusIsPresent());
    }
}
