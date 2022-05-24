package com.example.springex.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
public class LoanRequest {
    private long userId;
    private long bookId;
    private Boolean isRented;
}
