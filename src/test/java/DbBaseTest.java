import com.test.steps.DatabaseApplicantSteps;
import com.test.steps.DatabaseStaffSteps;
import org.testng.annotations.BeforeMethod;

public class DbBaseTest {

    DatabaseApplicantSteps dbApplicantSteps;
    DatabaseStaffSteps dbStaffSteps;

    @BeforeMethod
    public void setUp() {
        initSteps();
    }

    public void initSteps(){
        dbApplicantSteps = new DatabaseApplicantSteps();
        dbStaffSteps = new DatabaseStaffSteps();
    }
}
