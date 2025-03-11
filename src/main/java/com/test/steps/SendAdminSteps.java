package com.test.steps;

import com.test.pojo.SendAdminRequest;
import com.test.pojo.SendAdminResponse;
import com.test.utils.RequestResponseFilter;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.extern.log4j.Log4j2;

import static com.test.constants.TestConstants.*;
import static com.test.constants.UrlConstants.SEND_ADMIN_REQUEST;
import static com.test.specifications.Specifications.requestSpecification;
import static com.test.specifications.Specifications.responseSpecification;
import static io.restassured.RestAssured.given;

@Log4j2
public class SendAdminSteps {

    @Step
    public static SendAdminRequest createAdminRequest() {
        log.info("Creating admin request");

        SendAdminRequest request = SendAdminRequest.builder()
                .personalLastName(TEST_NAME)
                .personalFirstName(TEST_NAME)
                .personalMiddleName(TEST_NAME)
                .personalPhoneNumber(TEST_PHONE)
                .personalNumberOfPassport(TEST_PASSPORT)
                .dateOfBirth(TEST_DATE)
                .build();

        log.info("Admin request created: {}", request);

        return request;
    }

    @Step("Starting POST request '{request}' and getting response")
    public static SendAdminResponse createAndValidateAdmin(SendAdminRequest request) {
        log.info("Starting POST request to send admin request: {}", request);

        SendAdminResponse response = given()
                .filter(new RequestResponseFilter())
                .spec(requestSpecification())
                .body(request)
                .when()
                .post(SEND_ADMIN_REQUEST)
                .then()
                .spec(responseSpecification())
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/send_admin_response_schema.json"))
                .extract().as(SendAdminResponse.class);

        log.info("POST request completed successfully. Response: {}", response);

        return response;
    }
}
