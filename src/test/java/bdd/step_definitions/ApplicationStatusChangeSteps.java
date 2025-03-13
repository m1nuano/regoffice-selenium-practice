package bdd.step_definitions;

import com.test.components.AdminTableRows;
import com.test.drivers.WebDriverSingleton;
import com.test.models.BirthData;
import com.test.steps.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import static com.test.constants.TestConstants.*;

public class ApplicationStatusChangeSteps {

    private MainPageSteps mainPageSteps;
    private ApplicantPageSteps applicantPageSteps;
    private TypeOfApplicationSteps typeOfApplicationSteps;
    private CitizenSteps citizenSteps;
    private BirthAppSteps birthAppSteps;
    private AppStatusSteps appStatusSteps;
    private AdminPageSteps adminPageSteps;
    private AdminTableSteps adminTableSteps;

    private AdminTableRows adminTableRow;

    public ApplicationStatusChangeSteps() {
        init();
    }

    private void init() {
        WebDriver driver = WebDriverSingleton.getDriver();
        mainPageSteps = new MainPageSteps(driver);
        applicantPageSteps = new ApplicantPageSteps(driver);
        typeOfApplicationSteps = new TypeOfApplicationSteps(driver);
        citizenSteps = new CitizenSteps(driver);
        birthAppSteps = new BirthAppSteps(driver);
        appStatusSteps = new AppStatusSteps(driver);
        adminPageSteps = new AdminPageSteps(driver);
        adminTableSteps = new AdminTableSteps(driver);
    }

    @Given("birth application is created")
    @Step("Creating birth application")
    public void birthApplicationIsCreated() {
        mainPageSteps.chooseRole(TEST_USERROLE);
        applicantPageSteps.fillApplicantFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_ADDRESS);
        typeOfApplicationSteps.chooseApplication(APP_BIRTH);
        citizenSteps.fillCitizenFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT, TEST_GENDER, TEST_ADDRESS);

        BirthData birthData = new BirthData(TEST_ADDRESS, TEST_NAME, TEST_NAME, TEST_NAME, TEST_NAME);
        birthAppSteps.fillBirthApplicationPage(birthData);
        appStatusSteps.performAction(BUTTON_CLOSE);
    }

    @And("logging in as admin")
    @Step("Logging in as admin")
    public void loginAsAdmin() {
        mainPageSteps.chooseRole(TEST_ADMINROLE);
        adminPageSteps.fillAdminFormPage(TEST_NAME, TEST_NAME, TEST_NAME, TEST_PHONE, TEST_PASSPORT, TEST_DATE);
    }


    @When("I perform action with '{string}' icon to change the application status to {string}")
    @Step("Performing action {0} to change application status")
    public void performActionToChangeStatus(String actionType, String expectedStatus) {
        adminTableRow = adminTableSteps.getFirstRow();
        adminTableRow.performAction(actionType, expectedStatus);
    }

    @Then("the application status should be {string}")
    @Step("Verifying that application status is {0}")
    public void verifyApplicationStatus(String expectedStatus) {
        adminTableRow = adminTableSteps.getFirstRow();
        String actualStatus = adminTableRow.getApplicationStatus();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualStatus, expectedStatus, "The status of the application does not equals with the expected");
        softAssert.assertAll();
    }
}
