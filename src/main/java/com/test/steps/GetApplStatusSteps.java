package com.test.steps;

import com.test.pojo.GetApplStatusResponse;
import com.test.utils.RequestResponseFilter;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.extern.log4j.Log4j2;

import static com.test.constants.UrlConstants.GET_APPL_STATUS;
import static com.test.specifications.Specifications.requestSpecification;
import static com.test.specifications.Specifications.responseSpecification;
import static io.restassured.RestAssured.given;

@Log4j2
public class GetApplStatusSteps {

    @Step("Starting GET request to fetch application status for applicationId: {} and getting response")
    public static GetApplStatusResponse createAndValidateStatus(int applicationId) {
        log.info("Starting GET request to fetch application status for applicationId: {}", applicationId);

        GetApplStatusResponse response = given()
                .filter(new RequestResponseFilter())
                .spec(requestSpecification())
                .when()
                .get(String.format(GET_APPL_STATUS, applicationId))
                .then()
                .spec(responseSpecification())
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_appl_status_response_schema.json"))
                .extract().as(GetApplStatusResponse.class);

        log.info("GET request completed successfully. Application status: {}", response);

        return response;
    }
}
