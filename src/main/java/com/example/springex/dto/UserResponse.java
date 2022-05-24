package com.example.springex.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private long id;
    private String name;
    private String email;
    private int age;
    private Boolean loanData;
    private boolean isActive;

}
