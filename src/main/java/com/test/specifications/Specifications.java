package com.test.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.test.constants.UrlConstants.BASE_URL;
import static org.hamcrest.Matchers.notNullValue;

public class Specifications {

    public static RequestSpecification requestSpecification(){
        String username = System.getenv("APP_USERNAME");
        String password = System.getenv("APP_PASSWORD");
        String formattedUrl = String.format(BASE_URL, username, password);

        return new RequestSpecBuilder()
                .setBaseUri(formattedUrl)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecification(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectBody("requestId", notNullValue())
                .build();
    }
}
