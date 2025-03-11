package com.test.steps;

import com.test.models.AppData;
import com.test.pojo.SendUserRequest;
import com.test.pojo.SendUserResponse;
import com.test.utils.RequestResponseFilter;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

import static com.test.constants.TestConstants.*;
import static com.test.constants.UrlConstants.SEND_USER_REQUEST;
import static com.test.specifications.Specifications.requestSpecification;
import static com.test.specifications.Specifications.responseSpecification;
import static io.restassured.RestAssured.given;

@Log4j2
public class SendUserSteps {

    @Step("Creating user application request with '{mode}' mode}")
    public static SendUserRequest createApplicationRequest(String mode, AppData data) {
        log.info("Creating user application request for mode: {}", mode);

        SendUserRequest.SendUserRequestBuilder builder = SendUserRequest.builder()
                .mode(mode)
                .personalLastName(TEST_NAME)
                .personalFirstName(TEST_NAME)
                .personalMiddleName(TEST_NAME)
                .personalPhoneNumber(data.getPhone())
                .personalNumberOfPassport(data.getPassport())
                .personalAddress(data.getAddress())
                .citizenLastName(TEST_NAME)
                .citizenFirstName(TEST_NAME)
                .citizenMiddleName(TEST_NAME)
                .citizenBirthDate(data.getDate())
                .citizenNumberOfPassport(data.getPassport())
                .citizenGender(data.getGender())
                .citizenAddress(data.getAddress());

        Optional.ofNullable(data.getBirthData()).ifPresent(birthData -> builder
                .birthPlace(birthData.getBirthAddress())
                .birthMother(birthData.getMother())
                .birthFather(birthData.getFather())
                .birthGrandpa(birthData.getGrandpa())
                .birthGrandma(birthData.getGrandma()));

        Optional.ofNullable(data.getMarriageData()).ifPresent(marriageData -> builder
                .dateOfMarriage(marriageData.getDateOfRegistration())
                .newLastName(marriageData.getNewLastName())
                .anotherPersonLastName(marriageData.getLastNameOfSpouse())
                .anotherPersonFirstName(marriageData.getFirstNameOfSpouse())
                .anotherPersonMiddleName(marriageData.getMiddleNameOfSpouse())
                .birthOfAnotherPerson(marriageData.getDateOfBirthOfSpouse())
                .anotherPersonPassport(marriageData.getPassportNumberOfSpouse()));

        Optional.ofNullable(data.getDeathData()).ifPresent(deathData -> builder
                .deathDateOfDeath(deathData.getDateOfDeath())
                .deathPlaceOfDeath(deathData.getPlaceOfDeath()));

        SendUserRequest request = builder.build();

        log.info("User application request created: {}", request);

        return request;
    }

    @Step("Starting POST request '{request}' and getting response")
    public static SendUserResponse createAndValidateUser(SendUserRequest request) {
        log.info("Starting POST request to send user request: {}", request);

        SendUserResponse response = given()
                .filter(new RequestResponseFilter())
                .spec(requestSpecification())
                .body(request)
                .log().all()
                .when()
                .post(SEND_USER_REQUEST)
                .then()
                .spec(responseSpecification())
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/send_user_response_schema.json"))
                .extract().as(SendUserResponse.class);

        log.info("POST request completed successfully. Response: {}", response);

        return response;
    }
}
