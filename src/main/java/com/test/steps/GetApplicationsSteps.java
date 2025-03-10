package com.test.steps;

import com.test.pojo.GetApplicationsResponse;
import com.test.utils.RequestResponseFilter;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.extern.log4j.Log4j2;

import static com.test.constants.UrlConstants.GET_APPLICATIONS;
import static com.test.specifications.Specifications.requestSpecification;
import static com.test.specifications.Specifications.responseSpecification;
import static io.restassured.RestAssured.given;

@Log4j2
public class GetApplicationsSteps {

    @Step("Starting GET request to fetch all applications and getting response")
    public static GetApplicationsResponse sendGetRequest() {
        log.info("Starting GET request to fetch applications");

        GetApplicationsResponse response = given()
                .filter(new RequestResponseFilter())
                .spec(requestSpecification())
                .when()
                .get(GET_APPLICATIONS)
                .then()
                .spec(responseSpecification())
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_applications_response_schema.json"))
                .extract().as(GetApplicationsResponse.class);

        log.info("GET request completed successfully. Response: {}", response);

        return response;
    }
}
