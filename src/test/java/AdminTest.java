import com.test.components.AdminTableRows;
import com.test.constants.TestConstants;
import com.test.pages.AdminTablePage;
import com.test.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AdminTest extends BaseAdminTest implements TestConstants {

    @Test
    public void testAdminTable() {
        AdminTableRows adminTableRows = getAdminTableRows();
        System.out.println("Номер заявки: " + adminTableRows.getRequestNumber());
        System.out.println("Заявитель: " + adminTableRows.getApplicantName());
        System.out.println("Тип: " + adminTableRows.getApplicationType());
        System.out.println("Время: " + adminTableRows.getApplicationTime());
        System.out.println("Статус: " + adminTableRows.getApplicationStatus());

        Assert.assertEquals(adminTableRows.getRequestNumber(), applicationNumber);
    }

    @Test
    public void testAdminChangeApplicationStatus() {
        AdminTableRows row = getAdminTableRows();

        row.approve();
        String approvedStatus = row.getApplicationStatus();
        Assert.assertEquals("Одобрена", approvedStatus, "Статус заявки был изменён на 'Одобрена'");

        row.reject();
        String rejectedStatus = row.getApplicationStatus();
        Assert.assertEquals("Отклонена", rejectedStatus, "Статус заявки был изменён на 'Отклонена'");
    }

    private AdminTableRows getAdminTableRows() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginAsAdmin();

        AdminTablePage adminTable = new AdminTablePage(driver);
        List<AdminTableRows> rowsList = adminTable.getAllRequestRows();

        AdminTableRows row = rowsList.get(0);
        return row;
    }
}
