package com.restassured.learning.test;

import static com.restassured.learning.util.Constants.*;

import com.restassured.learning.util.PropertyFileReader;
import io.restassured.RestAssured;
import static io.restassured.http.ContentType.*;

import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public final RequestSpecification requestSpec = RestAssured.given();

    @BeforeMethod
    public void setRequestSpecification() {
        requestSpec
                .contentType(JSON)
                .accept(JSON)
                .header(new Header(AUTHENTICATION_HEADER_NAME, AUTHENTICATION_HEADER_VALUE_PREFIX + PropertyFileReader.propertyReader("auth_key")));
    }

}
