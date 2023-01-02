package com.restassured.learning.api.data.model.user;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
@Data
public class User {
    private int id;
    private String gender;
    private String name;
    private String email;
    private String status;
}
