package com.test.steps;

import com.test.dao.StaffDAO;
import com.test.entity.Staff;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

import static com.test.constants.TestConstants.*;

@Log4j2
public class DatabaseStaffSteps {
    private final StaffDAO staffDAO;

    public DatabaseStaffSteps() {
        this.staffDAO = new StaffDAO();
    }

    @Step("Creating and saving staff into db")
    public Staff createAndSaveStaff() {
        log.info("Creating and saving staff into db");
        Staff staff = new Staff();
        staff.setSurname(TEST_NAME);
        staff.setName(TEST_NAME);
        staff.setMiddleName(TEST_NAME);
        staff.setDateOfBirth(LocalDate.parse(TEST_DATE));
        staff.setPassportNumber(TEST_PASSPORT);
        staff.setPhoneNumber(TEST_PHONE);

        staffDAO.save(staff);
        return staff;
    }

    @Step("Getting staff with id: '{staffId}' from db")
    public Staff getStaffFromDb(Long staffId) {
        log.info("Getting staff with id: {} from db", staffId);
        return staffDAO.findById(staffId);
    }
}
