package com.test.steps;

import com.test.dao.ApplicantDAO;
import com.test.entity.Applicant;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.test.constants.TestConstants.*;

@Log4j2
public class DatabaseApplicantSteps {
    private final ApplicantDAO applicantDAO;

    public DatabaseApplicantSteps() {
        this.applicantDAO = new ApplicantDAO();
    }

    @Step("Creating and saving applicant into db")
    public Applicant createAndSaveApplicant() {
        log.info("Creating and saving applicant into db");
        Applicant applicant = new Applicant();
        applicant.setSurname(TEST_NAME);
        applicant.setName(TEST_NAME);
        applicant.setMiddleName(TEST_NAME);
        applicant.setPassportNumber(TEST_PASSPORT);
        applicant.setPhoneNumber(TEST_PHONE);
        applicant.setRegistrationAddress(TEST_ADDRESS);

        applicantDAO.save(applicant);
        return applicant;
    }

    @Step("Getting applicant with id: '{applicantId}' from db")
    public Applicant getApplicantFromDb(Long applicantId) {
        log.info("Getting applicant with id: {} from db", applicantId);
        return applicantDAO.findById(applicantId);
    }
}
