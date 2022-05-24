package com.example.springex.entitiy;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private long id;
    private String name;
    private String email;
    private String password;
    private int age;
    private Boolean loanData;
    private boolean isActivate;

}
