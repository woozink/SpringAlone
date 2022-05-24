package com.example.springex.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BookRequest {
    private String name;
    private String author;
    private String page;
    private boolean isActivate;
    private boolean isRented;
}