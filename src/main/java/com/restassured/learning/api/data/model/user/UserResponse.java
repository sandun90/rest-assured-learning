package com.restassured.learning.api.data.model.user;

import lombok.Data;

@Data
public class UserResponse {
    private int code;
    private String meta;
    private com.restassured.learning.api.data.model.user.Data data;
}
