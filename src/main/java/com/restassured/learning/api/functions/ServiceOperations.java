package com.restassured.learning.api.functions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ServiceOperations {


    private ServiceOperations() {

    }


    public static Response getData(RequestSpecification payload, String baseURI, String searchField) {

        log.info("Get Request Triggered for : " + baseURI + searchField);
        return payload.get(baseURI + searchField);
    }

    public static Response getData(RequestSpecification payload, String baseURI) {

        log.info("Get Request Triggered for : " + baseURI);
        return payload.get(baseURI);
    }

    public static Response postData(RequestSpecification payload, String baseURL) {

        log.info("Post Request Triggered for : " + baseURL);
        return payload.post(baseURL);
    }

    public static Response updateData(RequestSpecification payload, String baseURL, String updateID) {

        log.debug("Update Request Triggered for : " + baseURL + updateID);
        return payload.put(baseURL + updateID);
    }


    public static Response deleteData(RequestSpecification payload, String baseURL, String deleteID) {

        log.info("Delete Request Triggered for : " + baseURL + deleteID);
        return payload.delete(baseURL + deleteID);
    }

    //URL Only Requests
    public static Response deleteData(RequestSpecification payload, String baseURL) {

        log.info("Delete Request Triggered for : " + baseURL );
        return payload.delete(baseURL);
    }

    public static Response updateData(RequestSpecification payload, String baseURL) {

        log.debug("Update Request Triggered for : " + baseURL);
        return payload.put(baseURL);
    }


}
