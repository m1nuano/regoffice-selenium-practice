import com.test.models.BirthData;
import com.test.models.DeathData;
import com.test.models.MarriageData;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.test.constants.TestConstants.*;

public class CreateEveryApplicationTest extends BaseTest{

    @Test
    public void testCreateMarriageApplication() {
        mainPageSteps.chooseRole(TEST_USERROLE);

        applicantPageSteps.fillApplicantFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);

        typeOfApplicationSteps.chooseApplication(APP_MARRY);

        citizenSteps.fillCitizenFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);

        MarriageData marriageData = new MarriageData(TEST_DATE, TEST_LASTNAME, TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT);
        marriageAppSteps.fillMarriageApplicationPage(marriageData);

        Assert.assertTrue(appStatusSteps.checkAppStatusIsPresent());
    }

    @Test
    public void testCreateBirthApplication() {
        mainPageSteps.chooseRole(TEST_USERROLE);

        applicantPageSteps.fillApplicantFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);

        typeOfApplicationSteps.chooseApplication(APP_BIRTH);

        citizenSteps.fillCitizenFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);

        BirthData birthData = new BirthData(TEST_ADDRESS, TEST_NAME, TEST_NAME, TEST_NAME, TEST_NAME);
        birthAppSteps.fillBirthApplicationPage(birthData);

        Assert.assertTrue(appStatusSteps.checkAppStatusIsPresent());
    }
    @Test
    public void testCreateDeathApplication() {
        mainPageSteps.chooseRole(TEST_USERROLE);

        applicantPageSteps.fillApplicantFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);

        typeOfApplicationSteps.chooseApplication(APP_DEATH);

        citizenSteps.fillCitizenFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);

        DeathData deathData = new DeathData(TEST_DATE, TEST_ADDRESS);
        deathAppSteps.fillDeathApplicationPage(deathData);

        Assert.assertTrue(appStatusSteps.checkAppStatusIsPresent());
    }
}
