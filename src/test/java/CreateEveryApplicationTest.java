import com.test.constants.TestConstants;
import com.test.models.BirthData;
import com.test.models.DeathData;
import com.test.models.MarriageData;
import com.test.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateEveryApplicationTest extends BaseUserTest implements TestConstants {

    @Test
    public void testCreateMarriageApplication() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginAsUser();

        ApplicantFormPage applicantForm = new ApplicantFormPage(driver);
        applicantForm.fillApplicantData(TEST_NAME, TEST_NAME, TEST_NAME,
                TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);
        applicantForm.clickNext();

        TypeOfApplicationPage typeOfApplicationPage = new TypeOfApplicationPage(driver);
        typeOfApplicationPage.clickMarryButton();

        CitizenFormPage citizenForm = new CitizenFormPage(driver);
        citizenForm.fillCitizenData(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE,
                TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);
        citizenForm.clickNext();

        MarriageApplicationPage marriageApplicationPage = new MarriageApplicationPage(driver);
        MarriageData marriageData = new MarriageData(TEST_DATE, TEST_FAMILYNAME, TEST_NAME,
                TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT);
        marriageApplicationPage.fillApplicationData(marriageData);
        marriageApplicationPage.submitApplication();

        ApplicationStatusPage statusPage = new ApplicationStatusPage(driver);
        Assert.assertTrue(statusPage.isApplicationSubmitted(), "Заявка зарегистрирована");
    }
    @Test
    public void testCreateBirthApplication() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginAsUser();

        ApplicantFormPage applicantForm = new ApplicantFormPage(driver);
        applicantForm.fillApplicantData(TEST_NAME, TEST_NAME, TEST_NAME,
                TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);
        applicantForm.clickNext();

        TypeOfApplicationPage typeOfApplicationPage = new TypeOfApplicationPage(driver);
        typeOfApplicationPage.clickBirthButton();

        CitizenFormPage citizenForm = new CitizenFormPage(driver);
        citizenForm.fillCitizenData(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE,
                TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);
        citizenForm.clickNext();

        BirthApplicationPage birthApplicationPage = new BirthApplicationPage(driver);
        BirthData birthData = new BirthData(TEST_ADDRESS, TEST_NAME, TEST_NAME, TEST_NAME, TEST_NAME);
        birthApplicationPage.fillBirthApplicationData(birthData);
        birthApplicationPage.submitApplication();

        ApplicationStatusPage statusPage = new ApplicationStatusPage(driver);
        Assert.assertTrue(statusPage.isApplicationSubmitted(), "Заявка зарегистрирована");
    }
    @Test
    public void testCreateDeathApplication() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginAsUser();

        ApplicantFormPage applicantForm = new ApplicantFormPage(driver);
        applicantForm.fillApplicantData(TEST_NAME, TEST_NAME, TEST_NAME,
                TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);
        applicantForm.clickNext();

        TypeOfApplicationPage typeOfApplicationPage = new TypeOfApplicationPage(driver);
        typeOfApplicationPage.clickDeathButton();

        CitizenFormPage citizenForm = new CitizenFormPage(driver);
        citizenForm.fillCitizenData(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE,
                TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);
        citizenForm.clickNext();

        DeathApplicationPage deathApplicationPage = new DeathApplicationPage(driver);
        DeathData deathData = new DeathData(TEST_DATE, TEST_ADDRESS);
        deathApplicationPage.fillDeathApplicationData(deathData);
        deathApplicationPage.submitApplication();

        ApplicationStatusPage statusPage = new ApplicationStatusPage(driver);
        Assert.assertTrue(statusPage.isApplicationSubmitted(), "Заявка зарегистрирована");
    }
}
