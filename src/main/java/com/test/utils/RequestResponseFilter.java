package com.test.utils;

import io.qameta.allure.Attachment;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class RequestResponseFilter implements Filter {

    private static final List<String> apiLogs = new ArrayList<>();

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec, FilterContext ctx) {
        logRequest(requestSpec);
        Response response = ctx.next(requestSpec, responseSpec);
        logResponse(response);
        return response;
    }

    private void logRequest(FilterableRequestSpecification requestSpec) {
        String requestDetails = "Request: \n" +
                "Method: " + requestSpec.getMethod() + "\n" +
                "URL: " + requestSpec.getURI() + "\n" +
                "Headers: " + requestSpec.getHeaders() + "\n" +
                "Body: " + requestSpec.getBody();
        log.info(requestDetails);
        attachRequestDetails(requestDetails);
    }

    private void logResponse(Response response) {
        String responseDetails = "Response: \n" +
                "Status Code: " + response.getStatusCode() + "\n" +
                "Body: " + response.getBody().asString();
        log.info(responseDetails);
        attachResponseDetails(responseDetails);
    }

    @Attachment(value = "API Request", type = "text/plain")
    private String attachRequestDetails(String requestDetails) {
        return requestDetails;
    }

    @Attachment(value = "API Response", type = "text/plain")
    private String attachResponseDetails(String responseDetails) {
        return responseDetails;
    }

    public static List<String> getApiLogs() {
        return new ArrayList<>(apiLogs);  // Возвращаем копию логов
    }
}
