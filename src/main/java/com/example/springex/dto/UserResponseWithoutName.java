package com.example.springex.dto;

import com.example.springex.entitiy.User;

public class UserResponseWithoutName {
    private long id;
    private String email;
    private int age;
    private boolean isActivate;

    public UserResponseWithoutName(){}
    public UserResponseWithoutName(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.age= user.getAge();
        this.isActivate = user.isActivate();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.age= user.getAge();
        this.isActivate = user.isActivate();
    }

    // setter로 User를 받는다
    // Constructor에 User를 받는다
    // static method로 User를 받는다

    public static UserResponseWithoutName convert(User user) {
        UserResponseWithoutName response = new UserResponseWithoutName();
        response.id = user.getId();
        response.email = user.getEmail();
        response.age= user.getAge();
        response.isActivate = user.isActivate();
        return response;
    }
}
