package com.example.springex.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.User;
@Getter @Setter

public class UserRequest {
    private String name;
    private String email;
    private String password;
    private int age;
    private Boolean loanData;
    private int isActive;
}
