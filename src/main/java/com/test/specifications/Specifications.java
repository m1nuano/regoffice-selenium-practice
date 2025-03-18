package com.test.specifications;

import com.test.config.ConfigProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.notNullValue;

public class Specifications {

    public static RequestSpecification requestSpecification(){
        String url = ConfigProperties.getProperty("APP_URL");
        String username = ConfigProperties.getProperty("APP_USERNAME");
        String password = ConfigProperties.getProperty("APP_PASSWORD");
        String formattedUrl = String.format(url, username, password);

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
