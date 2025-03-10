package com.test.steps;

import com.test.pojo.RequestProcess;
import com.test.pojo.ResponseProcess;
import com.test.utils.RequestResponseFilter;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.extern.log4j.Log4j2;

import static com.test.constants.UrlConstants.REQUEST_PROCESS;
import static com.test.specifications.Specifications.requestSpecification;
import static com.test.specifications.Specifications.responseSpecification;
import static io.restassured.RestAssured.given;

@Log4j2
public class ProcessSteps {

    @Step
    public static RequestProcess createProcessRequest(Integer applicationId, Integer staffId, String action) {
        log.info("Creating process request with applicationId: {}, staffId: {}, action: {}", applicationId, staffId, action);

        RequestProcess request = RequestProcess.builder()
                .applId(applicationId)
                .staffid(staffId)
                .action(action)
                .build();

        log.info("Process request created: {}", request);

        return request;
    }

    @Step("Starting POST request '{request}' and getting response")
    public static ResponseProcess createAndValidateProcess(RequestProcess request) {
        log.info("Starting POST request to process the request: {}", request);

        ResponseProcess response = given()
                .filter(new RequestResponseFilter())
                .spec(requestSpecification())
                .body(request)
                .log().all()
                .when()
                .post(REQUEST_PROCESS)
                .then()
                .spec(responseSpecification())
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/response_process_schema.json"))
                .extract().as(ResponseProcess.class);

        log.info("POST request completed successfully. Response: {}", response);

        return response;
    }
}
