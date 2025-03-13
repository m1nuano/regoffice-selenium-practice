package bdd.step_definitions;

import com.test.components.AdminTableRows;
import com.test.config.TestConfig;
import com.test.models.BirthData;
import com.test.steps.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

import static com.test.constants.TestConstants.*;

@CucumberContextConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class ApplicationStatusChangeSteps {

    private final MainPageSteps mainPageSteps;
    private final ApplicantPageSteps applicantPageSteps;
    private final TypeOfApplicationSteps typeOfApplicationSteps;
    private final CitizenSteps citizenSteps;
    private final BirthAppSteps birthAppSteps;
    private final AppStatusSteps appStatusSteps;
    private final AdminPageSteps adminPageSteps;
    private final AdminTableSteps adminTableSteps;
    private AdminTableRows adminTableRow;

    @Autowired
    public ApplicationStatusChangeSteps(MainPageSteps mainPageSteps,
                                        ApplicantPageSteps applicantPageSteps,
                                        TypeOfApplicationSteps typeOfApplicationSteps,
                                        CitizenSteps citizenSteps,
                                        BirthAppSteps birthAppSteps,
                                        AppStatusSteps appStatusSteps,
                                        AdminPageSteps adminPageSteps,
                                        AdminTableSteps adminTableSteps) {
        this.mainPageSteps = mainPageSteps;
        this.applicantPageSteps = applicantPageSteps;
        this.typeOfApplicationSteps = typeOfApplicationSteps;
        this.citizenSteps = citizenSteps;
        this.birthAppSteps = birthAppSteps;
        this.appStatusSteps = appStatusSteps;
        this.adminPageSteps = adminPageSteps;
        this.adminTableSteps = adminTableSteps;
    }

    @Given("I create the birth application")
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

    @And("I log in as admin")
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

    @Then("I check that the status of the application is {string}")
    @Step("Verifying that application status is {0}")
    public void verifyApplicationStatus(String expectedStatus) {
        adminTableRow = adminTableSteps.getFirstRow();
        String actualStatus = adminTableRow.getApplicationStatus();

        Assert.assertEquals(actualStatus, expectedStatus, "The status of the application does not equals with the expected");
    }
}
