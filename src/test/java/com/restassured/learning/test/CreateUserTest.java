package com.restassured.learning.test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.restassured.learning.api.data.model.user.Data;
import com.restassured.learning.api.data.model.user.User;
import com.restassured.learning.api.data.model.user.UserResponse;
import com.restassured.learning.api.functions.ServiceOperations;
import com.restassured.learning.util.Constants;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.restassured.learning.util.Constants.API_BASE_URL;
import static com.restassured.learning.util.Constants.USERS_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseTest{

    @Test(description = "Verify that a user creation api using json object")
    public void testUserCreation() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fullName = firstName + " " + lastName;
        String email = firstName + lastName + Constants.FAKE_EMAIL_DOMAIN;
        String gender = "male";
        String status = "active";

        JSONObject userRequestJson = new JSONObject();
        userRequestJson.put("email", email);
        userRequestJson.put("name", fullName);
        userRequestJson.put("gender", gender);
        userRequestJson.put("status", status);

        given()
                .request()
                .spec(requestSpec)
                .body(userRequestJson.toString()).log().all()
                .post(API_BASE_URL+USERS_ENDPOINT)
                .then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("data.name", equalTo(fullName))
                .body("data.email", equalTo(email))
                .body("data.gender", equalTo(gender))
                .body("data.status", equalTo(status));
    }

    @Test(description = "Verify that a user creation api using java object")
    public void testUserCreationTwo() throws JsonProcessingException {
        SoftAssert softAssert=new SoftAssert();
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fullName = firstName + " " + lastName;
        String email = firstName + lastName + Constants.FAKE_EMAIL_DOMAIN;
        String gender = "male";
        String status = "active";

        User user=new User();
        user.setGender(gender);
        user.setEmail(email);
        user.setName(fullName);
        user.setStatus(status);

        RequestSpecification requestSpecification=requestSpec.body(user);
        Response response = ServiceOperations.postData(requestSpecification,API_BASE_URL+USERS_ENDPOINT);
        UserResponse userResponse=objectMapper.readValue(response.getBody().asString(), UserResponse.class);


        softAssert.assertEquals(response.getStatusCode(),200);

        softAssert.assertEquals(userResponse.getData().getEmail(),user.getEmail());
        softAssert.assertEquals(userResponse.getData().getStatus(),user.getStatus());
        softAssert.assertEquals(userResponse.getData().getGender(),user.getGender());
        softAssert.assertEquals(userResponse.getData().getName(),user.getName());
        softAssert.assertAll();

    }

    @Test(description = "Verify that a user get api using java object")
    public void testUserGet() throws JsonProcessingException {
        SoftAssert softAssert=new SoftAssert();
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fullName = firstName + " " + lastName;
        String email = firstName + lastName + Constants.FAKE_EMAIL_DOMAIN;
        String gender = "male";
        String status = "active";

        User user=new User();
        user.setGender(gender);
        user.setEmail(email);
        user.setName(fullName);
        user.setStatus(status);

        RequestSpecification requestSpecification=requestSpec.body(user);
        Response response = ServiceOperations.postData(requestSpecification,API_BASE_URL+USERS_ENDPOINT);
        UserResponse userResponse=objectMapper.readValue(response.getBody().asString(), UserResponse.class);


        softAssert.assertEquals(response.getStatusCode(),200);

        Response responseGet = ServiceOperations.getData(requestSpec,API_BASE_URL+USERS_ENDPOINT+"/"+userResponse.getData().getId());

        softAssert.assertEquals(responseGet.getStatusCode(),200);
        softAssert.assertAll();


    }

    @Test(description = "Verify that a user Delete api using java object")
    public void testUserDelete() throws JsonProcessingException {
        SoftAssert softAssert=new SoftAssert();
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fullName = firstName + " " + lastName;
        String email = firstName + lastName + Constants.FAKE_EMAIL_DOMAIN;
        String gender = "male";
        String status = "active";

        User user=new User();
        user.setGender(gender);
        user.setEmail(email);
        user.setName(fullName);
        user.setStatus(status);

        RequestSpecification requestSpecification=requestSpec.body(user);
        Response response = ServiceOperations.postData(requestSpecification,API_BASE_URL+USERS_ENDPOINT);
        UserResponse userResponse=objectMapper.readValue(response.getBody().asString(), UserResponse.class);
        JSONObject jsonObjectCreate = new JSONObject(response.getBody().asString());


        softAssert.assertEquals(jsonObjectCreate.get("code"),201);


        Response responseDelete = ServiceOperations.deleteData(requestSpec,API_BASE_URL+USERS_ENDPOINT+"/"+userResponse.getData().getId());
        JSONObject jsonObjectDelete = new JSONObject(responseDelete.getBody().asString());

        softAssert.assertEquals(jsonObjectDelete.get("code"),204);



        Response responseGet = ServiceOperations.getData(requestSpec,API_BASE_URL+USERS_ENDPOINT+"/"+userResponse.getData().getId());

        JSONObject jsonObjectGet = new JSONObject(responseGet.getBody().asString());
        softAssert.assertEquals(jsonObjectGet.get("code"),404);

        softAssert.assertAll();


    }

}
