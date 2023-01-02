package com.restassured.learning.util;

public class Constants {

    // Directories

    // Path params
    public static final String USER_ID_PATH_PARAM_NAME = "userId";
    public static final String USER_ID_PATH_PARAM = PropertyFileReader.propertyReader("user_id_path_param");

    // Urls
    public static final String API_BASE_URL = PropertyFileReader.propertyReader("api_base_url");
    public static final String USERS_ENDPOINT = PropertyFileReader.propertyReader("users_endpoint");
    public static final String GET_USER_ENDPOINT = USERS_ENDPOINT + USER_ID_PATH_PARAM;

    // Headers
    public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
    public static final String AUTHENTICATION_HEADER_VALUE_PREFIX = "Bearer ";

    // Fake email domain
    public static final String FAKE_EMAIL_DOMAIN = "@learnapiautomation.ra";
}
